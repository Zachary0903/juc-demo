package juc.singleton;


// Lazy initialization
public class LazyMan {

    private volatile static LazyMan lazyMan; // volatile prevents instruction reordering

    private LazyMan() {
        System.out.println(Thread.currentThread().getName() + "build OK");
    }

    // Double-checked locking prevents concurrency issues but was historically vulnerable to reordering
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan(); // This is not an atomic operation
                }
                //  1. Allocate memory
                //  2. Invoke the constructor
                //  3. Assign the reference to the new object
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> getInstance()).start();
        }
    }
}
