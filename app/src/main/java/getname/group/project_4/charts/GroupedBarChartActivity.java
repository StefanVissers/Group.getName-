package getname.group.project_4.charts;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
import Data.Queries;
import getname.group.project_4.R;
import getname.group.project_4.SQL.DatabaseHelper;
import getname.group.project_4.SQL.Dates;
import getname.group.project_4.activities.ActivityExtender;
import Data.builder.ChartData;
import getname.group.project_4.debug.LogHelper;

public class GroupedBarChartActivity extends ActivityExtender implements Chart {
    private static int counter = 0;
    private BarChart barchart;
    ArrayList<IBarDataSet> dataGroups = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();
    String title;
    String description;
    List<ChartData> chartDatas = new ArrayList<>();
    ListView listView1;
    ListView listView2;
    int filterYear = 2008;
    String filterWijk1;
    String filterWijk2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_groupedbarchart);

        Intent intent = getIntent();
        barchart = (BarChart) findViewById(R.id.chart);
        DatabaseHelper databaseHelper;
        databaseHelper = new DatabaseHelper(this);

        listView1 = (ListView) findViewById(R.id.listView1);
        listView2 = (ListView) findViewById(R.id.listView2);

        try {
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] values1 = databaseHelper.getPickAreaList(Queries.getFietsdiefstalAreas());
        String[] values2 = databaseHelper.getPickAreaList(Queries.getFietstrommelsAreas());

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values2);


        // Assign adapter to ListView
        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                filterWijk1 = listView1.getItemAtPosition(position).toString();
                ChartData chartData = chartDatas.get(0);
                ChartData.ChartDataBuilder builder =
                        new ChartData.ChartDataBuilder(chartData.getSql_query())
                                .setNestedTitle(chartData.getTitle())
                                .setNestedDesc(chartData.getDesc())
                                .setNestedColor(chartData.getColor())
                                .setNestedFilter("[Werkgebied] = '" + filterWijk1 + "'");

                getIntent().putExtra("ChartData" + 0, builder.createChartData());
                recreate();
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                filterWijk2 = listView2.getItemAtPosition(position).toString();
                ChartData chartData = chartDatas.get(1);
                ChartData.ChartDataBuilder builder =
                        new ChartData.ChartDataBuilder(chartData.getSql_query())
                                .setNestedTitle(chartData.getTitle())
                                .setNestedDesc(chartData.getDesc())
                                .setNestedColor(chartData.getColor())
                                .setNestedFilter("[Deelgem.] = '" + filterWijk2 + "'");
                getIntent().putExtra("ChartData" + 1, builder.createChartData());
                recreate();
            }
        });

        for (int i = 0; i < getIntent().getIntExtra("cdAmount", -1); i++) {
            chartDatas.add( (ChartData) getIntent().getSerializableExtra("ChartData" + i));
        }

        try {
            databaseHelper.createDataBase();
            databaseHelper.openDataBase();

            List<BarEntry> group1 = new ArrayList<>();
            group1.addAll(filterEntries(databaseHelper, 0, false));
            List<BarEntry> group2 = new ArrayList<>();
            group2.addAll(filterEntries(databaseHelper, 1, true));

            BarDataSet group1Set = new BarDataSet(group1, chartDatas.get(0).getTitle());
            BarDataSet group2Set = new BarDataSet(group2, chartDatas.get(1).getTitle());

            group1Set.setColors(ColorTemplate.JOYFUL_COLORS);
            group2Set.setColors(ColorTemplate.COLORFUL_COLORS);

            dataGroups.add(group1Set);
            dataGroups.add(group2Set);

            labels.addAll(Dates.AllMonths(filterYear));

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

        if (chartDatas.get(table_index).isFiltered()) {
            if (is_cumulative) {
                inputYearList = databaseHelper.getEntryListLabels(chartData.getFiltered_query(), 0);
                inputMonthList = databaseHelper.getEntryListLabels(chartData.getFiltered_query(), 1);
                inputValueList = databaseHelper.getCumulativeSum(chartData.getFiltered_query(), 2);
            } else {
                inputYearList = databaseHelper.getEntryListLabels(chartData.getFiltered_query(), 0);
                inputMonthList = databaseHelper.getEntryListLabels(chartData.getFiltered_query(), 1);
                inputValueList = databaseHelper.getBarEntryList(chartData.getFiltered_query(), 2);
            }
        } else {
            if (is_cumulative) {
                inputYearList = databaseHelper.getEntryListLabels(chartData.getSql_query(), 0);
                inputMonthList = databaseHelper.getEntryListLabels(chartData.getSql_query(), 1);
                inputValueList = databaseHelper.getCumulativeSum(chartData.getSql_query(), 2);
            } else {
                inputYearList = databaseHelper.getEntryListLabels(chartData.getSql_query(), 0);
                inputMonthList = databaseHelper.getEntryListLabels(chartData.getSql_query(), 1);
                inputValueList = databaseHelper.getBarEntryList(chartData.getSql_query(), 2);
            }
        }

        // Putting years, months and values in Nodes in an ArrayList
        List<NodeList> inputNodes = new ArrayList<>();
        int index = 0;
        boolean adjusted = false;
        while (true) {
            if (index >= inputValueList.size()) {
                break;
            }

            if (Integer.parseInt(inputYearList.get(index)) < filterYear) {
                // Function does not handle data before the year 2000
                index++;
                adjusted = true;
                continue;
            }

            if (is_cumulative && adjusted) {
                index--;
                adjusted = false;
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
        List<NodeList> datesList = Dates.MonthsAsNumberPerYear(filterYear);

        ArrayList<BarEntry> outputData = new ArrayList<>();

        // Checking year and Month. Filling output.
        int inputNodesIndex = 0;
        int dateNodesIndex = 0;
        int nodeOffset = 0;
        float cumulativeOffset = is_cumulative ? ((BarEntry) inputNodes.get(0).Tail().Tail().Value()).getVal() : 0;
        boolean matchedInputYear = false;
        boolean matchedInputMonth = false;
        boolean matchedInputs = false;

        while (true) {
            if (!matchedInputs && inputNodesIndex >= inputNodes.size()-1) {
                matchedInputs = true;
            }

            if (!matchedInputs) {
                NodeList dateNode = null;
                NodeList inputNode = null;
                try {
                    dateNode = datesList.get(dateNodesIndex);
                } catch (IndexOutOfBoundsException e) { LogHelper.logErrorMessage("filterEntries", "Stuck on index " + dateNodesIndex); }
                try {
                    inputNode = inputNodes.get(inputNodesIndex);
                } catch (IndexOutOfBoundsException e) { LogHelper.logErrorMessage("filterEntries", "Stuck on index " + inputNodesIndex); }

                if (dateNode == null || inputNode == null) {
                    return outputData;
                }

                for (int i = 0; i < nodeOffset; i++) {
                    dateNode = dateNode.Tail();
                    inputNode = inputNode.Tail();
                }

                if (!matchedInputYear) {
                    // Match year
                    if (((String) dateNode.Value()).equals((String) inputNode.Value())) {
                        matchedInputYear = true;
                        nodeOffset = 1;
                    } else if (is_cumulative) {
                        if (dateNodesIndex == 0) {
                            outputData.add(dateNodesIndex, new BarEntry(cumulativeOffset + 0f, dateNodesIndex));
                        } else {
                            outputData.add(dateNodesIndex, new BarEntry(outputData.get(dateNodesIndex - 1).getVal(), dateNodesIndex));
                        }
                        dateNodesIndex++;
                    } else {
                        outputData.add(dateNodesIndex, new BarEntry(cumulativeOffset + 0f, dateNodesIndex));
                        dateNodesIndex++;
                    }
                } else if (matchedInputYear && !matchedInputMonth) {
                    // Match Month
                    if (((String) dateNode.Value()).equals((String) inputNode.Value())) {
                        matchedInputMonth = true;
                        // Move node to value in tail
                        nodeOffset = 2;
                    } else if (is_cumulative) {
                        if (dateNodesIndex == 0) {
                            outputData.add(dateNodesIndex, new BarEntry(cumulativeOffset + 0f, dateNodesIndex));
                        } else {
                            outputData.add(dateNodesIndex, new BarEntry(outputData.get(dateNodesIndex - 1).getVal(), dateNodesIndex));
                        }
                        dateNodesIndex++;
                    } else {
                        // Index == 0
                        outputData.add(dateNodesIndex, new BarEntry(cumulativeOffset + 0f, dateNodesIndex));
                        dateNodesIndex++;
                    }
                } else {
                    outputData.add(dateNodesIndex, new BarEntry(((BarEntry) inputNode.Value()).getVal(), dateNodesIndex));
                    inputNodesIndex++;
                    dateNodesIndex++;
                    matchedInputYear = false;
                    matchedInputMonth = false;
                    nodeOffset = 0;
                }
            } else if (outputData.size() < datesList.size()) {
                if (is_cumulative) {
                    outputData.add(dateNodesIndex, new BarEntry(outputData.get(dateNodesIndex - 1).getVal(), dateNodesIndex));
                } else {
                    outputData.add(dateNodesIndex, new BarEntry(cumulativeOffset + 0f, dateNodesIndex));
                }
                dateNodesIndex++;
            } else {
                return outputData;
            }
        }
    }

    @Override
    public void addData(ChartData cd) {
        this.chartDatas.add(cd);
    }

    public List<ChartData> getData() {
        return chartDatas;
    }
}
