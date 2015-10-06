package com.example.enriqueangulomendoza.ipadel;

import android.provider.BaseColumns;

/**
 * Created by enriqueangulomendoza on 4/10/15.
 */
public class DataBaseScript {

//Metainformacíon de la base de batos

    //Tipos de dato
    public static final String STRING_TYPE = "text";
    public static final String LONG_TYPE = "long";
    public static final String INT_TYPE = "integer";
    public static final String DOUBLE_TYPE = "double";

    //Nombres de las tablas
    public static final String CLUBS_TABLE_NAME = "CLUBS";
    public static final String COMUNIDADES_TABLE_NAME = "COMUNIDADES";
    public static final String CONTACTOS_TABLE_NAME = "CONTACTOS";
    public static final String POBLACIONES_TABLE_NAME = "POBLACIONES";
    public static final String PAISES_TABLE_NAME = "PAISES";
    public static final String PISTAS_TABLE_NAME = "PISTAS";
    public static final String PROVINCIAS_TABLE_NAME = "PROVINCIAS";
    public static final String TIENDAS_TABLE_NAME = "TIENDAS";

    public static final String CLUBS_AUX_TABLE_NAME = "CLUBS_AUX";


    //Campos de las tablas

    //Campos de la tabla PAISES
    public static class ColumnPaises {
        public static final String ID_PAIS = BaseColumns._ID;
        //public static final String ID_PAIS = "id_pais";
        public static final String NOM_PAIS = "nom_pais";
    }

    //Campos de la tabla COMUNIDADES
    public static class ColumnComunidades {
        public static final String ID_COMUNIDAD = BaseColumns._ID;
        public static final String ID_PAIS_COMUNIDAD = "id_pais";
        public static final String NOM_COMUNIDAD = "nom_comunidad";
    }

    //Campos de la tabla PROVINCIAS
    public static class ColumnProvincias {
        public static final String ID_PROVINCIA = BaseColumns._ID;
        public static final String ID_PAIS_PROVINCIA = "id_pais";
        public static final String ID_COMUNIDAD_PROVINCIA = "id_comunidad";
        public static final String NOM_PROVINCIA = "nom_provincia";
    }

    //Campos de la tabla POBLACIONES
    public static class ColumnPoblaciones {
        public static final String ID_POBLACION = BaseColumns._ID;
        public static final String ID_PAIS_POBLACION = "id_pais";
        public static final String ID_PROVINCIA_POBLACION = "id_provincia";
        public static final String NOM_POBLACION = "nom_poblacion";
    }

    //Campos de la tabla CONTACTOS
    public static class ColumnContactos {
        public static final String ID_CONTACTO = BaseColumns._ID;
        public static final String ID_CLUB = "id_club";
        public static final String CARGO_CONTACTO = "car_contacto";
        public static final String NOM_CONTACTO = "nom_contacto";
        public static final String EMAIL_CONTACTO = "email_contacto";
        public static final String MOVIL_CONTACTO = "movil_contacto";
    }

    //Campos de la tabla CLUBS
    public static class ColumnClubs {
        public static final String ID_CLUB = BaseColumns._ID;
        //public static final String ID_CLUB = "id_club";
        public static final String NOM_CLUB = "nom_club";
        public static final String DIR_CLUB = "dir_club";
        public static final String CP_CLUB = "cp_club";
        public static final String ID_POBLACION_CLUB = "pob_club";
        public static final String ID_PROVINCIA_CLUB = "prov_club";
        public static final String ID_PAIS_CLUB = "pais_club";
        public static final String TELEFONO_CLUB = "tel_club";
        public static final String WEB_CLUB = "web_club";
        public static final String FACEBOOK_CLUB = "facebook_club";
        public static final String TWITTER_CLUB = "twitter_club";
        public static final String PISTAS_CLUB = "pistas_club";
        public static final String OBSERVACIONES_CLUB = "obs_club";
        public static final String EMAIL_CLUB = "email_club";
        public static final String LOGOTIPO_CLUB = "logo_club";
        public static final String VER_LOGOTIPO_CLUB = "ver_logo_club";
        public static final String ESTRELLAS_CLUB = "estrellas_club";
        public static final String LATITUD_CLUB = "lat_gps_club";
        public static final String LONGITUD_CLUB = "long_gps_club";
    }

    //Campos de la tabla CLUBS_AUX
    public static class ColumnClubsAux {
        public static final String ID_GENRE = BaseColumns._ID;
        public static final String ID_CLUB = "id_club";
        public static final String NOM_CLUB = "nom_club";
        public static final String DIR_CLUB = "dir_club";
        public static final String CP_CLUB = "cp_club";
        public static final String ID_POBLACION_CLUB = "id_pob_club";
        public static final String POBLACION_CLUB = "pob_club";
        public static final String ID_PAIS_CLUB = "id_pais_club";
        public static final String PAIS_CLUB = "pais_club";
        public static final String LATITUD_CLUB = "lat_gps_club";
        public static final String LONGITUD_CLUB = "long_gps_club";
        public static final String LATITUD_2_CLUB = "lat2_gps_club";
        public static final String LONGITUD_2_CLUB = "long2_gps_club";
    }

    //Campos de la tabla PISTAS
    public static class ColumnPistas {
        public static final String ID_PISTA = BaseColumns._ID;
        public static final String ID_CLUB = "id_club_pista";
        public static final String MURO_EXT = "muro_ext_pista";
        public static final String MURO_IND = "muro_ind_pista";
        public static final String CRISTAL_EXT = "cristal_ext_pista";
        public static final String CRISTAL_IND = "cristal_ind_pista";
        public static final String MIXTA = "mixta_pista";
        public static final String PISTA_CENTRAL = "central_pista";
        public static final String IND_EXT = "ind_ext_pista";
        public static final String IND_IND = "ind_ind_pista";
        public static final String TOTAL_PISTAS = "num_total_pista";
    }

    //Script creación de las tablas

    //Script tabla PAISES
    public static final String CREATE_PAISES_SCRIPT =
            "CREATE TABLE " + PAISES_TABLE_NAME + " (" +
                    ColumnPaises.ID_PAIS + " " + INT_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    ColumnPaises.NOM_PAIS + " " + STRING_TYPE + " NOT NULL" + ")";

    //Script tabla COMUNIDADES
    public static final String CREATE_COMUNIDADES_SCRIPT =
            "CREATE TABLE " + COMUNIDADES_TABLE_NAME + " (" +
                    ColumnComunidades.ID_COMUNIDAD + " " + INT_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    ColumnComunidades.ID_PAIS_COMUNIDAD + " " + INT_TYPE + " NOT NULL," +
                    ColumnComunidades.NOM_COMUNIDAD + " " + STRING_TYPE + " NOT NULL" + ")";

    //Script tabla PROVINCIAS
    public static final String CREATE_PROVINCIAS_SCRIPT =
            "CREATE TABLE " + PROVINCIAS_TABLE_NAME + " (" +
                    ColumnProvincias.ID_PROVINCIA + " " + INT_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    ColumnProvincias.ID_PAIS_PROVINCIA + " " + INT_TYPE + " NOT NULL," +
                    ColumnProvincias.ID_COMUNIDAD_PROVINCIA + " " + INT_TYPE + " NOT NULL," +
                    ColumnProvincias.NOM_PROVINCIA + " " + STRING_TYPE + " NOT NULL" + ")";


    //Script tabla POBLACIONES
    public static final String CREATE_POBLACIONES_SCRIPT =
            "CREATE TABLE " + POBLACIONES_TABLE_NAME + " (" +
                    ColumnPoblaciones.ID_POBLACION + " " + INT_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    ColumnPoblaciones.ID_PAIS_POBLACION + " " + INT_TYPE + " NOT NULL," +
                    ColumnPoblaciones.ID_PROVINCIA_POBLACION + " " + INT_TYPE + " NOT NULL," +
                    ColumnPoblaciones.NOM_POBLACION + " " + STRING_TYPE + " NOT NULL" + ")";

    //Script tabla CONTACTOS
    public static final String CREATE_CONTACTOS_SCRIPT =
            "CREATE TABLE " + CONTACTOS_TABLE_NAME + " (" +
                    ColumnContactos.ID_CONTACTO + " " + INT_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    ColumnContactos.ID_CLUB + " " + INT_TYPE + " NOT NULL," +
                    ColumnContactos.CARGO_CONTACTO + " " + STRING_TYPE + " NOT NULL," +
                    ColumnContactos.NOM_CONTACTO + " " + STRING_TYPE + " NOT NULL," +
                    ColumnContactos.EMAIL_CONTACTO + " " + STRING_TYPE + " NOT NULL," +
                    ColumnContactos.MOVIL_CONTACTO + " " + STRING_TYPE + " NOT NULL" + ")";

    //Script tabla CLUBS
    public static final String CREATE_CLUBS_SCRIPT =
            "CREATE TABLE " + CLUBS_TABLE_NAME + " (" +
                    ColumnClubs.ID_CLUB + " " + INT_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    ColumnClubs.NOM_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubs.DIR_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubs.CP_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubs.ID_POBLACION_CLUB + " " + INT_TYPE + " NOT NULL," +
                    ColumnClubs.ID_PROVINCIA_CLUB + " " + INT_TYPE + " NOT NULL," +
                    ColumnClubs.ID_PAIS_CLUB + " " + INT_TYPE + " NOT NULL," +
                    ColumnClubs.TELEFONO_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubs.WEB_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubs.FACEBOOK_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubs.TWITTER_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubs.PISTAS_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubs.OBSERVACIONES_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubs.EMAIL_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubs.LOGOTIPO_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubs.VER_LOGOTIPO_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubs.ESTRELLAS_CLUB + " " + INT_TYPE + " NOT NULL," +
                    ColumnClubs.LATITUD_CLUB + " " + DOUBLE_TYPE + " NOT NULL," +
                    ColumnClubs.LONGITUD_CLUB + " " + DOUBLE_TYPE + " NOT NULL" + ")";

    //Script tabla CLUBS_AUX
    public static final String CREATE_CLUBS_AUX_SCRIPT =
            "CREATE TABLE " + CLUBS_AUX_TABLE_NAME + " (" +
                    ColumnClubsAux.ID_GENRE + " " + INT_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    ColumnClubsAux.ID_CLUB + " " + INT_TYPE + " NOT NULL," +
                    ColumnClubsAux.NOM_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubsAux.DIR_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubsAux.CP_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubsAux.ID_POBLACION_CLUB + " " + INT_TYPE + " NOT NULL," +
                    ColumnClubsAux.POBLACION_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubsAux.ID_PAIS_CLUB + " " + INT_TYPE + " NOT NULL," +
                    ColumnClubsAux.PAIS_CLUB + " " + STRING_TYPE + " NOT NULL," +
                    ColumnClubsAux.LATITUD_CLUB + " " + DOUBLE_TYPE + " NOT NULL," +
                    ColumnClubsAux.LONGITUD_CLUB + " " + DOUBLE_TYPE + " NOT NULL," +
                    ColumnClubsAux.LATITUD_2_CLUB + " " + DOUBLE_TYPE + " NOT NULL," +
                    ColumnClubsAux.LONGITUD_2_CLUB + " " + DOUBLE_TYPE + " NOT NULL" + ")";

    //Script tabla PISTAS
    public static final String CREATE_PISTAS_SCRIPT =
            "CREATE TABLE " + PISTAS_TABLE_NAME + " (" +
                    ColumnPistas.ID_PISTA + " " + INT_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    ColumnPistas.ID_CLUB + " " + INT_TYPE + " NOT NULL," +
                    ColumnPistas.MURO_EXT + " " + INT_TYPE + " NOT NULL," +
                    ColumnPistas.MURO_IND + " " + INT_TYPE + " NOT NULL," +
                    ColumnPistas.CRISTAL_EXT + " " + INT_TYPE + " NOT NULL," +
                    ColumnPistas.CRISTAL_IND + " " + INT_TYPE + " NOT NULL," +
                    ColumnPistas.MIXTA + " " + INT_TYPE + " NOT NULL," +
                    ColumnPistas.PISTA_CENTRAL + " " + INT_TYPE + " NOT NULL," +
                    ColumnPistas.IND_EXT + " " + INT_TYPE + " NOT NULL," +
                    ColumnPistas.IND_IND + " " + INT_TYPE + " NOT NULL," +
                    ColumnPistas.TOTAL_PISTAS + " " + INT_TYPE + " NOT NULL" + ")";
}