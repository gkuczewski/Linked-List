package testcircularbag;
import java.util.Collections;

public class CircularBag<T> implements BagInterface<T>{

        private class Node {
        private T data;
        private Node next;
        private Node prev;
        
        Node(T dat, Node nex, Node pre){
            data = dat;
            next = nex;
            prev = pre;
        }
    }
    
    private Node tail;
    private int size = 0;
    
    @Override
    public T remove() {
        if(tail == null ){
            return null;
        }
        if(tail.next == tail){
            tail = null;
            size = 0;
            return tail.data;
        }
        T oldTail = tail.data;
        tail.next.prev = tail.prev;
        tail.prev.next = tail.next;
        tail = tail.prev;
        size--;
        return oldTail;
    }

    @Override
    public boolean remove(T anEntry) {
        if(tail == null){
            return false;
        }
        if(tail.next == tail && tail.data == anEntry){
            tail = null;
            size = 0;
            return true;
            }
        Node currentNode = tail.next;
        for(int i = 0; i < size; i++){
            if(currentNode.data == anEntry){
                currentNode.next.prev = currentNode.prev;
                currentNode.prev.next = currentNode.next;
                size--;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public void clear() {
        tail = null;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
       if(tail == null){
            return 0;
        }
        Node currentNode = tail.next;
        int freq = 0;
        for(int i = 0; i < size; i++){
            if(currentNode.data == anEntry){
                freq++;
            }
            currentNode = currentNode.next;
        }
        return freq;
    }

    @Override
    public boolean contains(T anEntry) {
        if(tail == null){
            return false;
        }
        Node currentNode = tail.next;
        for(int i = 0; i < size; i++){
            if(currentNode.data == anEntry){
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public T[] toArray() {
        if(tail == null){
            T[] teaArray = (T[]) new Object[0];
            return teaArray;
        }
        Node currentNode = tail.next;
        T[] teaArray = (T[]) new Object[size];
        for(int i = 0; i < size; i++){
            teaArray[i] = currentNode.data;
            currentNode = currentNode.next;
        }
        return teaArray;
    }

    @Override
    public int getCurrentSize() {
        return size;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return tail == null;
    }

    @Override
    public boolean add(T newEntry) {
        if(newEntry == null){
            return false;
        }
        if(tail == null){
            tail = new Node(newEntry, null,null);
            tail.next = tail;
            tail.prev = tail;
            size = 1;
            return true;
        }
        Node newNode = new Node(newEntry, tail.next, tail);
        tail.next.prev = newNode;
        tail.next = newNode;
        tail = newNode;
        size++;
        return true;
    }

}