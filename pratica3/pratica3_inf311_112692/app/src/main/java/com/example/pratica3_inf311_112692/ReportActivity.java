package com.example.pratica3_inf311_112692;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class ReportActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        DBgerencia db = new DBgerencia(this);
        ArrayList<String> logs = db.buscarLogs();

        if (logs.isEmpty()) {
            logs.add("Nenhum registro de acesso encontrado.");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, logs);
        setListAdapter(adapter);
    }
}