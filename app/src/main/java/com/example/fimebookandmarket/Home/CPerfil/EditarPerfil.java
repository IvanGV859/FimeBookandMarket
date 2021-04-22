package com.example.fimebookandmarket.Home.CPerfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fimebookandmarket.Home.Perfil;
import com.example.fimebookandmarket.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditarPerfil extends AppCompatActivity {

    private TextView mEditTextNombreP, mEditTextPhoneP;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mEditTextNombreP = findViewById(R.id.editTextusuario);
        mEditTextPhoneP = findViewById(R.id.editTextTextcelular);



        mDatabase.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    String id = mAuth.getCurrentUser().getUid();

                    String nombreP = dataSnapshot.child(id).child("usuario").getValue().toString();
                    mEditTextNombreP.setText(nombreP);

                    String phoneP = dataSnapshot.child(id).child("phone").getValue().toString();
                    mEditTextPhoneP.setText(phoneP);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        String Name = mEditTextNombreP.getText().toString();
        String phone = mEditTextPhoneP.getText().toString();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnGuardarE:



                Intent perfilP = new Intent(EditarPerfil.this, Perfil.class);
                startActivity(perfilP);
                break;
        }
    }


}