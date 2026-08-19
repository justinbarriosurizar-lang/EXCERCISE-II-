public class Mascota {
    private String nombre;
    private String especie;
    private int edad;
    private double[] controlPeso; // Se usa doble para decimales 0.0
    private int contador;

    public Mascota(String nombre, String especie, int edad) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.controlPeso = new double[10]; // Ingresar maximo 10 pesos
        this.contador = 0; // Inicias en 0 registros
    }

    public boolean agregarControlPeso(double peso) {
        if (peso <= 0 || contador >= 10) {
            return false;
        }
        controlPeso[contador] = peso;
        contador++;
        return true;
    }

    public double obtenerPesoControl(int numeroControl) {
        int indice = numeroControl - 1; // Ajuste para índice basado en 0
        if (indice >= 0 && indice < contador) {
            return controlPeso[indice];
        }
        return -1;
    }
    public boolean modificarPesoControl(int numeroControl, double nuevoPeso) {
        int indice = numeroControl - 1; // Ajuste para indice basado en 0
        if (indice >= 0 && indice < contador && nuevoPeso > 0) {
            controlPeso[indice] = nuevoPeso;
            return true;
        }
        return false;
    }
    public double calcularPromedioPeso() {
        if (contador == 0) return 0.0;
        double suma = 0;
        for (int i = 0; i < contador; i++) {
            suma += controlPeso[i];
        }
        return suma / contador;
    }
    public double obtenerPesoMaximo() {
        if (contador == 0) return 0.0;
        double mayor = controlPeso[0];
        for (int i = 1; i < contador; i++) {
            if (controlPeso[i] > mayor) {
                mayor = controlPeso[i];
            }
        }
        return mayor;
    }
    public double obtenerPesoMinimo() {
        if (contador == 0) return 0.0;
        double menor = controlPeso[0];
        for (int i = 1; i < contador; i++) {
            if (controlPeso[i] < menor) {
                menor = controlPeso[i];
            }
        }
        return menor;
    }
    
    public int getControlesRealizados() {
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
