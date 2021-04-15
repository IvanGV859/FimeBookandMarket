package com.example.fimebookandmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    private EditText mEditTextEmail, mEditTextPass;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        mEditTextEmail = findViewById(R.id.edtEmail);
        mEditTextPass = findViewById(R.id.edtPass);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.Signin:
                userLogin();
                break;
        }
    }

    private void userLogin(){

        String email = mEditTextEmail.getText().toString().trim();
        String password = mEditTextPass.getText().toString().trim();

        if(email.isEmpty()){
            mEditTextEmail.setError("Se requiere un correo");
            mEditTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEditTextEmail.setError("Ingrese un correo valido");
            mEditTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            mEditTextPass.setError("Se requiere una contraseña");
            mEditTextPass.requestFocus();
            return;
        }

        if(password.length() < 6){
            mEditTextPass.setError("Contraseña incorrecta!");
            mEditTextPass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(SignIn.this,Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(SignIn.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
