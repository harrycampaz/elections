package com.harrycampaz.votantes.models;

import android.content.ContentValues;

import com.harrycampaz.votantes.data.VotantesContract;

import java.io.Serializable;

public class Candidato  extends Usuario implements Serializable {

    public Candidato(int id, String nombre, String apellido, String telefono, String correo) {
        super(id, nombre, apellido, telefono, correo);
    }

    public Candidato(String nombre, String apellido, String telefono, String correo) {
        super(nombre, apellido, telefono, correo);
    }

    public Candidato(int id, String nombre){
        super(id, nombre);
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(VotantesContract.CandidatoEntry.COLUMN_NAME_NOMBRE, getNombre());
        values.put(VotantesContract.CandidatoEntry.COLUMN_NAME_APELLIDO, getApellido());
        values.put(VotantesContract.CandidatoEntry.COLUMN_NAME_CORREO, getCorreo());
        values.put(VotantesContract.CandidatoEntry.COLUMN_NAME_TELEFONO, getTelefono());
        return values;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
