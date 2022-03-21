package es.antoniodominguez.jarronesarena;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class Tablero extends HBox {
    Logica logica;
    PanelSuperior panelSuperior;
    
    // ESPACIO ENTRE JARRONES
    byte espaciado = 7;
    
    // VARIABLE QUE ALMACENA LA COLUMNA QUE SE HA PRESIONADO (PRESSED)
    byte columnaPressed;
    
    // VARIABLE QUE ALMACENA LA COLUMNA QUE SE HA PRESIONADO (RELEASED)
    byte columnaReleased;
    
    // ALMACENA EL CONTENIDO DE UNA POSICIÓN DEL ARRAY
    int numSeleccionado1;
    
    // ALMACENA EL CONTENIDO DE UNA POSICIÓN DEL ARRAY
    int numSeleccionado2;
    
    // ARRAY DE JARRON (CLASE JARRON)
    static Jarron [] numJarron;
    
    // FILA DE LA COLUMNA PRESIONADA (PRESSED)
    int filaPressed;
    
    // FILA DE LA COLUMNA PRESIONADA (RELEASED)
    int filaReleased;
    
    public Tablero(Logica logica) {
        this.logica = logica;
        
        // ASIGNACIÓN DE LAS DIMENSIONES DE LOS JARRONES
        this.setMinWidth(Jarron.TAM_WIDTH_JARRONES * logica.tamXJarrones);
        this.setMinHeight(Jarron.TAM_HEIGHT_JARRONES * logica.tamYJarrones); 
        this.setMaxWidth(Jarron.TAM_WIDTH_JARRONES * logica.tamXJarrones);
        this.setMaxHeight(Jarron.TAM_HEIGHT_JARRONES * logica.tamYJarrones); 
        
        // ASIGNACIÓN DEL ESPACIO ENTRE JARRONES
        this.setSpacing(espaciado);
        
        // LLAMADA AL MÉTODO DE ESTA MISMA CLASE
        iniciarTablero();
       
        // DETECCIÓN CLICK PRESIONADO
        this.setOnMousePressed((event) -> {
            
            // CUANDO CLICKEA DEVUELVE LA COORDENADA X DONDE SE HA PULSADO Y LA 
            // DIVIDE ENTRE LA ANCHURA DE LOS JARRONES + EL ESPACIADO
            columnaPressed = (byte)(event.getX() / (Jarron.TAM_WIDTH_JARRONES + espaciado));
            
            // ALMACENA LA FILA VACÍA DE LA COLUMNA PRESIONADA 
            filaPressed = this.logica.buscarFila(columnaPressed);
            
            if(filaPressed == -1){
                filaPressed = 0;
            } else {
                filaPressed++;
            }
            
            numSeleccionado1 = logica.jarrones[columnaPressed][filaPressed];
            
            // CAMBIA EL VALOR DE LA POSICION OBTENIDA DEL ARRAY 
            logica.jarrones[columnaPressed][filaPressed] = logica.VACIO;
            
            // COLOCAMOS EL COLOR BLANCO EN LA POSICIÓN SELECCIONADA ANTERIORMENTE
            numJarron[columnaPressed].rect[filaPressed].setFill(Color.WHITE);

        });
        
        // DETECCIÓN CLICK LIBERADO
        this.setOnMouseReleased((event) -> {
            // CUANDO CLICKEA DEVUELVE LA COORDENADA X DONDE SE HA PULSADO Y LA 
            // DIVIDE ENTRE LA ANCHURA DE LOS JARRONES + EL ESPACIADO
            columnaReleased = (byte)(event.getX() / (Jarron.TAM_WIDTH_JARRONES + espaciado));
            
            // ALMACENA LA FILA VACÍA DE LA COLUMNA PRESIONADA
            filaReleased = this.logica.buscarFila(columnaReleased);

            // SUMA 1 AL VALOR DE FILARELEASED
            int filaSiguiente = filaReleased + 1;
            
            // CORRIGE EL ERROR DE LÍMITE DE ARRAY POSICIONANDO NUMSELECCIONADO1 
            // EN SU POSICIÓN ORIGINAL
            if (filaReleased == -1){
                int fila = this.logica.buscarFila(columnaPressed);
                if(fila == -1){
                    fila = 0;
                }
                logica.jarrones[columnaPressed][fila] = numSeleccionado1;
                cambiarColor(numJarron[columnaPressed], fila, numSeleccionado1);
            }
            
            // ALMACENA EL VALOR DE LA POSICIÓN DEL ARRAY EN UNA VARIABLE
            numSeleccionado2 = logica.jarrones[columnaReleased][filaReleased];
            
            // DETECTAR EL COLOR DE LA SIGUIENTE POSICIÓN DEL ARRAY PARA COLOCAR
                // EL NUM ALMACENADO , DEBE SER EL MISMO NÚMERO O SER VACÍO
            // EN CASO DE QUE NO LO SEA VUELVE A COLORCAR EL NÚMERO ALMACENADO 
                // EN SU POSICIÓN ORIGINAL
            if(numSeleccionado1 != 0  && numSeleccionado2 == 0 ){
                if(filaSiguiente == 4){
                    filaSiguiente = 3;
                }

                if(logica.jarrones[columnaReleased][filaSiguiente] == logica.VACIO ||
                        logica.jarrones[columnaReleased][filaSiguiente] == numSeleccionado1){
                    logica.cambiarNum(columnaReleased, numSeleccionado1);
                    cambiarColor(numJarron[columnaReleased], filaReleased, numSeleccionado1);
                    logica.mostrarJarronesConsola();
                    logica.comprobarVertical(columnaReleased);
                } else {
                    int fila = this.logica.buscarFila(columnaPressed);
                    if(fila == -1){
                        fila = 0;
                    }
                    
                    
                    logica.jarrones[columnaPressed][fila] = numSeleccionado1;
                    
                    
                    cambiarColor(numJarron[columnaPressed], fila, numSeleccionado1);
                }

            }
        });
        
    }
    
    // MÉTODO QUE GENERA EL TABLERO
    public void iniciarTablero (){
        numJarron = new Jarron[6];
        for(int iJarron=0; iJarron < logica.tamXJarrones; iJarron++){
            Jarron jarron = new Jarron();
            numJarron[iJarron]= jarron;
           
            for(int fila=0; fila < logica.tamYJarrones; fila++){
                int contenido= 0;
                contenido = Logica.jarrones[iJarron][fila];
                cambiarColor(jarron, fila, contenido);
            } 
            this.getChildren().add(jarron);
        }
       
    }
    
    // MÉTODO QUE ELIMINA EL TABLERO 
    public void elimiarTablero(){
        for(int c=0; c<6;c++){
            this.getChildren().remove(numJarron[c]);
        }
        System.out.println(this.getChildren().size());
    }
    
    // MÉTODO QUE REINICIA EL TABLERO
    public void reinicioTablero(){
        if(Logica.finPartida == true){
            PanelSuperior.tiempo = Logica.TIEMPO_PREDEFINIDO ;
            PanelSuperior.tiempo =  PanelSuperior.tiempo - logica.restante;
        }
        logica = new Logica();
        logica.colocarNumAleatorios();
        logica.cambiarColumna();
        logica.mostrarJarronesConsola();
        iniciarTablero();
        Logica.finPartida = false;
        
    }
    
    // MÉTODO QUE ASIGNA EL COLOR DE LOS JARRONES SEGÚN SU CONTENIDO
    public void cambiarColor( Jarron jarron, int fila, int contenido ){
        
        switch(contenido){
            case 0:
                jarron.rect[fila].setFill(Color.WHITE);
                break;
            case 1:
                jarron.rect[fila].setFill(Color.BLUE);
                break;
            case 2:
                jarron.rect[fila].setFill(Color.RED);
                break;
            case 3:
                jarron.rect[fila].setFill(Color.GREEN);
                break;
            case 4:
                jarron.rect[fila].setFill(Color.ORANGE);
                break;
        }
    }
}
