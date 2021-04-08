package presentacion; 
 

import aplicacion.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * @version ECI 2016
 */
public class SinapGUI extends JFrame{

    private static final int ANCHO_PREFERIDO = 450;
    private static final int ALTO_PREFERIDO= 450;
    private static final Dimension DIMENSION_PREFERIDA =
                         new Dimension(ANCHO_PREFERIDO,ALTO_PREFERIDO);

    private Sinap areas;

    /*Panel botonListar*/
    private JButton botonListar;
    private JButton botonReiniciarListar;
    private JTextArea textoDetalles;
    
    /*Panel botonAdicionar*/
    private JTextField textoOriginal;   
    private JTextField textoEspa_ol;
    private JTextField textoPais;
    private JTextField textoDirector;
    private JTextArea textoDescripcion;
    
    private JButton botonAdicionar;
    private JButton botonReiniciarAdicionar;
    
    /*Panel buscar*/
    private JTextField busquedaTexto;
    private JTextArea resultadosTexto;
    
    /**
     * Create un marco para el catalogo de areas de arte
     */
    
    
    private SinapGUI(){
        areas=new Sinap();
        areas.adicioneCinco();
        prepareElementos();
        prepareAcciones();
    }


    private void prepareElementos(){
        setTitle("Sinap. Areas.");
        textoOriginal = new JTextField(50);
        textoEspa_ol = new JTextField(50);
        textoPais = new JTextField(50);
        textoDirector = new JTextField(4);
        textoDescripcion = new JTextArea(10, 50);
        textoDescripcion.setLineWrap(true);
        textoDescripcion.setWrapStyleWord(true);
        JTabbedPane etiquetas = new JTabbedPane();
        etiquetas.add("Listar",   prepareAreaListar());
        etiquetas.add("Adicionar",  prepareAreaAdicionar());
        etiquetas.add("Buscar", prepareAreaBuscar());
        getContentPane().add(etiquetas);
        setSize(DIMENSION_PREFERIDA);
        
    }


    /**
     * Prepara el panel para listar areas
     * @return el panel para listar areas
     */
    private JPanel prepareAreaListar(){

        textoDetalles = new JTextArea(10, 50);
        textoDetalles.setEditable(false);
        textoDetalles.setLineWrap(true);
        textoDetalles.setWrapStyleWord(true);
        JScrollPane scrollArea =
                new JScrollPane(textoDetalles,
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                                
        JPanel  botones = new JPanel();
        botonListar = new JButton("Listar");
        botonReiniciarListar = new JButton("Limpiar");
        botones.add(botonListar);
        botones.add(botonReiniciarListar);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollArea, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);

        return panel;
     }
     
    /**
     * Prepara el area de adición
     * @return El area de adición
     */
    private JPanel prepareAreaAdicionar(){
        
        Box textoOriginalArea = Box.createHorizontalBox();
        textoOriginalArea.add(new JLabel("Nombre", JLabel.LEFT));
        textoOriginalArea.add(Box.createGlue());
        Box OriginalArea = Box.createVerticalBox();
        OriginalArea.add(textoOriginalArea);
        OriginalArea.add(textoOriginal);

        Box textoEspa_olArea = Box.createHorizontalBox();
        textoEspa_olArea.add(new JLabel("Nombre internacional", JLabel.LEFT));
        textoEspa_olArea.add(Box.createGlue());
        Box Espa_olArea = Box.createVerticalBox();
        Espa_olArea.add(textoEspa_olArea);
        Espa_olArea.add(textoEspa_ol);
        
        Box textoPaisArea = Box.createHorizontalBox();
        textoPaisArea.add(new JLabel("Ubicación", JLabel.LEFT));
        textoPaisArea.add(Box.createGlue());
        Box PaisArea = Box.createVerticalBox();
        PaisArea.add(textoPaisArea);
        PaisArea.add(textoPais);

        Box textoDirectorArea = Box.createHorizontalBox();
        textoDirectorArea.add(new JLabel("Area", JLabel.LEFT));
        textoDirectorArea.add(Box.createGlue());
        Box a_oArea = Box.createVerticalBox();
        a_oArea.add(textoDirectorArea);
        a_oArea.add(textoDirector);
        
        Box textoDescripcionArea = Box.createHorizontalBox();
        textoDescripcionArea.add(new JLabel("Descripción", JLabel.LEFT));
        textoDescripcionArea.add(Box.createGlue());
        Box descripcionArea = Box.createVerticalBox();
        descripcionArea.add(textoDescripcionArea);
        descripcionArea.add(textoDescripcion);
 
        Box singleLineFields = Box.createVerticalBox();
        singleLineFields.add(OriginalArea);
        singleLineFields.add(Espa_olArea);
        singleLineFields.add(PaisArea);
        singleLineFields.add(a_oArea);        

        JPanel textoDetallesPanel = new JPanel();
        textoDetallesPanel.setLayout(new BorderLayout());
        textoDetallesPanel.add(singleLineFields, BorderLayout.NORTH);
        textoDetallesPanel.add(descripcionArea, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botonAdicionar = new JButton("Adicionar");
        botonReiniciarAdicionar = new JButton("Limpiar");

        botones.add(botonAdicionar);
        botones.add(botonReiniciarAdicionar);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textoDetallesPanel, BorderLayout.CENTER);
        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }

    



   /**
     * Prepara el area de liatar
     * @return El panel para buscar areass
     */
    private JPanel prepareAreaBuscar(){

        Box busquedaEtiquetaArea = Box.createHorizontalBox();
        busquedaEtiquetaArea.add(new JLabel("Buscar", JLabel.LEFT));
        busquedaEtiquetaArea.add(Box.createGlue());
        busquedaTexto = new JTextField(50);
        Box busquedaArea = Box.createHorizontalBox();
        busquedaArea.add(busquedaEtiquetaArea);
        busquedaArea.add(busquedaTexto);
        
        resultadosTexto = new JTextArea(10,50);
        resultadosTexto.setEditable(false);
        resultadosTexto.setLineWrap(true);
        resultadosTexto.setWrapStyleWord(true);
        JScrollPane scrollArea = new JScrollPane(resultadosTexto,
                                     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                     JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel botonListarArea = new JPanel();
        botonListarArea.setLayout(new BorderLayout());
        botonListarArea.add(busquedaArea, BorderLayout.NORTH);
        botonListarArea.add(scrollArea, BorderLayout.CENTER);

        return botonListarArea;
    }


    private void prepareAcciones(){
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                setVisible(false);
                System.exit(0);
            }
        });
        
        /*Listar*/
        botonListar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                accionListar();
            }
        });

        botonReiniciarListar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                textoDetalles.setText("");
            }
        });
        
        /*Adicionar*/
        botonAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                accionAdicionar();                    
            }
        });
        
        botonReiniciarAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                textoOriginal.setText("");
                textoEspa_ol.setText("");
                textoPais.setText("");
                textoDirector.setText("");
                textoDescripcion.setText("");
            }
        });
        
        /*Buscarr*/
        busquedaTexto.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent ev){
                accionBuscar();

            }
           
            public void insertUpdate(DocumentEvent ev){
                accionBuscar();
            }
            
            public void removeUpdate(DocumentEvent ev){
                accionBuscar();
            }
            

        });
    }    

    
    private void accionListar(){
        textoDetalles.setText(areas.toString());
    }
    
    private void  accionAdicionar(){
        try {
            areas.adicione(textoOriginal.getText(),textoEspa_ol.getText(),textoPais.getText(), textoDirector.getText(),
                    textoDescripcion.getText());
        }catch (SINAPExcepcion e){
            JOptionPane.showMessageDialog(null, SINAPExcepcion.SIN_NOMBRE_INT);
        }

    }

    private void accionBuscar(){
        String patronBusqueda=busquedaTexto.getText();
        StringBuffer buffer = new StringBuffer();
        if(patronBusqueda.length() > 0) {
            ArrayList <Area> results = areas.busque(patronBusqueda);
            for(int i = 0; i < results.size(); i++) {
                buffer.append(results.get(i).toString());
                buffer.append('\n');
                buffer.append('\n');
             }
        }
        resultadosTexto.setText(buffer.toString());
    } 
    
   public static void main(String args[]){
       SinapGUI gui=new SinapGUI();
       gui.setVisible(true);
   }    
}
