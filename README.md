# Generador de CURP y Registro en Base de Datos

Este proyecto es una aplicación de escritorio desarrollada en Java utilizando JFrame que permite generar la CURP (Clave Única de Registro de Población) para personas en México y registrar dicha información en una base de datos MySQL. A continuación, se explicará la lógica detrás del proyecto y cómo configurarlo para su correcto funcionamiento.

[Video Preview del Proyecto](./videos/captura.webm)
![Vista Preview del Proyecto](./videos/captura.gif)

## Estructura del Proyecto

El proyecto está organizado en varios archivos Java, cada uno con una función específica dentro del sistema de registro de usuarios. Esta estructura modular facilita la mantenibilidad y reutilización del código. A continuación se describe cada archivo y su propósito en la aplicación.

1. [**Constantes.java**](./src/main/java/com/mycompany/registrousuarios/Constantes.java)

   - **Función**: Este archivo contiene constantes reutilizables, como los nombres de los estados o entidades federativas, que pueden usarse en toda la aplicación. Al centralizar estas constantes, se evita la redundancia de código y se facilita la actualización de valores comunes en un solo lugar.
   - **Uso**: Principalmente útil para estandarizar valores que se repiten en la interfaz o en las operaciones con la base de datos.

2. [**CurpGenerador.java**](./src/main/java/com/mycompany/registrousuarios/CurpGenerador.java)

   - **Función**: Este archivo define la clase para generar el CURP (Clave Única de Registro de Población), que es un identificador único en México. Implementa los métodos necesarios para construir un CURP válido a partir de los datos de una persona, como nombre, apellidos, fecha de nacimiento, entidad federativa y género.
   - **Uso**: La lógica para generar CURP se aísla en esta clase, lo que facilita su llamada y modificación sin afectar otros componentes del sistema.

3. [**Interfaz.java**](./src/main/java/com/mycompany/registrousuarios/Interfaz.java)

   - **Función**: Este es el núcleo de la lógica de la aplicación y la interfaz gráfica de usuario (GUI). `Interfaz.java` extiende `JFrame` y utiliza componentes de Swing para construir la ventana principal de la aplicación, incluyendo formularios, botones, y otros elementos interactivos. También es responsable de manejar la lógica de eventos (como botones para agregar o eliminar registros) y realizar las operaciones correspondientes en la base de datos.
   - **Uso**: Centraliza la interacción del usuario con el sistema y conecta las acciones de la interfaz con las operaciones definidas en `MYSQL.java`.

4. [**MYSQL.java**](./src/main/java/com/mycompany/registrousuarios/MYSQL.java)

   - **Función**: Esta clase encapsula las operaciones de conexión y manipulación de datos en la base de datos MySQL. Proporciona métodos para conectar y desconectar la base de datos, así como para insertar, actualizar, eliminar y consultar registros en la tabla `personas`.
   - **Uso**: `MYSQL.java` actúa como un enlace entre la aplicación y la base de datos, facilitando la interacción con los datos sin que el resto de la aplicación necesite manejar directamente la lógica de conexión a MySQL.

5. [**RegistroUsuario.java**](./src/main/java/com/mycompany/registrousuarios/RegistroUsuarios.java)
   - **Función**: Este archivo contiene la clase principal de la aplicación y su método `main`. Su principal propósito es inicializar la interfaz gráfica llamando a `Interfaz.java`, lo que permite iniciar la aplicación.
   - **Uso**: Funciona como el punto de entrada de la aplicación, organizando y lanzando la ventana principal al ejecutar la aplicación.

---

## Requisitos Previos

### Instalación de JDBC y Configuración del [`pom.xml`](./pom.xml)

Este proyecto utiliza Maven para gestionar dependencias. El archivo [`pom.xml`](./pom.xml) es el archivo de configuración de Maven, donde se especifican las dependencias externas necesarias para el proyecto. Es importante tener instalada la dependencia de JDBC para conectar la aplicación a la base de datos MySQL.

#### Paso 1: Configurar el archivo [`pom.xml`](./pom.xml)

Abre el archivo [`pom.xml`](./pom.xml) y añade las siguientes dependencias en la sección `<dependencies>`:

```xml
<dependencies>
    <!-- Dependencia para JCalendar (OPCIONAL) -->
    <dependency>
        <groupId>com.toedter</groupId>
        <artifactId>jcalendar</artifactId>
        <version>1.4</version>
    </dependency>

    <!-- Dependencia para MySQL JDBC Driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version> <!-- Asegúrate de que sea la versión actual -->
    </dependency>
</dependencies>
```

## ¿Qué es pom.xml?

El archivo pom.xml es utilizado por Maven para manejar y descargar automáticamente las dependencias del proyecto. Esto permite mantener actualizado el proyecto sin necesidad de gestionar manualmente cada biblioteca utilizada.

### Dependencia Opcional: JCalendar

La biblioteca JCalendar es una dependencia opcional que permite incorporar un botón para desplegar un calendario, facilitando la selección de fechas en la aplicación. Esto puede mejorar la experiencia del usuario al evitar que ingrese manualmente la fecha.

---

## Lógica de generacion de CURP

Generación de CURP: [`CurpGenerador.java`](./src/main/java/com/mycompany/registrousuarios/CurpGenerador.java)
El archivo [`CurpGenerador.java`](./src/main/java/com/mycompany/registrousuarios/CurpGenerador.java) contiene la lógica para generar la CURP a partir de datos personales, incluyendo nombre, apellidos, fecha de nacimiento, sexo y estado de nacimiento.

### Descripción del Algoritmo

Inicialización de Estados: Se utiliza un mapa (Map<String, String>) para asociar los nombres de los estados con sus abreviaciones correspondientes.

Limpieza y Formato de Texto: Los métodos limpiarTexto, primeraVocalInterna y primeraConsonanteInterna se encargan de estandarizar el texto de entrada, buscar vocales internas y consonantes internas en los nombres y apellidos.

Generación de la CURP: El método generarCURP utiliza las siguientes reglas:

Extrae la primera letra y vocal interna del apellido paterno.
Extrae la primera letra del apellido materno (o "X" si está vacío).
Extrae la primera letra del nombre.
Formatea la fecha de nacimiento en YYMMDD.
Asigna la letra "H" o "M" para el sexo.
Busca la abreviación del estado.
Extrae las primeras consonantes internas de los nombres y apellidos.
Genera un número aleatorio para la homoclave.

### Ejemplo de Uso

```java
CurpGenerador generador = new CurpGenerador();
String curp = generador.generarCURP("Juan", "Pérez", "Lopez", "1990-05-23", "CIUDAD DE MÉXICO", "H");
System.out.println("CURP generada: " + curp);
```

Este método devuelve una CURP basada en los datos proporcionados. Puedes verificar en la base de datos que los datos se han guardado correctamente.

---

## Conexión a la Base de Datos MySQL - Clase [`MYSQL.java`](./src/main/java/com/mycompany/registrousuarios/MYSQL.java)

La clase [`MYSQL`](./src/main/java/com/mycompany/registrousuarios/MYSQL.java) maneja la conexión, inserción, consulta, actualización y eliminación de registros en una base de datos MySQL para el sistema de registro de usuarios. A continuación se detalla la estructura de la clase y la funcionalidad de cada método clave.

## 1. Variables de Conexión a MySQL

```java
private static final String URL = "jdbc:mysql://localhost:3306/registros";
private static final String USER = "root";
private static final String PASSWORD = "54628";
```

- **URL**: La URL de conexión sigue el formato `jdbc:mysql://[servidor]:[puerto]/[base_datos]`:

  - **jdbc:mysql**: Especifica el protocolo JDBC para conexión con MySQL.
  - **localhost**: Indica que la base de datos está en la misma máquina que la aplicación.
  - **3306**: Puerto predeterminado de MySQL.
  - **registros**: Nombre de la base de datos que contiene la tabla `personas`.

- **USER y PASSWORD**: Usuario y contraseña de MySQL para autenticarse en la base de datos.

## 2. Conexión a la Base de Datos

### Método `conectar()`

```java
public Connection conectar() {
    Connection conexion = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Conexión exitosa a la base de datos.");
    } catch (SQLException e) {
        System.out.println("Error de conexión: " + e.getMessage());
    } catch (ClassNotFoundException e) {
        System.out.println("Driver no encontrado: " + e.getMessage());
    }
    return conexion;
}
```

- **Descripción**: Establece la conexión a MySQL cargando el controlador JDBC de MySQL y llamando a `DriverManager.getConnection`.
- **Manejo de errores**: Si falla la conexión o el driver no se encuentra, se imprime un mensaje de error.

## 3. Cierre de la Conexión

### Método `cerrarConexion(Connection conexion)`

```java
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
```

- **Descripción**: Cierra la conexión establecida si está abierta. Se recomienda siempre cerrar la conexión para liberar recursos.
- **Manejo de errores**: Si ocurre un error al cerrar, se imprime un mensaje de error.

## 4. Operaciones CRUD (Create, Read, Update, Delete)

### 4.1 Inserción de Datos

#### Método `insertarValores`

```java
public void insertarValores(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String entidadFederativa, String genero, String curp) throws SQLException
```

- **Descripción**: Inserta un nuevo registro en la tabla `personas`.
- **Uso de `PreparedStatement`**: Evita la inyección SQL al usar parámetros.
- **Parámetros**:
  - `nombre`, `apellidoPaterno`, `apellidoMaterno`, `fechaNacimiento`, `entidadFederativa`, `genero`, `curp`: Datos personales de la persona a registrar.

### 4.2 Consulta de Datos

#### Método `obtenerPersonas`

```java
public List<String[]> obtenerPersonas()
```

- **Descripción**: Recupera todos los registros de la tabla `personas` y los devuelve como una lista de arreglos de `String`.
- **Manejo de Resultados**: Cada registro es almacenado en un arreglo de `String` y agregado a la lista.

#### Método `obtenerPersonaPorCURP`

```java
public String[] obtenerPersonaPorCURP(String curp)
```

- **Descripción**: Consulta un registro específico en la tabla `personas` usando el CURP como filtro.
- **Parámetro**:
  - `curp`: CURP de la persona que se desea consultar.
- **Retorno**: Un arreglo de `String` con los datos de la persona, o `null` si no existe.

### 4.3 Actualización de Datos

#### Método `actualizarPersonaPorCURP`

```java
public void actualizarPersonaPorCURP(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String entidadFederativa, String genero, String curp) throws SQLException
```

- **Descripción**: Actualiza los datos de una persona existente en la tabla `personas`, usando el CURP para identificar el registro.
- **Parámetros**:
  - Nuevos datos personales de la persona.
  - `curp`: CURP del registro que se desea actualizar.

#### Método `actualizarCurpPorCURP`

```java
public void actualizarCurpPorCURP(String curpAnterior, String nuevoCurp) throws SQLException
```

- **Descripción**: Cambia el CURP de una persona.
- **Parámetros**:
  - `curpAnterior`: CURP actual de la persona.
  - `nuevoCurp`: Nuevo CURP a asignar.

### 4.4 Eliminación de Datos

#### Método `eliminarPersonaPorCURP`

```java
public boolean eliminarPersonaPorCURP(String curp)
```

- **Descripción**: Elimina un registro de la tabla `personas` identificado por el CURP.
- **Parámetro**:
  - `curp`: CURP del registro que se desea eliminar.
- **Retorno**: `true` si se eliminó el registro, `false` si no se encontró o ocurrió un error.

### Configuración de la Base de Datos MySQL

Para utilizar esta clase de conexión ([`MYSQL.java`](./src/main/java/com/mycompany/registrousuarios/MYSQL.java)), es necesario contar con una base de datos llamada `registros`, y dentro de ella, una tabla denominada `personas`. Sin embargo, puedes modificar los valores de conexión (`URL`, `USER`, `PASSWORD`) para adaptarlos a tus necesidades, en caso de que utilices una configuración diferente. La estructura actual de la base de datos y de la tabla es la siguiente:

1. **Creación de la Base de Datos**:  
   Para crear la base de datos `registros`, ejecuta el siguiente comando en tu cliente SQL:

   ```sql
   CREATE DATABASE registros;
   ```

2. **Creación de la Tabla `personas`**:  
   Dentro de la base de datos `registros`, se debe crear la tabla `personas` con las siguientes columnas. En este ejemplo, la columna `curp` se define como `UNIQUE` para garantizar que cada registro tenga un valor único.

   ```sql
   USE registros;

   CREATE TABLE personas (
       id INT NOT NULL AUTO_INCREMENT,
       nombre VARCHAR(100) NOT NULL,
       apellidoPaterno VARCHAR(100) NOT NULL,
       apellidoMaterno VARCHAR(100) NOT NULL,
       fechaNacimiento VARCHAR(50) NOT NULL,
       entidadFederativa VARCHAR(100) NOT NULL,
       genero VARCHAR(50) NOT NULL,
       curp VARCHAR(100) NOT NULL UNIQUE,
       PRIMARY KEY (id)
   );
   ```

   **Descripción de cada columna**:

   - `id`: Identificador único de cada registro, con auto-incremento.
   - `nombre`: Nombre de la persona (máximo 100 caracteres).
   - `apellidoPaterno`: Apellido paterno de la persona (máximo 100 caracteres).
   - `apellidoMaterno`: Apellido materno de la persona (máximo 100 caracteres).
   - `fechaNacimiento`: Fecha de nacimiento en formato de texto (puede ser YYYY-MM-DD).
   - `entidadFederativa`: Estado o región de la persona (máximo 100 caracteres).
   - `genero`: Género de la persona (por ejemplo, 'H' para hombre, 'M' para mujer).
   - `curp`: Clave Única de Registro de Población, valor único para cada persona.

Este es el esquema básico para ejecutar el código de conexión y realizar las operaciones CRUD con la base de datos.

---

### Interfaz con el Archivo [`Interfaz.java`](./src/main/java/com/mycompany/registrousuarios/Interfaz.java)

Este archivo implementa la interfaz gráfica de usuario (GUI) utilizando JFrame y otras librerías de Swing, proporcionando una estructura base para interactuar visualmente con el sistema de registro de usuarios.

#### Estructura General

- **Bibliotecas**: Se importan librerías de Swing y AWT (Abstract Window Toolkit) para construir y gestionar los elementos visuales de la interfaz.
- **Clase Principal**: La clase `Interfaz` extiende `JFrame`, permitiendo que la ventana principal herede las propiedades de una ventana estándar en Java.
- **Componentes de Interfaz**:
  - **Botones y Campos de Texto**: Los elementos como botones (`JButton`), etiquetas (`JLabel`), y campos de entrada (`JTextField`) están dispuestos en el JFrame.
  - **Layout**: Se utiliza un administrador de diseño, como `GridLayout` o `BorderLayout`, para organizar los elementos de la interfaz. Esto facilita la distribución de componentes de manera flexible y visualmente agradable.

#### Funcionalidad Base

En el archivo, se definen métodos para capturar eventos, como clicks de botones, lo que permite realizar operaciones sobre la base de datos o actualizar el contenido de la interfaz. Estos eventos pueden estar vinculados a acciones de inserción, eliminación o actualización en la base de datos 'registros'.
