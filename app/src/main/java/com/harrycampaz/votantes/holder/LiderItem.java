package com.harrycampaz.votantes.holder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.harrycampaz.votantes.R;
import com.harrycampaz.votantes.activities.AgregarCoordinadorActivity;
import com.harrycampaz.votantes.activities.AgregarLiderActivity;
import com.harrycampaz.votantes.activities.VerPorLiderActivity;
import com.harrycampaz.votantes.activities.VerPorVotanteActivity;
import com.harrycampaz.votantes.data.VotacionesDBHelper;
import com.harrycampaz.votantes.models.Coordinador;
import com.harrycampaz.votantes.models.Lider;


public class LiderItem extends RecyclerView.ViewHolder {

    TextView nombre;
    TextView correo;
    TextView telefono;
    TextView candidato;
    Button ver;

    Button editarItem;
    Button borrarItem;

    Context context;
    VotacionesDBHelper votacionesDBHelper;

    public LiderItem(@NonNull View itemView, Context context) {
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
    public void bind(final Lider lider){
        System.out.println("bind Votantes");
        nombre.setText(lider.getNombre() + " "+ lider.getApellido());
        correo.setText("Correo: "+lider.getCorreo());
        telefono.setText("Telefono: " +lider.getTelefono());
        candidato.setText("Coordinador: "+ lider.getCoordinador().getNombre());

        final Intent intent = new Intent(context, VerPorVotanteActivity.class);
        intent.putExtra("lider", lider);


        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intent);
                System.out.println("CLick");
            }
        });

        final Intent intentEdit = new Intent(context, AgregarLiderActivity.class);
        intentEdit.putExtra("lider", lider);

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
                System.out.println(votacionesDBHelper.deleteData(lider));
            }
        });
    }
}

