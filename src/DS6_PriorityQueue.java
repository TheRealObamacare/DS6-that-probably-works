import java.util.ArrayList;
public class DS6_PriorityQueue<E extends Comparable<E>> implements DS6_PriorityQueueInterface<E>
{
    private ArrayList<E> queue;
    public DS6_PriorityQueue()
    {
        queue= new ArrayList<>();
    }
    public void offer(E o)
    {
        if (queue.isEmpty())
        {
            queue.add(o);
            return;
        }
        for(int i=0; i<queue.size(); i++)
        {
            if(queue.get(i).compareTo(o)>0)
            {
                queue.add(i, o);
                return;
            }
        }
        queue.add(o);
    }
    public E poll()
    {
        if(queue.isEmpty())
            return null;
        E temp= queue.get(0);
        queue.remove(0);
        return temp;
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
    public E get(int x)
    {
        return queue.get(x);
    }
    public E element()
    {
        if (queue.isEmpty())
            return null;
        return queue.get(0);
    }
    @Override
    public String toString()
    {
        return queue.toString();
    }
}
