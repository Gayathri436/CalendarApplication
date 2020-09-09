import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CalendarApp
{
    public static void main(String[] args)
    {
    	User user = null;
    	Calendar calendar = null;
    	Map<Integer, User> users = new HashMap<>();
    	Map<Integer, Calendar> calendars = new HashMap<>();
    	Map<Integer, Event> events = new HashMap<>();
	    Scanner input = new Scanner(System.in);
	    int digit = -1;
		while(!(digit == 0)) 
		{
			
			System.out.println("Select one of the following options" + "[0] Quit [1] Create User , [2] Create Calendar,[3] Create Event,[4] Remove Event,"
					+ "[5] Delete Calendar,[6] Read Calendar");
			
		    digit = Integer.parseInt(input.nextLine()); /*Error because of giving space*/
			switch(digit)
			{
			case 0:
				break;
			case 1 :
				System.out.println("Name: ");
				String name = input.nextLine();
				user = new User(name);
				users.put(user.id, user);
				continue;
			case 2 :
				LocalDateTime createdTime = LocalDateTime.now();
				System.out.println("Enter user id ");
				int userId = Integer.parseInt(input.nextLine());
				if(!users.containsKey(userId))
                {
					System.out.println("User not found with Id "+ userId);
					continue;
				
				}
				
			    User createdByUser = users.get(userId);
				calendar = new Calendar(createdByUser,createdTime);
				calendars.put(calendar.id,calendar);
				createdByUser.setCalendar(calendar);
				continue;
			case 3 :
				System.out.println("Enter a Calendar Id: ");
				int calendarId =Integer.parseInt(input.nextLine());
				if(!calendars.containsKey(calendarId))
				{
					System.out.println("Calendar not found with Id " + calendarId);
					continue;
				}
			    System.out.println("EventName: ");
			    String eventName = input.nextLine();
			    System.out.println("StartDateTime: MM/dd/yyyy HH:mm ");
			    String date = input.nextLine();
	            LocalDateTime startDateTime = LocalDateTime.parse(date,DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
	            System.out.println("EndDateTime: MM/dd/yyyy HH:mm ");
	            date = input.nextLine();
	            LocalDateTime endDateTime = LocalDateTime.parse(date,DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
			    System.out.println("Enter an EventType: " + "[W] Webinar , [B] Birthdays , [Wo] Work ,[M] Meetings, [O] other");
				String eventType = input.nextLine();
				EventType type = EventType.OTHER;
                switch(eventType)
			    	{
			    	case "W":
			    		type = EventType.WEBINAR;
			    		break;
			    	case "B":
			    		type = EventType.BIRTHDAY;
			    		break;
			    	case "Wo":
			    		type = EventType.WORK;
			    		break;
			    	case "M":
			    		type = EventType.MEETING;
		                break;
			    	case "O":
			    	default:
			    		type = EventType.OTHER;
			    		break;
			    	}
                Event event = new Event(eventName, type, startDateTime, endDateTime, user, LocalDateTime.now());
              
                events.put(event.id, event);
                calendar = calendars.get(calendarId);
			    calendar.addEvent(event);    
			    continue;
			case 4 :
				System.out.println("Enter a Calendar Id: ");
				calendarId =Integer.parseInt(input.nextLine());
				if(!calendars.containsKey(calendarId))
				{
					System.out.println("Calendar not found with Id " + calendarId);
					continue;
				}
				System.out.println("EventId: ");
			    int eventId = Integer.parseInt(input.nextLine());
			    calendar = calendars.get(calendarId);
			    calendar.removeEvent(events.get(eventId));
				continue;
			case 5:
				System.out.println("Enter User Id");
				userId = Integer.parseInt(input.nextLine());
				if(!users.containsKey(userId))
                {
					System.out.println("User not found with Id "+ userId);
					continue;
				}
				user = users.get(userId);
			    user.removeCalendar();
			    continue;
			case 6:
				System.out.println("Enter Calendar Id ");
				calendarId = Integer.parseInt(input.nextLine());
				if(!calendars.containsKey(calendarId))
				{
					System.out.println("Calendar not found with Id " + calendarId);
					continue;
				}
				calendar = calendars.get(calendarId);
				calendar.print();
				continue;
			default:
				System.out.println("Please Enter numbers from 1-6");
			}
			
		}
	}

}
