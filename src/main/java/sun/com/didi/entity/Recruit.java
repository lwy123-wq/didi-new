package sun.com.didi.entity;

public class Recruit {
    private int id;
    private String Rec_company;
    private String Rec_logo;
    private String Rec_address;
    private String Rec_job;
    private String Rec_category;
    private String Rec_salary;
    private String Rec_Duration;
    private String Rec_experience;
    private String UTCTime;

    public Recruit(String rec_company, String rec_logo, String rec_address, String rec_job, String rec_category, String rec_salary, String rec_Duration,String UTCTime, String rec_experience) {
        Rec_company = rec_company;
        Rec_logo = rec_logo;
        Rec_address = rec_address;
        Rec_job = rec_job;
        Rec_category = rec_category;
        Rec_salary = rec_salary;
        Rec_Duration = rec_Duration;
        Rec_experience = rec_experience;
        this.UTCTime = UTCTime;
    }

    public Recruit() {

    }

    public Recruit(String utcTime) {
        UTCTime=utcTime;
    }

    public String getUTCTime() {
        return UTCTime;
    }

    public void setUTCTime(String UTCTime) {
        this.UTCTime = UTCTime;
    }
    public void setRec_address(String rec_address) {
        Rec_address = rec_address;
    }

    public String getRec_address() {
        return Rec_address;
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

    public String getRec_job() {
        return Rec_job;
    }

    public String setRec_job(String rec_job) {
        Rec_job = rec_job;
        return rec_job;
    }

    public String getRec_category() {
        return Rec_category;
    }

    public void setRec_category(String rec_category) {
        Rec_category = rec_category;
    }

    public String getRec_salary() {
        return Rec_salary;
    }

    public void setRec_salary(String rec_salary) {
        Rec_salary = rec_salary;
    }

    public String getRec_Duration() {
        return Rec_Duration;
    }

    public void setRec_Duration(String rec_Duration) {
        Rec_Duration = rec_Duration;
    }

    public String getRec_experience() {
        return Rec_experience;
    }

    public void setRec_experience(String rec_experience) {
        Rec_experience = rec_experience;
    }
}
