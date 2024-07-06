package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Sequential search implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class SequentialSearchAutocomplete implements Autocomplete {
    /**
     * {@link List} of added autocompletion terms.
     */
    private final List<CharSequence> elements;

    /**
     * Constructs an empty instance.
     */
    public SequentialSearchAutocomplete() {
        elements = new ArrayList<>();
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        // TODO: Replace with your code 
       // throw new UnsupportedOperationException("Not implemented yet");
       elements.addAll(terms);
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        // TODO: Replace with your code 
        //throw new UnsupportedOperationException("Not implemented yet");
        List<CharSequence> result = new ArrayList<>();
        for (CharSequence c:elements){
            if(Autocomplete.isPrefixOf(prefix,c)){
                result.add(c);
            }
        }
        return result;
    }
}
