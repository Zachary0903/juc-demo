package juc.singleton;

// Uses a static nested holder, but reflection can still bypass singleton guarantees.
public class Holder {
    private Holder() {

    }

    public Holder getInstance() {
        return SingleTon.HOLDER;
    }

    public static class SingleTon {
        private static final Holder HOLDER = new Holder();
    }
}
