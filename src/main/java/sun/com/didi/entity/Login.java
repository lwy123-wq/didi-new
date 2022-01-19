package sun.com.didi.entity;

public class Login {
    private int id;
    private String name;
    private String passwd;
    private String email;
    private String code; //验证码


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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Login(String name, String passwd, String email) {
        this.name = name;
        this.passwd = passwd;
        this.email = email;


    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public Login() {
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                ", email='" + email + '\'' +
                ", code=" + code +
                '}';
    }
}
