
public class DS6_PriorityNode<E> implements Comparable
{
    private E data;
    private Integer priority;

    public DS6_PriorityNode(E data, Integer priority)
    {
        this.data 		= data;
        this.priority 	= priority;
    }

    public E getData()
    { return data; }

    public void setData(E data)
    { this.data = data; }

    public Integer getPriority()
    { return priority; }

    public void setPriority(Integer priority)
    {  this.priority 	= priority; }

    public int compareTo(Object o)
    {
        DS6_PriorityNode<E> other = (DS6_PriorityNode<E>)o;
        return priority.compareTo(other.getPriority());
    }

    public String toString()
    {
        return "("+data+", "+priority+")";
    }
}
