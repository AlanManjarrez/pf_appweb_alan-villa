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
import java.util.Calendar;
import pf_appweb_negocio_DTOS.PostDTO;
import pf_appweb_negocio_DTOS.TipoUsuarioDTO;
import pf_appweb_negocio_DTOS.UsuarioDTO;
import pf_appweb_negocio_controles.ControlPost;
import pf_appweb_negocio_interfaces.IControlPost;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
@WebServlet(name = "CrearPublicacionServlet", urlPatterns = {"/CrearPublicacionServlet"})
public class CrearPublicacionServlet extends HttpServlet {

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
            out.println("<title>Servlet CrearPublicacionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CrearPublicacionServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String titulo = request.getParameter("titulo");
            String contenido = request.getParameter("contenido");
            String ancladoParam = request.getParameter("anclado");
            
            UsuarioDTO usuarioDTO = (UsuarioDTO) request.getSession().getAttribute("usuarioDTO");
            
            System.out.println(ancladoParam);
            
            if (usuarioDTO == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"success\": false, \"message\": \"Usuario no autenticado. Por favor inicie sesión.\"}");
                return;
            }

            PostDTO postDTO = new PostDTO();
            postDTO.setTitulo(titulo);
            postDTO.setContenido(contenido);
            postDTO.setUsuario(usuarioDTO);
            postDTO.setFechaHoraCreacion(Calendar.getInstance());
            
            if (ancladoParam == null) {
                ancladoParam = "OFF";
            }
            postDTO.setAnclado(ancladoParam.equalsIgnoreCase("ON"));

            IControlPost controlPost = new ControlPost();
            boolean exito = controlPost.crearPost(postDTO);
            HttpSession session = request.getSession();
            session.setAttribute("publicaciones", controlPost.obtenerPost());
            session.setAttribute("anclados", controlPost.obtenerPostAnclados());

            if (exito) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("{\"success\": true, \"message\": \"Publicación creada con éxito.\"}");
                response.sendRedirect("PublicacionesServlet");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"success\": false, \"message\": \"Error al crear la publicación.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\": false, \"message\": \"Ocurrió un error al procesar la solicitud.\"}");
        }
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
