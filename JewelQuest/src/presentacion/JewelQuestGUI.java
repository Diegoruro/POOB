package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JewelQuestGUI extends JFrame {

    JMenuItem nuevoMenu, abrirMenu, salvarMenu, salvarComoMenu, salirMenu;

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
        salirMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
    }

    //Metodos usados por PrepareAcciones
    private void salir(){
        int ans = JOptionPane.showConfirmDialog(this, "Desea salir?", "Salida", JOptionPane.YES_NO_OPTION);
        if (ans==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    private void prepareElementos(){
        this.setTitle("Jewel Quest");

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();
        this.setLocation(width/4,height/4);

        this.setSize(width/2, height/2);

        prepareElementosMenu();
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


    public static void main(String[] args) {
        JewelQuestGUI gui = new JewelQuestGUI();
        gui.setVisible(true);
    }


}
