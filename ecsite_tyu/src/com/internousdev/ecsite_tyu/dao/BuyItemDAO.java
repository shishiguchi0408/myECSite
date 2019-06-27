package com.internousdev.ecsite_tyu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.ecsite_tyu.dto.BuyItemDTO;
import com.internousdev.ecsite_tyu.util.DBConnector;

public class BuyItemDAO {
	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();
	private BuyItemDTO buyItemDTO = new BuyItemDTO();

	public BuyItemDTO getBuyItemInfo(){
		String sql = "SELECT id,item_name,item_price FROM item_info_transaction";
		//商品情報をすべて取得するSQL文。

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();//SQL文を実行
			if(resultSet.next()){
				buyItemDTO.setId(resultSet.getInt("id"));//DBから取得した情報をDTOクラスに格納。
				buyItemDTO.setItemName(resultSet.getString("item_name"));
				buyItemDTO.setItemPrice(resultSet.getString("item_price"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return buyItemDTO;//actionクラスにDTOクラスを返す。
	}

}
