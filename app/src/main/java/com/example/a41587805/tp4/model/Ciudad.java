package com.example.a41587805.tp4.model;

import java.io.Serializable;

/**
 * Created by 41587805 on 9/8/2016.
 */
public class Ciudad implements Serializable {
    String nombre;
    int poblacion;
    String clase;
    String pais;
    double latitud;
    double longitud;

    public Ciudad(String nombre, int poblacion, String clase, String pais, double latitud, double longitud) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.clase = clase;
        this.pais = pais;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

}
