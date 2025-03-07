package org.equipo404;

import org.equipo404.Collections.*;
import org.equipo404.DesignPatterns.Observer;
import org.equipo404.Library.*;
import org.equipo404.User.*;
import org.equipo404.util.TerminalUI;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear las colecciones de recursos
        ArrayList<Book> books = createBooks();
        Magazine[] magazines = createMagazines();
        Hashtable<AudioBook, Integer> audiobooks = createAudiobooks();

        // Crear iteradores para cada colección
        CollectionIteratorStrategy<Book> bookIterator = new NormalIterator<>(books.iterator());
        CollectionIteratorStrategy<Magazine> magazineIterator = new NormalIterator<>(new IterableArray<>(magazines).iterator());
        CollectionIteratorStrategy<AudioBook> audiobookIterator = new NormalIterator<>(audiobooks.keySet().iterator());

        // Crear colecciones
        BooksCollection booksCollection = new BooksCollection(bookIterator, books);
        MagazinesCollection magazinesCollection = new MagazinesCollection(magazineIterator, magazines);
        AudioBooksCollection audioBooksCollection = new AudioBooksCollection(audiobookIterator, audiobooks);

        // Crear la biblioteca
        List<ResourceCollection<?>> resourceCollections = new ArrayList<>();
        resourceCollections.add(booksCollection);
        resourceCollections.add(magazinesCollection);
        resourceCollections.add(audioBooksCollection);
        Library library = new Library(resourceCollections);

        // Crear usuarios de ejemplo
        User carlos = new User(1, "carlos@example.com");
        User mariana = new User(2, "mariana@example.com");
        User jorge = new User(3, "jorge@example.com");
        User ana = new User(4, "ana@example.com");
        List<User> users = new ArrayList<>(List.of(carlos, mariana, jorge, ana));

        // Crear documentos de ejemplo basados en los recursos
        Map<String, DocumentTemplate> documents = createDocuments(books, magazines, audiobooks);

        // ============================
        //        SIMULACIÓN
        // ============================
        TerminalUI.success("INICIO DE SIMULACIÓN");

        // CASO 1: Carlos se vuelve moroso
        TerminalUI.info("Carlos pide un libro y lo olvida devolver por 30 días");
        DocumentTemplate libroCarlos = documents.get("Programación en Java");
        carlos.borrow(libroCarlos, new LongBorrow(15));

        // Simulamos que han pasado 30 días
        // Como pasa del límite de días, cambiamos su estado a moroso
        carlos.changeState(new Irregular());
        TerminalUI.warning("Carlos ahora es moroso y no puede pedir más libros");

        // CASO 2: Mariana pide un libro
        TerminalUI.info("Mariana solicita 'Cálculo Avanzado' con préstamo normal");
        DocumentTemplate libroMariana = documents.get("Cálculo Avanzado");
        mariana.borrow(libroMariana, new LongBorrow(15));

        // CASO 3: Jorge explora la biblioteca
        TerminalUI.info("Jorge explora la biblioteca");
        library.showLibraryResources();
        TerminalUI.info("Jorge elige una revista");

        // CASO 4: Ana reserva 'Cien años de soledad'
        TerminalUI.info("Ana intenta pedir 'Cien años de soledad', pero está prestado");
        DocumentTemplate libroAna = documents.get("Cien años de soledad");

        // Simulamos que el libro ya está prestado
        libroAna.lend(); // Cambia a estado prestado
        ana.borrow(libroAna, new LongBorrow(15)); // Intentará reservarlo

        TerminalUI.success("FIN DE SIMULACIÓN");

        // ============================
        //       MENÚ INTERACTIVO
        // ============================
        showMenu(scanner, library, users, documents);
    }

    private static void showMenu(Scanner scanner, Library library, List<User> users, Map<String, DocumentTemplate> documents) {
        while (true) {
            int option = TerminalUI.showMenu("Menú Principal",
                    "Explorar materiales",
                    "Buscar material por título",
                    "Solicitar préstamo",
                    "Devolver material",
                    "Reservar material",
                    "Ver estado del usuario",
                    "Avanzar tiempo (simular días)");

            switch (option) {
                case 1 -> library.showLibraryResources();
                case 2 -> {
                    TerminalUI.print("Ingrese el título a buscar:");
                    String title = scanner.nextLine();
                    searchByTitle(documents, title);
                }
                case 3 -> handleBorrow(scanner, users, documents);
                case 4 -> handleReturn(scanner, users);
                case 5 -> handleReservation(scanner, users, documents);
                case 6 -> handleUserStatus(scanner, users);
                case 7 -> {
                    TerminalUI.print("Ingrese la cantidad de días a avanzar:");
                    int days = scanner.nextInt();
                    scanner.nextLine();
                    simulateTimePassing(users, days);
                    TerminalUI.success("Tiempo avanzado: " + days + " días");
                }
                case 0 -> {
                    TerminalUI.success("Saliendo del sistema de biblioteca...");
                    return;
                }
                default -> TerminalUI.error("Opción inválida.");
            }
        }
    }

    private static void simulateTimePassing(List<User> users, int days) {
        for (User user : users) {
            // Solo actualizamos los días si el usuario tiene un préstamo activo
            if (user.getDocumentBorrowed() != null && user.getBorrowType() != null) {
                // Restamos los días que pasan del tiempo prestado
                user.getBorrowType().addDaysLeft(-days);

                // Si los días llegan a 0 o menos, el usuario se vuelve moroso
                if (user.getDaysLeftToDeadline() <= 0) {
                    user.changeState(new Irregular());
                    TerminalUI.warning("El usuario " + user.toString() + " se ha vuelto moroso");
                }
            }
        }
    }

    private static void handleBorrow(Scanner scanner, List<User> users, Map<String, DocumentTemplate> documents) {
        TerminalUI.print("Ingrese su ID de usuario:");
        User user = getUserById(scanner, users);
        if (user == null) {
            TerminalUI.error("Usuario no encontrado");
            return;
        }

        TerminalUI.print("Ingrese el título del material a prestar:");
        String title = scanner.nextLine();
        DocumentTemplate doc = documents.get(title);

        if (doc == null) {
            TerminalUI.error("Material no encontrado: " + title);
            return;
        }

        TerminalUI.print("Seleccione tipo de préstamo: 1. Normal (15 días) | 2. Express (7 días)");
        int type = scanner.nextInt();
        scanner.nextLine();
        BorrowType borrowType = (type == 1) ? new LongBorrow(15) : new ExpressBorrow(7);

        user.borrow(doc, borrowType);
    }

    private static void handleReturn(Scanner scanner, List<User> users) {
        TerminalUI.print("Ingrese su ID de usuario:");
        User user = getUserById(scanner, users);
        if (user == null) {
            TerminalUI.error("Usuario no encontrado");
            return;
        }

        if (user.getDocumentBorrowed() == null) {
            TerminalUI.error("No tiene préstamos activos");
            return;
        }

        user.returnBorrowedDoc();
    }

    private static void handleReservation(Scanner scanner, List<User> users, Map<String, DocumentTemplate> documents) {
        TerminalUI.print("Ingrese su ID de usuario:");
        User user = getUserById(scanner, users);
        if (user == null) {
            TerminalUI.error("Usuario no encontrado");
            return;
        }

        TerminalUI.print("Ingrese el título del material a reservar:");
        String title = scanner.nextLine();
        DocumentTemplate doc = documents.get(title);

        if (doc == null) {
            TerminalUI.error("Material no encontrado: " + title);
            return;
        }

        // Si el documento está prestado, se reserva
        if (doc instanceof DocumentTemplate) {
            doc.reserve(user);
            TerminalUI.success("Reserva realizada con éxito");
        }
    }

    private static void handleUserStatus(Scanner scanner, List<User> users) {
        TerminalUI.print("Ingrese su ID de usuario:");
        User user = getUserById(scanner, users);
        if (user == null) {
            TerminalUI.error("Usuario no encontrado");
            return;
        }

        TerminalUI.print(user.toString());
    }

    private static User getUserById(Scanner scanner, List<User> users) {
        int userId;
        try {
            userId = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpiar el buffer
            return null;
        }

        return users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
    }

    private static void searchByTitle(Map<String, DocumentTemplate> documents, String title) {
        DocumentTemplate doc = documents.get(title);
        if (doc != null) {
            TerminalUI.success("Material encontrado:");
            TerminalUI.print(doc.toString());
        } else {
            TerminalUI.error("Material no encontrado: " + title);
        }
    }

    // Métodos para crear recursos de ejemplo

    private static ArrayList<Book> createBooks() {
        ArrayList<Book> books = new ArrayList<>();

        // Crear algunos libros de ejemplo
        Book book1 = new Book();
        book1.setTitle("Cien años de soledad");
        book1.setResourceCategory(ResourceCategory.ARTE);

        Book book2 = new Book();
        book2.setTitle("Cálculo Avanzado");
        book2.setResourceCategory(ResourceCategory.CIENCIA);

        Book book3 = new Book();
        book3.setTitle("Programación en Java");
        book3.setResourceCategory(ResourceCategory.CIENCIA);

        books.add(book1);
        books.add(book2);
        books.add(book3);

        return books;
    }

    private static Magazine[] createMagazines() {
        Magazine[] magazines = new Magazine[3];

        Magazine magazine1 = new Magazine();
        magazine1.setTitle("National Geographic");
        magazine1.setResourceCategory(ResourceCategory.CIENCIA);

        Magazine magazine2 = new Magazine();
        magazine2.setTitle("Time");
        magazine2.setResourceCategory(ResourceCategory.BUSINESS);

        Magazine magazine3 = new Magazine();
        magazine3.setTitle("Scientific American");
        magazine3.setResourceCategory(ResourceCategory.CIENCIA);

        magazines[0] = magazine1;
        magazines[1] = magazine2;
        magazines[2] = magazine3;

        return magazines;
    }

    private static Hashtable<AudioBook, Integer> createAudiobooks() {
        Hashtable<AudioBook, Integer> audiobooks = new Hashtable<>();

        AudioBook audioBook1 = new AudioBook();
        audioBook1.setTitle("El Principito");
        audioBook1.setResourceCategory(ResourceCategory.ARTE);

        AudioBook audioBook2 = new AudioBook();
        audioBook2.setTitle("Sapiens");
        audioBook2.setResourceCategory(ResourceCategory.CIENCIA);

        audiobooks.put(audioBook1, 1);
        audiobooks.put(audioBook2, 2);

        return audiobooks;
    }

    private static Map<String, DocumentTemplate> createDocuments(ArrayList<Book> books, Magazine[] magazines, Hashtable<AudioBook, Integer> audiobooks) {
        Map<String, DocumentTemplate> documents = new HashMap<>();

        // Implementación de clase interna anónima para DocumentTemplate ya que es abstracta
        for (Book book : books) {
            DocumentTemplate doc = new DocumentTemplate(book, new Available()) {
                @Override
                public void getFormat() {
                    System.out.println("Formato: PDF, EPUB, MOBI");
                }
            };
            documents.put(book.getTitle(), doc);
        }

        for (Magazine magazine : magazines) {
            DocumentTemplate doc = new DocumentTemplate(magazine, new Available()) {
                @Override
                public void getFormat() {
                    System.out.println("Formato: PDF");
                }
            };
            documents.put(magazine.getTitle(), doc);
        }

        for (AudioBook audioBook : audiobooks.keySet()) {
            DocumentTemplate doc = new DocumentTemplate(audioBook, new Available()) {
                @Override
                public void getFormat() {
                    System.out.println("Formato: MP3, WAV");
                }
            };
            documents.put(audioBook.getTitle(), doc);
        }

        return documents;
    }
}