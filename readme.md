# Programa de Verificación y Generación de Cadenas para un Autómata

Este programa ha sido desarrollado como parte de la asignatura de **Compiladores** de la **Universidad de Alcalá de Henares**

## Descripción del Programa

El objetivo principal del programa es permitir la verificación y generación de cadenas válidas para un autómata finito determinista (AFD). El autómata debe estar definido en un archivo llamado **`Automata.txt`**, el cual contiene la configuración del mismo. El programa ofrece varias funcionalidades para interactuar con el autómata, como la verificación de pertenencia de una cadena y la generación de cadenas válidas.

## Funcionalidades

### 1. Verificar si una cadena pertenece al autómata

El usuario puede introducir una cadena de caracteres, y el programa comprobará si dicha cadena es aceptada por el autómata configurado en el archivo **`Automata.txt`**.

- **Proceso**:
    - El usuario introduce una cadena.
    - El programa evalúa la cadena utilizando el AFD.
    - Informa si la cadena es válida o no.
    - Se puede seguir verificando otras cadenas hasta que el usuario decida volver al menú principal.

### 2. Generar todas las posibles cadenas válidas

El programa permite generar todas las cadenas válidas según el autómata, bajo ciertas restricciones dadas por el usuario (*número máximo de cadenas* y *longitud máxima de las mismas*).

- **Proceso**:
    - El usuario define el número máximo de cadenas a generar y la longitud máxima de estas cadenas.
    - El programa genera todas las posibles combinaciones válidas que cumplen con las restricciones establecidas.
    - Las cadenas válidas se muestran por pantalla.
    - Opcionalmente, el usuario puede exportar las cadenas generadas a un archivo de texto llamado **`cadenasValidas.txt`**.

### 3. Salir

Finaliza la ejecución del programa.

## Instrucciones de Uso

1. **Automata.txt**: Para que el programa funcione correctamente, el archivo **`Automata.txt`** debe contener la descripción del autómata. Este archivo debe estar ubicado en la misma carpeta que el ejecutable y debe seguir el formato adecuado para ser leído por el programa.

2. **Ejecución del Programa**:
    - Al iniciar el programa, se mostrará un mensaje de bienvenida junto con las opciones disponibles.
    - El usuario puede seleccionar una de las opciones disponibles para interactuar con el autómata.
    - En la opción de generación de cadenas, si se elige exportar, el archivo **`cadenasValidas.txt`** se creará o sobrescribirá automáticamente.

3. **Salida del Programa**: Para salir del programa, el usuario debe seleccionar la opción `3. Salir` en el menú principal.

## Dependencias

El programa se ejecuta utilizando las siguientes clases:

- **`LeerAutomata`**: Se encarga de leer el archivo **`Automata.txt`** y cargar las configuraciones del autómata.
- **`MaquinaEstados`**: Implementa la lógica del autómata y la verificación de cadenas.
- **`GeneradorDeCadenas`**: Genera cadenas válidas en base al autómata.

## Archivos generados

- **`cadenasValidas.txt`**: Archivo que contiene todas las cadenas válidas generadas por el autómata, si el usuario selecciona la opción de exportarlas.

## Notas adicionales

- Asegúrate de que el archivo **`Automata.txt`** siga el formato correcto para que el programa lo procese adecuadamente.
- El archivo **`Automata.txt`** debe contener:
  - Estados del autómata
  - Símbolos del autómata
  - Estdos iniciales y finales del autómata
  - Matriz de incidencias del automata
- El archivo **`cadenasValidas.txt`** se sobrescribirá si se genera de nuevo, por lo que se recomienda realizar una copia de seguridad si es necesario mantener los resultados anteriores.
