package security;

public class encryption {
    public static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);

            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {
                    char encryptedChar = (char) (((ch - 'A' + 3) % 26) + 'A');
                    ciphertext.append(encryptedChar);
                } else {
                    char encryptedChar = (char) (((ch - 'a' + 3) % 26) + 'a');
                    ciphertext.append(encryptedChar);
                }
            } else {
                ciphertext.append(ch);
            }
        }

        return ciphertext.toString();
    }
}
