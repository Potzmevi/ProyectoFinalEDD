/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Estructuras.*;
import static Main.Main.guardarImagen;
import static Main.Main.*;
import Objetos.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author PotzMevi
 */
public class Graphviz {

    public static void graficarTabla(String[] datos) throws IOException {
        String texto = "";
        String punteros = "";
        texto += "struct1 [label=\"";
        for (int i = 0; i < datos.length; i++) {
            if (i + 1 == datos.length) {
                texto += "<" + i + "> " + i;
            } else {
                texto += "<" + i + "> " + i + "|";
            }
            if (!(datos[i].equalsIgnoreCase("[]") || datos[i].equalsIgnoreCase(" []"))) {
                punteros += "struct1:" + i + "->" + "\"" + datos[i] + "\";\n";
            }
        }
        texto += "\"];\n";

        String salida = "digraph G {\n rankdir=\"LR\";\n"
                + "node [shape=record];";
        salida += texto;
        salida += punteros;
        salida += "label = \" TABLA HASH \";\n";
        salida += "}\n";

        File imagenSalida = new File("./TablaHash.dot");
        if (!imagenSalida.exists()) {
            imagenSalida.createNewFile();
        } else {
            imagenSalida.delete();
            imagenSalida.createNewFile();
        }
        Main.guardarImagen(salida, imagenSalida.getAbsolutePath());
        String command = "dot -Tpng TablaHash.dot -o TablaHash.png";
        Runtime.getRuntime().exec(command);
    }

    public static void graficarArbolB() throws IOException {
        File imagenSalida = new File("./ArbolB.dot");
        if (!imagenSalida.exists()) {
            imagenSalida.createNewFile();
        } else {
            imagenSalida.delete();
            imagenSalida.createNewFile();
        }
        guardarImagen(horarios.toDot(), imagenSalida.getAbsolutePath());
        String command = "dot -Tpng ArbolB.dot -o ArbolB.png";
        Runtime.getRuntime().exec(command);

    }

    public static void graficarHorario() throws IOException {

        StringBuilder b = new StringBuilder();

        b.append(horarios.getmRaiz().getDotName());
        b.append("[label=\"<P0>");
        for (int i = 0; i < horarios.getmRaiz().getmB(); i++) {
            b.append("|" +"<"+horarios.getmRaiz().getmLlaves()[i].getKey().toString()+"> "+ horarios.getmRaiz().getmLlaves()[i].getKey().toString());
            b.append("|<P" + (i + 1) + ">");
        }

        b.append("\"];\n");

        for (int i = 0; i <= horarios.getmRaiz().getmB(); i++) {
            if (horarios.getmRaiz().getmPunteros()[i] != null) {
                b.append(horarios.getmRaiz().getmPunteros()[i].toDot());
                b.append(horarios.getmRaiz().getDotName() + ":P" + i + " -> " + horarios.getmRaiz().getmPunteros()[i].getDotName() + ";\n");
            }
        }

        String salida = "digraph g { \n node [shape=record];\n";
         salida +="graph [fontsize=10 fontname=\"Verdana\" compound=true];";


        
        int contadorSalones = 0;
        if (listaEdificios.getRoot() != null) {
            ListaCircular.Nodo aux = listaEdificios.getRoot();
            
            salida += "subgraph clusterLista { node [shape = square,height=.1];  label=\"Lista doble circular\"; \n";
            do {
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

                }

                aux = aux.getNext();
            } while (aux != listaEdificios.getRoot());
            salida += "}\n";
        }if (listaCursos.getRoot() != null) {
            ListaCircular.Nodo aux = listaCursos.getRoot();
            salida += "subgraph clusterCurso { node [shape = square,height=.1];  label=\"Cursos\"; \n";
            do {
                if (aux.getData() instanceof Curso) {
                    Curso curso = (Curso) aux.getData();
                    Curso cursoSiguiente = (Curso) aux.getNext().getData();
                    Curso cursoAnterior = (Curso) aux.getNext().getPrev().getData();
                    salida += curso.getCodigo() + "->" + cursoSiguiente.getCodigo() + " [constraint=false]; \n";
                    salida += cursoSiguiente.getCodigo() + "->" + cursoAnterior.getCodigo() + " [constraint=false]; \n";
                }

                aux = aux.getNext();
            } while (aux != listaCursos.getRoot());

            salida += "}\n";
        }
        
       salida+=catedraticos.crearGraficaHorario();
            salida += b;
        salida += "label = \" Horarios \";\n";
            ArrayList horarioList=horarios.getmRaiz().toArray();
            for (int i = 0; i <horarioList.size(); i++) {
                salida +=((Horario)horarioList.get(i)).getNodoPadre()+":"+((Horario)horarioList.get(i)).getCodigo()+"->"+((Horario)horarioList.get(i)).getEdificio().getNombre()+"_"+((Horario)horarioList.get(i)).getSalon().getId()+"[constraint=false];\n";
                salida +=((Horario)horarioList.get(i)).getNodoPadre()+":"+((Horario)horarioList.get(i)).getCodigo()+"->"+((Horario)horarioList.get(i)).getCurso().getCodigo()+"[constraint=false];\n";
                salida +=((Horario)horarioList.get(i)).getNodoPadre()+":"+((Horario)horarioList.get(i)).getCodigo()+"->"+"\" " +((Horario)horarioList.get(i)).getCatedratico().getId()+ "\n" +((Horario)horarioList.get(i)).getCatedratico().getNombre()+ "\""+"[constraint=false];\n";
            }
            salida += "}\n";
            File imagenSalida = new File("./HorarioCompleto.dot");
            if (!imagenSalida.exists()) {
                imagenSalida.createNewFile();
            } else {
                imagenSalida.delete();
                imagenSalida.createNewFile();
            }
            Main.guardarImagen(salida, imagenSalida.getAbsolutePath());
            String command = "dot -Tpng HorarioCompleto.dot -o HorarioCompleto.png";
            Runtime.getRuntime().exec(command);
        
    }
    
    public static void graficarAsignaciones() throws IOException {

        StringBuilder b = new StringBuilder();

        b.append(horarios.getmRaiz().getDotName());
        b.append("[label=\"<P0>");
        for (int i = 0; i < horarios.getmRaiz().getmB(); i++) {
            b.append("|" +"<"+horarios.getmRaiz().getmLlaves()[i].getKey().toString()+"> "+ horarios.getmRaiz().getmLlaves()[i].getKey().toString());
            b.append("|<P" + (i + 1) + ">");
        }

        b.append("\"];\n");

        for (int i = 0; i <= horarios.getmRaiz().getmB(); i++) {
            if (horarios.getmRaiz().getmPunteros()[i] != null) {
                b.append(horarios.getmRaiz().getmPunteros()[i].toDot());
                b.append(horarios.getmRaiz().getDotName() + ":P" + i + " -> " + horarios.getmRaiz().getmPunteros()[i].getDotName() + ";\n");
            }
        }

        String salida = "digraph g { \n node [shape=record];\n";
       
         salida +="graph [fontsize=10 fontname=\"Verdana\" compound=true];";
        int contadorSalones = 0;
        if (listaEdificios.getRoot() != null) {
            ListaCircular.Nodo aux = listaEdificios.getRoot();
            
            salida += "subgraph clusterLista { node [shape = square,height=.1];  label=\"Lista doble circular\"; \n";
            do {
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

                }

                aux = aux.getNext();
            } while (aux != listaEdificios.getRoot());
            salida += "}\n";
        }if (listaCursos.getRoot() != null) {
            ListaCircular.Nodo aux = listaCursos.getRoot();
            salida += "subgraph clusterCurso { node [shape = square,height=.1];  label=\"Cursos\"; \n";
            do {
                if (aux.getData() instanceof Curso) {
                    Curso curso = (Curso) aux.getData();
                    Curso cursoSiguiente = (Curso) aux.getNext().getData();
                    Curso cursoAnterior = (Curso) aux.getNext().getPrev().getData();
                    salida += curso.getCodigo() + "->" + cursoSiguiente.getCodigo() + " [constraint=false]; \n";
                    salida += cursoSiguiente.getCodigo() + "->" + cursoAnterior.getCodigo() + " [constraint=false]; \n";
                }

                aux = aux.getNext();
            } while (aux != listaCursos.getRoot());

            salida += "}\n";
        }
        if (listaAsignaciones.getRoot() != null) {
            ListaCircular.Nodo aux = listaAsignaciones.getRoot();
            salida += "subgraph clusterAsignacion { node [shape = square,height=.1];  label=\"Asignaciones\"; \n";
            do {
               if (aux.getData() instanceof Asignacion) {
                    Asignacion asignacion = (Asignacion) aux.getData();
                    Asignacion asignacionSiguiente = (Asignacion) aux.getNext().getData();
                    Asignacion asignacionAnterior = (Asignacion) aux.getNext().getPrev().getData();
                    salida += "Asignacion_" + asignacion.getCodigo() + "->" + "Asignacion_" + asignacionSiguiente.getCodigo() + " [constraint=false]; \n";
                    salida += "Asignacion_" + asignacionSiguiente.getCodigo() + "->" + "Asignacion_" + asignacionAnterior.getCodigo() + " [constraint=false]; \n";
                }

                aux = aux.getNext();
            } while (aux != listaAsignaciones.getRoot());

            salida += "}\n";
        }
        salida += "subgraph clusterTabla { ";
         salida += "rankdir=\"LR\"";
        String items= estudiantes.toString();
        String[] datos=items.split(",");
        String texto = "";
        String punteros = "";
        texto += "struct1 [label=\"";
        for (int i = 0; i < datos.length; i++) {
            if (i + 1 == datos.length) {
                texto += "<" + i + "> " + i;
            } else {
                texto += "<" + i + "> " + i + "|";
            }
            if (!(datos[i].equalsIgnoreCase("[]") || datos[i].equalsIgnoreCase(" []"))) {
                punteros += "struct1:" + i + "->" + "\"" + datos[i].replace(" ","") + "\";\n";
            }
        }
        texto += "\"];\n";
         salida += texto;
        salida += punteros;
        salida += "label = \" Estudiantes \";\n";
        salida += "}\n";
       salida+=catedraticos.crearGraficaHorario();
            salida += b;
        salida += "label = \" Horarios \";\n";
            ArrayList horarioList=horarios.getmRaiz().toArray();
            for (int i = 0; i <horarioList.size(); i++) {
                salida +=((Horario)horarioList.get(i)).getNodoPadre()+":"+((Horario)horarioList.get(i)).getCodigo()+"->"+((Horario)horarioList.get(i)).getEdificio().getNombre()+"_"+((Horario)horarioList.get(i)).getSalon().getId()+"[constraint=false];\n";
                salida +=((Horario)horarioList.get(i)).getNodoPadre()+":"+((Horario)horarioList.get(i)).getCodigo()+"->"+((Horario)horarioList.get(i)).getCurso().getCodigo()+"[constraint=false];\n";
                salida +=((Horario)horarioList.get(i)).getNodoPadre()+":"+((Horario)horarioList.get(i)).getCodigo()+"->"+"\" " +((Horario)horarioList.get(i)).getCatedratico().getId()+ "\n" +((Horario)horarioList.get(i)).getCatedratico().getNombre()+ "\""+"[constraint=false];\n";
            }
            ArrayList asignacionesList=listaAsignaciones.mostrarAsignaciones();
            for (int i = 0; i <asignacionesList.size(); i++) {
                salida += "Asignacion_" +((Asignacion)asignacionesList.get(i)).getCodigo()+"->"+((Asignacion)asignacionesList.get(i)).getHorario().getNodoPadre()+":"+((Asignacion)asignacionesList.get(i)).getHorario().getCodigo()+"[constraint=false];\n";
                 salida += "Asignacion_" +((Asignacion)asignacionesList.get(i)).getCodigo()+"->"+ "\"[" +((Asignacion)asignacionesList.get(i)).getEstudiante().getCarnet()+ "]\"" +"[constraint=false];\n";
            }
            salida += "}\n";
            File imagenSalida = new File("./AsignacionCompleta.dot");
            if (!imagenSalida.exists()) {
                imagenSalida.createNewFile();
            } else {
                imagenSalida.delete();
                imagenSalida.createNewFile();
            }
            Main.guardarImagen(salida, imagenSalida.getAbsolutePath());
            String command = "dot -Tpng AsignacionCompleta.dot -o AsignacionCompleta.png";
            Runtime.getRuntime().exec(command);
        
    }
}
