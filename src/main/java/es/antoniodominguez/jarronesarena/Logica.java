package es.antoniodominguez.jarronesarena;

import java.util.Random;

public class Logica {
    static short tamXJarrones = 6;
    static short tamYJarrones = 4;
    short cantidadJarronesLlenos = 4; // JARRONES QUE ESTÁN LLENOS, MISMA CANTIDAD DE COLORES
    static int [][] jarrones;
    
    static int restante = 0;
    
    final int VACIO = 0;
    static int color;
    
    final static int TIEMPO_PREDEFINIDO = 30;
    
    int col;
    int fil;
    int col1;
    int fil1;
    int columnaVacia;
    int numSeleccionado;
    int numGuardado;
    boolean comprobar1 = false;
    boolean comprobar2 = false;
    boolean comprobar3 = false;
    boolean comprobar4 = false;
    static boolean finPartida = false;
    
    public Logica() {  
        jarrones = new int[tamXJarrones][tamYJarrones];
        for(int x=0; x<tamXJarrones; x++) {
            for(int y=0; y<tamYJarrones; y++) {
                jarrones[x][y] = VACIO;
            }
        }
    }   
    public Logica(short tamX, short tamY) {  
        tamXJarrones = tamX;
        tamYJarrones = tamY;
        jarrones = new int[tamXJarrones][tamYJarrones];
        for(int x=0; x<tamXJarrones; x++) {
            for(int y=0; y<tamYJarrones; y++) {
                jarrones[x][y] = VACIO;
            }
        }
    }
    public void mostrarJarronesConsola() {
        for(int y=0; y<tamYJarrones; y++) {
            for(int x=0; x<tamXJarrones; x++) {
                System.out.print(jarrones[x][y]);
            }
            System.out.println();
        }    
        System.out.println();
    }
        
    public void colocarNumAleatorios(){
        Random random = new Random();
        
        for(int i=1; i<5; i++){
            for(int x=0; x<tamYJarrones; x++){
                col = random.nextInt(cantidadJarronesLlenos);
                fil = random.nextInt(tamYJarrones);
                if(jarrones[col][fil] == VACIO){
                    jarrones[col][fil]= i;
                    /*System.out.println("posicion COL: " + col);
                    System.out.println("posicion FIL: " + fil);
                    System.out.println("posción: " + jarrones[col][fil]);*/
                } else {
                   // System.out.println("Posicion ocupada: " +"col: " + col + " fil: "+ fil);
                    do {
                        col1 = random.nextInt(cantidadJarronesLlenos);
                        fil1 = random.nextInt(tamYJarrones);
                        
                        //System.out.println("Generando nueva posición: " + "col1: " + col1 + " fil1: "+ fil1);
                                
                    }
                    while(jarrones[col1][fil1] != VACIO );
                    //System.out.println("Posicion encontrada: " + "número: " + jarrones[col1][fil1] + "  col1: " + col1 + "  fil1: "+ fil1 );
                    jarrones[col1][fil1]=i;
                }
            }
        }
    }

    public void cambiarColumna(){
        Random random = new Random();
        for(int i=cantidadJarronesLlenos; i<tamXJarrones; i++){
            columnaVacia = random.nextInt(cantidadJarronesLlenos);
            if(jarrones[columnaVacia][0] != VACIO){
                for(int y=0; y<tamYJarrones; y++){
                    jarrones[i][y] = jarrones[columnaVacia][y];
                    jarrones[columnaVacia][y] = VACIO;
                }
            }
        }
    }
    
    public int cambiarNum(int columna, int numColor) {
        if(columna >= 0 && columna < tamXJarrones) {
            int fila = this.buscarFila(columna);
            if(fila != -1) {
//                System.out.println("Fila cambiarNum " + fila);
//                System.out.println("Columna cambiarNum " + columna);
//                System.out.println("Contenido cambiarNum " + numColor);
                jarrones[columna][fila] = numColor;   
            }
            return fila;
        } else {
            return -1;
        }        
        
    }
    
    public int buscarFila(int columna) {
        int fila = 0;
        //System.out.println("Contenido jarrones " + jarrones[columna][fila]);
        while(fila < tamYJarrones && jarrones[columna][fila] == VACIO) {
            fila++;
        }
        //System.out.println("Fila buscar fila " + fila);
        return fila -1;        
    }
    
    public void reiniciarPartida(){
        //Logica logica = new Logica();
        //Logica logica2 = new Logica(short tamX, short tamY);
       
        this.colocarNumAleatorios();
        this.cambiarColumna();
        this.mostrarJarronesConsola();
    }
    
    
    
    public boolean comprobarVertical(int columna) {
        int contador = 0;
        int num1 = 1;
        int num2 = 2;
        int num3 = 3;
        int num4 = 4;
        Tablero tablero;
        if(jarrones[columna][0] == num1){
            //System.out.println("Has entrado en bucle 1");
            for (int y = 0; y < tamYJarrones; y++){
                if(jarrones[columna][y] == num1){
                    contador++;
                   // System.out.println("Contador 1 " + contador);
                    if(contador == tamYJarrones){
                        comprobar1 = true;
                       // System.out.println("Comprobar 1 " + comprobar1);
                    }
                } else {
                        contador = 0;
                }
            }
        }else if(jarrones[columna][0] == num2){
            //System.out.println("Has entrado en bucle 2");
            for (int y = 0; y < tamYJarrones; y++){
                if(jarrones[columna][y] == num2){
                    contador++;
                    //System.out.println("Contador 2 " + contador);
                    if(contador == tamYJarrones){
                        comprobar2 = true;
                        //System.out.println("Comprobar 2 " + comprobar2);
                    }
                } else {
                        contador = 0;
                }
            }
        }else if(jarrones[columna][0] == num3){
            //System.out.println("Has entrado en bucle 3");
            for (int y = 0; y < tamYJarrones; y++){
                if(jarrones[columna][y] == num3){
                    contador++;
                   // System.out.println("Contador 3 " + contador);
                    if(contador == tamYJarrones){
                        comprobar3 = true;
                       // System.out.println("Comprobar 3 " + comprobar3);
                    }
                } else {
                        contador = 0;
                }
            }
        }else if(jarrones[columna][0] == num4){
            //System.out.println("Has entrado en bucle 4");
            for (int y = 0; y < tamYJarrones; y++){
                if(jarrones[columna][y] == num4){
                    contador++;
                    //System.out.println("Contador 4 " + contador);
                    if(contador == tamYJarrones){
                        comprobar4 = true;
                        //System.out.println("Comprobar 4 " + comprobar4);
                    } 
                } else {
                        contador = 0;
                }
            }
        }
        if (comprobar1 == true && comprobar2 == true && 
                comprobar3 == true && comprobar4 == true){
            System.out.println("Has ganado!!!");
            restante++;
            finPartida = true;
            comprobar1 = false;
            comprobar2 = false;
            comprobar3 = false;
            comprobar4 = false;
            return true;
        }else{
            return false;
        }
    }
}
