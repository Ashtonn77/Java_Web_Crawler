package com.gotham.multithreaded;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebCrawler implements Runnable {

    private final int MAX_DEPTH = 3;
    private final List<String> visited;
    private final int _id;
    private final String firstLink;
    private final Thread thread;

    public WebCrawler(String link, int id) {

        visited = new ArrayList<>();
        _id = id;
        firstLink = link;
        thread = new Thread(this);

        thread.start();       
     
        System.out.println("Web crawler: " + id + " -> initialized ...");
    }

    @Override
    public void run() {
        crawl(1, firstLink);
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

                System.out.println("\nBot id: " + _id);
                System.out.println("Inside webpage: " + url);

                String title = document.title();
                System.out.println("Title: " + title);

                visited.add(title);

                return document;

            }

            return null;

        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName());
            return null;
        }

    }

    public Thread getThread() {
        return thread;
    }
}