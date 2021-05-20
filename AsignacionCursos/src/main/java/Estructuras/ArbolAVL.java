/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Main.Main;
import Objetos.Catedratico;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author meza4
 */
public class ArbolAVL {

    private StringBuffer grafica;
    private NodoAVL raiz;
    public int size;
    private String graficaArbolCapas = "";

    public ArbolAVL() {
        this.raiz = null;
        size = 0;
    }

    private NodoAVL buscar(int id, NodoAVL raiz) {
        if (raiz == null) {
            return null;
        }
        if (id==raiz.getClave()) {
            return raiz;
        }
        if (id>raiz.getClave()) {
            return buscar(id, raiz.getDer());
        }
        if (id<raiz.getClave()) {
            return buscar(id, raiz.getIzq());
        }
        return null;
    }

    public NodoAVL buscar(int id) {
        return buscar(id, this.raiz);
    }

    public int obtenerAltura(NodoAVL x) {
        if (x == null) {
            return -1;
        }
        return x.getFactorEquilibrio();
    }

    public void modificarDato(int id, Catedratico ca){
        NodoAVL nodo = buscar(id);
        if(nodo!=null){
            nodo.setInfo(ca);
        }
    }
    
    public NodoAVL rotacionIzquierda(NodoAVL c) {
        NodoAVL aux = c.getIzq();
        c.setIzq(aux.getDer());
        aux.setDer(c);
        c.setFactorEquilibrio(Math.max(obtenerAltura(c.getIzq()), obtenerAltura(c.getDer())) + 1);
        aux.setFactorEquilibrio(Math.max(obtenerAltura(aux.getIzq()), obtenerAltura(aux.getDer())) + 1);
        return aux;
    }

    public NodoAVL rotacionDerecha(NodoAVL c) {
        NodoAVL aux = c.getDer();
        c.setDer(aux.getIzq());
        aux.setIzq(c);
        c.setFactorEquilibrio(Math.max(obtenerAltura(c.getIzq()), obtenerAltura(c.getDer())) + 1);
        aux.setFactorEquilibrio(Math.max(obtenerAltura(aux.getIzq()), obtenerAltura(aux.getDer())) + 1);
        return aux;
    }

    public NodoAVL rotacionDobleIzquierda(NodoAVL c) {
        NodoAVL aux;
        c.setIzq(rotacionDerecha(c.getIzq()));
        aux = rotacionIzquierda(c);
        return aux;
    }

    public NodoAVL rotacionDobleDerecha(NodoAVL c) {
        NodoAVL aux;
        c.setDer(rotacionIzquierda(c.getDer()));
        aux = rotacionDerecha(c);
        return aux;
    }

    public void eliminar(NodoAVL nuevo) {
        raiz = eliminarAVL(nuevo, raiz);
        size--;
    }

    private NodoAVL eliminarAVL(NodoAVL nuevo, NodoAVL raiz) {
        if (raiz == null) {
            return raiz;
        }
        if (nuevo.getClave()<raiz.getClave() ) {
            raiz.setIzq(eliminarAVL(nuevo, raiz.getIzq()));
        } else if (nuevo.getClave() > raiz.getClave()) {
            raiz.setDer(eliminarAVL(nuevo, raiz.getDer()));
        } else {
            if ((raiz.getDer() == null) || (raiz.getIzq()) == null) {
                NodoAVL aux = null;
                if (aux == raiz.getIzq()) {
                    aux = raiz.getDer();
                } else {
                    aux = raiz.getIzq();
                }

                if (aux == null) {
                    raiz = null;
                } else {
                    raiz = aux;
                }
            } else {
                NodoAVL aux = getNodoConValorMaximo(raiz.getIzq());
                raiz.setClave(aux.getClave());
                raiz.setIzq(eliminarAVL(aux, raiz.getIzq()));
            }
        }

        if (raiz == null) {
            return raiz;
        }

        raiz.setFactorEquilibrio(Math.max(obtenerAltura(raiz.getIzq()), obtenerAltura(raiz.getDer())) + 1);

        int fe = raiz.getFactorEquilibrio();

        if (fe > 1 && raiz.getIzq().getFactorEquilibrio() >= 0) {
            return rotacionDerecha(raiz);
        }
        if (fe < -1 && raiz.getDer().getFactorEquilibrio() <= 0) {
            return rotacionIzquierda(raiz);
        }
        if (fe > 1 && raiz.getIzq().getFactorEquilibrio() < 0) {
            raiz.setIzq(rotacionIzquierda(raiz.getIzq()));
            return rotacionDerecha(raiz);
        }
        if (fe < -1 && raiz.getDer().getFactorEquilibrio() > 0) {
            raiz.setDer(rotacionDerecha(raiz.getDer()));
            return rotacionIzquierda(raiz);
        }
        return raiz;

    }

    private NodoAVL insertarAVL(NodoAVL nuevo, NodoAVL subAr) {
        NodoAVL nuevoPadre = subAr;
        if (nuevo.getClave() < subAr.getClave()) {
            if (subAr.getIzq() == null) {
                subAr.setIzq(nuevo);
            } else {
                subAr.setIzq(insertarAVL(nuevo, subAr.getIzq()));
                if (obtenerAltura(subAr.getIzq()) - obtenerAltura(subAr.getDer()) == 2) {
                    if (nuevo.getClave() < subAr.getIzq().getClave() ){

                        nuevoPadre = rotacionIzquierda(subAr);
                    } else {
                        nuevoPadre = rotacionDerecha(subAr);
                    }
                }
            }
        } else if (nuevo.getClave() > subAr.getClave()) {
            if (subAr.getDer() == null) {
                subAr.setDer(nuevo);
            } else {
                subAr.setDer(insertarAVL(nuevo, subAr.getDer()));
                if (obtenerAltura(subAr.getDer()) - obtenerAltura(subAr.getIzq()) == 2) {
                    if (nuevo.getClave() > subAr.getDer().getClave()) {
                        nuevoPadre = rotacionDerecha(subAr);
                    } else {
                        nuevoPadre = rotacionIzquierda(subAr);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nodo duplicado");

        }
        if (subAr.getIzq() == null && subAr.getDer() != null) {
            subAr.setFactorEquilibrio(subAr.getDer().getFactorEquilibrio() + 1);
        } else if (subAr.getDer() == null && subAr.getIzq() != null) {
            subAr.setFactorEquilibrio(subAr.getIzq().getFactorEquilibrio() + 1);
        } else {
            subAr.setFactorEquilibrio(Math.max(obtenerAltura(subAr.getIzq()), obtenerAltura(subAr.getDer())) + 1);
        }
        return nuevoPadre;
    }

    

    public void insertar(int id, Object info) {
        NodoAVL nuevo = new NodoAVL(id, info);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            raiz = insertarAVL(nuevo, raiz);
        }
        size++;
    }

    private void inOrden(NodoAVL nodo) {
        if (null == nodo) {
            return;
        }
        inOrden(nodo.getIzq());
        System.out.println("{ Llave: " + nodo.getClave() + " }\n");
        inOrden(nodo.getDer());
    }

    private void preOrden(NodoAVL nodo) {
        if (null == nodo) {
            return;
        }
        System.out.println("{ Llave: " + nodo.getClave() + " }\n");
        preOrden(nodo.getIzq());
        preOrden(nodo.getDer());
    }

    private void postOrden(NodoAVL nodo) {
        if (null == nodo) {
            return;
        }
        postOrden(nodo.getIzq());
        postOrden(nodo.getDer());
        System.out.println("{ Llave: " + nodo.getClave() + " }\n");
    }

    public void inOrden() {
        inOrden(this.raiz);
    }

    public void preOrden() {
        preOrden(this.raiz);
    }

    public void postOrden() {
        postOrden(this.raiz);
    }

    private NodoAVL getNodoConValorMaximo(NodoAVL nodo) {
        NodoAVL actual = nodo;
        while (nodo.getDer() != null) {
            actual = actual.getDer();
        }
        return actual;
    }

    public class NodoAVL {

        private int clave;
        private NodoAVL izq;
        private NodoAVL der;
        private Object info;
        private int fe;

        public NodoAVL(int clave, Object info) {
            this.clave = clave;
            this.info = info;
            this.izq = null;
            this.der = null;
        }

        public Object getInfo() {
            return info;
        }

        public void setInfo(Object info) {
            this.info = info;
        }

        public int getClave() {
            return clave;
        }

        public void setClave(int clave) {
            this.clave = clave;
        }

        public NodoAVL getIzq() {
            return izq;
        }

        public void setIzq(NodoAVL izq) {
            this.izq = izq;
        }

        public NodoAVL getDer() {
            return der;
        }

        public void setDer(NodoAVL der) {
            this.der = der;
        }

        public int getFactorEquilibrio() {
            return fe;
        }

        public void setFactorEquilibrio(int altura) {
            this.fe = altura;
        }

    }
    
    public void crearGrafica() throws IOException {
        grafica = new StringBuffer();
        grafica.append("digraph G{\n"
                + "subgraph cluster_0{\n"
                + "style=filled;\n"
                + "color=lightgrey;\n"
                + "node[shape=rect,style=filled,color=white];\n");
        obtenerGrafica(raiz);
        grafica.append("label=\"Arbol de Catedraticos\";\n"
                + "}\n"
                + "}\n");
         File imagenSalida = new File("./ArbolCate.dot");
        if (!imagenSalida.exists()) {
            imagenSalida.createNewFile();
        } else {
            imagenSalida.delete();
            imagenSalida.createNewFile();
        }
        Main.guardarImagen(grafica.toString(), imagenSalida.getAbsolutePath());
        String command = "dot -Tpng ArbolCate.dot -o ArbolCate.png";
        Runtime.getRuntime().exec(command);
    }

    public void obtenerGrafica(NodoAVL nodo) {

        if (nodo == null)
        {
            return ;
        }
        if (nodo.getDer()== null && nodo.getIzq() == null)
        {
            Catedratico catedratico = (Catedratico) nodo.getInfo();
            grafica.append("\" " + catedratico.getId() + "\n" + catedratico.getNombre()+ "\" ;\n");

        }
        obtenerGrafica(nodo.getIzq());
        if (nodo.getIzq() != null)
        {
            Catedratico catedratico = (Catedratico) nodo.getInfo();
            Catedratico catedraticoChild = (Catedratico) nodo.getIzq().getInfo();
            grafica.append("\" " + catedratico.getId() + "\n" + catedratico.getNombre() + "\"" + "->" + "\" " + catedraticoChild.getId() + "\n" + catedraticoChild.getNombre()+ "\"" + ";\n");
        }
        if (nodo.getDer() != null)
        {
            Catedratico catedratico = (Catedratico) nodo.getInfo();
            Catedratico catedraticoChild = (Catedratico) nodo.getDer().getInfo();
            grafica.append("\" " + catedratico.getId() + "\n" + catedratico.getNombre()+ "\"" + "->" + "\" " + catedraticoChild.getId() + "\n" + catedraticoChild.getNombre() + "\"" + ";\n");
        }

        obtenerGrafica(nodo.getDer());

    }

    public String crearGraficaHorario() throws IOException {
        grafica = new StringBuffer();
        grafica.append(
                "subgraph cluster_0{\n"
                + "node[shape=rect];\n");
        obtenerGrafica(raiz);
        grafica.append("label=\"Catedraticos\";\n"
                + "}\n");

        return grafica.toString();

    }
    
    public NodoAVL getRaiz() {
        return raiz;
    }
    
}
