package com.example.enriqueangulomendoza.ipadel;

/**
 * Created by enriqueangulomendoza on 4/10/15.
 */
public class Provincia {

    private String idProvincia;
    private String idPais;
    private String idComunidad;
    private String provincia;

    public String getIdProvincia() {
        return idProvincia.trim();
    }

    public String getIdPais() {
        return idPais.trim();
    }

    public String getIdComunidad() {
        return idComunidad.trim();
    }

    public String getProvincia() {
        return provincia.trim();
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public void setIdComunidad(String idComunidad) {
        this.idComunidad = idComunidad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
