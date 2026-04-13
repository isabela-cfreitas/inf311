package com.example.pratica2_inf311_112692;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class relatorio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_relatorio);

        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome_usuario");
        String idade = intent.getStringExtra("idade_usuario");
        String pesoStr = intent.getStringExtra("peso_usuario");
        String alturaStr = intent.getStringExtra("altura_usuario");
        double peso = Double.parseDouble(pesoStr);
        double altura = Double.parseDouble(alturaStr);
        double imc = peso/(altura * altura);

        String classif = "";
        if (imc < 18.5) classif = "Abaixo do peso";
        else if (imc < 25) classif = "Peso Ideal";
        else classif = "Sobrepeso";

        ((TextView) findViewById(R.id.nome_res)).setText(nome);
        ((TextView) findViewById(R.id.idade_res)).setText(idade + " anos");
        ((TextView) findViewById(R.id.peso_res)).setText(pesoStr + " Kg");
        ((TextView) findViewById(R.id.altura_res)).setText(alturaStr + " m");
        ((TextView) findViewById(R.id.imc_res)).setText(String.format("%.1f Kg/m²", imc));
        ((TextView) findViewById(R.id.class_res)).setText(classif);

    }
    @Override
    protected void onStart() {
        super.onStart(); Log.d("CicloVida", getLocalClassName() + ": onStart()");
    }
    @Override
    protected void onResume() {
        super.onResume(); Log.d("CicloVida", getLocalClassName() + ": onResume()");
    }
    @Override
    protected void onPause() {
        super.onPause(); Log.d("CicloVida", getLocalClassName() + ": onPause()");
    }
    @Override
    protected void onStop() {
        super.onStop(); Log.d("CicloVida", getLocalClassName() + ": onStop()");
    }
    @Override
    protected void onDestroy() {super.onDestroy(); Log.d("CicloVida", getLocalClassName() + ": onDestroy()");
    }
    public void voltar(View v) {
        finish();
    }
}

