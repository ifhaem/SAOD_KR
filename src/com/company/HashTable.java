package com.company;

import java.util.Arrays;

public class HashTable {

    private Car[] dataTable;

    public HashTable() {
        dataTable = new Car[10000];
    }

    public void insert(Car data) {
        if (!data.checkNumber() || search(data.getNumber()) != null) {
            System.out.println("Не удалось добавить автомобиль в базу.");
            return;
        }

        int key = hashFunc(data.getNumber());
        int loops = 0;
        while (true) {
            if (dataTable[key] == null) {
                dataTable[key] = data;
                break;
            }
            if (key + 1 < dataTable.length)
                key += 1;
            else {
                key = (key + 1) % dataTable.length;
                loops++;
            }
            if (loops == 2) {
                System.out.println("Не удается сохранить автомобиль.");
                break;
            }
        }
    }

    public void searchByName(String name) {
        for (Car car : dataTable) {
            if (car == null) continue;
            if (car.getName().equals(name)) {
                System.out.println(car.getInfo());
            }
        }

    }

    public Car search(String keyy) {
        int key = hashFunc(keyy);
        int loops = 0;

        if (dataTable[key] == null) {
            return null;
        }

        while (true) {
            if (dataTable[key] != null) {
                if (dataTable[key].getNumber().equals(keyy)) return dataTable[key];
            } else {
                return null;
            }
            if (key + 1 < dataTable.length)
                key += 1;
            else {
                key = (key + 1) % dataTable.length;
                loops++;
            }
            if (loops == 2) {
                return null;
            }
        }
    }

    public void delete(String keyy) {
        int key = hashFunc(keyy);
        int loops = 0;

        if (dataTable[key] == null) {
            System.out.println("Автомобиля с таким номером нет в БД.");
            return;
        }

        while (true) {
            if (dataTable[key] != null) {
                if (dataTable[key].getNumber().equals(keyy)) {
                    dataTable[key].setNumber("deleted");
                    break;
                }
            } else {
                System.out.println("Автомобиля с таким номером нет БД.");
                break;
            }
            if (key + 1 < dataTable.length)
                key += 1;
            else {
                key = (key + 1) % dataTable.length;
                loops++;
            }
            if (loops == 2) {
                System.out.println("Автомобиля с таким номером нет в базе.");
                break;
            }
        }
    }

    public void clear() {
        Arrays.fill(dataTable, null);
    }

    public void printTable() {
        for (int i = 0; i < dataTable.length; i++)
            if (dataTable[i] != null && !(dataTable[i].getNumber().equals("deleted")))
                System.out.println(dataTable[i].getInfo());
    }

    private int hashFunc(String str) {
        // res = s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
        int res = 0;
        for (int i = 0; i < str.length(); i++)
            res += (int)(str.charAt(i)) * Math.pow(31.0, str.length() - 1 - i);
        if(true) return Math.abs(str.hashCode()%dataTable.length);
        return res%dataTable.length;
    }

}