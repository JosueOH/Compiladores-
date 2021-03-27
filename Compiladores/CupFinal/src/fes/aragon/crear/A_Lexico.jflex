package fes.aragon.compilador;
import java_cup.runtime.*;
import java.util.LinkedList;

%%
%{
    public static LinkedList<TError> TablaEL = new LinkedList<TError>();
    public Analizador_Lexico(java.io.InputStream in){
        this(new java.io.InputStreamReader(in));
    } 
%}

%public
%class Analizador_Lexico
%cupsym Simbolos
%cup
%char
%column
%full
%ignorecase
%line
%unicode


PuntoComa=";"

//expresiones
Or="or"
And="and"
Not="not"
True="true"
False="false"
Apertura="("
Cierre=")"

%%


/*------------  3ra Area: Reglas Lexicas ---------*/



<YYINITIAL> "or"        { return new Symbol(Simbolos.Or, yycolumn, yyline, yytext());  }
<YYINITIAL> "and"       { return new Symbol(Simbolos.And, yycolumn, yyline, yytext()); }
<YYINITIAL> "not"       { return new Symbol(Simbolos.Not, yycolumn, yyline, yytext()); }
<YYINITIAL> "("         { return new Symbol(Simbolos.Apertura, yycolumn, yyline, yytext()); }
<YYINITIAL> ")"         { return new Symbol(Simbolos.Cierre, yycolumn, yyline, yytext()); }
<YYINITIAL> ";"         { return new Symbol(Simbolos.PuntoComa, yycolumn, yyline, yytext()); }

<YYINITIAL> {True}    { return new Symbol(Simbolos.True, yycolumn, yyline, yytext()); }
<YYINITIAL> {False}    { return new Symbol(Simbolos.False, yycolumn, yyline, yytext()); }

[ \t\r\n\f]             {/* Espacios en blanco, se ignoran */}

.                       { System.out.println("Error Lexico"+yytext()+" Linea "+yyline+" Columna "+yycolumn);
                          TError datos = new TError(yytext(),yyline,yycolumn,"Error Lexico","Simbolo no existe en el lenguaje");
                          TablaEL.add(datos);}


