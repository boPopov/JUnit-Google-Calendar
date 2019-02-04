
public class DAOServiceEvents {
	
	DAOEvents daoEvents;
	
	public DAOServiceEvents(DAOEvents daoEvents) {
		this.daoEvents = daoEvents;
	}
	
	public void disabledEvent(String event) {
		daoEvents.disabledEvent(event);
	}
	
	public int numberOfEvents() {
		return daoEvents.numberOfEvents();	
	}
	
}
