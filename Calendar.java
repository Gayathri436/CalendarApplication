import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class Calendar
{
	int id;
	private User createdBy;
	private LocalDateTime createdTime;
	private static int autoGeneratedId = 1;
	private Map<LocalDate,ArrayList<Event>> events;
	public Calendar(User createdBy, LocalDateTime createdTime) 
	{
		this.id = autoGeneratedId++;
		this.createdBy = createdBy;
		this.createdTime = createdTime;
		System.out.println("Calendar created with id = " + this.id);
		events =  new HashMap<LocalDate, ArrayList<Event>>();
	}
	
	public void addEvent(Event event)
	{
		LocalDate eventDate = event.startDateTime.toLocalDate();
		if(!eventDate.isEqual(event.endDateTime.toLocalDate()))
		{
			return;
	    }
		if(event.endDateTime.isBefore(event.startDateTime))
		{
			System.out.println("Error - EndTime should not Before StartTime ");
			return;
		}
		if(events.containsKey(eventDate))
		{
			ArrayList<Event> eventList = events.get(eventDate);
			for(int i=0; i<eventList.size();i++)
			{
				if(eventList.get(i).checkConflict(event))
				{
					System.out.println("Event Id = " + event.id + " Conflict occurs Event cannot be added ");
					return;
				}
			}
			eventList.add(event);
			events.put(eventDate,eventList);		
		}
		else
		{ 
			ArrayList<Event> eventList = new ArrayList<Event>();
			eventList.add(event);
			events.put(eventDate,eventList);
		}	
	   System.out.println("Event Id = " + event.id + " Event added successfully");
	}
   
    public void removeEvent(Event event) 
    {
	    LocalDate eventDate = event.startDateTime.toLocalDate();
	    if(events.containsKey(eventDate))
	    {
	    	ArrayList<Event> eventList = events.get(eventDate);
	    	for(int i=0; i<eventList.size();i++)
	    	{
	    		if(eventList.get(i).equals(event))
	    		{
	    			eventList.remove(i);
	    			System.out.println(" Event Id= " + event.id + " removed successfully");
	    			return;
				}
	    	}
	   }  
	    System.out.println(" Event Id = " + event.id + " not found");
    }
    
    public void print()
    {
    	System.out.println("Calendar Id = " + id);   
    	System.out.println("Calendar Created By = " + createdBy.name);   

    	Set<LocalDate> setKeys = events.keySet();
    	if(setKeys.isEmpty()) 
    	{
    		System.out.println("Events are empty");
    		return;
    	}
    	for(LocalDate key : setKeys)
    	{
    	    System.out.println( key );
    	    for(Event event : events.get(key))
    	    {
    	    	event.print();
    	    }
    	}
    }
}