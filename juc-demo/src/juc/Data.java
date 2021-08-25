package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Data
 * @Description TODO
 * @Author ChenYu
 * @Date 2021/7/13 19:55
 **/
public class Data {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public void increment() {
        lock.lock();
        try {

            while (number != 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "==>" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "==>" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
