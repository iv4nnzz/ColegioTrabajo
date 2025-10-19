package principal;

import vista.ColegioVista;
import controlador.ColegioControlador;

public class ColegioTrabajo {
    public static void main(String[] args) {
        ColegioVista vista = new ColegioVista();
         
        ColegioControlador controlador = new ColegioControlador(vista);
        
        vista.setControlador(controlador);
        controlador.iniciar();
    }
}