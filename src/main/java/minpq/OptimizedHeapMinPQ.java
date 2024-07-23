package minpq;

import java.util.*;

/**
 * Optimized binary heap implementation of the {@link MinPQ} interface.
 *
 * @param <E> the type of elements in this priority queue.
 * @see MinPQ
 */
public class OptimizedHeapMinPQ<E> implements MinPQ<E> {
    /**
     * {@link List} of {@link PriorityNode} objects representing the heap of element-priority pairs.
     */
    private final List<PriorityNode<E>> elements;
    /**
     * {@link Map} of each element to its associated index in the {@code elements} heap.
     */
    private final Map<E, Integer> elementsToIndex;

    /**
     * Constructs an empty instance.
     */
    public OptimizedHeapMinPQ() {
        elements = new ArrayList<>();
        elements.add(null);
        elementsToIndex = new HashMap<>();
    }

    /**
     * Constructs an instance containing all the given elements and their priority values.
     *
     * @param elementsAndPriorities each element and its corresponding priority.
     */
    public OptimizedHeapMinPQ(Map<E, Double> elementsAndPriorities) {
        elements = new ArrayList<>(elementsAndPriorities.size());
        elementsToIndex = new HashMap<>(elementsAndPriorities.size());
        // TODO: Replace with your code 
        //throw new UnsupportedOperationException("Not implemented yet")
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
        elementsToIndex.put(element,elements.size()-1);
        swim(elements.size()-1);
    }

    @Override
    public boolean contains(E element) {
        // TODO: Replace with your code 
        //throw new UnsupportedOperationException("Not implemented yet");
        return elementsToIndex.containsKey(element);
    }

    @Override
    public E peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code 
        //throw new UnsupportedOperationException("Not implemented yet");
        return elements.get(1).getElement();
    }

    @Override
    public E removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code 
        //throw new UnsupportedOperationException("Not implemented yet");
        E removeNode = elements.get(1).getElement();
        swap(1,elements.size()-1);
        elements.remove(elements.size()-1);
        elementsToIndex.remove(removeNode);
        if(!isEmpty()){
            sink(1);
        }
        return removeNode;
    }

    @Override
    public void changePriority(E element, double priority) {
        if (!contains(element)) {
            throw new NoSuchElementException("PQ does not contain " + element);
        }
        // TODO: Replace with your code 
        //throw new UnsupportedOperationException("Not implemented yet");
        int index = elementsToIndex.get(element);
        double n = elements.get(index).getPriority();
        elements.get(index).setPriority(priority);
        if (priority<n){
            swim(index);
        }else {
            sink(index);
        }

    }

    @Override
    public int size() {
        // TODO: Replace with your code 
        //throw new UnsupportedOperationException("Not implemented yet");
        return elements.size()-1;
    }

    private void swim(int index){
        while (index > 1) {
            int parent = index / 2;
            if (elements.get(index).getPriority() < elements.get(parent).getPriority()) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }
    private void sink(int index){
        int size = elements.size();
        while (2 * index +1 <= size) {
            int i = 2 * index;
            if (i+1 < size && elements.get(i+1).getPriority() < elements.get(i).getPriority()) {
                i++;
            }
            if (elements.get(index).getPriority() <= elements.get(i).getPriority()) {
                break;
            }
            swap(index, i);
            index = i;
        }
    }
    private void swap(int i, int j){
        PriorityNode<E> n = elements.get(i);
        elements.set(i,elements.get(j));
        elements.set(j,n);
        elementsToIndex.put(elements.get(i).getElement(),i);
        elementsToIndex.put(elements.get(j).getElement(),j);
    }
}