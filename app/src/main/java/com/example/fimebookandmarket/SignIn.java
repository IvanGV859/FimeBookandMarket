package com.example.fimebookandmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignIn extends AppCompatActivity {

    private EditText mEditTextEmail, mEditTextPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEditTextEmail = findViewById(R.id.edtEmail);
        mEditTextPass = findViewById(R.id.edtPass);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.Signin:
                Intent intent = new Intent(SignIn.this,Home.class);
                startActivity(intent);
                break;
        }
    }
}
