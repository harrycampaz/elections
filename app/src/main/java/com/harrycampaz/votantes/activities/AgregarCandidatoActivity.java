package com.harrycampaz.votantes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.harrycampaz.votantes.R;
import com.harrycampaz.votantes.data.VotacionesDBHelper;
import com.harrycampaz.votantes.models.Candidato;

public class AgregarCandidatoActivity extends AppCompatActivity {

    EditText nombre;
    EditText apellido;
    EditText correo;
    EditText telefono;
    Button submit;
    VotacionesDBHelper votacionesDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_candidato);

        votacionesDBHelper = new VotacionesDBHelper(this);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        correo = findViewById(R.id.itemCorreo);
        telefono = findViewById(R.id.telefono);
        submit = findViewById(R.id.submit);

        final Candidato candidato = (Candidato)  getIntent().getSerializableExtra("candidato");

        if(candidato != null){
            nombre.setText(candidato.getNombre());
            apellido.setText(candidato.getApellido());
            correo.setText(candidato.getCorreo());
            telefono.setText(candidato.getTelefono());
        }

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(nombre.getText().toString().length() > 0
                        && apellido.getText().toString().length() > 0
                && correo.getText().toString().length() > 0
                        && telefono.getText().toString().length() > 0){

                    if(candidato != null){

                        candidato.setNombre(nombre.getText().toString());
                        candidato.setApellido(apellido.getText().toString());
                        candidato.setCorreo(correo.getText().toString());
                        candidato.setTelefono(telefono.getText().toString());

                        System.out.println( votacionesDBHelper.updateData(candidato));
                        Toast.makeText(getBaseContext(), "Candidato Actualizado", Toast.LENGTH_LONG).show();

                    }else {
                        votacionesDBHelper.insertData(new Candidato(nombre.getText().toString(),apellido.getText().toString(), telefono.getText().toString(),correo.getText().toString()));
                        Toast.makeText(getBaseContext(), "Candidato agregado", Toast.LENGTH_LONG).show();
                    }

                      finish();

                } else {
                    Toast.makeText(getBaseContext(), "Faltan datos", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
