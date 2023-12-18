package session05.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import session05.tx.dao.BookDao;

@Service
public class BookOneServiceImpl implements BookOneService {
	
	@Autowired
	private BookDao bookDao;
	
	@Override
	@Transactional(
			propagation = Propagation.REQUIRED // 預設: 若當前有 tx, 則繼續使用, 反之則建立一個 tx
	)
	public void buyOne(String username, Integer bookId) {
		// 1. 查詢書本價格
		Integer bookPrice = bookDao.getBookPrice(bookId);
		// 2. 減去庫存
		bookDao.reduceBookStock(bookId, 1); // 減去 1 本
		// 3. 修改餘額
		bookDao.reduceWalletBalance(username, bookPrice);
	}
	
}
