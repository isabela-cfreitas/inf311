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

    boolean ultimo_foi_igual = false;

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
        String expr = c1.getText().toString();

        String tag = v.getTag().toString();
        if (tag.equals("limpar")) {
            c1.setText("");
            return;
        } else if (tag.equals("shift")) {
            if (!expr.isEmpty()) {
                c1.setText(expr.substring(0,expr.length()-1));
            }
            return;
        } else if (tag.equals("igual")) {
            if (expr.isEmpty()) return;
            double resultado;
            if (expr.contains("+")) {
                String[] partes = expr.split("\\+");
                resultado = Double.parseDouble(partes[0]) + Double.parseDouble(partes[1]);
            } else if (expr.contains("-")) {
                String[] partes = expr.split("\\-");
                resultado = Double.parseDouble(partes[0]) - Double.parseDouble(partes[1]);
            } else if (expr.contains("*")) {
                String[] partes = expr.split("\\*");
                resultado = Double.parseDouble(partes[0]) * Double.parseDouble(partes[1]);
            } else if (expr.contains("/")){
                String[] partes = expr.split("/");
                Double v2 = Double.parseDouble(partes[1]);
                if (v2==0) {
                    c1.setText("ERRO: Divisão por 0");
                    return;
                }
                resultado = Double.parseDouble(partes[0]) / v2;
            } else {
                resultado = Double.parseDouble(expr);
            }
            c1.setText(String.valueOf(resultado));
            ultimo_foi_igual = true;
            return;
        }else if (tag.equals("soma") || tag.equals("mult") || tag.equals("div") || tag.equals("sub")) {
            ultimo_foi_igual = false;
            if (expr.isEmpty()) {
                return;
            } else {
                char antes = expr.charAt(expr.length() - 1);
                if (antes == '+' || antes == '-' || antes == '*' || antes == '/') {
                    return;
                } else {
                    if (tag.equals("soma")) c1.append("+");
                    if (tag.equals("mult")) c1.append("*");
                    if (tag.equals("div")) c1.append("/");
                    if (tag.equals("sub")) c1.append("-");
                    return;
                }
            }
        } else if (tag.matches("[0-9]") || tag.equals(".")) {
            if (ultimo_foi_igual) {
                c1.setText("");
                ultimo_foi_igual = false;
            }
            if (tag.equals(".") && expr.contains(".")) {
                return;
            }

            c1.append(tag);
            return;
        }
    }
}