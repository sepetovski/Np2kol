package zadaca.SuperString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

public class SuperString {
    private LinkedList<String> listOfStrings;
    private LinkedList<Integer> undo_stack;

    public SuperString() {
        listOfStrings = new LinkedList<>();
        undo_stack = new LinkedList<Integer>();
    }

    public void append(String next) {
        listOfStrings.addLast(next);
        undo_stack.addFirst(1);
    }

    public void insert(String next) {
        listOfStrings.addFirst(next);
        undo_stack.addFirst(-1);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for ( String s : listOfStrings )    sb.append(s);
        return sb.toString();
    }
    public boolean contains(String next) {
        return  toString().contains(next);
    }

    public void reverse() {
        for (ListIterator<String> it = listOfStrings.listIterator(); it.hasNext() ; )
            it.set(new StringBuilder(it.next()).reverse().toString());
        Collections.reverse(listOfStrings);
        for ( ListIterator<Integer> it = undo_stack.listIterator() ; it.hasNext() ; )
            it.set(it.next()*(-1));
    }

    public void removeLast(int k) {
        for ( int i = 0 ; i < k ; ++i )
            if ( undo_stack.removeFirst() < 0  ) listOfStrings.removeFirst();
            else listOfStrings.removeLast();
    }
}
