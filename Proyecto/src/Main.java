import java.util.List;
import java.util.ArrayList;
// en nuestro main solo utilizamos la clase menu para crear el menu como tal
// y luego ejecutarlo
public class Main {
    public static void main(String[] args) {


        // Crear una instancia de BolsaTrabajo
        BolsaTrabajo bolsa = new BolsaTrabajo();

        // Casos de prueba
        // Crear competencias para los postulantes y trabajos
        List<Competencia> competenciasPostulante1 = new ArrayList<>();
        competenciasPostulante1.add(new Competencia("Java", "experto"));
        competenciasPostulante1.add(new Competencia("C", "intermedio"));

        List<Competencia> competenciasPostulante2 = new ArrayList<>();
        competenciasPostulante2.add(new Competencia("Java", "principiante"));
        competenciasPostulante2.add(new Competencia("Python", "intermedio"));

        List<Competencia> competenciasTrabajo1 = new ArrayList<>();
        competenciasTrabajo1.add(new Competencia("HTML", "experto"));
        competenciasTrabajo1.add(new Competencia("Java", "intermedio"));

        List<Competencia> competenciasTrabajo2 = new ArrayList<>();
        competenciasTrabajo2.add(new Competencia("PHP", "experto"));
        competenciasTrabajo2.add(new Competencia("Java", "intermedio"));

        // Crear postulantes
        Postulante persona1 = new Postulante("Maximiliano Osorio", "15.548.652-2", "maxioso@gmail.com", competenciasPostulante1, 4);
        Postulante persona2 = new Postulante("Monserrath Espinoza", "19.254.687-6", "Monseespi12@gmail.com", competenciasPostulante2, 2);

        // Crear trabajos
        Trabajo trabajo1 = new Trabajo("Desarrollo pagina web", "Desarrollo de paginas web para empresas que están surgiendo.", competenciasTrabajo1, 2, 1);
        Trabajo trabajo2 = new Trabajo("Desarrollo aplicaciones moviles", "Desarrollo de aplicaciones para utilizarse en la playstore o appstore.", competenciasTrabajo2, 1, 2);

        // Agregar postulantes y trabajos a la bolsa de trabajo
        bolsa.agregarPostulante(persona1);
        bolsa.agregarPostulante(persona2);
        bolsa.agregarTrabajo(trabajo1);
        bolsa.agregarTrabajo(trabajo2);

        Menu menu = new Menu(bolsa);
        menu.ejecutarMenu();
    }
}
