package com.example.sergey.contactrecyclerview;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        }, 5 * 1000);
    }
}
/*создаем клас Мейнактивити активити унаследованный от Эпкомпэйтактивити
* создаем метод без возвращаемого значения который создает пользовательский интерфейс
*запускаем метод родителького класса onCreate() в дополнении с кодом своего onCreate()
*подключаем xml файл разметки
*создается объект хандлер который будет выполнен с задержкой postDelayed ??????????
*создается объект i класса интент с параметрами текущего класса Майнактивити и класса для перехода Хоумактивити
* метод старт активити
*метод финиш
*ожидание закрытия 5 секунд
* */
