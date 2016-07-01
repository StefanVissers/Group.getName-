package getname.group.project_4.charts;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import getname.group.project_4.SQL.DatabaseHelper;

/**
 * Created by floris-jan on 01-07-16.
 */
public class BarChartActivity1 extends BarChartActivity {
    @Override
    public void addFromSQL(ArrayList<BarEntry> entries, ArrayList<String> labels, String description) {
        DatabaseHelper databaseHelper;
        databaseHelper = new DatabaseHelper(getApplicationContext());
        try {
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();
            entries = databaseHelper.getBarEntryList("Select [Deelgem.], Count(*) as cnt\n" +
                    "From fietstrommels\n" +
                    "Where [Deelgem.] <> \"\"\n" +
                    "Group By [Deelgem.]\n" +
                    "Order By cnt Desc\n" +
                    "Limit 5");
            labels = databaseHelper.getEntryListLabels("Select [Deelgem.], Count(*) as cnt\n" +
                    "From fietstrommels\n" +
                    "Where [Deelgem.] <> \"\"\n" +
                    "Group By [Deelgem.]\n" +
                    "Order By cnt Desc\n" +
                    "Limit 5");
        } catch (Exception e) {
            e.printStackTrace();
        }

        description = "Aantal fietstrommels per wijk";
        super.addFromSQL(entries, labels, description);
    }
}
