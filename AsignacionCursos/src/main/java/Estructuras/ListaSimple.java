/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Salon;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author meza4
 */
public class ListaSimple<T> {

    private Nodo<T> root;
    private Nodo<T> end;

    public ListaSimple() {
        this.root = null;
        this.end = null;
    }

    public boolean add(T data) {
        Nodo<T> nuevoNodo = new Nodo<T>(data);
        if (root == null) {
            root = nuevoNodo;
            end = nuevoNodo;
        } else if (doesntExists(data)) {
            end.setNext(nuevoNodo);
            end = nuevoNodo;
        } else {
            JOptionPane.showMessageDialog(null, "El id: \"" + getId(data) + "\" ya existe.");
            return false;
        }
        sort();
        return true;
    }
    public boolean doesntExists(T data) {
        Nodo<T> aux = root;
        String aux_id;
        String data_id = getId(data);
        while (aux != null) {
            aux_id = getId(aux.getData());
            if (aux_id.equals(data_id)) {
                return false;
            } else {
                aux = aux.getNext();
            }
        }
        return true;
    }

    private String getId(T data) {
        if (data instanceof Salon) {
            Salon salon = (Salon) data;
            return String.valueOf(salon.getId());
        }
        return null;
    }

    private Nodo<T> getNodo(String id) {
        if (root != null) {
            Nodo<T> aux = root;
            while (aux != null) {
                String id_aux = getId(aux.getData());
                if (id.equals(id_aux)) {
                    return aux;
                } else {
                    aux = aux.getNext();
                }
            }
        }
        return null;
    }

    public T getData(String id) {
        if (root != null) {
            Nodo<T> aux = root;
            while (aux != null) {
                String id_aux = getId(aux.getData());
                if (id.equals(id_aux)) {
                    return aux.getData();
                } else {
                    aux = aux.getNext();
                }
               
            }
        }
        
        return null;
    }

    public Nodo<T>  getNode(String id) {
        if (root != null) {
            Nodo<T> aux = root;
            while (aux != null) {
                String id_aux = getId(aux.getData());
                if (id.equals(id_aux)) {
                    return aux;
                } else {
                    aux = aux.getNext();
                }
               
            }
        }
        
        return null;
    }
    
    private Nodo<T> getPrevious(Nodo<T> nodo) {
        Nodo<T> aux = root;

        while (aux != null) {
            if (aux.getNext() == nodo) {
                return aux;
            } else {
                aux = aux.getNext();
            }
        }
        return null;
    }

    public boolean delete(String id) {
        if (root != null) {
            Nodo<T> nodo = getNodo(id);
            if (nodo != null) {
                String id_root = getId(root.getData());
                String id_end = getId(end.getData());
                if (root == end) {
                    root = end = null;
                    return true;
                } else if (id.equals(id_root)) {
                    root = root.getNext();
                    return true;
                } else if (id.equals(id_end)) {
                    end = getPrevious(end);
                    end.setNext(null);
                    return true;
                }
                Nodo<T> nodoAnterior = getPrevious(nodo);
                Nodo<T> nodoSiguiente = nodo.getNext();
                nodoAnterior.setNext(nodoSiguiente);
                return true;
            }
        }
        return false;
    }

    public boolean update(T data) {
        String id = getId(data);
        Nodo<T> nodo = getNodo(id);
        if (nodo != null) {
            nodo.setData(data);
            return true;
        } else {
            return false;
        }
    }

    public Nodo<T> search(String id) {
        if (root != null) {
            Nodo<T> aux = root;
            while (aux != root) {
                if (aux.getData() instanceof Salon) {
                    Salon salon = (Salon) aux.getData();
                    if (id.equals(salon.getId())) {
                        return aux;
                    }
                }
                aux = aux.getNext();
            }
        }
        return null;
    }

    public T get(int indice) {
        int indice_aux = 0;
        if (root != null) {
            Nodo<T> aux = root;
            while (aux != null) {
                if (indice_aux == indice) {
                    return aux.getData();
                } else {
                    aux = aux.getNext();
                    indice_aux++;
                }
            }
        }
        return null;
    }

    public int getSize() {
        int size = 0;
        if (root != null) {
            Nodo<T> aux = root;
            while (aux != null) {
                size++;
                aux = aux.getNext();
            }
        }
        return size;
    }

    public void showData() {
        System.out.println("\n///////////////////////////////////////");
        if (root != null) {
            Nodo<T> aux = root;
            while (aux != null) {
                if (aux.getData() instanceof Salon) {
                    System.out.println("************ SALON ************");
                    Salon salon = (Salon) aux.getData();
                    System.out.println("Numero: " + salon.getId());
                    System.out.println("Capacidad: " + salon.getEstudiantes());
                    if (aux.getNext() != null) {
                        System.out.println(" Siguiente: " + getId(aux.getNext().getData()));
                    }
                }

                aux = aux.getNext();
            }
        } else {
            System.out.println("************ LISTA VACIA ************");
        }
    }
    
    public void sort() {
        int size = getSize();
        if (size > 1)
        {
            for (int i = 0; i < getSize(); i++)
            {
                Nodo<T> actual = root;
                Nodo<T> siguiente = actual.getNext();
                for (int j = 1; j < getSize(); j++)
                {
                    int id_actual =Integer.valueOf( getId(actual.getData()));
                    int id_siguiente = Integer.valueOf( getId(siguiente.getData()));
                    if ( id_actual < id_siguiente )
                    {
                        T data_actual = actual.getData();
                        actual.setData(siguiente.getData());
                        siguiente.setData(data_actual);
                        actual = actual.getNext();
                        siguiente = siguiente.getNext();
                    } else
                    {
                        actual = actual.getNext();
                        siguiente = siguiente.getNext();
                    }
                }
            }
        }
    }

    public Nodo<T> getRoot() {
        return root;
    }

    public void setRoot(Nodo<T> root) {
        this.root = root;
    }

    public String graficarSalones(String edificio) throws IOException {
        String salida = "";
        if (root != null) {
            Nodo<T> aux = root;
            while (aux != null) {
                if (aux.getData() instanceof Salon) {

                   try {
                        Salon salon = (Salon) aux.getData();
                        Salon salonSiguiente = (Salon) aux.getNext().getData();
                        salida += edificio+"_"+salon.getId()+ "->" + edificio+"_"+salonSiguiente.getId() + " ; \n";
                    } catch (Exception e) {
                        Salon salon = (Salon) aux.getData();
                        salida += edificio+"_"+salon.getId() + " [constraint=false]; \n";
                    }

                }
                aux = aux.getNext();
            }
        }
        return salida;
    }
    
    public class Nodo<T> {

        private T data;
        private Nodo<T> next;

        public Nodo(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public Nodo<T> getNext() {
            return next;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Nodo<T> next) {
            this.next = next;
        }

    }
}
