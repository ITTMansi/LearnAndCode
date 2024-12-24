public class EmailFootprintCalculator {

    public double calculateFootprint(String category) {
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
}
