package com.gotham.multithreaded;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebCrawler implements Runnable {

    private static final int MAX_DEPTH = 3;
    private final String firstLink;
    private List<String> visited;
    private final int ID;
    private final Thread thread;

    public WebCrawler(String link, int id) {

        visited = new ArrayList<>();
        System.out.println("Web crawler initialized ...");

        firstLink = link;
        ID = id;
        thread = new Thread(this);

        thread.start();

    }

    private void crawl(int level, String url) {

        if (level <= MAX_DEPTH) {

            Document document = request(url);

            if (document != null) {

                for (Element link : document.select("a[href]")) {

                    String nextLink = link.absUrl("href");
                    if (!visited.contains(nextLink)) {
                        crawl(level++, nextLink);
                    }

                }

            }

        }


    }

    private Document request(String url) {

        try {

            Connection connection = Jsoup.connect(url);
            Document document = connection.get();

            if (connection.response().statusCode() == 200) {

                System.out.println("\nBot id: " + ID + " --> Received webpage at " + url);

                String title = document.title();
                System.out.println("Title: " + title);                
                visited.add(title);

                return document;

            }

            return null;

        } catch (IOException e) {
            return null;
        }

    }

    @Override
    public void run() {
        crawl(1, firstLink);
    }
    
    public Thread getThread(){
        return thread;
    }
    
    
}
