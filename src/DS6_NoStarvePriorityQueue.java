public class DS6_NoStarvePriorityQueue<E extends DS6_PriorityNode> extends DS6_PriorityQueue<E> {

    @Override
    public E poll() {
        if (queue.isEmpty())
            return null;
        E yay = queue.remove(0);
        decrement();
        return yay;
    }

    public void decrement() {
        for (DS6_PriorityNode i : queue)
            i.setPriority(i.getPriority() - 1);
    }
}