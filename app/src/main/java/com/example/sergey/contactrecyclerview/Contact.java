package com.example.sergey.contactrecyclerview;

/**
 * Created by Sergey on 28.06.2016.
 */
public class Contact {
//создается класс Контакт со строковыми переменными экземпляра Нэйм и Намбер

    private String name;
    private String number;


    public Contact(String name, String number) {
        //создается конструктор класса с параметрами нэйм и намбер
        this.name = name;
        this.number = number;
        //переменные с this ссылаются на текущие экземпляры класса
    }

    public String getName() {

        return name;
    }

    public String getNumber() {

        return number;
    }
//созаеются геттеры для переменных экземпляра


}
