package com.example.fimebookandmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        mTextViewNombre = (TextView) findViewById(R.id.nombrePerfil);

        mDatabase.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    String nombre1 = dataSnapshot.child("usuario").getValue().toString();

                    mTextViewNombre.setText(nombre1);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

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
