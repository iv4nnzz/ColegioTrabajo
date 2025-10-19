/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author 9spot
 */


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Persona {
    protected String nombre;
    protected String direccion;
    protected String telefono;
    protected String fechaNacimiento;
    
    public Persona() {
        this.nombre = "";
        this.direccion = "";
        this.telefono = "";
        this.fechaNacimiento = "";
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public int calcularEdad() {
        try {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            
            LocalDate fechaNac = null;
            try {
                fechaNac = LocalDate.parse(fechaNacimiento, formatter1);
            } catch (DateTimeParseException e) {
                fechaNac = LocalDate.parse(fechaNacimiento, formatter2);
            }
            
            LocalDate fechaActual = LocalDate.now();
            return Period.between(fechaNac, fechaActual).getYears();
        } catch (Exception e) {
            return -1; 
        }
    }
    
    public boolean esMayorDeEdad() {
        int edad = calcularEdad();
        return edad >= 18;
    }
    
    public String obtenerInfo() {
        return "Nombre: " + nombre + "\n" +
               "Dirección: " + direccion + "\n" +
               "Teléfono: " + telefono + "\n" +
               "Fecha de Nacimiento: " + fechaNacimiento;
    }
    
    public String getNombre() {
        return nombre;
    }
}
