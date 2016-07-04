package com.example.sergey.contactrecyclerview.unused;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrey Nikonov on 01.03.16.
 */
public abstract class ObjectBaseRecycleAdapter<T> extends RecyclerView.Adapter<ObjectBaseRecycleAdapter.BaseViewHolder> {

    protected interface OnItemClick {
        void onItemClicked(View v, int positionInAdapter);
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnItemClick clickListener;

        public BaseViewHolder(View rootView) {
            super(rootView);
        }

        /**
         * Create holder with root clicked view
         */
        public BaseViewHolder(View rootView, OnItemClick listener) {
            super(rootView);
            clickListener = listener;
            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onItemClicked(v, getAdapterPosition());
        }
    }

    protected List<T> mItems;

    /**
     * Creates empty adapter
     */
    public ObjectBaseRecycleAdapter() {
        this.mItems = new ArrayList<>();
    }

    /**
     * Creates adapter with known items
     *
     * @param items items
     * @see #ObjectBaseRecycleAdapter()
     */
    public ObjectBaseRecycleAdapter(List<T> items) {
        this.mItems = (items == null ? new ArrayList<T>() : new ArrayList<>(items));
    }

    /**
     * Creates adapter with known items.
     *
     * @param items items
     * @see #ObjectBaseRecycleAdapter()
     */
    public ObjectBaseRecycleAdapter(T[] items) {
        this.mItems = (items == null ? new ArrayList<T>() : Arrays.asList(items));
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = createItemViewHolder(parent, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (!bindViewHolder(holder, mItems.get(position), position)) {
            bindViewHolder(holder, mItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * Gets item by position
     *
     * @param position position in collection data
     * @return item  associate with position in collection data
     * @throws IllegalArgumentException if bad position (more last position item in collection or < 0 )
     */
    public T getItemByPosition(int position) {
        if (position > mItems.size() - 1 || position < 0) {
            throw new IllegalArgumentException(" Bad position " + position + " size items: " + mItems.size());
        }
        return mItems.get(position);
    }

    /**
     * Fount item position in collection data. The same {@link ArrayList#indexOf(Object)}
     *
     * @return index item or -1
     */
    public int getPositionByItem(T item) {
        if (item == null) return -1;
        return mItems.indexOf(item);
    }

    /**
     * Replaces data in adapter. Old data will remove
     *
     * @param newData new data, must be not null
     */
    public void swap(@NonNull List<T> newData) {
        mItems.clear();
        mItems.addAll(newData);
        notifyDataSetChanged();
    }

    /**
     * Returns whether this adapter contains no elements.
     *
     * @return true if this adapter has no elements, false otherwise.
     */
    public boolean isEmpty() {
        return mItems.isEmpty();
    }

    /**
     * Adds item in adapter
     *
     * @param item item will be add in adapter, must be not null
     */
    public void addItem(@NonNull T item) {
        mItems.add(item);
        notifyItemInserted(mItems.size() - 1);
    }

    /**
     * Remove item in adapter
     *
     * @param item item will be add in adapter
     */
    public void removeItem(T item) {
        if (item == null) return;
        final int indexItem = mItems.indexOf(item);
        if (indexItem != -1) {
            mItems.remove(indexItem);
            notifyItemRemoved(indexItem);
        }
    }

    /**
     * Adds items in adapter
     *
     * @param items will add in adapter, if null missing
     */
    public void addItems(List<T> items) {
        if (items != null && items.size() > 0){
            mItems.addAll(items);
            notifyItemInserted(mItems.size() - 1);
        }
    }

    /**
     * Replaces item if fount in collection.
     * Notify adapter so item changed.
     *
     * @param item try replace item
     */
    public void replaceItem(T item) {
        if (item == null) return;
        final int indexItem = mItems.indexOf(item);
        if (indexItem != -1) {
            replaceItem(indexItem, item);
        }
    }

    /**
     * Replaces item by special position.
     * Notify adapter so item changed.
     *
     * @param position position in collection
     * @param item     try replace item. If null missed
     * @throws IllegalArgumentException if bad position (more last position item in collection or < 0 )
     */
    public void replaceItem(int position, T item) {
        if (item == null) return;
        if (position > mItems.size() - 1 || position < 0) {
            throw new IllegalArgumentException(" Bad position size items: " + mItems.size());
        }
        mItems.set(position, item);
        notifyItemChanged(position);
    }

    /**
     * Gets all items
     *
     * @return collection data associate with adapter
     */
    public List<T> getItems() {
        return mItems;
    }

    /**
     * Delete all items.  Notify adapter so item changed.
     */
    public void clear() {
        if (!mItems.isEmpty()) {
            mItems.clear();
            notifyDataSetChanged();
        }
    }

    protected abstract ObjectBaseRecycleAdapter.BaseViewHolder createItemViewHolder(ViewGroup parent, int viewType);

    protected abstract void bindViewHolder(BaseViewHolder holder, T data);

    /**
     * The same {@link #bindViewHolder(BaseViewHolder, Object)}, but says position in collection data
     *
     * @return result processed or not. If true {@link #bindViewHolder(BaseViewHolder, Object)} doesn't call. Default false.
     */
    protected boolean bindViewHolder(BaseViewHolder holder, T data, int position) {
        //sub class can override if need to know position in data collection
        return false;
    }
}