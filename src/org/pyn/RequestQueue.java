package org.pyn;

import org.pyn.message.Request;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by pyn on 2016/11/2.
 */
public class RequestQueue {
    private LinkedList<Request> queue;
    private Lock lock;
    private Condition cond;

    public RequestQueue() {
        this.queue = new LinkedList<Request>();
        this.lock = new ReentrantLock();
        this.cond = lock.newCondition();
    }

    public void push(Request req) {
        lock.lock();
        queue.add(req);
        cond.signalAll();
        lock.unlock();
    }

    public Request pop() {
        Request req = null;
        lock.lock();
        if(!queue.isEmpty()) {
            req = queue.pop();
        } else {
            try {
                cond.await();
                req = queue.pop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock.unlock();
        return req;
    }
}

