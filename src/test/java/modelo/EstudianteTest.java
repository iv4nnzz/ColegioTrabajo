/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package modelo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 9spot
 */
public class EstudianteTest {

    @Test
    public void testAproboYCalcularRendimiento() {
        Estudiante e1 = new Estudiante("Ana", "Calle 1", "3000000", "01/01/2007",
                "10A", "C001", 4.6);
        assertTrue(e1.aprobo());
        assertEquals("Excelente", e1.calcularRendimiento());

        Estudiante e2 = new Estudiante("Luis", "Calle 2", "3000001", "01/01/2009",
                "9B", "C002", 2.8);
        assertFalse(e2.aprobo());
        assertEquals("Insuficiente", e2.calcularRendimiento());
    }

    @Test
    public void testMetodoCalcularRendimientoConParametro() {
        Estudiante e = new Estudiante("X", "Y", "Z", "01/01/2005", "11A", "C003", 0.0);
        assertEquals("Sobresaliente", e.calcularRendimiento(4.0));
        assertEquals("Bueno", e.calcularRendimiento(3.6));
        assertEquals("Aceptable", e.calcularRendimiento(3.1));
    }
}