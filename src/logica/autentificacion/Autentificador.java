/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.autentificacion;

import logica.inicios_sesion.consultas.ConsultasInicioSesion;
import logica.usuario.consultas.ConsultasUsuario;
import logica.usuario.modelos.Usuario;



/**
 *
 * @author Angel Hernandez
 */
public class Autentificador {
    
    public static Usuario usuario;
    public static String existeUsuario(String correo,String clave) throws Exception
    {
        usuario = ConsultasUsuario.obtenerUsuario(correo, clave);
        System.out.println(usuario);
        ConsultasInicioSesion.crearInicioSesion(usuario.getId());
        return usuario.getRol();
    }
}
