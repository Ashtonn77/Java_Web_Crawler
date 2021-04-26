package com.gotham.multithreaded;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<WebCrawler> bots = new ArrayList<>();    
   
        bots.add(new WebCrawler("https://www.nytimes.com/", 1));
        bots.add(new WebCrawler("https://www.ft.com/", 2));
        bots.add(new WebCrawler("https://www.imdb.com/", 3));

        for(WebCrawler bot : bots){
            
            try {                
                bot.getThread().join();
            }catch (InterruptedException e){
                System.out.println(e.getClass().getSimpleName());
            }
           
            
        }
    
        
    }
    
}
