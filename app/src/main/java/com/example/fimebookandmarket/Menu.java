package com.example.fimebookandmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnLibr:
                Intent libros = new Intent(Menu.this,Libros.class);
                startActivity(libros);
                break;
        }
    }
}
