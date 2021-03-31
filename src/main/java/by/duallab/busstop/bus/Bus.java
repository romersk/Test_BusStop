package by.duallab.busstop.bus;

public class Bus implements Comparable<Bus>{

	private String nameService;
	private int startTime;
	private int endTime;
	private String startTime_s;
	private String endTime_s;

	public String getNameService()
	{
		return this.nameService;
	}
	
	public String getStartTime()
	{
		return this.startTime_s;
	}
	
	public String getEndTime()
	{	
		return this.endTime_s;
	}
	
	public void parseStringToObject(String data) {
		String[] words = data.split(" ");

		this.nameService = words[0];
		
		this.startTime_s = words[1];
		this.endTime_s = words[2];
		
		String[] numberOne = words[1].split(":");
		String[] numberTwo = words[2].split(":");

		int hourOne = Integer.parseInt(numberOne[0]);
		int minutesOne = Integer.parseInt(numberOne[1]);

		int hourTwo = Integer.parseInt(numberTwo[0]);
		int minutesTwo = Integer.parseInt(numberTwo[1]);

		this.startTime = hourOne * 60 + minutesOne;
		this.endTime = hourTwo * 60 + minutesTwo;
	}

	public boolean isSpecificObj(Bus obj)
	{
		boolean result = false;
		
		if (obj.startTime == this.startTime && obj.endTime < this.endTime)
			result = true;
		
		if (obj.startTime > this.startTime && obj.endTime == this.endTime)
			result = true;
		
		if (obj.startTime > this.startTime && obj.endTime < this.endTime)
			result = true;
		
		if (obj.startTime == this.startTime && obj.endTime == this.endTime && obj.nameService.compareTo("Posh")==0)
			result = true;
		
		return result;
	}
	
	public boolean isSpecificBus(Bus obj)
	{
		boolean result = false;
		
		if (obj.startTime == this.startTime && obj.endTime > this.endTime)
			result = true;
		
		if (obj.startTime < this.startTime && obj.endTime == this.endTime)
			result = true;
		
		if (obj.startTime < this.startTime && obj.endTime > this.endTime)
			result = true;
		
		if ((obj.startTime == this.startTime) && (obj.endTime == this.endTime) && (this.nameService.compareTo("Posh") == 0))
			result = true;
		
		return result;
	}
	
	public boolean checkOneHour()
	{
		boolean result = true;
		
		int intermidiate = this.endTime - this.startTime;
		
		if (intermidiate < 0)
			intermidiate += 1440;
		
		if (intermidiate > 60)
			result = false;
		
		return result;
	}

	public int compareTo(Bus obj) {
		return this.startTime - obj.startTime;
	}
	
}
