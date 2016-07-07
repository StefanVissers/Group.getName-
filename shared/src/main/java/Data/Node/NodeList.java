package Data.Node;

/**
 * Created by Lucas on 7/7/2016.
 */
public interface NodeList<T> {
    boolean isEmpty();
    T Value();
    String Name();
    NodeList Tail();
}
