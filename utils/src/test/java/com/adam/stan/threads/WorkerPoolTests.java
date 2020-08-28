package com.adam.stan.threads;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class WorkerPoolTests {

    @BeforeAll
    static void setMexWorkersNumber() throws Exception {
        Field maxWorkers = WorkerPool.class.getDeclaredField("MAX_WORKERS");
        maxWorkers.setAccessible(true);
        maxWorkers.set(Field.class, 4);
    }

    @Test
    void testExecute_one_task() {
        WorkerPool pool = new WorkerPool();
        Thread thread = new Thread(() -> task(10_000));
        pool.execute(thread);
        List<Informative> workers = pool.getWorkers();
        assertEquals(1, workers.size());
    }

    @Test
    void testExecute_two_task() {
        WorkerPool pool = new WorkerPool();
        Thread thread1 = new Thread(() -> task(10_000));
        Thread thread2 = new Thread(() -> task(10_000));
        pool.execute(thread1);
        pool.execute(thread2);
        List<Informative> workers = pool.getWorkers();
        assertEquals(2, workers.size());
    }

    @Test
    void testExecute_two_task_and_third_when_first_over() {
        WorkerPool pool = new WorkerPool();
        Thread thread1 = new Thread(() -> task(1_000));
        Thread thread2 = new Thread(() -> task(10_000));
        Thread thread3 = new Thread(() -> task(10_000));
        pool.execute(thread1);
        pool.execute(thread2);
        // waiting for task which is completed but worker is on sleep state.
        task(1_100);
        pool.execute(thread3);
        List<Informative> workers = pool.getWorkers();
        assertEquals(2, workers.size());
    }
    @Test
    void testExecute_four_task_and_another_two_and_check_if_two_are_in_queue()
            throws Exception {
        WorkerPool pool = new WorkerPool();
        Thread thread1 = new Thread(() -> task(10_000));
        Thread thread2 = new Thread(() -> task(10_000));
        Thread thread3 = new Thread(() -> task(10_000));
        Thread thread4 = new Thread(() -> task(10_000));
        Thread thread5 = new Thread(() -> task(10_000));
        Thread thread6 = new Thread(() -> task(10_000));
        pool.execute(thread1);
        pool.execute(thread2);
        pool.execute(thread3);
        pool.execute(thread4);
        pool.execute(thread5);
        pool.execute(thread6);
        List<Informative> workers = pool.getWorkers();

        Field queue = pool.getClass().getDeclaredField("queueTasks");
        queue.setAccessible(true);
        @SuppressWarnings("unchecked")
        BlockingQueue<Runnable> trueQueue = ((BlockingQueue<Runnable>) queue
                .get(pool));

        assertEquals(4, workers.size());
        assertEquals(2, trueQueue.size());
    }

    private void task(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
