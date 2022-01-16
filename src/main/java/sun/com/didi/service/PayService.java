package sun.com.didi.service;

import com.alipay.api.AlipayApiException;
import sun.com.didi.entity.AlipayBean;

/*支付服务*/
public interface PayService {
    /*支付宝*/
    String aliPay(AlipayBean alipayBean) throws AlipayApiException;
}