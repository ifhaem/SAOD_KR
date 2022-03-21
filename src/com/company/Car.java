package com.company;

public class Car {

    private String number;
    private String name;
    private String color;
    private String year;
    private boolean have;

    public Car(String number, String name, String color, String year) {
        this.number = number;
        this.name = name;
        this.color = color;
        this.year = year;
        have = true;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean getHave() {
        return have;
    }

    public void setHave(boolean have) {
        this.have = have;
    }

    public boolean checkNumber() { // Метод, проверяющий формат номера
        try {
            Integer.parseInt("" + number.charAt(7) + number.charAt(8));
            Integer.parseInt("" + number.charAt(1) + number.charAt(2) + number.charAt(3));
        } catch (Exception e) {
            return false;
        }
        switch (number.charAt(0)) {
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
        switch (number.charAt(5)) {
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
        return number.charAt(6) == '-' && number.length() == 9;
    }

    public String getInfo() {
        return "Гос. рег. номер: " + number
                + "\nМарка: " + name
                + "\nЦвет: " + color
                + "\nГод выпуска: " + year
                + "\n" + (have?"В наличии":"Отсутствует") + "\n";
    }

}