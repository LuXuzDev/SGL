/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.administrador.sistema.controladores;

import gestor_interfaces.GestorEscenas;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.entidad.implementaciones.ServicioEntidad;
import logica.entidad.modelos.EntidadRelacionada;
import logica.examen.implementaciones.ServicioExamenes;
import logica.examen.modelos.Examen;
import logica.usuario.implementaciones.ServicioUsuario;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorVerMasClinicas {

    private EntidadRelacionada clinica;

    @FXML
    TextField txfNombre;

    @FXML
    TextField txfDirector;

    @FXML
    TextField txfCantidadPacientes;

    @FXML
    TextField txfCantidadMedicos;
    
    @FXML
    TextField txfTelefono;

    @FXML
    TextField txfCorreo;

    @FXML
    TextField txfExamenesA;

    @FXML
    TextField txfExamenesD;

    @FXML
    TextArea txfDireccion;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnagregarUsuario;

    @FXML
    public void initialize() {
        System.out.println("Controlador ver mas Clinicas iniciado");
    }

    public void SetDatos(EntidadRelacionada Clinica) throws Exception {
        this.clinica = Clinica;
        Iniciar();
    }

    @FXML
    public void Iniciar() throws Exception {

        System.out.println("Iniciar llamado");

        btnAtras.setOnAction(e -> {
            GestorEscenas.cerrar(btnAtras);
        });
        String cantPacientes = String.valueOf(ServicioExamenes.obtenerTodosLosExamenesPorEntidad(clinica.getId()).size());
        String CantMed = String.valueOf(ServicioExamenes.TotalMedicos());
        String CantExamA = String.valueOf(ServicioExamenes.TotalExamenesAprobados(clinica.getId()));
        String CantExamS = String.valueOf(ServicioExamenes.TotalExamenesSuspensos(clinica.getId()));
        txfDirector.setText(clinica.getNombreDirector());
        txfCorreo.setText(clinica.getCorreo());
        txfNombre.setText(clinica.getNombre());
        txfTelefono.setText(clinica.getTelefono());
        txfDireccion.setText(clinica.getDireccion());
        txfCantidadPacientes.setText(cantPacientes);
        txfCantidadMedicos.setText(CantMed);
        txfExamenesA.setText(CantExamA);
        txfExamenesD.setText(CantExamS);

        btnEditar.setOnAction(e -> {

            if (btnEditar.getText().equals("Guardar")) {

                // Modo guardar
                try {
                    System.out.println("Guardando cambios...");

                    // Actualizar el objeto con los nuevos valores
                    clinica.setCorreo(txfCorreo.getText());
                    clinica.setNombreDirector(txfDirector.getText());
                    clinica.setTelefono(txfTelefono.getText());

                    // Guardar en la base de datos
                    ServicioEntidad.actualizarEntidad(clinica);

                    // Desactivar edición
                    txfCorreo.setEditable(false);
                    txfDirector.setEditable(false);
                    txfTelefono.setEditable(false);
                    txfCorreo.setDisable(true);
                    txfDirector.setDisable(true);
                    txfTelefono.setDisable(true);
                    editarCasillas(false);
                    btnEditar.setText("Editar");
                    GestorEscenas.cargarConfirmacion(btnEditar.getScene().getWindow(), "Cambios guardados");
                } catch (Exception ex) {
                    GestorEscenas.cargarError(btnEditar.getScene().getWindow(), ex);
                }

            } else {

                // Modo edición
                System.out.println("Modo edición activado");
                txfCorreo.setEditable(true);
                txfDirector.setEditable(true);
                txfTelefono.setEditable(true);
                txfCorreo.setDisable(false);
                txfDirector.setDisable(false);
                txfTelefono.setDisable(false);
                editarCasillas(true);
                btnEditar.setText("Guardar");
            }
        });
    }

    private void editarCasillas(boolean enable) {
        String style = enable ? "-fx-background-color: #ffffff; -fx-border-color: #4CAF50;"
                : "-fx-background-color: #f4f4f4; -fx-border-color: #cccccc;";

        txfCorreo.setStyle(style);
        txfDirector.setStyle(style);
        txfTelefono.setStyle(style);
    }

    @FXML
    private void transicionregistrarUsuario() {
        Window ventanaActual = txfNombre.getScene().getWindow();
        try {
            GestorEscenas.cargarRegistrarUsuario(ventanaActual, (Stage) txfNombre.getScene().getWindow(), null, clinica);
        } catch (Exception e) {
            GestorEscenas.cargarError(ventanaActual, e);
        }

    }
    
     
}
