package com.upn.contactsapp;

import android.os.Bundle;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upn.contactsapp.entities.Contact;

public class firebase_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        Button btn =findViewById(R.id.btn_registro);

        btn.setOnClickListener(v -> {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            //database.getReference("message").setValue("Hola firebase");


            DatabaseReference myRef = database.getReference("Contactos");
            Contact C1 = new Contact("YOEL","12345789");
            Contact C2 = new Contact("Zegarra","999999999");

            myRef.push().setValue(C1);
            myRef.push().setValue(C2);
        });
    }
}