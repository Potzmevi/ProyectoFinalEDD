/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Estructuras.*;
import Estructuras.ArbolB.ArbolB;
import Estructuras.ArbolB.LlaveEntero;
import GUI.Login;
import Objetos.Estudiante;
import Objetos.Usuario;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JOptionPane;

/**
 *
 * @author meza4
 */
public class Main {

    public static ListaCircular listaUsuarios = new ListaCircular();
    public static ListaCircular listaEdificios = new ListaCircular();
    public static ListaCircular listaCursos = new ListaCircular();
    public static ListaCircular listaAsignaciones=new ListaCircular();
    public static ArbolAVL catedraticos = new ArbolAVL();
    public static Usuario usuarioActual;
    public static TablaHash estudiantes = new TablaHash<>();
    public static ArbolB horarios = new ArbolB(2);
    public static int totalAsignaciones;
    
    
    public static void main(String[] args) throws IOException {

        listaUsuarios.add(new Usuario(201930268, "p", "p", "ESTUDIANTE"));
        listaUsuarios.add(new Usuario(1234, "g", "g", "COLABORADOR"));
        listaUsuarios.add(new Usuario(1235, "Trolo", "Alejandrio", "ESTUDIANTE"));

        Estudiante estu = new Estudiante(201930268, "Juan Pablo", "Zona 9");
        Estudiante estu2 = new Estudiante(201930258, "Juan Pablo", "Zona 9");
        Estudiante estu3 = new Estudiante(201330268, "Juan Pablo", "Zona 9");
        Estudiante estu4 = new Estudiante(201930268, "Juan Pablo", "Zona 9");
        Estudiante estu5 = new Estudiante(201930643, "Juan Pablo", "Zona 9");
        Estudiante estu6 = new Estudiante(201920643, "Juan Pablo", "Zona 9");
        Estudiante estu7 = new Estudiante(201935643, "Juan Pablo", "Zona 9");
        Estudiante estu8 = new Estudiante(201230643, "Juan Pablo", "Zona 9");

        System.out.println(horarios.search(new LlaveEntero(1)));
        System.out.println("add(abc)");
        estudiantes.add(estu);
        estudiantes.add(estu2);
        String items= estudiantes.toString();
        String[] datos=items.split(",");
        Graphviz.graficarTabla(datos);
        System.out.println("size: " + estudiantes.size());
        new Login();
    }

    public static void guardarImagen(String texto, String absolutePath) {
        //Writer para leer el archivo 
        FileWriter writer = null;
        try {
            //Crea el archivo en la absolute path
            writer = new FileWriter(absolutePath, true);
            try (BufferedWriter out = new BufferedWriter(writer)) {
                out.write("");
                out.write(texto);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la imagen");
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar la imagen");
            }
        }
    }

}
