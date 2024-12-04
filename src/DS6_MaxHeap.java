import java.util.ArrayList;

public class DS6_MaxHeap<E extends Comparable<E>> implements DS6_HeapInterface<E> 
{
    private ArrayList<E> heap;
    public DS6_MaxHeap()
    {
        heap= new ArrayList<>();
    }
    public DS6_MaxHeap(ArrayList<E> heap) 
    {
        this.heap = heap;
    }
    @Override
    public boolean insert(E item)
    {
        heap.add(item);
        heapifyUp();
        return true;
    }
    private void heapifyUp()
    {
        int index = heap.size()-1;
        while(index>0)
        {
            int p= (index-1)/2;
            E cur= heap.get(index);
            E parent= heap.get(p);
            if(cur.compareTo(parent)>0)
            {
                heap.set(index, parent);
                heap.set(p, cur);
                index=p;
            }
            else
                break;
        }
    }
    @Override
    public E remove()
    {
        if(heap.isEmpty())
            return null;
        E removedItem= heap.get(0);
        heap.set(0, heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        heapifyDown();
        return removedItem;
    }
    private void heapifyDown()
    {
        int k=0;
        int l=2*k+1;
        while(l<heap.size())
        {
            int max=l, r=l+1;
            if(r<heap.size())
            {
                if(heap.get(r).compareTo(heap.get(l))>0)
                    max++;
            }
            if(heap.get(k).compareTo(heap.get(max))<0)
            {
                E temp= heap.get(k);
                heap.set(k, heap.get(max));
                heap.set(max, temp);
                k=max;
                l=2*k+1;
            }
            else
                break;
        }
    }
    @Override
    public boolean isEmpty()
    {
        return heap.isEmpty();
    }
    @Override
    public int size()
    {
        return heap.size();
    }
    @Override
    public void clear()
    {
        heap.clear();
    }
    @Override
    public String toString()
    {
        return heap.toString();
    }
}
