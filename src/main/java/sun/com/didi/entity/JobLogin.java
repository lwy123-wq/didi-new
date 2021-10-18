package sun.com.didi.entity;

public class JobLogin {
    private int id;
    private String name;
    private String passwd;
    private int code; //验证码


    public JobLogin( String name, String passwd, int code){
        this.name=name;
        this.passwd=passwd;
        this.code=code;
    }

    public JobLogin() {

    }

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
}
