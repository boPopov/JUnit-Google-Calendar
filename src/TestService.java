import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

public class TestService {
	
	private Date date;
	private LocalDate localDate;
	
	public TestService() {
		date = new Date();
		localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	private DayOfWeek getDayOfWeek() {
		return localDate.getDayOfWeek();
	}
	
	private int getCurrentDate() {
		return date.getDate();
	}
	
	public int getFirstDayOfCurrentWeek() {
		String dayOfWeek = getDayOfWeek().toString().toLowerCase();
		int date = getCurrentDate();
		
		if(dayOfWeek.equals("tuesday"))
			date--;
		else if(dayOfWeek.equals("wednesday"))
			date -= 2;
		else if(dayOfWeek.equals("thursday"))
			date -= 3;
		else if(dayOfWeek.equals("friday"))
			date -= 4;
		else if(dayOfWeek.equals("saterday"))
			date -= 5;
		else if(dayOfWeek.equals("sunday"))
			date -= 6;
		
		return date;
	}
	
	public int returnCorretValue(int number) {
		YearMonth yearMonthObject = YearMonth.of(2019, 1);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		if(number > daysInMonth)
			return number - daysInMonth;
		return number;
	}
	
	public int getMonthBefore(int day) {
		YearMonth yearMonthObject = YearMonth.of(2019, 1);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		return daysInMonth + day;
	}
	
	public int getCurrentDay() {
		return getCurrentDate();
	}
	
}
