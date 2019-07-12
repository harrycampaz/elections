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
import com.harrycampaz.votantes.activities.CoordinadorActivity;
import com.harrycampaz.votantes.activities.VerPorCoodinadorActivity;
import com.harrycampaz.votantes.data.VotacionesDBHelper;
import com.harrycampaz.votantes.models.Candidato;
import com.harrycampaz.votantes.models.Coordinador;
import com.harrycampaz.votantes.models.Usuario;

public class CandidatoItem extends RecyclerView.ViewHolder {

    TextView nombre;
    TextView correo;
    TextView telefono;
    Button ver;
    Button editarItem;
    Button borrarItem;

    Context context;
    VotacionesDBHelper votacionesDBHelper;

    public CandidatoItem(@NonNull View itemView, Context context) {
        super(itemView);
        nombre = itemView.findViewById(R.id.itemNombre);
        correo = itemView.findViewById(R.id.itemCorreo);
        telefono = itemView.findViewById(R.id.itemTelefono);
        ver = itemView.findViewById(R.id.verItem);
        editarItem = itemView.findViewById(R.id.editarItem);
        borrarItem = itemView.findViewById(R.id.borrarItem);
        this.context = context;
        this.votacionesDBHelper = new VotacionesDBHelper(context);
    }
    public void bind(final Candidato candidato){
        System.out.println("bind Votantes");
        nombre.setText(candidato.getNombre() + " "+ candidato.getApellido());
        correo.setText("Correo: "+candidato.getCorreo());
        telefono.setText("Telefono: " +candidato.getTelefono());

        final Intent intent = new Intent(context, VerPorCoodinadorActivity.class);

        intent.putExtra("candidato", candidato);
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(intent);
                System.out.println("On click coordinar");
            }
        });
        final Intent intentEdit = new Intent(context, AgregarCandidatoActivity.class);
        intentEdit.putExtra("candidato", candidato);
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
                System.out.println(votacionesDBHelper.deleteData(candidato));
            }
        });
    }
}
