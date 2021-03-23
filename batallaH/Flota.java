import java.util.ArrayList;

public class Flota {
    private Tablero tablero;
    private String nombre;
    private ArrayList<Marino> marinos;
    private ArrayList<Maquina> maquinas;
    private ArrayList<Maquina> destruidas;
    
    public Flota(String nombre, Tablero tablero)
    {
        this.nombre = nombre;
        this.marinos = new ArrayList<Marino>();
        this.maquinas = new ArrayList<Maquina>();
        this.destruidas = new ArrayList<Maquina>();
        this.tablero = new Tablero();
    }
    
    /**
     * Retorna las maquinas de la flota
     */
    public ArrayList<Maquina> getMaquinas(){
        return this.maquinas;
    }
    
    /**
     * Se añade un barco a la flota
     *
     * @param Coordenada longitud 
     * @param Coordenada latitud
     */
    public void addBarco(int longitud, int latitud){
        maquinas.add(new Barco(longitud, latitud));
        maquinas.add(new Capsula(longitud,latitud,this));
    }
    
    /**
     * Se añade un avion a la flota
     *
     * @param Coordenada longitud 
     * @param Coordenada latitud
     */
    public void addAvion(int longitud, int latitud){
        maquinas.add(new Avion(longitud, latitud));
    }
    
    /**
     * Se añade un portaAvion a la flota
     *
     * @param Coordenada longitud 
     * @param Coordenada latitud
     */
    public void addPortaAvion(int longitud, int latitud){
        maquinas.add(new PortaAviones(longitud, latitud));
    }
    
    /**
    * Mueve la flota una posición al Norte
    *
    */
    public void alNorte(){
        for (Maquina maquina: maquinas){
            maquina.alNorte();
        }
    }
    
    /**
    * Calcula las maquinas debiles de la flota
    *
    * @return Retorna las maquinas debiles de la flota
    */
    public ArrayList<Maquina> maquinasDebiles(){
        ArrayList<Maquina> debiles = new ArrayList<Maquina>();
        for (Maquina maquina: maquinas){
            if (maquina.isDebil()){
                debiles.add(maquina);
            }
        }
        return debiles;
    }

    /**
     * Calcula la potencia de la flota
     *
     * @return La potencia de la flota
     * @throws BatallaNavalException - Si hay menos marinos que maquinas
     */
    public int potencia() throws BatallaNavalException{
        ArrayList<Maquina> debiles = maquinasDebiles();
        int potencia = this.maquinas.size() - debiles.size();
        int nMaquinas = 0;
        int nMarinos = 0;
        for (Maquina maquina: maquinas){
            nMaquinas++;
            if (maquina instanceof Barco){
                nMarinos += ((Barco)maquina).getMarinos().size();
            }else if (maquina instanceof Avion){
                if(((Avion)maquina).getPiloto() == null){
                    nMaquinas ++;
                }
                if(((Avion)maquina).getCopiloto() == null){
                    nMaquinas ++;
                }
            }
        }
        
        if (nMarinos < nMaquinas){
            throw new BatallaNavalException(BatallaNavalException.POTENCIAFLOTA);
        }
        return potencia;
    }
}
