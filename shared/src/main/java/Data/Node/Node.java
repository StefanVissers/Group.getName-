package Data.Node;

/**
 * Created by Lucas on 7/7/2016.
 */
public class Node<T> implements NodeList<T> {
    private T value;
    private String name;
    private NodeList tail;

    public Node(T value, String name, NodeList tail) {
        this.value = value;
        this.name = name;
        this.tail = tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public T Value() {
        return value;
    }

    @Override
    public String Name() {
        return name;
    }

    @Override
    public NodeList Tail() {
        return tail;
    }
}
