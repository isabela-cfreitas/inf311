package com.example.pratica3_inf311_112692;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] menu = new String[] {"Minha casa na cidade natal", "Minha casa em Viçosa", "Meu departamento", "Relatório", "Fechar aplicação"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String aux = l.getItemAtPosition(position).toString();

        if (aux.equals("Fechar aplicação")) {
            finish();
        } else if (aux.equals("Relatório")) {
            Intent intentRelatorio = new Intent(MainActivity.this, ReportActivity.class);
            startActivity(intentRelatorio);
        } else {
            Intent intentMapa = new Intent(MainActivity.this, MapsActivity.class);
            DBgerencia db = new DBgerencia(this);
            if (aux.equals("Minha casa na cidade natal")) {
                intentMapa.putExtra("local", "natal");
                db.salvarLog("Minha casa na cidade natal", "-20.7546", "-42.8825");
            } else if (aux.equals("Minha casa em Viçosa")) {
                intentMapa.putExtra("local", "vicosa");
                db.salvarLog("Minha casa em Viçosa", "-20.7610", "-42.8710");
            } else if (aux.equals("Meu departamento")) {
                intentMapa.putExtra("local", "departamento");
                db.salvarLog("Meu departamento", "-20.7649", "-42.8685");
            }
            startActivity(intentMapa);
        }
    }
}