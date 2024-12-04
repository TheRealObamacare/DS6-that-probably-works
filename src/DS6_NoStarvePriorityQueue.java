import java.util.ArrayList;

public class DS6_NoStarvePriorityQueue<E extends Comparable<E>> extends DS6_PriorityQueue<E>
{
    private ArrayList<DS6_PriorityNode> queue;
    public DS6_NoStarvePriorityQueue()
    {
        queue = new ArrayList<>();
    }
    public void offer(E o)
    {

    }
    public E poll()
    {

    }
    @Override
    public int size()
    {
        return queue.size();
    }
    @Override
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }
    @Override
    public void clear()
    {
        queue.clear();
    }
    @Override
    public E get(int x)
    {
        return (E) queue.get(x).getData();
    }
    @Override
    public E element()
    {
        return (E) queue.getFirst().getData();
    }
}
