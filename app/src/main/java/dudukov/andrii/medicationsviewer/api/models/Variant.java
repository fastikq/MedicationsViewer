package dudukov.andrii.medicationsviewer.api.models;

import com.google.gson.annotations.SerializedName;

public class Variant {

    private int id;

    @SerializedName("pharm_form")
    private PharmForm pharmForm;

    private String name;

    private String shortName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PharmForm getPharmForm() {
        return pharmForm;
    }

    public void setPharmForm(PharmForm pharmForm) {
        this.pharmForm = pharmForm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
