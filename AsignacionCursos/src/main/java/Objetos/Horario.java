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
    private Curso curso;
     private Edificio edificio;
    private Salon salon;
    private Catedratico catedratico;
    private int codigoCurso;
    private int codigoCatedratico;
    private String nombreEdificio;
    private int codigoSalon;
    
    public Horario(int codigo, String rangoHora, String dia, Curso curso, Edificio edificio, Salon salon, Catedratico catedratico) {
        this.codigo = codigo;
        this.rangoHora = rangoHora;
        this.dia = dia;
        this.curso = curso;
        this.edificio = edificio;
        this.salon = salon;
        this.catedratico = catedratico;
    }

    public Horario(int codigo, String rangoHora, String dia, int codigoCurso, String nombreEdificio, int codigoSalon, int codigoCatedratico) {
        this.codigo = codigo;
        this.rangoHora = rangoHora;
        this.dia = dia;
        this.codigoCurso = codigoCurso;
        this.codigoCatedratico = codigoCatedratico;
        this.nombreEdificio = nombreEdificio;
        this.codigoSalon = codigoSalon;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public int getCodigoCatedratico() {
        return codigoCatedratico;
    }

    public void setCodigoCatedratico(int codigoCatedratico) {
        this.codigoCatedratico = codigoCatedratico;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    public int getCodigoSalon() {
        return codigoSalon;
    }

    public void setCodigoSalon(int codigoSalon) {
        this.codigoSalon = codigoSalon;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Catedratico getCatedratico() {
        return catedratico;
    }

    public void setCatedratico(Catedratico catedratico) {
        this.catedratico = catedratico;
    }

   
  
    
    
}
