package com.example.a112692_pratica1_inf311;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

        //final botao = (Button)findViewById()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void calcular(View v) {
        EditText c1 = findViewById(R.id.c1);
        EditText c2 = findViewById(R.id.c2);
        String valor1 = c1.getText().toString();

        String valor2 = c2.getText().toString();

        TextView res = findViewById(R.id.res);
        if (valor1.isEmpty() || valor2.isEmpty()) {
            res.setText("É necessário preencher com dois números");
            return;
        }
        Double v1 = Double.parseDouble(valor1);
        Double v2 = Double.parseDouble(valor2);

        String tag = v.getTag().toString();
        double r;
        if (tag.equals("soma")) {
            r = v1+v2;
        } else if (tag.equals("sub")) {
            r=v1-v2;
        } else if (tag.equals("mult")) {
            r = v1*v2;
        } else {
            if (v2==0) {
                res.setText("Divisão inválida (denominador igual a zero)");
                return;
            }
            r = v1/v2;
        }
        res.setText("O resultado é: " + r);
    }
}