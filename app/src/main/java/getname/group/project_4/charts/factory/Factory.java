package getname.group.project_4.charts.factory;

/**
 * Created by Lucas on 6/27/2016.
 */
public interface Factory<T> {
    /**
     *  "sql: 'xxx' "
     *  "title: 'xxx' "
     *  "desc: 'desc' "
     *  "color:
     */
    T create(String... args);


}
