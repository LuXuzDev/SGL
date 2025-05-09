/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_usuario.trabajador_autoescuela.controladores;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import gestor_interfaces.GestorEscenas;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Kristian Aguila
 */
public class ControladorTrabajadorAutoescuela {
    
    @FXML
    private HBox VentanaPrincipal;
    
    @FXML
    private JFXButton Inicio;
    
    @FXML
    private JFXButton ExamenesTeoricos;
    
    @FXML
    private JFXButton ExamenesPracticos;
    
    @FXML 
    private Button RegistrarPractico;
    
    @FXML 
    private Button RegistrarTeorico;
    
    @FXML
    private Pane PanelInicio;
    
    @FXML
    private Pane PanelExamenesTeoricos;
    
    @FXML
    private Pane PanelExamenesPracticos;
    
    @FXML
    private ProgressBar ProgressbarAprobado;
    
    @FXML
    private Label LabelProgresoAprobado;
    
    @FXML
    private ProgressBar ProgressbarReprobado;
    
    @FXML
    private Label LabelProgresoReprobado;
    
    @FXML
    private ProgressBar ProgressbarTeorico;
    
    @FXML
    private Label LabelProgresoTeorico;
    
    @FXML
    private ProgressBar ProgressbarPractico;
    
    @FXML
    private Label LabelProgresoPractico;
    
    @FXML
    private Label EtiquetaRol;
    
    @FXML
    private Label EtiquetaCorreo;
    
    @FXML
    private JFXButton BotonCerrarSesion;
       
    private ImageView ImagenTeorico;
    private ImageView ImagenPractico;
    private ImageView ImagenInicio;
   
    //Funcion de inicio del menu
    @FXML
    public void initialize() {
        
        System.out.println("Controlador TrabajadorAutoescuela Iniciado");
        
        ImagenTeorico = (ImageView) ExamenesTeoricos.getGraphic();
        ImagenPractico = (ImageView) ExamenesPracticos.getGraphic();
        ImagenInicio = (ImageView) Inicio.getGraphic();
        

        BotonCerrarSesion.setOnAction(e ->
        {
            GestorEscenas.CerrarPrograma();
        });
        
        Label[] PorcentajesBarra = {LabelProgresoAprobado,LabelProgresoReprobado,LabelProgresoTeorico,LabelProgresoPractico};
        ProgressBar[] BarrasProgreso = {ProgressbarAprobado,ProgressbarReprobado,ProgressbarTeorico,ProgressbarPractico};
        GestorEscenas.ProgresoLabel(PorcentajesBarra, BarrasProgreso);
        
        JFXButton[] BotonesConsumirTecla={Inicio,ExamenesTeoricos,ExamenesPracticos};
        GestorEscenas.ConsumirTecla(BotonesConsumirTecla);
        
        
        System.out.println("Controlador TrabajadorAutoescuela Iniciado");

        this.TranscisionInicio();
        GestorEscenas.PonerIconoVentana(VentanaPrincipal, "Trabajador autoescuela");
    }
                 
    //Funcion para hacer la transicion de un menu a otro en este caso(ExamenesTeoricos)
    @FXML
    public void TranscisionExamenesTeoricos() {
        Pane[] PanelesOcultar={PanelExamenesPracticos,PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenesTeoricos,PanelesOcultar);
        
        JFXButton[] botones={Inicio,ExamenesPracticos};
        GestorEscenas.PintarBotones(ExamenesTeoricos, botones);
        
        ImageView IconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-examen-teorico-blanco.png")));
        ExamenesTeoricos.setGraphic(IconoActivo);
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInicio);
                add(ImagenPractico);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Inicio);
                add(ExamenesPracticos);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
    }
    
    //Funcion para hacer la transicion de un menu a otro en este caso(ExamenesPracticos)
    @FXML
    public void TranscisionExamenesPracticos() {
        Pane[] PanelesOcultar={PanelExamenesTeoricos,PanelInicio};
        GestorEscenas.MostrarOcultarPaneles(PanelExamenesPracticos,PanelesOcultar);
        
        JFXButton[] botones={Inicio,ExamenesTeoricos};
        GestorEscenas.PintarBotones(ExamenesPracticos, botones);
        
        ImageView IconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-chofer-blanco.png")));
        ExamenesPracticos.setGraphic(IconoActivo);  
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenInicio);
                add(ImagenTeorico);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(Inicio);
                add(ExamenesTeoricos);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);
        
    }
    
    //Funcion para hacer la transicion de un menu a otro en este caso(Inicio)
    @FXML
    public void TranscisionInicio() {
        Pane[] PanelesOcultar={PanelExamenesPracticos,PanelExamenesTeoricos};
        GestorEscenas.MostrarOcultarPaneles(PanelInicio,PanelesOcultar);
        
        JFXButton[] botones={ExamenesTeoricos,ExamenesPracticos};
        GestorEscenas.PintarBotones(Inicio, botones);
        
        ImageView IconoActivo= new ImageView(new Image(getClass().getResourceAsStream("/interfaz_usuario/recursos_compartidos/imagenes/ico-inicio-blanco.png")));
        Inicio.setGraphic(IconoActivo);  
        
        ArrayList<ImageView>ImagenesCambiar= new ArrayList(){{
                add(ImagenPractico);
                add(ImagenTeorico);
                }};
        ArrayList<JFXButton>BotonesCambiar= new ArrayList(){{
                add(ExamenesPracticos);
                add(ExamenesTeoricos);
                }};
        GestorEscenas.CambiarIconos(ImagenesCambiar, BotonesCambiar);    
    }
    
    //Funcion para aparecer el menu de RegistrarExamen en este caso(Practico)
    @FXML
    public void RegistrarExamenPractico()
    {
        String Direccion = "/interfaz_usuario/trabajador_autoescuela/menu_auxiliares/registrar/registrar-examen-practico.fxml";
        Stage Padre = (Stage) RegistrarPractico.getScene().getWindow();
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar examen practico");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }
    
    //Funcion para aparecer el menu de RegistrarExamen en este caso(Teorico)
     @FXML
    public void RegistrarExamenTeorico()
    {
        String Direccion = "/interfaz_usuario/trabajador_autoescuela/menu_auxiliares/registrar/registrar-examen-teorico.fxml";
        Stage Padre = (Stage) RegistrarPractico.getScene().getWindow();
        
        try {
            GestorEscenas.CargarPanelAuxiliar(Padre, Direccion, true, "Registrar examen teorico");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }
    
    /*
    @FXML
    public void MensajeCerrarSesion()
    {
        String Direccion = "/interfaz_usuario/recursos_compartidos/errores/mensaje-cerrar-sesion.fxml";
        Stage Padre = (Stage) BotonCerrarSesion.getScene().getWindow();
        
        try {
            GestorEscenas.CargarAlertaError(Padre, Direccion, "Cerrar sesión", "Desea cerrar Sesion?");
        } catch (Exception ex) {
            //CAPTURAR ERROR
        }
    }*/
    
   
    
}
    
    

