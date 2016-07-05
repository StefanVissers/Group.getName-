package Data;

//import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.util.NoSuchElementException;

/**
 * Created by floris-jan on 01-07-16.
 */
public class Queries {
    private Queries() {}

    public static String[] getBarStat1() {
        return new String[] { "sql: " +
                "Select [Deelgem.], Count(*) as cnt " +
                "From fietstrommels " +
                "Where [Deelgem.] <> \"\" " +
                "Group By [Deelgem.] " +
                "Order By cnt Desc " +
                "Limit 5",
                "title: " +
                "Fietstrommels",
                "desc: " +
                "Aantal fietstrommels per wijk"};
    }

    public static String[] getGroupedBarStat1() {
        return new String[] {
            "^START^1",
                "sql: " +
                    "SELECT REPLACE(Begindatum, RTRIM(Begindatum, REPLACE(Begindatum, '/', '' ) ), '') AS [jaar], " +
                    "REPLACE(Begindatum, LTRIM(Begindatum, REPLACE(Begindatum, '/', '' ) ), '') AS [maand], " +
                    "COUNT(Begindatum) AS [diefstallen] " +
                    "FROM fietsdiefstal " +
                    "WHERE (Begindatum <> NULL OR Begindatum <> \"\")" +
                    "GROUP BY jaar, maand " +
                    "ORDER BY jaar, maand",
                "title: " +
                    "Fietsdiefstallen",
                "desc: " +
                    "Aantal fietsdiefstallen per maand",
            "^END^1",

            "^START^2",
                "sql: " +
                    "SELECT replace(Mutdatum, rtrim(Mutdatum, replace(Mutdatum, '-', '' ) ), '') AS [jaar], " +
                    "substr(ltrim(ltrim(Mutdatum, \"0123456789\"), \"-\"),3,-3) AS [maand], " +
                    "Count(*) AS cnt " +
                    "FROM fietstrommels " +
                    "GROUP BY jaar, maand " +
                    "ORDER BY jaar, maand DESC",
                "title: " +
                    "Fietstrommels",
                "desc: " +
                    "Actieve fietstrommels per maand",
            "^END^2"
        };
    }

    public static String[] getPieStat1() {
        return new String[] {"sql: " +
                "select [kleur], Count(*) as cnt " +
                "from fietsdiefstal " +
                "where [kleur]<>\"\" " +
                "Group By [kleur] " +
                "Order By cnt Desc " +
                "limit 7",
                "title: " +
                "Fietskleur",
                "desc: " +
                "Gestolen fietsen per kleur"};
    }

    public static String[] getPieStat2() {
        return new String[] { "sql: " +
                "select [merk], Count(*) as cnt " +
                "from fietsdiefstal " +
                "where [merk]<>\"\" " +
                "Group By [merk] " +
                "Order By cnt Desc " +
                "limit 5",
                "title: " +
                "Fietsmerk",
                "desc: " +
                "Gestolen fietsen per merk"};
    }

    public static String[] getLineStat1() {
        return new String[] { "sql: " +
                "SELECT replace(Begindatum, rtrim(Begindatum, replace(Begindatum, '/', '' ) ), '') AS [jaar], " +
                "replace(Begindatum, ltrim(Begindatum, replace(Begindatum, '/', '' ) ), '') AS [maand], " +
                "Count(Begindatum) AS [diefstallen] " +
                "FROM fietsdiefstal " +
                "Where Begindatum <> null or Begindatum <> \"\" " +
                "Group By jaar, maand " +
                "Order By jaar, maand;",
                "title: " +
                "Fietsdiefstal",
                "desc: " +
                "Gestolen fietsen per maand"};
    }

    public static String[] getLineStat1(int year, RelativeTime relativeTime) {
        String filter = relativeTime == RelativeTime.BEFORE ? " < " + year :
                        relativeTime == RelativeTime.CURRENT ? " = " + year :
                        relativeTime == RelativeTime.AFTER ? " > " + year : null;

        return new String[] { "sql: " +
                "SELECT replace(Begindatum, rtrim(Begindatum, replace(Begindatum, '/', '' ) ), '') AS [jaar], " +
                "replace(Begindatum, ltrim(Begindatum, replace(Begindatum, '/', '' ) ), '') AS [maand], " +
                "Count(Begindatum) AS [diefstallen] " +
                "FROM fietsdiefstal " +
                "Where (Begindatum <> null or Begindatum <> \"\") " +
                "Group By jaar, maand " +
                "Order By jaar, maand;",
                "title: " +
                "Fietsdiefstal",
                "desc: " +
                "Gestolen fietsen per maand",
                "filter: " +
                "Cast(jaar AS INTEGER)" + filter,
                "reltime: " +
                relativeTime.toString()};
    }

    public enum RelativeTime {
        BEFORE,
        CURRENT,
        AFTER;

        @Override
        public String toString() {
            switch (this) {
                case BEFORE: return "BEFORE";
                case CURRENT: return "CURRENT";
                case AFTER: return "AFTER";
                default: throw new NoSuchElementException();
            }
        }

    }
}
