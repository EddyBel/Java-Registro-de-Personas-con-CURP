/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.registrousuarios;

/**
 * Clase principal que ejecuta la aplicación de registro de usuarios. Esta clase
 * inicializa la interfaz gráfica de usuario (GUI) y la configura para mostrarla
 * al usuario.
 *
 * @author ben_9
 */
public class RegistroUsuarios {

    /**
     * Método principal que lanza la aplicación. Inicializa la interfaz gráfica
     * de usuario (Interfaz), establece su título y la hace visible para el
     * usuario.
     *
     * @param args Argumentos de línea de comandos (no utilizados en este caso)
     */
    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz(); // Crea una nueva instancia de la clase Interfaz
        interfaz.setTitle("Proyecto Analisis y Diseño"); // Establece el título de la ventana
        interfaz.setVisible(true); // Hace visible la interfaz gráfica
    }
}
