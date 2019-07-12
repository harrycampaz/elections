package com.harrycampaz.votantes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.harrycampaz.votantes.R;
import com.harrycampaz.votantes.data.VotacionesDBHelper;
import com.harrycampaz.votantes.models.Item;
import com.harrycampaz.votantes.models.Lider;
import com.harrycampaz.votantes.models.Usuario;
import com.harrycampaz.votantes.models.Votante;

import java.util.ArrayList;
import java.util.List;

public class AgregarVotanteActivity extends AppCompatActivity {

    VotacionesDBHelper votacionesDBHelper;
    List<Usuario> lideres;
    EditText nombre;
    EditText apellido;
    EditText correo;
    EditText telefono;
    EditText cedula;
    EditText lugarVotacion;
    int idLider;
    Button submit;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_votante);

        spinner = (Spinner) findViewById(R.id.liderId);
        votacionesDBHelper = new VotacionesDBHelper(this);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        correo = findViewById(R.id.itemCorreo);
        telefono = findViewById(R.id.telefono);
        cedula = findViewById(R.id.itemCedula);
        lugarVotacion = findViewById(R.id.itemLugarVotacion);

        submit = findViewById(R.id.submit);

        final Votante votante = (Votante)  getIntent().getSerializableExtra("votante");

        if(votante != null){
            nombre.setText(votante.getNombre());
            apellido.setText(votante.getApellido());
            correo.setText(votante.getCorreo());
            telefono.setText(votante.getTelefono());
            cedula.setText(votante.getCedula());
            lugarVotacion.setText(votante.getLugarVotacion());
        }

        votacionesDBHelper = new VotacionesDBHelper(this);

        lideres = votacionesDBHelper.getLideres();

        initializeUI();

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(nombre.getText().toString().length() > 0
                        && apellido.getText().toString().length() > 0
                        && correo.getText().toString().length() > 0
                        && telefono.getText().toString().length() > 0
                        && cedula.getText().toString().length() > 0
                        && lugarVotacion.getText().toString().length() > 0){
                    if(votante != null){

                        votante.setNombre(nombre.getText().toString());
                        votante.setApellido(apellido.getText().toString());
                        votante.setCorreo(correo.getText().toString());
                        votante.setTelefono(telefono.getText().toString());
                        votante.setCedula(cedula.getText().toString());
                        votante.setLugarVotacion(lugarVotacion.getText().toString());
                        votante.setLider(new Lider(idLider, "name"));

                        System.out.println( votacionesDBHelper.updateData(votante));
                        Toast.makeText(getBaseContext(), "Votante Actualizado", Toast.LENGTH_LONG).show();

                    }else {

                        votacionesDBHelper.insertData(new Votante(nombre.getText().toString(), apellido.getText().toString(), correo.getText().toString(), telefono.getText().toString(), cedula.getText().toString(), lugarVotacion.getText().toString(), new Lider(idLider, "name")));
                        Toast.makeText(getBaseContext(), "Votante agregado", Toast.LENGTH_LONG).show();
                    }
                    finish();

                } else {
                    Toast.makeText(getBaseContext(), "Faltan datos", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void initializeUI() {

        final ArrayList<Item> cand = new ArrayList<>();

        for (Usuario can: lideres){
            cand.add(new Item(can.getNombre(), can.getId()));
        }

        ArrayAdapter<Item> adapter =
                new ArrayAdapter<Item>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, cand);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                System.out.println(cand.get(position).getName());
                idLider = cand.get(position).getId();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


    }
}
