package com.example.fimebookandmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Perfil extends AppCompatActivity {

    private TextView mTextViewNombreP, mTextViewEmailP, mTextViewPhoneP;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mTextViewNombreP = findViewById(R.id.usuarioPerfil);
        mTextViewEmailP = findViewById(R.id.emailPerfil);
        mTextViewPhoneP = findViewById(R.id.phonePerfil);


        mDatabase.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    String id = mAuth.getCurrentUser().getUid();

                    String nombreP = dataSnapshot.child(id).child("usuario").getValue().toString();
                    mTextViewNombreP.setText(nombreP);

                    String emailP = dataSnapshot.child(id).child("email").getValue().toString();
                    mTextViewEmailP.setText(emailP);

                    String phoneP = dataSnapshot.child(id).child("phone").getValue().toString();
                    mTextViewPhoneP.setText(phoneP);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}