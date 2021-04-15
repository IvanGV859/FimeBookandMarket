package com.example.fimebookandmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
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
