# Proyecto Bolsa de Trabajo (TERMINAL)

Este proyecto consiste en una bolsa de trabajo automatizada desarrollada como parte de la asignatura de Programación Avanzada en la Pontificia Universidad Católica de Valparaíso. El sistema tiene como objetivo facilitar la conexión entre postulantes y ofertas laborales, mejorando la empleabilidad mediante un proceso de selección automatizado basado en competencias y experiencias.

## Descripción

El sistema de bolsa de trabajo permite la gestión completa de postulantes y trabajos mediante operaciones CRUD (Crear, Leer, Actualizar y Eliminar). Adicionalmente, incluye funcionalidades para comparar competencias entre postulantes y trabajos, asegurando coincidencias precisas y eficientes. Si no se encuentran coincidencias, el sistema notifica que no hay resultados compatibles.

## Funcionalidades

- **Agregar, modificar y eliminar trabajos**: Gestión de las oportunidades laborales en la bolsa de trabajo.
- **Agregar, modificar y eliminar postulantes**: Gestión de los postulantes registrados en el sistema.
- **Comparar competencias**: Función que compara las competencias de los postulantes con las exigencias de los trabajos.
- **Mostrar postulantes y trabajos**: Visualización de la información detallada de los postulantes y trabajos registrados en el sistema.
- **Imprimir postulantes seleccionados para un trabajo específico**: Permite visualizar los postulantes que cumplen con los requisitos de un trabajo seleccionado.

## Estructura del Proyecto

El sistema está diseñado con varias clases que estructuran los datos y las funcionalidades:

- **Clase `Postulante`**: Representa a los individuos que buscan trabajo, incluyendo sus atributos personales y profesionales.
- **Clase `Trabajo`**: Representa las oportunidades laborales, sus requerimientos y descripción.
- **Clase `Competencia`**: Detalla las habilidades específicas de los postulantes o los requisitos de los trabajos.
- **Clase `BolsaTrabajo`**: Gestiona las listas de postulantes y trabajos, facilitando las operaciones de emparejamiento.

## Diseño Conceptual

- **Uso de `HashMap`**: Los postulantes y trabajos se almacenan en colecciones tipo `HashMap`, permitiendo acceso rápido mediante claves únicas (RUT para postulantes y ID para trabajos).
- **Sobrecarga de Métodos**: Implementación de sobrecarga de métodos para la gestión de competencias, añadiendo flexibilidad y optimización en la inserción de datos.

## Menú Interactivo

El sistema cuenta con un menú interactivo que permite a los usuarios gestionar todas las operaciones desde la consola. Las opciones del menú incluyen:

1. Agregar postulante
2. Mostrar postulantes
3. Eliminar postulante
4. Agregar trabajo
5. Mostrar trabajos
6. Eliminar trabajo
7. Comparar competencias
8. Imprimir postulantes seleccionados para un trabajo
9. Salir

## Datos Iniciales

Se han añadido datos iniciales al sistema para facilitar las pruebas:

**Postulantes de ejemplo:**
- Maximiliano Osorio, RUT: 15.548.652-2, Competencias: Java (Avanzado), C (Intermedio), Experiencia: 4 años.
- Monserrath Espinoza, RUT: 19.254.687-6, Competencias: Java (Principiante), Python (Intermedio), Experiencia: 2 años.

**Trabajos de ejemplo:**
- Desarrollo de Página Web, ID: 1, Competencias: HTML (Avanzado), Java (Intermedio), Experiencia mínima: 2 años.
- Desarrollo de Aplicaciones Móviles, ID: 2, Competencias: PHP (Avanzado), Java (Intermedio), Experiencia mínima: 1 año.

## Buenas Prácticas

- **Encapsulamiento**: Los atributos de las clases son privados y se utilizan métodos getter y setter para acceder a ellos, protegiendo la integridad de los datos.
- **Control de Versiones con GitHub**: Se recomienda el uso de commits frecuentes y detallados para documentar el desarrollo del proyecto.

## Requisitos

- Java 8 o superior
- Consola de comandos para la ejecución del programa

## Instrucciones de Ejecución

1. Clonar el repositorio: `git clone <url-del-repositorio>`
2. Navegar al directorio del proyecto: `cd <nombre-del-directorio>`
3. Compilar el proyecto: `javac Main.java`
4. Ejecutar el programa: `java Main`

**Pontificia Universidad Católica de Valparaíso - Facultad de Ingeniería - Escuela de Ingeniería Informática**

Integrantes del proyecto:
- Matías Pardo
- Juan Pablo Pizarro
- Joaquín Saldivia
