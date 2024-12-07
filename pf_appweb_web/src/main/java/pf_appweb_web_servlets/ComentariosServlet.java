/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pf_appweb_web_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import pf_appweb_negocio_DTOS.ComentarioDTO;
import pf_appweb_negocio_DTOS.PostDTO;
import pf_appweb_negocio_DTOS.UsuarioDTO;
import pf_appweb_negocio_controles.ControlComentario;
import pf_appweb_negocio_controles.ControlPost;
import pf_appweb_negocio_interfaces.IControlComentario;
import pf_appweb_negocio_interfaces.IControlPost;

/**
 *
 * @author uirtis
 */
@WebServlet(name = "ComentariosServlet", urlPatterns = {"/ComentariosServlet"})
public class ComentariosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ComentariosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComentariosServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Obtener el usuario desde la sesión
        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuarioDTO");
        if (usuarioDTO == null) {
            response.sendRedirect("Login.jsp");
            System.out.println("Usuario_Nulo");
            return;
        }

        try {
            // Obtener el ID de la publicación desde los parámetros de la solicitud
            String postIdParam = request.getParameter("postId");
            if (postIdParam == null || postIdParam.isEmpty()) {
                response.sendRedirect("Publicaciones.jsp");
                return;
            }

            Long postId = Long.parseLong(postIdParam);

            // Inicializar los controladores para obtener datos
            IControlPost controlPost = new ControlPost();
            IControlComentario controlComentario = new ControlComentario();

            // Obtener la publicación seleccionada
            PostDTO postDTO = controlPost.obtenerPostId(postId);
            if (postDTO == null) {
                response.sendRedirect("Publicaciones.jsp");
                return;
            }

            // Obtener los comentarios asociados con la publicación desde la sesión
            List<ComentarioDTO> comentarios = (List<ComentarioDTO>) session.getAttribute("comentarios");

            // Si no hay comentarios en la sesión, obtenerlos desde la base de datos
            if (comentarios == null || comentarios.isEmpty()) {
                comentarios = controlComentario.obtenerComentariosPost(postId);
                // Guardar los comentarios en la sesión para futuras solicitudes
                session.setAttribute("comentarios", comentarios);
            }

            // Guardar los datos de la publicación en la sesión
            session.setAttribute("postDTO", postDTO);

            // Redirigir al JSP para mostrar la publicación y los comentarios
            response.sendRedirect("Comentarios.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
