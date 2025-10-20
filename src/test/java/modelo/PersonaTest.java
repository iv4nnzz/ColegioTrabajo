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
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

/**
 *
 * @author 9spot
 */
public class PersonaTest {

    @Test
    public void testCalcularEdad_y_esMayorDeEdad() {
        Persona p = new Persona();
        String fecha = LocalDate.now().minusYears(20)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        p.setFechaNacimiento(fecha);

        int esperado = Period.between(
                LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalDate.now()).getYears();

        assertEquals(esperado, p.calcularEdad());
        assertTrue(p.esMayorDeEdad());
    }

    @Test
    public void testNoMayorDeEdad() {
        Persona p = new Persona();
        String fecha = LocalDate.now().minusYears(10)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        p.setFechaNacimiento(fecha);

        assertFalse(p.esMayorDeEdad());
    }
}