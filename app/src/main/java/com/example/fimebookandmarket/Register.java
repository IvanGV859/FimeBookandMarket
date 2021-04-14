package com.example.fimebookandmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText mEditTextPhone;
    private EditText mEditTextEmail;
    private EditText mEditTextPass;

    //VARIABLES DE LOS DATOS QUE VAMOS A REGISTRAR
    private String phone ="";
    private String email ="";
    private String password ="";

    //FirebaseAuth nos brinda el paquete de autenticacion de firebase
    FirebaseAuth mAuth;
    //Base de datos
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEditTextPhone = findViewById(R.id.edtPhone);
        mEditTextEmail = findViewById(R.id.edtEmail);
        mEditTextPass = findViewById(R.id.edtPass);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnRegister1:
                phone = mEditTextPhone.getText().toString();
                email = mEditTextEmail.getText().toString();
                password = mEditTextPass.getText().toString();

                if(!phone.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    if (password.length() >= 6) {
                        registerUser();
                    }
                    else {
                        Toast.makeText(Register.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Register.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //Validacion si se registro correctamente guardar los datos ingresados
                if(task.isSuccessful()) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("phone", phone);
                    map.put("email", email);
                    map.put("password", password);

                    String id = mAuth.getCurrentUser().getUid();

                    //Crear nodo hijo en la base de datos y setValue nos pide un mapa de valores
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            //Verificar si registra los datos correctamente en la base de datos
                            if(task2.isSuccessful()){
                                startActivity(new Intent(Register.this, MainActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(Register.this, "No se registraron los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(Register.this, "No se registro el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
