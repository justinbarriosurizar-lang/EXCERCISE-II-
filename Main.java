import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mascota mascotaActiva = null;
        boolean salir = false;

        System.out.println("SISTEMA DE CONTROL DE PESO VETERINARIO");

        //Registrar la primera mascota
        mascotaActiva = crearNuevaMascota(scanner);

        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione una opcion: ");
            int opcion = pedirEnteroValido(scanner);

            switch (opcion) {
                case 1:
                    // Nueva mascota
                    System.out.println("\nRegistro de nueva mascota");
                    mascotaActiva = crearNuevaMascota(scanner);
                    System.out.println("Mascota registrada correctamente.");
                    break;

                case 2:
                    // Registrar nuevo peso
                    System.out.println("\n--- Registrar nuevo peso ---");
                    if (mascotaActiva.getControlesDisponibles() == 0) {
                        System.out.println("Se alcanzó el límite de controles maximos permitidos.");
                    } else {
                        System.out.print("Ingrese el peso en kg: ");
                        double peso = pedirDoubleValido(scanner);
                        if (mascotaActiva.agregarControl(peso)) {
                            System.out.println("Peso registrado exitosamente.");
                        } else {
                            System.out.println("Error: El peso debe ser mayor a 0 kg.");
                        }
                    }
                    break;

                case 3:
                    // Consultar historial
                    System.out.println("\nHistorial de pesos");
                    mascotaActiva.mostrarHistorial();
                    break;

                case 4:
                    // Consultar un control de peso especifico
                    System.out.println("\nConsultar control especifico");
                    System.out.print("Ingrese el numero de control a consultar (1 a " + mascotaActiva.getTotalControles() + "): ");
                    int numConsulta = pedirEnteroValido(scanner);
                    mascotaActiva.consultarControl(numConsulta);
                    break;

                case 5:
                    // Modificar peso
                    System.out.println("\n--- Modificar peso de un control ---");
                    System.out.print("Ingrese el numero de control a modificar (1 a " + mascotaActiva.getTotalControles() + "): ");
                    int numModif = pedirEnteroValido(scanner);
                    if (numModif < 1 || numModif > mascotaActiva.getTotalControles()) {
                        System.out.println("Error: Numero de control invalido o no registrado aun.");
                    } else {
                        System.out.print("Ingrese el nuevo peso en kg: ");
                        double nuevoPeso = pedirDoubleValido(scanner);
                        if (mascotaActiva.modificarControl(numModif, nuevoPeso)) {
                            System.out.println("Control modificado exitosamente.");
                        } else {
                            System.out.println("Error: El peso debe ser mayor a 0 kg.");
                        }
                    }
                    break;

                case 6:
                    // Mostrar promedio
                    System.out.println("\nPromedio de peso");
                    double promedio = mascotaActiva.calcularPromedio();
                    if (promedio == 0) {
                        System.out.println("No hay controles registrados para calcular el promedio.");
                    } else {
                        System.out.printf("El promedio de peso de %s es: %.2f kg\n", mascotaActiva.getNombre(), promedio);
                    }
                    break;

                case 7:
                    // Mostrar peso mayor y menor
                    System.out.println("\nPeso mayor y menor");
                    if (mascotaActiva.getTotalControles() == 0) {
                        System.out.println("No hay controles registrados.");
                    } else {
                        System.out.println("Peso mas alto registrado: " + mascotaActiva.obtenerPesoMayor() + " kg");
                        System.out.println("Peso mas bajo registrado: " + mascotaActiva.obtenerPesoMenor() + " kg");
                    }
                    break;

                case 8:
                    // Consultar controles disponibles
                    System.out.println("\nEstado de controles");
                    System.out.println("Controles realizados: " + mascotaActiva.getTotalControles());
                    System.out.println("Controles disponibles: " + mascotaActiva.getControlesDisponibles());
                    break;

                case 9:
                    // Salir
                    salir = true;
                    System.out.println("\n¡Gracias por utilizar el sistema!");
                    break;

                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    // MÉTODOS 

    private static void mostrarMenu() {
        System.out.println("\nMenú Principal");
        System.out.println("1. Nueva mascota");
        System.out.println("2. Registrar nuevo control");
        System.out.println("3. Consultar historial de pesos");
        System.out.println("4. Consultar un control");
        System.out.println("5. Modificar peso");
        System.out.println("6. Mostrar promedio");
        System.out.println("7. Mostrar peso mayor y menor");
        System.out.println("8. Consultar controles disponibles");
        System.out.println("9. Salir");
    }

    private static Mascota crearNuevaMascota(Scanner scanner) {
        System.out.print("Ingrese el nombre de la mascota: ");
        String nombre = scanner.nextLine().trim();
        while (nombre.isEmpty()) {
            System.out.print("El nombre no puede estar vacio. Ingrese el nombre: ");
            nombre = scanner.nextLine().trim();
        }

        System.out.print("Ingrese la especie de la mascota: ");
        String especie = scanner.nextLine().trim();

        System.out.print("Ingrese la edad de la mascota (años): ");
        int edad = pedirEnteroValido(scanner);

        return new Mascota(nombre, especie, edad);
    }

    private static int pedirEnteroValido(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada invalida. Ingrese un numero entero: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static double pedirDoubleValido(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Entrada invalida. Ingrese un numero valido: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine(); 
        return valor;
    }
}