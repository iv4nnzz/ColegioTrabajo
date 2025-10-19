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
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Period;

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

    private boolean esFechaValida(String fecha) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(fecha, fmt);
            return true;
        } catch (DateTimeParseException ex) {
            try {
                DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate.parse(fecha, fmt2);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }
    }
    
    private int calcularEdadDesdeFecha(String fecha) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaNac;
            try {
                fechaNac = LocalDate.parse(fecha, fmt);
            } catch (DateTimeParseException ex) {
                DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                fechaNac = LocalDate.parse(fecha, fmt2);
            }
            LocalDate hoy = LocalDate.now();
            return Period.between(fechaNac, hoy).getYears();
        } catch (Exception e) {
            return -1;
        }
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
