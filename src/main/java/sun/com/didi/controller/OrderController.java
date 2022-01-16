package sun.com.didi.controller;


import com.alipay.api.AlipayApiException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.com.didi.entity.AlipayBean;
import sun.com.didi.service.PayService;

import javax.annotation.Resource;

/* 订单接口 */
@RestController()
@RequestMapping("order")
public class OrderController {

    @Resource
    private PayService payService;//调用支付服务
    /*阿里支付*/
    @PostMapping(value = "alipay")
    public String alipay(String out_trade_no,String subject,String total_amount,String body) throws AlipayApiException {
        return  payService.aliPay(new AlipayBean(body,out_trade_no,new StringBuffer().append(total_amount),subject));

    }
}
