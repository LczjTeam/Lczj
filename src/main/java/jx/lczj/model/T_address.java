package jx.lczj.model;

public class T_address {
    private String address;
    private String customer;
    private String consignee;
    private String phone;
    private String street;
    private String provincename;
    private String countyname;
    private String cityname;
    private String isdefault;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public String getCountyname() {
        return countyname;
    }

    public void setCountyname(String countyname) {
        this.countyname = countyname;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    @Override
    public String toString() {
        return "T_address{" +
                "address='" + address + '\'' +
                ", customer='" + customer + '\'' +
                ", consignee='" + consignee + '\'' +
                ", phone='" + phone + '\'' +
                ", street='" + street + '\'' +
                ", provincename='" + provincename + '\'' +
                ", countyname='" + countyname + '\'' +
                ", cityname='" + cityname + '\'' +
                ", isdefault=" + isdefault +
                '}';
    }
}
