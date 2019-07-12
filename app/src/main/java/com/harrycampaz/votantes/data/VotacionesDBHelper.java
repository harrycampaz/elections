package com.harrycampaz.votantes.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.harrycampaz.votantes.models.Candidato;
import com.harrycampaz.votantes.models.Coordinador;
import com.harrycampaz.votantes.models.Lider;
import com.harrycampaz.votantes.models.Usuario;
import com.harrycampaz.votantes.models.Votante;

import java.util.ArrayList;
import java.util.List;

public class VotacionesDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "votantes.db";
    private Context context;

    public VotacionesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println(" VotacionesDBHelper - Inicializar OnCreate ");
        // Create tables...
        db.execSQL("CREATE TABLE " + VotantesContract.CandidatoEntry.TABLE_NAME + " ("
                + VotantesContract.CandidatoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + VotantesContract.CandidatoEntry.COLUMN_NAME_NOMBRE + " TEXT NOT NULL,"
                + VotantesContract.CandidatoEntry.COLUMN_NAME_APELLIDO + " TEXT NOT NULL,"
                + VotantesContract.CandidatoEntry.COLUMN_NAME_TELEFONO  + " TEXT NOT NULL,"
                + VotantesContract.CandidatoEntry.COLUMN_NAME_CORREO + " TEXT,"

                + "UNIQUE (" + VotantesContract.CandidatoEntry._ID + "))");


        db.execSQL("CREATE TABLE " + VotantesContract.CoordinadorEntry.TABLE_NAME + " ("
                + VotantesContract.CoordinadorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + VotantesContract.CoordinadorEntry.COLUMN_NAME_NOMBRE + " TEXT NOT NULL,"
                + VotantesContract.CoordinadorEntry.COLUMN_NAME_APELLIDO + " TEXT NOT NULL,"
                + VotantesContract.CoordinadorEntry.COLUMN_NAME_TELEFONO  + " TEXT NOT NULL,"
                + VotantesContract.CoordinadorEntry.COLUMN_NAME_IDCANDIDATO  + " INTEGER NOT NULL,"
                + VotantesContract.CoordinadorEntry.COLUMN_NAME_CORREO + " TEXT,"
                + VotantesContract.CoordinadorEntry.COLUMN_NAME_CEDULA+ " TEXT,"
                + VotantesContract.CoordinadorEntry.COLUMN_NAME_LUGARVOTACION + " TEXT,"
                + "UNIQUE (" + VotantesContract.CoordinadorEntry._ID + "))");

        db.execSQL("CREATE TABLE " + VotantesContract.LiderEntry.TABLE_NAME + " ("
                + VotantesContract.LiderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + VotantesContract.LiderEntry.COLUMN_NAME_NOMBRE + " TEXT NOT NULL,"
                + VotantesContract.LiderEntry.COLUMN_NAME_APELLIDO + " TEXT NOT NULL,"
                + VotantesContract.LiderEntry.COLUMN_NAME_TELEFONO  + " TEXT NOT NULL,"
                + VotantesContract.LiderEntry.COLUMN_NAME_IDCOORDINADOR  + " INTEGER NOT NULL,"
                + VotantesContract.LiderEntry.COLUMN_NAME_CORREO + " TEXT,"
                + VotantesContract.LiderEntry.COLUMN_NAME_CEDULA+ " TEXT,"
                + VotantesContract.LiderEntry.COLUMN_NAME_LUGARVOTACION + " TEXT,"
                + "UNIQUE (" + VotantesContract.LiderEntry._ID + "))");

        db.execSQL("CREATE TABLE " + VotantesContract.VotanteEntry.TABLE_NAME + " ("
                + VotantesContract.VotanteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + VotantesContract.VotanteEntry.COLUMN_NAME_NOMBRE + " TEXT NOT NULL,"
                + VotantesContract.VotanteEntry.COLUMN_NAME_APELLIDO + " TEXT NOT NULL,"
                + VotantesContract.VotanteEntry.COLUMN_NAME_TELEFONO  + " TEXT NOT NULL,"
                + VotantesContract.VotanteEntry.COLUMN_NAME_IDLIDER  + " INTEGER NOT NULL,"
                + VotantesContract.VotanteEntry.COLUMN_NAME_CORREO + " TEXT,"
                + VotantesContract.VotanteEntry.COLUMN_NAME_CEDULA+ " TEXT,"
                + VotantesContract.VotanteEntry.COLUMN_NAME_LUGARVOTACION + " TEXT,"
                + "UNIQUE (" + VotantesContract.VotanteEntry._ID + "))");

    }

    public void abrir(){
        this.getWritableDatabase();

    }

    public long insertData(Usuario usuario) {
        System.out.println(" VotacionesDBHelper - Inicializar initRol ");
        SQLiteDatabase db = this.getReadableDatabase();

        if(usuario instanceof Candidato){
            return db.insert(
                    VotantesContract.CandidatoEntry.TABLE_NAME,
                    null,
                    ((Candidato) usuario).toContentValues());
        } else  if(usuario instanceof Coordinador){
            return db.insert(
                    VotantesContract.CoordinadorEntry.TABLE_NAME,
                    null,
                    ((Coordinador) usuario).toContentValues());
        } else  if(usuario instanceof Lider){
            return db.insert(
                    VotantesContract.LiderEntry.TABLE_NAME,
                    null,
                    ((Lider) usuario).toContentValues());
        } else {
            return db.insert(
                    VotantesContract.VotanteEntry.TABLE_NAME,
                    null,
                    ((Votante) usuario).toContentValues());
        }
    }

    public long deleteData(Usuario usuario) {
        System.out.println(" VotacionesDBHelper - Inicializar initRol ");
        SQLiteDatabase db = this.getReadableDatabase();

        if(usuario instanceof Candidato){
            //return db.delete(VotantesContract.CandidatoEntry.TABLE_NAME, null);
            db.delete(VotantesContract.CandidatoEntry.TABLE_NAME, VotantesContract.CandidatoEntry._ID + "=?", new String[]{""+usuario.getId()});
            return db.delete(VotantesContract.CoordinadorEntry.TABLE_NAME, VotantesContract.CoordinadorEntry.COLUMN_NAME_IDCANDIDATO + "=?", new String[]{""+usuario.getId()});
        } else  if(usuario instanceof Coordinador){

             db.delete(VotantesContract.CoordinadorEntry.TABLE_NAME, VotantesContract.CoordinadorEntry._ID + "=?", new String[]{""+usuario.getId()});
            return db.delete(VotantesContract.LiderEntry.TABLE_NAME, VotantesContract.LiderEntry.COLUMN_NAME_IDCOORDINADOR + "=?", new String[]{""+usuario.getId()});

        } else  if(usuario instanceof Lider){
             db.delete(VotantesContract.LiderEntry.TABLE_NAME, VotantesContract.LiderEntry._ID + "=?", new String[]{""+usuario.getId()});
            return db.delete(VotantesContract.VotanteEntry.TABLE_NAME, VotantesContract.VotanteEntry.COLUMN_NAME_IDLIDER + "=?", new String[]{""+usuario.getId()});

        } else {
            return db.delete(VotantesContract.VotanteEntry.TABLE_NAME, VotantesContract.VotanteEntry._ID + "=?", new String[]{""+usuario.getId()});
        }
    }

    public long updateData(Usuario usuario) {
        System.out.println(" VotacionesDBHelper - Inicializar updateData ");
        SQLiteDatabase db = this.getReadableDatabase();

        if(usuario instanceof Candidato){
           // String strSQL = "UPDATE myTable SET nombre = "+usuario.getNombre()+" WHERE _id = 4";
            //db.execSQL(strSQL);
            //return 0;
            return db.update(
                    VotantesContract.CandidatoEntry.TABLE_NAME,
                    ((Candidato) usuario).toContentValues(),
                    VotantesContract.CandidatoEntry._ID +"="+ usuario.getId(),
                    null);
        } else  if(usuario instanceof Coordinador){
            return db.update(
                    VotantesContract.CoordinadorEntry.TABLE_NAME,
                    ((Coordinador) usuario).toContentValues(),
                    VotantesContract.CoordinadorEntry._ID + "=?",
                    new String[]{""+usuario.getId()});
        } else  if(usuario instanceof Lider){
            return db.update(
                    VotantesContract.LiderEntry.TABLE_NAME,
                    ((Lider) usuario).toContentValues(),
                    VotantesContract.LiderEntry._ID + "=?",
                    new String[]{""+usuario.getId()});
        } else {
            return db.update(
                    VotantesContract.VotanteEntry.TABLE_NAME,
                    ((Votante) usuario).toContentValues(),
                    VotantesContract.VotanteEntry._ID + "=?",
                    new String[]{""+usuario.getId()});
        }
    }

    public List<Usuario> getCandidatos(int offset){
        List<Usuario> candidato = new ArrayList<>();

        String[] campo = {VotantesContract.CandidatoEntry._ID,
                VotantesContract.CandidatoEntry.COLUMN_NAME_NOMBRE,
                VotantesContract.CandidatoEntry.COLUMN_NAME_APELLIDO,
                VotantesContract.CandidatoEntry.COLUMN_NAME_TELEFONO,
                VotantesContract.CandidatoEntry.COLUMN_NAME_CORREO,
        };

        Cursor c = this.getReadableDatabase()
                .query(VotantesContract.CandidatoEntry.TABLE_NAME,
                        campo, null, null, null,null, VotantesContract.CandidatoEntry._ID+ " DESC limit "+ offset +" offset 0");

        int id = c.getColumnIndex(VotantesContract.CandidatoEntry._ID);
        int nombre = c.getColumnIndex(VotantesContract.CandidatoEntry.COLUMN_NAME_NOMBRE);
        int apellido = c.getColumnIndex(VotantesContract.CandidatoEntry.COLUMN_NAME_APELLIDO);
        int telefono = c.getColumnIndex(VotantesContract.CandidatoEntry.COLUMN_NAME_TELEFONO);
        int correo = c.getColumnIndex(VotantesContract.CandidatoEntry.COLUMN_NAME_CORREO);

        if (c.moveToFirst()) {

            do {

                Candidato candidatoL = new Candidato(c.getInt(id), c.getString(nombre), c.getString(apellido), c.getString(telefono), c.getString(correo));
                candidato.add(candidatoL);

            } while(c.moveToNext());
        }
        return candidato;
    }

    public List<Usuario> getCoordinadores(){
        List<Usuario> coordinador = new ArrayList<>();


        String query = "select candidato._id as idCandidato, coordinador._id as idCoordinador, candidato.nombre as nombreCandidato, coordinador.nombre as nombreCoordinador , coordinador.apellido as apellidoCoordinador, coordinador.telefono as telefonoCoordinador, coordinador.correo as correoCoordinador from " + VotantesContract.CoordinadorEntry.TABLE_NAME + ", " + VotantesContract.CandidatoEntry.TABLE_NAME + " WHERE candidato_id = candidato._id";
        System.out.println(query);

        Cursor c = this.getReadableDatabase()
        .rawQuery(query, null);

        int id = c.getColumnIndex("idCoordinador");
        int nombre = c.getColumnIndex("nombreCoordinador");
        int apellido = c.getColumnIndex("apellidoCoordinador");
        int telefono = c.getColumnIndex("telefonoCoordinador");
        int correo = c.getColumnIndex("correoCoordinador");
        int idCandidato = c.getColumnIndex("idCandidato");

        int nombreCandidato =  c.getColumnIndex("nombreCandidato");


        if (c.moveToFirst()) {

            do {

                Coordinador candidatoL = new Coordinador(c.getInt(id), c.getString(nombre), c.getString(apellido), c.getString(telefono), c.getString(correo), new Candidato(c.getInt(idCandidato), c.getString(nombreCandidato)));
                coordinador.add(candidatoL);

            } while(c.moveToNext());
        }
        return coordinador;
    }

    public List<Usuario> getByCandidatoCoordinadores(int idcandi){
        List<Usuario> coordinador = new ArrayList<>();


        String query = "select candidato._id as idCandidato, coordinador._id as idCoordinador, candidato.nombre as nombreCandidato, coordinador.nombre as nombreCoordinador , coordinador.apellido as apellidoCoordinador, coordinador.telefono as telefonoCoordinador, coordinador.correo as correoCoordinador from " + VotantesContract.CoordinadorEntry.TABLE_NAME + ", " + VotantesContract.CandidatoEntry.TABLE_NAME + " WHERE candidato_id = candidato._id and candidato_id = "+idcandi+"";
        System.out.println(query);

        Cursor c = this.getReadableDatabase()
                .rawQuery(query, null);

        int id = c.getColumnIndex("idCoordinador");
        int nombre = c.getColumnIndex("nombreCoordinador");
        int apellido = c.getColumnIndex("apellidoCoordinador");
        int telefono = c.getColumnIndex("telefonoCoordinador");
        int correo = c.getColumnIndex("correoCoordinador");
        int idCandidato = c.getColumnIndex("idCandidato");

        int nombreCandidato =  c.getColumnIndex("nombreCandidato");


        if (c.moveToFirst()) {

            do {

                Coordinador candidatoL = new Coordinador(c.getInt(id), c.getString(nombre), c.getString(apellido), c.getString(telefono), c.getString(correo), new Candidato(c.getInt(idCandidato), c.getString(nombreCandidato)));
                coordinador.add(candidatoL);

            } while(c.moveToNext());
        }
        return coordinador;
    }

    public List<Usuario> getVotantes(){
        List<Usuario> votantes = new ArrayList<>();


        String query = "select votante._id as idVotante, lider._id as idLider, votante.nombre as nombreVotante, lider.nombre as nombreLider , votante.apellido as apellidoVotante, votante.telefono as telefonoVotante, votante.correo as correoVotante, votante.cedula as cedulaVotante, votante.lugar_votacion as lugarVotacionVotante   from " + VotantesContract.VotanteEntry.TABLE_NAME + ", " + VotantesContract.LiderEntry.TABLE_NAME + " WHERE lider_id = lider._id";
        System.out.println(query);

        Cursor c = this.getReadableDatabase()
                .rawQuery(query, null);

        int id = c.getColumnIndex("idVotante");
        int nombre = c.getColumnIndex("nombreVotante");
        int apellido = c.getColumnIndex("apellidoVotante");
        int telefono = c.getColumnIndex("telefonoVotante");
        int correo = c.getColumnIndex("correoVotante");
        int idLider = c.getColumnIndex("idLider");
        int cedula = c.getColumnIndex("cedulaVotante");
        int lugarVotacionVotante = c.getColumnIndex("lugarVotacionVotante");

        int nombreCandidato =  c.getColumnIndex("nombreLider");


        if (c.moveToFirst()) {

            do {

                Votante candidatoL = new Votante(c.getInt(id), c.getString(nombre), c.getString(apellido), c.getString(correo), c.getString(telefono), c.getString(cedula),c.getString(lugarVotacionVotante), new Lider(c.getInt(idLider), c.getString(nombreCandidato)));
                votantes.add(candidatoL);

            } while(c.moveToNext());
        }
        return votantes;
    }

    public List<Usuario> getByCoordinadorLideres(int idCoor){
        List<Usuario> lideres = new ArrayList<>();


        String query = "select lider._id as idLider, coordinador._id as idCoordinador, lider.nombre as nombreLider, coordinador.nombre as nombreCoordinador , lider.apellido as apellidoLider, lider.telefono as telefonoLider, Lider.correo as correoLider from " + VotantesContract.CoordinadorEntry.TABLE_NAME + ", " + VotantesContract.LiderEntry.TABLE_NAME + " WHERE coordinador_id = coordinador._id and coordinador_id = " + idCoor+"";
        System.out.println(query);

        Cursor c = this.getReadableDatabase()
                .rawQuery(query, null);

        int id = c.getColumnIndex("idLider");
        int nombre = c.getColumnIndex("nombreLider");
        int apellido = c.getColumnIndex("apellidoLider");
        int telefono = c.getColumnIndex("telefonoLider");
        int correo = c.getColumnIndex("correoLider");
        int idCandidato = c.getColumnIndex("idCoordinador");

        int nombreCandidato =  c.getColumnIndex("nombreCoordinador");


        if (c.moveToFirst()) {

            do {

                Lider candidatoL = new Lider(c.getInt(id), c.getString(nombre), c.getString(apellido), c.getString(telefono), c.getString(correo), new Coordinador(c.getInt(idCandidato), c.getString(nombreCandidato)));
                lideres.add(candidatoL);

            } while(c.moveToNext());
        }
        return lideres;
    }

    public List<Usuario> getLideres(){
        List<Usuario> lideres = new ArrayList<>();


        String query = "select lider._id as idLider, coordinador._id as idCoordinador, lider.nombre as nombreLider, coordinador.nombre as nombreCoordinador , lider.apellido as apellidoLider, lider.telefono as telefonoLider, Lider.correo as correoLider from " + VotantesContract.CoordinadorEntry.TABLE_NAME + ", " + VotantesContract.LiderEntry.TABLE_NAME + " WHERE coordinador_id = coordinador._id";
        System.out.println(query);

        Cursor c = this.getReadableDatabase()
                .rawQuery(query, null);

        int id = c.getColumnIndex("idLider");
        int nombre = c.getColumnIndex("nombreLider");
        int apellido = c.getColumnIndex("apellidoLider");
        int telefono = c.getColumnIndex("telefonoLider");
        int correo = c.getColumnIndex("correoLider");
        int idCandidato = c.getColumnIndex("idCoordinador");

        int nombreCandidato =  c.getColumnIndex("nombreCoordinador");


        if (c.moveToFirst()) {

            do {

                Lider candidatoL = new Lider(c.getInt(id), c.getString(nombre), c.getString(apellido), c.getString(telefono), c.getString(correo), new Coordinador(c.getInt(idCandidato), c.getString(nombreCandidato)));
                lideres.add(candidatoL);

            } while(c.moveToNext());
        }
        return lideres;
    }

    public List<Usuario> getByLiderVotantes(int idVot){
        List<Usuario> votantes = new ArrayList<>();


        String query = "select votante._id as idVotante, lider._id as idLider, votante.nombre as nombreVotante, lider.nombre as nombreLider , votante.apellido as apellidoVotante, votante.telefono as telefonoVotante, votante.correo as correoVotante, votante.cedula as cedulaVotante, votante.lugar_votacion as lugarVotacionVotante   from " + VotantesContract.VotanteEntry.TABLE_NAME + ", " + VotantesContract.LiderEntry.TABLE_NAME + " WHERE lider_id = lider._id and lider_id = " + idVot+"";
        System.out.println(query);

        Cursor c = this.getReadableDatabase()
                .rawQuery(query, null);

        int id = c.getColumnIndex("idVotante");
        int nombre = c.getColumnIndex("nombreVotante");
        int apellido = c.getColumnIndex("apellidoVotante");
        int telefono = c.getColumnIndex("telefonoVotante");
        int correo = c.getColumnIndex("correoVotante");
        int idLider = c.getColumnIndex("idLider");
        int cedula = c.getColumnIndex("cedulaVotante");
        int lugarVotacionVotante = c.getColumnIndex("lugarVotacionVotante");

        int nombreCandidato =  c.getColumnIndex("nombreLider");


        if (c.moveToFirst()) {

            do {

                Votante candidatoL = new Votante(c.getInt(id), c.getString(nombre), c.getString(apellido), c.getString(telefono), c.getString(correo), c.getString(cedula),c.getString(lugarVotacionVotante), new Lider(c.getInt(idLider), c.getString(nombreCandidato)));
                votantes.add(candidatoL);

            } while(c.moveToNext());
        }
        return votantes;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
