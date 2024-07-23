package gift.util;

import java.util.Base64;

public class TokenUtil {

    public static String generateToken(String email, String password) {
        return Base64.getEncoder().encodeToString((email + ":" + password).getBytes());
    }
}