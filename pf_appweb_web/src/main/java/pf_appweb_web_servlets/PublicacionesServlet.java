/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pf_appweb_web_servlets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pf_appweb_negocio_DTOS.PostDTO;
import pf_appweb_negocio_DTOS.UsuarioDTO;
import pf_appweb_negocio_controles.ControlPost;
import pf_appweb_negocio_controles.ControlUsuario;
import pf_appweb_negocio_interfaces.IControlPost;
import pf_appweb_negocio_interfaces.IControlUsuario;

/**
 *
 * @author Jesus Eduardo Villanueva Godoy 235078
 * @author Jose Alan Manjarrez Ontiveros 228982
 */
@WebServlet(name = "PublicacionesServlet", urlPatterns = {"/PublicacionesServlet"})
public class PublicacionesServlet extends HttpServlet {

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
            out.println("<title>Servlet PublicacionesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PublicacionesServlet at " + request.getContextPath() + "</h1>");
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
        UsuarioDTO usuarioDTO = (UsuarioDTO) request.getSession().getAttribute("usuarioDTO");
        if (usuarioDTO == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        try {
            IControlPost controlPost = new ControlPost();
            List<PostDTO> publicaciones = controlPost.obtenerPost();
            List<PostDTO> publicacionesAncladas = controlPost.obtenerPostAnclados();
            System.out.println("Aqui" + publicaciones.size());

            session.setAttribute("publicaciones", publicaciones);
            session.setAttribute("anclados", publicacionesAncladas);

            request.getRequestDispatcher("Publicaciones.jsp").forward(request, response);
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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject jsonResponse = new JsonObject();

        try {
            // Leer y procesar el cuerpo de la solicitud
            BufferedReader reader = request.getReader();
            JsonObject requestBody = JsonParser.parseReader(reader).getAsJsonObject();

            // Recuperar valores del cuerpo
            Long postId = requestBody.get("postId").getAsLong();

            // Controladores
            ControlPost controlPost = new ControlPost();
            IControlUsuario controlUsuario = new ControlUsuario();

            // Recuperar el post basado en el ID
            PostDTO postDTO = controlPost.obtenerPostId(postId);
            if (postDTO == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                jsonResponse.addProperty("error", "No se encontró el post solicitado.");
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            // Recuperar usuario asociado a la sesión
            HttpSession session = request.getSession();
            UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuarioDTO");

            if (usuarioDTO == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                jsonResponse.addProperty("error", "Usuario no autenticado.");
                jsonResponse.addProperty("url_redirect", "Login.jsp");
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            // Guardar datos en la sesión
            session.setAttribute("postDTO", postDTO);
            session.setAttribute("comentarios", controlPost.obtenerPostId(postId));

            // Respuesta JSON exitosa
            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("message", "Post y comentarios cargados correctamente.");
            jsonResponse.addProperty("url_redirect", "Comentarios.jsp");

        } catch (Exception e) {
            Logger.getLogger(PublicacionesServlet.class.getName()).log(Level.SEVERE, null, e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            jsonResponse.addProperty("error", "Ocurrió un error interno. Inténtalo más tarde.");
        }

        response.getWriter().write(jsonResponse.toString());
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
