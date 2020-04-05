package fr.asinnappan.splashscreenexample;

public class Portable {

    private String id, marque, os;

    public Portable(){

    }

    public Portable(String id, String marque, String os) {
        this.id = id;
        this.marque = marque;
        this.os = os;
    }

    public String getId() {
        return id;
    }

    public String getMarque() {
        return marque;
    }

    public String getOs() {
        return os;
    }
}


