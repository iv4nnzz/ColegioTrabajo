/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author 9spot
 */
import modelo.Profesor;
import modelo.Estudiante;
import vista.ColegioVista;
import java.util.ArrayList;

public class ColegioControlador {
    private ArrayList<Profesor> profesores;
    private ArrayList<Estudiante> estudiantes;
    private ColegioVista vista;
    
    public ColegioControlador(ColegioVista vista) {
        this.vista = vista;
        this.profesores = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
    }

    public void agregarProfesor(String nombre, String direccion, String telefono, 
                               String fechaNacimiento, String cedula, String area,
                               double salarioPorHora, int horasTrabajadas) {
        Profesor profesor = new Profesor();
        profesor.setNombre(nombre);
        profesor.setDireccion(direccion);
        profesor.setTelefono(telefono);
        profesor.setFechaNacimiento(fechaNacimiento);
        
        if (!profesor.esMayorDeEdad()) {
            throw new IllegalArgumentException("El profesor debe ser mayor de edad (18 años o más)");
        }
        
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("La cédula es obligatoria para los profesores");
        }
        
        if (salarioPorHora < 0) {
            throw new IllegalArgumentException("El salario por hora no puede ser negativo");
        }
        
        if (horasTrabajadas < 0) {
            throw new IllegalArgumentException("Las horas trabajadas no pueden ser negativas");
        }
        
        profesor.setCedula(cedula);
        profesor.setArea(area);
        profesor.setSalarioPorHora(salarioPorHora);
        profesor.setHorasTrabajadas(horasTrabajadas);
        
        profesores.add(profesor);
    }
}
