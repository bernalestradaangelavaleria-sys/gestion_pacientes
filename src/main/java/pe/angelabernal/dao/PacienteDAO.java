package pe.angelabernal.dao;

import pe.angelabernal.config.Conexion;
import pe.angelabernal.modelo.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public List<Paciente> listar() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM pacientes ORDER BY id DESC";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                pacientes.add(mapear(rs));
            }
        }
        return pacientes;
    }

    public Paciente buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pacientes WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public boolean insertar(Paciente p) throws SQLException {
        String sql = "INSERT INTO pacientes "
                + "(nombres, apellidos, dni, fecha_nacimiento, telefono, correo, direccion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            completarParametros(ps, p);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Paciente p) throws SQLException {
        String sql = "UPDATE pacientes SET nombres=?, apellidos=?, dni=?, "
                + "fecha_nacimiento=?, telefono=?, correo=?, direccion=? WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            completarParametros(ps, p);
            ps.setInt(8, p.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM pacientes WHERE id=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private void completarParametros(PreparedStatement ps, Paciente p) throws SQLException {
        ps.setString(1, p.getNombres());
        ps.setString(2, p.getApellidos());
        ps.setString(3, p.getDni());
        ps.setDate(4, Date.valueOf(p.getFechaNacimiento()));
        ps.setString(5, p.getTelefono());
        ps.setString(6, p.getCorreo());
        ps.setString(7, p.getDireccion());
    }

    private Paciente mapear(ResultSet rs) throws SQLException {
        return new Paciente(
                rs.getInt("id"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getString("dni"),
                rs.getDate("fecha_nacimiento").toLocalDate(),
                rs.getString("telefono"),
                rs.getString("correo"),
                rs.getString("direccion")
        );
    }
}
