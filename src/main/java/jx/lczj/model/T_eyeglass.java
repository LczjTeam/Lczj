package jx.lczj.model;

public class T_eyeglass {

    private String eyeglass;
    private String name;
    private float refraction;
    private int price;
    private int efficacy;
    private int brand;
    private int mask;
    private int style;
    private String detailphoto;

    public String getDetailphoto() {
        return detailphoto;
    }

    public void setDetailphoto(String detailphoto) {
        this.detailphoto = detailphoto;
    }

    public String getEyeglass() {
        return eyeglass;
    }

    public void setEyeglass(String eyeglass) {
        this.eyeglass = eyeglass;
    }

    public float getRefraction() {
        return refraction;
    }

    public void setRefraction(float refraction) {
        this.refraction = refraction;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(int efficacy) {
        this.efficacy = efficacy;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public int getMask() {
        return mask;
    }

    public void setMask(int mask) {
        this.mask = mask;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "T_eyeglass{" +
                "eyeglass='" + eyeglass + '\'' +
                ", name='" + name + '\'' +
                ", refraction=" + refraction +
                ", price=" + price +
                ", efficacy=" + efficacy +
                ", brand=" + brand +
                ", mask=" + mask +
                ", style=" + style +
                ", detailphoto='" + detailphoto + '\'' +
                '}';
    }
}
