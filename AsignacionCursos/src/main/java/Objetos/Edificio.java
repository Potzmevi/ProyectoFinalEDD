/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Estructuras.ListaSimple;

/**
 *
 * @author meza4
 */
public class Edificio {
    private String nombre;
    private ListaSimple salones;

    public Edificio(String nombre) {
        this.nombre = nombre;
        this.salones= new ListaSimple();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaSimple getSalones() {
        return salones;
    }

    public void setSalones(ListaSimple salones) {
        this.salones = salones;
    }
    
    
}
