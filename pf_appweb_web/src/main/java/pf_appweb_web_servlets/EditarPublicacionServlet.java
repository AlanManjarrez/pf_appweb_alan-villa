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
import java.util.Calendar;
import pf_appweb_negocio_DTOS.PostDTO;
import pf_appweb_negocio_DTOS.UsuarioDTO;
import pf_appweb_negocio_controles.ControlPost;
import pf_appweb_negocio_interfaces.IControlPost;

/**
 *
 * @author uirtis
 */
@WebServlet(name = "EditarPublicacionServlet", urlPatterns = {"/EditarPublicacionServlet"})
public class EditarPublicacionServlet extends HttpServlet {

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
            out.println("<title>Servlet EditarPublicacionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarPublicacionServlet at " + request.getContextPath() + "</h1>");
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
        String postIdParam = request.getParameter("postId");

        try {
            if (postIdParam != null) {
                long postId = Long.parseLong(postIdParam);
                ControlPost postControl = new ControlPost();
                PostDTO postDTO = postControl.obtenerPostId(postId);

                if (postDTO != null) {
                    session.setAttribute("postDTO", postDTO);
                    session.setAttribute("postId", postDTO.getId());
                    session.setAttribute("titulo", postDTO.getTitulo());
                    session.setAttribute("contenido", postDTO.getContenido());
                    request.getRequestDispatcher("EditarPublicacion.jsp").forward(request, response);
                } else {
                    response.sendRedirect("PublicacionesServlet?error=postNotFound");
                }
            } else {
                response.sendRedirect("PublicacionesServlet?error=invalidId");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("PublicacionesServlet?error=internalError");
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
        JsonObject responseJson = new JsonObject();
        HttpSession session = request.getSession();

        try {
            UsuarioDTO usuarioDTO = (UsuarioDTO) request.getSession().getAttribute("usuarioDTO");
            PostDTO postDTO = (PostDTO) request.getSession().getAttribute("postDTO");
            String postIdParam = request.getParameter("postId");
            String titulo = request.getParameter("titulo");
            String contenido = request.getParameter("contenido");
           

            if (usuarioDTO == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"success\": false, \"message\": \"Usuario no autenticado. Por favor inicie sesión.\"}");
                return;
            }

            if (postDTO.getId() == null || titulo == null || contenido == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                responseJson.addProperty("error", "Faltan parámetros necesarios.");
            } else {

                //Long postId = postIdParam;
                IControlPost controlPost = new ControlPost();

                if (postDTO == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    responseJson.addProperty("error", "La publicación no existe.");
                } else if (postDTO.getAnclado()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    responseJson.addProperty("error", "Las publicaciones ancladas no pueden ser editadas.");
                } else {
                   
                    postDTO.setTitulo(titulo);
                    postDTO.setContenido(contenido);
                    postDTO.setFechaHoraEdicion(Calendar.getInstance());
                    
                    boolean isUpdated = controlPost.editarPost(postDTO);
                    session.setAttribute("publicaciones", controlPost.obtenerPost());
                    session.setAttribute("anclados", controlPost.obtenerPostAnclados());

                    if (isUpdated) {
                        response.setStatus(HttpServletResponse.SC_OK);
                        responseJson.addProperty("success", true);
                        responseJson.addProperty("message", "Publicación actualizada con éxito.");
                        response.sendRedirect("PublicacionesServlet");
                    } else {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        responseJson.addProperty("error", "No se pudo actualizar la publicación.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseJson.addProperty("error", "Ocurrió un error interno en el servidor.");
        }

        response.getWriter().write(responseJson.toString());
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
