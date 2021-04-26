package org.example.dto.util;

public class Mutex {

    private boolean lock;

    public Mutex() {

    }

    public boolean acquireLock() {
        if (!this.lock) {
            this.lock = true;
            return true;
        } else {
            return false;
        }
    }

    public void releaseLock() {
        this.lock = false;
    }
}
