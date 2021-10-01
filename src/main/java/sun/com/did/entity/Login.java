package sun.com.did.entity;

public class Login {
    private int id;
    private String name;
    private String passwd;
    private String email;
    private int code; //验证码

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Login(String name, String passwd, String email, int code) {
        this.name = name;
        this.passwd = passwd;
        this.email = email;
        this.code = code;
    }

    public Login() {
    }
}
