package core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Calendar {
	
	public static LocalDate SystemDate = LocalDate.of(2020, 4, 27);//LocalDate.now();//LocalDate.of(2020, 4, 25);
	
	public static LocalDate getSystemDate() {
		return SystemDate;
	}
	
	public static void setSystemDate(LocalDate newSysD) {
		SystemDate = newSysD;
	}
	
	public void goTomorrow() {
		
		SystemDate = SystemDate.plusDays(1);
		
		Logs log = DatabaseData.getLogs();
		
		ContainerJourney[] cJs = DatabaseData.getJournies();
		
		for (int i = 0; i < cJs.length; i++) {
		
			if (((int)ChronoUnit.DAYS.between(cJs[i].getStartDate(), SystemDate) > 0) && 
				((int)ChronoUnit.DAYS.between( SystemDate, cJs[i].getEndDate()) >= 0)) 
			{
				log.appendContainerLog(cJs[i]);
				cJs[i].moveContainerOnJ();
				DatabaseData.getDatabase().updateDatabase("Journies", "Current_x", Double.toString(cJs[i].getCurrentX()), Integer.toString(cJs[i].getJourneyID()));
				DatabaseData.getDatabase().updateDatabase("Journies", "Current_y", Double.toString(cJs[i].getCurrentY()), Integer.toString(cJs[i].getJourneyID()));		
				
			}
			
			if ((int)ChronoUnit.DAYS.between(cJs[i].getStartDate(), SystemDate) == 0)
			{
				log.appendContainerLog(cJs[i]);
			}
		}
	}
	
	public void goIntoTheFutureXDays(int days) {
		for (int i = 0; i < days; i++) {
			goTomorrow();
		}
	}
}
