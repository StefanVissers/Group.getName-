package Data.builder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import Data.Queries;

public class ChartData implements Serializable {
    private final String sql_query;
    private final String desc;
    private final String title;
    private final String color;
    private final Queries.RelativeTime relativeTime;

    ChartData(final String sql_query, final String title,
              final String desc, final String color,
              final Queries.RelativeTime relativeTime) {
        this.sql_query = sql_query;
        this.title = title;
        this.desc = desc;
        this.color = color;
        this.relativeTime = relativeTime;
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
    public Queries.RelativeTime getRelativeTime() {
        return relativeTime;
    }

    public static class ChartDataBuilder {
        private String nestedQuery;
        private String nestedFQuery = null;
        private String nestedFilter = null;
        private String nestedTitle = "";
        private String nestedDesc = "";
        private String nestedColor = "#000000";
        private Queries.RelativeTime nestedRelativeTime = null;

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
        }

        public void setNestedRelativeTime(Queries.RelativeTime nestedRelativeTime) {
            this.nestedRelativeTime = nestedRelativeTime;
        }

        public ChartData createChartData() {
            return nestedFQuery == null ?
                    new ChartData(nestedQuery,nestedTitle,nestedDesc,nestedColor,nestedRelativeTime) :
                    new ChartData(nestedFQuery,nestedTitle,nestedDesc,nestedColor,nestedRelativeTime);
        }
    }
}