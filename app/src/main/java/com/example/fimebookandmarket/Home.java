package com.example.fimebookandmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {

    private TextView mTextViewNombre;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mTextViewNombre = findViewById(R.id.nombrePerfil);

        mDatabase.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    String id = mAuth.getCurrentUser().getUid();

                    String nombreP = dataSnapshot.child(id).child("usuario").getValue().toString();

                    mTextViewNombre.setText("Usuario: " + nombreP);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btnPerfil:
                Toast.makeText(Home.this,"Editar Perfil", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnMenu:
                Intent menu = new Intent(Home.this,Menu.class);
                startActivity(menu);
                break;
            case R.id.btnCerrarSesion:
                mAuth.signOut();
                Toast.makeText(this, "Cerrando sesion...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Home.this,MainActivity.class));
                finish();
                break;
        }
    }
}
