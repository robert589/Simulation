package library;

import java.util.Comparator;
import java.util.Objects;
import entity.Event;
/**
 * Created by user on 6/4/2016.
 */
public class EventPriority implements Comparator<Object> {
    public int compare(Object o1, Object o2){
        Event a1 = (Event)o1;
        Event a2 = (Event) o2;

        double p1 = ((Event) o1).getTime();
        double p2 = ((Event) o2).getTime();

        if (p1 > p2) {
            return 1;
        } else if (p1 < p2){
            return -1;
        } else {
            return 0;
        }
    }
}
