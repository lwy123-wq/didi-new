package sun.com.didi.entity;

public class Report {
    private String company;
    private String user;
    private String time;
    public Report(){}
    public Report(String company, String user, String time) {
        this.company = company;
        this.user = user;
        this.time = time;
    }
    public Report( String time) {
        this.time = time;
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
