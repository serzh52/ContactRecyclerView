package com.example.sergey.contactrecyclerview.unused;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sergey.contactrecyclerview.Contact;
import com.example.sergey.contactrecyclerview.R;

/**
 * Created by Sergey on 27.06.2016.
 */
public class Adapter extends ObjectBaseRecycleAdapter<Contact> {




    @Override
    protected BaseViewHolder createItemViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new VodItemViewHolder(root, new OnItemClick() {
            @Override
            public void onItemClicked(View v, int positionInAdapter) {


            }
        });
    }

    @Override
    protected void bindViewHolder(BaseViewHolder holder, Contact data) {


    }
    private static class VodItemViewHolder extends BaseViewHolder {
        private final TextView mName;
        private final TextView mNumber;

        public VodItemViewHolder(View rootView, OnItemClick listener) {
            super(rootView, listener);
            mName = (TextView) rootView.findViewById(R.id.contact_name);
            mNumber = (TextView) rootView.findViewById(R.id.contact_phone);
        }
    }
}
