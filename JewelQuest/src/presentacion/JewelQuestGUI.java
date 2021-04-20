package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class JewelQuestGUI extends JFrame {

    //Items menu
    JMenuItem nuevoMenu, abrirMenu, salvarMenu, salvarComoMenu, salirMenu, cambiarColorMenu;

    private JPanel principal, ventanaTablero, ventanaNivel, color;
    private CardLayout cd;
    private JButton[][] tablero;
    private int row, column;
    private JLabel gemas, movimientos;


    public JewelQuestGUI(){
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
                nuevo();
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
        prepareElementosTablero();
        cd.show(principal,"Nivel");
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
        this.setBounds(width / 4, height / 4,width / 2, height / 2);

        cd = new CardLayout();
        principal = new JPanel(cd);
        principal.setBounds(width / 4, height / 4,width / 2, height / 2);
        add(principal);
        prepareElementosMenu();
    }

    private void prepareElementosTablero(){
        ventanaNivel= new JPanel();
        ventanaTablero = new JPanel();
        ventanaNivel.add(ventanaTablero, "Tablero");
        principal.add(ventanaNivel,"Nivel");
        ventanaTablero.setLayout(null);
        ventanaNivel.setLayout(null);
        ventanaNivel.setBounds(0,0,principal.getWidth(),principal.getHeight());
        ventanaTablero.setBounds(0,0,(ventanaNivel.getWidth()/4)*3,principal.getHeight());
        ventanaNivel.setBackground(Color.red);
        matrizTablero(Color.white,Color.black,6,6);


        gemas = new JLabel("Puntuación \n 0");
        gemas.setBounds((ventanaNivel.getWidth()/8)*6,0,ventanaNivel.getWidth()/4,ventanaNivel.getHeight()/4);
        gemas.setFont(new Font("Monaco",Font.BOLD,25));
        ventanaNivel.add(gemas);

        movimientos = new JLabel("Movimientos \n 0");
        movimientos.setBounds((ventanaNivel.getWidth()/8)*6,(ventanaNivel.getHeight()/4)*2,ventanaNivel.getWidth()/4,ventanaNivel.getHeight()/4);
        movimientos.setFont(new Font("Monaco",Font.BOLD,25));
        ventanaNivel.add(movimientos);

    }

    private void matrizTablero(Color color1, Color color2, int row, int column){
        tablero = new JButton[row][column];
        int heigth = ventanaTablero.getHeight()/row;
        int width = ventanaTablero.getWidth()/column;
        int x;
        int y=0;
        boolean intermitente=true;
        for(int i=0;i<row;i++){
            x=0;
            for(int j=0;j<column;j++){
                tablero[i][j]=new JButton();
                tablero[i][j].setBounds(x,y,width,heigth);
                tablero[i][j].setBorderPainted(false);
                if(intermitente){
                    tablero[i][j].setBackground(color1);
                }
                else{
                    tablero[i][j].setBackground(color2);
                }
                ventanaTablero.add(tablero[i][j]);
                intermitente=!intermitente;
                x+= width;
            }
            intermitente=!intermitente;
            y+= heigth;
        }
    }


    private void prepareElementosMenu(){
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu archivo = new JMenu("Archivo");
        menuBar.add(archivo);

        nuevoMenu = new JMenuItem("Nuevo");
        archivo.add(nuevoMenu);
        archivo.addSeparator();
        cambiarColorMenu = new JMenuItem("Cambiar Color");
        archivo.add(cambiarColorMenu);
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
