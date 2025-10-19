/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author 9spot'
 */
import controlador.ColegioControlador;
import modelo.Profesor;
import modelo.Estudiante;
import javax.swing.JOptionPane;

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
  
    public String solicitarDato(String mensaje) {
        String dato = JOptionPane.showInputDialog(mensaje);
        if (dato != null && dato.trim().isEmpty()) {
            return null;
        }
        return dato;
    }
    
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", 
                                     JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", 
                                     JOptionPane.ERROR_MESSAGE);
    }
}
