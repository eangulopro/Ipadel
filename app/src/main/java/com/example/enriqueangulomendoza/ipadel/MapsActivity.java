package com.example.enriqueangulomendoza.ipadel;

import android.os.AsyncTask;
import android.app.ProgressDialog;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.Address;
import android.location.Geocoder;
import java.util.Locale;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    //Variable para el maneo del mapa
    private GoogleMap mapa;

    //Variable manejo base de datos
    private SQLiteDatabase db;

    //Variable para el manejo de los ficheros xml
    private FileInputStream xmlFile;

    //Variable para los datos leidos de los ficheros xml
    private List<Pais> paises;
    private List<Comunidad> comunidades;
    private List<Provincia> provincias;
    private List<Poblacion> poblaciones;
    private List<Contacto> contactos;
    private List<Pista> pistas;
    private List<Club> clubs;

    //Variable de manejo del cuadro de dialogo de la tarea en segundo plano
    private ProgressDialog pDialog;

    //Variable de manejo de tareas en segundo plano
    private LeerDatosXmlTask xmlTask;
    private ObtenerCoordenadasGPSTask GPSTask;

    //Variables auxiliares
    private Cursor regClub;
    private Cursor regClubAux;
    private String aux1;
    private String aux2;
    private String direccion;
    private String[] campos;
    private String[] campos2;
    private String[] args;
    private double latitud;
    private double longitud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        //Borramos el archivo de la base de datos si existe
        String path = getApplicationInfo().dataDir;
        path = path + "/databases/";
        File dbFile = new File (path + DataBaseSQLiteHelper.DATABASE_NAME);
        if (dbFile.exists()){
            //dbFile.delete();
        }

        //Creamos la base de datos
        DataBaseSQLiteHelper usdbh = new DataBaseSQLiteHelper(this);
        db = usdbh.getWritableDatabase();

        //Inicializamos el cuadro de diálogo para la tarea en segundo plano
        pDialog = new ProgressDialog(this);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Inicializando aplicación...");


        //Leemos los ficheros xml e insertamos los datos en la base de datos
        //Con tarea asíncrona
        //xmlTask = new LeerDatosXmlTask();
        //xmlTask.execute();

        //Obtenemos las coordenadas GPS para los clubs a partir de las direcciones
        //GPSTask = new ObtenerCoordenadasGPSTask();
        //GPSTask.execute();


        //Obtenemos el SupportMapFragment y notificamos cuando esté preparado para usar
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        GoogleMap mapa = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        //Colocamos la vista del mapa en tipo Satélite
        mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        //Obtenemos la referencia del mapa
        mapa = googleMap;

        //Creamos las coordenadas de Madrid para centrar el mapa en Madrid
        LatLng madrid = new LatLng(40.417325, -3.683081);

        //Añadimos un marcador en Madrid
        mapa.addMarker(new MarkerOptions().position(madrid).title("Madrid"));

        CameraPosition camPos = new CameraPosition.Builder()
                .target(madrid)
                .zoom (10)
                .bearing(0)
                .tilt(0)
                .build();

        CameraUpdate camUpd = CameraUpdateFactory.newCameraPosition(camPos);

        mapa.animateCamera(camUpd);
        //mapa.setMyLocationEnabled(true);
        //mapa.moveCamera(CameraUpdateFactory.newLatLng(madrid));



    }

    private class LeerDatosXmlTask extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                PaisXmlHandler domParser1 = new PaisXmlHandler();
                xmlFile = MapsActivity.this.openFileInput("paises.xml");
                paises = domParser1.parse(xmlFile);

                ComunidadXmlHandler domParser2 = new ComunidadXmlHandler();
                xmlFile = MapsActivity.this.openFileInput("comunidades.xml");
                comunidades = domParser2.parse(xmlFile);

                ProvinciaXmlHandler domParser3 = new ProvinciaXmlHandler();
                xmlFile = MapsActivity.this.openFileInput("provincias.xml");
                provincias = domParser3.parse(xmlFile);

                PoblacionXmlHandler domParser4 = new PoblacionXmlHandler();
                xmlFile = MapsActivity.this.openFileInput("municipios.xml");
                poblaciones = domParser4.parse(xmlFile);

                ContactoXmlHandler domParser5 = new ContactoXmlHandler();
                xmlFile = MapsActivity.this.openFileInput("Contactos.xml");
                contactos = domParser5.parse(xmlFile);

                PistaXmlHandler domParser6 = new PistaXmlHandler();
                xmlFile = MapsActivity.this.openFileInput("Pistas.xml");
                pistas = domParser6.parse(xmlFile);

                ClubXmlHandler domParser7 = new ClubXmlHandler();
                xmlFile = MapsActivity.this.openFileInput("Clubs.xml");
                clubs = domParser7.parse(xmlFile);


                return true;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                pDialog.dismiss();

                //Procesamos la lista de paises leidos del fichero xml
                // y los insertamos en la tabla de Paises
                for (int i = 0; i < paises.size(); i++) {
                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put(DataBaseScript.ColumnPaises.ID_PAIS, paises.get(i).getIdPais());
                    nuevoRegistro.put(DataBaseScript.ColumnPaises.NOM_PAIS, paises.get(i).getPais());
                    db.insert(DataBaseScript.PAISES_TABLE_NAME, null, nuevoRegistro);
                }

                //Procesamos la lista de comunidades leidos del fichero xml
                // y los insertamos en la tabla de Comunidades
                for (int i = 0; i < comunidades.size(); i++) {
                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put(DataBaseScript.ColumnComunidades.ID_COMUNIDAD, comunidades.get(i).getIdComunidad());
                    nuevoRegistro.put(DataBaseScript.ColumnComunidades.ID_PAIS_COMUNIDAD, comunidades.get(i).getIdPais());
                    nuevoRegistro.put(DataBaseScript.ColumnComunidades.NOM_COMUNIDAD, comunidades.get(i).getComunidad());
                    db.insert(DataBaseScript.COMUNIDADES_TABLE_NAME, null, nuevoRegistro);
                }

                //Procesamos la lista de provincias leidos del fichero xml
                // y los insertamos en la tabla de Provincias
                for (int i = 0; i < provincias.size(); i++) {
                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put(DataBaseScript.ColumnProvincias.ID_PROVINCIA, provincias.get(i).getIdProvincia());
                    nuevoRegistro.put(DataBaseScript.ColumnProvincias.ID_PAIS_PROVINCIA, provincias.get(i).getIdPais());
                    nuevoRegistro.put(DataBaseScript.ColumnProvincias.ID_COMUNIDAD_PROVINCIA, provincias.get(i).getIdComunidad());
                    nuevoRegistro.put(DataBaseScript.ColumnProvincias.NOM_PROVINCIA, provincias.get(i).getProvincia());
                    db.insert(DataBaseScript.PROVINCIAS_TABLE_NAME, null, nuevoRegistro);
                }

                //Procesamos la lista de poblaciones leidos del fichero xml
                // y los insertamos en la tabla de Poblaciones
                for (int i = 0; i < poblaciones.size(); i++) {
                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put(DataBaseScript.ColumnPoblaciones.ID_POBLACION, poblaciones.get(i).getIdPoblacion());
                    nuevoRegistro.put(DataBaseScript.ColumnPoblaciones.ID_PAIS_POBLACION, poblaciones.get(i).getIdPais());
                    nuevoRegistro.put(DataBaseScript.ColumnPoblaciones.ID_PROVINCIA_POBLACION, poblaciones.get(i).getIdProvincia());
                    nuevoRegistro.put(DataBaseScript.ColumnPoblaciones.NOM_POBLACION, poblaciones.get(i).getPoblacion());
                    db.insert(DataBaseScript.POBLACIONES_TABLE_NAME, null, nuevoRegistro);
                }

                //Procesamos la lista de contactos leidos del fichero xml
                // y los insertamos en la tabla de Contactos
                for (int i = 0; i < contactos.size(); i++) {
                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put(DataBaseScript.ColumnContactos.ID_CONTACTO, contactos.get(i).getIdContacto());
                    nuevoRegistro.put(DataBaseScript.ColumnContactos.ID_CLUB, contactos.get(i).getIdClub());
                    nuevoRegistro.put(DataBaseScript.ColumnContactos.CARGO_CONTACTO, contactos.get(i).getCargo());
                    nuevoRegistro.put(DataBaseScript.ColumnContactos.NOM_CONTACTO, contactos.get(i).getNombre());
                    nuevoRegistro.put(DataBaseScript.ColumnContactos.EMAIL_CONTACTO, contactos.get(i).getEmail());
                    nuevoRegistro.put(DataBaseScript.ColumnContactos.MOVIL_CONTACTO, contactos.get(i).getMovil());
                    db.insert(DataBaseScript.CONTACTOS_TABLE_NAME, null, nuevoRegistro);
                }

                //Procesamos la lista de pistas leidos del fichero xml
                // y los insertamos en la tabla de Pistas
                for (int i = 0; i < pistas.size(); i++) {
                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put(DataBaseScript.ColumnPistas.ID_CLUB, pistas.get(i).getIdClub());
                    nuevoRegistro.put(DataBaseScript.ColumnPistas.MURO_EXT, pistas.get(i).getMuro_ext());
                    nuevoRegistro.put(DataBaseScript.ColumnPistas.MURO_IND, pistas.get(i).getMuro_ind());
                    nuevoRegistro.put(DataBaseScript.ColumnPistas.CRISTAL_EXT, pistas.get(i).getCristal_ext());
                    nuevoRegistro.put(DataBaseScript.ColumnPistas.CRISTAL_IND, pistas.get(i).getCristal_ind());
                    nuevoRegistro.put(DataBaseScript.ColumnPistas.MIXTA, pistas.get(i).getMixta());
                    nuevoRegistro.put(DataBaseScript.ColumnPistas.PISTA_CENTRAL, pistas.get(i).getPista_central());
                    nuevoRegistro.put(DataBaseScript.ColumnPistas.IND_EXT, pistas.get(i).getIndividual_ext());
                    nuevoRegistro.put(DataBaseScript.ColumnPistas.IND_IND, pistas.get(i).getIndividual_ind());
                    nuevoRegistro.put(DataBaseScript.ColumnPistas.TOTAL_PISTAS, pistas.get(i).getTotal());
                    db.insert(DataBaseScript.PISTAS_TABLE_NAME, null, nuevoRegistro);
                }

                //Procesamos la lista de clubs leidos del fichero xml
                // y los insertamos en la tabla de CLubs
                for (int i = 0; i < clubs.size(); i++) {
                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.ID_CLUB, clubs.get(i).getIdClub());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.NOM_CLUB, clubs.get(i).getNombre());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.DIR_CLUB, clubs.get(i).getDireccion());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.CP_CLUB, clubs.get(i).getCP());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.ID_POBLACION_CLUB, clubs.get(i).getIdPoblacion());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.ID_PROVINCIA_CLUB, clubs.get(i).getIdProvincia());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.ID_PAIS_CLUB, clubs.get(i).getIdPais());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.TELEFONO_CLUB, clubs.get(i).getTelefono());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.WEB_CLUB, clubs.get(i).getWeb());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.FACEBOOK_CLUB, clubs.get(i).getFacebook());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.TWITTER_CLUB, clubs.get(i).getTwitter());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.PISTAS_CLUB, clubs.get(i).getPistas());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.OBSERVACIONES_CLUB, clubs.get(i).getObservaciones());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.EMAIL_CLUB, clubs.get(i).getEmail());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.LOGOTIPO_CLUB, clubs.get(i).getLogo());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.VER_LOGOTIPO_CLUB, 0);
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.ESTRELLAS_CLUB, clubs.get(i).getEstrellas());
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.LATITUD_CLUB, 0);
                    nuevoRegistro.put(DataBaseScript.ColumnClubs.LONGITUD_CLUB, 0);
                    db.insert(DataBaseScript.CLUBS_TABLE_NAME, null, nuevoRegistro);
                }


                //Procesamos la lista de clubs leidos del fichero xml
                // y los insertamos en la tabla de CLubs_Aux incluyendo las coordenadas GPP
                //Calculadas con direccion + poblacion + pais
                //y con direccion + CP + pais
                for (int i = 0; i < clubs.size(); i++) {
                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put(DataBaseScript.ColumnClubsAux.ID_CLUB, clubs.get(i).getIdClub());
                    nuevoRegistro.put(DataBaseScript.ColumnClubsAux.NOM_CLUB, clubs.get(i).getNombre());
                    nuevoRegistro.put(DataBaseScript.ColumnClubsAux.DIR_CLUB, clubs.get(i).getDireccion());
                    nuevoRegistro.put(DataBaseScript.ColumnClubsAux.CP_CLUB, clubs.get(i).getCP());
                    nuevoRegistro.put(DataBaseScript.ColumnClubsAux.ID_POBLACION_CLUB, clubs.get(i).getIdPoblacion());

                    campos = new String[]{DataBaseScript.ColumnPoblaciones.NOM_POBLACION};
                    args = new String[]{clubs.get(i).getIdPoblacion()};
                    regClub = db.query(DataBaseScript.POBLACIONES_TABLE_NAME,
                            campos,
                            DataBaseScript.ColumnPoblaciones.ID_POBLACION + " = ?",
                            args, null, null, null);

                    aux1 = null;
                    if (regClub.moveToFirst()) {
                        aux1 = regClub.getString(0);
                    }else{
                        aux1 = "";
                    }

                    nuevoRegistro.put(DataBaseScript.ColumnClubsAux.POBLACION_CLUB, aux1);
                    nuevoRegistro.put(DataBaseScript.ColumnClubsAux.ID_PAIS_CLUB, clubs.get(i).getIdPais());

                    campos = new String[]{DataBaseScript.ColumnPaises.NOM_PAIS};
                    args = new String[]{clubs.get(i).getIdPais()};
                    regClub = db.query(DataBaseScript.PAISES_TABLE_NAME,
                            campos,
                            DataBaseScript.ColumnPaises.ID_PAIS + " = ?",
                            args, null, null, null);

                    aux2 = null;
                    if (regClub.moveToFirst()) {
                        aux2 = regClub.getString(0);
                    }else{
                        aux2 = "";
                    }

                    nuevoRegistro.put(DataBaseScript.ColumnClubsAux.PAIS_CLUB, aux2);

                    List <Address> geocodeMatches = null;
                    try {
                        //geocodeMatches = new Geocoder(this).getFromLocation("600 Independence Ave SW, Washington, DC 20560", 1);
                        //geocodeMatches = new Geocoder(this, Locale.getDefault()).getFromLocationName("600 Independence Ave SW, Washington, DC 20560",1);
                        //geocodeMatches = new Geocoder(this, Locale.getDefault()).getFromLocationName("Menendez Pelayo 3,Logroño, Spain",1);
                        direccion = clubs.get(i).getDireccion() + "," + aux1 + "," + aux2;
                        geocodeMatches = new Geocoder(MapsActivity.this, Locale.getDefault()).getFromLocationName(direccion,1);
                    }catch (Exception ex) {
                        // Bloque catch generada automáticamente TODO
                        ex.printStackTrace ();
                    }
                    if (! geocodeMatches.isEmpty())
                    {
                        latitud = geocodeMatches.get(0).getLatitude();
                        longitud = geocodeMatches.get(0).getLongitude();
                    }else{
                        latitud = 0;
                        longitud = 0;
                    }

                    nuevoRegistro.put(DataBaseScript.ColumnClubsAux.LATITUD_CLUB, latitud);
                    nuevoRegistro.put(DataBaseScript.ColumnClubsAux.LONGITUD_CLUB, longitud);

                    try {
                        //geocodeMatches = new Geocoder(this).getFromLocation("600 Independence Ave SW, Washington, DC 20560", 1);
                        //geocodeMatches = new Geocoder(this, Locale.getDefault()).getFromLocationName("600 Independence Ave SW, Washington, DC 20560",1);
                        //geocodeMatches = new Geocoder(this, Locale.getDefault()).getFromLocationName("Menendez Pelayo 3,Logroño, Spain",1);
                        direccion = clubs.get(i).getDireccion() + "," + clubs.get(i).getCP() + "," + aux2;
                        geocodeMatches = new Geocoder(MapsActivity.this, Locale.getDefault()).getFromLocationName(direccion,1);
                    }catch (Exception ex) {
                        // Bloque catch generada automáticamente TODO
                        ex.printStackTrace ();
                    }
                    if (! geocodeMatches.isEmpty())
                    {
                        latitud = geocodeMatches.get(0).getLatitude();
                        longitud = geocodeMatches.get(0).getLongitude();
                    }else{
                        latitud = 0;
                        longitud = 0;
                    }

                    nuevoRegistro.put(DataBaseScript.ColumnClubsAux.LATITUD_2_CLUB, latitud);
                    nuevoRegistro.put(DataBaseScript.ColumnClubsAux.LONGITUD_2_CLUB, longitud);

                    db.insert(DataBaseScript.CLUBS_AUX_TABLE_NAME, null, nuevoRegistro);
                }


            }
        }
    }

    private class ObtenerCoordenadasGPSTask extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                campos = new String[]{DataBaseScript.ColumnClubs.ID_CLUB,
                        DataBaseScript.ColumnClubs.LATITUD_CLUB,
                        DataBaseScript.ColumnClubs.LONGITUD_CLUB};

                regClub = db.query(DataBaseScript.CLUBS_TABLE_NAME,
                        campos,null,null, null, null, null);

                if (regClub.moveToFirst()) {
                    do {

                        aux1 = regClub.getString(0);
                        campos2 = new String[]{DataBaseScript.ColumnClubsAux.ID_CLUB,
                                DataBaseScript.ColumnClubsAux.LATITUD_CLUB,
                                DataBaseScript.ColumnClubsAux.LONGITUD_CLUB};
                        args = new String[]{aux1};

                        regClubAux = db.query(DataBaseScript.CLUBS_AUX_TABLE_NAME,
                                campos2,
                                DataBaseScript.ColumnClubsAux.ID_CLUB + " = ?",
                                args, null, null, null);

                        if (regClubAux.moveToFirst()) {

                            ContentValues nuevoRegistroClub = new ContentValues();
                            nuevoRegistroClub.put(DataBaseScript.ColumnClubs.LATITUD_CLUB,regClubAux.getString(1));
                            nuevoRegistroClub.put(DataBaseScript.ColumnClubs.LONGITUD_CLUB, regClubAux.getString(2));
                            db.update(DataBaseScript.CLUBS_TABLE_NAME,
                                    nuevoRegistroClub,
                                    DataBaseScript.ColumnClubs.ID_CLUB + " = " + aux1,
                                    null);
                        }
                    } while (regClub.moveToNext());

                }


                return true;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                pDialog.dismiss();


            }
        }
    }

    @Override
    protected void onDestroy(){

        if (db.isOpen()) db.close();
        super.onDestroy();
    }

}

