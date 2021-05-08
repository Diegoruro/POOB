package domain;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


/**
 * Clase principal del dominio en donde se llama a ejecución el resto de clases de dominio
 */
public class AutomataCelular implements Serializable{
    static private int LONGITUD=30;
    private Elemento[][] automata;
    private Elemento[][] newMatrix;
    public static int poblacion;
    
    public AutomataCelular() {
        automata=new Elemento[LONGITUD][LONGITUD];
        for (int f=0;f<LONGITUD;f++){
            for (int c=0;c<LONGITUD;c++){
                automata[f][c]=null;
            }
        }
        algunosElementos();
        modifyMatrix();
    }
    
    /**Retorna la lista de los vecinos que rodean el elemento en la posicion indiciada
     * @param    fila numero de fila de la posicion del elemento
     *           columna numero de columna de la posicion del elemento
     * @return  Vecinos de la posición
     */
    
    public Elemento[] vecinos(int fila, int columna)
    {
        Elemento[] vecinos = new Elemento[8];
        int cont = 0;
        for (int i=fila-1;i<fila+2;i++){
            for (int j=columna-1;j<columna+2;j++){
                if (i!=fila || j!=columna){
                    vecinos[cont] = this.getElementoMatrix(i+1, j+1);
                    cont++;
                }
            }
        }
        return vecinos;
    }
    
    /**Retorna el automata en el que se encuentra
     * @return Automata
     */
    public Elemento[][] getAutomata(){
        return this.automata;
    }
    
    /**Retorna la longitud del automata 
     * @return Longitud automata
     */
    public int  getLongitud(){
        return LONGITUD;
    }

    /**Retorna el elemento de la posicion indicada
    @param   int fila numero de fila de la posicion del elemento
             int columna numero de columna de la posicion del elemento
    @return 
     */
    public Elemento getElemento(int f,int c){
        return automata[f][c];
    }

    /**
     * Retorna el elemento de la posicion indicada de la copia ampliada de la matriz
     * @param   int fila numero de fila de la posicion del elemento
             int columna numero de columna de la posicion del elemento
     * @return  Elemento de la matriz
     */
    public Elemento getElementoMatrix(int f,int c){
        return this.newMatrix[f][c];
    }
    
    /**
     * Retorna el elemento de la posicion indicada
     *@param   int numero de fila de la posicion del elemento
             int columna numero de columna de la posicion del elemento
             Elemento nueva elemento que se va agregar al automata
     */
    public void setElemento(int f, int c, Elemento nueva){
        automata[f][c]=nueva;
    }

    /**
     * inicializa los elementos especificados en el codigo
     */
    public void algunosElementos(){
        // Indiana y 007
        CelulaNormal indiana = new CelulaNormal(this,1,1);
        CelulaNormal oo7 = new CelulaNormal(this,2,2);

        
        // Agamenon y Venus
        CelulaEspecial agamenon = new CelulaEspecial(this,5,1);
        CelulaEspecial venus = new CelulaEspecial(this,8,1);

       
        // Horton y Jhon
        CelulaConway jhon = new CelulaConway(this,5,1);
        CelulaConway horton = new CelulaConway(this,5,2);

       
        // Bloque
        CelulaConway celulaBloque1 = new CelulaConway(this,28,0);
        CelulaConway celulaBloque2 = new CelulaConway(this,28,1);
        CelulaConway celulaBloque3 = new CelulaConway(this,29,0);
        CelulaConway celulaBloque4 = new CelulaConway(this,29,1);

       
        // parpadeador
        CelulaConway celulaParpadeador1 = new CelulaConway(this,28,13);
        CelulaConway celulaParpadeador2 = new CelulaConway(this,28,14);
        CelulaConway celulaParpadeador3 = new CelulaConway(this,28,15);

        //Calefactor calefactor = new Calefactor(this, 15,15);
    }
    
    /**
     * Pasa un instante de tiempo y muestra el cambio de las celulas
     */
    public void ticTac(){
        for (int i=0; i<LONGITUD;i++){
            for (int j=0; j<LONGITUD; j++){
                Elemento[] vecinos =vecinos(i,j);
                if (automata[i][j] != null){
                    automata[i][j].decida(vecinos);
                }
                else{
                    int vivos=vecinosVivos(vecinos);
                    if(vivos==3){
                        CelulaConway nueva=new CelulaConway(this,i,j);
                        nueva.estado='d';
                    }
                }
                this.modifyMatrix();
            }
        }
        for (int i=0; i<LONGITUD;i++){
            for (int j=0; j<LONGITUD; j++){
                if (automata[i][j] != null){
                    automata[i][j].cambie();
                }
            }
        }
    }
    
    /**
     * Crea una celula en una alguna casilla vacia al rededor de una celula en la posicion indicada
     *@param   fila numero de fila de la posicion de la celula
     *        int columna numero de columna de la posicion de la celula
    */
    public void newCelula(int fila,int columna){
        boolean flag=false;
        for (int i=fila-1;i<fila+2;i++){
            for (int j=columna-1;j<columna+2;j++){
                if (i!=fila || j!=columna){
                    if(this.getElementoMatrix(i+1,j+1)==null){
                        flag=true;
                        new CelulaNormal(this,i,j);
                        break;
                    }
                }
            }
            if (flag){
                break;
            }
        }
    }    
    
    /**
     * copia la matriz del automata y la amplia en una fila y columna por cada lado
    */
    public void modifyMatrix(){
        Elemento[][] matrix = this.automata;
        int n = this.getLongitud()+2;
        this.newMatrix = new Elemento[n][n];
        for (int i=0; i<n;i++){
            for (int j=0; j<n; j++){
                if (i==0 || j==0 || i==n-1 || j==n-1){
                    this.newMatrix[i][j] = null;
                }else{
                    this.newMatrix[i][j] = matrix[i-1][j-1];
                }
            }
        }
        
    }
    
    /**
     * Retorna los vecinos vivos al rededor de una celula
    * @param   vecinos lista de los vecinos al rededor de una celula
    * @return Vecinos vivos alrededor
    */
    public int vecinosVivos(Elemento[] vecinos){
        int vivos = 0;
        for (int i=0;i<vecinos.length;i++){
            if (vecinos[i]!=null && vecinos[i].isVivo()){
                vivos++;
            }
        }
        return vivos;
    }

    /**
     * Método que abre un archivo y lo carga en el gui
     * @param archivo Archivo  a abrir
     * @return AutomataCelular
     * @throws AutomataExcepcion
     */
    public static AutomataCelular abrir(File archivo) throws AutomataExcepcion {
        if (!archivo.getAbsolutePath().endsWith(".dat")){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_TIPO_ABRIR);
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo.getAbsolutePath()));

            AutomataCelular automata = (AutomataCelular) in.readObject();

            for (Elemento[] fila: automata.getAutomata()){
                for (Elemento elemento: fila){
                    if (elemento != null) {
                        System.out.println(elemento.isVivo());
                    }
                }
            }

            in.close();
            return automata;
        }catch (IOException | ClassNotFoundException e){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_ABRIR);
        }
    }

    /**
     * Método que abre un archivo y lo carga en el gui
     * @param archivo Archivo  a abrir
     * @return AutomataCelular
     * @throws AutomataExcepcion
     */
    public static AutomataCelular abrir01(File archivo) throws IOException{
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo.getAbsolutePath()));

            AutomataCelular automata = (AutomataCelular) in.readObject();

            for (Elemento[] fila: automata.getAutomata()){
                for (Elemento elemento: fila){
                    if (elemento != null) {
                        System.out.println(elemento.isVivo());
                    }
                }
            }

            in.close();
            return automata;
        }catch (IOException | ClassNotFoundException e){
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Método que abre un archivo y lo carga en el gui
     * @param archivo Archivo  a abrir
     * @return AutomataCelular
     * @throws AutomataExcepcion
     */
    public static AutomataCelular abrir00(File archivo) throws AutomataExcepcion{
        throw new AutomataExcepcion(AutomataExcepcion.OPCION_EN_CONSTRUCCION);
    }

    /**
     * Método que guarda en un archivo la informacíon del programa
     * @param archivo Archivo a guardar
     * @throws AutomataExcepcion
     */
    public void guardar(File archivo) throws AutomataExcepcion {
        if (!archivo.getAbsolutePath().endsWith(".dat")){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_TIPO_GUARDAR);
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo.getAbsolutePath()));

            out.writeObject(this);

            out.close();
        }catch (IOException e){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_GUARDAR);
        }
    }

    /**
     * Método que guarda en un archivo la informacíon del programa
     * @param archivo Archivo a guardar
     * @throws AutomataExcepcion
     */
    public void guardar01(File archivo) throws IOException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo.getAbsolutePath()));

            out.writeObject(this);

            out.close();
        }catch (IOException e){
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Método que guarda en un archivo la informacíon del programa
     * @param archivo Archivo a guardar
     * @throws AutomataExcepcion
     */
    public void guardar00(File archivo) throws AutomataExcepcion{
        throw new AutomataExcepcion(AutomataExcepcion.OPCION_EN_CONSTRUCCION);
    }


    /**
     * Método que exporta un archivo
     * @param archivo Archivo  a exportar
     * @throws AutomataExcepcion
     */
    public void exportar(File archivo) throws AutomataExcepcion{
        if (!archivo.getAbsolutePath().endsWith(".txt")){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_TIPO_EXPORTAR);
        }
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(archivo.getAbsolutePath()));

            for (int i=0; i<LONGITUD;i++){
                for (int j=0; j<LONGITUD;j++){
                    if (automata[i][j]!=null) {
                        pw.println(automata[i][j].getClass().toString().replace("class ", "") + " " + i + " " + j + " " + automata[i][j].isVivo());
                    }
                }
            }

            pw.close();
        }catch (IOException e){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_EXPORTAR);
        }
    }

    /**
     * Método que exporta un archivo
     * @param archivo Archivo  a exportar
     * @throws AutomataExcepcion
     */
    public void exportar03(File archivo) throws AutomataExcepcion{
        if (!archivo.getAbsolutePath().endsWith(".txt")){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_TIPO_EXPORTAR);
        }
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(archivo.getAbsolutePath()));

            for (int i=0; i<LONGITUD;i++){
                for (int j=0; j<LONGITUD;j++){
                    if (automata[i][j]!=null) {
                        pw.println(automata[i][j].getClass().getSimpleName() + " " + i + " " + j + " " + automata[i][j].isVivo());
                    }
                }
            }

            pw.close();
        }catch (IOException e){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_EXPORTAR);
        }
    }

    /**
     * Método que exporta un archivo
     * @param archivo Archivo  a exportar
     * @throws AutomataExcepcion
     */
    public void exportar02(File archivo) throws AutomataExcepcion{
        if (!archivo.getAbsolutePath().endsWith(".txt")){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_TIPO_EXPORTAR);
        }
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(archivo.getAbsolutePath()));

            for (int i=0; i<LONGITUD;i++){
                for (int j=0; j<LONGITUD;j++){
                    if (automata[i][j]!=null) {
                        pw.println(automata[i][j].getClass().getSimpleName() + " " + i + " " + j + " " + automata[i][j].isVivo());
                    }
                }
            }

            pw.close();
        }catch (IOException e){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_EXPORTAR);
        }
    }

    /**
     * Método que exporta un archivo
     * @param archivo Archivo  a exportar
     * @throws AutomataExcepcion
     */
    public void exportar01(File archivo) throws AutomataExcepcion{
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(archivo.getAbsolutePath()));

            for (int i=0; i<LONGITUD;i++){
                for (int j=0; j<LONGITUD;j++){
                    if (automata[i][j]!=null) {
                        pw.println(automata[i][j].getClass().getSimpleName() + " " + i + " " + j + " " + automata[i][j].isVivo());
                    }
                }
            }

            pw.close();
        }catch (IOException e){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_EXPORTAR);
        }
    }

    /**
     * Método que exporta un archivo
     * @param archivo Archivo  a exportar
     * @throws AutomataExcepcion
     */
    public void exportar00(File archivo) throws AutomataExcepcion{
        throw new AutomataExcepcion(AutomataExcepcion.OPCION_EN_CONSTRUCCION);
    }


    /**
     * Método que importa en un archivo y lo carga en el gui
     * @param archivo Archivo a importar
     * @throws AutomataExcepcion
     */
    public AutomataCelular importar(File archivo) throws AutomataExcepcion{
        int linea = 0;

        if (!archivo.getAbsolutePath().endsWith(".txt")){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_TIPO_IMPORTAR);
        }

        for (int i=0;i<LONGITUD;i++){
            for (int j=0;j<LONGITUD;j++){
                automata[i][j]=null;
            }
        }

        for (int i=0;i<LONGITUD+2;i++){
            for (int j=0;j<LONGITUD+2;j++){
                newMatrix[i][j]=null;
            }
        }

        try{
            BufferedReader br =new BufferedReader(new FileReader(archivo.getAbsolutePath()));

            String line = br.readLine();
            linea = 1;

            while (line != null){
                line = line.trim();

                cargarLinea(line);

                line = br.readLine();
                linea++;
            }

            br.close();
            return this;

        }catch(IOException | AutomataExcepcion e){
            throw new AutomataExcepcion("Linea " + linea + "\n" + e.getMessage());
        }
    }

    /**
     * Método que importa en un archivo y lo carga en el gui
     * @param archivo Archivo a importar
     * @throws AutomataExcepcion
     */
    public AutomataCelular importar03(File archivo) throws AutomataExcepcion{
        int linea = 0;

        if (!archivo.getAbsolutePath().endsWith(".txt")){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_TIPO_IMPORTAR);
        }

        for (int f=0;f<LONGITUD;f++){
            for (int c=0;c<LONGITUD;c++){
                automata[f][c]=null;
            }
        }

        try{
            BufferedReader br =new BufferedReader(new FileReader(archivo.getAbsolutePath()));

            String line = br.readLine();
            linea = 1;

            while (line != null){
                line = line.trim();

                cargarLinea(line);

                line = br.readLine();
                linea++;
            }

            br.close();
            return this;

        }catch(IOException | AutomataExcepcion e){
            throw new AutomataExcepcion("Linea " + linea + "\n" + e.getMessage());
        }
    }

    /**
     * Método que importa en un archivo y lo carga en el gui
     * @param archivo Archivo a importar
     * @throws AutomataExcepcion
     */
    public AutomataCelular importar02(File archivo) throws AutomataExcepcion{
        if (!archivo.getAbsolutePath().endsWith(".txt")){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_TIPO_IMPORTAR);
        }

        for (int f=0;f<LONGITUD;f++){
            for (int c=0;c<LONGITUD;c++){
                automata[f][c]=null;
            }
        }

        try{
            BufferedReader br =new BufferedReader(new FileReader(archivo.getAbsolutePath()));

            String line = br.readLine();

            while (line != null){
                line = line.trim();

                cargarLinea(line);

                line = br.readLine();
            }

            br.close();
            return this;

        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return this;
    }

    /**
     * Método que importa en un archivo y lo carga en el gui
     * @param archivo Archivo a importar
     * @throws AutomataExcepcion
     */
    public AutomataCelular importar01(File archivo) throws AutomataExcepcion{
        for (int f=0;f<LONGITUD;f++){
            for (int c=0;c<LONGITUD;c++){
                automata[f][c]=null;
            }
        }

        try{
            BufferedReader br =new BufferedReader(new FileReader(archivo.getAbsolutePath()));

            String line = br.readLine();

            while (line != null){
                line = line.trim();

                cargarLinea(line);

                line = br.readLine();
            }

            br.close();
            return this;

        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return this;
    }

    /**
     * Método que importa en un archivo y lo carga en el gui
     * @param archivo Archivo a importar
     * @throws AutomataExcepcion
     */
    public static AutomataCelular importar00(File archivo) throws AutomataExcepcion{
        throw new AutomataExcepcion(AutomataExcepcion.OPCION_EN_CONSTRUCCION);
    }

    //Diagrama
    /**
     * Método que carga una linea y la agrega al automata
     * @param line Linea a cargar
     * @throws AutomataExcepcion
     */
    public void cargarLinea(String line) throws AutomataExcepcion{
        char estadoChar;
        int i = -1,j=-1;
        String[] info = line.split(" ");
        if (info.length!=4){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_INFORMACIÓN);
        }

        try {
            System.out.println("Antes: "+info[1]);
            System.out.println("Antes: "+info[2]);
            i = Integer.parseInt(info[1]);
            j = Integer.parseInt(info[2]);
            System.out.println("Despues: "+i);
            System.out.println("Despues: "+j);
        }catch (NumberFormatException e){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_NUMERO);
        }
        boolean estado = Boolean.parseBoolean(info[3]);

        if (estado){
            estadoChar = 'v';
        }else {
            estadoChar = 'm';
        }
        try {
            Class<?> nuevaClase = Class.forName(info[0]);
            Constructor<?> nuevoCons = nuevaClase.getConstructor(AutomataCelular.class, int.class, int.class);
            Object nuevoObjeto = nuevoCons.newInstance(this, i, j);
            automata[i][j] = (Elemento)nuevoObjeto;
            automata[i][j].setEstadoSiguiente(estadoChar);
            automata[i][j].cambie();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_CREAR);
        }
    }

    /**
     * Método que carga una linea y la agrega al automata
     * @param line Linea a cargar
     * @throws AutomataExcepcion
     */
    public void cargarLinea00(String line) throws AutomataExcepcion {
        char estadoChar;
        int i = -1,j=-1;
        String[] info = line.split(" ");
        if (info.length!=4){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_INFORMACIÓN);
        }

        try {
            i = Integer.parseInt(info[1]);
            j = Integer.parseInt(info[2]);
        }catch (NumberFormatException e){
            throw new AutomataExcepcion(AutomataExcepcion.ERROR_NUMERO);
        }
        boolean estado = Boolean.parseBoolean(info[3]);

        if (estado){
            estadoChar = 'v';
        }else {
            estadoChar = 'm';
        }

        switch (info[0]){
            case "CelulaNormal":
                new CelulaNormal(this, i, j);
                automata[i][j].setEstadoSiguiente(estadoChar);
                break;
            case "CelulaConway":
                new CelulaConway(this, i, j);
                automata[i][j].setEstadoSiguiente(estadoChar);
                break;
            case "CelulaEspecial":
                new CelulaEspecial(this, i, j);
                automata[i][j].setEstadoSiguiente(estadoChar);
                break;
            case "CelulaAsustada":
                new CelulaAsustada(this, i, j);
                automata[i][j].setEstadoSiguiente(estadoChar);
                break;
            case "Poblacion":
                new Poblacion(this, i, j);
                automata[i][j].setEstadoSiguiente(estadoChar);
                                break;
            case "Calefactor":
                new Calefactor(this, i, j);
                automata[i][j].setEstadoSiguiente(estadoChar);
                break;
            default:
                throw new AutomataExcepcion(AutomataExcepcion.ERROR_ELEMENTO_INVALIDO);
        }
        automata[i][j].cambie();
    }
}
