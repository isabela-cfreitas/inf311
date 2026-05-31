package com.example.pratica4_aplicativo2_inf311_112692;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void devolver (View v) {
        Intent intentRecebida = getIntent();

        float luz = intentRecebida.getFloatExtra("luz", 0.0f);
        float prox = intentRecebida.getFloatExtra("prox", 0.0f);

        String luzClass;
        if (luz < 20.0f) {
            luzClass = "baixa";
        } else {
            luzClass = "alta";
        }

        String proxClass;
        if (prox > 3.0f) {
            proxClass = "distante";
        } else {
            proxClass = "perto";
        }

        Intent it = new Intent();
        it.putExtra("luzClass", luzClass);
        it.putExtra("proxClass", proxClass);

        setResult(RESULT_OK, it);
        finish();
    }
}