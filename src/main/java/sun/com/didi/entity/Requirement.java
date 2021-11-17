package sun.com.didi.entity;

public class Requirement {
    private String company;
    private String OccupationalCategory;
    private String  RemainingPositions;
    private String SalaryRequirements;
    private String Number;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOccupationalCategory() {
        return OccupationalCategory;
    }

    public void setOccupationalCategory(String occupationalCategory) {
        OccupationalCategory = occupationalCategory;
    }

    public String getRemainingPositions() {
        return RemainingPositions;
    }

    public void setRemainingPositions(String remainingPositions) {
        RemainingPositions = remainingPositions;
    }

    public String getSalaryRequirements() {
        return SalaryRequirements;
    }

    public void setSalaryRequirements(String salaryRequirements) {
        SalaryRequirements = salaryRequirements;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
