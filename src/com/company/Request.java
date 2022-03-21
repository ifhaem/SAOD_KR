package com.company;

public class Request {

    private String numberDriver;
    private String numberCar;
    private String dateOut;
    private String dateIn;
    private Request next;

    public Request(Driver dr, Car cr, String dateOut, String dateIn) {
        numberCar = cr.getNumber();
        numberDriver = dr.getNumber();
        this.dateOut = dateOut;
        this.dateIn = dateIn;
    }

    public Request getNext() {
        return next;
    }
    public void setNext(Request next) {
        this.next = next;
    }

    public Integer getGosNumber() {
        char[] arr = numberCar.toCharArray();
        String str = "" + arr[1] + arr[2] + arr[3];
        return Integer.parseInt(str);
    }

    public void copy(Request req1) {
        String numD = numberDriver;
        String numC = numberCar;
        String in = dateIn;
        String out = dateOut;
        this.numberDriver = req1.getNumberDriver();
        this.numberCar = req1.getNumberCar();
        this.dateIn = req1.getDateIn();
        this.dateOut = req1.getDateOut();
        req1.numberDriver = numD;
        req1.numberCar = numC;
        req1.dateIn = in;
        req1.dateOut = out;


    }

    public String getDateOut() {
        return dateOut;
    }

    public String getDateIn() {
        return dateIn;
    }

    public String getNumberDriver() { return numberDriver; }

    public String getNumberCar() { return numberCar; }
}