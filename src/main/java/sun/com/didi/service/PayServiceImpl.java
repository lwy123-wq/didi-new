package sun.com.didi.service;

import com.alipay.api.AlipayApiException;
import org.springframework.stereotype.Service;
import sun.com.didi.entity.AlipayBean;
import sun.com.didi.util.AlipayUtil;

/* 支付服务 */
@Service(value = "alipayOrderService")
public class PayServiceImpl implements PayService {
    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return AlipayUtil.connect(alipayBean);
    }
}