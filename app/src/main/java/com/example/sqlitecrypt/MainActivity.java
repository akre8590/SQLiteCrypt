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
    private Button dbCryp;
    public  File path =  new File("/storage/emulated/0/02datos.db3");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase.loadLibs(this);


        dbButton = (Button) findViewById(R.id.btnDB);
        dbCryp = (Button) findViewById(R.id.btnCryp);

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

        dbCryp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDataBase()){
                    cryptDataBase();
                }else {
                    Toast.makeText(MainActivity.this, "La base de datos no existe", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void createDataBase(){

        db = SQLiteDatabase.openOrCreateDatabase(path,"", null);
        Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        /**db.execSQL("CREATE TABLE IF NOT EXISTS student(id INT,name VARCHAR);");
        db.execSQL("insert into student(id,name) VALUES(1, 'Sparta');");
        Cursor c=db.rawQuery("SELECT * FROM student", null);
        if(c.moveToFirst())
        {
            Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Base de datos no creada", Toast.LENGTH_SHORT).show();
        }**/
    }

    private void cryptDataBase(){
        SQLiteDatabase db;
        db = SQLiteDatabase.openOrCreateDatabase(path,"", null);
        db.changePassword("abc123");
        Toast.makeText(this, "Encriptación de base de datos exitosa", Toast.LENGTH_SHORT).show();
        /**Cursor c = db.rawQuery("SELECT * FROM student", null);
        if (c.moveToFirst()){
            Toast.makeText(this, "Encriptación de base de datos exitosa", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Encriptación de base de datos fallida", Toast.LENGTH_SHORT).show();
        }**/
    }

    private boolean checkDataBase() {
        return path.exists();
    }
}
