package Estructuras.ArbolB;

import static Main.Main.horarios;
import Objetos.Horario;
import java.util.ArrayList;

public class Nodo {

    int mK;
    int mB;
    Ordenable[] mLlaves;
    Object[] mDatos;
    Nodo[] mPunteros;

    private static int numeroDeNodo = 1;

    public String getDotName() {
        return "Nodo" + this.hashCode();
    }

   public String toDot(  )  {
        
        StringBuilder b = new StringBuilder();
        
        b.append( getDotName() );
        b.append("[label=\"<P0>");
        for( int i = 0; i < mB; i++ ) {
            b.append( "|"+"<"+  mLlaves[i].getKey().toString()+"> " +  mLlaves[i].getKey().toString() );
            b.append( "|<P" + (i+1) + ">" );                
        }
        
        b.append("\"];\n");
        
        for( int i = 0; i <= mB ; i++ ) {
            if( mPunteros[i] != null )   {
                b.append( mPunteros[i].toDot() );
                b.append( getDotName() + ":P" + i + " -> " + mPunteros[i].getDotName() + ";\n" );
            }
        }
        
        return b.toString();
        
        
    }

    public ArrayList<Horario> toArray() {
        ArrayList<Horario> horariosEncontrados = new ArrayList<>();
        for (int i = 0; i < mB; i++) {
            Horario horario = (Horario) horarios.search(new LlaveEntero(Integer.valueOf(mLlaves[i].getKey().toString())));
            horario.setNodoPadre(horarios.searchNodo(new LlaveEntero(Integer.valueOf(mLlaves[i].getKey().toString()))).getDotName());
            horariosEncontrados.add(horario);
        }
        for (int i = 0; i <= mB; i++) {
            if (mPunteros[i] != null) {
                horariosEncontrados.addAll(mPunteros[i].toArray());
            }
        }

        return horariosEncontrados;
    }


    public Nodo(int pK) {
        this.mK = pK;
        mB = 0;
        mLlaves = new Ordenable[2 * pK + 1];
        mDatos = new Object[2 * pK + 1];
        mPunteros = new Nodo[(2 * pK) + 2];
    }

    public Nodo(int pK, Ordenable pLlave, Object pDato) {
        this(pK);
        mB = 1;
        mLlaves[0] = pLlave;
        mDatos[0] = pDato;
    }

    public void setK(int mK) {
        this.mK = mK;
    }

    public int getK() {
        return mK;
    }

    public int getmK() {
        return mK;
    }

    public void setmK(int mK) {
        this.mK = mK;
    }

    public int getmB() {
        return mB;
    }

    public void setmB(int mB) {
        this.mB = mB;
    }

    public Ordenable[] getmLlaves() {
        return mLlaves;
    }

    public void setmLlaves(Ordenable[] mLlaves) {
        this.mLlaves = mLlaves;
    }

    public Object[] getmDatos() {
        return mDatos;
    }

    public void setmDatos(Object[] mDatos) {
        this.mDatos = mDatos;
    }

    public Nodo[] getmPunteros() {
        return mPunteros;
    }

    public void setmPunteros(Nodo[] mPunteros) {
        this.mPunteros = mPunteros;
    }

    public static int getNumeroDeNodo() {
        return numeroDeNodo;
    }

    public static void setNumeroDeNodo(int numeroDeNodo) {
        Nodo.numeroDeNodo = numeroDeNodo;
    }
    
    
}
