package com.raj.cache;

import java.util.HashMap;

// stores pages (represented by Node in doubly linked list)
public class LRUCache {

    private final int cacheSize;
    private final HashMap<Integer, DoublyLinkedList.Node> map = new HashMap<>();
    private DoublyLinkedList list = null;

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        list = new DoublyLinkedList(cacheSize);
    }

    public void accessPage(int pageNumber) {

        // cache contains pageNumber, Get the reference from the map
        // and mark as mostly used by moving it top of cache
        if(map.containsKey(pageNumber)) {
            DoublyLinkedList.Node pageRef = map.get(pageNumber);
            list.moveToHead(pageRef);
            return;
        }

        // cache does not contain the referred page

        // if cache is full, remove least recently used item from cache
        if(list.isFull()) {
            list.removeTailNode();
        }

        // add the page to cache
        DoublyLinkedList.Node newPageNode = list.insert(pageNumber);

        // add the page to map
        map.put(pageNumber, newPageNode);

    }

    public int getMostRecentlyUsedPage() {
        return list.head.pageNumber;
    }

    public int getleastRecentlyUsedPage() {
        return list.tail.pageNumber;
    }

    class DoublyLinkedList {

         class Node {

             int pageNumber;
             Node next = null;
             Node previous = null;

             Node(int pageNumber) {
                 this.pageNumber = pageNumber;
             }

             public int getPageNumber() {
                 return pageNumber;
             }

             public Node getNext() {
                 return next;
             }

             public Node getPrevious() {
                 return previous;
             }

             public void setNext(Node next) {
                 this.next = next;
             }

             public void setPrevious(Node previous) {
                 this.previous = previous;
             }
         }

         final int size;
         Node head = null, tail = null;
         int countofNodes = 0;


        DoublyLinkedList(int size) {
            this.size = size;
        }

        void moveToHead(Node page) {

            if(page == head) {
                return;
            }

            // set this page's previous page's next to this page's next
            page.getPrevious().setNext(page.getNext());

            // set this page's next page's previous to point to this page's previous page
            if(page != tail) {
                page.getNext().setPrevious(page.getPrevious());
            }

            // set this page's next to point to current head
            page.setNext(head);

            // set this page's previous to null
            if(page == tail) {
                tail = page.getPrevious();
            }
            page.setPrevious(null);

            // set  head to this page
            head = page;
        }

        void removeTailNode() {

            Node tobeFreedNode = tail;

            // set tail to previous node of tail
            tail = tail.getPrevious();
            if(tail == null) {
                head = null;
            }

            // set tail's next to null
            if(tail != null) {
                tail.setNext(null);
            }

            // set previous of tobeFreedNode as null
            tobeFreedNode.setPrevious(null);

            tobeFreedNode = null;

            countofNodes--;
        }

        // insert as head
         Node insert(int pageNumber) {

            Node newPage = new Node(pageNumber);
            countofNodes += 1;

            // if list is empty
            if(head == null) {
                head = newPage;
                tail = head;
                return newPage;
            }

            newPage.setNext(head);
            head.setPrevious(newPage);
            head = newPage;

            return newPage;
         }

         boolean isFull() {
            return countofNodes == size;
         }

    }
}