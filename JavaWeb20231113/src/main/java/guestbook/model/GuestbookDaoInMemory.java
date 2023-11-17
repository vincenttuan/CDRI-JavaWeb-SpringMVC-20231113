package guestbook.model;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GuestbookDaoInMemory implements GuestbookDao {
	// In-Memory 模擬資料庫
	private static List<Guestbook> guestbooks;
	static { // 類別成員初始區段
		guestbooks = new CopyOnWriteArrayList<Guestbook>();
		// 預設 4 筆資料
		guestbooks.add(new Guestbook(1, "John", 18, "M", new Date(), "message 1"));
		guestbooks.add(new Guestbook(2, "Mary", 19, "F", new Date(), "message 2"));
		guestbooks.add(new Guestbook(3, "Jack", 20, "M", new Date(), "message 3"));
		guestbooks.add(new Guestbook(4, "Rose", 21, "F", new Date(), "message 4"));
		
	}
	
	// 新增資料
	@Override
	public void create(Guestbook guestbook) {
		guestbooks.add(guestbook);
	}

	// 取得所有紀錄
	@Override
	public List<Guestbook> readAll() {
		return guestbooks;
	}

}
