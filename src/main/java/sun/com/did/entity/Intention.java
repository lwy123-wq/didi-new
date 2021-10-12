package sun.com.did.entity;

public class Intention {
    private String port;
    private String Category;
    private String province;
    private String city;
    private  String condition;
    private String Duration;
    private  String experience;

    public Intention(String port, String category, String province, String city, String condition, String duration, String experience) {
        this.port=port;
        this.Category=category;
        this.province=province;
        this.city=city;
        this.condition=condition;
        this.Duration=duration;
        this.experience=experience;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }


}
