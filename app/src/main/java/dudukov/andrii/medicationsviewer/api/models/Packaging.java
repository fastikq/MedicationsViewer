package dudukov.andrii.medicationsviewer.api.models;

import com.google.gson.annotations.SerializedName;

public class Packaging {

    private int id;

    private Composition composition;

    private String description;

    @SerializedName("in_bulk")
    private boolean inBulk;

    @SerializedName("minimal_quantity")
    private String minimalQuantity;

    @SerializedName("package_quantity")
    private String packageQuantity;

    private Variant variant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Composition getComposition() {
        return composition;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInBulk() {
        return inBulk;
    }

    public void setInBulk(boolean inBulk) {
        this.inBulk = inBulk;
    }

    public String getMinimalQuantity() {
        return minimalQuantity;
    }

    public void setMinimalQuantity(String minimalQuantity) {
        this.minimalQuantity = minimalQuantity;
    }

    public String getPackageQuantity() {
        return packageQuantity;
    }

    public void setPackageQuantity(String packageQuantity) {
        this.packageQuantity = packageQuantity;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }
}
