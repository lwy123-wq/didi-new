package sun.com.didi.entity;

import lombok.Data;
import lombok.experimental.Accessors;


@Accessors(chain = true)
public class AlipayBean {
    private String out_trade_no;
    /*订单名称，必填*/
    private String subject;
    /*付款金额，必填*/
    private StringBuffer total_amount;
    /*商品描述，可空*/
    private String body;
    /*超时时间参数*/
    private String timeout_express="10m";
    private String product_code="FAST_INSTANT_TRADE_PAY";

    public AlipayBean(String body, String out_trade_no, StringBuffer total_amount, String subject) {
        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_amount = total_amount;
        this.subject = subject;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public AlipayBean() {
    }

    public AlipayBean(String out_trade_no, String subject, StringBuffer total_amount, String body, String timeout_express, String product_code) {
        this.out_trade_no = out_trade_no;
        this.subject = subject;
        this.total_amount = total_amount;
        this.body = body;
        this.timeout_express = timeout_express;
        this.product_code = product_code;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public StringBuffer getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(StringBuffer total_amount) {
        this.total_amount = total_amount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }
}
