/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import CargaDatos.Lexer;
import CargaDatos.Parser;
import Estructuras.ArbolAVL;
import Estructuras.ArbolB.LlaveEntero;
import Estructuras.ListaCircular;
import Estructuras.ListaSimple;
import Main.Graphviz;
import Main.Main;
import static Main.Main.*;

import Objetos.*;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author PotzMevi
 */
public class Controlador {

    public static void cargarArchivos(String cadena) throws Exception {
        Parser s = new Parser(new Lexer(new StringReader(cadena)));
        s.parse();
        ArrayList<Estudiante> estudiantesList = s.getEstudiantesList();
        ArrayList<Usuario> usuarioList = s.getUsuarios();
        ArrayList<Edificio> edificiosList = s.getEdificios();
        ArrayList<Salon> salonesList = s.getSalones();
        ArrayList<Catedratico> catedraticosList = s.getCatedratico();
        ArrayList<Curso> cursosList = s.getCursos();
        ArrayList<Horario> horariosList = s.getHorarios();
        ArrayList<Asignacion> asignacionesList = s.getAsignaciones();

        for (int i = 0; i < estudiantesList.size(); i++) {
            estudiantes.add(estudiantesList.get(i));
        }
        for (int i = 0; i < usuarioList.size(); i++) {
            if (usuarioList.get(i).getTipo().equals("ESTUDIANTE")) {
                Estudiante estu = estudiantes.get(usuarioList.get(i).getId());
                if (estu != null) {
                    listaUsuarios.add(usuarioList.get(i));
                }
            } else {
                listaUsuarios.add(usuarioList.get(i));
            }
        }
        for (int i = 0; i < edificiosList.size(); i++) {
            listaEdificios.add(edificiosList.get(i));
        }
        for (int i = 0; i < salonesList.size(); i++) {
            ListaCircular.Nodo nodo = listaEdificios.buscarNombre(salonesList.get(i).getEdificio());
            Edificio edif = (Edificio) nodo.getData();
            ListaSimple salones = edif.getSalones();
            salones.add(new Salon(salonesList.get(i).getId(), salonesList.get(i).getEstudiantes()));
        }
        for (int i = 0; i < catedraticosList.size(); i++) {

            catedraticos.insertar(Integer.valueOf(catedraticosList.get(i).getId()), catedraticosList.get(i));

        }
        for (int i = 0; i < cursosList.size(); i++) {
            listaCursos.add(cursosList.get(i));
        }
        for (int i = 0; i < horariosList.size(); i++) {
            ListaCircular.Nodo nodo = listaEdificios.buscarNombre(horariosList.get(i).getNombreEdificio());
            Edificio edif = (Edificio) nodo.getData();
            ListaSimple salones = edif.getSalones();
            ListaSimple.Nodo nodosalon = salones.getNode(String.valueOf(horariosList.get(i).getCodigoSalon()));
            ListaCircular.Nodo nodocurso = listaCursos.buscarId(Integer.valueOf(horariosList.get(i).getCodigoCurso()));
            ArbolAVL.NodoAVL nodoCatedratico = catedraticos.buscar(Integer.valueOf(horariosList.get(i).getCodigoCatedratico()));
            Salon salon = (Salon) nodosalon.getData();
            Curso curso = (Curso) nodocurso.getData();
            Catedratico catedratico = (Catedratico) nodoCatedratico.getInfo();
            Horario horario = new Horario(Integer.valueOf(horariosList.get(i).getCodigo()), horariosList.get(i).getRangoHora(), horariosList.get(i).getDia(), curso, edif, salon, catedratico);
            horarios.insert(new LlaveEntero(horario.getCodigo()), horario);
        }
        for (int i = 0; i < asignacionesList.size(); i++) {
            Estudiante estu = estudiantes.get(Integer.valueOf(asignacionesList.get(i).getCodigoEstudiante()));
            Horario hora = (Horario) horarios.search(new LlaveEntero(Integer.valueOf(asignacionesList.get(i).getCodigoHorario())));
            if (listaAsignaciones.obtenerAsignacionesSalon(hora.getSalon().getId(), hora.getEdificio().getNombre()) < hora.getSalon().getEstudiantes()) {
                Asignacion asig = new Asignacion(estu, hora, asignacionesList.get(i).getZona(), asignacionesList.get(i).getNotaFinal());
                listaAsignaciones.add(asig);
            }

        }
        JOptionPane.showMessageDialog(null, "Datos cargados con exito");
        listaEdificios.graficarListaCircular();
        catedraticos.crearGrafica();
        
        Graphviz.graficarHorario();
        Graphviz.graficarAsignaciones();
    }
}
