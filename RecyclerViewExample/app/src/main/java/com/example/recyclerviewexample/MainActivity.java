package com.example.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView contactsRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsRecView = findViewById(R.id.contactsRecView);

        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Kurt Agius", "kagius@gmail.com", "https://scontent.fmla3-1.fna.fbcdn.net/v/t1.0-9/43222783_1882078618576700_8164862705212063744_o.jpg?_nc_cat=104&ccb=2&_nc_sid=09cbfe&_nc_ohc=kpICxM-zXHwAX80EwRv&_nc_ht=scontent.fmla3-1.fna&oh=a613c2a5fde16dfda95aaa34808b957d&oe=60030B42"));
        contacts.add(new Contact("Iebes Agius", "kagius@gmail.com", "https://scontent.fmla3-1.fna.fbcdn.net/v/t1.0-9/43222783_1882078618576700_8164862705212063744_o.jpg?_nc_cat=104&ccb=2&_nc_sid=09cbfe&_nc_ohc=kpICxM-zXHwAX80EwRv&_nc_ht=scontent.fmla3-1.fna&oh=a613c2a5fde16dfda95aaa34808b957d&oe=60030B42"));
        contacts.add(new Contact("Mark Agius", "kagius@gmail.com", "https://scontent.fmla3-1.fna.fbcdn.net/v/t1.0-9/43222783_1882078618576700_8164862705212063744_o.jpg?_nc_cat=104&ccb=2&_nc_sid=09cbfe&_nc_ohc=kpICxM-zXHwAX80EwRv&_nc_ht=scontent.fmla3-1.fna&oh=a613c2a5fde16dfda95aaa34808b957d&oe=60030B42"));
        contacts.add(new Contact("Johnny Agius", "kagius@gmail.com", "https://scontent.fmla3-1.fna.fbcdn.net/v/t1.0-9/43222783_1882078618576700_8164862705212063744_o.jpg?_nc_cat=104&ccb=2&_nc_sid=09cbfe&_nc_ohc=kpICxM-zXHwAX80EwRv&_nc_ht=scontent.fmla3-1.fna&oh=a613c2a5fde16dfda95aaa34808b957d&oe=60030B42"));
        contacts.add(new Contact("Ronald Agius", "kagius@gmail.com", "https://scontent.fmla3-1.fna.fbcdn.net/v/t1.0-9/43222783_1882078618576700_8164862705212063744_o.jpg?_nc_cat=104&ccb=2&_nc_sid=09cbfe&_nc_ohc=kpICxM-zXHwAX80EwRv&_nc_ht=scontent.fmla3-1.fna&oh=a613c2a5fde16dfda95aaa34808b957d&oe=60030B42"));


        ContactsRecViewAdapter adapter = new ContactsRecViewAdapter(this);
        adapter.setContacts(contacts);

        contactsRecView.setAdapter(adapter);
        contactsRecView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}