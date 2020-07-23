package center.uhc.core.commons;

import java.util.ArrayList;

public enum QueueType {

    BYPASS(8),
    STAFFHIGH(7),
    STAFFLOW(6),
    PREMIUMHIGH(5),
    DONATOR3(4),
    DONATOR2(3),
    DONATOR1(2),
    REGULAR(1);

    private int priorityLevel;

    private QueueType(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getPriorityLevel() { return priorityLevel; }

    public static ArrayList<QueueType> getOrder() {
        ArrayList<QueueType> r = new ArrayList<>();
        r.add(BYPASS);
        r.add(STAFFHIGH);
        r.add(STAFFLOW);
        r.add(PREMIUMHIGH);
        r.add(DONATOR3);
        r.add(DONATOR2);
        r.add(DONATOR1);
        r.add(REGULAR);

        return r;
    }
}
