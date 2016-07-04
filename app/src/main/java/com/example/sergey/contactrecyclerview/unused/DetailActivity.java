package com.example.sergey.contactrecyclerview.unused;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.sergey.contactrecyclerview.Contact;
import com.example.sergey.contactrecyclerview.R;

public class DetailActivity extends AppCompatActivity {
    public final static String ID = "ID";
    private Contact mContact;
    private TextView mName, mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       /* mContact = Contact.getItem(getIntent().getIntExtra(ID, 0));

        mName = (TextView) findViewById(R.id.details_name);
        mPhone = (TextView) findViewById(R.id.details_phone);

        mName.setText(mContact.get(Contact.Field.NAME));
        mPhone.setText(mContact.get(Contact.Field.PHONE));*/
    }
}
