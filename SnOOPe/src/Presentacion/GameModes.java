package Presentacion;

import Aplicacion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GameModes extends JFrame {
    public JPanel vistaGameMode,vistaSingle,vistaMulti,vistaIA;
    private JButton menuSingle,menuMulti,menuIA,menuVolver;
    private JButton singleColorCuerpo,singleColorCabeza,singleJugar,singleVolver;
    private JButton multiColorCuerpo1,multiColorCabeza1,multiColorCuerpo2,multiColorCabeza2,multiJugar,multiVolver;
    private JButton iAColorCuerpo,iAColorCabeza,iAJugar,iAVolver;
    private JComboBox iAMode;
    private JTextField singleName, multiName1,multiName2,iAName;
    private SnOOPeGUI gui;
    private Color colorCabeza1,colorCabeza2,colorCuerpo1,colorCuerpo2;

    public GameModes(SnOOPeGUI gui){
        this.gui=gui;
        prepareElementosMenu();
        prepareAccionesMenu();
    }

    public void prepareElementosMenu(){
        //Menu de modos de juego
        vistaGameMode=new JPanel();
        this.gui.principal.add(vistaGameMode,"GameMode");
        vistaGameMode.setLayout(null);
        vistaGameMode.setBounds(gui.width / 4, gui.height / 4,gui.width / 2, gui.height / 2);
        vistaGameMode.setBackground(new Color(56, 87, 53));


        //Boton Single Player
        menuSingle = new JButton("Un Jugador");
        menuSingle.setBounds((vistaGameMode.getWidth()/8)*3,(vistaGameMode.getHeight()/12)*2,(vistaGameMode.getWidth()/8)*2,vistaGameMode.getHeight()/8);
        vistaGameMode.add(menuSingle);

        //Boton Multiplayer
        menuMulti = new JButton("Multijugador");
        menuMulti.setBounds((vistaGameMode.getWidth()/8)*3,(vistaGameMode.getHeight()/12)*4,(vistaGameMode.getWidth()/8)*2,vistaGameMode.getHeight()/8);
        vistaGameMode.add(menuMulti);

        //Boton VS IA
        menuIA = new JButton("Vs IA");
        menuIA.setBounds((vistaGameMode.getWidth()/8)*3,(vistaGameMode.getHeight()/12)*6,(vistaGameMode.getWidth()/8)*2,vistaGameMode.getHeight()/8);
        vistaGameMode.add(menuIA);
        //Boton Volver
        menuVolver = new JButton("Volver");
        menuVolver.setBounds((vistaGameMode.getWidth()/8)*3,(vistaGameMode.getHeight()/12)*8,(vistaGameMode.getWidth()/8)*2,vistaGameMode.getHeight()/8);
        vistaGameMode.add(menuVolver);
    }

    private void prepareElementosMenuSingle(){
        //Menu de Single player
        vistaSingle = new JPanel();
        this.gui.principal.add(vistaSingle,"SinglePlayer");
        vistaSingle.setLayout(null);
        vistaSingle.setBounds(gui.width / 4, gui.height / 4,gui.width / 2, gui.height / 2);
        vistaSingle.setBackground(new Color(56, 87, 53));

        // Nombre Jugador
        singleName = new JTextField("Jugador");
        singleName.setBounds((vistaSingle.getWidth()/8)*3,(vistaSingle.getHeight()/10)*2,(vistaSingle.getWidth()/8)*2,vistaSingle.getHeight()/8);
        vistaSingle.add(singleName);

        //Boton Color Cabeza
        singleColorCabeza = new JButton("Color de la cabeza");
        singleColorCabeza.setBounds((vistaSingle.getWidth()/8)*2,(vistaSingle.getHeight()/10)*4,(vistaSingle.getWidth()/8)*2,vistaSingle.getHeight()/8);
        vistaSingle.add(singleColorCabeza);

        //Boton Color Cuerpo
        singleColorCuerpo = new JButton("Color del cuerpo");
        singleColorCuerpo.setBounds((vistaSingle.getWidth()/8)*4,(vistaSingle.getHeight()/10)*4,(vistaSingle.getWidth()/8)*2,vistaSingle.getHeight()/8);
        vistaSingle.add(singleColorCuerpo);

        //Boton volver
        singleVolver = new JButton("Volver");
        singleVolver.setBounds((vistaSingle.getWidth()/8)*2,(vistaSingle.getHeight()/10)*6,(vistaSingle.getWidth()/8)*2,vistaSingle.getHeight()/8);
        vistaSingle.add(singleVolver);

        //Boton jugar
        singleJugar = new JButton("Jugar");
        singleJugar.setBounds((vistaSingle.getWidth()/8)*4,(vistaSingle.getHeight()/10)*6,(vistaSingle.getWidth()/8)*2,vistaSingle.getHeight()/8);
        vistaSingle.add(singleJugar);
    }

    private void prepareElementosMenuMulti(){
        //Menu de Multiplayer
        vistaMulti = new JPanel();
        this.gui.principal.add(vistaMulti,"MultiPlayer");
        vistaMulti.setLayout(null);
        vistaMulti.setBounds(gui.width / 4, gui.height / 4,gui.width / 2, gui.height / 2);
        vistaMulti.setBackground(new Color(56, 87, 53));

        //Nombre Jugador1
        multiName1 = new JTextField("Jugador1");
        multiName1.setBounds((vistaMulti.getWidth()/8)*3,(vistaMulti.getHeight()/14)*2,(vistaMulti.getWidth()/8)*2,vistaMulti.getHeight()/8);
        vistaMulti.add(multiName1);

        //Boton Color Cabeza 1
        multiColorCabeza1 = new JButton("Color de la cabeza");
        multiColorCabeza1.setBounds((vistaMulti.getWidth()/8)*2,(vistaMulti.getHeight()/14)*4,(vistaMulti.getWidth()/8)*2,vistaMulti.getHeight()/8);
        vistaMulti.add(multiColorCabeza1);

        //Boton Color Cuerpo 1
        multiColorCuerpo1 = new JButton("Color de la cuerpo");
        multiColorCuerpo1.setBounds((vistaMulti.getWidth()/8)*4,(vistaMulti.getHeight()/14)*4,(vistaMulti.getWidth()/8)*2,vistaMulti.getHeight()/8);
        vistaMulti.add(multiColorCuerpo1);

        //Nombre Jugador 2
        multiName2 = new JTextField("Jugador2");
        multiName2.setBounds((vistaMulti.getWidth()/8)*3,(vistaMulti.getHeight()/14)*6,(vistaMulti.getWidth()/8)*2,vistaMulti.getHeight()/8);
        vistaMulti.add(multiName2);

        //Boton Color Cabeza 2
        multiColorCabeza2 = new JButton("Color de la cabeza");
        multiColorCabeza2.setBounds((vistaMulti.getWidth()/8)*2,(vistaMulti.getHeight()/14)*8,(vistaMulti.getWidth()/8)*2,vistaMulti.getHeight()/8);
        vistaMulti.add(multiColorCabeza2);

        //Boton Color Cuerpo 2
        multiColorCuerpo2 = new JButton("Color del cuerpo");
        multiColorCuerpo2.setBounds((vistaMulti.getWidth()/8)*4,(vistaMulti.getHeight()/14)*8,(vistaMulti.getWidth()/8)*2,vistaMulti.getHeight()/8);
        vistaMulti.add(multiColorCuerpo2);

        //Boton volver
        multiVolver = new JButton("Volver");
        multiVolver.setBounds((vistaMulti.getWidth()/8)*2,(vistaMulti.getHeight()/14)*10,(vistaMulti.getWidth()/8)*2,vistaMulti.getHeight()/8);
        vistaMulti.add(multiVolver);

        //Boton jugar
        multiJugar = new JButton("Jugar");
        multiJugar.setBounds((vistaMulti.getWidth()/8)*4,(vistaMulti.getHeight()/14)*10,(vistaMulti.getWidth()/8)*2,vistaMulti.getHeight()/8);
        vistaMulti.add(multiJugar);
    }

    private void prepareElementosMenuIA(){
        //Menu IA
        vistaIA = new JPanel();
        this.gui.principal.add(vistaIA,"IA");
        vistaIA.setLayout(null);
        vistaIA.setBounds(gui.width / 4, gui.height / 4,gui.width / 2, gui.height / 2);
        vistaIA.setBackground(new Color(56, 87, 53));

        //Nombre Jugador
        iAName = new JTextField("Jugador");
        iAName.setBounds((vistaIA.getWidth()/8)*3,(vistaIA.getHeight()/12)*2,(vistaIA.getWidth()/8)*2,vistaIA.getHeight()/8);
        vistaIA.add(iAName);

        //Color de la cabeza
        iAColorCabeza = new JButton("Color de la cabeza");
        iAColorCabeza.setBounds((vistaIA.getWidth()/8)*2,(vistaIA.getHeight()/12)*4,(vistaIA.getWidth()/8)*2,vistaIA.getHeight()/8);
        vistaIA.add(iAColorCabeza);

        //Color del cuerpo
        iAColorCuerpo = new JButton("Color del cuerpo");
        iAColorCuerpo.setBounds((vistaIA.getWidth()/8)*4,(vistaIA.getHeight()/12)*4,(vistaIA.getWidth()/8)*2,vistaIA.getHeight()/8);
        vistaIA.add(iAColorCuerpo);

        //Modo de la serpiente
        iAMode = new JComboBox<String>();
        iAMode.addItem("Distraida");
        iAMode.addItem("Prudente");
        iAMode.addItem("Glotona");
        iAMode.setBounds((vistaIA.getWidth()/8)*3,(vistaIA.getHeight()/12)*6,(vistaIA.getWidth()/8)*2,vistaIA.getHeight()/20);
        vistaIA.add(iAMode);

        //Volver
        iAVolver = new JButton("Volver");
        iAVolver.setBounds((vistaIA.getWidth()/8)*2,(vistaIA.getHeight()/12)*7,(vistaIA.getWidth()/8)*2,vistaIA.getHeight()/8);
        vistaIA.add(iAVolver);

        //Jugar
        iAJugar = new JButton("Jugar");
        iAJugar.setBounds((vistaIA.getWidth()/8)*4,(vistaIA.getHeight()/12)*7,(vistaIA.getWidth()/8)*2,vistaIA.getHeight()/8);
        vistaIA.add(iAJugar);

    }

    public void prepareAccionesMenu(){
        menuSingle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                single();
            }
        });

        menuMulti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                multi();
            }
        });

        menuIA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iA();
            }
        });

        menuVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverPpal();
            }
        });
    }

    private void prepareAccionesMenuSingle(){
        singleColorCabeza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elegirColorCabeza1();
            }
        });
        singleColorCuerpo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elegirColorCuerpo1();
            }
        });
        singleVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverGameMode();
            }
        });
    }

    private void prepareAccionesMenuMulti(){
        multiColorCuerpo1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elegirColorCuerpo1();
            }
        });

        multiColorCuerpo2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elegirColorCuerpo2();
            }
        });

        multiColorCabeza1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elegirColorCabeza1();
            }
        });

        multiColorCabeza2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elegirColorCabeza2();
            }
        });

        multiVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverGameMode();
            }
        });
    }

    private void prepareAccionesMenuIA(){
        iAColorCabeza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elegirColorCabeza1();
            }
        });

        iAColorCuerpo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elegirColorCuerpo1();
            }
        });

        iAVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverGameMode();
            }
        });
    }

    private void elegirColorCabeza1(){
        JColorChooser selector = new JColorChooser();
        colorCabeza1 = selector.showDialog(null, "Elige un color para la cabeza",Color.GRAY);
    }

    private void elegirColorCuerpo1(){
        JColorChooser selector = new JColorChooser();
        colorCuerpo1 = selector.showDialog(null, "Elige un color para el cuerpo",Color.GRAY);

    }

    private void elegirColorCabeza2(){
        JColorChooser selector = new JColorChooser();
        colorCabeza2 = selector.showDialog(null, "Elige un color para la cabeza",Color.GRAY);
    }

    private void elegirColorCuerpo2(){
        JColorChooser selector = new JColorChooser();
        colorCuerpo2 = selector.showDialog(null, "Elige un color para el cuerpo",Color.GRAY);

    }

    private void volverGameMode(){
        gui.cd.show(gui.principal,"GameMode");
    }

    private void single(){
        prepareElementosMenuSingle();
        prepareAccionesMenuSingle();
        this.gui.cd.show(gui.principal,"SinglePlayer");

    }

    private void multi(){
        prepareElementosMenuMulti();
        prepareAccionesMenuMulti();
        this.gui.cd.show(gui.principal,"MultiPlayer");
    }

    private void iA(){
        prepareElementosMenuIA();
        prepareAccionesMenuIA();
        this.gui.cd.show(gui.principal,"IA");
    }

    private void volverPpal(){
        gui.cd.show(gui.principal, "MenuPpal");
    }
}
