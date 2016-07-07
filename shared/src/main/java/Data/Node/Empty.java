package Data.Node;

/**
 * Created by Lucas on 7/7/2016.
 */
public class Empty implements NodeList{
    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Object Value() {
        return this;
    }

    @Override
    public String Name() {
        return "Empty";
    }

    @Override
    public NodeList Tail() {
        return this;
    }
}
