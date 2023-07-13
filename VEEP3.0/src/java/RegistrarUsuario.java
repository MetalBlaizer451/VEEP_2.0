/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistrarUsuario.servlet", urlPatterns = {"/RegistrarUsuario"})
public class RegistrarUsuario extends HttpServlet {
   
    private final String DB_URL = "jdbc:mysql://localhost:3308/registros";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los datos del formulario
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String contrasena = request.getParameter("contrasena");

        // Guardar los datos en la base de datos
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String query = "INSERT INTO usuarios (nombre, email, contrasena) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, email);
            statement.setString(3, contrasena);
            statement.executeUpdate();

            statement.close();
            conn.close();

            // Redireccionar a una página de éxito o mostrar un mensaje de éxito
            response.sendRedirect("registro_exitoso.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            // Manejar cualquier error que ocurra durante el proceso
            e.printStackTrace();
            response.sendRedirect("registro_error.jsp");
        }
    }
}
