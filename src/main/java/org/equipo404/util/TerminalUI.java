package org.equipo404.util;

public class TerminalUI {
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
}
