import org.equipo404.User.User;
import org.equipo404.Collections.Library;
import org.equipo404.util.TerminalUI;
import org.equipo404.User.Regular;
import org.equipo404.User.ExpressBorrow;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        List<User> users = new ArrayList<>();
        Map<User, Integer> borrowedDays = new HashMap<>();
        int currentDay = 0;

        // Crear usuarios
        User carlos = new User(1, "carlos@example.com");
        User mariana = new User(2, "mariana@example.com");
        User jorge = new User(3, "jorge@example.com");
        User ana = new User(4, "ana@example.com");
        users.addAll(List.of(carlos, mariana, jorge, ana));

        // ============================
        //        SIMULACIÓN
        // ============================
        TerminalUI.success("INICIO DE SIMULACIÓN");

        // CASO 1: Carlos se vuelve moroso
        TerminalUI.info("Carlos pide un libro y lo olvida devolver por 30 días");
        library.borrowMaterial(carlos, "Libro 1", new Regular());
        borrowedDays.put(carlos, currentDay);
        currentDay += 30;
        carlos.updateDaysLeft(30);
        TerminalUI.warning("Carlos ahora es moroso y no puede pedir más libros");

        // CASO 2: Mariana pide un libro
        TerminalUI.info("Mariana solicita 'Cálculo Avanzado' con préstamo normal");
        library.borrowMaterial(mariana, "Cálculo Avanzado", new Regular());

        // CASO 3: Jorge busca un libro y elige una revista
        TerminalUI.info("Jorge explora la biblioteca");
        library.exploreMaterials();
        TerminalUI.info("Jorge elige una revista");

        // CASO 4: Ana reserva 'Cien años de soledad'
        TerminalUI.info("Ana intenta pedir 'Cien años de soledad', pero está prestado");
        library.reserveMaterial(ana, "Cien años de soledad");

        TerminalUI.success("FIN DE SIMULACIÓN");

        // ============================
        //       MENÚ INTERACTIVO
        // ============================
        showMenu(scanner, library, users, borrowedDays, currentDay);
    }

    private static void showMenu(Scanner scanner, Library library, List<User> users, Map<User, Integer> borrowedDays, int currentDay) {
        while (true) {
            int option = TerminalUI.showMenu("Menú Principal",
                    "Explorar materiales",
                    "Buscar material por título",
                    "Solicitar préstamo",
                    "Devolver material",
                    "Reservar material",
                    "Ver estado del usuario",
                    "Simular paso de días");

            switch (option) {
                case 1 -> library.exploreMaterials();
                case 2 -> {
                    TerminalUI.print("Ingrese el título a buscar:");
                    String title = scanner.nextLine();
                    library.searchByTitle(title);
                }
                case 3 -> handleBorrow(scanner, library, users, borrowedDays, currentDay);
                case 4 -> handleReturn(scanner, library, users, borrowedDays);
                case 5 -> handleReservation(scanner, library, users);
                case 6 -> handleUserStatus(scanner, users);
                case 7 -> {
                    TerminalUI.print("Ingrese la cantidad de días a avanzar:");
                    int days = scanner.nextInt();
                    scanner.nextLine();
                    currentDay += days;
                    for (User user : users) {
                        if (borrowedDays.containsKey(user)) {
                            int daysBorrowed = currentDay - borrowedDays.get(user);
                            user.updateDaysLeft(daysBorrowed);
                        }
                    }
                    TerminalUI.success("Tiempo avanzado: " + days + " días");
                }
                case 0 -> {
                    TerminalUI.success("Saliendo...");
                    return;
                }
                default -> TerminalUI.error("Opción inválida.");
            }
        }
    }

    private static void handleBorrow(Scanner scanner, Library library, List<User> users, Map<User, Integer> borrowedDays, int currentDay) {
        TerminalUI.print("Ingrese su ID de usuario:");
        User user = getUserById(scanner, users);
        if (user == null) return;
        TerminalUI.print("Ingrese el título del material a prestar:");
        String title = scanner.nextLine();
        TerminalUI.print("Seleccione tipo de préstamo: 1. Normal (15 días) | 2. Express (7 días)");
        int type = scanner.nextInt();
        scanner.nextLine();
        BorrowType borrowType = (type == 1) ? new Regular() : new ExpressBorrow();
        if (library.borrowMaterial(user, title, borrowType)) {
            borrowedDays.put(user, currentDay);
        }
    }

    private static void handleReturn(Scanner scanner, Library library, List<User> users, Map<User, Integer> borrowedDays) {
        TerminalUI.print("Ingrese su ID de usuario:");
        User user = getUserById(scanner, users);
        if (user == null) return;
        TerminalUI.print("Ingrese el título del material a devolver:");
        String title = scanner.nextLine();
        library.returnMaterial(user, title);
        borrowedDays.remove(user);
    }

    private static void handleReservation(Scanner scanner, Library library, List<User> users) {
        TerminalUI.print("Ingrese su ID de usuario:");
        User user = getUserById(scanner, users);
        if (user == null) return;
        TerminalUI.print("Ingrese el título del material a reservar:");
        String title = scanner.nextLine();
        library.reserveMaterial(user, title);
    }

    private static void handleUserStatus(Scanner scanner, List<User> users) {
        TerminalUI.print("Ingrese su ID de usuario:");
        User user = getUserById(scanner, users);
        if (user == null) return;
        TerminalUI.print(user.toString());
    }

    private static User getUserById(Scanner scanner, List<User> users) {
        int userId = scanner.nextInt();
        scanner.nextLine();
        return users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
    }
}
