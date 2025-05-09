/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_tablas;


import gestor_interfaces.GestorEscenas;
import interfaz_usuario.recursos_compartidos.menus.controladores.ControladorVerMasConductor;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.examen.implementaciones.ServiciosExamenes;
import logica.examen.modelos.Examen;
import logica.examen_medico.implementaciones.ServiciosExamenesMedicos;
import logica.examen_medico.modelos.ExamenMedico;
import logica.persona.implementaciones.ServicioConductor;
import logica.persona.modelos.Conductor;
import logica.persona.modelos.Persona;

/**
 *
 * @author Angel Hernandez
 */
public class GestorTablas {

    private static <T> void LlenarColumnaDetalles(TableView<T> Tabla, int cantidadFilas) {
        TableColumn<T, ?> ultimaColumna = (TableColumn<T, ?>) Tabla.getColumns().get(Tabla.getColumns().size() - 1);
        Platform.runLater(() -> {
            // Buscar TODAS las celdas visibles
            Set<Node> todasLasCeldas = Tabla.lookupAll(".table-cell");
            for (Node nodo : todasLasCeldas) {
                if (nodo instanceof TableCell) {
                    TableCell<T, ?> celda = (TableCell<T, ?>) nodo;
                    if (celda.getTableColumn().equals(ultimaColumna)) {
                        Label label = new Label("Ver más");
                        label.setStyle("-fx-cursor: hand; -fx-underline: true; -fx-text-fill: #8000ff; -fx-font-weight: bold;");
                        label.setOnMouseClicked(event -> {
                            T objetoFila = Tabla.getItems().get(celda.getIndex());
                            try {
                                MostrarDetalles(objetoFila, label.getScene().getWindow());
                            } catch (Exception ex) {
                                System.out.println("Error al cargar el ver mas de conductor: "+ex.getMessage());
                                GestorEscenas.CargarError(label.getScene().getWindow(), ex);
                            }
                        });                        
                        celda.setGraphic(label);
                        if (celda.getIndex() == cantidadFilas) {
                            break;
                        }             // Salir del bucle una vez encontrada
                    }
                }
            }
        });
    }
    
    
    private static void MostrarDetalles(Object Objeto,Window Ventana) throws Exception
    {
        if(Objeto instanceof Conductor)
        {
            Conductor Conductor = (Conductor) Objeto;
            GestorEscenas.CargarVerMasConductor(Ventana, Conductor);
        }
    }
    
    
    private static <T> void LlenarColumnaFotos(TableView<T> Tabla, int cantidadFilas) {
        Image defaultIcon = new Image(
        Thread.currentThread().getContextClassLoader().getResourceAsStream(
            "interfaz_usuario/recursos_compartidos/imagenes/ico-cuenta-usuario.png"
        )
    );
        TableColumn<T, ?> PrimeraColumna = (TableColumn<T, ?>) Tabla.getColumns().get(0);
        Platform.runLater(() -> {
            // Buscar TODAS las celdas visibles
            Set<Node> todasLasCeldas = Tabla.lookupAll(".table-cell");
            for (Node nodo : todasLasCeldas) {
                if (nodo instanceof TableCell) {
                    TableCell<T, ?> celda = (TableCell<T, ?>) nodo;
                    if (celda.getTableColumn().equals(PrimeraColumna)) {
                        ImageView imageView = new ImageView();
                        imageView.setFitHeight(40);
                        imageView.setFitWidth(40);
                        imageView.setPreserveRatio(true);
                        imageView.setImage(defaultIcon);
                        celda.setGraphic(imageView);
                        if (celda.getIndex() == cantidadFilas) {
                            break;
                        }
                    }
                }
            }
        });
    }
    
    public static void ConfigurarColumnasExamenes(
            TableColumn<Examen, String> ColumnaFotoExamen,
            TableColumn<Examen, String> ColumnaExaminadoExamen,
            TableColumn<Examen, String> ColumnaTipoExamen,
            TableColumn<Examen, Date> ColumnaFechaExamen,
            TableColumn<Examen, String> ColumnaExaminadorExamen,
            TableColumn<Examen, String> ColumnaResultadoExamen,
            TableColumn<Examen, String> ColumnaDetallesExamen) {

        // Configuración de la columna de nombre completo
        ColumnaExaminadoExamen.setCellValueFactory(cellData -> {
            Examen Persona = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", Persona.getPersona().getNombre(), Persona.getPersona().getApellidos())
            );
        });
        ColumnaExaminadorExamen.setCellValueFactory(cellData -> {
            Examen Persona = cellData.getValue();
            return new SimpleStringProperty(Persona.getExaminador().getNombre()
                    );
        });

        ColumnaTipoExamen.setCellValueFactory(cellData -> {
            Examen Examen = cellData.getValue();
            return new SimpleStringProperty(Examen.getTipo());
        });
        
        ColumnaResultadoExamen.setCellValueFactory(cellData -> {
            Examen Examen = cellData.getValue();
            if(Examen.isAprobado())
               return new SimpleStringProperty("Aprobado");
            return new SimpleStringProperty("Reprobado");
        });
        
 

        // Configuración estándar para otras columnas
        ConfigurarColumnaStandard(ColumnaFechaExamen, "Fecha");
    }

    public static void ConfigurarColumnasConductores(
            TableColumn<Conductor, String> ColumnaFoto,
            TableColumn<Conductor, String> ColumnaNombre,
            TableColumn<Conductor, String> ColumnaCI,
            TableColumn<Conductor, String> ColumnaTelefono,
            TableColumn<Conductor, String> ColumnaCorreo) {

        // Configuración de la columna de nombre completo
        ColumnaNombre.setCellValueFactory(cellData -> {
            Conductor conductor = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", conductor.getNombre(), conductor.getApellidos())
            );
        });

        ColumnaCI.setCellValueFactory(cellData -> {
            Conductor conductor = cellData.getValue();
            return new SimpleStringProperty(conductor.getCI());
        });
 

        // Configuración estándar para otras columnas
        ConfigurarColumnaStandard(ColumnaTelefono, "Telefono");
        ConfigurarColumnaStandard(ColumnaCorreo, "Correo");
    }

    private static <T, S> void ConfigurarColumnaStandard(TableColumn<T, S> columna, String propiedad) {
        if (columna != null) {
            columna.setCellValueFactory(new PropertyValueFactory<>(propiedad));
        }
    }

    
    public static void CargarTablaConductores(TableView<Conductor> TablaConductor) {
        try {
            ObservableList<Conductor> Conductores = ServicioConductor.ObtenerConductores();
            TablaConductor.setItems(Conductores);
            LlenarColumnaDetalles(TablaConductor, TablaConductor.getItems().size()-1);
            LlenarColumnaFotos(TablaConductor, TablaConductor.getItems().size()-1);
        } catch (Exception ex) {
            //
        }
    }
    
    public static void CargarTablaExamenes(TableView<Examen> TablaExamenes) {
        try {
            ObservableList<Examen> ExamenesPracticos = ServiciosExamenes.ObtenerExamenesPracticos();
            ObservableList<Examen> ExamenesTeoricos = ServiciosExamenes.ObtenerExamenesTeoricos();
            ObservableList<ExamenMedico> ExamenesMedicos = ServiciosExamenesMedicos.ObtenerExamenesMedico();
            ObservableList<Examen>ExamenesMedicosNuevos=FXCollections.observableArrayList();
            for(int i=0;i<ExamenesMedicos.size();i++)
            {
                Examen exa= new Examen(ExamenesMedicos.get(i).getId(), ExamenesMedicos.get(i).getFecha(), ExamenesMedicos.get(i).isAprobado(), ExamenesMedicos.get(i).getEntidad(), ExamenesMedicos.get(i).getPersona(),ExamenesMedicos.get(i).getExaminador(),ExamenesMedicos.get(i).getTipo());
                ExamenesMedicosNuevos.add(exa);
            }
            
            ObservableList<Examen>Examenes= FXCollections.concat(ExamenesTeoricos,ExamenesPracticos,ExamenesMedicosNuevos);
            TablaExamenes.setItems(Examenes);
            LlenarColumnaDetalles(TablaExamenes, TablaExamenes.getItems().size()-1);
            LlenarColumnaFotos(TablaExamenes, TablaExamenes.getItems().size()-1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }
    
    public static void CargarTablaExamenesPracticosAdminAutoescuela(TableView<Examen> TablaExamenes) {
        try {
            ObservableList<Examen> ExamenesPracticos = ServiciosExamenes.ObtenerExamenesPracticos();
            TablaExamenes.setItems(ExamenesPracticos);
            LlenarColumnaDetalles(TablaExamenes, TablaExamenes.getItems().size()-1);
            LlenarColumnaFotos(TablaExamenes, TablaExamenes.getItems().size()-1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }
    
     public static void CargarTablaExamenesTeoricosAdminAutoescuela(TableView<Examen> TablaExamenes) {
        try {
            ObservableList<Examen> ExamenesTeoricos = ServiciosExamenes.ObtenerExamenesTeoricos();
            TablaExamenes.setItems(ExamenesTeoricos);
            LlenarColumnaDetalles(TablaExamenes, TablaExamenes.getItems().size()-1);
            LlenarColumnaFotos(TablaExamenes, TablaExamenes.getItems().size()-1);
        } catch (Exception ex) {
            //Capturar Error
        }
    }
    
    public static void ConfigurarColumnasExamenesAdminAutoescuela(
            TableColumn<Examen, String> ColumnaFotoExamen,
            TableColumn<Examen, String> ColumnaExaminadoExamen,
            TableColumn<Examen, Date> ColumnaFechaExamen,
            TableColumn<Examen, String> ColumnaExaminadorExamen,
            TableColumn<Examen, String> ColumnaResultadoExamen,
            TableColumn<Examen, String> ColumnaAutoescuelaExamen,
            TableColumn<Examen, String> ColumnaDetallesExamen) {

        // Configuración de la columna de nombre completo
        ColumnaExaminadoExamen.setCellValueFactory(cellData -> {
            Examen Persona = cellData.getValue();
            return new SimpleStringProperty(
                    String.format("%s %s", Persona.getPersona().getNombre(), Persona.getPersona().getApellidos())
            );
        });
        ColumnaExaminadorExamen.setCellValueFactory(cellData -> {
            Examen Persona = cellData.getValue();
            return new SimpleStringProperty(Persona.getExaminador().getNombre()
                    );
        });

        ColumnaAutoescuelaExamen.setCellValueFactory(cellData -> {
            Examen Examen = cellData.getValue();
            return new SimpleStringProperty(Examen.getEntidad().getNombre());
        });
        
        ColumnaResultadoExamen.setCellValueFactory(cellData -> {
            Examen Examen = cellData.getValue();
            if(Examen.isAprobado())
               return new SimpleStringProperty("Aprobado");
            return new SimpleStringProperty("Reprobado");
        });
        
 

        // Configuración estándar para otras columnas
        ConfigurarColumnaStandard(ColumnaFechaExamen, "Fecha");
    }
}
