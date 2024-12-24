import java.util.HashMap;
import java.util.Map;

public class EmailCarbonFootprint {
    
    private final EmailSourceDetector sourceDetector;
    private final EmailFootprintCalculator footprintCalculator;

    public EmailCarbonFootprint() {
        this.sourceDetector = new EmailSourceDetector();
        this.footprintCalculator = new EmailFootprintCalculator();
    }

    public Map<String, String> getCarbonFootprint(String entityType, Map<String, String> entity) {
        Map<String, String> response = new HashMap<>();

        if (entityType.equals("email")) {
            String emailId = entity.get("email");
            String source = sourceDetector.determineSource(emailId);
            double inboxFootprint = footprintCalculator.calculateFootprint("inbox");
            double sentFootprint = footprintCalculator.calculateFootprint("sent");
            double spamFootprint = footprintCalculator.calculateFootprint("spam");

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

    public static void main(String[] args) {
        String entityType = "email";
        Map<String, String> entity = new HashMap<>();
        entity.put("email", "mansi@gmail.com");
        
        EmailCarbonFootprint emailCarbonFootprint = new EmailCarbonFootprint();
        Map<String, String> response = emailCarbonFootprint.getCarbonFootprint(entityType, entity);
        
        System.out.println(entityType + " (entityType basis)");
        response.forEach((key, value) -> {
            System.out.println("  " + key + " : " + value);
        });
    }
}
