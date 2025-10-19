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
}