package by.duallab.busstop.bus;

import java.time.LocalTime;

public class Bus{

	private String nameService;
	private LocalTime startTime;
	private LocalTime endTime;

	public String getNameService()
	{
		return this.nameService;
	}
	
	public String getStartTime()
	{
		return this.startTime.toString();
	}
	
	public String getEndTime()
	{	
		return this.endTime.toString();
	}
	
	public LocalTime getStart()
	{
		return this.startTime;
	}
	
	public LocalTime getEnd()
	{
		return this.endTime;
	}
	
	public void parseStringToObject(String data) {
		String[] words = data.split(" ");

		this.nameService = words[0];
		
		this.startTime = LocalTime.parse(words[1]);
		this.endTime = LocalTime.parse(words[2]);
	}

	public boolean SpecificObj(Bus obj) //Object is Specific
	{
		return (this.startTime.equals(obj.startTime) && this.endTime.isBefore(obj.endTime) ||
				this.startTime.isAfter(obj.startTime) && this.endTime.equals(obj.endTime) ||
				this.endTime.isBefore(obj.endTime));
	}

	
	public boolean checkOneHour()
	{
		boolean result = true;
		
		int intermidiate = (this.endTime.getHour()*60+this.endTime.getMinute()) - (this.startTime.getHour()*60+this.startTime.getMinute());
		
		if (intermidiate < 0)
			intermidiate += 1440;
		
		if (intermidiate > 60)
			result = false;
		
		return result;
	}

	public int compareTo(Bus serviceBus) {
		
		if (this.startTime.compareTo(serviceBus.startTime) == 0 && 
				this.endTime.compareTo(serviceBus.endTime) == 0)
		{
			return 0;
		}
		
		return 1;
	}

}
