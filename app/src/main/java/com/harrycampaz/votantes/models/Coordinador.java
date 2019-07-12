package com.harrycampaz.votantes.models;

import android.content.ContentValues;

import com.harrycampaz.votantes.data.VotantesContract;

import java.io.Serializable;

public class Coordinador extends Usuario implements Serializable {


    private String cedula;
    private String lugarVotacion;
    private Candidato candidato;

    public Coordinador(int id, String nombre, String apellido, String correo, String telefono, Candidato candidato) {
        super(id, nombre, apellido, telefono, correo);

        this.cedula = cedula;
        this.lugarVotacion = lugarVotacion;
        this.candidato = candidato;
    }

    public Coordinador(String nombre,  String apellido, String correo, String telefono, Candidato candidato) {
        super(nombre, apellido, telefono, correo);

        this.cedula = cedula;
        this.lugarVotacion = lugarVotacion;
        this.candidato = candidato;
    }
    public Coordinador(int id, String nombre){
        super(id, nombre);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getLugarVotacion() {
        return lugarVotacion;
    }

    public void setLugarVotacion(String lugarVotacion) {
        this.lugarVotacion = lugarVotacion;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(VotantesContract.CoordinadorEntry.COLUMN_NAME_NOMBRE, getNombre());
        values.put(VotantesContract.CoordinadorEntry.COLUMN_NAME_APELLIDO, getApellido());
        values.put(VotantesContract.CoordinadorEntry.COLUMN_NAME_CORREO, getCorreo());
        values.put(VotantesContract.CoordinadorEntry.COLUMN_NAME_TELEFONO, getTelefono());

        values.put(VotantesContract.CoordinadorEntry.COLUMN_NAME_CEDULA, cedula);
        values.put(VotantesContract.CoordinadorEntry.COLUMN_NAME_LUGARVOTACION, lugarVotacion);
        values.put(VotantesContract.CoordinadorEntry.COLUMN_NAME_IDCANDIDATO, candidato.getId());

        return values;
    }
}
