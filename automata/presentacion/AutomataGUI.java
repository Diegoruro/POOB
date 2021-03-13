package presentacion;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AutomataGUI extends JFrame{  
    public static final int CELDA=21;
    public static final int DIMENSION=CELDA*31;
    
    private JButton botonTicTac;
    private JLabel lFila;
    private JLabel lColumna;
    private JTextField tFila;
    private JTextField tColumna;
    private JPanel panelControl;
    private JPanel panelNueva;
    private JPanel panelBNueva;
    private JButton botonViva;
    private JButton botonLatente;
    private FotoAutomata foto;
    
    private AutomataCelular automata;

    public AutomataGUI() {
        automata=new AutomataCelular();
        prepareElementos();
        prepareAcciones();
    }
    
    private void prepareElementos() {
        setTitle("Automata celular");
        foto=new FotoAutomata(this);
        botonTicTac=new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(foto,BorderLayout.NORTH);
        add(botonTicTac,BorderLayout.SOUTH);
        setSize(new Dimension(DIMENSION,DIMENSION+50)); 
        setResizable(false);
        foto.repaint();
    }

    private void prepareAcciones(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        botonTicTac.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    botonTicTacAccion();
                }
            });

    }

    private void botonTicTacAccion() {
        automata.ticTac();
        foto.repaint();
    }

    public AutomataCelular getAutomata(){
        return automata;
    }
    
    public static void main(String[] args) {
        AutomataGUI ca=new AutomataGUI();
        ca.setVisible(true);
    }  
}

class FotoAutomata extends JPanel{
    private AutomataGUI gui;

    public FotoAutomata(AutomataGUI gui) {
        this.gui=gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.DIMENSION, gui.DIMENSION));         
    }


    public void paintComponent(Graphics g){
        AutomataCelular automata=gui.getAutomata();
        super.paintComponent(g);
         
        for (int f=0;f<=automata.getLongitud();f++){
            g.drawLine(f*gui.CELDA,0,f*gui.CELDA,automata.getLongitud()*gui.CELDA);
        }
        for (int c=0;c<=automata.getLongitud();c++){
            g.drawLine(0,c*gui.CELDA,automata.getLongitud()*gui.CELDA,c*gui.CELDA);
        }       
        for (int f=0;f<automata.getLongitud();f++){
            for(int c=0;c<automata.getLongitud();c++){
                if (automata.getElemento(f,c)!=null){
                    g.setColor(automata.getElemento(f,c).getColor());
                    if (automata.getElemento(f,c).forma()==Elemento.CUADRADA){                  
                        if (automata.getElemento(f,c).isVivo()){
                            g.fillRoundRect(gui.CELDA*c+1,gui.CELDA*f+1,gui.CELDA-2,gui.CELDA-2,2,2);
                        }else{
                            g.drawRoundRect(gui.CELDA*c+1,gui.CELDA*f+1,gui.CELDA-2,gui.CELDA-2,2,2);    

                        }
                    }else {
                        if (automata.getElemento(f,c).isVivo()){
                            g.fillOval(gui.CELDA*c+1,gui.CELDA*f+1,gui.CELDA-2,gui.CELDA-2);
                        } else {
                            g.drawOval(gui.CELDA*c+1,gui.CELDA*f+1,gui.CELDA-2,gui.CELDA-2);
                        }
                    }
                }
            }
        }
    }
}