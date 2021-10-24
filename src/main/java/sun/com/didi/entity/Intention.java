package sun.com.didi.entity;

public class Intention {
    private int id;
    private String post;
    private String city;
    private String category;
    private String province;
    private String condition;
    private String duration;
    private String experience;

    public Intention(String post, String city, String category, String province, String condition, String duration, String experience) {
        this.post = post;
        this.city = city;
        this.category = category;
        this.province = province;
        this.condition = condition;
        this.duration = duration;
        this.experience = experience;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }


}
