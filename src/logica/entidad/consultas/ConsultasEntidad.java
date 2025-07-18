/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.entidad.consultas;

import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.entidad.modelos.EntidadRelacionada;

/**
 *
 * @author Adrian
 */
public class ConsultasEntidad {

    public static ObservableList<EntidadRelacionada> obtenerEntidadesConsulta() throws Exception {
        ObservableList<EntidadRelacionada> Entidades = FXCollections.observableArrayList();

        String consulta = "SELECT * FROM \"Entidad\"";

        try (Connection conn = ConectorBaseDato.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(consulta)) {

            while (rs.next()) {
                EntidadRelacionada Entidad = new EntidadRelacionada(
                        rs.getLong("Id"),
                        rs.getString("Nombre"),
                        rs.getString("Direccion"),
                        rs.getString("Telefono"),
                        rs.getString("Correo"),
                        rs.getString("NombreDirector"),
                        rs.getString("Tipo_Entidad"));

                Entidades.add(Entidad);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener entidades: " + e.getMessage());
            e.printStackTrace();
        }

        return Entidades;
    }

    public static EntidadRelacionada obtenerEntidadPorNombreConsulta(String nombre) throws Exception {
        EntidadRelacionada Entidad = null;

        String consulta = "SELECT * FROM \"Entidad\" WHERE \"Nombre\" = ?";

        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setString(1, nombre);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Entidad = new EntidadRelacionada(
                            rs.getLong("Id"),
                            rs.getString("Nombre"),
                            rs.getString("Direccion"),
                            rs.getString("Telefono"),
                            rs.getString("Correo"),
                            rs.getString("NombreDirector"),
                            rs.getString("Tipo_Entidad"));
                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener la entidad de la base de datos", e);
        }

        return Entidad;
    }
    public static EntidadRelacionada obtenerEntidadPorIdConsulta(long Id) throws Exception {
        EntidadRelacionada Entidad = null;

        String consulta = "SELECT * FROM \"Entidad\" WHERE \"Id\" = ?";

        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setLong(1, Id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Entidad = new EntidadRelacionada(
                            rs.getLong("Id"),
                            rs.getString("Nombre"),
                            rs.getString("Direccion"),
                            rs.getString("Telefono"),
                            rs.getString("Correo"),
                            rs.getString("NombreDirector"),
                            rs.getString("Tipo_Entidad"));
                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener la entidad de la base de datos", e);
        }

        return Entidad;
    }

    public static ObservableList<EntidadRelacionada> obtenerAutoescuelasConsulta() throws SQLException, Exception {
        ObservableList<EntidadRelacionada> Autoescuelas = FXCollections.observableArrayList();
        String consulta = "SELECT * FROM \"Entidad\" WHERE \"Tipo_Entidad\" = ?";

        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement pstmt = conn.prepareStatement(consulta)) {

            pstmt.setString(1, "Autoescuela");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    EntidadRelacionada Autoescuela = new EntidadRelacionada(
                            rs.getLong("Id"),
                            rs.getString("Nombre"),
                            rs.getString("Direccion"),
                            rs.getString("Telefono"),
                            rs.getString("Correo"),
                            rs.getString("NombreDirector"),
                            "Autoescuela");

                    Autoescuelas.add(Autoescuela);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener autoescuelas: " + e.getMessage());
            throw e; // Relanzar la excepción para manejo superior
        }

        return Autoescuelas;
    }

    public static EntidadRelacionada obtenerAutoescuelaPorIdConsulta(long Id) throws Exception {
        EntidadRelacionada Autoescuela = null;

        String consulta = "SELECT * FROM \"Entidad\""
                + "WHERE \"Id\" = ? AND \"Tipo_Entidad\" = ?";

        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setLong(1, Id);
            stmt.setString(2, "Autoescuela");

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Autoescuela = new EntidadRelacionada(
                            rs.getLong("Id"),
                            rs.getString("Nombre"),
                            rs.getString("Direccion"),
                            rs.getString("Telefono"),
                            rs.getString("Correo"),
                            rs.getString("NombreDirector"),
                            "Autoescuela");
                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener la entidad de la base de datos", e);
        }

        return Autoescuela;
    }

    public static ObservableList<EntidadRelacionada> obtenerClinicasConsulta() throws SQLException, Exception {
        ObservableList<EntidadRelacionada> Clinicas = FXCollections.observableArrayList();
        String consulta = "SELECT * FROM \"Entidad\" WHERE \"Tipo_Entidad\" = ?";

        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement pstmt = conn.prepareStatement(consulta)) {

            pstmt.setString(1, "Clinica");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    EntidadRelacionada Clinica = new EntidadRelacionada(
                            rs.getLong("Id"),
                            rs.getString("Nombre"),
                            rs.getString("Direccion"),
                            rs.getString("Telefono"),
                            rs.getString("Correo"),
                            rs.getString("NombreDirector"),
                            "Clinica");

                    Clinicas.add(Clinica);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener autoescuelas: " + e.getMessage());
            throw e; // Relanzar la excepción para manejo superior
        }

        return Clinicas;
    }

    public static EntidadRelacionada obtenerClinicaPorIdConsulta(long Id) throws Exception {
        EntidadRelacionada Clinica = null;

        String consulta = "SELECT * FROM \"Entidad\""
                + "WHERE \"Id\" = ? AND \"Tipo_Entidad\" = ?";

        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setLong(1, Id);
            stmt.setString(2, "Clinica");

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Clinica = new EntidadRelacionada(
                            rs.getLong("Id"),
                            rs.getString("Nombre"),
                            rs.getString("Direccion"),
                            rs.getString("Telefono"),
                            rs.getString("Correo"),
                            rs.getString("NombreDirector"),
                            "Clinica");
                }
            }

        } catch (SQLException e) {
            throw new Exception("Error al obtener la entidad de la base de datos", e);
        }

        return Clinica;
    }

    public static long guardarEntidad(EntidadRelacionada entidad) throws SQLException, Exception {
        String guardar = "INSERT INTO \"Entidad\" (\"Nombre\", \"Direccion\", \"Telefono\", \"Correo\", \"NombreDirector\", \"Tipo_Entidad\") VALUES (?, ?, ?, ?, ?, ?) RETURNING \"Id\"";

        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement pstmt = conn.prepareStatement(guardar)) {

            pstmt.setString(1, entidad.getNombre());
            pstmt.setString(2, entidad.getDireccion());
            pstmt.setString(3, entidad.getTelefono());
            pstmt.setString(4, entidad.getCorreo());
            pstmt.setString(5, entidad.getNombreDirector());
            pstmt.setString(6, entidad.getTipoEntidad());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong(1); // Retorna el ID generado
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar entidad: " + e.getMessage());
            throw e;
        }
        throw new SQLException("No se pudo guardar la entidad ni obtener el ID");
    }

    
    public static void actualizarEntidadConsulta(EntidadRelacionada entidad) throws Exception{
        String consulta = "UPDATE \"Entidad\" SET \"Telefono\" = ?, \"Correo\" = ?, \"NombreDirector\" = ? WHERE \"Id\" = ?";

        try (Connection conn = ConectorBaseDato.conectar(); PreparedStatement pstmt = conn.prepareStatement(consulta)) {

            pstmt.setString(1, entidad.getTelefono());
            pstmt.setString(2, entidad.getCorreo());
            pstmt.setString(3, entidad.getNombreDirector());
            pstmt.setLong(4, entidad.getId());
        
    int filasAfectadas = pstmt.executeUpdate();
                
                if (filasAfectadas == 0) {
                    throw new Exception("Error al editar la entidad");
                    
                }
        }catch (SQLException e) {
            throw new Exception("Error al actualizar la entidad");
        }
    }
    
    public static void eliminarEntidadConsulta(Long id) throws Exception {
    if (id == null) {
        throw new IllegalArgumentException("El ID de la entidad no puede ser nulo");
    }

    String consulta = "DELETE FROM \"Entidad\" WHERE \"Id\" = ?";
    
    try (Connection conn = ConectorBaseDato.conectar();
         PreparedStatement pstmt = conn.prepareStatement(consulta)) {
         
        pstmt.setLong(1, id);
         pstmt.executeUpdate();
        
                
    } catch (SQLException e) {
        throw new Exception("Error al eliminar la entidad de la base de datos", e);
    }
}
}
