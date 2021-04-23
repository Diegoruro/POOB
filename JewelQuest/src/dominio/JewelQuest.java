package dominio;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class JewelQuest {
    public Gema[][] tablero;
    private final int rows;
    private final int columns;
    private int score;
    private int movements;
    public ArrayList<int[]> ganadores;
    int filaGanador, columnaGanador;

    /**
     * Constructor del Jewel Quest
     * @param rows Cantidad de filas del tablero
     * @param columns Cantidad de columnas del tablero
     */
    public JewelQuest(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.tablero = new Gema[rows+4][columns+4];
        this.score = 0;
        this.movements = 0;
        this.ganadores= new ArrayList<>();
        createTablero(rows, columns);
        limpiar();
        this.ganadores.clear();
        this.score = 0;
    }

    /**
     * Método que construye el tablero del juego y lo llena de gemas aleatorias
     * @param rows Cantidad de filas del tablero
     * @param columns Cantidad de columnas del tablero
     */
    private void createTablero(int rows, int columns){
        for (int i = 0; i < rows+4; i++) {
            for (int j = 0; j < columns+4; j++) {
                if(i == 0 || j == 0 || i == rows+2 || j==columns+2 || i == 1 || j == 1 || i == rows+3 || j == columns+3) {
                    this.tablero[i][j] = null;
                }else{
                    this.tablero[i][j] = new Gema(generateRandInt());
                }
            }
        }
    }

    /**
     * Método que genera un entero aleatorio entre 0-5
     * @return Entero aleatorio
     */
    private int generateRandInt(){
        Random rand = new Random();
        int randomInt = rand.nextInt(5);
        return randomInt;
    }

    //Métodos para realizar un movimiento

    /**
     * Método que realiza un movimiento en el tablero
     * @param fromI Fila inicial
     * @param fromJ Columna Inicial
     * @param toI Fila Destino
     * @param toJ Columna Destino
     */
    public void doMovement(int fromI, int fromJ, int toI, int toJ){
        if (!interchange(fromI, fromJ, toI, toJ)) {
            interchange(toI, toJ, fromI, fromJ);
        }
        movements++;
        limpiar();
    }

    /**
     * Método que intenta intercambiar dos gemas y actualizar el tablero
     * @param fromI Fila inicial
     * @param fromJ Columna Inicial
     * @param toI Fila Destino
     * @param toJ Columna Destino
     * @return Booleano que dice si pudo realizar el intercambio
     */
    private boolean interchange(int fromI, int fromJ, int toI, int toJ){
        try{
            //Verificamos y realizamos movimiento
            Gema from = this.tablero[fromI][fromJ];
            Gema to = this.tablero[toI][toJ];
            this.tablero[toI][toJ] = from;
            this.tablero[fromI][fromJ] = to;
            String  tipo = isValid(fromI, fromJ, toI, toJ);
            update(filaGanador, columnaGanador, tipo);
            return true;
        }catch (JewelQuestException e){
            //Si el movimiento no es válido devolvemos el movimiento
            Gema from = this.tablero[fromI][fromJ];
            Gema to = this.tablero[toI][toJ];
            this.tablero[toI][toJ] = from;
            this.tablero[fromI][fromJ] = to;
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    /**
     * Método que verifica si un movimiento puede ser realizado
     * @param fromI Fila inicial
     * @param fromJ Columna Inicial
     * @param toI Fila Destino
     * @param toJ Columna Destino
     * @return tipo Dice el modo en que el movimiento es válido
     * @throws JewelQuestException Si cambia gemas del mismo tipo
     * @throws JewelQuestException Si selecciona dos veces la misma gema
     * @throws JewelQuestException Si se mueve más de una unidad
     */
    private String isValid(int fromI, int fromJ, int toI, int toJ) throws JewelQuestException{
        //Revisamos que no intercambia gemas del mismo tipo
        if (this.tablero[fromI][fromJ].getType() == this.tablero[toI][toJ].getType()){
            throw new JewelQuestException(JewelQuestException.MOVIMIENTO_INVALIDO);
        }

        //Revisamos que no seleccione dos veces la misma posición
        if(Math.abs(fromI - toI)==0 && Math.abs(fromJ-toJ)==0){
            throw new JewelQuestException(JewelQuestException.MOVIMIENTO_INVALIDO);
        }

        //Revisamos que solo se mueva 1 unidad
        if(Math.abs(fromI - toI)>1 || Math.abs(fromJ-toJ)>1){
            throw new JewelQuestException(JewelQuestException.MOVIMIENTO_INVALIDO);
        }

        //Revisamos que hayan suficientes gemas del mismo tipo para realizar el movimiento
        String tipo = verify(toI, toJ);
        return tipo;
    }

    /**
     * Método que verifica si hay suficientes gemas del mismo tipo para hacer un movimiento
     * @param i Fila de la gema
     * @param j Columna de la gema
     * @return Forma en la que es válido el movimiento
     * @throws JewelQuestException Si no hay suficientes gemas del mismo tipo
     */
    private String verify(int i, int j) throws JewelQuestException{
        if (verifyHor(i, j) < 3 && verifyVer(i, j) < 3 && verifyDiag1(i, j)<3 && verifyDiag2(i, j) <3){
            throw new JewelQuestException(JewelQuestException.MOVIMIENTO_INVALIDO);
        }else if(verifyHor(i, j) >= 3){
            return "Hor";
        }else if(verifyVer(i, j) >= 3){
            return "Ver";
        }else if(verifyDiag1(i, j) >= 3){
            return "Diag1";
        }else if(verifyDiag2(i, j) >= 3){
            return "Diag2";
        }
        return null;
    }

    /**
     * Método que verifica si se puede realizar un movimiento horizontal
     * @param i Fila de la gema
     * @param j Columna de la gema
     * @return Cantidad de gemas consecutivas
     */
    private int verifyHor(int i , int j){
        int consec = 0;
        int type = this.tablero[i][j].getType();
        for (int k = j-2; k <= j+2; k++) {
            try {
                if (type == this.tablero[i][k].getType()) {
                    consec++;
                    if (consec == 3){
                        filaGanador = i;
                        columnaGanador = k-1;
                        return consec;
                    }
                } else {
                    consec = 0;
                }
            }catch (NullPointerException e){
            }
        }
        return consec;
    }

    /**
     * Método que verifica si se puede realizar un movimiento vertical
     * @param i Fila de la gema
     * @param j Columna de la gema
     * @return Cantidad de gemas consecutivas
     */
    private int verifyVer(int i , int j){
        int consec = 0;
        int type = this.tablero[i][j].getType();
        for (int k = i-2; k <= i+2; k++) {
            try {
                if (type == this.tablero[k][j].getType()) {
                    consec++;
                    if (consec == 3){
                        filaGanador = k-1;
                        columnaGanador = j;
                        return consec;
                    }
                } else {
                    consec = 0;
                }
            }catch (NullPointerException e){
            }
        }
        return consec;
    }

    /**
     * Método que verifica si se puede realizar un movimiento diagonal
     * @param i Fila de la gema
     * @param j Columna de la gema
     * @return Cantidad de gemas consecutivas
     */
    private int verifyDiag1(int i , int j){
        int consec = 0;
        int type = this.tablero[i][j].getType();
        int k=i-2;
        int l=j-2;
        while (k<=i+2 && l<=j+2){
            try {
                if (type == this.tablero[k][l].getType()) {
                    consec++;
                    if (consec == 3){
                        filaGanador = k+1;
                        columnaGanador = k+1;
                        return consec;
                    }
                } else {
                    consec = 0;
                }
            }catch (NullPointerException e){
            }
            k++;
            l++;
        }
        return consec;
    }

    /**
     * Método que verifica si se puede realizar un movimiento diagonal
     * @param i Fila de la gema
     * @param j Columna de la gema
     * @return Cantidad de gemas consecutivas
     */
    private int verifyDiag2(int i , int j){
        int consec = 0;
        int type = this.tablero[i][j].getType();

        int k = i+2;
        int l = j-2;
        while (k>=i-2 && l<=j+2){
            try {
                if (type == this.tablero[k][l].getType()) {
                    consec++;
                    if (consec == 3){
                        filaGanador = k+1;
                        columnaGanador = k+1;
                        return consec;
                    }
                } else {
                    consec = 0;
                }
            }catch (NullPointerException e){
            }
            k--;
            l++;
        }
        return consec;
    }

    //Método para actualizar el tablero al realizar un movimiento

    /**
     * Método que actualiza el tablero cambiando ubicaciones ganadoras
     * @param i Fila Ganadora
     * @param j Columna Ganadora
     * @param forma Manera en la que se realizó el movimiento
     */
    private void update(int i, int j, String forma){
        int tipo = this.tablero[i][j].getType();
        switch (forma){
            case "Hor":
                if (!inGanadores(new int[]{i ,j-1})) {
                    ganadores.add(new int[]{i, j-1});
                }
                if (!inGanadores(new int[]{i, j})) {
                    ganadores.add(new int[]{i, j});
                }
                if (!inGanadores(new int[]{i, j+1})) {
                    ganadores.add(new int[]{i, j+1});
                }
                while(i-1 >= 2){
                    this.tablero[i][j].setType(this.tablero[i-1][j].getType());
                    this.tablero[i][j+1].setType(this.tablero[i-1][j+1].getType());
                    this.tablero[i][j-1].setType(this.tablero[i-1][j-1].getType());
                    i--;
                }
                this.tablero[i][j].setType(generateRandInt());
                this.tablero[i][j+1].setType(generateRandInt());
                this.tablero[i][j-1].setType(generateRandInt());
                break;
            case "Ver":
                if (!inGanadores(new int[]{i-1,j})) {
                    ganadores.add(new int[]{i-1, j});
                }
                if (!inGanadores(new int[]{i, j})) {
                    ganadores.add(new int[]{i, j});
                }
                if (!inGanadores(new int[]{i+1, j})) {
                    ganadores.add(new int[]{i+1, j});
                }
                i--;
                while(i-1 >= 2){
                    this.tablero[i][j].setType(this.tablero[i-1][j].getType());
                    this.tablero[i+1][j].setType(this.tablero[i][j].getType());
                    this.tablero[i+2][j].setType(this.tablero[i+1][j].getType());
                    i--;
                }
                this.tablero[i][j].setType(generateRandInt());
                this.tablero[i+1][j].setType(generateRandInt());
                this.tablero[i+2][j].setType(generateRandInt());
                break;
            case "Diag1":
                if (!inGanadores(new int[]{i -1,j-1})) {
                    ganadores.add(new int[]{i-1, j-1});
                }
                if (!inGanadores(new int[]{i, j})) {
                    ganadores.add(new int[]{i, j});
                }
                if (!inGanadores(new int[]{i+1, j+1})) {
                    ganadores.add(new int[]{i+1, j+1});
                }
                i++;
                j++;
                while(i-1 >= 2){
                    if (i-2 == 2){
                        this.tablero[i][j].setType(this.tablero[i-1][j].getType());
                        this.tablero[i-1][j-1].setType(this.tablero[i-2][j-1].getType());
                    }else if(i-1 == 2){
                        this.tablero[i][j].setType(this.tablero[i-1][j].getType());
                    }else {
                        this.tablero[i][j].setType(this.tablero[i-1][j].getType());
                        this.tablero[i-1][j-1].setType(this.tablero[i-2][j-1].getType());
                        this.tablero[i-2][j-2].setType(this.tablero[i-3][j-2].getType());
                    }
                    i--;
                }
                this.tablero[i][j].setType(generateRandInt());
                this.tablero[i][j-1].setType(generateRandInt());
                this.tablero[i][j-2].setType(generateRandInt());
                break;

            case "Diag2":

                if (!inGanadores(new int[]{i+1, j-1})) {
                    ganadores.add(new int[]{i+1, j-1});
                }
                if (!inGanadores(new int[]{i, j})) {
                    ganadores.add(new int[]{i, j});
                }
                if (!inGanadores(new int[]{i-1, j+1})) {
                    ganadores.add(new int[]{i-1, j+1});
                }
                i++;
                j--;
                while(i-1 >= 2) {
                    if (i - 2 == 2) {
                        this.tablero[i][j].setType(this.tablero[i - 1][j].getType());
                        this.tablero[i - 1][j + 1].setType(this.tablero[i - 2][j + 1].getType());
                    } else if (i - 1 == 2) {
                        this.tablero[i][j].setType(this.tablero[i - 1][j].getType());
                    } else {
                        this.tablero[i][j].setType(this.tablero[i - 1][j].getType());
                        this.tablero[i - 1][j + 1].setType(this.tablero[i - 2][j + 1].getType());
                        this.tablero[i - 2][j + 2].setType(this.tablero[i - 3][j + 2].getType());
                    }
                    i--;
                }
                this.tablero[i][j].setType(generateRandInt());
                this.tablero[i][j+1].setType(generateRandInt());
                this.tablero[i][j+2].setType(generateRandInt());
                break;

        }
        score += 3;
    }

    /**
     * Método que retorna el tablero de juego
     * @return Tablero actual
     */
    public Gema[][] getTablero() {
        return tablero;
    }

    /**
     * Método que dice si hay un ganador
     * @return Booleano por si hay o no un movimiento ganador
     */
    public boolean hayGanadores(){
        for (int i = 2; i < rows + 2; i++) {
            for (int j = 2; j < columns + 2; j++) {
                try {
                    String resVerify = verify(i, j);
                    if (resVerify != null){
                        return true;
                    }
                }catch(Exception e){
                }
            }
        }
        return false;
    }

    /**
     * Método que limpia el tablero quitando los 3 iguales que tenga
     */
    public void limpiar(){
        while (hayGanadores()) {
            for (int i = 2; i < rows + 2; i++) {
                for (int j = 2; j < columns + 2; j++) {
                    try {
                        update(i, j, verify(i, j));
                    } catch (Exception e) {
                    }
                }
            }
        }
        hayUno();
    }

    /**
     * Método que genera una copia del tablero
     * @return Matriz de copia
     */
    public Gema[][] copiaMatriz(){
        Gema[][] copia =new Gema[rows+4][columns+4];
        for (int i=0;i<rows+4;i++){
            for (int j = 0; j < columns+4; j++) {
                try {
                    copia[i][j] = new Gema(tablero[i][j].getType());
                }catch (NullPointerException e){
                    copia[i][j] = null;
                }
            }
        }
        return copia;
    }

    /**
     * Método que reinicia el puntaje y los movimientos
     */
    public void reiniciarStats(){
        this.score=0;
        this.movements=0;
    }

    /**
     * Método para generar una impresión del tablero de forma organizada
     */
    public void prettyPrint(){
        for (Gema[] row : this.tablero) {
            for (Gema gema: row){
                try{
                    System.out.print(gema.getType() + " ");
                }catch (Exception e){
                    System.out.print("N" + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Método que retorna el puntaje
     * @return Int puntaje
     */
    public int getScore() {
        return score;
    }

    /**
     * Método que retorna la cantidad de movimientos
     * @return Int movimientos
     */
    public int getMovements() {
        return movements;
    }

    /**
     * Método que retorna la cantidad de filas
     * @return int rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Método que retorna la cantidad de columnas
     * @return int columns
     */
    public int getColumns() {
        return columns;
    }
    public boolean inGanadores(int[] coords){
        for (int[] ganador: ganadores) {
            if (coords[0]==ganador[0] && coords[1]==ganador[1]){
                return true;
            }
        }
        return false;
    }
    /**
     * Método que verifica si hay coordenadas erroneas en la lista de posiciones ganadas
     */
    private void hayUno(){
        ganadores.removeIf(tupla -> tupla[0] == 1 || tupla[1] == 1);
    }

    public static void main(String[] args) {
        JewelQuest jq = new JewelQuest(6,6);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            int fromI = sc.nextInt();
            int fromJ = sc.nextInt();

            int toI = sc.nextInt();
            int toJ = sc.nextInt();
            jq.doMovement(fromI, fromJ, toI, toJ);
        }
        System.out.println("\nFinished");



    }


}
