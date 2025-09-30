# Práctica Maven MVC con Base de Datos

Este proyecto es una práctica de arquitectura MVC utilizando Java, Maven y conexión a base de datos MySQL.

## Estructura del Proyecto

- `src/main/java/com/uniajc/mvn/` — Código fuente Java organizado en los paquetes `modelo`, `vista` y `controlador`.
- `src/main/resources/` — Recursos adicionales (vacío por defecto).
- `config.properties` — Archivo de configuración para la conexión a la base de datos.
- `pom.xml` — Archivo de configuración de Maven.

## Configuración de la Base de Datos

El proyecto utiliza un archivo `config.properties` para definir los parámetros de conexión a la base de datos MySQL. Este archivo debe estar en la raíz del proyecto y tener el siguiente formato:

```
URL=jdbc:mysql://localhost:3306/practica-mvc
USERNAME=root
PASSWORD=admin
```

- **URL**: Cadena de conexión JDBC a la base de datos MySQL.
- **USERNAME**: Usuario de la base de datos.
- **PASSWORD**: Contraseña del usuario de la base de datos.

> **Importante:** Modifica estos valores según tu entorno y credenciales de MySQL.

## Uso del Archivo `config.properties`

La clase `ConexionDatabase` carga automáticamente el archivo `config.properties` al iniciar la aplicación para obtener los datos de conexión. Si el archivo no existe o los datos son incorrectos, la conexión fallará.

No subas tus credenciales reales a repositorios públicos.

## Ejecución del Proyecto

1. Asegúrate de tener MySQL en ejecución y la base de datos `practica-mvc` creada.
2. Configura el archivo `config.properties` con tus datos.
3. Compila el proyecto con Maven:
   ```sh
   mvn clean package
   ```
4. Ejecuta la aplicación:
   ```sh
   java -cp target/practica-mvn-mvc-bd-1.0-SNAPSHOT.jar com.uniajc.mvn.Main
   ```

## Dependencias

- Java 8+
- Maven
- MySQL
- Driver JDBC de MySQL (incluido en el `pom.xml`)

---

## Documentación de Clases Principales

### `Estudiante` (Modelo)
Representa la entidad estudiante con atributos `nombre` y `edad`. Incluye métodos para insertar y obtener estudiantes desde la base de datos.

### `ControladorEstudiante` (Controlador)
Gestiona la lógica entre el modelo y la vista. Permite agregar y listar estudiantes.

### `VistaEstudiante` (Vista)
Muestra los datos de los estudiantes (implementación básica).

### `ConexionDatabase`
Gestiona la conexión a la base de datos usando los parámetros definidos en `config.properties`.

---

## Guía para Crear Métodos de Actualizar y Eliminar

Para ampliar la funcionalidad y permitir actualizar y eliminar estudiantes, sigue estos pasos:

### 1. En el Modelo (`Estudiante.java`)

#### Método para actualizar:
```java
public static void actualizarEstudiante(String nombreOriginal, Estudiante estudianteActualizado) {
    String sql = "UPDATE estudiante SET nombre = ?, edad = ? WHERE nombre = ?";
    try {
        Connection conexion = ConexionDatabase.getConnection();
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, estudianteActualizado.getNombre());
        ps.setInt(2, estudianteActualizado.getEdad());
        ps.setString(3, nombreOriginal);
        ps.executeUpdate();
    } catch (Exception e) {
        System.out.println("Error al actualizar el estudiante: " + e.getMessage());
    }
}
```

#### Método para eliminar:
```java
public static void eliminarEstudiante(String nombre) {
    String sql = "DELETE FROM estudiante WHERE nombre = ?";
    try {
        Connection conexion = ConexionDatabase.getConnection();
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.executeUpdate();
    } catch (Exception e) {
        System.out.println("Error al eliminar el estudiante: " + e.getMessage());
    }
}
```

### 2. En el Controlador (`ControladorEstudiante.java`)

#### Método para actualizar:
```java
public void actualizarEstudiante(String nombreOriginal, Estudiante estudianteActualizado) {
    Estudiante.actualizarEstudiante(nombreOriginal, estudianteActualizado);
    System.out.println("Estudiante actualizado: " + estudianteActualizado.getNombre());
}
```

#### Método para eliminar:
```java
public void eliminarEstudiante(String nombre) {
    Estudiante.eliminarEstudiante(nombre);
    System.out.println("Estudiante eliminado: " + nombre);
}
```

### 3. Uso en `Main.java`

Puedes llamar estos métodos desde el controlador, por ejemplo:
```java
controlador.actualizarEstudiante("Leonel Messi", new Estudiante("Leo Messi", 39));
controlador.eliminarEstudiante("Leo Messi");
```

---

Para dudas o problemas, contacta al autor o revisa la documentación en el código fuente.
