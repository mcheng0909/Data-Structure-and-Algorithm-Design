package minpq;

import java.util.*;

/**
 * Unsorted array (or {@link ArrayList}) implementation of the {@link MinPQ} interface.
 *
 * @param <E> the type of elements in this priority queue.
 * @see MinPQ
 */
public class UnsortedArrayMinPQ<E> implements MinPQ<E> {
    /**
     * {@link List} of {@link PriorityNode} objects representing the element-priority pairs in no specific order.
     */
    private final List<PriorityNode<E>> elements;

    /**
     * Constructs an empty instance.
     */
    public UnsortedArrayMinPQ() {
        elements = new ArrayList<>();
    }

    /**
     * Constructs an instance containing all the given elements and their priority values.
     *
     * @param elementsAndPriorities each element and its corresponding priority.
     */
    public UnsortedArrayMinPQ(Map<E, Double> elementsAndPriorities) {
        elements = new ArrayList<>(elementsAndPriorities.size());
        for (Map.Entry<E, Double> entry : elementsAndPriorities.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void add(E element, double priority) {
        if (contains(element)) {
            throw new IllegalArgumentException("Already contains " + element);
        }
        // TODO: Replace with your code 
        //throw new UnsupportedOperationException("Not implemented yet");
        elements.add(new PriorityNode<>(element,priority));
    }

    @Override
    public boolean contains(E element) {
        // TODO: Replace with your code 
        //throw new UnsupportedOperationException("Not implemented yet");
        return elements.contains(new PriorityNode<>(element, 1));
    }

    @Override
    public E peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code 
        //throw new UnsupportedOperationException("Not implemented yet");
        PriorityNode<E> min = elements.get(0);
        for(PriorityNode<E> element:elements){
            if (element.getPriority()< min.getPriority()){
                min = element;
            }
        }
        return min.getElement();
    }

    @Override
    public E removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code 
       // throw new UnsupportedOperationException("Not implemented yet");
       PriorityNode<E> minElement = elements.get(0);
        int minIndex = 0;
        for (int i = 1; i <= elements.size()-1; i++){
            if (elements.get(i).getPriority()< minElement.getPriority()){
                minElement = elements.get(i);
                minIndex = i;
            }
        }
        elements.remove(minIndex);
        return minElement.getElement();
    }

    @Override
    public void changePriority(E element, double priority) {
        if (!contains(element)) {
            throw new NoSuchElementException("PQ does not contain " + element);
        }
        // TODO: Replace with your code 
        //throw new UnsupportedOperationException("Not implemented yet");
        for(PriorityNode<E> entry:elements){
            if(entry.getElement().equals(element)){
                entry.setPriority(priority);
                break;
            }
        }
    }

    @Override
    public int size() {
        // TODO: Replace with your code 
        //throw new UnsupportedOperationException("Not implemented yet");
        return elements.size();
    }
}
