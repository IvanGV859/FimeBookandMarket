package com.example.fimebookandmarket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Configuracion extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        mAuth = FirebaseAuth.getInstance();
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btnCerrarSesionConfig:
                AlertDialog.Builder builder = new AlertDialog.Builder(Configuracion.this);

                builder.setMessage("¿Estas seguro que quieres cerrar sesión?")
                        .setTitle("Cerrar sesión");

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth.signOut();
                        Toast.makeText(Configuracion.this, "Cerrando sesion...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Configuracion.this,MainActivity.class));
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