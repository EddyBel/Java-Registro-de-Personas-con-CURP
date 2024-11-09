package com.mycompany.registrousuarios;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta clase se encarga de generar una CURP (Clave Única de Registro de Población) 
 * para una persona a partir de su nombre, apellidos, fecha de nacimiento, sexo y estado de nacimiento.
 * La CURP es un identificador único para cada ciudadano en México.
 * 
 * Los métodos en esta clase realizan varias operaciones sobre los datos proporcionados, 
 * como limpieza de texto, extracción de letras, validación de sexo y formato de fecha.
 * 
 * @author ben_9
 */
public class CurpGenerador {

    // Mapa que asocia los nombres de los estados de México con sus abreviaciones correspondientes.
    private static final Map<String, String> estados;

    // Bloque estático que inicializa el mapa de estados
    static {
        estados = new HashMap<>();
        estados.put("AGUASCALIENTES", "AS");
        estados.put("BAJA CALIFORNIA", "BC");
        estados.put("BAJA CALIFORNIA SUR", "BS");
        estados.put("CAMPECHE", "CC");
        estados.put("COAHUILA", "CL");
        estados.put("COLIMA", "CM");
        estados.put("CHIAPAS", "CS");
        estados.put("CHIHUAHUA", "CH");
        estados.put("CIUDAD DE MÉXICO", "DF");
        estados.put("DURANGO", "DG");
        estados.put("GUANAJUATO", "GT");
        estados.put("GUERRERO", "GR");
        estados.put("HIDALGO", "HG");
        estados.put("JALISCO", "JC");
        estados.put("MÉXICO", "MC");
        estados.put("MICHOACÁN", "MN");
        estados.put("MORELOS", "MS");
        estados.put("NAYARIT", "NT");
        estados.put("NUEVO LEÓN", "NL");
        estados.put("OAXACA", "OC");
        estados.put("PUEBLA", "PL");
        estados.put("QUERÉTARO", "QT");
        estados.put("QUINTANA ROO", "QR");
        estados.put("SAN LUIS POTOSÍ", "SP");
        estados.put("SINALOA", "SL");
        estados.put("SONORA", "SR");
        estados.put("TABASCO", "TC");
        estados.put("TAMAULIPAS", "TS");
        estados.put("TLAXCALA", "TL");
        estados.put("VERACRUZ", "VZ");
        estados.put("YUCATÁN", "YN");
        estados.put("ZACATECAS", "ZS");
    }

    /**
     * Este método limpia un texto para que esté en mayúsculas y elimine cualquier caracter
     * que no sea una letra del abecedario o una "Ñ".
     *
     * @param texto El texto a limpiar.
     * @return El texto limpio en mayúsculas.
     */
    private static String limpiarTexto(String texto) {
        return texto.trim().toUpperCase().replaceAll("[^A-ZÑ]", "");
    }

    /**
     * Este método busca la primera vocal interna (después de la primera letra) de un texto dado.
     * Si no encuentra una vocal interna, devuelve "X".
     *
     * @param texto El texto del cual extraer la vocal interna.
     * @return La primera vocal interna o "X" si no se encuentra.
     */
    private static String primeraVocalInterna(String texto) {
        // Utiliza una expresión regular para encontrar la primera vocal interna (después de la primera letra).
        Matcher matcher = Pattern.compile("[AEIOU]").matcher(texto.substring(1));
        return matcher.find() ? String.valueOf(matcher.group().charAt(0)) : "X";
    }

    /**
     * Este método busca la primera consonante interna (después de la primera letra) de un texto dado.
     * Si no encuentra una consonante interna, devuelve "X".
     *
     * @param texto El texto del cual extraer la consonante interna.
     * @return La primera consonante interna o "X" si no se encuentra.
     */
    private static String primeraConsonanteInterna(String texto) {
        // Utiliza una expresión regular para encontrar la primera consonante interna (después de la primera letra).
        Matcher matcher = Pattern.compile("[^AEIOU]").matcher(texto.substring(1));
        return matcher.find() ? String.valueOf(matcher.group().charAt(0)) : "X";
    }

    /**
     * Este es el método principal que genera la CURP a partir de los datos proporcionados.
     * La CURP se genera siguiendo una serie de reglas basadas en los apellidos, nombre, fecha de nacimiento,
     * sexo y estado de nacimiento.
     *
     * @param nombre El nombre de la persona.
     * @param apellidoPaterno El apellido paterno de la persona.
     * @param apellidoMaterno El apellido materno de la persona.
     * @param fechaNacimiento La fecha de nacimiento en formato "YYYY-MM-DD".
     * @param estado El estado de nacimiento de la persona.
     * @param sexo El sexo de la persona ("H" para hombre, "M" para mujer).
     * @return La CURP generada.
     */
    public static String generarCURP(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String estado, String sexo) {
        // Limpiar y estandarizar los textos de los apellidos y nombre.
        nombre = limpiarTexto(nombre);
        apellidoPaterno = limpiarTexto(apellidoPaterno);
        apellidoMaterno = limpiarTexto(apellidoMaterno);

        // Extraer la primera letra y la primera vocal interna del apellido paterno.
        String primeraLetraApellidoPaterno = String.valueOf(apellidoPaterno.charAt(0));
        String primeraVocalInternaApellidoPaterno = primeraVocalInterna(apellidoPaterno);

        // Si el apellido materno está vacío, usar "X". Si no, usar la primera letra.
        String primeraLetraApellidoMaterno = apellidoMaterno.isEmpty() ? "X" : String.valueOf(apellidoMaterno.charAt(0));

        // Extraer la primera letra del nombre. Si es un nombre compuesto como "MARIA" o "JOSE", se toma la segunda parte.
        String primeraLetraNombre = nombre.matches("^(MARIA|JOSE|MA|JO|J)\\b") ? String.valueOf(nombre.split(" ")[1].charAt(0)) : String.valueOf(nombre.charAt(0));

        // Extraer y formatear la fecha de nacimiento en formato YYMMDD.
        String[] fechaParts = fechaNacimiento.split("-");
        String anioFormato = fechaParts[0].substring(2); // Tomamos los últimos dos dígitos del año.
        String mesFormato = fechaParts[1];  // El mes, tal cual.
        String diaFormato = fechaParts[2];  // El día, tal cual.

        // Validación de sexo: "H" para hombre, "M" para mujer.
        sexo = sexo.equalsIgnoreCase("H") ? "H" : "M";

        // Obtener la abreviación del estado. Si no se encuentra, usamos "NE" para "Nacido en el Extranjero".
        String entidad = estados.getOrDefault(estado.toUpperCase(), "NE");

        // Extraer la primera consonante interna de los apellidos y el nombre.
        String consonantePaterno = primeraConsonanteInterna(apellidoPaterno);
        String consonanteMaterno = primeraConsonanteInterna(apellidoMaterno);
        String consonanteNombre = primeraConsonanteInterna(nombre);

        // Generar un número aleatorio para la homoclave (parte final de la CURP).
        int homoclave = (int) (Math.random() * 10);

        // Devolver la CURP concatenando todas las partes formadas.
        return primeraLetraApellidoPaterno + primeraVocalInternaApellidoPaterno + primeraLetraApellidoMaterno
                + primeraLetraNombre + anioFormato + mesFormato + diaFormato + sexo + entidad
                + consonantePaterno + consonanteMaterno + consonanteNombre + homoclave;
    }
}
