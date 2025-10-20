/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package controlador;


import modelo.Estudiante;
import modelo.Profesor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author 9spot
 */
public class ColegioControladorIT {

    @Test
    public void testAgregarYObtenerEstudianteYProfesor() {
        ColegioControlador ctrl = new ColegioControlador(null);

        ctrl.agregarEstudiante("María", "Cra 10", "3111111111", "15/05/2010",
                "7B", "EST01", 4.2);

        List<Estudiante> estudiantes = ctrl.obtenerEstudiantes();
        assertEquals(1, estudiantes.size());
        assertTrue(estudiantes.get(0).obtenerInfo().contains("Código Estudiante: EST01"));
        assertTrue(estudiantes.get(0).obtenerInfo().contains("Promedio: 4.20"));

        String fechaMayor = LocalDate.now().minusYears(30)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        ctrl.agregarProfesor("Carlos", "Av 5", "3202222222", fechaMayor,
                "12345678", "Matemáticas", 40.0, 90);

        List<Profesor> profesores = ctrl.obtenerProfesores();
        assertEquals(1, profesores.size());
        Profesor p = profesores.get(0);
        assertEquals("12345678", p.getCedula());
        assertTrue(p.obtenerInfo().contains("Área: Matemáticas"));
    }

    @Test
    public void testValidacionesControlador_ErroresEsperados() {
        ColegioControlador ctrl = new ColegioControlador(null);

        String fechaMenor = LocalDate.now().minusYears(16)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        assertThrows(IllegalArgumentException.class, () -> {
            ctrl.agregarProfesor("Joven", "Dir", "300", fechaMenor,
                    "87654321", "Inglés", 30.0, 40);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ctrl.agregarEstudiante("X", "D", "300", "01/01/2009", "6A", "E01", 6.0);
        });

        ctrl.agregarEstudiante("A", "D", "300", "01/01/2008", "6A", "E02", 3.0);
        assertThrows(IllegalArgumentException.class, () -> {
            ctrl.agregarEstudiante("B", "D", "300", "01/01/2008", "6A", "E02", 4.0);
        });

        String fechaMayor = LocalDate.now().minusYears(25)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        ctrl.agregarProfesor("Doc", "Dir", "301", fechaMayor, "CED1", "Ciencias", 20.0, 10);
        assertThrows(IllegalArgumentException.class, () -> {
            ctrl.agregarProfesor("Doc2", "Dir", "302", fechaMayor, "CED1", "Ciencias", 25.0, 12);
        });
    }
}