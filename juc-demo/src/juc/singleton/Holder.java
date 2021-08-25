package juc.singleton;

//使用静态内部类的方式,但其实这种方式都不安全，都可能被反射给破坏
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
