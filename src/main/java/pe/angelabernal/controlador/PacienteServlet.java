package pe.angelabernal.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.angelabernal.dao.PacienteDAO;
import pe.angelabernal.modelo.Paciente;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/pacientes")
public class PacienteServlet extends HttpServlet {
    private final PacienteDAO dao = new PacienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        try {
            switch (accion) {
                case "nuevo" -> mostrarFormulario(request, response, null);
                case "editar" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Paciente paciente = dao.buscarPorId(id);
                    if (paciente == null) {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Paciente no encontrado");
                        return;
                    }
                    mostrarFormulario(request, response, paciente);
                }
                case "eliminar" -> {
                    dao.eliminar(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect(request.getContextPath() + "/pacientes?mensaje=eliminado");
                }
                default -> {
                    request.setAttribute("lista", dao.listar());
                    request.getRequestDispatcher("/WEB-INF/vistas/listar.jsp").forward(request, response);
                }
            }
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("No se pudo procesar la solicitud. Verifica la conexión y los datos.", e);
        }
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response, Paciente paciente)
            throws ServletException, IOException {
        request.setAttribute("paciente", paciente);
        request.getRequestDispatcher("/WEB-INF/vistas/formulario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String idTexto = request.getParameter("id");
            Paciente p = new Paciente();
            if (idTexto != null && !idTexto.isBlank()) p.setId(Integer.parseInt(idTexto));
            p.setNombres(request.getParameter("nombres").trim());
            p.setApellidos(request.getParameter("apellidos").trim());
            p.setDni(request.getParameter("dni").trim());
            p.setFechaNacimiento(LocalDate.parse(request.getParameter("fechaNacimiento")));
            p.setTelefono(request.getParameter("telefono").trim());
            p.setCorreo(request.getParameter("correo").trim());
            p.setDireccion(request.getParameter("direccion").trim());

            if (p.getNombres().isBlank() || p.getApellidos().isBlank() || !p.getDni().matches("\\d{8}")) {
                request.setAttribute("error", "Completa los campos obligatorios y escribe un DNI de 8 dígitos.");
                mostrarFormulario(request, response, p);
                return;
            }

            if (p.getId() == 0) dao.insertar(p);
            else dao.actualizar(p);

            response.sendRedirect(request.getContextPath() + "/pacientes?mensaje=guardado");
        } catch (SQLException | RuntimeException e) {
            throw new ServletException("No se pudo guardar el paciente. Revisa los datos y que el DNI no esté repetido.", e);
        }
    }
}
