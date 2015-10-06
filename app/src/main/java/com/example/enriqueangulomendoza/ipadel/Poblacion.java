package com.example.enriqueangulomendoza.ipadel;

/**
 * Created by enriqueangulomendoza on 4/10/15.
 */
public class Poblacion {

    private String idPoblacion;
    private String idPais;
    private String idProvincia;
    private String poblacion;


    public String getIdPoblacion() {
        return idPoblacion.trim();
    }

    public String getIdPais() {
        return idPais.trim();
    }

    public String getIdProvincia() {
        return idProvincia.trim();
    }

    public String getPoblacion() {
        return poblacion.trim();
    }

    public void setIdPoblacion(String idPoblacion) {
        this.idPoblacion = idPoblacion;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }
}
