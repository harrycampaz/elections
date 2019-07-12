package com.harrycampaz.votantes.holder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.harrycampaz.votantes.R;
import com.harrycampaz.votantes.activities.AgregarLiderActivity;
import com.harrycampaz.votantes.activities.AgregarVotanteActivity;
import com.harrycampaz.votantes.data.VotacionesDBHelper;
import com.harrycampaz.votantes.models.Lider;
import com.harrycampaz.votantes.models.Votante;


public class VotateItem extends RecyclerView.ViewHolder {

    TextView nombre;
    TextView correo;
    TextView telefono;
    TextView candidato;

    TextView cedula;
    TextView lugarVotacion;

    Button editarItem;
    Button borrarItem;

    Context context;
    VotacionesDBHelper votacionesDBHelper;

    public VotateItem(@NonNull View itemView, Context context) {
        super(itemView);
        nombre = itemView.findViewById(R.id.itemNombre);
        correo = itemView.findViewById(R.id.itemCorreo);
        telefono = itemView.findViewById(R.id.itemTelefono);
        candidato = itemView.findViewById(R.id.itemCandidato);
        cedula = itemView.findViewById(R.id.itemCedula);
        lugarVotacion = itemView.findViewById(R.id.itemLugarVotacion);
        editarItem = itemView.findViewById(R.id.editarItem);
        borrarItem = itemView.findViewById(R.id.borrarItem);
        this.context = context;
        this.votacionesDBHelper = new VotacionesDBHelper(context);
    }
    public void bind(final Votante votante){
        System.out.println("bind Votantes");
        nombre.setText(votante.getNombre() + " "+ votante.getApellido());
        correo.setText("Correo: "+votante.getCorreo());
        telefono.setText("Telefono: " +votante.getTelefono());
        candidato.setText("Lider: "+ votante.getLider().getNombre());
        cedula.setText("Cedula: " +votante.getCedula());
        lugarVotacion.setText("Lugar de Votacion: "+ votante.getLugarVotacion());
        final Intent intentEdit = new Intent(context, AgregarVotanteActivity.class);
        intentEdit.putExtra("votante", votante);

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
                System.out.println(votacionesDBHelper.deleteData(votante));
            }
        });

    }
}

