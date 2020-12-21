package com.example.sqldemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class EditCustomer extends Activity {

    private Button btn_saveChanges, btn_cancel, btn_delete;
    private EditText et_name, et_age;
    private Switch sw_activeCustomer;

    private CustomerModel editCustomer;

    private DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Database Helper
        dataBaseHelper = new DataBaseHelper(EditCustomer.this);

        // Display new Activity
        setContentView(R.layout.edit_customer);

        // Get selected customer
        Intent editCustomerIntent = getIntent();
        editCustomer = (CustomerModel) editCustomerIntent.getSerializableExtra("customer");

        // Initialize Resources
        btn_saveChanges = findViewById(R.id.edit_btn_saveChanges);
        btn_cancel = findViewById(R.id.edit_btn_cancel);
        btn_delete = findViewById(R.id.edit_btn_delete);

        et_name = findViewById(R.id.edit_et_name);
        et_age = findViewById(R.id.edit_et_age);

        sw_activeCustomer = findViewById(R.id.edit_sw_active);

        initDefaultValues();

        // On Click Listeners

        btn_saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCustomer.setName(et_name.getText().toString());
                editCustomer.setAge(Integer.parseInt(et_age.getText().toString()));
                editCustomer.setActive(sw_activeCustomer.isChecked());

                dataBaseHelper.updateOne(editCustomer);
                Toast.makeText(EditCustomer.this, "Updated " + editCustomer.getName(), Toast.LENGTH_SHORT).show();
                finishActivity();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.deleteOne(editCustomer);
                Toast.makeText(EditCustomer.this, "Deleted " + editCustomer.getName(), Toast.LENGTH_SHORT).show();
                finishActivity();
            }
        });
    }

    private void initDefaultValues(){
        // Add default values
        et_name.setText(editCustomer.getName());
        et_age.setText(""+editCustomer.getAge());
        sw_activeCustomer.setChecked(editCustomer.isActive());
    }

    private void finishActivity(){
        Intent goBackToMainActivity = new Intent(EditCustomer.this, MainActivity.class);
        startActivity(goBackToMainActivity);
    }
}
