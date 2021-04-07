package com.example.fimebookandmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button btrLibr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btrLibr = findViewById(R.id.btnLibr);

        btrLibr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent libros = new Intent(Menu.this,Libros.class);
                startActivity(libros);
            }
        });

    }
}
