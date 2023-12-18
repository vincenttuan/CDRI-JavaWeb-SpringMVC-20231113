package session05.tx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import session05.tx.service.BookOneService;

@Controller
public class BookController {
	
	@Autowired
	private BookOneService bookOneService;
	
	// 買單本書
	public void buyOneBook(String username, Integer bookId) {
		bookOneService.buyOne(username, bookId);
		System.out.println("buyOneBook OK");
	}
	
}
