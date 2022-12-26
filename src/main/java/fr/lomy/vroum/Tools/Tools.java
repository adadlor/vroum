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

    public static boolean ipIsValid(String anwser) {
        String[] ip = anwser.split("\\.");
        if(ip.length != 4){
            return false;
        }
        for(String s : ip){
            try{
                int i = Integer.parseInt(s);
                if(i < 0 || i > 255){
                    return false;
                }
            }catch (NumberFormatException e){
                return false;
            }
        }
        return true;
    }
}
