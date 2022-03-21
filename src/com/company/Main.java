package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static AVLTree avl = new AVLTree();
    static HashTable table = new HashTable();
    static RequestsList list = new RequestsList();

    public static String checkString(String str) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                str = reader.readLine();
                Integer.parseInt(str);
                System.out.println("Некорректный ввод. Повторите попытку.");
            } catch (Exception e) {
                break;
            }
        }
        return str;
    }

    public static int Digit(){
        while (true) {
            int choice;
            try {
                choice = new Scanner(System.in).nextInt();
                return choice;
            } catch (Exception e) {
                System.out.println("Некорректный ввод. Повторите попытку.");
                continue;
            }
        }
    }

    public static void main(String[] args)throws IOException {
        table.insert(new Car("А111ВС-15", "Mercedes", "Black", "2015"));
        table.insert(new Car("В222СТ-86", "BMW", "White", "1999"));
        table.insert(new Car("С333УХ-76", "Lexus", "Blue", "2020"));
        table.insert(new Car("Т444ВВ-63", "Nissan", "Red", "2006"));
        table.insert(new Car("У555ХХ-45", "Toyota", "White", "2018"));

        avl.insert(new Driver("11 АА 000006", "Иванов Алексей Дмитриевич", "654325", "Москва"));
        avl.insert(new Driver("11 АА 000007", "Афанасьев Илья Владимирович", "654323", "Уфа"));
        avl.insert(new Driver("11 АА 000008", "Сырин Станислав Андреевич", "321382", "Санкт-Петербург"));
        avl.insert(new Driver("11 АА 000009", "Коробов Денис Вячеславович", "647895", "Саратов"));
        avl.insert(new Driver("11 АА 000001", "Дмитриев Михаил Игоревич", "324221", "Тверь"));
        avl.insert(new Driver("11 АА 000002", "Воронов Алексей Викторович", "759654", "Тверь"));
        while (true){
            System.out.println();
            menu();
            switch (Digit()){
                case 1:{
                    clientInfoMenu();
                    switch (Digit()){
                        case 1:{
                            registrationClient();
                            break;
                        }
                        case 2:{
                            deleteClient();
                            break;
                        }
                        case 3:{
                            showClient();
                            break;
                        }
                        case 4:{
                            deleteAllDataClient();
                            break;
                        }
                        case 5:{
                            findClientByLicenseNumber();
                            break;
                        }
                        case 6:{
                            findClientByFIO();
                            break;
                        }
                        case 0:{
                            break;
                        }
                    }
                    break;
                }

                case 2:{
                    carInfoMenu();
                    switch (Digit()){
                        case 1:{
                            addCAr();
                            break;
                        }
                        case 2:{
                            deleteCar();
                            break;
                        }
                        case 3:{
                            showCars();
                            break;
                        }
                        case 4:{
                            clearCarData();
                            break;
                        }
                        case 5:{
                            try {
                                searchCarByNumber();
                            }catch (Exception e) {
                                break;
                            }
                            break;
                        }
                        case 6:{
                            searchCarNyName();
                            break;
                        }
                        case 0:{
                            break;
                        }
                    }
                    break;
                }

                case 3:{
                    prokatInfo();
                    switch (Digit()){
                        case 1:{
                            try {
                                sendRepair();
                            }catch (Exception e){
                                break;
                            }
                            break;
                        }
                        case 2:{
                            try {
                                getRepair();
                            }catch (Exception e){
                                break;
                            }
                            break;
                        }
                        case 3:{
                            try {
                                getHire();
                            }catch (Exception e){
                                break;
                            }
                            break;
                        }
                        case 4:{
                            try {
                                returnHire();
                            }catch (Exception e){
                                break;
                            }
                            break;
                        }
                        case 0:{
                            break;
                        }
                    }
                    break;
                }

                case 0: {
                    System.exit(0);
                }
            }
        }
    }

    static void menu(){
        System.out.println("1. Информация о клиентах");
        System.out.println("2. Информация об автомобилях, принадлежащих бюро проката");
        System.out.println("3. Информация о выдаче напрокат и возврате автомобилей от клиентов");
        System.out.println("0. Выход из программы");
    }

    static void clientInfoMenu(){
        System.out.println("1. Регистрация нового клиента");
        System.out.println("2. Снятие с обслуживания клиента");
        System.out.println("3. Просмотр всех зарегистрированных клиентов");
        System.out.println("4. Очистка данных о клиентах");
        System.out.println("5. Поиск клиента по № водительского удостоверения");
        System.out.println("6. Поиск клиента по фрагментам ФИО или адреса");
        System.out.println("0. Вернуться в главное меню");
    }

    static void carInfoMenu(){
        System.out.println("1. Добавление нового автомобиля"); //+
        System.out.println("2. Удаление сведений об автомобиле");//+
        System.out.println("3. Просмотр всех имеющихся автомобилей");//+
        System.out.println("4. Очистка данных об автомобилях"); //+
        System.out.println("5. Поиск автомобиля по государственному регистрационному номеру"); //+
        System.out.println("6. Поиск автомобиля по названию марки автомобиля");
        System.out.println("0. Вернуться в главное меню");
    }

    static void prokatInfo(){
        System.out.println("1. Регистрация отправки автомобиля в ремонт"); //+
        System.out.println("2. Регистрация прибытия автомобиля из ремонта"); //+
        System.out.println("3. Регистрация выдачи клиенту автомобиля на прокат"); //+
        System.out.println("4. Регистрация возврата автомобиля от клиентов");
        System.out.println("0. Вернуться в главное меню");
    }

    static void registrationClient()throws IOException {
        System.out.print("\nВведите номер водительского удостоверения(формат RR AA NNNNNN): ");
        String number = reader.readLine();
        String FIO = "";
        System.out.print("\nВведите ФИО: ");
        FIO = checkString(FIO);
        System.out.print("\nВведите данные паспорта: ");
        String pass = reader.readLine();
        System.out.print("\nВведите адрес: ");
        String adress = reader.readLine();
        avl.insert(new Driver(number, FIO, pass, adress));
    }

    static void deleteClient()throws IOException{
        System.out.print("\nВведите номер водительского удостоверения(формат RR AA NNNNNN): ");
        String deleteNumber = reader.readLine();
        avl.delete(Driver.getNumberValue(deleteNumber));
    }

    static void showClient(){
        avl.traverseInOrder();
    }

    static void deleteAllDataClient(){
        avl.clear();
        System.out.println("\nДерево очищенно.");
    }

    static void findClientByLicenseNumber()throws IOException{
        System.out.print("\nВведите номер водительского удостоверения(формат RR AA NNNNNN): ");
        String searchNumber = reader.readLine();
        avl.search(Driver.getNumberValue(searchNumber));
    }

    static void findClientByFIO(){
        String choice1;
        while (true) {
            System.out.println("1. Искать по фрагменту ФИО.");
            System.out.println("2. Искать по фрагменту адреса.");
            try {
                System.out.print("\nВыберите пункт меню: ");
                choice1 = reader.readLine();
                Integer.parseInt(choice1);
                break;
            } catch (Exception e) {
                System.out.println("Введено некорректное значение.");
            }
        }
        switch (choice1) {
            case "1":
                System.out.print("Введите фрагмент ФИО: ");
                String frag = "";
                frag = checkString(frag);
                avl.searchByFragment(frag);
                break;
            case "2":
                System.out.print("Введите фрагмент адреса: ");
                String frag1 = "";
                frag1 = checkString(frag1);
                avl.searchByFragmentAdres(frag1);
                break;
        }
    }

    static void addCAr()throws IOException{
        System.out.print("\nВведите государственный регистрационный номер: ");
        String gosNum = reader.readLine();
        System.out.print("\nВведите марку автомобиля: ");
        String mark = "";
        mark = checkString(mark);
        System.out.print("\nВведите цвет автомобиля: ");
        String color = "";
        color = checkString(color);
        String year = "";
        while (true) {
            try {
                System.out.print("\nВведите год выпуска автомобиля: ");
                year = reader.readLine();
                Integer.parseInt(year);
                break;
            } catch (Exception e) {
                System.out.println("Введено некорректное значение.");
            }
        }
        table.insert(new Car(gosNum, mark, color, year));
    }

    static void deleteCar()throws IOException{
        System.out.print("\nВведите государственный регистрационный номер: ");
        String deleteGosNum = reader.readLine();
        table.delete(deleteGosNum);
    }

    static void showCars(){
        table.printTable();
    }

    static void clearCarData(){
        table.clear();
        System.out.print("Таблица очищена.");
    }

    static void searchCarByNumber()throws IOException{
        System.out.print("Введите гос. рег. номер (БЦЦЦББ-ЦЦ): ");
        String str1 = reader.readLine();
        System.out.println(table.search(str1).getInfo());
        if (list.search(table.search(str1)) != null) {
            System.out.println("Выдан: " +
                    avl.search(Driver.getNumberValue(list.search(table.search(str1)).getNumberDriver())).getDriver().getFIO());
            System.out.println(avl.search(Driver.getNumberValue(list.search(table.search(str1)).getNumberDriver())).getDriver().getNumber());
        }
    }

    static void searchCarNyName(){
        System.out.print("\nВведите марку автомобиля: ");
        String mark1 = "";
        mark1 = checkString(mark1);
        table.searchByName(mark1);
    }

    static void sendRepair()throws IOException{
        System.out.print("\nВведите государственный регистрационный номер: ");
        String seaGosNum = reader.readLine();
        table.search(seaGosNum).setHave(false);
        System.out.println("Автомобиль отправлен на ремонт.");
    }

    static void getRepair()throws IOException{
        System.out.print("\nВведите государственный регистрационный номер: ");
        String seaGosNum1 = reader.readLine();
        table.search(seaGosNum1).setHave(true);
        System.out.println("Автомобиль отремонтирован.");
    }

    static void getHire()throws IOException{
        System.out.print("\nВведите номер водительского удостоверения(формат RR AA NNNNNN): ");
        String numberDriver = reader.readLine();
        if (avl.search(Driver.getNumberValue(numberDriver)).getDriver() == null) {
            System.out.println("Такого водителя нет в БД.");
        }
        else {
            System.out.print("\nВведите государственный регистрационный номер: ");
            String numberCar = reader.readLine();
            if (table.search(numberCar) == null || !table.search(numberCar).getHave()) {
                System.out.print("Данной машины не существует или она отсутствует.");
            } else {
                System.out.print("\nВведите дату выдачи: ");
                String dateOut = reader.readLine();
                System.out.print("\nВведите дату возврата: ");
                String dateIn = reader.readLine();
                table.search(numberCar).setHave(false);
                list.add(new Request(avl.search(Driver.getNumberValue(numberDriver)).getDriver(), table.search(numberCar), dateOut, dateIn));
                list.sort(list);
                System.out.println("Автомобиль выдан в прокат.");
            }
        }
    }

    static void returnHire()throws IOException{
        System.out.print("\nВведите государственный регистрационный номер: ");
        String numberCar1 = reader.readLine();
        if (table.search(numberCar1) == null) {
            System.out.print("Данного автомобиля не существует.");
        }
        else {
            table.search(numberCar1).setHave(true);
            list.delete(table.search(numberCar1));
            System.out.println("Автомобиль возвращен.");
        }
    }


}

