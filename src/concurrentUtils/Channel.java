package concurrentUtils;

import java.util.LinkedList;

/**
 * Created by leon2 on 25.05.2017.
 */

public class Channel<T> {
    private final int maxSize;
    private final LinkedList<T> queue = new LinkedList<>();
    private final Object lock = new Object();

    public Channel (int maxSize) {
        this.maxSize = maxSize;
    }

    void put(T x){
        synchronized (lock){
            while (queue.size() == maxSize)
                try{
                    lock.wait();
                }
                catch (InterruptedException e){
                    System.err.println(e);
                }
            queue.addLast(x);
            lock.notifyAll();
        }
    }

    T take(){
        synchronized (lock){
            while (queue.isEmpty())
                try{
                    lock.wait();
                }
                catch (InterruptedException e){
                    System.out.println(e);
                }
            lock.notifyAll();
            return queue.removeFirst();
        }
    }

    boolean isEmpty(){
        synchronized (lock){
            return queue.isEmpty();
        }
    }
}