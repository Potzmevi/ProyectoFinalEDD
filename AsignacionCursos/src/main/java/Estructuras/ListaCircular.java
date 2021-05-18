/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Asignacion;
import Objetos.Curso;
import Objetos.Edificio;
import Objetos.Estudiante;
import Objetos.Horario;
import Objetos.Usuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author meza4
 */
public class ListaCircular<T> {

    private Nodo<T> root;
    private Nodo<T> end;
    private int size;

    public ListaCircular() {
        root = null;
        end = null;
    }

    public Nodo<T> getRoot() {
        return root;
    }

    public void add(T data) {
        Nodo nuevoNodo = new Nodo<T>(data);

        if (root == null) {
            root = nuevoNodo;
            end = nuevoNodo;
            root.setNext(root);
            root.setPrev(end);
        } else if (doesntExist(data)) {
            end.setNext(nuevoNodo);
            nuevoNodo.setNext(root);
            nuevoNodo.setPrev(end);
            end = nuevoNodo;
            root.setPrev(end);
        } else {
            String id = getId(data);
            JOptionPane.showMessageDialog(null, "El dato: \"" + id + "\" ya existe.");
        }
    }

    private boolean doesntExist(T data) {
        String aux_id;
        String id = getId(data);

        Nodo<T> aux = root;

        do {
            aux_id = getId(aux.getData());
            if (aux_id.equals(id)) {
                return false;
            }
            aux = aux.getNext();

        } while (aux != root);

        return true;
    }

    private String getId(T data) {
        if (data instanceof Usuario) {
            Usuario user = (Usuario) data;
            return String.valueOf(user.getId());
        }
        else if (data instanceof Edificio) {
            Edificio edif = (Edificio) data;
            return edif.getNombre();
        }
         else if (data instanceof Curso) {
            Curso curso = (Curso) data;
            return String.valueOf(curso.getCodigo());
        } else if (data instanceof Asignacion) {
            Asignacion asignacion = (Asignacion) data;
            return String.valueOf(asignacion.getCodigo());
        }
        return null;
    }
    
 public int obtenerAsignacionesSalon(int salon, String edificio) {
        int contador = 0;
        if (root != null) {
            Nodo<T> aux = root;

            do {
                if (aux.getData() instanceof Asignacion) {
                    Asignacion asignacion = (Asignacion) aux.getData();
                    Horario horario = asignacion.getHorario();
                    if ((horario.getEdificio().getNombre().equals(edificio)) && (horario.getSalon().getId()== salon)) {
                        contador++;
                    }

                }
                aux = aux.getNext();
            } while (aux != root);
        }
        return contador;
    }

 public ArrayList obtenerAsignacionesEstudiante(int carnet) {
        ArrayList asignaciones = new ArrayList();
     if (root != null) {
            Nodo<T> aux = root;

            do {
                if (aux.getData() instanceof Asignacion) {
                    Asignacion asignacion = (Asignacion) aux.getData();
                    Estudiante estu = asignacion.getEstudiante();
                    if (estu.getCarnet()==carnet ) {
                        asignaciones.add(asignacion);
                    }

                }
                aux = aux.getNext();
            } while (aux != root);
        }
        return asignaciones;
    }
    
    
    private Nodo<T> getNodo(String id) {
        if (root != null) {
            Nodo<T> aux = root;
            do {
                String id_aux = getId(aux.getData());
                if (id.equals(id_aux)) {
                    return aux;
                } else {
                    aux = aux.getNext();
                }
            } while (aux != root);
        }
        return null;
    }

    public boolean delete(String id) {
        if (root != null) {
            Nodo nodo = getNodo(id);
            if (nodo != null) {
                String id_root = getId(root.getData());
                String id_end = getId(end.getData());
                if (root == end) {
                    root = end = null;
                    return true;
                } else if (id.equals(id_root)) {
                    root = root.getNext();
                } else if (id.equals(id_end)) {
                    end = end.getPrev();
                }
                Nodo<T> nodoAnterior = nodo.getPrev();
                Nodo<T> nodoSiguiente = nodo.getNext();
                nodoAnterior.setNext(nodoSiguiente);
                nodoSiguiente.setPrev(nodoAnterior);
                return true;
            }
        }
        return false;
    }

    public Nodo<T> buscarNombre(String nombre) {
        if (root != null) {
            Nodo<T> aux = root;
            do {
                if (aux.getData() instanceof Usuario) {
                    Usuario user = (Usuario) aux.getData();
                    if (user.getNombre().equals(nombre)) {
                        return aux;
                    }
                } else if (aux.getData() instanceof Edificio) {
                    Edificio edif= (Edificio) aux.getData();
                    if (edif.getNombre().equals(nombre)) {
                        return aux;
                    }
                }
                aux = aux.getNext();
            } while (aux != root);
        }
        return null;
    }

    public Nodo<T> buscarId(int id) {
        if (root != null) {
            Nodo<T> aux = root;
            do {
                if (aux.getData() instanceof Usuario) {
                    Usuario user = (Usuario) aux.getData();
                    if (user.getId() == id) {
                        return aux;
                    }
                }else  if (aux.getData() instanceof Curso) {
                    Curso curso = (Curso) aux.getData();
                    if (curso.getCodigo()== id) {
                        return aux;
                    }
                }
                aux = aux.getNext();
            } while (aux != root);
        }
        return null;
    }

    public Nodo<T> modificarData(String id, T data) {
        if (root != null) {
            Nodo<T> aux = root;
            do {
                if (aux.getData() instanceof Usuario) {
                    Usuario user = (Usuario) aux.getData();
                    if (user.getId() ==Integer.valueOf( id)) {
                        aux.setData(data);
                        return aux;
                    }
                }
                else if (aux.getData() instanceof Curso) {
                     Curso curso = (Curso) aux.getData();
                    if (curso.getCodigo()== Integer.valueOf( id)) {
                        aux.setData(data);
                        return aux;
                    }
                    
                }else if (aux.getData() instanceof Edificio) {
                     Edificio curso = (Edificio) aux.getData();
                    if (curso.getNombre().equalsIgnoreCase(String.valueOf(id))) {
                        aux.setData(data);
                        return aux;
                    }
                    
                }
                aux = aux.getNext();
            } while (aux != root);
        }
        return null;
    }

    public void mostrarDatos() {
        if (root != null) {
            Nodo<T> aux = root;
            System.out.println("///////////////////////////////////////");
            do {
                if (aux.getData() instanceof Usuario) {
                    Usuario user = (Usuario) aux.getData();
                    System.out.println("************ USUARIO ************");
                    System.out.println("ID: " + user.getId());
                    System.out.println("Nombre: " + user.getNombre());
                    System.out.println("Password: " + user.getPassword());
                    System.out.println("Tipo: " + user.getTipo());
                    if (aux.getNext() != null) {
                        System.out.println("Siguiente: " + getId(aux.getNext().getData()));
                    }
                    if (aux.getPrev() != null) {
                        System.out.println("Anterior: " + getId(aux.getPrev().getData()));
                    }

                }
                aux = aux.getNext();
            } while (aux != root);
        }
    }

    public class Nodo<T> {

        private T data;
        private Nodo<T> next;
        private Nodo<T> prev;

        public Nodo(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public Nodo<T> getNext() {
            return next;
        }

        public Nodo<T> getPrev() {
            return prev;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Nodo<T> next) {
            this.next = next;
        }

        public void setPrev(Nodo<T> prev) {
            this.prev = prev;
        }
    }
}
