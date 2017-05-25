package concurrentUtils;

import java.util.LinkedList;

/**
 * Created by leon2 on 25.05.2017.
 */

public class ThreadPool {
    private final LinkedList<WorkerThread> allWorkers;
    private final Channel<WorkerThread> freeWorkers;
    private final int maxSize;

    public ThreadPool(int maxSize)
    {
        this.maxSize = maxSize;
        this.freeWorkers = new Channel<> (maxSize);
        this.allWorkers = new LinkedList<> ();
        WorkerThread workerThread = new WorkerThread (this); /* starts implicitly */
        allWorkers.add (workerThread);
        freeWorkers.put (workerThread);
    }

    public void execute(Runnable task) {
        if (freeWorkers.isEmpty () && (allWorkers.size () < maxSize))
        {
            WorkerThread workerThread = new WorkerThread (this);
            allWorkers.addLast(workerThread);
            freeWorkers.put(workerThread);
        }

        freeWorkers.take().execute(task);
    }
    void onTaskCompleted(WorkerThread workerThread) {
        freeWorkers.put (workerThread);
    }

    public void stop () {
        for (WorkerThread wt : allWorkers) {
            wt.stop();
        }
    }
}