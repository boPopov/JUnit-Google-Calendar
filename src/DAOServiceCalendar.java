
public class DAOServiceCalendar {
	
	DAOCalendar daoCalendar;
	
	public DAOServiceCalendar(DAOCalendar daoCalendar) {
		this.daoCalendar = daoCalendar;
	}
	
	public int returnNumberOfCalendars() {
		return daoCalendar.returnNumberOfCalendars();
	}
	
	public void addCalendar(String emailCalendar) throws InterruptedException {
		daoCalendar.addCalendar(emailCalendar);
	}
	
}
