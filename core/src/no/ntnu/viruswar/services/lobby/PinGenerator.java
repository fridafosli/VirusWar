package no.ntnu.viruswar.services.lobby;

public class PinGenerator {

    public static String VALID_CHARS = "abcdefghijklmnopqrstuvw0123456789";

    public static String generate(int length) {

        String pin = "";

        for (int i = 0; i < length; i++) {
            int ch = (int) Math.floor(Math.random() * VALID_CHARS.length());
            pin += VALID_CHARS.charAt(ch);
        }
        
        return pin;
    }

}
