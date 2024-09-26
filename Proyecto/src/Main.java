import java.util.List;
import java.util.ArrayList;
// en nuestro main solo utilizamos la clase menu para crear el menu como tal
// y luego ejecutarlo
public class Main {
    public static void main(String[] args) {
        
        // Crear una instancia de BolsaTrabajo
        BolsaTrabajo bolsa = new BolsaTrabajo();

        // Cargar los datos desde el archivo CSV
        bolsa.cargarDatos();
        
        // Crear el menú y ejecutarlo
        Menu menu = new Menu(bolsa);
        menu.ejecutarMenu();

        // Guardar datos en archivos CSV al salir del programa
        bolsa.guardarDatos();
        
    }
}
