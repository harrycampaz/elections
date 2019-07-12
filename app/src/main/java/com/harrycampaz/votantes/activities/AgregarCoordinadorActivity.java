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
import com.harrycampaz.votantes.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AgregarCoordinadorActivity extends AppCompatActivity {


    VotacionesDBHelper votacionesDBHelper;
    List<Usuario> candidatoes;
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
        setContentView(R.layout.activity_agregar_coordinador);
        spinner = (Spinner) findViewById(R.id.candidadoId);
        votacionesDBHelper = new VotacionesDBHelper(this);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        correo = findViewById(R.id.itemCorreo);
        telefono = findViewById(R.id.telefono);
        submit = findViewById(R.id.submit);

        votacionesDBHelper = new VotacionesDBHelper(this);
        final Coordinador coordinador = (Coordinador)  getIntent().getSerializableExtra("coordinador");

        if(coordinador != null){
            nombre.setText(coordinador.getNombre());
            apellido.setText(coordinador.getApellido());
            correo.setText(coordinador.getCorreo());
            telefono.setText(coordinador.getTelefono());
        }

        candidatoes = votacionesDBHelper.getCandidatos(10);

        initializeUI();

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(nombre.getText().toString().length() > 0
                        && apellido.getText().toString().length() > 0
                        && correo.getText().toString().length() > 0
                        && telefono.getText().toString().length() > 0){

                    if(coordinador != null){

                        coordinador.setNombre(nombre.getText().toString());
                        coordinador.setApellido(apellido.getText().toString());
                        coordinador.setCorreo(correo.getText().toString());
                        coordinador.setTelefono(telefono.getText().toString());
                        coordinador.setCandidato(new Candidato(idCandidato, "name"));

                        System.out.println( votacionesDBHelper.updateData(coordinador));
                        Toast.makeText(getBaseContext(), "Coordinador Actualizado", Toast.LENGTH_LONG).show();

                    }else {
                        votacionesDBHelper.insertData(new Coordinador(nombre.getText().toString(), apellido.getText().toString(), correo.getText().toString(), telefono.getText().toString(), new Candidato(idCandidato, "name")));
                        Toast.makeText(getBaseContext(), "Coordinador agregado", Toast.LENGTH_LONG).show();
                    }
                    finish();

                } else {
                    Toast.makeText(getBaseContext(), "Faltan datos", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void initializeUI() {

        final ArrayList<Candidatos> cand = new ArrayList<>();

        for (Usuario can: candidatoes ){
            cand.add(new Candidatos(can.getNombre(), can.getId()));
        }

        ArrayAdapter<Candidatos> adapter =
                new ArrayAdapter<Candidatos>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, cand);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                System.out.println(cand.get(position).name);
                idCandidato = cand.get(position).candidato_id;

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


    }

    private class Candidatos {
        private String name;
        private int candidato_id;

        public Candidatos() {
        }

        public Candidatos(String contact_name, int contact_id) {
            this.name = contact_name;
            this.candidato_id = contact_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCandidato_id() {
            return candidato_id;
        }

        public void setCandidato_id(int candidato_id) {
            this.candidato_id = candidato_id;
        }

        /**
         * Pay attention here, you have to override the toString method as the
         * ArrayAdapter will reads the toString of the given object for the name
         *
         * @return name
         */
        @Override
        public String toString() {
            return name;
        }
    }
}
