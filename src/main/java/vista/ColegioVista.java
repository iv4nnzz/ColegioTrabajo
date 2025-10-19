/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author 9spot
 */
import controlador.ColegioControlador;
import modelo.Profesor;
import modelo.Estudiante;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Period;

public class ColegioVista {
    private ColegioControlador controlador;
    
    public void setControlador(ColegioControlador controlador) {
        this.controlador = controlador;
    }
    
    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            continuar = mostrarMenu();
        }
        mostrarMensaje("Gracias por usar el Sistema del Colegio");
    }
    
    private boolean mostrarMenu() {
        String[] opciones = {"Registrar Profesor", "Registrar Estudiante", 
                            "Ver Reportes", "Salir"};
        
        int seleccion = JOptionPane.showOptionDialog(
            null,
            "   SISTEMA DE GESTIÓN DEL COLEGIO\n" +
            "Seleccione una opción:",
            "Menú Principal",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        switch (seleccion) {
            case 0:
                solicitarDatosProfesor();
                return true;
            case 1:
                solicitarDatosEstudiante();
                return true;
            case 2:
                mostrarReportes();
                return true;
            case 3:
            case JOptionPane.CLOSED_OPTION:
                return false;
            default:
                return true;
        }
    }
    
    public void solicitarDatosEstudiante() {
        try {
            String nombre = solicitarDatoValidado("Ingrese el nombre del estudiante:", "nombre");
            if (nombre == null) return;
            
            String direccion = solicitarDatoValidado("Ingrese la dirección:", "obligatorio");
            if (direccion == null) return;
            
            String telefono = solicitarDatoValidado("Ingrese el teléfono:", "telefono");
            if (telefono == null) return;
            
            String fechaNacimiento = solicitarDatoValidado("Ingrese la fecha de nacimiento (dd/mm/aaaa):", "fecha");
            if (fechaNacimiento == null) return;
            
            String grado = solicitarDatoValidado("Ingrese el grado\n(Ej: 3° A, 10° B, 11° C):", "obligatorio");
            if (grado == null) return;
            
            String codigoEstudiante = solicitarDatoValidado("Ingrese el código del estudiante:", "obligatorio");
            if (codigoEstudiante == null) return;
            
            String promedioStr = solicitarDatoValidado("Ingrese el promedio del estudiante\n(Escala de 0.0 a 5.0):", "promedio");
            if (promedioStr == null) return;
            double promedio = Double.parseDouble(promedioStr);
            
            controlador.agregarEstudiante(nombre, direccion, telefono, fechaNacimiento,
                                         grado, codigoEstudiante, promedio);
            
            mostrarMensaje("Estudiante registrado exitosamente");
            
        } catch (NumberFormatException e) {
            mostrarError("Error: Debe ingresar valores numéricos válidos");
        } catch (IllegalArgumentException e) {
            mostrarError("Error: " + e.getMessage());
        }
    }
    
    public void mostrarReportes() {
        if (!controlador.hayDatos()) {
            mostrarError("No hay datos registrados para generar reportes");
            return;
        }
        
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("REPORTE DE ESTUDIANTES\n");
        
        List<Estudiante> estudiantes = controlador.obtenerEstudiantes();
        
        if (estudiantes.isEmpty()) {
            reporte.append("No hay estudiantes registrados.\n\n");
        } else {
            for (int i = 0; i < estudiantes.size(); i++) {
                reporte.append("ESTUDIANTE #").append(i + 1).append(" ─────────────────────────────\n");
                reporte.append(estudiantes.get(i).obtenerInfo());
            }
        }
        
        reporte.append("REPORTE DE PROFESORES (Ordenado por Salario)\n");
        
        List<Profesor> profesores = controlador.obtenerProfesores();
        
        if (profesores.isEmpty()) {
            reporte.append("No hay profesores registrados.\n\n");
        } else {
            controlador.ordenarProfesoresPorSalario();
            
            for (int i = 0; i < profesores.size(); i++) {
                reporte.append("┌─ PROFESOR #").append(i + 1).append(" ──────────────────────────────┐\n");
                reporte.append(profesores.get(i).obtenerInfo());
            }
            
            double totalPrestaciones = controlador.calcularTotalPrestaciones();
            reporte.append("TOTAL PRESTACIONES SOCIALES: $");
            reporte.append(String.format("%-20.2f", totalPrestaciones));
        }
        
        JTextArea textArea = new JTextArea(reporte.toString());
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(650, 550));
        
        JOptionPane.showMessageDialog(null, scrollPane, 
                                     "Reportes del Sistema", 
                                     JOptionPane.INFORMATION_MESSAGE);
    }
    
    public String solicitarDatoValidado(String mensaje, String tipo) {
        while (true) {
            String dato = JOptionPane.showInputDialog(mensaje);
            if (dato == null) return null; 
            if (dato.trim().isEmpty()) {
                mostrarError("Este campo es obligatorio.");
                continue;
            }
            
            switch (tipo) {
                case "nombre":
                    if (!dato.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) {
                        mostrarError("El nombre solo puede contener letras y espacios.");
                        continue;
                    }
                    return dato.trim();
                case "cedula":
                    if (!dato.matches("\\d+")) {
                        mostrarError("La cédula debe contener solo números.");
                        continue;
                    }
                    return dato.trim();
                case "telefono":
                    if (!dato.matches("[0-9+\\- ]{6,20}")) {
                        mostrarError("Teléfono inválido. Use números (6-20 dígitos), puede incluir + o - .");
                        continue;
                    }
                    return dato.trim();
                case "fecha":
                    if (!esFechaValida(dato)) {
                        mostrarError("Formato de fecha inválido. Use dd/mm/aaaa.");
                        continue;
                    }
                    return dato.trim();
                case "fecha_mayor18":
                    int edad = calcularEdadDesdeFecha(dato);
                    if (edad == -1) {
                        mostrarError("Formato de fecha inválido. Use dd/mm/aaaa.");
                        continue;
                    }
                    if (edad < 18) {
                        mostrarError("La persona debe ser mayor de edad (18 años o más). Edad detectada: " + edad);
                        continue;
                    }
                    return dato.trim();
                case "double_nonneg":
                    try {
                        double v = Double.parseDouble(dato);
                        if (v < 0) {
                            mostrarError("El valor no puede ser negativo.");
                            continue;
                        }
                        return dato.trim();
                    } catch (NumberFormatException e) {
                        mostrarError("Ingrese un número válido (puede contener decimales).");
                        continue;
                    }
                case "int_nonneg":
                    try {
                        int vi = Integer.parseInt(dato);
                        if (vi < 0) {
                            mostrarError("El valor no puede ser negativo.");
                            continue;
                        }
                        return dato.trim();
                    } catch (NumberFormatException e) {
                        mostrarError("Ingrese un número entero válido.");
                        continue;
                    }
                case "promedio":
                    try {
                        double p = Double.parseDouble(dato);
                        if (p < 0.0 || p > 5.0) {
                            mostrarError("El promedio debe estar entre 0.0 y 5.0.");
                            continue;
                        }
                        return dato.trim();
                    } catch (NumberFormatException e) {
                        mostrarError("Ingrese un número válido (ej: 3.5).");
                        continue;
                    }
                case "obligatorio":
                default:
                    return dato.trim();
            }
        }
    }
    
    private boolean esFechaValida(String fecha) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(fecha, fmt);
            return true;
        } catch (DateTimeParseException ex) {
            try {
                DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate.parse(fecha, fmt2);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }
    }
    
    private int calcularEdadDesdeFecha(String fecha) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaNac;
            try {
                fechaNac = LocalDate.parse(fecha, fmt);
            } catch (DateTimeParseException ex) {
                DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                fechaNac = LocalDate.parse(fecha, fmt2);
            }
            LocalDate hoy = LocalDate.now();
            return Period.between(fechaNac, hoy).getYears();
        } catch (Exception e) {
            return -1;
        }
    }
    
    public String solicitarDato(String mensaje) {
        String dato = JOptionPane.showInputDialog(mensaje);
        if (dato != null && dato.trim().isEmpty()) {
            return null;
        }
        return dato;
    }
    
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", 
                                     JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", 
                                     JOptionPane.ERROR_MESSAGE);
    }
}
