package com.company;

public class Driver {

    private String number;
    private String FIO;
    private String passport;
    private String adress;

    public Driver(String number, String FIO, String passport, String adress) {
        this.number = number;
        this.FIO = FIO;
        this.passport = passport;
        this.adress = adress;
    }

    public boolean checkNumber() {
        try {
            Integer.parseInt("" + number.charAt(0) + number.charAt(1));
            Integer.parseInt("" + number.charAt(6) + number.charAt(7) + number.charAt(8) + number.charAt(9) + number.charAt(10) + number.charAt(11));
        } catch (Exception e) {
            return false;
        }
        switch (number.charAt(3)) {
            case 'А':
            case 'В':
            case 'Е':
            case 'К':
            case 'М':
            case 'Н':
            case 'О':
            case 'Р':
            case 'С':
            case 'Т':
            case 'У':
            case 'Х':
                break;
            default:
                return false;
        }
        switch (number.charAt(4)) {
            case 'А':
            case 'В':
            case 'Е':
            case 'К':
            case 'М':
            case 'Н':
            case 'О':
            case 'Р':
            case 'С':
            case 'Т':
            case 'У':
            case 'Х':
                break;
            default:
                return false;
        }
        return number.charAt(2) == ' ' && number.charAt(5) == ' ' && number.length() == 12;
    }

    public int getNumberValue() {
        char[] cur = number.toCharArray();
        String str = "" + cur[6] + cur[7] + cur[8] + cur[9] + cur[10] + cur[11];
        return Integer.parseInt(str);
    }

    public static int getNumberValue(String strg) {
        char[] cur = strg.toCharArray();
        String str = "" + cur[6] + cur[7] + cur[8] + cur[9] + cur[10] + cur[11];
        return Integer.parseInt(str);
    }

    public String getAdress() {
        return adress;
    }

    public String getNumber() { return number; }

    public String getFIO() {
        return FIO;
    }

    public String getInfo() {
        return "№ водительского удостоверения: " + number
                + "\nФИО: " + FIO
                + "\nПаспорт: " + passport
                + "\nАдрес: " + adress + "\n";
    }
}