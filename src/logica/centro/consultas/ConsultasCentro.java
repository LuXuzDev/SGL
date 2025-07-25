/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.centro.consultas;

import infraestructura.ConectorBaseDato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logica.centro.modelos.Centro;


/**
 *
 * @author Kris
 */
public class ConsultasCentro {
    
    
    public static Centro obtenerCentroConsulta() throws Exception {
        Centro centro = null;

        String consulta = "SELECT * FROM \"Centro\"";

        try (Connection conn = ConectorBaseDato.conectar(); 
                PreparedStatement stmt = conn.prepareStatement(consulta)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    centro = new Centro(
                        rs.getString("Nombre"),
                        rs.getString("DireccionPostal"),
                        rs.getString("Telefono"),
                        rs.getString("NombreDirectorGeneral"),
                        rs.getString("NombreJefeDptoRH"),
                        rs.getString("NombreJefeDptoCont"),
                        rs.getString("NombreSecretarioGS"),
                        rs.getString("Logo")
                    );
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al obtener el centro de la base de datos", e);
        }
        System.out.println(centro);
        return centro;
    }
    
    public static boolean actualizarCentroConsulta(Centro centro) throws Exception {
    String consulta = "UPDATE \"Centro\" SET \"NombreDirectorGeneral\" = ?, \"Telefono\" = ?, \"NombreSecretarioGS\" = ?, \"NombreJefeDptoRH\" = ?";

    try (Connection conn = ConectorBaseDato.conectar();
         PreparedStatement stmt = conn.prepareStatement(consulta)) {
        
        stmt.setString(1, centro.getNombreDirectorGeneral());
        stmt.setString(2, centro.getTelefono());
        stmt.setString(3, centro.getNombreSecretarioGS());
        stmt.setString(4, centro.getNombreJefeDptoRH());
        
        int filasAfectadas = stmt.executeUpdate();
        return filasAfectadas > 0;
        
    } catch (SQLException e) {
        throw new Exception("Error al actualizar el centro en la base de datos", e);
    }
}
}

