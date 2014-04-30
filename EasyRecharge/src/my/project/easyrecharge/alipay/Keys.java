/*
 * Copyright (C) 2010 The MobileSecurePay Project
 * All right reserved.
 * author: shiqun.shi@alipay.com
 * 
 *  提示：如何获取安全校验码和合作身份者id
 *  1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *  2.点击“商家服务”(https://b.alipay.com/order/myorder.htm)
 *  3.点击“查询合作者身份(pid)”、“查询安全校验码(key)”
 */

package my.project.easyrecharge.alipay;

//
// 请参考 Android平台安全支付服务(msp)应用开发接口(4.2 RSA算法签名)部分，并使用压缩包中的openssl RSA密钥生成工具，生成一套RSA公私钥。
// 这里签名时，只需要使用生成的RSA私钥。
// Note: 为安全起见，使用RSA私钥进行签名的操作过程，应该尽量放到商家服务器端去进行。
public final class Keys {

	// 合作身份者id，以2088开头的16位纯数字
	public static final String DEFAULT_PARTNER = "2088411187809669";

	// 收款支付宝账号
	public static final String DEFAULT_SELLER = "2088411187809669";

	// 商户私钥，自助生成
	public static final String PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANQduIkpCQLWBJv1lPVx0wqwlI8X06UMY96nQbzybgHLCG2gP0vVePadKbHtrTrtGSMJ5kfh69GTKYytY+q+GjGnkDLXRgMC+CaLv9JJc0I6FdQRoEjGbCr/RKJ0RqZLC/8Py1sgmIts0BgzqUgHf7payIwxhogc37e3mr9wekoFAgMBAAECgYEAi9iseYGcd0oy78AR3WCWC04wI8dJul9Y2MrdBH2xGW2KuPwVvyGXBsDHZI+WzmrTT86PDIVevGWR1zXZ0kJTcYYD0lGJ/uHT6e86aJk4iMiV86/4rY4h5YYcOfkfnS4PQvx4vwAWizzdBKz3KR9oqwzneaSk6TJDovmv2ynpWmECQQDsI2NRbdKB90AQzWLjbyU8BErjFpWPDGFGjc/IwQYgOoRQPD67TmrDu+7JN0Or3KZqSDDytaBB7xBWFedbbS33AkEA5fUUaJ8SVfLxXQNDP7YVTqdWbh75FsRkwy3LAdNScoGPJ6v0w/WMeN9yBMzL9knDqRqlx21twuFcRRxBAye44wJAKytJA+Ds4/+cgbXahzORfghW3G8Lhmw/+FMCknPfN9Maz0REP5/VWtUnkTd+MpQTBEkQUb6sfoFC3koA0rEB/wJBAJNzdfWr4k74a6nvmKR2tezsQfB0rdXacp3wdBakkV5ZExkmGtm4Tq2G4Y6QX0x6ehqpuplI4rOuW8L43fsO8m8CQEVgJc9Udp8wrxgOULcFdfULm4eIJ+yZtxKQIKehShcTlzZQ5VgY/mJrQhFQLLTO0TFuQcKOlhaquQrS9KtXkkY=";

	public static final String PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

}
