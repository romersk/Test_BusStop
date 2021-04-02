package by.duallab.busstop.bus;

import java.time.LocalTime;

public class Bus{

	private String nameService;
	private LocalTime startTime;
	private LocalTime endTime;
	private int difference;

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
		 if (this.endTime.compareTo(obj.endTime) == 0) {
             if (this.difference > obj.difference) {
                 return true;
             }
		 }
		 
		 if ((this.endTime.isBefore(this.startTime) && (obj.endTime.isBefore(obj.endTime)))) {
	            if (this.startTime.isBefore(obj.startTime) && (this.endTime.isAfter(obj.endTime))) {
	                return true;
	            }
	        } else if ((this.endTime.isBefore(this.startTime) && (obj.endTime.isAfter(obj.startTime)))) {
	            if (this.startTime.isBefore(obj.startTime) && (this.endTime.isBefore(obj.endTime))) {
	                return true;
	            }
	        } else if ((this.endTime.isAfter(this.startTime) && (obj.endTime.isAfter(obj.startTime)))) {
	            if (this.startTime.isBefore(obj.startTime) && (this.endTime.isAfter(obj.endTime))) {
	                return true;
	            }
	        }
		 
		 return false;
	}

	
	public boolean checkOneHour()
	{
		boolean result = true;
		
		int intermidiate = (this.endTime.getHour()*60+this.endTime.getMinute()) - (this.startTime.getHour()*60+this.startTime.getMinute());
		
		if (intermidiate < 0)
			intermidiate += 1440;
		
		difference = intermidiate;
		
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
