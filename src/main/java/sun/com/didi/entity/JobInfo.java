package sun.com.didi.entity;

public class JobInfo {
    private int id;
    private String name;
    private String phone;
    private String id_code;
    private String card;
    private String school;
    private String email;
    private String marriage;
    //省份
    private String address;
    private String city;
    private String education;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId_code() {
        return id_code;
    }

    public void setId_code(String id_code) {
        this.id_code = id_code;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public JobInfo(String name, String phone, String id_code, String card, String school, String email, String marriage, String address, String city, String education) {
        this.name = name;
        this.phone = phone;
        this.id_code = id_code;
        this.card = card;
        this.school = school;
        this.email = email;
        this.marriage = marriage;
        this.address = address;
        this.city = city;
        this.education = education;
    }

    public JobInfo() {
    }
}
