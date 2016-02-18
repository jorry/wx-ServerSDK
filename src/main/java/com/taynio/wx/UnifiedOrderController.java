package com.taynio.wx;

import static org.junit.Assert.assertNotNull;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leonoss.wechat.apppay.WechatAppPayServiceImpl;
import com.leonoss.wechat.apppay.cfg.AppPayConf;
import com.leonoss.wechat.apppay.cfg.HttpConf;
import com.leonoss.wechat.apppay.client.dto.WechatAppPayRequest;
import com.leonoss.wechat.apppay.dto.UnifiedOrder;
import com.leonoss.wechat.apppay.dto.UnifiedOrderResponse;
import com.leonoss.wechat.apppay.util.Util;
import com.taynio.dto.ListBean;

@Controller
@RequestMapping(value = "/rest", method = RequestMethod.POST)
public class UnifiedOrderController {

	String key = "AQSWDEZXCVFRT123456XSDERT789LMNH"; // 商户平台
	WechatAppPayServiceImpl service;
	String device_info = "013467007045764"; // 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	String body = "Ipad mini 16G 白色"; // 商品描述
	String detail = "Ipad mini 16G 白色 二手"; // 商品名称明细列表
	String attach = "说明"; // 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据 非必填项
	String out_trade_no = "1217752501201407033233368018"; // 商户系统内部的订单号,32个字符内、可包含字母,
															// 其他说明见商户订单号
	int total_fee = 1; // 订单总金额，单位为分，详见支付金额
	String spbill_create_ip = "8.8.8.8"; // APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
	String time_start = "20091225091010"; // 订单生成时间
											// 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	String time_expire = "20091227091010"; // 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则
											// 注意：最短失效时间间隔必须大于5分钟
	String goods_tag = "WXG"; // 商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
	String notify_url = "http://yoursite.com"; // 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
	String product_id = "12235413214070356458058"; // trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
	String nounce_str = "nounce";
	String appid = "wx7ddd1310bcc3d59a";
	String mch_id = "1299147201";
	AppPayConf appPayConf;
	HttpConf httpConf;

	public UnifiedOrderController() {

		System.out.println("===============================");

		appPayConf = new AppPayConf();
		appPayConf.appId = appid;
		appPayConf.key = key;
		appPayConf.mchId = mch_id;
		httpConf = new HttpConf();
		httpConf.timeOutInSeconds = 30;
		httpConf.numberOfConnections = 20;
		// service = new WechatAppPayServiceImpl();
		// service.init(appPayConf, httpConf);
	}

	@RequestMapping(value = "/wx", method = RequestMethod.POST)
	public WechatAppPayRequest getAllTest() throws Exception {
		service = new WechatAppPayServiceImpl();
		service.init(appPayConf, httpConf);
		UnifiedOrder order = new UnifiedOrder();
		order.setAppid(appid).setMch_id(mch_id).setAttach(attach).setBody(body).setDetail(detail)
				.setDevice_info(device_info).setGoods_tag(goods_tag).setLimit_pay("no_credit").setNonce_str(nounce_str);
		order.setNotify_url(notify_url);
		order.setOut_trade_no(out_trade_no);
		order.setProduct_id(product_id);
		order.setSpbill_create_ip(spbill_create_ip);
		// order.setTime_startByDate(Util.fromWeixinDateFormat(time_start));
		// order.setTime_expireByDate(Util.fromWeixinDateFormat(time_expire));
		order.setTotal_fee(total_fee);
		System.out.println(order.toString());

		try {
			UnifiedOrderResponse response = service.unifiedOrder(order);

			WechatAppPayRequest request = service.createPayRequestForClient(response.getPrepay_id());
			System.out.println(request);
			return request;
		} finally {
			System.out.println("4444444444444");
			service.close();
			service.close();
		}
	}
}
