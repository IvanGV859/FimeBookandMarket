package com.example.fimebookandmarket.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fimebookandmarket.Inicio.MainActivity;
import com.example.fimebookandmarket.R;
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

        mTextViewNombre = findViewById(R.id.nombreHome);

        mDatabase.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    String id = mAuth.getCurrentUser().getUid();

                    String nombreP = dataSnapshot.child(id).child("usuario").getValue().toString();

                    mTextViewNombre.setText(nombreP);
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
                Intent perfil = new Intent(Home.this, Perfil.class);
                startActivity(perfil);
                break;
            case R.id.btnMenu:
                Intent menu = new Intent(Home.this, Menu.class);
                startActivity(menu);
                break;
            case R.id.btnConfig:
                Intent configP = new Intent(Home.this, Configuracion.class);
                startActivity(configP);
                break;
            case R.id.btnCerrarSesion:
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);

                builder.setMessage("¿Estas seguro que quieres cerrar sesión?")
                        .setTitle("Cerrar sesión");

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth.signOut();
                        Toast.makeText(Home.this, "Cerrando sesion...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Home.this, MainActivity.class));
                        finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
    }
}
