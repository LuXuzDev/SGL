/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor_interfaces.modelos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Angel Hernandez
 */
public abstract class Controlador {
    
    public abstract void Iniciar(MenuEstadisticas MenuEstadisticas);
    
    protected abstract void CargarEstadisticas(MenuEstadisticas MenuEstadisticas);

}
