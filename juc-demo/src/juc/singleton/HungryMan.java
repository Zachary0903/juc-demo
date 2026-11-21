package juc.singleton;

// Eager initialization creates the instance immediately and may waste memory.
public class HungryMan {
    private static final HungryMan HUNGRY_MAN = new HungryMan();

    private HungryMan() {

    }

    public static HungryMan getInstance() {
        return HUNGRY_MAN;
    }
}
