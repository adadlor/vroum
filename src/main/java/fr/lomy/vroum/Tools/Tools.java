package fr.lomy.vroum.Tools;

/**
 * Classe de gestion des outils
 * Elle contient les méthodes de calculs et de debug
 */

public class Tools {

    public static void debug_print(String message) {
        System.out.println("[DEBUG] " + message);
    }

    public static void success_print(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    public static void error_print(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void warning_print(String message) {
        System.out.println("[WARNING] " + message);
    }

    public static void info_print(String message) {
        System.out.println("[INFO] " + message);
    }
}
