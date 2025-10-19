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
}
