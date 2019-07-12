package com.harrycampaz.votantes.models;

import android.content.ContentValues;

import com.harrycampaz.votantes.data.VotantesContract;

public class Lider extends Usuario {


    private String cedula;
    private String lugarVotacion;
    private Coordinador coordinador;

    public Lider(int id, String nombre,  String apellido, String correo, String telefono,Coordinador coordinador) {
        super(id, nombre, apellido, telefono, correo);

        this.coordinador = coordinador;
    }

    public Lider(String nombre,  String apellido, String correo, String telefono,Coordinador coordinador) {
        super(nombre, apellido, correo, telefono);

        this.coordinador = coordinador;
    }
    public Lider(int id, String nombre){
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

    public Coordinador getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(VotantesContract.LiderEntry.COLUMN_NAME_NOMBRE, getNombre());
        values.put(VotantesContract.LiderEntry.COLUMN_NAME_APELLIDO, getApellido());
        values.put(VotantesContract.LiderEntry.COLUMN_NAME_CORREO, getCorreo());
        values.put(VotantesContract.LiderEntry.COLUMN_NAME_TELEFONO, getTelefono());

        values.put(VotantesContract.LiderEntry.COLUMN_NAME_CEDULA, cedula);
        values.put(VotantesContract.LiderEntry.COLUMN_NAME_LUGARVOTACION, lugarVotacion);
        values.put(VotantesContract.LiderEntry.COLUMN_NAME_IDCOORDINADOR, coordinador.getId());

        return values;
    }

}
