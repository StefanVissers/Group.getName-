package Data;

//import com.sun.xml.internal.fastinfoset.util.StringArray;

/**
 * Created by floris-jan on 01-07-16.
 */
public class Queries {
    public String[] getBarStat1() {
        return new String[] { "sql: " +
                "Select [Deelgem.], Count(*) as cnt\n" +
                "From fietstrommels\n" +
                "Where [Deelgem.] <> \"\"\n" +
                "Group By [Deelgem.]\n" +
                "Order By cnt Desc\n" +
                "Limit 5",
                "title: " +
                "Fietstrommels",
                "description: " +
                "Aantal fietstrommels per wijk" };
    }

    public String[] getGroupedBarStat1() {
        return new String[] { "" };
    }

    public String[] getPieStat1() {
        return new String[] {"sql: " +
                "select [kleur], Count(*) as cnt\n" +
                "from fietsdiefstal\n" +
                "where [kleur]<>\"\"\n" +
                "Group By [kleur]\n" +
                "Order By cnt Desc\n" +
                "limit 7",
                "title: " +
                "Fietskleur",
                "description: " +
                "Gestolen fietsen per kleur"};
    }

    public String[] getPieStat2() {
        return new String[] { "sql: " +
                "select [merk], Count(*) as cnt\n" +
                "from fietsdiefstal\n" +
                "where [merk]<>\"\"\n" +
                "Group By [merk]\n" +
                "Order By cnt Desc\n" +
                "limit 6",
                "title: " +
                "Fietsmerk",
                "description: " +
                "Gestolen fietsen per merk"};
    }

    public String[] getLineStat1() {
        return new String[] { "sql: " +
                "SELECT replace(Begindatum, rtrim(Begindatum, replace(Begindatum, '/', '' ) ), '') AS [jaar],\n" +
                "\t\t\treplace(Begindatum, ltrim(Begindatum, replace(Begindatum, '/', '' ) ), '') AS [maand],\n" +
                "\t\t\tCount(Begindatum) AS [diefstallen]\n" +
                "FROM fietsdiefstal\n" +
                "Where Begindatum <> null or Begindatum <> \"\"\n" +
                "Group By jaar, maand\n" +
                "Order By jaar, maand;",
                "title: " +
                "Fietsdiefstal",
                "description: " +
                "Gestolen fietsen per maand"};
    }
}
