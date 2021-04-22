package com.example.fimebookandmarket.Inicio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fimebookandmarket.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText mEditTextUsuario, mEditTextEmail, mEditTextPass, mEditTextConfPass, mEditTextPhone;

    //VARIABLES DE LOS DATOS QUE VAMOS A REGISTRAR
    private String phone ="";
    private String email ="";
    private String password ="";
    private String confpass ="";
    private String usuario ="";

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
        mEditTextConfPass = findViewById(R.id.edtConfPass);
        mEditTextUsuario =findViewById(R.id.edtUsuario);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnRegister1:
                phone = mEditTextPhone.getText().toString();
                email = mEditTextEmail.getText().toString();
                password = mEditTextPass.getText().toString();
                confpass = mEditTextConfPass.getText().toString();
                usuario = mEditTextUsuario.getText().toString();

                if(!usuario.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confpass.isEmpty() && !phone.isEmpty()) {
                    if (password.length() >= 6) {
                        if(usuario.length() >=5){
                            if(password.equals(confpass)){
                                registerUser();
                            }else {
                                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(this, "El usuario debe tener al menos 5 caracteres", Toast.LENGTH_SHORT).show();
                        }
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
                    map.put("confpass", confpass);
                    map.put("usuario", usuario);

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
