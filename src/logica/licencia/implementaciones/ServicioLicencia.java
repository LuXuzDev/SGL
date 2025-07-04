/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.licencia.implementaciones;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.examen_medico.modelos.ExamenMedico;
import logica.licencia.consultas.ConsultasLicencia;
import logica.licencia.modelos.Licencia;


/**
 *
 * @author Adrian
 */
public class ServicioLicencia {
 
    public static ObservableList<Licencia> obtenerLicencias() throws Exception {
        return ConsultasLicencia.obtenerLicenciasConsulta();
    }

    public static ObservableList<Licencia> obtenerLicenciasPorPeriodo(LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        // Validar las fechas de entrada
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Ambas fechas deben ser proporcionadas");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

   
        Date fechaInicioDate = java.sql.Date.valueOf(fechaInicio);
        Date fechaFinDate = java.sql.Date.valueOf(fechaFin.plusDays(1));

        // Obtener todas las licencias
        ObservableList<Licencia> licencias = ConsultasLicencia.obtenerLicenciasConsulta();

        // Filtrar por el rango de fechas
        List<Licencia> licenciasFiltradas = licencias.stream()
                .filter(licencia -> licencia.getFechaEmision() != null)
                .filter(licencia
                        -> !licencia.getFechaEmision().before(fechaInicioDate)
                && licencia.getFechaEmision().before(fechaFinDate))
                .sorted((lic1, lic2) -> lic2.getFechaEmision().compareTo(lic1.getFechaEmision())) // Orden descendente
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(licenciasFiltradas);
    }

    public static Licencia obtenerLicenciaPorId(long Id) throws Exception {
        return ConsultasLicencia.obtenerLicenciaPorIdConsulta(Id);
    }
    
    public static ObservableList<Licencia> obtenerLicenciasPorTipo(String tipo) throws Exception {
        ObservableList<Licencia>licenciasPorTipo=FXCollections.observableArrayList();
        ObservableList<Licencia>licencias=ConsultasLicencia.obtenerLicenciasConsulta();
        for(Licencia lic: licencias)
        {
            switch (tipo) {
            case "A":
                if(lic.getTipo().equalsIgnoreCase(tipo))
                    licenciasPorTipo.add(lic);
                break;
            case "B":
                if(lic.getTipo().equalsIgnoreCase(tipo))
                    licenciasPorTipo.add(lic);
                break;
            case "C":
                if(lic.getTipo().equalsIgnoreCase(tipo))
                    licenciasPorTipo.add(lic);
                break;
            case "D":
                if(lic.getTipo().equalsIgnoreCase(tipo))
                    licenciasPorTipo.add(lic);
                break;
            case "E":
                if(lic.getTipo().equalsIgnoreCase(tipo))
                    licenciasPorTipo.add(lic);
                break;
            default:
                licenciasPorTipo.add(lic);
            }
        }
        return licenciasPorTipo;
    }

    public static int actualizarPuntosLicencia(long idLicencia, int puntosASumar) throws Exception{
        return ConsultasLicencia.actualizarPuntosLicenciaConsulta(idLicencia, puntosASumar);
    }
    
    public static void crearLicencia(Licencia licencia,ExamenMedico examenMedico) throws Exception
    {
        ConsultasLicencia.crearLicenciaConsulta(licencia, examenMedico);
    }
    
    public static long obtenerProximoIdLicencia() throws Exception
    {
        return ConsultasLicencia.obtenerProximoIdLicenciaConsulta();
    }
}
