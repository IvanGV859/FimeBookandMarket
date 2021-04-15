package com.example.fimebookandmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnSignin:
                Intent signIn = new Intent(MainActivity.this,SignIn.class);
                startActivity(signIn);
                break;
            case R.id.btnRegister:
                Intent register = new Intent(MainActivity.this,Register.class);
                startActivity(register);
                break;
        }
    }

    @Override
    protected void onStart(){
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this, Home.class));
            finish();
        }
    }
}
