package com.example.pratica4_inf311_112692;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.sensorintent_leituras.LanternaHelper;
import com.example.sensorintent_leituras.MotorHelper;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sm;
    Sensor sensorLuz, sensorProx;
    float valorLuz, valorProx;

    LanternaHelper lanterna;
    MotorHelper motor;
    Switch switchLanterna, switchMotor;

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

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorLuz = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorProx = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        lanterna = new LanternaHelper(this);
        motor = new MotorHelper(this);
        switchLanterna = findViewById(R.id.switchLanterna);
        switchMotor = findViewById(R.id.switchMotor);
    }

    public void classificar (View v) {
        Intent it = new Intent("ACAO_CONSEGUIR_CLASSIFICACAO");
        it.putExtra("luz", valorLuz);
        it.putExtra("prox", valorProx);
        startActivityForResult(it, 1504);
    }

    @Override
    protected  void onActivityResult (int codigoRequisicao, int codigoResultado, Intent it) {
        super.onActivityResult(codigoRequisicao, codigoResultado, it);
        if (codigoRequisicao == 1504 && codigoResultado == RESULT_OK && it != null) {
            String luzClass = it.getStringExtra("luzClass");
            String proxClass = it.getStringExtra("proxClass");
            if ("baixa".equalsIgnoreCase(luzClass)) {
                lanterna.ligar();
                switchLanterna.setChecked(true);
            } else {
                lanterna.desligar();
                switchLanterna.setChecked(false);
            }
            if ("distante".equalsIgnoreCase(proxClass)) {
                motor.iniciarVibracao();
                switchMotor.setChecked(true);
            } else {
                motor.pararVibracao();
                switchMotor.setChecked(false);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorLuz != null && sensorProx!=null){
            sm.registerListener(this,sensorLuz, SensorManager.SENSOR_DELAY_GAME);
            sm.registerListener(this,sensorProx, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (lanterna != null) lanterna.desligar();
        if (motor != null) motor.pararVibracao();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor s = event.sensor;

        if(s.getType() == Sensor.TYPE_PROXIMITY) {
            valorProx = event.values[0];
        } else if (s.getType() == Sensor.TYPE_LIGHT) {
            valorLuz = event.values[0];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        String prec = "";
        switch (accuracy) {
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                prec = "Baixa";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                prec = "Média";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                prec = "Alta";
                break;
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                prec = "Sinal indisponível – não confiável";
                break;
            default:
        }
        Log.i("SENSOR_PRECISAO", "NOME: " + sensor.getName() + " Precisão: " + prec);
    }

}