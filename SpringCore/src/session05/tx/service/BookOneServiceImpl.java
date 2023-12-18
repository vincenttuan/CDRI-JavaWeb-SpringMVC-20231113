package session05.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import session05.tx.dao.BookDao;

@Service
public class BookOneServiceImpl implements BookOneService {
	
	@Autowired
	private BookDao bookDao;
	
	@Override
	public void buyOne(String username, Integer bookId) {
		// 1. 查詢書本價格
		Integer bookPrice = bookDao.getBookPrice(bookId);
		// 2. 減去庫存
		bookDao.reduceBookStock(bookId, 1); // 減去 1 本
		// 3. 修改餘額
		bookDao.reduceWalletBalance(username, bookPrice);
	}
	
}
