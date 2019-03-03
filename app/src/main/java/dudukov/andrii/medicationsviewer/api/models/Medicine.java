package dudukov.andrii.medicationsviewer.api.models;

import com.google.gson.annotations.SerializedName;

public class Medicine {

    private int id;

    private Composition composition;

    private Packaging packaging;

    @SerializedName("trade_label")
    private TradeLabel tradeLabel;

    private Manufacturer manufacturer;

    private String code;

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

    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }

    public TradeLabel getTradeLabel() {
        return tradeLabel;
    }

    public void setTradeLabel(TradeLabel tradeLabel) {
        this.tradeLabel = tradeLabel;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
