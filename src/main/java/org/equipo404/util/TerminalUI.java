package org.equipo404.util;

import java.util.Scanner;

public class TerminalUI {

    private static final Scanner scanner = new Scanner(System.in);

    public static void printNotification(String message) {
        int width = Math.min(message.length() + 4, 50); // Ajusta hasta un máximo de 50 caracteres
        String border = "┌" + "─".repeat(width) + "┐";
        String middle = "│ " + message + " " + "│";
        System.out.println(border);
        System.out.println("│  NOTIFICACIÓN" + " ".repeat(width - 15) + "│");
        System.out.println("├" + "─".repeat(width) + "┤");
        System.out.println(middle);
        System.out.println("└" + "─".repeat(width) + "┘");
    }

    /**
     * Imprime un mensaje informativo estándar.
     * @param message El texto del mensaje a mostrar.
     */
    public static void info(String message) {
        printBox(message, "ℹ️ INFO");
    }

    /**
     * Imprime un mensaje de advertencia al usuario.
     * @param message El texto del mensaje de advertencia.
     */
    public static void warning(String message) {
        printBox(message, "⚠️ ADVERTENCIA");
    }

    /**
     * Imprime un mensaje de error importante.
     * @param message El texto del mensaje de error.
     */
    public static void error(String message) {
        printBox(message, "❌ ERROR");
    }

    /**
     * Imprime un mensaje de confirmación de éxito.
     * @param message El texto del mensaje de éxito.
     */
    public static void success(String message) {
        printBox(message, "✅ ÉXITO");
    }

    /**
     * Método interno que formatea e imprime un mensaje dentro de un recuadro ASCII,
     * con un título dado (nivel de mensaje).
     * @param message Mensaje a mostrar.
     * @param title   Título del recuadro que indica el nivel (INFO, ADVERTENCIA, etc.).
     */
    private static void printBox(String message, String title) {
        // Determina el ancho interior del recuadro basado en el texto más largo (título o mensaje)
        // agregando un margen de 2 espacios a cada lado, límite máximo de 50 caracteres.
        int contentLength = Math.max(message.length(), title.length());
        int innerWidth = Math.min(contentLength + 4, 50);

        // Si el mensaje o título sobrepasa el espacio, se trunca y agrega "..." para indicar omisión.
        if (message.length() > innerWidth - 2) {
            message = message.substring(0, innerWidth - 5) + "...";
        }
        if (title.length() > innerWidth - 2) {
            title = title.substring(0, innerWidth - 5) + "...";
        }

        // Vuelve a calcular en caso de truncamiento.
        contentLength = Math.max(message.length(), title.length());
        innerWidth = Math.min(contentLength + 4, 50);

        // Construye las líneas del recuadro
        String topBorder    = "┌" + "─".repeat(innerWidth) + "┐";
        String bottomBorder = "└" + "─".repeat(innerWidth) + "┘";
        String separator    = "├" + "─".repeat(innerWidth) + "┤";

        // Línea de título (con dos espacios de margen a la izquierda)
        String titleLine = "│  " + title;
        int titleUsed = 2 + title.length();  // caracteres de contenido usados (dos espacios + texto título)
        if (titleUsed < innerWidth) {
            titleLine += " ".repeat(innerWidth - titleUsed);  // rellena el resto con espacios
        }
        titleLine += "│";  // cierra borde derecho

        // Línea de mensaje (con dos espacios de margen a la izquierda)
        String messageLine = "│  " + message;
        int messageUsed = 2 + message.length();
        if (messageUsed < innerWidth) {
            messageLine += " ".repeat(innerWidth - messageUsed);
        }
        messageLine += "│";

        // Imprime el recuadro completo a la terminal
        System.out.println(topBorder);
        System.out.println(titleLine);
        System.out.println(separator);
        System.out.println(messageLine);
        System.out.println(bottomBorder);
    }




    /**
     * Muestra un menú interactivo y devuelve la opción elegida.
     * @param title El título del menú.
     * @param options Lista de opciones a mostrar.
     * @return La opción seleccionada por el usuario.
     */
    public static int showMenu(String title, String... options) {
        System.out.println("╔════════════════════════════╗");
        System.out.printf("║ %-26s ║%n", title);
        System.out.println("╠════════════════════════════╣");

        for (int i = 0; i < options.length; i++) {
            System.out.printf("║ %d. %-22s ║%n", i + 1, options[i]);
        }
        System.out.println("║ 0. Salir                  ║");
        System.out.println("╚════════════════════════════╝");

        return getUserChoice(options.length);
    }

    /**
     * Obtiene la elección del usuario, validando la entrada.
     * @param maxOption Número máximo de opciones disponibles.
     * @return La opción elegida por el usuario.
     */
    private static int getUserChoice(int maxOption) {
        int choice = -1;
        while (choice < 0 || choice > maxOption) {
            System.out.print("Seleccione una opción: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next(); // Descartar entrada no válida
            }
        }
        return choice;
    }

}
