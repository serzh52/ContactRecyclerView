package com.example.sergey.contactrecyclerview;

import java.io.Serializable;

/**
 * Created by Sergey on 28.06.2016.
 */
public class Contact implements Serializable {
//создается класс Контакт со строковыми переменными экземпляра Нэйм и Намбер

    private String name;
    private String number;
    private Double lng;
    private Double lat;


    public Contact(String name, String number, Double lng, Double lat) {
        //создается конструктор класса с параметрами нэйм и намбер
        this.lat = lat;
        this.lng = lng;
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

    public Double getLng() {
        return lng;
    }

    public Double getLat() {
        return lat;
    }
//созаеются геттеры для переменных экземпляра


}
