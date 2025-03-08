package org.equipo404;

import org.equipo404.Collections.*;
import org.equipo404.User.BorrowType;
import org.equipo404.User.User;
import org.equipo404.util.TerminalUI;
import org.equipo404.User.ExpressBorrow;
import org.equipo404.User.LongBorrow;
import org.equipo404.Library.*;
import java.util.Random;


import java.util.*;

public class Main {
    public static int amountDocs = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear colecciones iniciales
        List<ResourceCollection<? extends Resource>> resources = createResourceCollection();
        Library<Resource> library = new Library<>(resources);

        List<User> users = new ArrayList<>();
        Map<User, Integer> borrowedDays = new HashMap<>();
        int currentDay = 0;

        // Crear usuarios
        User carlos = new User(1, "carlos@example.com");
        User mariana = new User(2, "mariana@example.com");
        User jorge = new User(3, "jorge@example.com");
        User ana = new User(4, "ana@example.com");
        users.addAll(List.of(carlos, mariana, jorge, ana));
        TerminalUI.info("Estos son los usuarios disponibles para usar");
        showAllUsers(users);
        // ============================
        //        SIMULACIÓN
        // ============================
        TerminalUI.success("INICIO DE SIMULACIÓN");

        // CASO 1: Carlos se vuelve moroso
        TerminalUI.info("Carlos pide un libro y lo olvida devolver por 30 días");
        DocumentTemplate doc1 = createDocument(library, 0, 1);
        library.borrowMaterial(carlos, new ExpressBorrow(), doc1);
        borrowedDays.put(carlos, currentDay);
        currentDay += 30;
        carlos.getBorrowType().substractDaysLet(30);
        carlos.checkRegularity();
        library.borrowMaterial(carlos, new ExpressBorrow(), createDocument(library, 4, 2));

        // CASO 2: Mariana pide un libro
        TerminalUI.info("Mariana solicita 'Cálculo Avanzado' con préstamo normal");
        DocumentTemplate doc2 = createDocument(library, 5, 2);
        library.borrowMaterial(mariana, new LongBorrow(), doc2);

        // CASO 3: Jorge busca un libro y elige una revista
        TerminalUI.info("Jorge explora la biblioteca");
        library.showLibraryResources(); // Usar showLibraryResources en lugar de exploreMaterials
        DocumentTemplate cienSoledad = createDocument(library, 3, 3);
        library.borrowMaterial(jorge, new ExpressBorrow(), cienSoledad);


        // CASO 4: Ana reserva 'Cien años de soledad'
        TerminalUI.info("Ana intenta pedir 'Cien años de soledad', pero está prestado");
        library.reserveMaterial(ana, cienSoledad);

        // Se envia notificacion cuando
        library.returnMaterial(jorge, 3);

        TerminalUI.success("FIN DE SIMULACIÓN");

        // ============================
        //       MENÚ INTERACTIVO
        // ============================
        showMenu(scanner, library, users, borrowedDays, currentDay);
    }


    // Método ajustado para crear documentos
    private static DocumentTemplate createDocument(Library library, int id, int formatType) {
        Resource resource = library.findResourceById(id);
        if (resource == null) {
            return null;
        }
        return switch (formatType) {
            case 1 -> new PDFDocument(resource, new Available());
            case 2 -> new EPUBDocument(resource, new Available());
            case 3 -> new MOBIDocument(resource, new Available());
            default -> new PDFDocument(resource, new Available());
        };
    }


    private static List<ResourceCollection<? extends Resource>> createResourceCollection() {
        List<ResourceCollection<? extends Resource>> collections = new ArrayList<>();

        // Libros
        ArrayList<Book> booksList = new ArrayList<>();
        booksList.add(new Book(amountDocs, "El algoritmo infinito", ResourceCategory.CIENCIA));
        booksList.add(new Book(++amountDocs, "Mentes brillantes", ResourceCategory.ARTE));
        booksList.add(new Book(++amountDocs, "Cien años de soledad", ResourceCategory.LITERATURA));
        booksList.add(new Book(++amountDocs, "La guía del programador", ResourceCategory.CIENCIA));

        BooksCollection booksCollection = new BooksCollection(
                new NormalIterator<>(booksList), booksList);

        // Revistas
        Magazine[] magazinesArray = {
                new Magazine(++amountDocs, "Historia en papel", ResourceCategory.CIENCIA),
                new Magazine(++amountDocs, "Descifrando el pasado", ResourceCategory.SALUD)
        };

        MagazinesCollection magazinesCollection = new MagazinesCollection(
                new NormalIterator<>(Arrays.stream(magazinesArray).toList()), magazinesArray);

        // Audiolibros
        Hashtable<AudioBook, Integer> audiobooksTable = new Hashtable<>();
        audiobooksTable.put(new AudioBook(++amountDocs, "Filosofía moderna", ResourceCategory.ARTE), 1);
        audiobooksTable.put(new AudioBook(++amountDocs, "La ciencia de lo imposible", ResourceCategory.CIENCIA), 1);

        AudioBooksCollection audioBooksCollection = new AudioBooksCollection(
                new NormalIterator<>(audiobooksTable.keySet().stream().toList()), audiobooksTable);


        // Agregar colecciones a la biblioteca
        collections.add(booksCollection);
        collections.add(magazinesCollection);
        collections.add(audioBooksCollection);

        System.out.println("📌 Total de colecciones creadas: " + collections.size());
        for (ResourceCollection<? extends Resource> collection : collections) {
            System.out.println("📖 Clase: " + collection.getClass().getSimpleName() + " - Elementos: " + getCollectionSize(collection));
        }


        return collections;
    }

    private static int getCollectionSize(ResourceCollection<? extends Resource> collection) {
        int size = 0;
        for (Resource resource : collection) {
            size++;
        }
        return size;
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
                    "Simular paso de días",
                    "Renovar prestamo");

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
                case 6 -> handleUserStatus(users);
                case 7 -> {
                    TerminalUI.print("Ingrese la cantidad de días a avanzar:");
                    int days = scanner.nextInt();
                    scanner.nextLine();
                    currentDay += days;
                    for (User user : users) {
                        if (user.getDocumentBorrowed() != null && user.getBorrowType() != null) {
                            user.getBorrowType().substractDaysLet(days);
                        }
                    }
                    TerminalUI.success("Tiempo avanzado: " + days + " días");
                    for (User user : users){
                        user.checkRegularity();
                    }
                }
                case 8 -> handleRenewal(users);
                case 0 -> {
                    TerminalUI.success("Saliendo...");
                    return;
                }
                default -> TerminalUI.error("Opción inválida.");
            }
        }
    }

    private static void handleBorrow(Scanner scanner, Library library, List<User> users, Map<User, Integer> borrowedDays, int currentDay) {
        User user = getUserById(TerminalUI.inputInt("Ingrese su ID de usuario:"), users);
        if (user == null) return;

        int id = TerminalUI.inputInt("Ingrese el id del material a prestar:");


        TerminalUI.print("Seleccione formato de préstamo: 1. PDF | 2. EPUB | 3. MOBI");
        int formatType = scanner.nextInt();
        scanner.nextLine();

        DocumentTemplate document = createDocument(library, id, formatType);
        if (document == null) {
            TerminalUI.error("El material solicitado no existe en la biblioteca.");
            return;
        }

        TerminalUI.print("Seleccione tipo de préstamo: 1. Normal (15 días) | 2. Express (7 días)");
        int type = scanner.nextInt();
        scanner.nextLine();

        BorrowType borrowType = (type == 1) ? new LongBorrow() : new ExpressBorrow();
        if (library.borrowMaterial(user, borrowType, document)) {
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
        User user = getUserById(TerminalUI.inputInt("Ingrese su ID de usuario:"), users);
        if (user == null) return;

        int id = TerminalUI.inputInt("Ingrese el id del material a devolver:");

        library.returnMaterial(user, id);
        borrowedDays.remove(user);
    }

    private static void handleReservation(Scanner scanner, Library library, List<User> users) {
        User user = getUserById(TerminalUI.inputInt("Ingrese su ID de usuario:"), users);
        if (user == null) return;

        int id = TerminalUI.inputInt("Ingrese el id del material a reservar:");

        int formatType = TerminalUI.inputInt("Seleccione formato para reservar: 1. PDF | 2. EPUB | 3. MOBI");

        DocumentTemplate document = createDocument(library, id, formatType);
        if (document == null) {
            TerminalUI.error("El material solicitado no existe en la biblioteca.");
            return;
        }

        library.reserveMaterial(user, document);
        String formatName = switch (formatType) {
            case 1 -> "PDF";
            case 2 -> "EPUB";
            case 3 -> "MOBI";
            default -> "desconocido";
        };
        TerminalUI.success("Material reservado con éxito en formato " + formatName);
    }

    private static void handleRenewal(List<User> users) {
        User user = getUserById(TerminalUI.inputInt("Ingrese ID de usuario"), users);
        if (user == null) return;

        DocumentTemplate borrowedDocument = user.getDocumentBorrowed();
        if (borrowedDocument == null) {
            TerminalUI.warning("No tiene préstamos activos para renovar.");
            return;
        }

        BorrowType borrowType = user.getBorrowType();
        if (borrowType instanceof LongBorrow longBorrow) {
            if (longBorrow.renew()) {
                TerminalUI.success("Su préstamo ha sido renovado con éxito. Nuevo tiempo: " + longBorrow.getDaysLeft() + " días.");
            } else {
                TerminalUI.error("No se pudo renovar el préstamo. Ya alcanzó el máximo de renovaciones.");
            }
        } else {
            TerminalUI.warning("Solo los préstamos de tipo LongBorrow pueden renovarse.");
        }
    }

    private static void handleUserStatus(List<User> users) {
        User user = getUserById(TerminalUI.inputInt("Ingrese su ID de usuario:"), users);
        if (user == null) return;
        TerminalUI.print(user.toString());
    }

    private static User getUserById(int id, List<User> users) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }
    private static void showAllUsers(List<User> users) {
        TerminalUI.info("Lista de Usuarios:");

        if (users.isEmpty()) {
            TerminalUI.warning("No hay usuarios registrados.");
            return;
        }

        for (User user : users) {
            TerminalUI.print("ID: " + user.getId() + " | Email: " + user.getEmail());
        }
    }
}