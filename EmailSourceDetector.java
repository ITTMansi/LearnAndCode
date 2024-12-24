public class EmailSourceDetector {

    public String determineSource(String emailId) {
        if (emailId.endsWith("@gmail.com")) {
            return "Gmail";
        } else if (emailId.endsWith("@outlook.com")) {
            return "Outlook";
        } else if (emailId.endsWith("@yahoo.com")) {
            return "Yahoo";
        }
        return "Unknown";
    }
}
