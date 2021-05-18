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
public class Asignacion {
    private int codigo;
    private Estudiante estudiante;
    private Horario horario;
    private int zona;
    private int notaFinal;
    private int codigoHorario;
    private int codigoEstudiante;

    public Asignacion(Estudiante estudiante, Horario horario, int zona, int notaFinal) {
        this.estudiante = estudiante;
        this.horario = horario;
        this.zona = zona;
        this.notaFinal = notaFinal;
        this.codigo=Main.Main.totalAsignaciones;
        Main.Main.totalAsignaciones++;
    }

    public Asignacion(int codigoEstudiante, int codigoHorario, int zona, int notaFinal) {
        this.codigoEstudiante = codigoEstudiante;
        this.zona = zona;
        this.notaFinal = notaFinal;
        this.codigoHorario = codigoHorario;
        this.codigo=Main.Main.totalAsignaciones;
        Main.Main.totalAsignaciones++;
    }

    public int getCodigoHorario() {
        return codigoHorario;
    }

    public void setCodigoHorario(int codigoHorario) {
        this.codigoHorario = codigoHorario;
    }

    public int getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(int codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    
    
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public int getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(int notaFinal) {
        this.notaFinal = notaFinal;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
    
}
