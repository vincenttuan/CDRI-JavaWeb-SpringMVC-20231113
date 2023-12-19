package session05.tx.service;

import session05.tx.exception.InsufficientAmount;
import session05.tx.exception.InsufficientStock;

public interface BookOneService {
	void buyOne(String username, Integer bookId) throws InsufficientAmount, InsufficientStock; // 購書服務
	//void refundOne(String username, Integer bookId); // 退書服務
}
