package com.adam.stan.threads;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class WorkerPoolTests {

    @Test
    void testExecute_one_task() {
        WorkerPool pool = new WorkerPool();
        Thread thread = new Thread(() -> task(10_000));
        pool.execute(thread);
        List<Worker> workers = pool.getWorkers();
        assertEquals(1, workers.size());
    }

    @Test
    void testExecute_two_task() {
        WorkerPool pool = new WorkerPool();
        Thread thread1 = new Thread(() -> task(10_000));
        Thread thread2 = new Thread(() -> task(10_000));
        pool.execute(thread1);
        pool.execute(thread2);
        List<Worker> workers = pool.getWorkers();
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
        List<Worker> workers = pool.getWorkers();
        assertEquals(2, workers.size());
    }

    private void task(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
