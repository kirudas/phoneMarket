package info.androidhive.recyclerview;

/**
 * Created by Lincoln on 15/01/16.
 */
public class Mobile {
    private String nom, marca, model, any, pantalla, hdd, ram, camara, So, preu, imatge;

    public String getPantalla() {
        return pantalla;
    }

    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCamara() {
        return camara;
    }

    public void setCamara(String camara) {
        this.camara = camara;
    }

    public String getSo() {
        return So;
    }

    public void setSo(String so) {
        So = so;
    }

    public String getPreu() {
        return preu;
    }

    public void setPreu(String preu) {
        this.preu = preu;
    }

    public String getImatge() {
        return imatge;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }

    public Mobile(String nom, String marca, String model, String any, String pantalla, String hdd, String ram, String camara, String so, String preu, String imatge) {
        this.nom = nom;
        this.marca = marca;
        this.model = model;
        this.any = any;
        this.pantalla = pantalla;
        this.hdd = hdd;
        this.ram = ram;
        this.camara = camara;
        So = so;
        this.preu = preu;
        this.imatge = imatge;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAny() {
        return any;
    }

    public void setAny(String any) {
        this.any = any;
    }
}
