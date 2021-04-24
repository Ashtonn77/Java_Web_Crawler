package com.gotham.basic;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	
        String url = "https://en.wikipedia.org/wiki/Main_Page";
        crawl(1, url, new ArrayList<String>());
        
    }
    
    private static void crawl(int level, String url, ArrayList<String> visited){
        
        if(level <= 5){
            
            Document document = request(url, visited);
            
            if(document != null){
                
                for(Element link : document.select("a[href]")){
                    
                    String newLink = link.absUrl("href");
                    
                    if(!visited.contains(newLink)){
                        crawl(level++, newLink, visited);
                    }
                    
                }
                
            }
            
            
        }
        
    }
    
    private static Document request(String url, ArrayList<String> visited){
        
        try{

            Connection connection = Jsoup.connect(url);
            Document document = connection.get();
            
            if(connection.response().statusCode() == 200){
                System.out.println("Link: " + url);
                System.out.println("Title: " + document.title());
                
                visited.add(url);
                return document;
                
            }
            
            return null;
            
        }catch (IOException e){
            
            return null;
            
        }
     
    }
    
}
