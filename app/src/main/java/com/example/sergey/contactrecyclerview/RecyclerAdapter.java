package com.example.sergey.contactrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 04.07.2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Contact> contacts;

    public RecyclerAdapter() {
        this.contacts = new ArrayList<>();
    }

    public void swap(List<Contact> contacts) {
        contacts.clear();
        contacts.addAll(contacts);
        notifyDataSetChanged();
    }

    // класс view holder с помощью которого мы получаем ссылку на каждый элемен отдельного пункта списка
     class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView phone;
        //текствью нашего итема
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.contact_name);
            phone = (TextView) itemView.findViewById(R.id.contact_phone);
        }
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contact_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int i) {
// Заполнение  View данными из элемента списка с номером i
        Contact contact = contacts.get(i);
        holder.name.setText(contact.getName());
        holder.phone.setText(contact.getNumber());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
