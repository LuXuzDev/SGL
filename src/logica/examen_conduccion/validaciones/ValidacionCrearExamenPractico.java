/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.examen_conduccion.validaciones;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.collections.ObservableList;
import logica.examen_conduccion.implementaciones.ServiciosExamenesConduccion;
import logica.examen_conduccion.modelos.ExamenConduccion;
import logica.examen_medico.implementaciones.ServiciosExamenesMedicos;
import logica.examen_medico.modelos.ExamenMedico;
import logica.persona.implementaciones.ServicioPersona;
import logica.persona.modelos.Persona;
import logica.usuario.modelos.Usuario;

/**
 *
 * @author Angel Hernandez
 */
public class ValidacionCrearExamenPractico {
    
    public static ExamenMedico validarCrearExamenPractico(String nombre,String ci,Usuario usuario) throws Exception
    {
        boolean existePersona=false;
        ObservableList<Persona> personas = ServicioPersona.obtenerPersonas();
        
        for (Persona p : personas) {
            if (p.getCI().equals(ci)) {
                existePersona = true;
                String nombreApellidos = p.getNombre() + " " + p.getApellidos();
                if (!nombreApellidos.equalsIgnoreCase(nombre)) {
                    throw new Exception("El carnet no se corresponde al nombre");
                }
            }
        }
        
        ExamenMedico examenMedico = revisarExamenMedicoValido(ServiciosExamenesMedicos.obtenerExamenesMedicoPorCI(ci));
        revisarExamenTeoricoValido(ServiciosExamenesConduccion.obtenerExamenesTeoricosPorCI(ci));
        
        if(usuario.getRol().equals("AdministradorMedico") || usuario.getRol().equals("Medico"))
            throw new Exception("Ese usuario no puede crear examenes medicos");
        
        return examenMedico;
    }
    
    public static ExamenMedico revisarExamenMedicoValido(ArrayList<ExamenMedico> examenes) throws Exception
    {
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.MONTH, -6);
        Date fechaLimite = calendar.getTime();
        
  
        for(ExamenMedico e : examenes)
        {
            if(e.isAprobado() && e.getFecha().after(fechaLimite))
                return e;
        }
        throw new Exception("No tiene un examen medico valido");
    }
    
    public static ExamenConduccion revisarExamenTeoricoValido(ArrayList<ExamenConduccion> examenes) throws Exception
    {
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.MONTH, -6);
        Date fechaLimite = calendar.getTime();
        
        ExamenConduccion examen=null;
        
  
        for(ExamenConduccion e : examenes)
        {
            if(e.isAprobado() && e.getFecha().after(fechaLimite))
                examen=e;
        }
        if(examen == null)
            throw new Exception("No tiene un examen medico valido");
        return examen;
    }
    
    
}
