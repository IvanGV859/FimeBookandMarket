package com.example.fimebookandmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
