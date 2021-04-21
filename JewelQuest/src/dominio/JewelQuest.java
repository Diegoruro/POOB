package dominio;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class JewelQuest {
    public Gema[][] tablero;
    int rows, columns, score, movements;
    int filaGanador, columnaGanador;

    public JewelQuest(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.tablero = new Gema[rows+4][columns+4];
        this.score = 0;
        this.movements = 0;
        createTablero(rows, columns);
    }

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

    private int generateRandInt(){
        Random rand = new Random();
        int randomInt = rand.nextInt(5);
        return randomInt;
    }

    //Métodos para realizar un movimiento

    private void interchange(int fromI, int fromJ, int toI, int toJ){
        try{
            Gema from = tablero[fromI][fromJ];
            Gema to = tablero[toI][toJ];
            tablero[toI][toJ] = from;
            tablero[fromI][fromJ] = to;
            movements++;
            prettyPrint();
            String  tipo = isValid(fromI, fromJ, toI, toJ);
            update(filaGanador, columnaGanador, tipo);
            prettyPrint();
        }catch (JewelQuestException e){
            Gema from = tablero[fromI][fromJ];
            Gema to = tablero[toI][toJ];
            tablero[toI][toJ] = from;
            tablero[fromI][fromJ] = to;
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private String isValid(int fromI, int fromJ, int toI, int toJ) throws JewelQuestException{
        //Revisamos que no intercambia gemas del mismo tipo
        if (tablero[fromI][fromJ].getType() == tablero[toI][toJ].getType()){
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

    private int verifyHor(int i , int j){
        int consec = 0;
        int type = tablero[i][j].getType();
        for (int k = j-2; k <= j+2; k++) {
            try {
                if (type == tablero[i][k].getType()) {
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

    private int verifyVer(int i , int j){
        int consec = 0;
        int type = tablero[i][j].getType();
        for (int k = i-2; k <= i+2; k++) {
            try {
                if (type == tablero[k][j].getType()) {
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

    private int verifyDiag1(int i , int j){
        int consec = 0;
        int type = tablero[i][j].getType();
        for (int k = i-2; k <= i+2; k++) {
            try {
                if (type == tablero[k][k].getType()) {
                    consec++;
                    if (consec == 3){
                        filaGanador = k-1;
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

    private int verifyDiag2(int i , int j){
        int consec = 0;
        int type = tablero[i][j].getType();

        int k = i+2;
        int l = j-2;
        while (k>=i-2 && l<=j+2){
            try {
                if (type == tablero[k][l].getType()) {
                    consec++;
                    if (consec == 3){
                        filaGanador = k+1;
                        columnaGanador = k+1;
                        System.out.println("Diag2");
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
    private void update(int i, int j, String forma){
        int tipo = tablero[i][j].getType();
        switch (forma){
            case "Hor":
                while(i-1 >= 2){
                    tablero[i][j].setType(tablero[i-1][j].getType());
                    tablero[i][j+1].setType(tablero[i-1][j+1].getType());
                    tablero[i][j-1].setType(tablero[i-1][j-1].getType());
                    i--;
                }
                tablero[i][j].setType(generateRandInt());
                tablero[i][j+1].setType(generateRandInt());
                tablero[i][j-1].setType(generateRandInt());
                break;
            case "Ver":
                i--;
                while(i-1 >= 2){
                    tablero[i][j].setType(tablero[i-1][j].getType());
                    tablero[i+1][j].setType(tablero[i][j].getType());
                    tablero[i+2][j].setType(tablero[i+1][j].getType());
                    i--;
                }
                tablero[i][j].setType(generateRandInt());
                tablero[i+1][j].setType(generateRandInt());
                tablero[i+2][j].setType(generateRandInt());
                break;
            case "Diag1":
                i++;
                j++;
                while(i-1 >= 2){
                    if (i-2 == 2){
                        tablero[i][j].setType(tablero[i-1][j].getType());
                        tablero[i-1][j-1].setType(tablero[i-2][j-1].getType());
                    }else if(i-1 == 2){
                        tablero[i][j].setType(tablero[i-1][j].getType());
                    }else {
                        tablero[i][j].setType(tablero[i-1][j].getType());
                        tablero[i-1][j-1].setType(tablero[i-2][j-1].getType());
                        tablero[i-2][j-2].setType(tablero[i-3][j-2].getType());
                    }
                    i--;
                }
                tablero[i][j].setType(generateRandInt());
                tablero[i][j-1].setType(generateRandInt());
                tablero[i][j-2].setType(generateRandInt());
                break;

            case "Diag2":
                i++;
                j--;
                while(i-1 >= 2) {
                    if (i - 2 == 2) {
                        tablero[i][j].setType(tablero[i - 1][j].getType());
                        tablero[i - 1][j + 1].setType(tablero[i - 2][j + 1].getType());
                    } else if (i - 1 == 2) {
                        tablero[i][j].setType(tablero[i - 1][j].getType());
                    } else {
                        tablero[i][j].setType(tablero[i - 1][j].getType());
                        tablero[i - 1][j + 1].setType(tablero[i - 2][j + 1].getType());
                        tablero[i - 2][j + 2].setType(tablero[i - 3][j + 2].getType());
                    }
                    i--;
                    prettyPrint();
                    System.out.println();
                }
                tablero[i][j].setType(generateRandInt());
                tablero[i][j+1].setType(generateRandInt());
                tablero[i][j+2].setType(generateRandInt());
                break;

        }
    }

    public void prettyPrint(){
        for (Gema[] row : tablero) {
            for (Gema gema: row){
                try{
                    System.out.print(gema.getType() + " ");
                }catch (Exception e){
                    System.out.print("N" + " ");
                }
            }
        }
    }

    public static void main(String[] args) {
        JewelQuest jq = new JewelQuest(6,6);
        jq.prettyPrint();
        Scanner sc = new Scanner(System.in);
        int fromI = sc.nextInt();
        int fromJ = sc.nextInt();

        int toI = sc.nextInt();
        int toJ = sc.nextInt();

        jq.interchange(fromI, fromJ, toI, toJ);

    }


}
