package getname.group.project_4.charts.factory;

public interface Factory<T> {
    /**
     *  "sql: 'xxx' "
     *  "title: 'xxx' "
     *  "desc: 'desc' "
     *  "color:
     */
    T create(String... args);


}
