package com.example.enriqueangulomendoza.ipadel;

/**
 * Created by enriqueangulomendoza on 5/10/15.
 */
public class Contacto {

    private String idContacto;
    private String idClub;
    private String cargo;
    private String nombre;
    private String email;
    private String movil;

    public void setIdContacto(String idContacto) {
        this.idContacto = idContacto.trim();
    }

    public void setIdClub(String idClub) {
        this.idClub = idClub.trim();
    }

    public void setCargo(String cargo) {
        this.cargo = cargo.trim();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.trim();
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public void setMovil(String movil) {
        this.movil = movil.trim();
    }

    public String getIdContacto() {
        return idContacto.trim();
    }

    public String getIdClub() {
        return idClub;
    }

    public String getCargo() {
        return cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getMovil() {
        return movil;
    }
}
