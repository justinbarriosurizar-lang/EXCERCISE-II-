public class Mascota {
    private String nombre;
    private String especie;
    private int edad;
    private double[] controlPeso; // Arreglo para almacenar máximo 10 pesos
    private int contador;         // Contador de registros válidos realizados

    public Mascota(String nombre, String especie, int edad) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.controlPeso = new double[10];
        this.contador = 0;
    }

    // 1. Agregar nuevo registro de peso
    public boolean agregarControl(double peso) {
        if (peso <= 0 || contador >= 10) {
            return false;
        }
        controlPeso[contador] = peso;
        contador++;
        return true;
    }

    // 2. Imprimir todo el historial de pesos registrados
    public void mostrarHistorial() {
        if (contador == 0) {
            System.out.println("No hay controles de peso registrados.");
            return;
        }
        for (int i = 0; i < contador; i++) {
            System.out.println("Control #" + (i + 1) + ": " + controlPeso[i] + " kg");
        }
    }

    // 3. Consultar e imprimir un control específico
    public void consultarControl(int numeroControl) {
        int indice = numeroControl - 1;
        if (indice >= 0 && indice < contador) {
            System.out.println("Control #" + numeroControl + ": " + controlPeso[indice] + " kg");
        } else {
            System.out.println("Error: Numero de control invalido o no registrado aun.");
        }
    }

    // 4. Modificar peso de un control existente
    public boolean modificarControl(int numeroControl, double nuevoPeso) {
        int indice = numeroControl - 1;
        if (indice >= 0 && indice < contador && nuevoPeso > 0) {
            controlPeso[indice] = nuevoPeso;
            return true;
        }
        return false;
    }

    // 5. Calcular promedio de pesos
    public double calcularPromedio() {
        if (contador == 0) return 0.0;
        double suma = 0;
        for (int i = 0; i < contador; i++) {
            suma += controlPeso[i];
        }
        return suma / contador;
    }

    // 6. Obtener peso mayor
    public double obtenerPesoMayor() {
        if (contador == 0) return 0.0;
        double mayor = controlPeso[0];
        for (int i = 1; i < contador; i++) {
            if (controlPeso[i] > mayor) {
                mayor = controlPeso[i];
            }
        }
        return mayor;
    }

    // 7. Obtener peso menor
    public double obtenerPesoMenor() {
        if (contador == 0) return 0.0;
        double menor = controlPeso[0];
        for (int i = 1; i < contador; i++) {
            if (controlPeso[i] < menor) {
                menor = controlPeso[i];
            }
        }
        return menor;
    }

    // 8. Métodos de acceso (Getters) requeridos por el Main
    public int getTotalControles() {
        return contador;
    }

    public int getControlesDisponibles() {
        return 10 - contador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public int getEdad() {
        return edad;
    }
}