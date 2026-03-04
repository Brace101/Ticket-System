import java.util.UUID;

public class IdGenerator {

    public static String generateEventId() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public static String generateAccessCode() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
