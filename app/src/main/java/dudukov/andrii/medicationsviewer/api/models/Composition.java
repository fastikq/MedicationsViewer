package dudukov.andrii.medicationsviewer.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Composition {

    private int id;

    private String description;

    private List<String> atc;

    private Ingredient inn;

    @SerializedName("pharm_form")
    private PharmForm pharmForm;

    private double dosage;

    private Measure measure;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescroption() {
        return description;
    }

    public void setDescroption(String descroption) {
        this.description = descroption;
    }

    public List<String> getAtc() {
        return atc;
    }

    public void setAtc(List<String> atc) {
        this.atc = atc;
    }

    public Ingredient getInn() {
        return inn;
    }

    public void setInn(Ingredient inn) {
        this.inn = inn;
    }

    public PharmForm getPharmForm() {
        return pharmForm;
    }

    public void setPharmForm(PharmForm pharmForm) {
        this.pharmForm = pharmForm;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }
}
