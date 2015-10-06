package com.example.enriqueangulomendoza.ipadel;

/**
 * Created by enriqueangulomendoza on 4/10/15.
 */
public class Comunidad {

    private String idComunidad;
    private String idPais;
    private String comunidad;


    public String getIdComunidad() {
        return idComunidad.trim();
    }

    public String getIdPais() {
        return idPais.trim();
    }

    public String getComunidad() {
        return comunidad.trim();
    }

    public void setIdComunidad(String idComunidad) {
        this.idComunidad = idComunidad;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }
}
