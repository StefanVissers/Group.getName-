package getname.group.project_4.SQL;

import java.util.ArrayList;
import java.util.List;

import Data.Node.Empty;
import Data.Node.Node;
import Data.Node.NodeList;

public class Dates {
    public final static String[] Years = new String[] {
            "2000", "2001", "2002",
            "2003", "2004", "2005",
            "2006", "2007", "2008",
            "2009", "2010", "2011",
            "2012", "2013"
    };

    public final static String[] MonthsAsNumber = new String[] {
            "01", "02", "03", "04",
            "05", "06", "07", "08",
            "09", "10", "11", "12"
    };

    public final static String[] MonthsAsName = new String[] {
            "January", "February", "March",
            "April", "May", "June", "July",
            "August", "September", "October",
            "November", "December"
    };

    public static List<String> AllMonths(String filterYear) {
        List<String> years_months = new ArrayList<>();

        for (String Year : Years) {
            if (filterYear == null || Year.equals(filterYear)) {
                for (String Month : MonthsAsNumber) {
                    years_months.add(Year + ", " + Month);
                }
            }
        }

        return years_months;
    }

    public static List<NodeList> MonthsAsNumberPerYear() {
        List<NodeList> nodes = new ArrayList<>();

        for (String year : Years) {
            for (int i = 0; i < MonthsAsNumber.length; i++) {
                nodes.add(new Node<String>(year,"Year", new Node<String>(MonthsAsNumber[i], "MonthNR", new Node<String>(MonthsAsNumber[i], "MonthName", new Empty()))));
            }
        }
        return nodes;
    }

}
