package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class JewelQuestGUI extends JFrame {

    JMenuItem nuevoMenu, abrirMenu, salvarMenu, salvarComoMenu, salirMenu;
    private JPanel principal;
    private JPanel ventanaTablero;
    private JPanel opciones;
    private CardLayout cd;
    private JButton[][] tablero;
    private int row, column;

    public JewelQuestGUI(){
        cd = new CardLayout();
        principal = new JPanel(cd);
        add(principal);
        prepareElementos();
        prepareAcciones();
    }

    private void prepareAcciones() {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //Salir de la app
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                salir();
            }
        });
        prepareAccionesMenu();
    }

    private void prepareAccionesMenu(){

        nuevoMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareElementosTablero();
            }
        });
        salirMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        abrirMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrir(e);
            }
        });

        salvarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardar(e);
            }
        });
    }

    private void nuevo(){
        cd.show(principal,"Tablero");
    }


    //Metodos usados por PrepareAcciones
    private void salir(){
        int ans = JOptionPane.showConfirmDialog(this, "Desea salir?", "Salida", JOptionPane.YES_NO_OPTION);
        if (ans==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    private void prepareElementos() {
        this.setTitle("Jewel Quest");

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();
        this.setLocation(width / 4, height / 4);

        this.setSize(width / 2, height / 2);

        prepareElementosMenu();
    }

    private void prepareElementosTablero(){
        ventanaTablero = new JPanel();
        row=6;
        column=6;
        tablero = new JButton[row][column];
        ventanaTablero.setLayout(null);
        int heigth = ventanaTablero.getHeight()/row;
        int width = ventanaTablero.getWidth()/column;
        int x;
        int y=0;
        principal.add(ventanaTablero, "Tablero");
        boolean intermitente=false;
        for(int i=0;i<row;i++){
            x=0;
            for(int j=0;j<column;j++){
                tablero[i][j]=new JButton();
                tablero[i][j].setBounds(x,y,width,heigth);
                if(intermitente){
                    tablero[i][j].setBackground(Color.white);
                    intermitente=false;
                }
                else{
                    tablero[i][j].setBackground(Color.black);
                    intermitente=true;
                }
                ventanaTablero.add(tablero[i][j]);
                x+=heigth;
            }
            y+=width;
        }
        nuevo();
    }

    private void prepareElementosMenu(){
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu archivo = new JMenu("Archivo");
        menuBar.add(archivo);

        nuevoMenu = new JMenuItem("Nuevo");
        archivo.add(nuevoMenu);
        archivo.addSeparator();
        abrirMenu = new JMenuItem("Abrir");
        archivo.add(abrirMenu);
        salvarMenu = new JMenuItem("Salvar");
        archivo.add(salvarMenu);
        salvarComoMenu = new JMenuItem("Salvar Como");
        archivo.add(salvarComoMenu);
        archivo.addSeparator();
        salirMenu = new JMenuItem("Salir");
        archivo.add(salirMenu);
    }

    private void abrir(java.awt.event.ActionEvent evt) {
        JFileChooser fc = new JFileChooser();
        int sel = fc.showOpenDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File archivo = fc.getSelectedFile();
            String nombre = archivo.getName();
            JOptionPane.showMessageDialog(null,"Funcionalidad Abrir en construccion.\n Archivo a abrir : "+nombre);
        }
    }

    private void guardar(java.awt.event.ActionEvent evt){
        JFileChooser fc = new JFileChooser();
        int sel =fc.showSaveDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File archivo = fc.getSelectedFile();
            String nombre = archivo.getName();
            JOptionPane.showMessageDialog(null,"Funcionalidad Guardar en construccion.\n Archivo a guardar : "+nombre);

        }
    }


    public static void main(String[] args) {
        JewelQuestGUI gui = new JewelQuestGUI();
        gui.setVisible(true);
    }


}
