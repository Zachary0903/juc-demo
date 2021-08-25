package juc.singleton;

//终极大招，防止被反射破坏,换句话说，ENUM里面的都是单例的！！
//此处注意,enmu的class文件实际上没有无参构造，用jad反编译出来的（string,int）才是最靠谱的
public enum SingletonEnum {
    INSTANCE;
}
