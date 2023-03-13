package firstexam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

	public static String getNowDate() {
		LocalDateTime now = LocalDateTime.now();
		
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

}
