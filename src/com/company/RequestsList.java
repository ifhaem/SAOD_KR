package com.company;

public class RequestsList {

    private int size = 0;
    private Request head;

    public void add(Request r) {
        if (head == null){
            head = r;
            head.setNext(null);
        }
        else
        {
            getLast().setNext(r);
        }
        size++;
    }
    private Request getLast() {
        Request cur = head;
        while (cur.getNext() != null)
            cur = cur.getNext();
        return cur;
    }

    public void delete(Car cr) {
        search(cr).setNext(search(cr).getNext());
        size--;
    }

    public Request search(Car cr) {
        Request cur = head;
        while (!cur.getNumberCar().equals(cr.getNumber()))
            cur = cur.getNext();
        return cur;
    }

    private Request getByPosition(int pos) {
        Request cur = head;
        for (int i = 0; i != pos; i++) {
            cur = cur.getNext();
        }
        return cur;
    }

    public void sort(RequestsList list) {
        for (int i = 1; i < size; i++) {
            for (int j = i; j > 0 && list.getByPosition(j-1).getGosNumber() > list.getByPosition(j).getGosNumber(); j--) {
                list.getByPosition(j-1).copy(list.getByPosition(j));
            }
        }
    }

}