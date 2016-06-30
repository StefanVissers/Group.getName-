package getname.group.project_4.charts;


public class ChartData {
    String sql_query; String title; String desc; String db_index; String color;
    public String getSql_query() {
        return sql_query;
    }
    public ChartData setSql_query(String sql_query) {
        this.sql_query = sql_query; return this;
    }
    public String getTitle() {
        return title;
    }
    public ChartData setTitle(String title) {
        this.title = title; return this;
    }
    public String getDesc() {
        return desc;
    }
    public ChartData setDesc(String desc) {
        this.desc = desc; return this;
    }
    public String getDb_index() {
        return db_index;
    }
    public ChartData setDb_index(String db_index) {
        this.db_index = db_index; return this;
    }
    public String getColor() {
        return color;
    }
    public ChartData setColor(String color) {
        this.color = color; return this;
    }

    public void parseAdd(String s) {
        String[] splittedString = s.split(": ");
        String type = splittedString[0];
        String arg = splittedString[1];
        if (type.equalsIgnoreCase("sql")) {
            this.setSql_query(s);
        } else if (type.equalsIgnoreCase("title")){
            this.setTitle(s);
        } else if (type.equalsIgnoreCase("desc")) {
            this.setDesc(s);
        } else if (type.equalsIgnoreCase("color")) {
            this.setColor(s);
        } else if (type.equalsIgnoreCase("db")) {
            this.setDb_index(s);
        }
    }
}