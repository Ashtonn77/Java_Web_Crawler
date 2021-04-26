package com.gotham.multithreaded;

import java.util.List;

public class Main {

    public static void main(String[] args){
        
        BotFactory botFactory = WebCrawler::new;

        ThreadFactory threadFactory = (id, url) -> new Thread(() -> botFactory.createBot(id).crawl(1, url));
        
        List<Thread> threads = new HQ().getBots(threadFactory);

        for(var thread : threads){
            thread.start();
        }
         
    }
    
}
