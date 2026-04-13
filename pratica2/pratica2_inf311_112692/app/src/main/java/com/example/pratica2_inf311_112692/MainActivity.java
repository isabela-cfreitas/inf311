package com.example.pratica2_inf311_112692;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.util.Log;

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

    public void tela2(View v) {
        EditText Nome = findViewById(R.id.val_nome);
        EditText Idade = findViewById(R.id.val_idade);
        EditText Peso = findViewById(R.id.val_peso);
        EditText Altura = findViewById(R.id.val_altura);
        Intent intent = new Intent(this, relatorio.class);
        intent.putExtra("nome_usuario", Nome.getText().toString());
        intent.putExtra("idade_usuario", Idade.getText().toString());
        intent.putExtra("peso_usuario", Peso.getText().toString());
        intent.putExtra("altura_usuario", Altura.getText().toString());

        startActivity(intent);
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
}