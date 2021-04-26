package com.gotham.multithreaded;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<WebCrawler> bots = new ArrayList<>
        (
                List.of
                (

                        new WebCrawler("https://www.nytimes.com/", 1),
                        new WebCrawler("https://en.wikipedia.org/wiki/Main_Page", 2),
                        new WebCrawler("https://www.ft.com/", 3)


                )

        );

        
        for(WebCrawler bot : bots){
            
            try {
                
                bot.getThread().join();
                
            }catch(InterruptedException e){
                
                System.out.println(e.getClass().getSimpleName());
                
            }
            
        }
        
    }
    
}
