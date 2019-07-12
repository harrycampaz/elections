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
import com.harrycampaz.votantes.models.Candidato;
import com.harrycampaz.votantes.models.Coordinador;
import com.harrycampaz.votantes.models.Item;
import com.harrycampaz.votantes.models.Lider;
import com.harrycampaz.votantes.models.Usuario;
import com.harrycampaz.votantes.models.Votante;

import java.util.ArrayList;
import java.util.List;

public class AgregarLiderActivity extends AppCompatActivity {
    VotacionesDBHelper votacionesDBHelper;
    List<Usuario> coordinador;
    EditText nombre;
    EditText apellido;
    EditText correo;
    EditText telefono;
    int idCandidato;
    Button submit;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_lider);

        spinner = (Spinner) findViewById(R.id.coordinadorId);
        votacionesDBHelper = new VotacionesDBHelper(this);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        correo = findViewById(R.id.itemCorreo);
        telefono = findViewById(R.id.telefono);
        submit = findViewById(R.id.submit);

        votacionesDBHelper = new VotacionesDBHelper(this);

        final Lider lider = (Lider)  getIntent().getSerializableExtra("lider");

        if(lider != null){
            nombre.setText(lider.getNombre());
            apellido.setText(lider.getApellido());
            correo.setText(lider.getCorreo());
            telefono.setText(lider.getTelefono());

        }

        coordinador = votacionesDBHelper.getCoordinadores();

        initializeUI();

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(nombre.getText().toString().length() > 0
                        && apellido.getText().toString().length() > 0
                        && correo.getText().toString().length() > 0
                        && telefono.getText().toString().length() > 0){

                    if(lider != null){

                        lider.setNombre(nombre.getText().toString());
                        lider.setApellido(apellido.getText().toString());
                        lider.setCorreo(correo.getText().toString());
                        lider.setTelefono(telefono.getText().toString());
                        lider.setCoordinador(new Coordinador(idCandidato, "name"));

                        System.out.println( votacionesDBHelper.updateData(lider));
                        Toast.makeText(getBaseContext(), "Lider Actualizado", Toast.LENGTH_LONG).show();

                    }else {
                        votacionesDBHelper.insertData(new Lider(nombre.getText().toString(), apellido.getText().toString(), correo.getText().toString(), telefono.getText().toString(), new Coordinador(idCandidato, "name")));
                        Toast.makeText(getBaseContext(), "Lider agregado", Toast.LENGTH_LONG).show();
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

        for (Usuario can: coordinador){
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
                idCandidato = cand.get(position).getId();

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


    }
}
