package org.equipo404;

import org.equipo404.Collections.*;
import org.equipo404.User.BorrowType;
import org.equipo404.User.User;
import org.equipo404.util.TerminalUI;
import org.equipo404.User.ExpressBorrow;
import org.equipo404.User.LongBorrow;
import org.equipo404.Library.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear colecciones iniciales
        List<ResourceCollection> resources = createResourceCollection();
        Library library = new Library(resources);

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
        DocumentTemplate doc1 = createDocument(library, "Libro 1", 1);
        library.borrowMaterial(carlos, "Libro 1", new ExpressBorrow(7), doc1);
        borrowedDays.put(carlos, currentDay);
        currentDay += 30;
        carlos.getBorrowType().substractDaysLet(30);
        TerminalUI.warning("Carlos ahora es moroso y no puede pedir más libros");

        // CASO 2: Mariana pide un libro
        TerminalUI.info("Mariana solicita 'Cálculo Avanzado' con préstamo normal");
        DocumentTemplate doc2 = createDocument(library, "Cálculo Avanzado", 2);
        library.borrowMaterial(mariana, "Cálculo Avanzado", new LongBorrow(15), doc2);

        // CASO 3: Jorge busca un libro y elige una revista
        TerminalUI.info("Jorge explora la biblioteca");
        library.showLibraryResources(); // Usar showLibraryResources en lugar de exploreMaterials
        TerminalUI.info("Jorge elige una revista");

        // CASO 4: Ana reserva 'Cien años de soledad'
        TerminalUI.info("Ana intenta pedir 'Cien años de soledad', pero está prestado");
        library.reserveMaterial(ana, "Cien años de soledad", createDocument(library, "Cien años de soledad", 3));

        TerminalUI.success("FIN DE SIMULACIÓN");

        // ============================
        //       MENÚ INTERACTIVO
        // ============================
        showMenu(scanner, library, users, borrowedDays, currentDay);
    }


    private static List<ResourceCollection> createResourceCollection() {
        List<ResourceCollection> collections = new ArrayList<>();

        // Crear lista de libros (usa List<Book>)
        List<Book> booksList = new ArrayList<>();
        booksList.add(new Book("El algoritmo infinito", ResourceCategory.CIENCIA));
        booksList.add(new Book("La guía del programador", ResourceCategory.BUSINESS));
        booksList.add(new Book("Mentes brillantes", ResourceCategory.ARTE));

        BooksCollection booksCollection = new BooksCollection(
                new CategoryIterator<>(booksList.iterator(), ResourceCategory.CIENCIA), booksList);

        // Crear arreglo de revistas (usa Magazine[])
        Magazine[] magazinesArray = {
                new Magazine("Historia en papel", ResourceCategory.CIENCIA),
                new Magazine("Descifrando el pasado", ResourceCategory.SALUD)
        };

        MagazinesCollection magazinesCollection = new MagazinesCollection(
                new CategoryIterator<>(Arrays.stream(magazinesArray).iterator(), ResourceCategory.SALUD), magazinesArray);

        // Crear mapa de audiolibros (usa Hashtable<AudioBook, Integer>)
        Hashtable<AudioBook, Integer> audiobooksTable = new Hashtable<>();
        audiobooksTable.put(new AudioBook("Filosofía moderna", ResourceCategory.ARTE), 1);
        audiobooksTable.put(new AudioBook("La ciencia de lo imposible", ResourceCategory.CIENCIA), 1);

        AudioBooksCollection audioBooksCollection = new AudioBooksCollection(
                new CategoryIterator<>(audiobooksTable.keySet().iterator(), ResourceCategory.ARTE), audiobooksTable);

        // Agregar colecciones concretas a la biblioteca
        collections.add(booksCollection);
        collections.add(magazinesCollection);
        collections.add(audioBooksCollection);

        return collections;
    }


    // Método ajustado para crear documentos
    private static DocumentTemplate createDocument(Library library, String title, int formatType) {
        Resource resource = library.findResourceByTitle(title);
        if (resource == null) {
            resource = new Book("Libro por defecto", ResourceCategory.CIENCIA); // Por defecto, un libro
            resource.setTitle(title);
        }
        return switch (formatType) {
            case 1 -> new PDFDocument(resource, new Available());
            case 2 -> new EPUBDocument(resource, new Available());
            case 3 -> new MOBIDocument(resource, new Available());
            default -> new PDFDocument(resource, new Available());
        };
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
                case 1 -> library.showLibraryResources(); // Usar showLibraryResources
                case 2 -> {
                    TerminalUI.print("Ingrese el título a buscar:");
                    String title = scanner.nextLine();
                    Resource found = library.findResourceByTitle(title); // Usar findResourceByTitle
                    if (found != null) {
                        TerminalUI.success("Recurso encontrado: " + found.getTitle());
                    } else {
                        TerminalUI.error("No se encontró el recurso con título: " + title);
                    }
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
                        if (borrowedDays.containsKey(user) && user.getBorrowType() != null) {
                            user.getBorrowType().substractDaysLet(days);
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

        TerminalUI.print("Seleccione formato de préstamo: 1. PDF | 2. EPUB | 3. MOBI");
        int formatType = scanner.nextInt();
        scanner.nextLine();

        DocumentTemplate document = createDocument(library, title, formatType);
        if (document == null) {
            TerminalUI.error("El material solicitado no existe en la biblioteca.");
            return;
        }

        TerminalUI.print("Seleccione tipo de préstamo: 1. Normal (15 días) | 2. Express (7 días)");
        int type = scanner.nextInt();
        scanner.nextLine();

        BorrowType borrowType = (type == 1) ? new LongBorrow(15) : new ExpressBorrow(7);
        if (library.borrowMaterial(user, title, borrowType, document)) {
            borrowedDays.put(user, currentDay);
            String formatName = switch (formatType) {
                case 1 -> "PDF";
                case 2 -> "EPUB";
                case 3 -> "MOBI";
                default -> "desconocido";
            };
            TerminalUI.success("Material prestado con éxito en formato " + formatName);
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

        TerminalUI.print("Seleccione formato para reservar: 1. PDF | 2. EPUB | 3. MOBI");
        int formatType = scanner.nextInt();
        scanner.nextLine();

        DocumentTemplate document = createDocument(library, title, formatType);
        if (document == null) {
            TerminalUI.error("El material solicitado no existe en la biblioteca.");
            return;
        }

        library.reserveMaterial(user, title, document);
        String formatName = switch (formatType) {
            case 1 -> "PDF";
            case 2 -> "EPUB";
            case 3 -> "MOBI";
            default -> "desconocido";
        };
        TerminalUI.success("Material reservado con éxito en formato " + formatName);
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