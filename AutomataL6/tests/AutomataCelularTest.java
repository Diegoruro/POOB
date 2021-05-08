package tests;

import domain.AutomataCelular;
import domain.AutomataExcepcion;
import org.junit.Assert;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class AutomataCelularTest {
    AutomataCelular automata;
    File archivoDat = new File("./tests/test.dat");
    File archivoTxt = new File("./tests/test.txt");
    int cont;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        automata = new AutomataCelular();
        cont = 0;
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

    }

    @org.junit.jupiter.api.Test
    void abrirTest() {

        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivoDat.getAbsolutePath()));

            automata = (AutomataCelular) in.readObject();

            for (int i = 0; i < automata.getLongitud(); i++) {
                for (int j = 0; j < automata.getLongitud(); j++) {
                    if (automata.getAutomata()[i][j]!=null){
                        cont++;
                    }
                }
            }

            assertEquals(15, cont);
        } catch (FileNotFoundException e) {
            fail();
        } catch (IOException e) {
            fail();
        } catch (ClassNotFoundException e) {
            fail();
        }
    }

    @org.junit.jupiter.api.Test
    void guardarTest() {
        automata.ticTac();
        automata.ticTac();
        try {
            automata.guardar(archivoDat);

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivoDat.getAbsolutePath()));

            automata = (AutomataCelular) in.readObject();

            for (int i = 0; i < automata.getLongitud(); i++) {
                for (int j = 0; j < automata.getLongitud(); j++) {
                    if (automata.getAutomata()[i][j]!=null){
                        cont++;
                    }
                }
            }

            assertEquals(15, cont);
        }catch (AutomataExcepcion | FileNotFoundException e){
            fail();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void exportar() {
        try {
            automata.ticTac();
            automata.ticTac();

            automata.exportar(archivoTxt);
            automata.importar(archivoTxt);

            for (int i = 0; i < automata.getLongitud(); i++) {
                for (int j = 0; j < automata.getLongitud(); j++) {
                    if (automata.getAutomata()[i][j]!=null){
                        cont++;
                    }
                }
            }

            assertEquals(15, cont);
        } catch (AutomataExcepcion automataExcepcion) {
            automataExcepcion.printStackTrace();
        }

    }

    @org.junit.jupiter.api.Test
    void importar() {
        try {
            automata.importar(archivoTxt);

            for (int i = 0; i < automata.getLongitud(); i++) {
                for (int j = 0; j < automata.getLongitud(); j++) {
                    if (automata.getAutomata()[i][j] != null) {
                        cont++;
                    }
                }
            }

            assertEquals(15, cont);
        } catch (AutomataExcepcion automataExcepcion) {
            automataExcepcion.printStackTrace();
        }
    }

}