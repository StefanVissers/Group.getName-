package getname.group.project_4.charts;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import Data.Node.Empty;
import Data.Node.Node;
import Data.Node.NodeList;
import getname.group.project_4.R;
import getname.group.project_4.SQL.DatabaseHelper;
import getname.group.project_4.SQL.Dates;
import getname.group.project_4.activities.ActivityExtender;
import Data.builder.ChartData;
import getname.group.project_4.debug.LogHelper;

public class GroupedBarChartActivity extends ActivityExtender implements Chart {
    private static int counter = 0;
    private BarChart barchart;
    ArrayList<BarEntry> entries = new ArrayList<>();
    ArrayList<IBarDataSet> dataGroups = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();
    String title;
    String description;
    List<ChartData> chartDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_groupedbarchart);

        ID = counter++;
        LogHelper.logDebugMessage("CREATE", this);

        Intent intent = getIntent();
        barchart = (BarChart) findViewById(R.id.chart);

        for (int i = 0; i < getIntent().getIntExtra("cdAmount", -1); i++) {
            chartDatas.add( (ChartData) getIntent().getSerializableExtra("ChartData" + i));
        }

        DatabaseHelper databaseHelper;
        databaseHelper = new DatabaseHelper(this);
        try {
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();

            List<BarEntry> group1 = new ArrayList<>();
            group1.addAll(filterEntries(databaseHelper,0, false));
            List<BarEntry> group2 = new ArrayList<>();
            group2.addAll(filterEntries(databaseHelper,1, true));

            BarDataSet group1Set = new BarDataSet(group1, chartDatas.get(0).getTitle());
            BarDataSet group2Set = new BarDataSet(group2, chartDatas.get(1).getTitle());

            group1Set.setColors(ColorTemplate.JOYFUL_COLORS);
            group2Set.setColors(ColorTemplate.COLORFUL_COLORS);

            dataGroups.add(group1Set);
            dataGroups.add(group2Set);

            // TODO: Add all months to labels
            labels.addAll(Dates.AllMonths(null));

            description = chartDatas.get(0).getDesc();
            title = chartDatas.get(0).getTitle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        BarData data = new BarData(labels, dataGroups);
        barchart.setData(data);
        barchart.setDescription(description);
        barchart.setVisibleXRangeMaximum(2);
        barchart.animateXY(3000, 3000);

    }

    private ArrayList<BarEntry> filterEntries(DatabaseHelper databaseHelper, int table_index, boolean is_cumulative) {
        ArrayList<String> inputYearList;
        ArrayList<String> inputMonthList;
        ArrayList<BarEntry> inputValueList;
        ChartData chartData = chartDatas.get(table_index);
        ArrayList<BarEntry> filteredEntries = new ArrayList<>();

        if (chartDatas.get(table_index).isFiltered()) {
            inputYearList = databaseHelper.getEntryListLabels(chartData.getFiltered_query(), 0);
            inputMonthList = databaseHelper.getEntryListLabels(chartData.getFiltered_query(), 1);
            inputValueList =  databaseHelper.getBarEntryList(chartData.getFiltered_query(), 2);
        } else {
            inputYearList = databaseHelper.getEntryListLabels(chartData.getSql_query(), 0);
            inputMonthList = databaseHelper.getEntryListLabels(chartData.getSql_query(), 1);
            inputValueList = databaseHelper.getBarEntryList(chartData.getSql_query(), 2);
        }

        // Putting years, months and values in Nodes in an ArrayList
        List<NodeList> inputNodes = new ArrayList<>();
        int index = 0;
        while (true) {
            if (index >= inputValueList.size()) {
                break;
            }

            if (inputMonthList.get(index).length() == 1) {
                inputMonthList.set(index, "0" + inputMonthList.get(index));
            }

            inputNodes.add(new Node<>(inputYearList.get(index), "Year",
                            new Node<>(inputMonthList.get(index), "MonthNR",
                            new Node<>(inputValueList.get(index), "Value",
                            new Empty()))));
            index++;
        }

        // Getting a list of years and their months
        List<NodeList> datesList = Dates.MonthsAsNumberPerYear();

        ArrayList<BarEntry> outputData = new ArrayList<>();

        // CHECKING YEAR AND MONTH
        int inputNodesIndex = 0;
        int dateNodesIndex = 0;
        int nodeOffset = 0;

        boolean matchedInputYear = false;
        boolean matchedInputMonth = false;
        while (true) {
            if (inputNodesIndex >= inputNodes.size()) {
                if (dateNodesIndex >= datesList.size()) {
                    break;
                }

                outputData.add(new BarEntry(0f, dateNodesIndex));
                dateNodesIndex++;
                continue;
            }

            NodeList dateNode = datesList.get(dateNodesIndex);
            NodeList inputNode = inputNodes.get(inputNodesIndex);

            for (int i = 0; i < nodeOffset; i++) {
                dateNode = dateNode.Tail();
                inputNode = inputNode.Tail();
            }

            if (index == 144) {
                LogHelper.logDebugMessage("Kappa", "Ross");
            }

            if (!matchedInputYear) {
                // Match year
                if (((String)dateNode.Value()).equals((String)inputNode.Value())) {
                    matchedInputYear = true;
                    nodeOffset = 1;
                } else if (!(dateNodesIndex == 0) && is_cumulative) {
                    outputData.add(dateNodesIndex, new BarEntry(outputData.get(dateNodesIndex-1).getVal(), dateNodesIndex));
                    dateNodesIndex++;
                } else {
                    outputData.add(dateNodesIndex, new BarEntry(0f, dateNodesIndex));
                    dateNodesIndex++;
                }
            } else if (matchedInputYear && !matchedInputMonth) {
                // Match Month
                if (((String)dateNode.Value()).equals((String)inputNode.Value())) {
                    matchedInputMonth = true;
                    // Move node to value in tail
                    nodeOffset = 2;
                } else if (!(dateNodesIndex == 0) && is_cumulative) {
                    outputData.add(dateNodesIndex, new BarEntry(outputData.get(dateNodesIndex-1).getVal(), dateNodesIndex));
                    dateNodesIndex++;
                } else {
                    outputData.add(dateNodesIndex, new BarEntry(0f, dateNodesIndex));
                    dateNodesIndex++;
                }
            } else if (matchedInputYear && matchedInputMonth) {
                outputData.add(dateNodesIndex, new BarEntry(((BarEntry) inputNode.Value()).getVal(),dateNodesIndex));
                LogHelper.logDebugMessage("filterDates", "Matched Entry Position: " + dateNodesIndex + " Value: " + ((BarEntry) inputNode.Value()).getVal());
                inputNodesIndex++;
                dateNodesIndex++;
                matchedInputYear = false; matchedInputMonth = false;
                nodeOffset = 0;
            } else {
                LogHelper.logDebugMessage("filterDates", "Unexpected Error");
            }
        }

        return outputData;
    }

    @Override
    public void addData(ChartData cd) {
        this.chartDatas.add(cd);
        LogHelper.logDebugMessage("ADD_DATA", this);
    }

    public List<ChartData> getData() {
        return chartDatas;
    }
}
