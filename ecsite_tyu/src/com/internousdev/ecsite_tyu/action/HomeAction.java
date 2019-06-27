package com.internousdev.ecsite_tyu.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite_tyu.dao.BuyItemDAO;
import com.internousdev.ecsite_tyu.dto.BuyItemDTO;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware{
	public Map<String, Object> session;

	public String execute(){
		String result = "login";
		if(session.containsKey ("login_user_id")){//一度ログインしていたら、ログイン画面を飛ばす。
			BuyItemDAO buyItemDAO = new BuyItemDAO();
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();
			session.put("id",buyItemDTO.getId());//DBから取得した商品情報をセッションに格納。
			session.put("buyItem_name",buyItemDTO.getItemName());
			session.put("buyItem_price",buyItemDTO.getItemPrice());

			result = SUCCESS;
		}
		return result;//execute()の戻り値、ログインしていたら「SUCCESS」、していなかったら「"login"」を返す。
	}

	@Override
	public void setSession(Map<String,Object> session){
		this.session = session;
	}
	public Map<String, Object>getSession(){
		return this.session;
	}

}
