package presentacion;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AutomataGUI extends JFrame{  
    public static final int CELDA=21;
    public static final int DIMENSION=CELDA*32;
    
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
    private JMenuBar menuBarra;
    private JMenu menu;
    private JMenuItem menuAbrir,menuGuardar,menuImportar,menuExportar, menuSalir, menuNuevo;
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
        prepareElementosMenuBarra();
    }

    private void prepareElementosMenuBarra(){
        menuBarra = new JMenuBar();
        setJMenuBar(menuBarra);

        menu = new JMenu("Archivo");
        menuBarra.add(menu);

        menuNuevo = new JMenuItem("Nuevo");
        menu.add(menuNuevo);

        menu.addSeparator();

        menuAbrir = new JMenuItem("Abrir");
        menu.add(menuAbrir);

        menuGuardar = new JMenuItem("Guardar");
        menu.add(menuGuardar);

        menuImportar = new JMenuItem("Importar");
        menu.add(menuImportar);


        menuExportar = new JMenuItem("Exportar");
        menu.add(menuExportar);

        menu.addSeparator();

        menuSalir=new JMenuItem("Salir");
        menu.add(menuSalir);
    }


    private void prepareAcciones(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        botonTicTac.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    botonTicTacAccion();
                }
            });
        prepareAccionesMenuBarra();

    }

    private void prepareAccionesMenuBarra(){
        menuNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevo();
            }
        });
        menuAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcionAbrir();
            }
        });

        menuGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcionGuardar();
            }
        });

        menuExportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcionExportar();
            }
        });

        menuImportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcionImportar();
            }
        });
        menuSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
    }

    private void nuevo(){
        automata = new AutomataCelular();
        foto.repaint();
    }

    private void opcionAbrir(){
        JFileChooser fc = new JFileChooser();
        int sel = fc.showOpenDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            try {
                automata = automata.abrir(file);
                foto.repaint();
            }catch (AutomataExcepcion e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }

    private void abrir00(){
        JFileChooser fc = new JFileChooser();
        int sel = fc.showOpenDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            String name = file.getName();
            try {
                automata.abrir(file);
            }catch (AutomataExcepcion e){
                JOptionPane.showMessageDialog(null,"Funcionalidad Abrir en construccion.\n Archivo a abrir : "+ name);
            }
        }

    }

    private void opcionGuardar(){
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Guardar");
        fc.setApproveButtonText("Guardar");
        int sel = fc.showOpenDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            try {
                automata.guardar(file);
            }catch (AutomataExcepcion e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }

    private void guardar00(){
        JFileChooser fc = new JFileChooser();
        int sel = fc.showOpenDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            String name = file.getName();
            try {
                automata.guardar(file);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Funcionalidad Guardar en construccion.\n Archivo a guardar : "+ name);
            }
        }

    }

    private void opcionExportar(){
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Exportar");
        fc.setApproveButtonText("Exportar");
        int sel = fc.showOpenDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            try {
                automata.exportar(file);
            }catch (AutomataExcepcion e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    private void exportar00(){
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Exportar");
        fc.setApproveButtonText("Exportar");
        int sel = fc.showOpenDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            String name = file.getName();
            try {
                automata.exportar(file);
            }catch (AutomataExcepcion e){
                JOptionPane.showMessageDialog(null,"Funcionalidad Exportar en construccion.\n Archivo a guardar : "+ name);
            }
        }
    }

    private void opcionImportar(){
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Importar");
        fc.setApproveButtonText("Importar");
        int sel = fc.showOpenDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            try {
                automata.importar(file);
                foto.repaint();
            }catch (AutomataExcepcion e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }

    private void importar00(){
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Importar");
        fc.setApproveButtonText("Importar");
        int sel = fc.showOpenDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            String name = file.getName();
            try {
                automata.importar(file);
            }catch (AutomataExcepcion e){
                JOptionPane.showMessageDialog(null,"Funcionalidad Importar en construccion.\n Archivo a Importar : "+ name);
            }
        }
    }

    private void salir(){
        JOptionPane.showMessageDialog(null, "Ejecución Finalizada");
        System.exit(0);
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