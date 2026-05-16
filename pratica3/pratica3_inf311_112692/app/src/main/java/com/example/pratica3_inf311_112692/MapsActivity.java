package com.example.pratica3_inf311_112692;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        local = getIntent().getStringExtra("local");

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapaFragment)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (local != null) {
            if (local.equals("natal")) cliqueBotao(findViewById(R.id.btnNatal));
            if (local.equals("vicosa")) cliqueBotao(findViewById(R.id.btnVicosa));
            if (local.equals("departamento")) cliqueBotao(findViewById(R.id.btnDepto));
        }
    }

    public void cliqueBotao(View v) {
        if (mMap == null) return;
        mMap.clear();
        LatLng coord = new LatLng(-20.76500566656298, -42.86841749444079);
        String titulo = "Meu departamento";

        int id = v.getId();

        if (id == R.id.btnNatal) {
            coord = new LatLng(-20.758072337802414, -42.88050274356949);
            titulo = "Minha casa na cidade natal";
        } else if (id == R.id.btnVicosa) {
            coord = new LatLng(-20.758072337802414, -42.88050274356949);
            titulo = "Minha casa em Viçosa";
        }
        mMap.addMarker(new MarkerOptions().position(coord).title(titulo));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coord, 16f));

        DBgerencia db = new DBgerencia(this);
        db.salvarLog(titulo, String.valueOf(coord.latitude), String.valueOf(coord.longitude));
    }
}