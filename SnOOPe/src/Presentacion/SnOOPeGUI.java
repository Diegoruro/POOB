package Presentacion;

import Aplicacion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class SnOOPeGUI extends JFrame {

    CardLayout cd;
    JPanel principal, vistaMenuPrincipal;
    JMenuBar menuBar;
    JMenuItem abrirMenu, guardarMenu,guardarComoMenu, salirMenu;


    public SnOOPeGUI(){
        prepareElementos();
        prepareAcciones();
    }
    public void prepareElementos(){
        this.setTitle("SnOOPe");

        //Dimensiones de la pantalla para que quede centralizado y con dimensiones de 1/4 de la pantalla
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();
        this.setBounds(width / 4, height / 4,width / 2, height / 2);

        //Definimos el panel Principal para poder recorrer entre las diferentes vistas
        cd = new CardLayout();
        principal = new JPanel(cd);
        principal.setBounds(width / 4, height / 4,width / 2, height / 2);
        add(principal);

        prepareElementosMenuBarra();
        prepareElementosMenu();
        cd.show(principal,"MenuPpal");
    }
    public void prepareElementosMenu(){

        //vista del Menu Principal
        vistaMenuPrincipal = new JPanel();
        principal.add(vistaMenuPrincipal, "MenuPpal");
        vistaMenuPrincipal.setLayout(null);
        vistaMenuPrincipal.setBackground(new Color(56, 87, 53));



    }

    public void prepareElementosMenuBarra(){
        //Menu de barra
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu archivo = new JMenu("Archivo");
        menuBar.add(archivo);

        //Items del menu
        abrirMenu = new JMenuItem("Abrir");
        archivo.add(abrirMenu);

        archivo.addSeparator();

        guardarMenu = new JMenuItem("Guardar");
        archivo.add(guardarMenu);

        guardarComoMenu = new JMenuItem("Guardar Como");
        archivo.add(guardarComoMenu);

        archivo.addSeparator();

        salirMenu = new JMenuItem("Salir");
        archivo.add(salirMenu);
    }

    public void prepareAcciones(){
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salir();
            }
        });
        prepareAccionesMenu();
        prepareAccionesMenuBarra();
    }

    public void prepareAccionesMenu(){

    }
    public void prepareAccionesMenuBarra(){
        //Acciones de las opciones de archivo
        abrirMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrir();
            }
        });
        guardarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });
        guardarComoMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });
        salirMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
    }
    public void abrir(){
        JFileChooser fc = new JFileChooser();
        int sel = fc.showOpenDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            String name = file.getName();
            JOptionPane.showMessageDialog(null,"Funcionalidad Abrir en construccion.\n Archivo a abrir : "+ name);
        }
    }

    private void guardar(){
        JFileChooser fc = new JFileChooser();
        int sel =fc.showSaveDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            String name = file.getName();
            JOptionPane.showMessageDialog(null,"Funcionalidad Guardar en construccion.\n Archivo a guardar : "+name);
        }
    }
    public void salir(){
        int res = JOptionPane.showConfirmDialog(this,"Desea salir?", "salir", JOptionPane.YES_NO_OPTION);
        if (res==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        SnOOPeGUI gui = new SnOOPeGUI();
        gui.setVisible(true);
    }

}
