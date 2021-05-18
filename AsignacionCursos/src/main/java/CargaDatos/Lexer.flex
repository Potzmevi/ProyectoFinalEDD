package Archivos;
import java_cup.runtime.*;
import static Archivos.symCapas.*;

%%
%class Lexer
%type java_cup.runtime.Symbol
%cup
%full
%line
%unicode
%column
%public

D = [0-9]+
SEPARADOR = \r|\n|\r\n
ESPACIO = {SEPARADOR} | [ \t\f]
ERRORES = [^]
StringSin="\""[^[ \r\t\b\f\n]["\""]]+"\""
String="\""[^["\""]]+"\""
TEXTO = [:jletterdigit:]+

%{
  private Symbol symbol(int type, String lexema) {
        return new Symbol(type, new Token(lexema, yyline + 1, yycolumn + 1));
      }
%}
%%

/* Espacios en blanco */
{ESPACIO} {/*Ignore*/}

/* Llave de apertura */
( "Estudiante" ) {return  symbol(sym.Estudiante, yytext());}

/* Llave de apertura */
( "estudiante" ) {return  symbol(sym.EstudianteUs, yytext());}

/* Llave de apertura */
( "colaborador" ) {return  symbol(sym.Colaborador, yytext());}

/* Llave de apertura */
( "Usuario" ) {return  symbol(sym.Usuario, yytext());}

/* Llave de apertura */
( "Catedratico" ) {return  symbol(sym.Catedratico, yytext());}

/* Llave de apertura */
( "Edificio" ) {return  symbol(sym.Edificio, yytext());}

/* Llave de apertura */
( "Salon" ) {return  symbol(sym.Salon, yytext());}

/* Llave de apertura */
( "Curso" ) {return  symbol(sym.Curso, yytext());}

/* Llave de apertura */
( "Horario" ) {return  symbol(sym.Horario, yytext());}

/* Llave de apertura */
( "Asignar" ) {return  symbol(sym.Asignar, yytext());}

/* Llave de apertura */
( "(" ) {return  symbol(sym.Parente_a, yytext());}

/* Llave de cierre */
( ")" ) {return  symbol(sym.Parente_c, yytext());}

/* Coma */
( "," ) {return symbol(sym.Coma,  yytext());}

/* Punto y Coma */
( ";" ) {return symbol(sym.PuntoComa,  yytext());}

/* Numero */
{D} {return  symbol(sym.Numero, yytext());}

/* Texto */
{String} {return  symbol(symUsuario.Texto, yytext());}

/* Texto */
{TEXTO} {return  symbol(symUsuario.Textosin, yytext());}

/* Error */
{ERRORES} {return  symbol(sym.Error, yytext());}


