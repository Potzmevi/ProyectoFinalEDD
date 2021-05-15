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
    private Curso codigoCurso;
     private Edificio codigoEdificio;
    private Salon codigoSalon;
    private Catedratico codigoCatedratico;

    public Horario(int codigo, String rangoHora, String dia, Curso codigoCurso, Edificio codigoEdificio, Salon codigoSalon, Catedratico codigoCatedratico) {
        this.codigo = codigo;
        this.rangoHora = rangoHora;
        this.dia = dia;
        this.codigoCurso = codigoCurso;
        this.codigoEdificio = codigoEdificio;
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

    public Curso getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(Curso codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public Edificio getCodigoEdificio() {
        return codigoEdificio;
    }

    public void setCodigoEdificio(Edificio codigoEdificio) {
        this.codigoEdificio = codigoEdificio;
    }

    public Salon getCodigoSalon() {
        return codigoSalon;
    }

    public void setCodigoSalon(Salon codigoSalon) {
        this.codigoSalon = codigoSalon;
    }

    public Catedratico getCodigoCatedratico() {
        return codigoCatedratico;
    }

    public void setCodigoCatedratico(Catedratico codigoCatedratico) {
        this.codigoCatedratico = codigoCatedratico;
    }

  
    
    
}
