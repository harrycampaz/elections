package com.harrycampaz.votantes.models;

import android.content.ContentValues;

import com.harrycampaz.votantes.data.VotantesContract;

public class Votante extends Usuario {


    private String cedula;
    private String lugarVotacion;
    private Lider lider;

    public Votante(int id, String nombre,  String apellido, String correo, String telefono, String cedula, String lugarVotacion, Lider lider) {
        super(id, nombre, apellido, telefono, correo);
        this.cedula = cedula;
        this.lugarVotacion = lugarVotacion;
        this.lider = lider;
    }

    public Votante(String nombre,  String apellido, String correo, String telefono, String cedula, String lugarVotacion, Lider lider) {
        super(nombre, apellido, telefono, correo);

        this.cedula = cedula;
        this.lugarVotacion = lugarVotacion;
        this.lider = lider;
    }
    public Votante(int id, String nombre){
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

    public Lider getLider() {
        return lider;
    }

    public void setLider(Lider lider) {
        this.lider = lider;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(VotantesContract.VotanteEntry.COLUMN_NAME_NOMBRE, getNombre());
        values.put(VotantesContract.VotanteEntry.COLUMN_NAME_APELLIDO, getApellido());
        values.put(VotantesContract.VotanteEntry.COLUMN_NAME_CORREO, getCorreo());
        values.put(VotantesContract.VotanteEntry.COLUMN_NAME_TELEFONO, getTelefono());

        values.put(VotantesContract.VotanteEntry.COLUMN_NAME_CEDULA, cedula);
        values.put(VotantesContract.VotanteEntry.COLUMN_NAME_LUGARVOTACION, lugarVotacion);
        values.put(VotantesContract.VotanteEntry.COLUMN_NAME_IDLIDER, lider.getId());

        return values;
    }
}
