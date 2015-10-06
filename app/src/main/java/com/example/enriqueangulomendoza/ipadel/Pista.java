package com.example.enriqueangulomendoza.ipadel;

/**
 * Created by enriqueangulomendoza on 5/10/15.
 */
public class Pista {
    private String idClub;
    private String muro_ext;
    private String muro_ind;
    private String cristal_ext;
    private String cristal_ind;
    private String mixta;
    private String pista_central;
    private String individual_ext;
    private String individual_ind;
    private String total;

    public String getIdClub() {
        return idClub.trim();
    }

    public String getMuro_ext() {
        return muro_ext.trim();
    }

    public String getMuro_ind() {
        return muro_ind.trim();
    }

    public String getCristal_ext() {
        return cristal_ext.trim();
    }

    public String getCristal_ind() {
        return cristal_ind.trim();
    }

    public String getMixta() {
        return mixta.trim();
    }

    public String getPista_central() {
        return pista_central.trim();
    }

    public String getIndividual_ext() {
        return individual_ext.trim();
    }

    public String getIndividual_ind() {
        return individual_ind.trim();
    }

    public String getTotal() {
        return total.trim();
    }

    public void setIdClub(String idClub) {
        this.idClub = idClub;
    }

    public void setMuro_ext(String muro_ext) {
        this.muro_ext = muro_ext;
    }

    public void setMuro_ind(String muro_ind) {
        this.muro_ind = muro_ind;
    }

    public void setCristal_ext(String cristal_ext) {
        this.cristal_ext = cristal_ext;
    }

    public void setCristal_ind(String cristal_ind) {
        this.cristal_ind = cristal_ind;
    }

    public void setMixta(String mixta) {
        this.mixta = mixta;
    }

    public void setPista_central(String pista_central) {
        this.pista_central = pista_central;
    }

    public void setIndividual_ext(String individual_ext) {
        this.individual_ext = individual_ext;
    }

    public void setIndividual_ind(String individual_ind) {
        this.individual_ind = individual_ind;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
