/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Estructuras.*;
import GUI.Login;
import Objetos.Usuario;

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

        new Login();
    }
}
