/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author meza4
 */
public class Salon {
    private String edificio;
    private int id;
    private int estudiantes;

    public Salon(int id, int estudiantes) {
        this.id = id;
        this.estudiantes = estudiantes;
    }

    public Salon(String edificio, int id, int estudiantes) {
        this.edificio = edificio;
        this.id = id;
        this.estudiantes = estudiantes;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(int estudiantes) {
        this.estudiantes = estudiantes;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }
    
    
}
