package session05.tx.dao;

import session05.tx.exception.InsufficientAmount;
import session05.tx.exception.InsufficientStock;

public interface BookDao {
	// 一般服務
	Integer getBookPrice(Integer bookId); // 取得書本價格
	Integer getBookStock(Integer bookId); // 取得書本庫存
	Integer getWalletBalance(String username); // 取得客戶目前餘額
	
	// 交易服務
	Integer reduceBookStock(Integer bookId, Integer amountToReduce) throws InsufficientStock; // 更新書本庫存(減量)
	Integer reduceWalletBalance(String username, Integer bookPrice) throws InsufficientAmount; // 更新錢包餘額(減量)
	
	//Integer incrementBookStock(Integer bookId, Integer amountToReduce); // 更新書本庫存(增量)
	//Integer incrementWalletBalance(String username, Integer bookPrice); // 更新錢包餘額(增量)
	
}
