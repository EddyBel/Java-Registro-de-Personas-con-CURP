/*
 * Esta clase contiene constantes relacionadas con los estados de la República Mexicana
 * y su respectiva abreviatura, almacenadas en un mapa inmutable.
 * 
 * El mapa `ESTADOS` mapea el nombre completo del estado a su abreviatura correspondiente, 
 * utilizando claves y valores de tipo `String`.
 * Esta clase también proporciona un método para obtener los nombres de los estados.
 */
package com.mycompany.registrousuarios;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * La clase `Constantes` almacena un mapa de los estados de la República Mexicana 
 * junto con sus abreviaturas, y ofrece un método para obtener un conjunto con 
 * los nombres de los estados.
 * 
 * @author ben_9
 */
public class Constantes {

    // Mapa inmutable que contiene los estados y sus respectivas abreviaturas.
    // La clave es el nombre completo del estado y el valor es la abreviatura del estado.
    public static final Map<String, String> ESTADOS;

    // Bloque estático para inicializar el mapa con los estados y sus abreviaturas
    static {
        ESTADOS = new HashMap<>();
        ESTADOS.put("AGUASCALIENTES", "AS");
        ESTADOS.put("BAJA CALIFORNIA", "BC");
        ESTADOS.put("BAJA CALIFORNIA SUR", "BS");
        ESTADOS.put("CAMPECHE", "CC");
        ESTADOS.put("COAHUILA", "CL");
        ESTADOS.put("COLIMA", "CM");
        ESTADOS.put("CHIAPAS", "CS");
        ESTADOS.put("CHIHUAHUA", "CH");
        ESTADOS.put("CIUDAD DE MÉXICO", "DF");
        ESTADOS.put("DURANGO", "DG");
        ESTADOS.put("GUANAJUATO", "GT");
        ESTADOS.put("GUERRERO", "GR");
        ESTADOS.put("HIDALGO", "HG");
        ESTADOS.put("JALISCO", "JC");
        ESTADOS.put("MÉXICO", "MC");
        ESTADOS.put("MICHOACÁN", "MN");
        ESTADOS.put("MORELOS", "MS");
        ESTADOS.put("NAYARIT", "NT");
        ESTADOS.put("NUEVO LEÓN", "NL");
        ESTADOS.put("OAXACA", "OC");
        ESTADOS.put("PUEBLA", "PL");
        ESTADOS.put("QUERÉTARO", "QT");
        ESTADOS.put("QUINTANA ROO", "QR");
        ESTADOS.put("SAN LUIS POTOSÍ", "SP");
        ESTADOS.put("SINALOA", "SL");
        ESTADOS.put("SONORA", "SR");
        ESTADOS.put("TABASCO", "TC");
        ESTADOS.put("TAMAULIPAS", "TS");
        ESTADOS.put("TLAXCALA", "TL");
        ESTADOS.put("VERACRUZ", "VZ");
        ESTADOS.put("YUCATÁN", "YN");
        ESTADOS.put("ZACATECAS", "ZS");
    }
    
    /**
     * Método para obtener un conjunto con los nombres de todos los estados de la República Mexicana.
     * 
     * @return Un conjunto que contiene los nombres de los estados.
     */
    public static Set<String> obtenerNombresEstados() {
        return ESTADOS.keySet();
    }
}
