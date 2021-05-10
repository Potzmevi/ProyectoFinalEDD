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
public class Horario {
    private int codigo;
    private String rangoHora;
    private String dia;
    private int codigoCurso;
    private int codigoSalon;
    private int codigoCatedratico;

    public Horario(int codigo, String rangoHora, String dia, int codigoCurso, int codigoSalon, int codigoCatedratico) {
        this.codigo = codigo;
        this.rangoHora = rangoHora;
        this.dia = dia;
        this.codigoCurso = codigoCurso;
        this.codigoSalon = codigoSalon;
        this.codigoCatedratico = codigoCatedratico;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRangoHora() {
        return rangoHora;
    }

    public void setRangoHora(String rangoHora) {
        this.rangoHora = rangoHora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public int getCodigoSalon() {
        return codigoSalon;
    }

    public void setCodigoSalon(int codigoSalon) {
        this.codigoSalon = codigoSalon;
    }

    public int getCodigoCatedratico() {
        return codigoCatedratico;
    }

    public void setCodigoCatedratico(int codigoCatedratico) {
        this.codigoCatedratico = codigoCatedratico;
    }
    
    
}
