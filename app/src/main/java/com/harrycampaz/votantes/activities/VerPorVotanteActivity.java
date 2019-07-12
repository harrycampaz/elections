package com.harrycampaz.votantes.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.harrycampaz.votantes.R;
import com.harrycampaz.votantes.adapter.CustomAdapter;
import com.harrycampaz.votantes.data.VotacionesDBHelper;
import com.harrycampaz.votantes.models.Lider;
import com.harrycampaz.votantes.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class VerPorVotanteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private VotacionesDBHelper votacionesDBHelper;

    private List<Usuario> usuarioList = new ArrayList<>();

    private List<Usuario> usuList = new ArrayList<>();

    Lider lider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinador);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        votacionesDBHelper = new VotacionesDBHelper(this);
        lider = (Lider) getIntent().getSerializableExtra("lider");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              System.out.println(lider.getNombre());
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(new CustomAdapter(this, usuarioList));

        getData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Votantes - "+ lider.getNombre());
    }

    private void getData(){

        usuList = votacionesDBHelper.getByLiderVotantes(lider.getId());

        usuarioList.addAll(usuList);

        recyclerView.getAdapter().notifyDataSetChanged();

    }


}
