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

    // Threshold for splitting the task
    private Long temp = 100000L;

    public ForkJoinTask(Long start, Long end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        if ((end - start) > temp) {  // Fork and recursively split the computation
            long middle = (start + end) / 2;
            ForkJoinTask task1 = new ForkJoinTask(start, middle);
            task1.fork();  // Schedule the first subtask in the ForkJoinPool
            ForkJoinTask task2 = new ForkJoinTask(middle + 1, end);
            task2.fork();  // Schedule the second subtask in the ForkJoinPool
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
