/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author 9spot
 */
import controlador.ColegioControlador;
import modelo.Profesor;
import modelo.Estudiante;

public class ColegioVista {
    private ColegioControlador controlador;
    
    public void setControlador(ColegioControlador controlador) {
        this.controlador = controlador;
    }
    
    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            continuar = mostrarMenu();
        }
        mostrarMensaje("Gracias por usar el Sistema del Colegio");
    }
}
