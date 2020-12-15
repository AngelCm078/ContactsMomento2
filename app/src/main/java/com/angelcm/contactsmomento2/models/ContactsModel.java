package com.angelcm.contactsmomento2.models;


import java.io.Serializable;

public class ContactsModel implements Serializable {

    private int _id;
    private String nombre;
    private String apellido;
    private String celular;
    private String fijo;
    private String correo;
    private String empresa;
    private String idFireBase;

    public ContactsModel(String nombre, String apellido, String celular, String idFireBase) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.idFireBase = idFireBase;
    }

    public ContactsModel() {
    }

    public ContactsModel(String nombre, String apellido, String celular, String fijo, String correo, String empresa) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.fijo = fijo;
        this.correo = correo;
        this.empresa = empresa;
    }

    public ContactsModel(int _id, String nombre, String apellido, String celular, String fijo, String correo, String empresa) {
        this._id = _id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.fijo = fijo;
        this.correo = correo;
        this.empresa = empresa;
    }

    @Override
    public String toString(){
        return "ContactsModel{" +
                "_id=" + idFireBase +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", celular='" + celular + '\'' +
                ", fijo='" + fijo + '\'' +
                ", correo='" + correo + '\'' +
                ", empresa='" + empresa + '\'' +
                '}';
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFijo() {
        return fijo;
    }

    public void setFijo(String fijo) {
        this.fijo = fijo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getIdFireBase() {
        return idFireBase;
    }

    public void setIdFireBase(String idFireBase) {
        this.idFireBase = idFireBase;
    }
}
