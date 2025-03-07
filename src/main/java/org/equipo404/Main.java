import org.equipo404.User.User;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library(); // Clase para manejar libros, revistas y audiolibros
        List<User> users = new ArrayList<>();
        Map<User, Integer> borrowedDays = new HashMap<>(); // Simula el paso de días por usuario

        users.add(new User(1, "carlos@example.com"));
        users.add(new User(2, "mariana@example.com"));
        users.add(new User(3, "jorge@example.com"));
        users.add(new User(4, "ana@example.com"));

        int currentDay = 0;

        while (true) {
            TerminalUI.showMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1: // Explorar materiales
                    library.exploreMaterials();
                    break;
                case 2: // Buscar material por título
                    TerminalUI.print("Ingrese el título a buscar:");
                    String title = scanner.nextLine();
                    library.searchByTitle(title);
                    break;
                case 3: // Solicitar préstamo
                    TerminalUI.print("Ingrese su ID de usuario:");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    User user = getUserById(users, userId);
                    if (user == null) {
                        TerminalUI.error("Usuario no encontrado.");
                        break;
                    }
                    TerminalUI.print("Ingrese el título del material a prestar:");
                    String borrowTitle = scanner.nextLine();
                    TerminalUI.print("Seleccione tipo de préstamo: 1. Normal (15 días) | 2. Express (7 días)");
                    int borrowType = scanner.nextInt();
                    scanner.nextLine();
                    BorrowType type = (borrowType == 1) ? new Regular() : new ExpressBorrow();
                    if (library.borrowMaterial(user, borrowTitle, type)) {
                        borrowedDays.put(user, currentDay);
                    }
                    break;
                case 4: // Devolver material
                    TerminalUI.print("Ingrese su ID de usuario:");
                    userId = scanner.nextInt();
                    scanner.nextLine();
                    user = getUserById(users, userId);
                    if (user == null) {
                        TerminalUI.error("Usuario no encontrado.");
                        break;
                    }
                    TerminalUI.print("Ingrese el título del material a devolver:");
                    String returnTitle = scanner.nextLine();
                    library.returnMaterial(user, returnTitle);
                    borrowedDays.remove(user);
                    break;
                case 5: // Reservar material
                    TerminalUI.print("Ingrese su ID de usuario:");
                    userId = scanner.nextInt();
                    scanner.nextLine();
                    user = getUserById(users, userId);
                    if (user == null) {
                        TerminalUI.error("Usuario no encontrado.");
                        break;
                    }
                    TerminalUI.print("Ingrese el título del material a reservar:");
                    String reserveTitle = scanner.nextLine();
                    library.reserveMaterial(user, reserveTitle);
                    break;
                case 6: // Ver estado del usuario
                    TerminalUI.print("Ingrese su ID de usuario:");
                    userId = scanner.nextInt();
                    scanner.nextLine();
                    user = getUserById(users, userId);
                    if (user == null) {
                        TerminalUI.error("Usuario no encontrado.");
                        break;
                    }
                    TerminalUI.print(user.toString());
                    break;
                case 7: // Simular paso de días
                    TerminalUI.print("Ingrese la cantidad de días a avanzar:");
                    int days = scanner.nextInt();
                    scanner.nextLine();
                    currentDay += days;
                    for (User u : users) {
                        if (borrowedDays.containsKey(u)) {
                            int daysBorrowed = currentDay - borrowedDays.get(u);
                            u.updateDaysLeft(daysBorrowed);
                        }
                    }
                    TerminalUI.success("Tiempo avanzado: " + days + " días");
                    break;
                case 8: // Salir
                    TerminalUI.success("Saliendo...");
                    return;
                default:
                    TerminalUI.error("Opción inválida.");
            }
        }
    }

    private static User getUserById(List<User> users, int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}

