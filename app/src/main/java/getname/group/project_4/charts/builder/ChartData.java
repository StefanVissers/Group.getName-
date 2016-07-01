package getname.group.project_4.charts.builder;

import java.io.Serializable;

import getname.group.project_4.debug.LogHelper;

public class ChartData implements Serializable {
    private final String sql_query; private final String title;
    private final String desc; private final String color;

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

        public ChartData createChartData() {
            return new ChartData(nestedQuery,nestedTitle,nestedDesc,nestedColor);
        }
    }
}