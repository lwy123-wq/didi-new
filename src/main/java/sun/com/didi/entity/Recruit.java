package sun.com.didi.entity;

public class Recruit {
    private int id;
    private String Rec_company;
    private String Rec_logo;
    private String Rec_category;
    private int Rec_salary;
    private int Rec_Duration;
    private String Rec_experience;

    public Recruit(String Rec_company, String Rec_logo, String Rec_category, int Rec_salary, int Rec_Duration, String Rec_experience){
        this.Rec_company=Rec_company;
        this.Rec_logo=Rec_logo;
        this.Rec_category=Rec_category;
        this.Rec_salary=Rec_salary;
        this.Rec_Duration=Rec_Duration;
        this.Rec_experience=Rec_experience;
    }

    public Recruit() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRec_company() {
        return Rec_company;
    }

    public void setRec_company(String rec_company) {
        Rec_company = rec_company;
    }

    public String getRec_logo() {
        return Rec_logo;
    }

    public void setRec_logo(String rec_logo) {
        Rec_logo = rec_logo;
    }

    public String getRec_category() {
        return Rec_category;
    }

    public void setRec_category(String rec_category) {
        Rec_category = rec_category;
    }

    public int getRec_salary() {
        return Rec_salary;
    }

    public void setRec_salary(int rec_salary) {
        Rec_salary = rec_salary;
    }

    public int getRec_Duration() {
        return Rec_Duration;
    }

    public void setRec_Duration(int rec_Duration) {
        Rec_Duration = rec_Duration;
    }

    public String getRec_experience() {
        return Rec_experience;
    }

    public void setRec_experience(String rec_experience) {
        Rec_experience = rec_experience;
    }
}
