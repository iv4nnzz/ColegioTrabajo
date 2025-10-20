/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author 9spot
 */
public class Profesor extends Persona {
    private String cedula;
    private String area;
    private double salarioPorHora;
    private int horasTrabajadas;
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    
    public void setSalarioPorHora(double salarioPorHora) {
        this.salarioPorHora = salarioPorHora;
    }
    
    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
    
    public String getCedula() {
    return cedula;
}
    
    public double calcularSalarioTotal() {
        double salarioBase = salarioPorHora * horasTrabajadas;
        double bonificacion = salarioBase * 0.20; 
        return salarioBase + bonificacion;
    }
    
    public double calcularSalarioTotal(int horas) {
        double salarioBase = salarioPorHora * horas;
        double bonificacion = salarioBase * 0.20;
        return salarioBase + bonificacion;
    }
    
    public double calcularSalarioTotal(int horas, double porcentajeBonificacion) {
        double salarioBase = salarioPorHora * horas;
        double bonificacion = salarioBase * porcentajeBonificacion;
        return salarioBase + bonificacion;
    }
    
    public double calcularPrestaciones() {
        return calcularSalarioTotal() * 0.17;
    }
    
    @Override
    public String obtenerInfo() {
        return super.obtenerInfo() + "\n" +
               "Cédula: " + cedula + "\n" +
               "Área: " + area + "\n" +
               "Salario por Hora: $" + String.format("%.2f", salarioPorHora) + "\n" +
               "Horas Trabajadas: " + horasTrabajadas + "\n" +
               "Salario Total (con 20% preparación): $" + String.format("%.2f", calcularSalarioTotal()) + "\n" +
               "Prestaciones Sociales (17%): $" + String.format("%.2f", calcularPrestaciones());
    }
}