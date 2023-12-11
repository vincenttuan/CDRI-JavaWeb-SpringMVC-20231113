package mvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 會議室預訂系統(Web API)
 * 假設您正在為一家公司開發一個會議室預訂系統。您需要實現一個控制器，該控制器可以處理會議室的預訂請求、取消請求以及查詢當前預訂狀態。
 * 
 * 功能要求：
 * -----------------------------------------------------------------------------------------------
 * 1.預訂會議室：
 * 路徑：/bookingMySQL/bookRoom
 * 參數：會議室ID (roomId), 使用者名稱 (name), 預訂日期 (date)
 * 返回：預訂成功(會得到預約號碼 bookingId)或失敗的消息
 * 透過AOP: 預訂會議室:會議室ID (roomId), 使用者名稱 (name), 預訂日期 (date)
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/SpringMVC/mvc/bookingMySQL/bookRoom?roomId=101&name=Tom&date=2023-12-04
 * 範例：http://localhost:8080/SpringMVC/mvc/bookingMySQL/bookRoom?roomId=102&name=Mary&date=2023-12-05
 * 範例：http://localhost:8080/SpringMVC/mvc/bookingMySQL/bookRoom?roomId=201&name=Jack&date=2023-12-06
 * 範例：http://localhost:8080/SpringMVC/mvc/bookingMySQL/bookRoom?roomId=403&name=Rose&date=2023-12-06
 * 
 * -----------------------------------------------------------------------------------------------
 * 2.取消預訂：
 * 路徑：/bookingMySQL/cancelBooking/{bookingId}
 * 參數：預訂ID (bookingId)
 * 返回：取消成功或失敗的消息
 * 透過AOP: 取消預訂:預約 bookingId
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/SpringMVC/mvc/bookingMySQL/cancelBooking/1
 * 
 * -----------------------------------------------------------------------------------------------
 * 3.查看所有預訂：
 * 路徑：/booking/viewBookings
 * 返回：當前所有預訂的列表（可以簡單地返回字符串格式的預訂詳情）
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/SpringMVC/mvc/bookingMySQL/viewBookings
 * 
 * CR
 * 4.修改預約人
 * 路徑：/bookingMySQL/{bookingId}/updateName
 * 返回：是否變更成功
 * -----------------------------------------------------------------------------------------------
 * 範例：http://localhost:8080/SpringMVC/mvc/bookingMySQL/1/updateName?name=Tom
 * 範例：http://localhost:8080/SpringMVC/mvc/bookingMySQL/2/updateName?name=Helen
 * 
 * */
@Controller
//@RestController
@RequestMapping("/bookingMySQL")
public class BookingMySQLController {
	/**
	 * 會議室資訊(Room)
	 +--------+-----------+------------+
	 | roomId | roomName  |  roomSize  |
	 +--------+-----------+------------+
	 |  101   |  101(S)   |     10     |
	 |  102   |  102(M)   |     25     |
	 +--------+-----------+------------+

	 * 預約紀錄(BookingRoom)
	 +-----------+--------+----------+-------------+
	 | bookingId | roomId | username | bookingDate |
	 +-----------+--------+----------+-------------+
	 |     1     |  101   |  Tom     | 2023-12-04  |
	 |     2     |  102   |  Mary    | 2023-12-05  |
	 |     3     |  102   |  Rose    | 2023-12-06  |
	 +-----------+--------+----------+-------------+
	*/
	
	/** 1.預訂會議室：
	 * 路徑：/bookingMySQL/bookRoom
	 * 參數：會議室ID (roomId), 使用者名稱 (name), 預訂日期 (date)
	 * 返回：預訂成功(會得到預約號碼 bookingId)或失敗的消息
	 * 範例：http://localhost:8080/SpringMVC/mvc/bookingMySQL/bookRoom?roomId=101&name=Tom&date=2023-12-04
	 * @throws ParseException 
	*/
	@RequestMapping(value = "/bookRoom", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String bookingBookRoom(@RequestParam(name = "roomId") Integer roomId,
								  @RequestParam(name = "name") String name,
								  @RequestParam(name = "date") String date) throws ParseException {
		
		// 判斷是否該會議室當日已被預約
		// 預約號碼
		// 放到預約集合
		return String.format("預訂成功 (預約號碼 = %d)");
	}
	
	/** 2.取消預訂：
	 * 路徑：/bookingMySQL/cancelBooking/{bookingId}
	 * 參數：預訂ID (bookingId)
	 * 返回：取消成功或失敗的消息
	 * 範例：http://localhost:8080/SpringMVC/mvc/bookingMySQL/cancelBooking/1
	*/
	@GetMapping(value = "/cancelBooking/{bookingId}", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String cancelBooking(@PathVariable("bookingId") Integer bookingId) {
		
		return String.format("取消失敗 (預約號碼 = %d)");
	}
	
	@GetMapping(value = "/autoCancelFirstBooking", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String autoCancelFirstBooking() {
		
		return "自動取消第一筆失敗";
	}
	
	/** 3.查看所有預訂：
	 * 路徑：/bookingMySQL/viewBookings
	 * 返回：當前所有預訂的列表（可以簡單地返回字符串格式的預訂詳情）
	 * 範例：http://localhost:8080/SpringMVC/mvc/bookingMySQL/viewBookings
	 */
	
	@GetMapping(value = "/viewBookings", produces = "text/plain;charset=utf-8")
	public String bookingViewBookings(Model model) {
		return "booking/list";
	}
	
	/* 4.修改預約人
	 * 路徑：/bookingMySQL/{bookingId}/updateName
	 * 返回：是否變更成功
	 * -----------------------------------------------------------------------------------------------
	 * 範例：http://localhost:8080/SpringMVC/mvc/bookingMySQL/1/updateName?name=Tom
	 */
	@RequestMapping(value = "/{bookingId}/updateName", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String updateName(@PathVariable("bookingId") Integer bookingId, @RequestParam("name") String newName) {
		return "預約人修改成功";
	}
}









