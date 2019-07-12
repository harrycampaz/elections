package com.harrycampaz.votantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.harrycampaz.votantes.activities.CandidatoActivity;
import com.harrycampaz.votantes.activities.CoordinadorActivity;
import com.harrycampaz.votantes.activities.LiderActivity;
import com.harrycampaz.votantes.activities.VotanteActivity;

public class MainActivity extends AppCompatActivity {

     Button buttonCandidato;
     Button buttonCoordinador;
     Button buttonLideres;
     Button buttonVotantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCandidato  = findViewById(R.id.buttonCandidato);
        buttonCoordinador = findViewById(R.id.buttonCoordinador);
        buttonLideres = findViewById(R.id.buttonLideres);
        buttonVotantes = findViewById(R.id.buttonVotantes);
        final Intent intentCandidato = new Intent(this, CandidatoActivity.class);

        final Intent intentCoordianador = new Intent(this, CoordinadorActivity.class);

        final Intent intentLider = new Intent(this, LiderActivity.class);

        final Intent intentVotante = new Intent(this, VotanteActivity.class);

        buttonCandidato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(intentCandidato);
            }
        });
        buttonCoordinador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intentCoordianador);

            }
        });

        buttonLideres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentLider);
            }
        });

        buttonVotantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentVotante);
            }
        });
    }


}
