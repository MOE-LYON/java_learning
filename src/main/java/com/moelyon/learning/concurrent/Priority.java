package com.moelyon.learning.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Priority {
     static volatile boolean nostart = true;
     static volatile boolean notend = true;

    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<>();
        IntStream.range(0,10).forEach((value -> {
            int priority = value<5?Thread.MIN_PRIORITY:Thread.MAX_PRIORITY;

            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job,"Thread "+value);
            thread.setPriority(priority);
            thread.start();
        }));
        nostart = false;
        Thread.sleep(10*1000);
        notend = false;

        jobs.forEach((job -> {
            System.out.println(String.format("Job priority %d count: %d",job.priority,job.jobCount));
        }));

    }

    public static class Job implements Runnable{

        private final int priority;
        private long jobCount;
        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while(nostart){
                Thread.yield();
            }
            while(notend){
                Thread.yield();
                jobCount++;
            }
        }
    }
}
