package com.internousdev.ecsite_tyu.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite_tyu.dao.MyPageDAO;
import com.internousdev.ecsite_tyu.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;


public class MyPageAction extends ActionSupport implements SessionAware{

	public Map<String, Object> session;
	private MyPageDAO myPageDAO = new MyPageDAO();
	private ArrayList<MyPageDTO> myPageList = new ArrayList<MyPageDTO>();
	private String deleteFlg;
	private String message;

	public String execute() throws SQLException {
		if(!session.containsKey ("login_user_id")){
			return ERROR;
		}

		if(deleteFlg == null){//履歴の削除がされているかどうか。
			String item_transaction_id = session.get("id").toString();
			String user_master_id = session.get("login_user_id").toString();
			myPageList = myPageDAO.getMyPageUserInfo(item_transaction_id, user_master_id);
			//DBから取得した履歴の情報をmyPageListに格納している。
		} else if(deleteFlg.equals("1")){
			delete();//deleteメソッド（↓）で、履歴の削除をする。
		}

		String result = SUCCESS;
		return result;
	}

	public void delete() throws SQLException {//履歴の削除を行うメソッド。

		String item_transaction_id = session.get("id").toString();
		String user_master_id = session.get("login_user_id").toString();

		int res = myPageDAO.buyItemHistoryDelete(item_transaction_id,user_master_id);
		//resに入るのはDBから削除した履歴の件数。

		if(res > 0){//一件以上削除されたかどうかで、正常に削除が行われたかみている。
			myPageList = null;
			setMessage("商品情報を削除しました。");
		}else if(res == 0){
			setMessage("商品情報の削除に失敗しました。");
		}

	}

	public void setDeleteFlg(String deleteFlg){
		this.deleteFlg = deleteFlg;
	}

	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}

	public ArrayList<MyPageDTO> getMyPageList(){
		return this.myPageList;
	}

	public String getMessage(){
		return this.message;
	}

	public void setMessage(String message){
		this.message = message;
	}

}