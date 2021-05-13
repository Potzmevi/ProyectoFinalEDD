/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Estructuras.*;
import GUI.Login;
import Objetos.Estudiante;
import Objetos.Usuario;
import java.util.Hashtable;

/**
 *
 * @author meza4
 */
public class Main {

    public static ListaCircular listaUsuarios = new ListaCircular();
    public static ListaCircular listaEdificios = new ListaCircular();
    public static ListaCircular listaCursos = new ListaCircular();
    public static ArbolAVL catedraticos = new ArbolAVL();
    public static Usuario usuarioActual;

    public static void main(String[] args) {

        listaUsuarios.add(new Usuario(123, "p", "p", "ESTUDIANTE"));
        listaUsuarios.add(new Usuario(1234, "Gordo", "Alejandrio", "ESTUDIANTE"));
        listaUsuarios.add(new Usuario(1235, "Trolo", "Alejandrio", "ESTUDIANTE"));

        TablaHash list = new TablaHash<>();
        Estudiante estu = new Estudiante(201930268, "Juan Pablo", "Zona 9");
        Estudiante estu2 = new Estudiante(201930268, "Juan Pablo", "Zona 9");
        Estudiante estu3 = new Estudiante(201930268, "Juan Pablo", "Zona 9");
        Estudiante estu4 = new Estudiante(201930268, "Juan Pablo", "Zona 9");
        Estudiante estu5 = new Estudiante(201930643, "Juan Pablo", "Zona 9");
        Estudiante estu6 = new Estudiante(201930643, "Juan Pablo", "Zona 9");
        Estudiante estu7 = new Estudiante(201930643, "Juan Pablo", "Zona 9");
        Estudiante estu8 = new Estudiante(201930643, "Juan Pablo", "Zona 9");
        System.out.println("add(abc)");
        list.add(estu);
        list.add(estu2);
        list.add(estu3);
        list.add(estu4);

        System.out.println("items: " + list);
        System.out.println("size: " + list.size());
        list.remove(estu.getCarnet());

        System.out.println("items: " + list);
        System.out.println("size: " + list.size());

        new Login();
    }
}
