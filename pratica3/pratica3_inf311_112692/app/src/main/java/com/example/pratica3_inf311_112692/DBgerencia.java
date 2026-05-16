package com.example.pratica3_inf311_112692;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBgerencia extends SQLiteOpenHelper {
    public DBgerencia(Context context) {
        super(context, "Dados.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Logs (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "msg TEXT, " +
                "lat TEXT, " +
                "lng TEXT, " +
                "dt DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Logs");
        onCreate(db);
    }
    public void salvarLog(String mensagem, String latitude, String longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("msg", mensagem);
        valores.put("lat", latitude);
        valores.put("lng", longitude);
        db.insert("Logs", null, valores);
        db.close();
    }

    public java.util.ArrayList<String> buscarLogs() {
        java.util.ArrayList<String> lista = new java.util.ArrayList<>();
        android.database.sqlite.SQLiteDatabase db = this.getReadableDatabase();

        android.database.Cursor cursor = db.rawQuery("SELECT msg, lat, lng, dt FROM Logs ORDER BY id DESC", null);

        if (cursor.moveToFirst()) {
            do {
                String log = cursor.getString(0) + "\n" +
                        "Lat: " + cursor.getString(1) + " | Lng: " + cursor.getString(2) + "\n" +
                        "Data: " + cursor.getString(3);
                lista.add(log);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }
}