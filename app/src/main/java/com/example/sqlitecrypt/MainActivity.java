package com.example.sqlitecrypt;

import android.Manifest;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sqlitecrypt.database.SQLiteDatabase;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;


    private Button dbButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase.loadLibs(this);


        dbButton = (Button) findViewById(R.id.btnDB);


        dbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //createDataBase();
                ProgressDialog progress = new ProgressDialog(MainActivity.this);
                progress.setMessage("Encriptando base de datos");
                progress.setCancelable(false);
                new MyTaskCrypt(MainActivity.this, progress).execute();
            }
        });

    }

}
