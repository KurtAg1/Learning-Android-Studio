package com.example.sqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // references to buttons and other controls on the layout
    private Button btn_add, btn_viewAll, btn_search;
    private EditText et_name, et_age, et_searchText;
    private Switch sw_activeCustomer;
    private ListView lv_customerList;

    private ArrayAdapter customerArrayAdapter;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        btn_search = findViewById(R.id.btn_search);
        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);
        et_searchText = findViewById(R.id.et_searchText);
        sw_activeCustomer = findViewById(R.id.sw_active);
        lv_customerList = findViewById(R.id.lv_customerList);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        ShowCustomersOnListView(dataBaseHelper);

        // button listeners for the add and view all buttons
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerModel customerModel;

                try{
                    customerModel = new CustomerModel(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_activeCustomer.isChecked());
                    Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "error", 0, false);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean success = dataBaseHelper.addOne(customerModel);

                Toast.makeText(MainActivity.this, "Success = " + success, Toast.LENGTH_SHORT).show();
                ShowCustomersOnListView(dataBaseHelper);
            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                // Clear Search Text
                et_searchText.setText("");

                ShowCustomersOnListView(dataBaseHelper);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filter = et_searchText.getText().toString();
                ShowCustomersOnListView(dataBaseHelper, filter);
                Toast.makeText(MainActivity.this, "Results were filtered", Toast.LENGTH_SHORT).show();
            }
        });

        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get clicked customer
                CustomerModel clickedCustomer = (CustomerModel) parent.getItemAtPosition(position);

                Intent editCustomerIntent = new Intent(MainActivity.this,
                        EditCustomer.class);

                editCustomerIntent.putExtra("customer", clickedCustomer);

                startActivity(editCustomerIntent);

//                dataBaseHelper.deleteOne(clickedCustomer);
//                ShowCustomersOnListView(dataBaseHelper);
//                Toast.makeText(MainActivity.this, "Deleted " + clickedCustomer.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ShowCustomersOnListView(DataBaseHelper dataBaseHelper) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        lv_customerList.setAdapter(customerArrayAdapter);
    }

    private void ShowCustomersOnListView(DataBaseHelper dataBaseHelper, String filter) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone(filter));
        lv_customerList.setAdapter(customerArrayAdapter);
    }
}