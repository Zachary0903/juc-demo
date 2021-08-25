package juc.singleton;


//懒汉式
public class LazyMan {

    private volatile static LazyMan lazyMan; //加volatile防止指令重排

    private LazyMan() {
        System.out.println(Thread.currentThread().getName() + "build OK");
    }

    //DCL双重检测锁 可以防止并发问题，但是不能防止指令重排
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan(); //但这不是一个原子性的操作
                }
                //  1.在内存里分配新空间
                //  2.通过构造函数创建对象
                //  3.把对象指向新工间
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
