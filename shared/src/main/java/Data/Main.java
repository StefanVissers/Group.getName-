package Data;

public class Main {

    public static void main(String[] args) {
        DatabaseReader databaseReader = new DatabaseReader();
        databaseReader.executeQuery("fietsdiefstal.db", "SELECT count('Voorval nummer') FROM diefstal", "count('Voorval nummer')", "DESKTOP");
    }

    public static String getString() {
        return "Hello world";
    }
}
