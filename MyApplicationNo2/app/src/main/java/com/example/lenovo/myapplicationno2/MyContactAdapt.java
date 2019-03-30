package com.example.lenovo.myapplicationno2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyContactAdapt extends BaseAdapter {
    private Context mContext;
    private ArrayList<Contact> contacts;
    private ViewHolder mViewHolder;
    private ArrayList<Contact> selected_contacts = new ArrayList<Contact>();

    private boolean itemMultiCheckable;

    public MyContactAdapt(Context mContext, ArrayList<Contact> contacts) {
        this.mContext = mContext;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_layout_item, null);

            mViewHolder = new ViewHolder();

            mViewHolder.img = (ImageView) convertView.findViewById(R.id.image_view);
            mViewHolder.title = (TextView) convertView.findViewById(R.id.text_view);
            mViewHolder.item_selete = (CheckBox) convertView.findViewById(R.id.checkbox1);

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        if (itemMultiCheckable) {
            mViewHolder.item_selete.setVisibility(View.VISIBLE);
            if (selected_contacts.contains(contacts.get(position))) {
                mViewHolder.item_selete.setChecked(true);
            } else {
                mViewHolder.item_selete.setChecked(false);
            }

        } else {
            mViewHolder.item_selete.setVisibility(View.GONE);
        }

        // 控件赋值
        Contact contact = contacts.get(position);
        mViewHolder.img.setImageResource(contact.getImg());
        mViewHolder.title.setText(contact.getTitle());

        return convertView;
    }

    public void setItemMultiCheckable(boolean flag) {
        itemMultiCheckable = flag;
    }

    public void addSelectedContact(int position) {
        selected_contacts.add(contacts.get(position));
    }

    public void cancelSeletedContact(int position) {
        selected_contacts.remove(contacts.get(position));
    }

    public void clearSeletedContacts() {
        selected_contacts = new ArrayList<Contact>();
    }

    public void deleteSeletedContacts() {
        for (Contact contact : selected_contacts) {
            contacts.remove(contact);
        }
    }



    static class ViewHolder {
        ImageView img;
        TextView title;
        CheckBox item_selete;
    }

}
