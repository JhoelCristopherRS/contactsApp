package com.upn.contactsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.upn.contactsapp.entities.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);


        EditText name=findViewById(R.id.txt_nombre);
        Button btn = findViewById(R.id.btnCreateOnFirebase);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ContactmyRef = database.getReference("N00171225").child("contacts");

        List<Contact> contacts=new ArrayList<>();
        ContactmyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child:snapshot.getChildren()){
                    Contact c=child.getValue(Contact.class);
                    contacts.add(c);
                    Log.i("Main_APP",c.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn.setOnClickListener(v -> {
            //name
            String nombre=name.getText().toString();

            Contact c1 = new Contact(nombre, "12345678");
            c1.uuid = UUID.randomUUID().toString();

            //Contact c2 = new Contact("Miguel", "123456");
            ///c2.uuid = UUID.randomUUID().toString();

            ContactmyRef.child(c1.uuid).setValue(c1);

        });
    }
}