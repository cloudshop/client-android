package com.grjf365.gongrongpoints.bean;

/**
 * Created by vip on 2018/4/10.
 */

public class PayWXResultBean {
    /*
    <xml>
" +
                            "   <appid>wxf177c6755716fa32</appid>\n" +
                            "   <nonce_str>GUBXaWMNcmio22v6</nonce_str>\n" +
                            "   <package>Sign=WXPay</package>\n" +
                            "   <partnerid>1500998061</partnerid>\n" +
                            "   <prepayid>wx141619251008134bfcca995d1251431738</prepayid>\n" +
                            "   <timestamp>1526286100364</timestamp>\n" +
                            "   <sign>73CEB533C7096FF947CDF036F5C20122</sign>\n" +
                            "</xml>
     */
    public String resultCode;
    public String appid;
    public String partnerid;
    public String packageValue;
    public String noncestr;
    public String timestamp;
    public String prepayid;
    public String sign;
}
