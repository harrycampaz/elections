package com.harrycampaz.votantes.data;

import android.provider.BaseColumns;

public class VotantesContract {
    public  static final class  RolEntry implements BaseColumns{
        public static final String TABLE_NAME ="rol";

        public static final String COLUMN_NAME_NOMBRE = "nombre";
    }

    public  static final class CandidatoEntry implements BaseColumns{
        public static final String TABLE_NAME ="candidato";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_APELLIDO = "apellido";
        public static final String COLUMN_NAME_TELEFONO = "telefono";
        public static final String COLUMN_NAME_CORREO = "correo";

    }

    public  static final class CoordinadorEntry implements BaseColumns{
        public static final String TABLE_NAME ="coordinador";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_APELLIDO = "apellido";
        public static final String COLUMN_NAME_TELEFONO = "telefono";
        public static final String COLUMN_NAME_CORREO = "correo";
        public static final String COLUMN_NAME_CEDULA = "cedula";
        public static final String COLUMN_NAME_DIRECCION = "direccion";
        public static final String COLUMN_NAME_LUGARVOTACION = "lugar_votacion";
        public static final String COLUMN_NAME_IDCANDIDATO = "candidato_id";

    }

    public  static final class LiderEntry implements BaseColumns{
        public static final String TABLE_NAME ="lider";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_APELLIDO = "apellido";
        public static final String COLUMN_NAME_TELEFONO = "telefono";
        public static final String COLUMN_NAME_CORREO = "correo";
        public static final String COLUMN_NAME_CEDULA = "cedula";
        public static final String COLUMN_NAME_DIRECCION = "direccion";
        public static final String COLUMN_NAME_LUGARVOTACION = "lugar_votacion";
        public static final String COLUMN_NAME_IDCOORDINADOR = "coordinador_id";

    }

    public  static final class VotanteEntry implements BaseColumns{
        public static final String TABLE_NAME ="votante";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_APELLIDO = "apellido";
        public static final String COLUMN_NAME_TELEFONO = "telefono";
        public static final String COLUMN_NAME_CORREO = "correo";
        public static final String COLUMN_NAME_CEDULA = "cedula";
        public static final String COLUMN_NAME_DIRECCION = "direccion";
        public static final String COLUMN_NAME_LUGARVOTACION = "lugar_votacion";
        public static final String COLUMN_NAME_IDLIDER = "lider_id";


    }

}
