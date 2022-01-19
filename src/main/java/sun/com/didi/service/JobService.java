package sun.com.didi.service;

public interface JobService {
    /**
     * 新增一个用户
     *
     * @return
     */
    int create(String name, String phone, String id_code, String card, String school, String email, String marriage, String address,String city, String education);

    int insertReport(String company,String username,String time);
}
