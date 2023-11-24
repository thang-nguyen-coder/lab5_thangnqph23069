package com.example.lab5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProductActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtName, edtPrice, edtDes;
    private Button btnSave, btnDelete;
    String pid, strName, strPrice, strDes;
    GetProductDetailsTask productDetailsTask;
    SaveProductDetailsTask saveProductDetailsTask;
    DeleteProductTask deleteProductTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        edtName = (EditText) findViewById(R.id.edtProductName);
        edtPrice = (EditText) findViewById(R.id.edtProductPrice);
        edtDes = (EditText) findViewById(R.id.edtProductDes);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        pid = getIntent().getStringExtra(Constants.TAG_PID);
        productDetailsTask = new
                GetProductDetailsTask(this, edtName, edtPrice, edtDes);
        productDetailsTask.execute(pid);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btnSave) {
            strName = edtName.getText().toString();
            strPrice = edtPrice.getText().toString();
            strDes = edtDes.getText().toString();
            saveProductDetailsTask = new SaveProductDetailsTask(this);
            saveProductDetailsTask.execute(pid, strName, strPrice, strDes);
        } else if (view.getId() == R.id.btnDelete) {
            deleteProductTask = new DeleteProductTask(this);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Deleting product...");
            builder.setMessage("Are you sure you want to delete this product?");
            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    deleteProductTask.execute(pid);
                    dialogInterface.dismiss();
                    Toast.makeText(EditProductActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                }
            });
            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }

    }
}