package com.example.lenovo.myapplicationno2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActionmode extends AppCompatActivity{


    int[] img=new int[]{R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4,R.drawable.icon5,R.drawable.icon6,
            R.drawable.icon7,R.drawable.icon8,};
    String title[]={"Dead Pool","Captain America","Iron Man","Venom","Thanos","Spider Man","Star Lord","Rocket Raccoon"};


    private ListView list_view;
    private ProgressDialog mDialog;
    private MyContactAdapt mAdapt;
    private MultiModeCallback mCallback;

    private ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        initView();

        new ContactsDownloadTask().execute();
    }

    private void initView() {
        list_view = (ListView) this.findViewById(R.id.list_view);

        mDialog = new ProgressDialog(this);

        mDialog.setTitle("提示信息");
        mDialog.setMessage("下载列表中...");
        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        mCallback = new MultiModeCallback();
        list_view.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        list_view.setMultiChoiceModeListener(mCallback);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s= contacts.get(position).getTitle();
                Toast.makeText(MainActionmode.this,s,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void downloadContactsFromServer() {
        if (contacts == null) {
            contacts = new ArrayList<Contact>();
        }

        for (int i = 0; i < title.length; i++) {
            contacts.add(new Contact(img[i], title[i]));
        }
    }

    private class ContactsDownloadTask extends AsyncTask<Void, Integer, Void> {

        private int currentlyProgressValue;

        @Override
        protected void onPreExecute() {
            mDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            while (currentlyProgressValue < 100) {
                publishProgress(++currentlyProgressValue);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // download data from server
            downloadContactsFromServer();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            mAdapt = new MyContactAdapt(MainActionmode.this, contacts);
            list_view.setAdapter(mAdapt);

            mDialog.dismiss();
        }

    }


    private class MultiModeCallback implements AbsListView.MultiChoiceModeListener {
        private View mMultiSelectActionBarView;
        private TextView mSelectedCount;

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_actionmode, menu);

            mAdapt.setItemMultiCheckable(true);
            mAdapt.notifyDataSetChanged();

            if (mMultiSelectActionBarView == null) {
                mMultiSelectActionBarView = LayoutInflater.from(MainActionmode.this)
                        .inflate(R.layout.select_actionbar, null);
                mSelectedCount = (TextView) mMultiSelectActionBarView.findViewById(R.id.selected_conv_count);
            }
            mode.setCustomView(mMultiSelectActionBarView);
            //((TextView) mMultiSelectActionBarView.findViewById(R.id.title)).setText(R.string.);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ac1:
                    mAdapt.setItemMultiCheckable(false);
                    mAdapt.clearSeletedContacts();
                    mAdapt.notifyDataSetChanged();
                    mode.finish();
                    break;
                case R.id.ac2:
                    mAdapt.deleteSeletedContacts();
                    mAdapt.notifyDataSetChanged();
                    mode.invalidate();
                    mode.finish();
                    break;

                default:
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mAdapt.setItemMultiCheckable(false);
            mAdapt.clearSeletedContacts();
            mAdapt.notifyDataSetChanged();

        }

        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            if (checked) {
                mAdapt.addSelectedContact(position);
            } else {
                mAdapt.cancelSeletedContact(position);
            }

            mAdapt.notifyDataSetChanged();

            updateSeletedCount();
            mode.invalidate();

        }

        public void updateSeletedCount() {
            mSelectedCount.setText(Integer.toString(list_view.getCheckedItemCount()) + "条");
        }

    }
}
