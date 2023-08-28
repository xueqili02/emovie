package com.groupfour.eMovie.utils.distributedLock;

public interface ILock {

    boolean tryLock(long timeoutSec);

    void unlock();

}
