package archivo.municipal.dao;

import archivo.municipal.modelo.Expediente;
import archivo.municipal.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpedienteDAOImpl implements ExpedienteDAO {

    @Override
    public boolean guardar(Expediente expediente) {
        String sql = "INSERT INTO expedientes (numero_expediente, id_area, asunto, responsable, fecha_apertura, estado, ubicacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = ConexionBD.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, expediente.getNumeroExpediente());
            ps.setInt(2, expediente.getIdArea());
            ps.setString(3, expediente.getAsunto());
            ps.setString(4, expediente.getResponsable());
            ps.setDate(5, java.sql.Date.valueOf(expediente.getFechaApertura()));
            ps.setString(6, expediente.getEstado());
            ps.setString(7, expediente.getUbicacion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Expediente> listar() {
        List<Expediente> lista = new ArrayList<>();
        String sql = "SELECT e.id_expediente, e.numero_expediente, e.id_area, a.nombre AS area, e.asunto, e.responsable, e.fecha_apertura, e.estado, e.ubicacion " +
                     "FROM expedientes e INNER JOIN areas a ON e.id_area = a.id_area ORDER BY e.id_expediente DESC";
        try (Connection cn = ConexionBD.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Expediente expediente = new Expediente();
                expediente.setId(rs.getInt("id_expediente"));
                expediente.setNumeroExpediente(rs.getString("numero_expediente"));
                expediente.setIdArea(rs.getInt("id_area"));
                expediente.setArea(rs.getString("area"));
                expediente.setAsunto(rs.getString("asunto"));
                expediente.setResponsable(rs.getString("responsable"));
                expediente.setFechaApertura(rs.getDate("fecha_apertura").toLocalDate());
                expediente.setEstado(rs.getString("estado"));
                expediente.setUbicacion(rs.getString("ubicacion"));
                lista.add(expediente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Expediente> buscar(String texto) {
        List<Expediente> lista = new ArrayList<>();
        String sql = "SELECT e.id_expediente, e.numero_expediente, e.id_area, a.nombre AS area, e.asunto, e.responsable, e.fecha_apertura, e.estado, e.ubicacion " +
                     "FROM expedientes e INNER JOIN areas a ON e.id_area = a.id_area " +
                     "WHERE e.numero_expediente LIKE ? OR e.asunto LIKE ? OR a.nombre LIKE ? ORDER BY e.id_expediente DESC";
        try (Connection cn = ConexionBD.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            String busqueda = "%" + texto + "%";
            ps.setString(1, busqueda);
            ps.setString(2, busqueda);
            ps.setString(3, busqueda);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Expediente expediente = new Expediente();
                expediente.setId(rs.getInt("id_expediente"));
                expediente.setNumeroExpediente(rs.getString("numero_expediente"));
                expediente.setIdArea(rs.getInt("id_area"));
                expediente.setArea(rs.getString("area"));
                expediente.setAsunto(rs.getString("asunto"));
                expediente.setResponsable(rs.getString("responsable"));
                expediente.setFechaApertura(rs.getDate("fecha_apertura").toLocalDate());
                expediente.setEstado(rs.getString("estado"));
                expediente.setUbicacion(rs.getString("ubicacion"));
                lista.add(expediente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}