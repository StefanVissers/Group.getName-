package Data.builder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import Data.Queries;

public class ChartData implements Serializable {
    private final String sql_query;
    private final String filtered_query;
    private final String desc;
    private final String title;
    private final String color;

    ChartData(final String sql_query, final String title,
              final String desc, final String color,
              final String filtered_query) {
        this.sql_query = sql_query;
        this.filtered_query = filtered_query;
        this.title = title;
        this.desc = desc;
        this.color = color;
    }

    public String getSql_query() {
        return sql_query;
    }
    public String getFiltered_query() {
        return filtered_query;
    }
    public boolean isFiltered() {
        return filtered_query != null;
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
        private String nestedFilter = null;
        private String nestedTitle = "";
        private String nestedDesc = "";
        private String nestedColor = "#000000";

        public ChartDataBuilder(final String nestedQuery) {
            this.nestedQuery = nestedQuery;
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

        public ChartDataBuilder setNestedFilter(String nestedFilter) {
            this.nestedFilter = nestedFilter;

            String OLDQuery = this.nestedQuery;
            List<String> unFilteredQuery = Arrays.asList(OLDQuery.split(" "));
            String filteredQuery = "";

            for (int i = 0; i < unFilteredQuery.size(); i++) {
                unFilteredQuery.set(i, unFilteredQuery.get(i));
                String s = unFilteredQuery.get(i);
                if (unFilteredQuery.get(i).equalsIgnoreCase("Where")) {
                    filteredQuery += "Where ";
                    filteredQuery += this.nestedFilter;
                    filteredQuery += " and ";
                } else {
                    filteredQuery += unFilteredQuery.get(i) + " ";
                }
            }
            nestedFQuery = filteredQuery;
            return this;
        }

        public void tryParse(String type, String value) {
            if (type.equalsIgnoreCase("title")) {
                this.setNestedTitle(value);
            } else if (type.equalsIgnoreCase("desc")) {
                this.setNestedDesc(value);
            } else if (type.equalsIgnoreCase("color")) {
                this.setNestedColor(value);
            } else if (type.equalsIgnoreCase("filter")) {
                this.setNestedFilter(value);
            }
        }

        public ChartData createChartData() {
            return new ChartData(nestedQuery,nestedTitle,nestedDesc,nestedColor,nestedFQuery);
        }
    }
}