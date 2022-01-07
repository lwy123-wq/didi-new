package sun.com.didi.entity;

public class Detailed {
    private int id;
    private String company;
    private String Information;
    public Detailed(){

    }
    public Detailed(String company, String information) {
        this.company = company;
        Information = information;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }


}
