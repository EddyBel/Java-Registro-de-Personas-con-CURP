package com.mycompany.registrousuarios;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 * Clase que gestiona las operaciones de conexión, inserción, consulta,
 * actualización y eliminación de registros en la base de datos MySQL para el
 * sistema de registro de usuarios. Los métodos aquí permiten manipular la tabla
 * 'personas' en la base de datos 'registros'.
 */
public class MYSQL {

    // CONSTANTES DE LA CONEXIÓN A MYSQL
    // URL de conexión, usuario y contraseña de la base de datos
    // 1. jdbc:mysql: Indica que se utilizará el protocolo JDBC (Java Database Connectivity) para conectarse a una base de datos MySQL.
    // 2. localhost: El nombre del host o la dirección del servidor donde está ejecutándose la base de datos MySQL. "localhost" significa que la base de datos está en la misma máquina que el programa.
    // 3. 3306: Es el puerto por defecto en el que MySQL escucha las conexiones entrantes. Si se utiliza un puerto diferente, este valor debe ser modificado.
    // 4. registros: Es el nombre de la base de datos a la que se desea conectar. En este caso, la base de datos llamada "registros" es la que contiene la tabla "personas" y otros datos relacionados.
    private static final String URL = "jdbc:mysql://localhost:3306/registros";
    private static final String USER = "root"; // Usuario de la base de datos
    private static final String PASSWORD = "54628"; // Contraseña de la base de datos

    /**
     * Establece la conexión con la base de datos MySQL.
     *
     * @return La conexión a la base de datos o null si ocurre un error.
     */
    public Connection conectar() {
        Connection conexion = null;
        try {
            // Cargamos el driver de MySQL, necesario para establecer la conexión.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecemos la conexión usando los parámetros definidos anteriormente.
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado: " + e.getMessage());
        }
        return conexion;
    }

    /**
     * Cierra la conexión a la base de datos si está abierta.
     *
     * @param conexion La conexión que se desea cerrar.
     */
    public void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    /**
     * Inserta un nuevo registro en la tabla 'personas' de la base de datos.
     *
     * @param nombre Nombre de la persona.
     * @param apellidoPaterno Apellido paterno de la persona.
     * @param apellidoMaterno Apellido materno de la persona.
     * @param fechaNacimiento Fecha de nacimiento de la persona (formato
     * YYYY-MM-DD).
     * @param entidadFederativa Entidad federativa de la persona.
     * @param genero Género de la persona (H para hombre, M para mujer).
     * @param curp CURP de la persona.
     * @throws SQLException Si ocurre un error al realizar la operación.
     */
    public void insertarValores(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String entidadFederativa, String genero, String curp) throws SQLException {
        Connection conexion = conectar();

        if (conexion != null) {
            String sql = "INSERT INTO registros.personas (nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, entidadFederativa, genero, curp) VALUES(?, ?, ?, ?, ?, ?, ?)";

            // Usamos un PreparedStatement para prevenir inyecciones SQL.
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                // Establecemos los valores a insertar
                statement.setString(1, nombre);
                statement.setString(2, apellidoPaterno);
                statement.setString(3, apellidoMaterno);
                statement.setString(4, fechaNacimiento);
                statement.setString(5, entidadFederativa);
                statement.setString(6, genero);
                statement.setString(7, curp);

                // Ejecutamos la inserción
                int filasInsertadas = statement.executeUpdate();
                if (filasInsertadas > 0) {
                    System.out.println("Se insertó correctamente el registro en la tabla personas.");
                }

            } catch (SQLException e) {
                System.out.println("Error al insertar registro: " + e.getMessage());
            } finally {
                cerrarConexion(conexion);
            }
        }
    }

    /**
     * Obtiene todos los registros de la tabla 'personas'.
     *
     * @return Una lista con los registros obtenidos, cada uno representado como
     * un arreglo de strings.
     */
    public List<String[]> obtenerPersonas() {
        List<String[]> personas = new ArrayList<>();
        Connection conexion = conectar();
        if (conexion != null) {
            String sql = "SELECT * FROM registros.personas";
            try (PreparedStatement statement = conexion.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

                // Iteramos sobre los resultados y agregamos cada registro a la lista
                while (resultSet.next()) {
                    String nombre = resultSet.getString("nombre");
                    String apellidoPaterno = resultSet.getString("apellidoPaterno");
                    String apellidoMaterno = resultSet.getString("apellidoMaterno");
                    String fechaNacimiento = resultSet.getString("fechaNacimiento");
                    String entidadFederativa = resultSet.getString("entidadFederativa");
                    String genero = resultSet.getString("genero");
                    String curp = resultSet.getString("curp");

                    // Agregamos los datos de cada persona a la lista
                    String[] persona = new String[]{nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, entidadFederativa, genero, curp};
                    personas.add(persona);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener los registros: " + e.getMessage());
            } finally {
                cerrarConexion(conexion);
            }
        }
        return personas;
    }

    /**
     * Obtiene un registro específico de la tabla 'personas' filtrando por CURP.
     *
     * @param curp CURP de la persona que se desea obtener.
     * @return Un arreglo de strings con los datos de la persona, o null si no
     * se encuentra.
     */
    public String[] obtenerPersonaPorCURP(String curp) {
        String[] persona = null;
        Connection conexion = conectar();

        if (conexion != null) {
            String sql = "SELECT * FROM registros.personas WHERE curp = ?";

            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                // Establecemos el parámetro CURP
                statement.setString(1, curp);

                // Ejecutamos la consulta y procesamos los resultados
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String nombre = resultSet.getString("nombre");
                        String apellidoPaterno = resultSet.getString("apellidoPaterno");
                        String apellidoMaterno = resultSet.getString("apellidoMaterno");
                        String fechaNacimiento = resultSet.getString("fechaNacimiento");
                        String entidadFederativa = resultSet.getString("entidadFederativa");
                        String genero = resultSet.getString("genero");

                        // Creamos el arreglo con los datos obtenidos
                        persona = new String[]{nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, entidadFederativa, genero, curp};
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener el registro: " + e.getMessage());
            } finally {
                cerrarConexion(conexion);
            }
        }
        return persona;
    }

    /**
     * Elimina un registro de la tabla 'personas' filtrando por CURP.
     *
     * @param curp CURP de la persona que se desea eliminar.
     * @return true si el registro fue eliminado, false si no se encontró o hubo
     * un error.
     */
    public boolean eliminarPersonaPorCURP(String curp) {
        boolean eliminado = false;
        Connection conexion = conectar();

        if (conexion != null) {
            String sql = "DELETE FROM registros.personas WHERE curp = ?";

            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                // Establecemos el parámetro CURP
                statement.setString(1, curp);

                // Ejecutamos la consulta
                int filasEliminadas = statement.executeUpdate();
                if (filasEliminadas > 0) {
                    eliminado = true;
                    System.out.println("Persona con CURP " + curp + " eliminada correctamente.");
                } else {
                    System.out.println("No se encontró ninguna persona con CURP " + curp);
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar el registro: " + e.getMessage());
            } finally {
                cerrarConexion(conexion);
            }
        }

        return eliminado;
    }

    /**
     * Actualiza los datos de una persona en la tabla 'personas' filtrando por
     * CURP.
     *
     * @param nombre Nombre actualizado de la persona.
     * @param apellidoPaterno Apellido paterno actualizado de la persona.
     * @param apellidoMaterno Apellido materno actualizado de la persona.
     * @param fechaNacimiento Fecha de nacimiento actualizada de la persona.
     * @param entidadFederativa Entidad federativa actualizada de la persona.
     * @param genero Género actualizado de la persona.
     * @param curp CURP de la persona a actualizar.
     * @throws SQLException Si ocurre un error durante la actualización.
     */
    public void actualizarPersonaPorCURP(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String entidadFederativa, String genero, String curp) throws SQLException {
        Connection conexion = conectar();

        if (conexion != null) {
            String sql = "UPDATE registros.personas SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, fechaNacimiento = ?, entidadFederativa = ?, genero = ? WHERE curp = ?";

            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setString(2, apellidoPaterno);
                statement.setString(3, apellidoMaterno);
                statement.setString(4, fechaNacimiento);
                statement.setString(5, entidadFederativa);
                statement.setString(6, genero);
                statement.setString(7, curp);

                int filasActualizadas = statement.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Se actualizó correctamente el registro con CURP " + curp);
                } else {
                    System.out.println("No se encontró ninguna persona con CURP " + curp);
                }

            } catch (SQLException e) {
                System.out.println("Error al actualizar el registro: " + e.getMessage());
            } finally {
                cerrarConexion(conexion);
            }
        }
    }

    /**
     * Actualiza el CURP de una persona filtrando por el CURP anterior.
     *
     * @param curpAnterior CURP antiguo de la persona.
     * @param nuevoCurp Nuevo CURP de la persona.
     * @throws SQLException Si ocurre un error durante la actualización.
     */
    public void actualizarCurpPorCURP(String curpAnterior, String nuevoCurp) throws SQLException {
        Connection conexion = conectar();

        if (conexion != null) {
            // Verificamos si existe la persona con el CURP anterior
            String verificarSQL = "SELECT COUNT(*) FROM registros.personas WHERE curp = ?";

            try (PreparedStatement statement = conexion.prepareStatement(verificarSQL)) {
                statement.setString(1, curpAnterior);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    // Si la persona existe, actualizamos el CURP
                    String updateSQL = "UPDATE registros.personas SET curp = ? WHERE curp = ?";

                    try (PreparedStatement updateStatement = conexion.prepareStatement(updateSQL)) {
                        updateStatement.setString(1, nuevoCurp);
                        updateStatement.setString(2, curpAnterior);

                        int filasActualizadas = updateStatement.executeUpdate();
                        if (filasActualizadas > 0) {
                            System.out.println("Se actualizó correctamente el CURP de " + curpAnterior + " a " + nuevoCurp);
                        } else {
                            System.out.println("No se encontró ninguna persona con CURP " + curpAnterior);
                        }
                    }
                } else {
                    System.out.println("No se encontró ninguna persona con CURP " + curpAnterior);
                }
            } catch (SQLException e) {
                System.out.println("Error al verificar o actualizar el CURP: " + e.getMessage());
            } finally {
                cerrarConexion(conexion);
            }
        }
    }
}
