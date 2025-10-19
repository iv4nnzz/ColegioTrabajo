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
}