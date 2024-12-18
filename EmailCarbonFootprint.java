import java.util.HashMap;
import java.util.Map;

public class EmailCarbonFootprint{

    public static Map<String, String> getCarbonFootprint(String entityType, Map<String, String> entity) {
        Map<String, String> response = new HashMap<>();

        if (entityType.equals("email")) {
            String emailId = entity.get("email");
            String source = determineSource(emailId);
            double inboxFootprint = calculateFootprint("inbox");
            double sentFootprint = calculateFootprint("sent");
            double spamFootprint = calculateFootprint("spam");
            response.put("emailId", emailId);
            response.put("source", source);
            response.put("inbox", String.format("%.2f KG", inboxFootprint));
            response.put("sent", String.format("%.2f KG", sentFootprint));
            response.put("spam", String.format("%.2f KG", spamFootprint));
        } else {
            throw new IllegalArgumentException("Unsupported entity type: " + entityType);
        }

        return response;
    }

    private static String determineSource(String emailId) {
        if (emailId.endsWith("@gmail.com")) {
            return "Gmail";
        } else if (emailId.endsWith("@outlook.com")) {
            return "Outlook";
        } else if (emailId.endsWith("@yahoo.com")) {
            return "Yahoo";
        }
        return "Unknown";
    }

    private static double calculateFootprint(String category) {
        switch (category) {
            case "inbox":
                return 0.05 * 100;
            case "sent":
                return 0.03 * 50;
            case "spam":
                return 0.02 * 20;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        String entityType = "email";
        Map<String, String> entity = new HashMap<>();
        entity.put("email", "mansi@gmail.com");
        Map<String, String> response = getCarbonFootprint(entityType, entity);
        System.out.println(entityType + " (entityType basis)");
        response.forEach((key, value) -> {
            System.out.println("  " + key + " : " + value);
        });
    }
}
