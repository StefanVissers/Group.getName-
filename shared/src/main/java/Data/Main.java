package Data;

public class Main {

    public static void main(String[] args) {
        DatabaseReader databaseReader = new DatabaseReader();
        databaseReader.executeQuery("Data.db", "SELECT count(diefstal.diefstal_voorvalnummer) FROM diefstal", "count(diefstal.diefstal_voorvalnummer)", "DESKTOP");
    }

    public static String getString() {
        return "Hello world";
    }
}
