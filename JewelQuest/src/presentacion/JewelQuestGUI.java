package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class JewelQuestGUI extends JFrame {

    //Items menu
    JMenuItem nuevoMenu, abrirMenu, salvarMenu, salvarComoMenu, salirMenu, cambiarColorMenu;

    private JPanel principal,ventanaInicio, ventanaTablero, ventanaNivel, ventanaColor;
    private CardLayout cd;
    private JButton[][] tablero;
    private int row, column;
    private JLabel puntos, movimientos, inicio;
    private Color color1, color2;
    private JButton colorPpal, colorSec, guardarColores, volverColores;
    private ImageIcon logo;

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

        cambiarColorMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarColor();
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
        logo = new ImageIcon("./images/jewel-quest.png");
        cd = new CardLayout();
        principal = new JPanel(cd);
        principal.setBounds(width / 4, height / 4,width / 2, height / 2);
        add(principal);
        ventanaInicio = new JPanel();
        principal.add(ventanaInicio, "Inicio");
        ventanaInicio.setLayout(null);
        inicio = new JLabel();
        inicio.setIcon(logo);
        ventanaInicio.add(inicio);
        ventanaInicio.setBounds(0,0, principal.getWidth(), principal.getHeight());
        ventanaInicio.setBackground(new Color(242, 153, 74, 255));
        inicio.setBounds((ventanaInicio.getWidth()/10)*3,(ventanaInicio.getHeight()/10)*2,(ventanaInicio.getWidth()/6)*3,(ventanaInicio.getHeight()/4)*2);
        cd.show(principal,"Inicio");
        prepareElementosMenu();
    }


    private void prepareElementosTablero(){
        color1 = Color.WHITE;
        color2 = Color.BLACK;
        cambiarColorMenu.setVisible(true);

        //Creación paneles de la ventana ppal
        ventanaNivel= new JPanel();
        ventanaTablero = new JPanel();
        ventanaNivel.add(ventanaTablero, "Tablero");
        principal.add(ventanaNivel,"Nivel");

        //Evitar problemas de ubicación
        ventanaTablero.setLayout(null);
        ventanaNivel.setLayout(null);

        //Tamaños y ubicaciones de cada panel
        ventanaNivel.setBounds(0,0,principal.getWidth(),principal.getHeight());
        ventanaTablero.setBounds(0,0,(ventanaNivel.getWidth()/4)*3,principal.getHeight());
        ventanaNivel.setBackground(new Color(242, 153, 74, 255));
        matrizTablero(6,6);


        puntos = new JLabel();
        puntos.setText("<html><div style='text-align: center;'> Puntuación<br>0</div></html>");
        puntos.setBounds((ventanaNivel.getWidth()/8)*6 + 50,0,ventanaNivel.getWidth()/4,ventanaNivel.getHeight()/4);
        puntos.setFont(new Font("Monaco",Font.BOLD,25));
        ventanaNivel.add(puntos);

        movimientos = new JLabel();
        movimientos.setText("<html><div style='text-align: center;'> Movimientos<br>0</div></html>");
        movimientos.setBounds((ventanaNivel.getWidth()/8)*6 + 50,(ventanaNivel.getHeight()/4)*2,ventanaNivel.getWidth()/4,ventanaNivel.getHeight()/4);
        movimientos.setFont(new Font("Monaco",Font.BOLD,25));
        ventanaNivel.add(movimientos);

    }

    private void matrizTablero(int row, int column){
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
        cambiarColorMenu.setVisible(false);
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

    private void refresque(){
        ventanaNivel.remove(ventanaTablero);
        ventanaTablero = new JPanel();
        ventanaTablero.setLayout(null);
        ventanaNivel.add(ventanaTablero, "Tablero");
        ventanaTablero.setBounds(0,0,(ventanaNivel.getWidth()/4)*3,principal.getHeight());
        matrizTablero(6, 6);
    }

    private void cambiarColor(){
        prepareElementosCambiarColor();
        prepareAccionesCambiarColor();
        cd.show(principal, "Color");
    }

    private void prepareElementosCambiarColor(){
        ventanaColor = new JPanel();
        ventanaColor.setLayout(null);
        principal.add(ventanaColor, "Color");

        colorPpal = new JButton("Color Principal");
        colorPpal.setBounds((principal.getWidth()/8)*3,(principal.getHeight()/8)*2,(principal.getWidth()/8)*2,principal.getHeight()/8);
        ventanaColor.add(colorPpal);

        colorSec = new JButton("Color Secundario");
        colorSec.setBounds((principal.getWidth()/8)*3,(principal.getHeight()/8)*4,(principal.getWidth()/8)*2,principal.getHeight()/8);
        ventanaColor.add(colorSec);

        volverColores = new JButton("Volver");
        volverColores.setBounds(10,(principal.getHeight()/8)*7,(principal.getWidth()/8),principal.getHeight()/8);
        ventanaColor.add(volverColores);

        guardarColores = new JButton("Guardar");
        guardarColores.setBounds((principal.getWidth()/8)*7-10,(principal.getHeight()/8)*7,(principal.getWidth()/8),principal.getHeight()/8);
        ventanaColor.add(guardarColores);
    }

    private void prepareAccionesCambiarColor(){
        colorPpal.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                elegirColorPpal();
            }
        });

        colorSec.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                elegirColorSec();
            }
        });

        volverColores.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                volverColor();
            }
        });

        guardarColores.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambiosColores();
            }
        });
    }

    private void elegirColorPpal(){
        JColorChooser selector = new JColorChooser();
        color1 = selector.showDialog(null, "Elige un color", Color.GRAY);
    }

    private void elegirColorSec(){
        JColorChooser selector = new JColorChooser();
        color2 = selector.showDialog(null, "Elige un color", Color.GRAY);
    }

    private void volverColor(){
        cd.show(principal, "Nivel");
    }

    private void guardarCambiosColores(){
        refresque();
        cd.show(principal, "Nivel");
    }

    public static void main(String[] args) {
        JewelQuestGUI gui = new JewelQuestGUI();
        gui.setVisible(true);
    }


}
