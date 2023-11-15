package guestbook.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GuestbookDaoInMemory implements GuestbookDao {
	// In-Memory 模擬資料庫
	private static List<Guestbook> guestbooks;
	static { // 類別成員初始區段
		guestbooks = new CopyOnWriteArrayList<Guestbook>();
		// 預設 4 筆資料
		
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
