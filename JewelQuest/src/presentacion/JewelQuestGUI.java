package presentacion;

import dominio.JewelQuest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class JewelQuestGUI extends JFrame {

    JewelQuest jq, jqCopia;
    //Items menu
    JMenuItem nuevoMenu, abrirMenu, salvarMenu, salvarComoMenu, salirMenu, cambiarColorMenu, reiniciarMenu, configuracionMenu;

    private JPanel principal,ventanaInicio, ventanaTablero, ventanaNivel, ventanaColor, vacio, ventanaConfiguracion;
    private CardLayout cd;
    private JButton[][] tablero;
    private int row = 6, column=6;
    private JLabel puntos, movimientos, inicio,filas,columnas;
    private Color color1, color2;
    private JButton colorPpal, colorSec, guardarColores, volverColores, guardarConfiguracion, volverConfiguracion;
    private JTextField cRow,cColumn;
    private ImageIcon logo;
    private boolean intermitente = true;
    private int fromI, fromJ, toI, toJ;

    /**
     * Constructor del GUI para JewelQuest
     */
    public JewelQuestGUI(){
        prepareElementos();
        prepareAcciones();
    }

    /**
     * Método encargado de asignar el listener para cuando se desee cerrar la ventana
     */
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

    /**
     * Método que asigna los listeners a los items del menu
     */
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
                abrir();
            }
        });

        salvarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });

        cambiarColorMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarColor();
            }
        });

        reiniciarMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciar();
            }
        });

        configuracionMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                configuracion();
            }
        });
    }

    /**
     * Método que crea un listener para los botones del tablero y lo asigna a estos
     */
    public void prepareAccionesJuego(){
        ActionListener casillaTablero = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();
                if (intermitente){
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < column; j++) {
                            if (tablero[i][j].equals(o)){
                                fromI = i+2;
                                fromJ = j+2;
                            }
                        }
                    }
                    intermitente = !intermitente;
                }else if (!intermitente){
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < column; j++) {
                            if (tablero[i][j].equals(o)) {
                                toI = i + 2;
                                toJ = j + 2;
                                jq.doMovement(fromI, fromJ, toI, toJ);
                                refresque();

                            }
                        }
                    }
                    jq.prettyPrint();
                    System.out.println();
                    intermitente = !intermitente;
                    cd.show(principal, "Tablero");
                }
            }
        };

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                tablero[i][j].addActionListener(casillaTablero);
            }
        }
    }

    private void configuracion(){
        prepareElementosConfiguracion();
        prepareAccionesConfiguracion();
        cd.show(principal,"Configuracion");
    }

    private void prepareElementosConfiguracion(){
        ventanaConfiguracion = new JPanel();
        ventanaConfiguracion.setLayout(null);
        principal.add(ventanaConfiguracion,"Configuracion");

        cRow = new JTextField("6");
        cRow.setBounds((principal.getWidth()/8)*4,(principal.getHeight()/8)*2,principal.getWidth()/20,principal.getHeight()/16);
        ventanaConfiguracion.add(cRow);

        cColumn = new JTextField("6");
        cColumn.setBounds((principal.getWidth()/8)*4,(principal.getHeight()/8)*4,principal.getWidth()/20,principal.getHeight()/16);
        ventanaConfiguracion.add(cColumn);

        filas = new JLabel("Filas");
        filas.setBounds((principal.getWidth()/8)*4-50,(principal.getHeight()/8)*2,principal.getWidth()/20,principal.getHeight()/16);
        filas.setFont(new Font("Monaco",Font.BOLD,20));
        ventanaConfiguracion.add(filas);

        columnas = new JLabel("columnas");
        columnas.setBounds((principal.getWidth()/8)*4-100,(principal.getHeight()/8)*4,principal.getWidth()/10,principal.getHeight()/16);
        columnas.setFont(new Font("Monaco",Font.BOLD,20));
        ventanaConfiguracion.add(columnas);

        volverConfiguracion = new JButton("Volver");
        volverConfiguracion.setBounds(10,(principal.getHeight()/8)*7,(principal.getWidth()/8),principal.getHeight()/8);
        ventanaConfiguracion.add(volverConfiguracion);

        guardarConfiguracion = new JButton("Guardar");
        guardarConfiguracion.setBounds((principal.getWidth()/8)*7-10,(principal.getHeight()/8)*7,(principal.getWidth()/8),principal.getHeight()/8);
        ventanaConfiguracion.add(guardarConfiguracion);
    }

    private void prepareAccionesConfiguracion(){
        volverConfiguracion.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                volverConfiguraciones();
            }
        });
        guardarConfiguracion.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarConfiguraciones();
            }
        });
    }

    private void volverConfiguraciones(){
        cd.show(principal,"Inicio");
    }

    private void guardarConfiguraciones(){
        try {
            row = Integer.parseInt(cRow.getText());
            column = Integer.parseInt(cColumn.getText());
            cd.show(principal, "Inicio");
        }catch (Exception e){
            JOptionPane.showMessageDialog(ventanaConfiguracion,"Por favor ingrese solo numeros enteros.");
        }
    }

    /**
     * Método que genera un nuevo juego y lo muestra
     */
    private void nuevo(){
        jq = new JewelQuest(row, column);
        jqCopia = new JewelQuest(row,column);
        jqCopia.tablero = jq.copiaMatriz();
        prepareElementosTablero();
        prepareAccionesJuego();
        jq.prettyPrint();
        cd.show(principal,"Nivel");
    }

    /**
     * Método que genera la pestaña de confirmación de salida
     */
    private void salir(){
        int ans = JOptionPane.showConfirmDialog(this, "Desea salir?", "Salida", JOptionPane.YES_NO_OPTION);
        if (ans==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    /**
     * Método que genera la pestaña de seleccionar archivo a abrir
     */
    private void abrir() {
        JFileChooser fc = new JFileChooser();
        int sel = fc.showOpenDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File archivo = fc.getSelectedFile();
            String nombre = archivo.getName();
            JOptionPane.showMessageDialog(null,"Funcionalidad Abrir en construccion.\n Archivo a abrir : "+nombre);
        }
    }

    /**
     * Método que genera la pestaña de seleccionar archivo a guardar
     */
    private void guardar(){
        JFileChooser fc = new JFileChooser();
        int sel =fc.showSaveDialog(this);

        if (sel==JFileChooser.APPROVE_OPTION){
            File archivo = fc.getSelectedFile();
            String nombre = archivo.getName();
            JOptionPane.showMessageDialog(null,"Funcionalidad Guardar en construccion.\n Archivo a guardar : "+nombre);
        }
    }

    /**
     * Método que genera la pestaña para realizar el cambio de color
     */
    private void cambiarColor(){
        prepareElementosCambiarColor();
        prepareAccionesCambiarColor();
        cd.show(principal, "Color");
    }

    private void reiniciar(){
        jq.tablero=jqCopia.copiaMatriz();
        refresque();
        jq.reiniciarStats();
        updateStats();
    }

    /**
     * Método que genera la ventana principal
     */
    private void prepareElementos() {
        this.setTitle("Jewel Quest");
        //Tomamos dimensiones de la pantalla y ajustamos la ventana a 1/4 de esta
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();
        this.setBounds(width / 4, height / 4,width / 2, height / 2);

        logo = new ImageIcon("./images/jewel-quest.png");

        //Definimos el tipo de layout a usar y creamos el panel ppal
        cd = new CardLayout();
        principal = new JPanel(cd);
        principal.setBounds(width / 4, height / 4,width / 2, height / 2);
        add(principal);

        //Creación panel de inicio
        ventanaInicio = new JPanel();
        principal.add(ventanaInicio, "Inicio");
        ventanaInicio.setLayout(null);
        //Label del logo
        inicio = new JLabel();
        inicio.setIcon(logo);

        ventanaInicio.add(inicio);
        ventanaInicio.setBounds(0,0, principal.getWidth(), principal.getHeight());
        ventanaInicio.setBackground(new Color(242, 153, 74, 255));
        inicio.setBounds((ventanaInicio.getWidth()/10)*3,(ventanaInicio.getHeight()/10)*2,(ventanaInicio.getWidth()/6)*3,(ventanaInicio.getHeight()/4)*2);

        cd.show(principal,"Inicio");

        prepareElementosMenu();
    }

    /**
     * Método que asigna el menu y sus items a la ventana
     */
    private void prepareElementosMenu(){
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu archivo = new JMenu("Archivo");
        menuBar.add(archivo);

        nuevoMenu = new JMenuItem("Nuevo");
        archivo.add(nuevoMenu);

        configuracionMenu = new JMenuItem("Configuracion");
        archivo.add(configuracionMenu);

        archivo.addSeparator();

        cambiarColorMenu = new JMenuItem("Cambiar Color");
        archivo.add(cambiarColorMenu);
        cambiarColorMenu.setVisible(false);

        reiniciarMenu = new JMenuItem("Reiniciar");
        archivo.add(reiniciarMenu);
        reiniciarMenu.setVisible(false);

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

    /**
     * Método que crea la ventana de juego
     */
    private void prepareElementosTablero(){
        color1 = Color.WHITE;
        color2 = Color.BLACK;
        cambiarColorMenu.setVisible(true);
        reiniciarMenu.setVisible(true);
        configuracionMenu.setVisible(false);

        //Creación paneles de la ventana ppal
        ventanaNivel= new JPanel();
        ventanaTablero = new JPanel();
        vacio = new JPanel();
        ventanaNivel.add(ventanaTablero, "Tablero");
        principal.add(ventanaNivel,"Nivel");
        ventanaNivel.add(vacio, "Vacio");

        //Evitar problemas de ubicación
        ventanaTablero.setLayout(null);
        ventanaNivel.setLayout(null);

        //Tamaños y ubicaciones de cada panel
        ventanaNivel.setBounds(0,0,principal.getWidth(),principal.getHeight());
        ventanaTablero.setBounds(0,0,(ventanaNivel.getWidth()/4)*3,principal.getHeight());
        ventanaNivel.setBackground(new Color(242, 153, 74, 255));
        matrizTablero(row,column);

        //Etiqueta puntuación
        puntos = new JLabel();
        puntos.setText("<html><div style='text-align: center;'> Puntuación<br>" + jq.getScore() +"</div></html>");
        puntos.setBounds((ventanaNivel.getWidth()/8)*6 + 50,0,ventanaNivel.getWidth()/4,ventanaNivel.getHeight()/4);
        puntos.setFont(new Font("Monaco",Font.BOLD,25));
        ventanaNivel.add(puntos);

        //Etiqueta movimientos
        movimientos = new JLabel();
        movimientos.setText("<html><div style='text-align: center;'> Movimientos<br>" + jq.getMovements() + "</div></html>");
        movimientos.setBounds((ventanaNivel.getWidth()/8)*6 + 50,(ventanaNivel.getHeight()/4)*2,ventanaNivel.getWidth()/4,ventanaNivel.getHeight()/4);
        movimientos.setFont(new Font("Monaco",Font.BOLD,25));
        ventanaNivel.add(movimientos);

    }

    /**
     * Método que crea los objetos de la ventana de cambio de color
     */
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

    /**
     * Método que genera una matriz de botones dada su dimension
     * @param row Cantidad de filas
     * @param column Cantidad de columnas
     */
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
                tablero[i][j].setIcon(new ImageIcon(jq.tablero[i+2][j+2].getRuta()));
                ventanaTablero.add(tablero[i][j]);
                intermitente=!intermitente;
                x+= width;
            }
            if(column%2==0) {
                intermitente = !intermitente;
            }
            y+= heigth;
        }
    }

    /**
     * Método que actualiza la ventana
     */
    private void refresque(){
        ventanaNivel.remove(ventanaTablero);
        ventanaTablero = new JPanel();
        ventanaTablero.setLayout(null);
        ventanaNivel.add(ventanaTablero, "Tablero");
        ventanaTablero.setBounds(0,0,(ventanaNivel.getWidth()/4)*3,principal.getHeight());
        jq.limpiar();
        matrizTablero(row, column);
        prepareAccionesJuego();
        updateStats();
    }

    /**
     * Método que actualiza el puntaje y los movimientos
     */
    private void updateStats(){
        puntos.setText("<html><div style='text-align: center;'> Puntuación<br>" + jq.getScore() +"</div></html>");
        movimientos.setText("<html><div style='text-align: center;'> Movimientos<br>" + jq.getMovements() + "</div></html>");
    }

    /**
     * Método que añade los listeners para realizar el cambio de color
     */
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

    /**
     * Método que genera la ventana para seleccionar el color principal
     */
    private void elegirColorPpal(){
        JColorChooser selector = new JColorChooser();
        color1 = selector.showDialog(null, "Elige un color", Color.GRAY);
    }

    /**
     * Método que genera la ventana para seleccionar el color secundario
     */
    private void elegirColorSec(){
        JColorChooser selector = new JColorChooser();
        color2 = selector.showDialog(null, "Elige un color", Color.GRAY);
    }

    /**
     * Método que vuelve de la ventana de cambio de color a la ventana de juego
     */
    private void volverColor(){
        cd.show(principal, "Nivel");
    }

    /**
     * Método que guarada la selección de colores y los aplica en la ventana de juego
     */
    private void guardarCambiosColores(){
        refresque();
        cd.show(principal, "Nivel");
    }


    public static void main(String[] args) {
        JewelQuestGUI gui = new JewelQuestGUI();
        gui.setVisible(true);
    }
}
