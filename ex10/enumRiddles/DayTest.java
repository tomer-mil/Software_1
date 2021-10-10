package enumRiddles;

enum Day {
	   MONDAY(1),
	   TUESDAY(2),
	   WEDNESDAY(3),
	   THURSDAY(4),
	   FRIDAY(5),
	   SATURDAY(6),
	   SUNDAY(7);

	   private final int dayNumber;

	Day(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	public Day next() {
		switch (this) {
			case MONDAY: 	return TUESDAY;
			case TUESDAY:	return WEDNESDAY;
			case WEDNESDAY:	return THURSDAY;
			case THURSDAY:	return FRIDAY;
			case FRIDAY:	return SATURDAY;
			case SATURDAY:	return SUNDAY;
			case SUNDAY:	return MONDAY;
		}
	   	return null;
	   }
	 
	   int getDayNumber() {
	      return this.dayNumber;
	   }
	}
	   
	public class DayTest {
	   public static void main(String[] args) {
	      for (Day day : Day.values()) {
	         System.out.printf("%s (%d), next is %s\n", day, day.getDayNumber(), day.next());
	      }
	   }
	}
