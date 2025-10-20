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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        cedula = cedula != null ? cedula.trim() : "";
        nombre = nombre != null ? nombre.trim() : "";
        direccion = direccion != null ? direccion.trim() : "";
        telefono = telefono != null ? telefono.trim() : "";
        fechaNacimiento = fechaNacimiento != null ? fechaNacimiento.trim() : "";
        area = area != null ? area.trim() : "";

        for (Profesor p : profesores) {
            if (p.getCedula().equalsIgnoreCase(cedula)) {
                throw new IllegalArgumentException(
                    "Ya existe un profesor registrado con la cédula: " + cedula);
            }
        }
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
    
    public void agregarEstudiante(String nombre, String direccion, String telefono,
                                 String fechaNacimiento, String grado, 
                                 String codigoEstudiante, double promedio) {
        codigoEstudiante = codigoEstudiante != null ? codigoEstudiante.trim() : "";
        nombre = nombre != null ? nombre.trim() : "";
        direccion = direccion != null ? direccion.trim() : "";
        telefono = telefono != null ? telefono.trim() : "";
        fechaNacimiento = fechaNacimiento != null ? fechaNacimiento.trim() : "";
        grado = grado != null ? grado.trim() : "";

        for (Estudiante e : estudiantes) {
            if (e.getCodigoEstudiante().equalsIgnoreCase(codigoEstudiante)) {
                throw new IllegalArgumentException(
                    "Ya existe un estudiante registrado con el código: " + codigoEstudiante);
            }
}
        if (promedio < 0.0 || promedio > 5.0) {
            throw new IllegalArgumentException("El promedio debe estar entre 0.0 y 5.0");
        }
        
        if (codigoEstudiante == null || codigoEstudiante.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del estudiante es obligatorio");
        }
        
        Estudiante estudiante = new Estudiante(nombre, direccion, telefono, 
                                              fechaNacimiento, grado, 
                                              codigoEstudiante, promedio);
        estudiantes.add(estudiante);
    }
    
    public List<Profesor> obtenerProfesores() {
        return Collections.unmodifiableList(profesores);
    }
    
    public List<Estudiante> obtenerEstudiantes() {
        return Collections.unmodifiableList(estudiantes);
    }
    
    public void ordenarProfesoresPorSalario() {
        Collections.sort(profesores, new Comparator<Profesor>() {
            @Override
            public int compare(Profesor p1, Profesor p2) {
                return Double.compare(p1.calcularSalarioTotal(), p2.calcularSalarioTotal());
            }
        });
    }
    
    public double calcularTotalPrestaciones() {
        double total = 0;
        for (Profesor profesor : profesores) {
            total += profesor.calcularPrestaciones();
        }
        return total;
    }
    
    public boolean hayDatos() {
        return !profesores.isEmpty() || !estudiantes.isEmpty();
    }
    
    public void iniciar() {
        vista.iniciar();
    }
}