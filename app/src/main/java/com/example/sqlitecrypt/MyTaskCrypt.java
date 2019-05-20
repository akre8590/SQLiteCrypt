package com.example.sqlitecrypt;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.sqlitecrypt.database.SQLiteDatabase;

import java.io.File;

public class MyTaskCrypt extends AsyncTask<Void, Void, Void> {

     SQLiteDatabase db;


     File path =  new File("/storage/emulated/0/test2.db3");
     Context context;
     ProgressDialog progress;
     private long time;

     public MyTaskCrypt(Context context, ProgressDialog progress){
        this.context = context;
        this.progress = progress;
    }

    @Override
    protected void onPreExecute() {
      progress.show();
      time = System.currentTimeMillis();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        //createDataBase();
        /**db1 = org.sqlite.database.sqlite.SQLiteDatabase.openOrCreateDatabase(path,null);
        db1.execSQL("PRAGMA rehexkey = 'secretkey'");
        db1.close();**/
        cryptDataBase();

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
       progress.dismiss();
        Toast.makeText(context, "Encriptación finalizada", Toast.LENGTH_SHORT).show();
        Log.d("TestTask", "Diferencia de Tiempo = " + (System.currentTimeMillis() - time));
    }

    private void createDataBase(){

        db = SQLiteDatabase.openOrCreateDatabase(path,"", null);

    }

    private void cryptDataBase(){
        //SQLiteDatabase db;
        db = SQLiteDatabase.openOrCreateDatabase(path,"", null);
        db.changePassword("abc123");
        db.close();
    }
}


