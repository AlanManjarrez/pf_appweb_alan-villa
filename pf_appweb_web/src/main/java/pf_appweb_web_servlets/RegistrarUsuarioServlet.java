/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pf_appweb_web_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pf_appweb_negocio_DTOS.UsuarioDTO;
import pf_appweb_negocio_controles.ControlUsuario;
import pf_appweb_persistencia_entity.TipoUsuario;

/**
 *
 * @author uirtis
 */
@WebServlet(name = "RegistrarUsuarioServlet", urlPatterns = {"/RegistrarUsuarioServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class RegistrarUsuarioServlet extends HttpServlet {

    private static final String DEFAULT_AVATAR_PATH = "ruta/default.png";
    private static final String UPLOAD_DIRECTORY = "ruta";

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
            out.println("<title>Servlet RegistrarUsuarioServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrarUsuarioServlet at " + request.getContextPath() + "</h1>");
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
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telefono = request.getParameter("telefono");
        String domicilio = request.getParameter("domicilio");
        String fechaNacimiento = request.getParameter("fecha-nacimiento");
        String genero = request.getParameter("genero");

        String avatarPath = null;
        Part filePart = request.getPart("avatar");

        if (filePart != null && filePart.getSize() > 0) {
            String fileName = getFileName(filePart).replaceAll("[^a-zA-Z0-9._-]", "_");

            if (fileName != null
                    && (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))) {
                String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                // Guardar el archivo en el servidor
                avatarPath = uploadPath + File.separator + fileName;
                filePart.write(avatarPath); // Guardar el archivo

                // La ruta que se guardará en la base de datos será la ruta relativa
                avatarPath = "ruta" + "/" + fileName;
            } else {
                request.setAttribute("mensaje", "El archivo debe ser PNG o JPG.");
                request.setAttribute("tipoMensaje", "error");
                request.getRequestDispatcher("RegistroUsuario.jsp").forward(request, response);
                return;
            }
        } else {
            // Asignar la ruta de imagen por defecto si no se ha subido ninguna imagen
            avatarPath = "ruta/default.png";
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setNombreCompleto(nombre);
        usuarioDTO.setCorreo(email);
        usuarioDTO.setContrasena(password);
        usuarioDTO.setTelefono(telefono);
        usuarioDTO.setDomicilio(domicilio);
        usuarioDTO.setTipoUsuario(TipoUsuario.NORMAL);

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fechaNacimiento);
        } catch (java.text.ParseException ex) {
            Logger.getLogger(RegistrarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(fechaDate);

        usuarioDTO.setFechaNacimiento(fechaCalendar);
        usuarioDTO.setGenero(genero);

        ControlUsuario controlUsuario = new ControlUsuario();

        try {
            usuarioDTO = controlUsuario.registrarUsuario(usuarioDTO);

            if (usuarioDTO != null) {
                request.setAttribute("mensaje", "Usuario registrado correctamente.");
                request.setAttribute("tipoMensaje", "success");
                response.sendRedirect("Login.jsp");
            } else {
                request.setAttribute("mensaje", "No se pudo registrar el usuario. Inténtalo nuevamente.");
                request.setAttribute("tipoMensaje", "error");
                request.getRequestDispatcher("RegistroUsuario.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Manejar otros errores
            e.printStackTrace();
            request.setAttribute("mensaje", "Ocurrió un error inesperado. Inténtalo nuevamente.");
            request.setAttribute("tipoMensaje", "error");
            request.getRequestDispatcher("RegistroUsuario.jsp").forward(request, response);
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
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
