package com.example.sergey.contactrecyclerview;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.OperationApplicationException;
import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//создаем класс AddContactActivity унаследуемый от класса AppCompatActivity
public class AddContactActivity extends AppCompatActivity {

    EditText txtStatus;
    TextView lblCount;
    Button addButton;
    static int MIN_COUNT = 0;
/*переопределяем метод без возвращаемого значения который создает пользовательский интерфейс
*создаем метод без возвращаемого значения который создает пользовательский интерфейс
*запускаем метод родителького класса onCreate() в дополнении с кодом своего onCreate()
*подключаем xml файл разметки activity_add_contact
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        txtStatus = (EditText)findViewById(R.id.contactName);
        lblCount = (TextView)findViewById(R.id.lblCount);
        addButton = (Button) findViewById(R.id.add);

        txtStatus.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {

                int count = MIN_COUNT + s.length();
                lblCount.setText(Integer.toString(count));
                if (count >= 20) {
                    lblCount.setTextColor(Color.parseColor("#FFFF0004"));
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Колличество знаков превышено!",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    addButton.setEnabled(false);
                }else {
                    lblCount.setTextColor(Color.parseColor("#FF000000"));
                    addButton.setEnabled(true);
                }
            }
        });

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

        EditText contactName = (EditText) findViewById(R.id.contactName);
        EditText contactNum = (EditText) findViewById(R.id.contactNum);
        String newContactName = contactName.getText().toString();
        String newContactNum = contactNum.getText().toString();

        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        int rawContactInsertIndex = ops.size();

        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME,null )
                .build());
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, newContactNum)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                .build());
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,newContactName)
                .build());
        Toast.makeText(this, newContactName + " добавлен в список контактов", Toast.LENGTH_LONG).show();
        try {
            ContentProviderResult[] res = getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }




       /* ContentValues contactValues = new ContentValues();
        EditText contactName = (EditText) findViewById(R.id.contactName);
        EditText contactNum = (EditText) findViewById(R.id.contactNum);
        String newContactName = contactName.getText().toString();
        String newContactNum = contactNum.getText().toString();
        contactValues.put(ContactsContract.RawContacts.ACCOUNT_NAME, newContactName);
        contactValues.put(ContactsContract.RawContacts.ACCOUNT_TYPE, newContactName);
        contactValues.put(ContactsContract.RawContacts.ACCOUNT_NAME, newContactNum);
        contactValues.put(ContactsContract.RawContacts.ACCOUNT_TYPE, newContactNum);
        Uri newUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, contactValues);
        long rawContactsId = ContentUris.parseId(newUri);
        contactValues.clear();
        contactValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactsId);
        contactValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        contactValues.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, newContactName);
        contactValues.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, newContactNum);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, contactValues);
        */
    }
}