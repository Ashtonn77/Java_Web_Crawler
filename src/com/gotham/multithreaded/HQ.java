package com.gotham.multithreaded;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HQ {
    
    List<Thread> getBots(ThreadFactory threadFactory){
        
        List<Thread> threads = new ArrayList<>();
        
        try {
           
            Scanner in = new Scanner(System.in);
            System.out.print("\nHow many web crawlers would you like to initialize: ");
            int numBots = in.nextInt();
            in.nextLine();
            
            for(int i = 0; i < numBots; i++){
                
                int _id = i + 1;
                System.out.print("\nEnter url to crawl: ");
                String _url = in.nextLine();
                System.out.println("starting bot #" + _id + " ..");
                
                threads.add(threadFactory.createThread(_id, _url));
                
            }
            return threads;
            
        } catch (Exception e){

            System.out.println("Arg! we've gone and done something wrong - let's try that again, shall we?");
            return getBots(threadFactory);
            
        }
        
                
    }
    
}
