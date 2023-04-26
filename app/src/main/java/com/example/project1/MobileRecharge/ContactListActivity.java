package com.example.project1.MobileRecharge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project1.ModelClasses.ContactModel;
import com.example.project1.R;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {
    RecyclerView contactListRecView;
    ArrayList<ContactModel> contactList = new ArrayList<>();
    ContactListAdapter contactListAdapter;
    EditText searchText;

    ProgressDialog progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        contactListRecView = findViewById(R.id.contactListRecViewId);
        searchText = findViewById(R.id.searchConTextId);

        // checkPermission();
        getContactList();
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                contactListAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(ContactListActivity.this
                , Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ContactListActivity.this
                    , new String[]{Manifest.permission.READ_CONTACTS}, 100);
        } else {


            getContactList();


        }
    }

    private void getContactList() {

        //Initialize URI
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        //sort by Ascending
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        //Initialize cursor
        Cursor cursor = getContentResolver().query(
                uri, null, null, null, sort
        );

        //check condition
        if (cursor.getCount() > 0) {
            //when count is greater then 0
            while (cursor.moveToNext()) {
                //get contact id
                @SuppressLint("Range")
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                //get contact name
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                //Initialize Phone uri
                Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                //Initialize Selection
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";
                //initialize phone cursor
                Cursor phoneCursor = getContentResolver().query(phoneUri, null, selection, new String[]{id}, null);
                //check condition
                if (phoneCursor.moveToNext()) {
                    //when phone cursor move to next
                    @SuppressLint("Range")
                    String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    //Initialize Contact Model
                    ContactModel model = new ContactModel(name, number);
                    //add model in arrayList
                    contactList.add(model);
                    //close phone cursor
                    phoneCursor.close();


                }
            }
            //close cursor
            cursor.close();
        }
        //set layoutManager
        contactListRecView.setLayoutManager(new LinearLayoutManager(this));
        contactListAdapter = new ContactListAdapter(contactList, this);
        contactListRecView.setAdapter(contactListAdapter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //check condition
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            getContactList();

        } else {
            Toast.makeText(this, "Permission Denied.", Toast.LENGTH_LONG).show();
            checkPermission();
        }
    }

}