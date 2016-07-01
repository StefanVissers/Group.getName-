package getname.group.project_4.charts;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

import getname.group.project_4.SQL.DatabaseHelper;

/**
 * Created by floris-jan on 30-06-16.
 */
public class PieChartActivity1 extends PieChartActivity {
    @Override
    public void addFromSQL(ArrayList<Entry> entries, ArrayList<String> labels, String description) {
        DatabaseHelper databaseHelper;
        databaseHelper = new DatabaseHelper(getApplicationContext());
        try {
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();
            entries = databaseHelper.getEntryList("select [kleur], Count(*) as cnt\n" +
                    "from fietsdiefstal\n" +
                    "where [kleur]<>\"\"\n" +
                    "Group By [kleur]\n" +
                    "Order By cnt Desc\n" +
                    "limit 5");
            labels = databaseHelper.getEntryListLabels("select [kleur], Count(*) as cnt\n" +
                    "from fietsdiefstal\n" +
                    "where [kleur]<>\"\"\n" +
                    "Group By [kleur]\n" +
                    "Order By cnt Desc\n" +
                    "limit 5");
        } catch (Exception e) {
            e.printStackTrace();
        }

        description = "Gestolen fietsen per kleur";
        super.addFromSQL(entries, labels, description);
    }
}
