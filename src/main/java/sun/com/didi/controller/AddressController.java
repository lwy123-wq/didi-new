package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.interceptor.AddressedInterceptor;
import sun.com.didi.service.AddressServiceImpl;

@Controller
public class AddressController {
    @Autowired
    private AddressServiceImpl addressService;
    @Autowired
    private AddressedInterceptor addressedInterceptor;
    @PostMapping(value = "/address")
    @ResponseBody
    public String CityByIp(){
        System.out.println("address"+addressedInterceptor.address);
        return addressService.Address(addressedInterceptor.address);

    }
}
