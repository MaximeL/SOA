package fr.unice.polytech.soa1.tamtamers.rest.entity;

public class User {
    private int id;
    private String fullname;
    private String address1;
    private String address2;
    private String state;
    private String zc;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String minToString() {
        return "{" +
                "\"id\":" + id +
                ", \"fullname\":" + fullname +
            "}";

    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"fullname\":\"" + fullname + "\"" +
                ", \"address 1\":\"" + address1 + "\"" +
                ", \"address 2\":\"" + address2 + "\"" +
                ", \"state\":\"" + state + "\"" +
                ", \"zip code\":\"" + zc + "\"" +
                ", \"phone\":\"" + phone + "\"" +
            "}";
    }
}
