package juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @ClassName ForkJoinDemo
 * @Description TODO
 * @Author ChenYu
 * @Date 2021/8/11 19:59
 **/
public class ForkJoinTask extends RecursiveTask<Long> {
    private Long start;
    private Long end;

    //临界值
    private Long temp = 100000L;

    public ForkJoinTask(Long start, Long end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        if ((end - start) > temp) {  //forkjoin 分支合并计算
            long middle = (start + end) / 2;
            ForkJoinTask task1 = new ForkJoinTask(start, middle);
            task1.fork();  //拆分任务，把任务压入线程队列
            ForkJoinTask task2 = new ForkJoinTask(middle + 1, end);
            task2.fork();  //拆分任务，把任务压入线程队列
            return task1.join() + task2.join();
        } else {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
