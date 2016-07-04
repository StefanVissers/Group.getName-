package Data;

import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {
        DatabaseReader databaseReader = new DatabaseReader();


        try {
            ResultSet rs = databaseReader.executeQuery("dataSource.db", "Select [Deelgem.], Count(*) as cnt\n" +
                    "From fietstrommels\n" +
                    "Where [Deelgem.] <> \"\"\n" +
                    "Group By [Deelgem.]\n" +
                    "Order By cnt Desc\n" +
                    "Limit 5", "", "DESKTOP");
            while (rs.next()) {
                String str = rs.getString(0);
                System.out.println(str);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getString() {
        return "Hello world";
    }
}
