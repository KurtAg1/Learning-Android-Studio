package com.example.challengetext_register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnRegisterClick(View view){
        // Get values from input fields
        EditText editFirstName = findViewById(R.id.editTxtFirstName);
        EditText editLastName = findViewById(R.id.editTxtLastName);
        EditText editEmail = findViewById(R.id.editTxtEmail);

        // Get text views
        TextView txtFirstName = findViewById(R.id.txtFirstName);
        TextView txtLastName = findViewById(R.id.txtLastName);
        TextView txtEmail = findViewById(R.id.txtEmail);

        // Place input in text views
        txtFirstName.setText("First Name: " + editFirstName.getText().toString());
        txtLastName.setText("Last Name: " + editLastName.getText().toString());
        txtEmail.setText("Email: " + editEmail.getText().toString());
    }
}