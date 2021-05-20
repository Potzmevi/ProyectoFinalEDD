/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Main.Main;
import Objetos.Asignacion;
import Objetos.Curso;
import Objetos.Edificio;
import Objetos.Estudiante;
import Objetos.Horario;
import Objetos.Salon;
import Objetos.Usuario;
import java.io.File;
import java.io.IOException;
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
        } else if (data instanceof Edificio) {
            Edificio edif = (Edificio) data;
            return edif.getNombre();
        } else if (data instanceof Curso) {
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
                    if ((horario.getEdificio().getNombre().equals(edificio)) && (horario.getSalon().getId() == salon)) {
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
                    if (estu.getCarnet() == carnet) {
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
                    Edificio edif = (Edificio) aux.getData();
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
                } else if (aux.getData() instanceof Curso) {
                    Curso curso = (Curso) aux.getData();
                    if (curso.getCodigo() == id) {
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
                    if (user.getId() == Integer.valueOf(id)) {
                        aux.setData(data);
                        return aux;
                    }
                } else if (aux.getData() instanceof Curso) {
                    Curso curso = (Curso) aux.getData();
                    if (curso.getCodigo() == Integer.valueOf(id)) {
                        aux.setData(data);
                        return aux;
                    }

                } else if (aux.getData() instanceof Edificio) {
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

    public ArrayList<Asignacion> mostrarAsignaciones() {
        ArrayList<Asignacion> asignacionesEncontradas = new ArrayList<>();
        if (root != null) {
            Nodo<T> aux = root;
            do {
                if (aux.getData() instanceof Asignacion) {
                    Asignacion asignacion = (Asignacion) aux.getData();
                    asignacionesEncontradas.add(asignacion);
                    if (aux.getNext() != null) {
                       
                    }
                    if (aux.getPrev() != null) {
                        
                    }

                }
                aux = aux.getNext();
            } while (aux != root);
        }
        return asignacionesEncontradas;
    }

    public void graficarListaCircular() throws IOException {
        String salida = "digraph G{\n";
        salida += "style=filled;\n";
        salida += "graph [fontsize=10 fontname=\"Verdana\" compound=true];";
        int contadorSalones = 0;
        if (root != null) {
            Nodo<T> aux = root;
            if (aux.getData() instanceof Usuario) {
                salida += "label = \" Lista de Usuarios \";\n";
            } else if (aux.getData() instanceof Curso) {
                salida += "label = \" Lista de Cursos \";\n";
            } else if (aux.getData() instanceof Edificio) {
                salida += "label = \" Lista de Edificios y salones \";\n";
            }
            salida += "subgraph Lista { node [shape = square,height=.1];  label=\"Lista doble circular\"; \n";
            do {
                if (aux.getData() instanceof Usuario) {

                    Usuario user = (Usuario) aux.getData();
                    Usuario userSiguiente = (Usuario) aux.getNext().getData();
                    Usuario userAnterior = (Usuario) aux.getNext().getPrev().getData();
                    salida += user.getId() + "->" + userSiguiente.getId() + " [constraint=false]; \n";
                    salida += userSiguiente.getId() + "->" + userAnterior.getId() + " [constraint=false]; \n";
                }
                if (aux.getData() instanceof Curso) {
                    Curso curso = (Curso) aux.getData();
                    Curso cursoSiguiente = (Curso) aux.getNext().getData();
                    Curso cursoAnterior = (Curso) aux.getNext().getPrev().getData();
                    salida += curso.getCodigo() + "->" + cursoSiguiente.getCodigo() + " [constraint=false]; \n";
                    salida += cursoSiguiente.getCodigo() + "->" + cursoAnterior.getCodigo() + " [constraint=false]; \n";
                }
                if (aux.getData() instanceof Edificio) {
                    Edificio edificio = (Edificio) aux.getData();
                    Edificio edificioSiguiente = (Edificio) aux.getNext().getData();
                    Edificio edificioAnterior = (Edificio) aux.getNext().getPrev().getData();
                    salida += edificio.getNombre() + "->" + edificioSiguiente.getNombre() + " [constraint=false]; \n";
                    salida += edificioSiguiente.getNombre() + "->" + edificioAnterior.getNombre() + " [constraint=false]; \n";
                    //Salones
                    ListaSimple salones = edificio.getSalones();
                    salida += "subgraph cluster_" + contadorSalones + "{node [shape = square,height=.1]; rankdir=LR; label=\"Salones" + contadorSalones + "\";  \n";
                    salida += salones.graficarSalones(edificio.getNombre());
                    salida += " } \n";
                    if (salones.getRoot() != null) {
                        if (salones.getRoot().getData() instanceof Salon) {
                            Salon salon = (Salon) salones.getRoot().getData();
                            salida += edificio.getNombre() + "->" + edificio.getNombre() + "_" + salon.getId() + "[lhead = cluster_" + contadorSalones + "]; \n";
                        }
                    }
                    contadorSalones++;

                } if (aux.getData() instanceof Asignacion) {
                    Asignacion asignacion = (Asignacion) aux.getData();
                    Asignacion asignacionSiguiente = (Asignacion) aux.getNext().getData();
                    Asignacion asignacionAnterior = (Asignacion) aux.getNext().getPrev().getData();
                    salida += "Asignacion_" + asignacion.getCodigo() + "->" + "Asignacion_" + asignacionSiguiente.getCodigo() + " [constraint=false]; \n";
                    salida += "Asignacion_" + asignacionSiguiente.getCodigo() + "->" + "Asignacion_" + asignacionAnterior.getCodigo() + " [constraint=false]; \n";
                }

                aux = aux.getNext();
            } while (aux != root);
            salida += "}";
            salida += "}";
            if (aux.getData() instanceof Usuario) {
                File imagenSalida = new File("./listaUsuarios.dot");
                if (!imagenSalida.exists()) {
                    imagenSalida.createNewFile();
                } else {
                    imagenSalida.delete();
                    imagenSalida.createNewFile();
                }
                Main.guardarImagen(salida, imagenSalida.getAbsolutePath());
                String command = "dot -Tpng listaUsuarios.dot -o listaUsuariosImagen.png";
                Runtime.getRuntime().exec(command);
            } else if (aux.getData() instanceof Curso) {
                File imagenSalida = new File("./listaCursos.dot");
                if (!imagenSalida.exists()) {
                    imagenSalida.createNewFile();
                } else {
                    imagenSalida.delete();
                    imagenSalida.createNewFile();
                }
                Main.guardarImagen(salida, imagenSalida.getAbsolutePath());
                String command = "dot -Tpng listaCursos.dot -o listaCursosImagen.png";
                Runtime.getRuntime().exec(command);
            } else if (aux.getData() instanceof Edificio) {
                File imagenSalida = new File("./listaEdificios.dot");
                if (!imagenSalida.exists()) {
                    imagenSalida.createNewFile();
                } else {
                    imagenSalida.delete();
                    imagenSalida.createNewFile();
                }
                Main.guardarImagen(salida, imagenSalida.getAbsolutePath());
                String command = "dot -Tpng listaEdificios.dot -o listaEdificiosImagen.png";
                Runtime.getRuntime().exec(command);
            }
        }

    }

}
