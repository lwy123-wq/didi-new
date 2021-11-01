package sun.com.didi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.com.didi.service.AddressServiceImpl;

@Controller
public class AddressController {
    @Autowired
    private AddressServiceImpl addressService;
    @PostMapping(value = "/address")
    @ResponseBody
    public String CityByIp(String address){
        return addressService.Address(address);
    }
}
