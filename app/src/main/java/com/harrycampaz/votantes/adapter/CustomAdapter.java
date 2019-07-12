package com.harrycampaz.votantes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harrycampaz.votantes.R;
import com.harrycampaz.votantes.holder.CandidatoItem;
import com.harrycampaz.votantes.holder.CoordinadorItem;
import com.harrycampaz.votantes.holder.LiderItem;
import com.harrycampaz.votantes.holder.VotateItem;
import com.harrycampaz.votantes.models.Candidato;
import com.harrycampaz.votantes.models.Coordinador;
import com.harrycampaz.votantes.models.Lider;
import com.harrycampaz.votantes.models.Usuario;
import com.harrycampaz.votantes.models.Votante;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Usuario> usuariosList;
    private Context context;

    private static final int TYPE_CANDIDATO = 0;
    private static final int TYPE_COORDINADOR = 1;
    private static final int TYPE_LIDER = 2;
    private static final int TYPE_VOTANTE = 3;

    public  CustomAdapter(Context context, List<Usuario> usuariosList){
        this.context = context;
        this.usuariosList = usuariosList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        System.out.println("onCreateViewHolder Votantes");
        if(i == TYPE_CANDIDATO){
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_candidato, viewGroup, false);
            return new CandidatoItem(itemView, context);

        } else  if(i == TYPE_COORDINADOR){
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_coodinador, viewGroup, false);
            return new CoordinadorItem(itemView, context);
        } else  if(i == TYPE_LIDER){
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_lider, viewGroup, false);
            return new LiderItem(itemView, context);
        } else {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_votante, viewGroup, false);
            return new VotateItem(itemView, context);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        System.out.println("onBindView Votantes");

        if (viewHolder instanceof CandidatoItem) {
            CandidatoItem holderCandidato = (CandidatoItem) viewHolder;
            holderCandidato.bind((Candidato) usuariosList.get(i));
        }else if(viewHolder instanceof CoordinadorItem){
            CoordinadorItem holderCoordinador = (CoordinadorItem) viewHolder;
            holderCoordinador.bind((Coordinador) usuariosList.get(i));
        }else if(viewHolder instanceof LiderItem){
            LiderItem  holderVotante = (LiderItem) viewHolder;
            holderVotante.bind((Lider) usuariosList.get(i));
        } else {
            VotateItem  holderVotante = (VotateItem) viewHolder;
            holderVotante.bind((Votante) usuariosList.get(i));
        }

    }

    @Override
    public int getItemCount() {
        return usuariosList.size();
    }
    @Override
    public int getItemViewType(int position) {
        if(usuariosList.get(position) instanceof  Candidato){
            return  TYPE_CANDIDATO;
        }else if(usuariosList.get(position) instanceof Coordinador) {
            return TYPE_COORDINADOR;
        }else if(usuariosList.get(position) instanceof Lider) {
            return  TYPE_LIDER;
        } else {
            return TYPE_VOTANTE;
        }

    }
}
