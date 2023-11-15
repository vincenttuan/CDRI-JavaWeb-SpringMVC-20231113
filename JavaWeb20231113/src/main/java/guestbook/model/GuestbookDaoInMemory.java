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
		guestbooks.add(new Guestbook(1, "John", 18, "M", new Date()));
		guestbooks.add(new Guestbook(2, "Mary", 19, "F", new Date()));
		guestbooks.add(new Guestbook(3, "Jack", 20, "M", new Date()));
		guestbooks.add(new Guestbook(4, "Rose", 21, "F", new Date()));
		
	}
	
	
	@Override
	public void create(Guestbook guestbook) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Guestbook> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
