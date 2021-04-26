package com.gotham.multithreaded;

public interface ThreadFactory {
    
    Thread createThread(int id, String url);
    
}
