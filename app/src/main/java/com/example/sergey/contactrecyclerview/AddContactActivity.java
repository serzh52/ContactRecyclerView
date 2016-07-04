package com.example.sergey.contactrecyclerview;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//создаем класс AddContactActivity унаследуемый от класса AppCompatActivity
public class AddContactActivity extends AppCompatActivity {
/*переопределяем метод без возвращаемого значения который создает пользовательский интерфейс
*создаем метод без возвращаемого значения который создает пользовательский интерфейс
*запускаем метод родителького класса onCreate() в дополнении с кодом своего onCreate()
*подключаем xml файл разметки activity_add_contact
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    }

/*создаем метод addContact с параметрами View view
*создаем объект contactValues и инициализируем его в классе ContentValues
*находим по айди компонент пользовательского ввода из ффайла разметки
*создается объект ньюконтактс возвращяет текст из текстового поля и возвращает строку
* устанавливаем название и тип контакта. В качестве ключей выставляются значения RawContacts.ACCOUNT_NAME и RawContacts.ACCOUNT_TYPE
* в качестве  значения вставляем текст из текстового поля.
*Объект добавляется в RawContacts с помощью метода insert()
*Метод insert() возвращает URI ссылку на  объект  у которого мы  получаем id.
*Очищаем данные объекта
*заполняем данными
*снова добавляем с пом метода insert
* появляется тост с успешним завершением
* */

    public void addContact(View view) {

        ContentValues contactValues = new ContentValues();
        EditText contactText = (EditText) findViewById(R.id.contactText);
        String newContact = contactText.getText().toString();
        contactValues.put(ContactsContract.RawContacts.ACCOUNT_NAME, newContact);
        contactValues.put(ContactsContract.RawContacts.ACCOUNT_TYPE, newContact);
        Uri newUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, contactValues);
        long rawContactsId = ContentUris.parseId(newUri);
        contactValues.clear();
        contactValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactsId);
        contactValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        contactValues.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, newContact);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, contactValues);
        Toast.makeText(this, newContact + " добавлен в список контактов", Toast.LENGTH_LONG).show();
    }
}