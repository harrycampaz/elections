package com.harrycampaz.votantes.holder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.harrycampaz.votantes.R;
import com.harrycampaz.votantes.activities.AgregarCandidatoActivity;
import com.harrycampaz.votantes.activities.AgregarCoordinadorActivity;
import com.harrycampaz.votantes.activities.VerPorCoodinadorActivity;
import com.harrycampaz.votantes.activities.VerPorLiderActivity;
import com.harrycampaz.votantes.data.VotacionesDBHelper;
import com.harrycampaz.votantes.models.Coordinador;
import com.harrycampaz.votantes.models.Usuario;


public class CoordinadorItem extends RecyclerView.ViewHolder {

    TextView nombre;
    TextView correo;
    TextView telefono;
    TextView candidato;
    Button ver;
    Button editarItem;
    Button borrarItem;

    Context context;
    VotacionesDBHelper votacionesDBHelper;

    public CoordinadorItem(@NonNull View itemView, Context context) {
        super(itemView);
        nombre = itemView.findViewById(R.id.itemNombre);
        correo = itemView.findViewById(R.id.itemCorreo);
        telefono = itemView.findViewById(R.id.itemTelefono);
        candidato = itemView.findViewById(R.id.itemCandidato);
        ver = itemView.findViewById(R.id.verItem);
        editarItem = itemView.findViewById(R.id.editarItem);
        borrarItem = itemView.findViewById(R.id.borrarItem);
        this.context = context;
        this.votacionesDBHelper = new VotacionesDBHelper(context);
    }

    public void bind(final Coordinador coordinador){



        System.out.println("bind Votantes");
        nombre.setText(coordinador.getNombre() + " "+ coordinador.getApellido());
        correo.setText("Correo: "+coordinador.getCorreo());
        telefono.setText("Telefono: " +coordinador.getTelefono());
        candidato.setText("Candicato: "+ coordinador.getCandidato().getNombre());

        final Intent intent = new Intent(context, VerPorLiderActivity.class);
        intent.putExtra("coordinador", coordinador);

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intent);
                System.out.println("On click coordinar");
            }
        });

        final Intent intentEdit = new Intent(context, AgregarCoordinadorActivity.class);
        intentEdit.putExtra("coordinador", coordinador);

        editarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intentEdit);
                System.out.println("Editar");
            }
        });


        borrarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Borrar");
                System.out.println(votacionesDBHelper.deleteData(coordinador));
            }
        });
    }
}

