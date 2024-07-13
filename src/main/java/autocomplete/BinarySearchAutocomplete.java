package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Binary search implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class BinarySearchAutocomplete implements Autocomplete {
    /**
     * {@link List} of added autocompletion terms.
     */
    private final List<CharSequence> elements;

    /**
     * Constructs an empty instance.
     */
    public BinarySearchAutocomplete() {
        elements = new ArrayList<>();
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {

        elements.addAll(terms);
        Collections.sort(elements, CharSequence::compare);

    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        int start = Collections.binarySearch(elements, prefix, CharSequence::compare);

        if (start<0){
            start = -(start + 1);
        }

        for (int i = start; i < elements.size(); i++) {
            CharSequence term = elements.get(i);
            if (Autocomplete.isPrefixOf(prefix,term)) {
                result.add(term);
            } else {
                break;
            }
        }
        return result;
    }
}
