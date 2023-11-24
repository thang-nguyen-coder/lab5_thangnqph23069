package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnView, btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnView = findViewById(R.id.btnViewProducts);
        btnAdd = findViewById(R.id.btnAddProduct);
        btnView.setOnClickListener(v -> {
            startActivity(new Intent(this, AllProductsActivity.class));
        });
        btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, AddProductActivity.class));
        });
    }
}