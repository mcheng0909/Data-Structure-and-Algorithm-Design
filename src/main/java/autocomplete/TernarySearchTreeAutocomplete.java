package autocomplete;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Ternary search tree (TST) implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class TernarySearchTreeAutocomplete implements Autocomplete {
    /**
     * The overall root of the tree: the first character of the first autocompletion term added to this tree.
     */
    private Node overallRoot;

    /**
     * Constructs an empty instance.
     */
    public TernarySearchTreeAutocomplete() {
        overallRoot = null;
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        for (CharSequence term : terms){
            overallRoot= put(overallRoot,term,0);
        }
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {

        if (prefix == null) {
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
        }
        List<CharSequence> result = new ArrayList<>();

        Node x = get(overallRoot, prefix, 0);
        if (x == null) {
            return result;
        }
        if (x.isTerm){
            result.add(prefix);
        }
        collect(x.mid,new StringBuilder(prefix), result);
        return result;
    }

    private Node put(Node curr,CharSequence term, int index){
        char c = term.charAt(index);
        if (curr == null) {
            curr = new Node(c);
        }
        if(c < curr.data){
            curr.left  = put(curr.left, term, index);
        } else if (c > curr.data) {
            curr.right = put(curr.right, term, index);
        } else if (index < term.length() - 1){
            curr.mid  = put(curr.mid, term, index+1);
        } else {
            curr.isTerm=true;
        }
        return curr;
    }

    private Node get(Node curr,CharSequence prefix , int index) {
        if (curr == null){
            return null;
        }
        char c = prefix.charAt(index);
        if(c < curr.data){
            return get(curr.left, prefix, index);
        } else if (c > curr.data) {
            return get(curr.right, prefix, index);
        } else if (index < prefix.length() - 1) {
            return get(curr.mid, prefix, index + 1);
        }else {
            return curr;
        }
    }

    private void collect(Node curr, StringBuilder prefix, List<CharSequence> result) {
        if (curr == null){
            return;
        }
        collect(curr.left,  prefix, result);
        if (curr.isTerm){
            result.add(prefix.toString() + curr.data);
        }
        collect(curr.mid,   prefix.append(curr.data), result);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(curr.right, prefix, result);
    }

    /**
     * A search tree node representing a single character in an autocompletion term.
     */
    private static class Node {
        private final char data;
        private boolean isTerm;
        private Node left;
        private Node mid;
        private Node right;

        public Node(char data) {
            this.data = data;
            this.isTerm = false;
            this.left = null;
            this.mid = null;
            this.right = null;
        }
    }
}
