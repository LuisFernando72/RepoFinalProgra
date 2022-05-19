package modelo;
import compilerTools.TextColor;
import java.awt.Color;
%%
%class AnalizadorColor
%type TextColor
%char
%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int)start, size, color); 
    }
%}
    TerminadorDeLinea = \r|\n|\r\n
    EntradaDeCaracter = [^\r\n]
    EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
    ComentarioTradicional = "/*" [^*] ~"*/" | "/*" "*"+ "/"
    FinDeLineaComentario = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?
    ContenidoComentario = ( [^*] | \*+ [^/*] )*
    ComentarioDeDocumentacion = "/**" {ContenidoComentario} "*"+ "/"

    /* Comentario */
    Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}

    /* Identificador */
    Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
    Digito = [0-9]
    Identificador = {Letra}({Letra}|{Digito})*

    /* Número */
    Numero = 0 | [1-9][0-9]*
%%

  /* Comentarios o espacios en blanco 
    {Comentario} { return textColor(yychar, yylength(), new Color(146,146,146)); }*/
    {EspacioEnBlanco} { /*Ignorar*/ }
/* Excepciones */
"=" { return textColor(yychar, yylength(), new Color(0,0,0)); }

/* Estructura */
!DOCTYPE | html | head | body | base | meta | link | style 1 script { return textColor(yychar, yylength(), new Color(255,140,0)); }

/* Signos */
"(" | ")" | "{" | "}" | "[" | "]" | "<" | ">" | "/" { return textColor(yychar, yylength(), new Color(153,50, 204)); }

/* Atributos */
id/=  { return textColor(yychar, yylength(), new Color(0, 191, 255)); }
class | lang | translate | title | data-share | accesskey | dir | style { return textColor(yychar, yylength(), new Color(0, 191, 255)); }

/* Obsoleto */
applet | acronym | bgsound | frame | frameset | noframes | hgroup | isindex |
 listing | xmp | noembed | strike | basefont | big | blink | center | font |
 marquee | multicol | nobr | spacer | menu { return textColor(yychar, yylength(), new Color(255, 215, 0)); }

/* Atributos Obsoletos */
charset | name | language | link | alink | vlink | bgcolor | align |
 valign | hspace | vspace | allowtransparency | frameborder | scrolling |
 border | cellpadding | cellspacing | nowrap { return textColor(yychar, yylength(), new Color(124, 252, 0)); }

/* Texto */
"<strong>" | "<em>" | "<mark>" | "<i>" | "<b>" | "<u>" | "<s>" | "<span>" | "<cite>" | "<sup>" | "</sup>" | "<sub>" | "</sub>" |
 "<small>" | "<q>" | "</q>" | "<dfn>" | "<abbr>" | "<br>" | "<wbr>" | "<kbd>" | "<samp>" | "<var>" | "<time>" |
 "<data>" | "<code>" | "<ins>" | "<del>" { return textColor(yychar, yylength(), new Color(32, 178, 170)); }
"q " { return textColor(yychar, yylength(), new Color(32, 178, 170)); }

/* Agrupación */
"<"p">" | "<""/"p">" | "p " | "<"div">" | "<""/"div">" | "div " | "<"pre">" | "<""/"pre">" |
 "<"blockquote">" | "<""/"blockquote">" | "blockquote " | "<"main">" | "<""/"main">" |
 "<"hr">" | "<""/"hr">" | "<"ul">" | "<""/"ul">" | "<"ol">" | "<""/"ol">" | "ol " |
 "<"li">" | "<""/"li">" | "<"dl">" | "<""/"dl">" |  "<"dt">" | "<""/"dt">" |
 "<"dd">" | "<""/"dd">" |  "<"figure">" | "<""/"figure">" |  "<"figcaption">" | "<""/"figcaption">" { return textColor(yychar, yylength(), new Color(255, 165, 0)); }
start/= { return textColor(yychar, yylength(), new Color(255, 165, 0)); }
reversed/= { return textColor(yychar, yylength(), new Color(255, 165, 0)); }
type/= { return textColor(yychar, yylength(), new Color(255, 165, 0)); }

/* Hipervinculos */
"<"a">" | "<""/"a">" { return textColor(yychar, yylength(), new Color(218, 112, 214)); }
href/=  { return textColor(yychar, yylength(), new Color(218, 112, 214)); }
download/=  { return textColor(yychar, yylength(), new Color(218, 112, 214)); }
target/=  { return textColor(yychar, yylength(), new Color(218, 112, 214)); }
rel/= { return textColor(yychar, yylength(), new Color(218, 112, 214)); }
hreflang/= { return textColor(yychar, yylength(), new Color(218, 112, 214)); }
type/= { return textColor(yychar, yylength(), new Color(218, 112, 214)); }

/* Seccion */
"<"article">" | "<""/"article">" | "article " | "<"nav">" | "<""/"nav">" | "<"header">" | "<""/"header">" |
 "<"h1">" | "<""/"h1">" | "<"h2">" | "<""/"h2">" | "<"h3">" | "<""/"h3">" | "<"h4">" | "<""/"h4">" |
 "<"h5">" | "<""/"h5">" | "<"h6">" | "<""/"h6">" | "<"footer">" | "<""/"footer">" | "<"section">" | "<""/"section">" |
 "<"aside">" | "<""/"aside">" | "<"address">" | "<""/"address">" { return textColor(yychar, yylength(), new Color(0, 255, 127)); }

/* Tablas */
"<"table">" | "<""/"table">" | "<"tr">" | "<""/"tr">" | "<"td">" | "<""/"td">" | "<"th">" | "<""/"th">" |
 colspan | rowspan | headers | scope | abbr | "<"thead">" | "<""/"thead">" | "<"tbody">" | "<""/"tbody">" |
 "<"tfoot">" | "<""/"tfoot">" | "<"caption">" | "<""/"caption">" | "<"colgroup">" | "<""/"colgroup">" |
 "<"col">" | "<""/"col">" | "col " { return textColor(yychar, yylength(), new Color(70, 130, 180)); }

/* Imagenes */
src/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
alt/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
width/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
height/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
poster/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
preload/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
mediagroup/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
autoplay/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
loop/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
muted/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
controls/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
srclang/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
label/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
kind/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
default/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
"<"picture">" | "<""/"picture">" | "picture " | "<"source">" | "<""/"source">" |
 "source " | "<"img">" | "<""/"img">" | "img " | "<"track">" | "<""/"track">" |
 "track " | "<"video">" | "<""/"video">" | "video " | "<"audio">" | "<""/"audio">" |
 "audio " { return textColor(yychar, yylength(), new Color(62, 246, 166)); }
srcset/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
sizes/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }
media/=  { return textColor(yychar, yylength(), new Color(64, 224, 208)); }

/* Contenido Externo */
"<"iframe">" | "<""/"iframe">" | "iframe " | "<"embed">" | "<""/"embed">" |
 "embed " | "<"object">" | "<""/"object">" | "object " | "<"param">" | "<""/"param">" | "param " { return textColor(yychar, yylength(), new Color(255, 64, 216)); }

/* Metadatos */
"<"meta">" | "<""/"meta">" | "meta " { return textColor(yychar, yylength(), new Color(159, 0, 12)); }
description | keywords | author | language | generator | theme-color | viewport |
 google | robots | index | noindex | follow | nofollow | nosnippet | noodp |
 noarchive | unavailable_after | noimageindex | none | refresh | expires |
 pragma | cache-control { return textColor(yychar, yylength(), new Color(159, 0, 12)); }

/* Script */
nomodule | async | defer | alert | import | from |var { return textColor(yychar, yylength(), new Color(238, 234, 12)); }

/* Interactivas */
"<"details">" | "<""/"details">" | "details " | "<"summary">" | "<""/"summary">" | "summary " |
 "<"dialog">" | "<""/"dialog">" | "dialog " { return textColor(yychar, yylength(), new Color(91, 145, 14)); }
true { return textColor(yychar, yylength(), new Color(15, 255, 0)); }
false { return textColor(yychar, yylength(), new Color(255, 0, 0)); }

/* HTML5 */
minlength | maxlength | min | max | step | required | disabled | readonly | value | low |
 high | optimum { return textColor(yychar, yylength(), new Color(166, 7, 7)); }

/* Formulario */
"<"input">" | "<""/"input">" | "input " | "<button>" | "<"select">" | "<""/"select">" |
 "select " | "<"option">" | "<""/"option">" | "option " | "<"datalist">" | "<""/"datalist">" |
 "datalist " | "<"form">" | "<""/"form">" | "form " { return textColor(yychar, yylength(), new Color(231, 159, 33)); }

/* Campos */
"<"fieldset">" | "<""/"fieldset">" | "fieldset " | "<"legend">" | "<""/"legend">" | "legend " |
 "<"label">" | "<""/"label">" | "label " | action | method | target | enctype |
accept-charset | autocomplete | novalidate | placeholder | size | autofocus { return textColor(yychar, yylength(), new Color(191, 199, 20)); }

/* Archivos */
submit | image | reset | button | file | color | checkbox | radio | date | time |
 datetime-local | month | week | number | range | text | search | tel | url | email |
 password | hidden { return textColor(yychar, yylength(), new Color(120, 199, 20)); }

. { /*Ignorar*/ }