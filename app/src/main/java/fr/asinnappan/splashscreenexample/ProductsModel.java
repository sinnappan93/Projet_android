package fr.asinnappan.splashscreenexample;

public class ProductsModel {
    private String etat ;
    private String id ;
    private String marque ;

    private ProductsModel(){}

    private ProductsModel(String etat, String id, String marque){
        this.etat = etat;
        this.id = id;
        this.marque = marque;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
}
