/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.trabajador_centro.controladores;

import com.jfoenix.controls.JFXButton;
import gestor_interfaces.GestorEscenas;
import gestor_interfaces.modelos.Controlador;
import gestor_interfaces.modelos.MenuEstadisticas;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logica.autentificacion.Autentificador;

/**
 *
 * @author Angel Hernandez
 */
public class ControladorTrabajadorCentro extends Controlador{

    @FXML
    private JFXButton Inicio;

    @FXML
    private JFXButton Licencias;

    @FXML
    private JFXButton Conductores;

    @FXML
    private JFXButton Infracciones;

    @FXML
    private JFXButton Examenes;

    @FXML
    private JFXButton Reportes;

    @FXML
    private Button RegistrarExamen;

    @FXML
    private Button RegistrarLicencia;

    @FXML
    private Button RegistrarInfracciones;

    @FXML
    private Button RegistrarConductores;

    @FXML
    private Pane PanelInicio;

    @FXML
    private Pane PanelExamenes;

    @FXML
    private Pane PanelInfracciones;

    @FXML
    private Pane PanelLicencias;

    @FXML
    private Pane PanelConductores;
    
    @FXML
    private HBox hbVentanaPrincipal;
    
    @FXML
    private ProgressBar BarraProgresoLicenciaA;
    
    @FXML
    private ProgressBar BarraProgresoLicenciaB;
    
    @FXML
    private ProgressBar BarraProgresoLicenciaC;
    
    @FXML
    private ProgressBar BarraProgresoLicenciaD;
    
    @FXML
    private ProgressBar BarraProgresoLicenciaE;
    
    @FXML
    private ProgressBar BarraProgresoInfraccionLeve;
    
    @FXML
    private ProgressBar BarraProgresoInfraccionGrave;
    
    @FXML
    private ProgressBar BarraProgresoInfraccionMGrave;
    
    @FXML
    private Label LabelLicenciaA;
    
    @FXML
    private Label LabelLicenciaB;
    
    @FXML
    private Label LabelLicenciaC;
    
    @FXML
    private Label LabelLicenciaD;
    
    @FXML
    private Label LabelLicenciaE;
    
    @FXML
    private Label LabelInfraccionLeve;
    
    @FXML
    private Label LabelInfraccionGrave;
    
    @FXML
    private Label LabelInfraccionMGrave;

    @FXML
    private JFXButton BotonCerrarSesion;
    
    @FXML
    private Label LabelUsuarioNombre;
    
    @FXML
    private Label LabelCorreoUsuario;
    
    @FXML
    private Label LabelFechaHora;
    
    private ImageView ImagenLicencias;
    private ImageView ImagenConductores;
    private ImageView ImagenInicio;
    private ImageView ImagenExamenes;
    private ImageView ImagenInfracciones;
    private ImageView ImagenReportes;

    @FXML
    public void initialize() {
        
        ImagenLicencias = (ImageView) Licencias.getGraphic();
        ImagenConductores = (ImageView) Conductores.getGraphic();
        ImagenInicio = (ImageView) Inicio.getGraphic();
        ImagenExamenes = (ImageView) Examenes.getGraphic();
        ImagenInfracciones = (ImageView) Infracciones.getGraphic();
        ImagenReportes = (ImageView) Reportes.getGraphic();
        
        GestorEscenas.configurarReloj(LabelFechaHora);

        GestorEscenas.ponerIconoVentana(hbVentanaPrincipal, "Trabajador centro");
         
        BotonCerrarSesion.setOnAction(e ->
        {
            GestorEscenas.cerrarPrograma();
        });
        
        JFXButton[] BotonesConsumirTecla = {Inicio, Examenes, Licencias, Conductores, Infracciones, Reportes};
        GestorEscenas.consumirTecla(BotonesConsumirTecla);
        
        Label[] PorcentajesBarra = {LabelLicenciaA,LabelLicenciaB,LabelLicenciaC,LabelLicenciaD,LabelLicenciaE,LabelInfraccionLeve,LabelInfraccionGrave,LabelInfraccionMGrave};
        ProgressBar[] BarrasProgreso = {BarraProgresoLicenciaA,BarraProgresoLicenciaB,BarraProgresoLicenciaC,BarraProgresoLicenciaD,BarraProgresoLicenciaE,BarraProgresoInfraccionLeve,BarraProgresoInfraccionGrave,BarraProgresoInfraccionMGrave};
        GestorEscenas.progresoLabel(PorcentajesBarra, BarrasProgreso);
        
        System.out.println("Controlador Administrador Trabajador Centro Iniciado");
        this.TransicionInicio();
    }

    @FXML
    public void TransicionLicencias() {
        Pane[] PanelesOcultar = {PanelInfracciones, PanelInicio, PanelConductores, PanelExamenes};
        GestorEscenas.mostrarOcultarPaneles(PanelLicencias, PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Examenes, Reportes};
        GestorEscenas.pintarBotones(Licencias, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-licencia-blanco.png")));
        Licencias.setGraphic(IconoActivo);
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInfracciones);
                add(ImagenInicio);
                add(ImagenConductores);
                add(ImagenExamenes);
                add(ImagenReportes);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Infracciones);
                add(Inicio);
                add(Conductores);
                add(Examenes);
                add(Reportes);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }

    @FXML
    public void TransicionConductores() {
        Pane[] PanelesOcultar = {PanelInfracciones, PanelLicencias, PanelInicio, PanelExamenes};
        GestorEscenas.mostrarOcultarPaneles(PanelConductores, PanelesOcultar);
        JFXButton[] botones = {Inicio, Licencias, Infracciones, Examenes, Reportes};
        GestorEscenas.pintarBotones(Conductores, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-conductor-blanco.png")));
        Conductores.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInfracciones);
                add(ImagenInicio);
                add(ImagenLicencias);
                add(ImagenExamenes);
                add(ImagenReportes);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Infracciones);
                add(Inicio);
                add(Licencias);
                add(Examenes);
                add(Reportes);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }

    @FXML
    public void TransicionInicio() {
        Pane[] PanelesOcultar = {PanelInfracciones, PanelLicencias, PanelConductores, PanelExamenes};
        GestorEscenas.mostrarOcultarPaneles(PanelInicio, PanelesOcultar);
        JFXButton[] botones = {Licencias, Conductores, Infracciones, Examenes, Reportes};
        GestorEscenas.pintarBotones(Inicio, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-inicio-blanco.png")));
        Inicio.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInfracciones);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenExamenes);
                add(ImagenReportes);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Infracciones);
                add(Conductores);
                add(Licencias);
                add(Examenes);
                add(Reportes);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }

    @FXML
    public void TransicionExamenes() {
        Pane[] PanelesOcultar = {PanelInfracciones, PanelLicencias, PanelConductores, PanelInicio};
        GestorEscenas.mostrarOcultarPaneles(PanelExamenes, PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Licencias, Reportes};
        GestorEscenas.pintarBotones(Examenes, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-examen-blanco.png")));
        Examenes.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInfracciones);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenInicio);
                add(ImagenReportes);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Infracciones);
                add(Conductores);
                add(Licencias);
                add(Inicio);
                add(Reportes);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }

    @FXML
    public void TransicionInfracciones() {
        Pane[] PanelesOcultar = {PanelInicio, PanelLicencias, PanelConductores, PanelExamenes};
        GestorEscenas.mostrarOcultarPaneles(PanelInfracciones, PanelesOcultar);
        JFXButton[] botones = {Inicio, Conductores, Licencias, Examenes, Reportes};
        GestorEscenas.pintarBotones(Infracciones, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-infraccion-blanco.png")));
        Infracciones.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInicio);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenExamenes);
                add(ImagenReportes);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Inicio);
                add(Conductores);
                add(Licencias);
                add(Examenes);
                add(Reportes);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    @FXML
    public void TransicionReportes() {
        JFXButton[] botones = {Inicio, Conductores, Infracciones, Examenes, Licencias};
        GestorEscenas.pintarBotones(Reportes, botones);
        ImageView IconoActivo = new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-reporte-blanco.png")));
        Reportes.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInfracciones);
                add(ImagenConductores);
                add(ImagenLicencias);
                add(ImagenExamenes);
                add(ImagenInicio);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Infracciones);
                add(Conductores);
                add(Licencias);
                add(Examenes);
                add(Inicio);
                }};
        GestorEscenas.cambiarIconos(ImagenesCambiar, BotonesCambiar);
    }

    @FXML
    public void TransicionRegistrarExamen() {
        String Direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/registrar/registrar-examen.fxml";
        Stage Padre = (Stage) RegistrarExamen.getScene().getWindow();

        try {
            GestorEscenas.cargarPanelAuxiliar(Padre, Direccion, true, "Registrar examen");
        } catch (Exception ex) {
            //CAPTURAR ERROR

        }
    }

    @FXML
    public void TransicionRegistrarLicencia() {
        String Direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/registrar/registrar-licencia.fxml";
        Stage Padre = (Stage) RegistrarLicencia.getScene().getWindow();

        try {
            GestorEscenas.cargarPanelAuxiliar(Padre, Direccion, true, "Registrar Licencia");
        } catch (Exception ex) {
            //CAPTURAR ERROR

        }
    }

    @FXML
    public void TransicionRegistrarInfracciones() {
        String Direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/registrar/registrar-infraccion.fxml";
        Stage Padre = (Stage) RegistrarInfracciones.getScene().getWindow();

        try {
            GestorEscenas.cargarPanelAuxiliar(Padre, Direccion, true, "Registrar Infraccion");
        } catch (Exception ex) {
            //CAPTURAR ERROR

        }
    }

    @FXML
    public void TransicionRegistrarConductores() {
        String Direccion = "/interfaz_usuario/recursos_compartidos/menus/menu_auxiliares/registrar/registrar-conductor.fxml";
        Stage Padre = (Stage) RegistrarConductores.getScene().getWindow();

        try {
            GestorEscenas.cargarPanelAuxiliar(Padre, Direccion, true, "Registrar Conductor");
        } catch (Exception ex) {
            //CAPTURAR ERROR

        }
    }

    @Override
    public void Iniciar(MenuEstadisticas MenuEstadisticas) {
        
        LabelUsuarioNombre.setText(GestorEscenas.abreviarNombre(Autentificador.usuario.getNombre()));
        Tooltip MouseNombre= new Tooltip(Autentificador.usuario.getNombre());
        MouseNombre.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        LabelUsuarioNombre.setTooltip(MouseNombre);
        LabelUsuarioNombre.setMaxWidth(100);
        LabelCorreoUsuario.setText(GestorEscenas.seguridadCorreo(Autentificador.usuario.getCorreo()));
        
    }

    
    @Override
    protected void CargarEstadisticas(MenuEstadisticas MenuEstadisticas) {
        
    }

}
