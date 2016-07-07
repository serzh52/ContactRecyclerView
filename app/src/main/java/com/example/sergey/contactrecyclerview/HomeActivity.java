package com.example.sergey.contactrecyclerview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*создаем клас Хоумактивити активити унаследованный от Эпкомпэйтактивити  с приватными глобольными переменными
* создаем метод без возвращаемого значения который создает пользовательский интерфейс
*запускаем метод родителького класса onCreate() в дополнении с кодом своего onCreate()
*подключаем xml файл разметки
* */
public class HomeActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener {
    private ProgressBar progress;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // используем linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // создаем адаптер
        mAdapter = new RecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

     /*   mRecyclerView.addOnItemTouchListener( // and the click is handled
                new RecyclerClickListener(this, new RecyclerClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                }));*/


        this.mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        this.progress = (ProgressBar) findViewById(R.id.progressBar);//
        //


    }

    @Override
    public void onResume() {
        super.onResume();
        this.showContacts();
    }

    private void showContacts() {//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            new DetailCont().execute();//
        }
    }

    @Override//
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {//
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                new DetailCont().execute();//
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();//
            }
        }
    }

    private List<Contact> getContactNames() {//
        final Random rundom = new Random();
        List<Contact> contacts = new ArrayList<>();//
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Double lng = rundom.nextDouble();
            Double lat = rundom.nextDouble();
            Contact contact = new Contact(name, phoneNumber,lng,lat);//
            contacts.add(contact);
        }
        phones.close();

        return contacts;//
    }

    @Override
    public void onItemClick(Contact contacts) {
        Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
        intent.putExtra("contact", contacts);
        startActivity(intent);

    }

    public void addOnClick(View view) {
        Intent intent = new Intent(this, AddContactActivity.class);
        startActivity(intent);
    }

    class DetailCont extends AsyncTask<Void, Void, List<Contact>> {//создаем класс который перемещает отображение контактов в отдельный поток унаследованный от асинктаск (на прямую исп.нельзя)

        @Override
        protected void onPreExecute() {//выполняется перед doInBackground()
            progress.setVisibility(View.VISIBLE);//перед началом выполнения задачи показывааем прогресс бар
        }

        @Override
        protected List<Contact> doInBackground(Void... params) {//основной метод в котором находятся тяжелые задачи требующие участия отдельного потока в данном случае использование метода гетконтакт неймс и массива контактов
            return getContactNames();//возвращает данные метода гетконтактнейм
        }

        @Override
        protected void onPostExecute(List<Contact> result) {//выполняется после doInBackground(),обновляет пользовательски интерфейс
            progress.setVisibility(View.INVISIBLE);//заканчивает показ прогресс бара
            mAdapter.swap(result);
        }


    }
}
