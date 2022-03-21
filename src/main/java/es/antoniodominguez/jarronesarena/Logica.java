package es.antoniodominguez.jarronesarena;

import java.util.Random;

public class Logica {
    
    // TAMAÑO DEL ARRAY EJE X
    static short tamXJarrones = 6;
    
    // TAMAÑO DEL ARRAY EJE Y
    static short tamYJarrones = 4;
    
    // CANTIDAD DE JARRONES QUE SE LLENARÁN DE NUM ALEATORIOS
    short cantidadJarronesLlenos = 4;
    
    // CREACIÓN DEL ARRAY JARRONES 
    static int [][] jarrones;
    
    // CANTIDAD QUE SE LE RESTA A CONTADOR POR CADA VICTORIA
    static int restante = 0;
    
    // VALOR QUE TIENE EL CONTENIDO DEL ARRAY CUANDO ESTÁ VACIO
    final int VACIO = 0;
        
    // TIEMPO PREDEFINIDO PARA EL CONTADOR
    final static int TIEMPO_PREDEFINIDO = 30;
    
    // VARIABLES PARA ALMACENAR POSICIONES DEL ARRAY
    int col;
    int fil;
    int col1;
    int fil1;
    int columnaVacia;
    
    // VARIABLES PARA ALMACENAR EL CONTENIDO DE CIERTAS POSICIONES DEL ARRAY
    int numSeleccionado;
    int numGuardado;
    
    // VARIABLES BOOLEAN PARA COMPROBAR QUE CADA UNA DE LAS COLUMNAS COLOREADAS ESTÁN COMPLETADAS
    boolean comprobar1 = false;
    boolean comprobar2 = false;
    boolean comprobar3 = false;
    boolean comprobar4 = false;
    
    // BOOLEAN PARA DETERMINAR SI LA PARTIDA HA TERMINADO
    static boolean finPartida = false;
    
    
    // CONSTRUCTOR QUE GENERA EL TAMAÑO DEL ARRAY
    public Logica() {  
        jarrones = new int[tamXJarrones][tamYJarrones];
        for(int x=0; x<tamXJarrones; x++) {
            for(int y=0; y<tamYJarrones; y++) {
                jarrones[x][y] = VACIO;
            }
        }
    }   
    
    // MÉTODO QUE MUESRTA POR CONSOLA EL ARRAY Y SU CONTENIDO
    public void mostrarJarronesConsola() {
        for(int y=0; y<tamYJarrones; y++) {
            for(int x=0; x<tamXJarrones; x++) {
                System.out.print(jarrones[x][y]);
            }
            System.out.println();
        }    
        System.out.println();
    }
        
    // MÉTODO QUE GENERA 4 VECES LOS NÚMEROS DEL 1 AL 4 Y LOS COLOCA ALEATORIAMENTE EN EL ARRAY
    public void colocarNumAleatorios(){
        Random random = new Random();
        
        for(int i=1; i<5; i++){
            for(int x=0; x<tamYJarrones; x++){
                col = random.nextInt(cantidadJarronesLlenos);
                fil = random.nextInt(tamYJarrones);
                if(jarrones[col][fil] == VACIO){
                    jarrones[col][fil]= i;
                } else {
                    do {
                        col1 = random.nextInt(cantidadJarronesLlenos);
                        fil1 = random.nextInt(tamYJarrones);
                    }
                    while(jarrones[col1][fil1] != VACIO );
                    jarrones[col1][fil1]=i;
                }
            }
        }
    }

    // MÉTODO QUE CAMBIA LA POSICIÓN DE LAS COLMNAS VACÍAS POR LAS LLENAS
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
    
    // MÉTODO QUE CAMBIA EL NÚMERO RECIBIDO A LA COLUMNA PRESIONADA (RELEASED)
    public int cambiarNum(int columna, int numColor) {
        if(columna >= 0 && columna < tamXJarrones) {
            int fila = this.buscarFila(columna);
            if(fila != -1) {
                jarrones[columna][fila] = numColor;   
            }
            return fila;
        } else {
            return -1;
        }        
        
    }
    
    // MÉTODO QUE BUSCA LA POSICION ADECUADA EN LA FILA DE LA COLUMNA PRESIONADA (PRESSED/RELEASED)
    public int buscarFila(int columna) {
        int fila = 0;
        while(fila < tamYJarrones && jarrones[columna][fila] == VACIO) {
            fila++;
        }
        return fila -1;        
    }
    
    // MÉTODO QUE LLAMA A LOS MÉTODOS NECESARIOS PARA QUE LA PARTIDA EMPIECE DE NUEVO
    public void reiniciarPartida(){
        this.colocarNumAleatorios();
        this.cambiarColumna();
        this.mostrarJarronesConsola();
    }
    
    // MÉTODO QUE COMPARA EL CONTENIDO DE LA COLUMNA CON LOS POSIBLES NÚMEROS,
    // EN CASO DE QUE TODA LA COLUMNA COMPARTA EL MISMO CONTENIDO CAMBIARÁ EL VALOR DE LOS BOOLEANS
    // EN CASO DE QUE TODAS LAS COLUMNAS ESTÉN COMPLETAS LA PARTIDA TERMINA POR GANAR
    public boolean comprobarVertical(int columna) {
        int contador = 0;
        int num1 = 1;
        int num2 = 2;
        int num3 = 3;
        int num4 = 4;
        
        if(jarrones[columna][0] == num1){
            for (int y = 0; y < tamYJarrones; y++){
                if(jarrones[columna][y] == num1){
                    contador++;
                    if(contador == tamYJarrones){
                        comprobar1 = true;
                    }
                } else {
                        contador = 0;
                }
            }
        }else if(jarrones[columna][0] == num2){
            for (int y = 0; y < tamYJarrones; y++){
                if(jarrones[columna][y] == num2){
                    contador++;
                    if(contador == tamYJarrones){
                        comprobar2 = true;
                    }
                } else {
                        contador = 0;
                }
            }
        }else if(jarrones[columna][0] == num3){
            for (int y = 0; y < tamYJarrones; y++){
                if(jarrones[columna][y] == num3){
                    contador++;
                    if(contador == tamYJarrones){
                        comprobar3 = true;
                    }
                } else {
                        contador = 0;
                }
            }
        }else if(jarrones[columna][0] == num4){
            for (int y = 0; y < tamYJarrones; y++){
                if(jarrones[columna][y] == num4){
                    contador++;
                    if(contador == tamYJarrones){
                        comprobar4 = true;
                    } 
                } else {
                        contador = 0;
                }
            }
        }
        
        // SITUACIÓN DE VICTORIA 
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
