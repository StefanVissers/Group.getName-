package getname.group.project_4.charts.builder;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import getname.group.project_4.debug.LogHelper;

public class ChartData implements Serializable {
    private final String sql_query;
    private final String desc;
    private final String title;
    private final String color;

    ChartData(final String sql_query, final String title,
              final String desc, final String color) {
        this.sql_query = sql_query;
        this.title = title;
        this.desc = desc;
        this.color = color;
    }

    public String getSql_query() {
        return sql_query;
    }
    public String getTitle() {
        return title;
    }
    public String getDesc() {
        return desc;
    }
    public String getColor() {
        return color;
    }

    public static class ChartDataBuilder {
        private String nestedQuery;
        private String nestedFQuery = null;
        private String nestedFilter;
        private String nestedTitle = "";
        private String nestedDesc = "";
        private String nestedColor = "#000000";

        public ChartDataBuilder(final String newQuery) {
            nestedQuery = newQuery;
        }

        public ChartDataBuilder setNestedTitle(String nestedTitle) {
            this.nestedTitle = nestedTitle;
            return this;
        }

        public ChartDataBuilder setNestedDesc(String nestedDesc) {
            this.nestedDesc = nestedDesc;
            return this;
        }

        public ChartDataBuilder setNestedColor(String nestedColor) {
            this.nestedColor = nestedColor;
            return this;
        }

        public void setNestedFilter(String nestedFilter) {
            this.nestedFilter = nestedFilter;

            String OLDQuery = this.nestedQuery;
            List<String> unFilteredQuery = Arrays.asList(OLDQuery.split(" "));
            List<String> filteredQuery = new ArrayList<>();

            for (int i = 0; i < unFilteredQuery.size(); i++) {
                unFilteredQuery.set(i, unFilteredQuery.get(i) + " ");
                String s = unFilteredQuery.get(i);
                if (unFilteredQuery.get(i).equalsIgnoreCase("(Begindatum ")) {
//                    filteredQuery.add("where ");
//                    filteredQuery.add(this.nestedFilter);
//                    filteredQuery.add(" and ");
                    filteredQuery.add(i, "Cast(jaar AS INTEGER) " + this.nestedFilter);
                    filteredQuery.add(" and ");
                    filteredQuery.add("(Begindatum ");
                } else {
                    filteredQuery.add(unFilteredQuery.get(i));
                }
            }

            String newQuery = TextUtils.join("", filteredQuery);
            LogHelper.logDebugMessage("FILTER", newQuery);
            nestedFQuery = newQuery;
        }

        public ChartData createChartData() {
//            return new ChartData(nestedQuery,nestedTitle,nestedDesc,nestedColor);
            return nestedFQuery == null ?
                    new ChartData(nestedQuery,nestedTitle,nestedDesc,nestedColor) :
                    new ChartData(nestedFQuery,nestedTitle,nestedDesc,nestedColor);
        }
    }
}