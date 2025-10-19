/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author 9spot
 */
public class Estudiante extends Persona {
    private String grado;
    private String codigoEstudiante;
    private double promedio;
    
    public Estudiante(String nombre, String direccion, String telefono, 
                     String fechaNacimiento, String grado, 
                     String codigoEstudiante, double promedio) {
        super(); 
        this.setNombre(nombre);
        this.setDireccion(direccion);
        this.setTelefono(telefono);
        this.setFechaNacimiento(fechaNacimiento);
        this.grado = grado;
        this.codigoEstudiante = codigoEstudiante;
        this.promedio = promedio;
    }
    
    public boolean aprobo() {
        return promedio >= 3.0;
    }
    
    public String calcularRendimiento() {
        if (promedio >= 4.5) {
            return "Excelente";
        } else if (promedio >= 4.0) {
            return "Sobresaliente";
        } else if (promedio >= 3.5) {
            return "Bueno";
        } else if (promedio >= 3.0) {
            return "Aceptable";
        } else {
            return "Insuficiente";
        }
    }
    
    public String calcularRendimiento(double promedioSimulado) {
        if (promedioSimulado >= 4.5) {
            return "Excelente";
        } else if (promedioSimulado >= 4.0) {
            return "Sobresaliente";
        } else if (promedioSimulado >= 3.5) {
            return "Bueno";
        } else if (promedioSimulado >= 3.0) {
            return "Aceptable";
        } else {
            return "Insuficiente";
        }
    }
    
    @Override
    public String obtenerInfo() {
        return super.obtenerInfo() + "\n" +
               "Grado: " + grado + "\n" +
               "Código Estudiante: " + codigoEstudiante + "\n" +
               "Promedio: " + String.format("%.2f", promedio) + "\n" +
               "Rendimiento: " + calcularRendimiento() + "\n" +
               "Estado: " + (aprobo() ? "APROBADO" : "REPROBADO");
    }
}