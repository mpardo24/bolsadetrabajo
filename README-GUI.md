# Proyecto Bolsa de Trabajo (GUI)

Este proyecto es una bolsa de trabajo automatizada, desarrollada como parte de la asignatura de Programación Avanzada en la Pontificia Universidad Católica de Valparaíso. Ahora con una interfaz gráfica de usuario (GUI) desarrollada en Java, permite gestionar postulantes y trabajos de forma visual y simplificada, facilitando la interacción y el acceso a la información.

## Descripción

El sistema de bolsa de trabajo ofrece una gestión completa de postulantes y ofertas laborales, manteniendo las operaciones CRUD (Crear, Leer, Actualizar y Eliminar) directamente desde una interfaz gráfica amigable. La aplicación realiza el emparejamiento (matching) automático entre postulantes y trabajos basándose en competencias y experiencia, almacenando los datos de forma persistente en archivos CSV y generando reportes en formato de texto.

## Funcionalidades

- **Gestión de trabajos y postulantes con interfaces gráficas**:
  - **Agregar, modificar y eliminar trabajos y postulantes** de manera intuitiva a través de ventanas de diálogo.
  - **Visualizar listas detalladas** de postulantes y trabajos con toda la información relevante.
- **Comparar y emparejar postulantes con trabajos**:
  - Herramienta de emparejamiento que compara las competencias de los postulantes con los requisitos de los trabajos.
  - Posibilidad de realizar el proceso inverso: buscar trabajos para los cuales un postulante cumple con los requisitos.
- **Manejo de Competencias**:
  - Añadir y editar competencias específicas para postulantes y trabajos desde cuadros de diálogo especializados.
- **Generación de reportes**:
  - Genera reportes en un archivo de texto (`reporte.txt`) con detalles de todos los postulantes y trabajos registrados.
- **Persistencia de datos**:
  - Los datos se almacenan de manera persistente en archivos CSV (`postulantes.csv` y `trabajos.csv`), permitiendo que la información se conserve entre sesiones.

## Estructura del Proyecto

El proyecto está compuesto por varias clases que organizan la lógica de la aplicación y la interacción con la GUI:

- **Clases de Frame para la GUI**:
  - Cada operación principal tiene su propia ventana (frame) o cuadro de diálogo, como `AgregarPostulanteFrame`, `EditarPostulanteFrame`, `MostrarTrabajosFrame`, entre otros.
  - La interfaz gráfica permite realizar operaciones CRUD y manejar competencias de forma sencilla.
- **Manejo de datos mediante clases modelo**:
  - **Clase `Postulante`**: Modela a los candidatos, con sus atributos personales, competencias y experiencia.
  - **Clase `Trabajo`**: Modela las ofertas laborales, con sus requisitos de experiencia y competencias necesarias.
  - **Clase `Competencia`**: Define habilidades específicas y sus niveles, tanto para postulantes como para trabajos.
- **Gestión centralizada con `BolsaTrabajo`**:
  - La clase `BolsaTrabajo` administra listas de trabajos y postulantes, permitiendo realizar todas las operaciones de acceso y manipulación de datos, así como la generación de reportes.

## Almacenamiento y Persistencia

- **Almacenamiento en CSV**:
  - Los datos de postulantes y trabajos se almacenan en archivos CSV para facilitar su lectura y escritura. Esto permite que la información se mantenga persistente entre sesiones de uso de la aplicación.
- **Reporte en formato de texto**:
  - Un reporte detallado con información sobre todos los postulantes y trabajos se genera en un archivo de texto (`reporte.txt`), brindando un resumen claro de los datos almacenados en el sistema.

## Diseño Conceptual

- **Uso de `HashMap`**:
  - Los datos de postulantes y trabajos se almacenan en colecciones de tipo `HashMap` para un acceso rápido y eficiente. La clave para los postulantes es el RUT, y para los trabajos, el ID.
- **Interfaz gráfica con `JFrame` y `JDialog`**:
  - La interfaz está implementada usando `JFrame` y `JDialog`, brindando ventanas y cuadros de diálogo intuitivos para cada operación de la aplicación.
- **Manejo de competencias y experiencia**:
  - La aplicación compara competencias y experiencia para emparejar postulantes con trabajos, asegurando que se cumplan los requisitos mínimos.

## Requisitos

- Java 8 o superior
- Sistema operativo compatible con `Swing` (cualquier versión de Windows, MacOS, o Linux con entorno de escritorio)

## Instrucciones de Ejecución

1. Clonar el repositorio:
    ```bash
    git clone <url-del-repositorio>
    ```
2. Navegar al directorio del proyecto:
    ```bash
    cd <nombre-del-directorio>
    ```
3. Compilar el proyecto:
    ```bash
    javac Ventanas/MainVentana.java
    ```
4. Ejecutar la aplicación:
    ```bash
    java Ventanas.MainVentana
    ```

## Buenas Prácticas y Control de Versiones

- **Encapsulamiento y modularización**:
  - La aplicación sigue principios de encapsulamiento, manteniendo atributos privados y exponiendo solo lo necesario mediante métodos getter y setter.
  - La lógica de negocios y la interfaz gráfica están modularizadas para mantener un código limpio y comprensible.
- **Control de versiones con GitHub**:
  - Cada grupo de cambios se realiza con commits detallados, indicando nuevas funcionalidades, refactorizaciones o correcciones de errores.

## Próximos Pasos

- **Integración de nuevos frames**:
  - Se añadirán nuevas ventanas para mejorar la usabilidad y administración de datos.
- **Optimización de manejo de datos**:
  - Se planean mejoras en la lectura y escritura de archivos CSV para manejar mayores volúmenes de datos de manera eficiente.
- **Mejora del emparejamiento**:
  - Futuras actualizaciones incluirán algoritmos de matching más avanzados para mejorar la precisión de las coincidencias.

---

**Pontificia Universidad Católica de Valparaíso - Facultad de Ingeniería - Escuela de Ingeniería Informática**

Integrantes del proyecto:
- Matías Pardo
- Juan Pablo Pizarro
- Joaquín Saldivia
