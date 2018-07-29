package com.raj.clients;

import com.raj.cache.LRUCache;

public class Main {


    public static void main(String[] args) {

        LRUCache cache = new LRUCache(5);

        cache.accessPage(1);
        System.out.println(cache.getMostRecentlyUsedPage());
        cache.accessPage(2);
        System.out.println(cache.getMostRecentlyUsedPage());
        cache.accessPage(3);
        System.out.println(cache.getMostRecentlyUsedPage());
        cache.accessPage(4);
        System.out.println(cache.getMostRecentlyUsedPage());
        cache.accessPage(5);
        System.out.println(cache.getMostRecentlyUsedPage());


        System.out.println("#############");
        System.out.println(cache.getMostRecentlyUsedPage());
        System.out.println(cache.getleastRecentlyUsedPage());
        cache.accessPage(6);
        System.out.println(cache.getMostRecentlyUsedPage());
        System.out.println(cache.getleastRecentlyUsedPage());

    }
}
