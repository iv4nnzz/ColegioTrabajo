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
public class ProfesorTest {

    private static final double EPS = 1e-6;

    @Test
    public void testCalculoSalarioTotalYPrestaciones() {
        Profesor prof = new Profesor();
        prof.setSalarioPorHora(50.0); 
        prof.setHorasTrabajadas(80);  

        double salarioTotal = prof.calcularSalarioTotal(); 
        double esperadoBase = 50.0 * 80; 
        double esperadoTotal = esperadoBase * 1.20; 
        double esperadoPrestaciones = esperadoTotal * 0.17;

        assertEquals(esperadoTotal, salarioTotal, EPS);
        assertEquals(esperadoPrestaciones, prof.calcularPrestaciones(), EPS);
    }

    @Test
    public void testSobrecargasCalcularSalarioTotal() {
        Profesor prof = new Profesor();
        prof.setSalarioPorHora(60.0);

        double res1 = prof.calcularSalarioTotal(10); 
        assertEquals((60.0 * 10) * 1.20, res1, 1e-6);

        double res2 = prof.calcularSalarioTotal(10, 0.10); 
        assertEquals((60.0 * 10) * 1.10, res2, 1e-6);
    }
}