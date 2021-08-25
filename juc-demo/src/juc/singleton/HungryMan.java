package juc.singleton;

//饿汉式，一上来就初始化对象，容易造成内存浪费，不推荐
public class HungryMan {
    private static final HungryMan HUNGRY_MAN = new HungryMan();

    private HungryMan() {

    }

    public static HungryMan getInstance() {
        return HUNGRY_MAN;
    }
}
