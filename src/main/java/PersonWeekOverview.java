public class PersonWeekOverview
{
	private int personID;
	private int hoursMon = 0;
	private int hoursTue = 0;
	private int hoursWed = 0;
	private int hoursThu = 0;
	private int hoursFri = 0;
	private int hoursSat = 0;
	private int hoursSun = 0;

	public PersonWeekOverview(int personID)
	{
		this.personID = personID;
	}

	public PersonWeekOverview(int personID, int hoursMon, int hoursTue, int hoursWed, int hoursThu, int hoursFri, int hoursSat, int hoursSun)
	{
		this.personID = personID;
		this.hoursMon = hoursMon;
		this.hoursTue = hoursTue;
		this.hoursWed = hoursWed;
		this.hoursThu = hoursThu;
		this.hoursFri = hoursFri;
		this.hoursSat = hoursSat;
		this.hoursSun = hoursSun;
	}

	public void updateDayValues(int hoursMon, int hoursTue, int hoursWed, int hoursThu, int hoursFri, int hoursSat, int hoursSun)
	{
		if(hoursMon != 0) this.hoursMon = hoursMon;
		if(hoursTue != 0) this.hoursTue = hoursTue;
		if(hoursWed != 0) this.hoursWed = hoursWed;
		if(hoursThu != 0) this.hoursThu = hoursThu;
		if(hoursFri != 0) this.hoursFri = hoursFri;
		if(hoursSat != 0) this.hoursMon = hoursSat;
		if(hoursSun != 0) this.hoursMon = hoursSun;
	}

	public int getPersonID()
	{
		return personID;
	}

	public void setPersonID(int personID)
	{
		this.personID = personID;
	}

	public int getHoursMon()
	{
		return hoursMon;
	}

	public void setHoursMon(int hoursMon)
	{
		this.hoursMon = hoursMon;
	}

	public int getHoursTue()
	{
		return hoursTue;
	}

	public void setHoursTue(int hoursTue)
	{
		this.hoursTue = hoursTue;
	}

	public int getHoursWed()
	{
		return hoursWed;
	}

	public void setHoursWed(int hoursWed)
	{
		this.hoursWed = hoursWed;
	}

	public int getHoursThu()
	{
		return hoursThu;
	}

	public void setHoursThu(int hoursThu)
	{
		this.hoursThu = hoursThu;
	}

	public int getHoursFri()
	{
		return hoursFri;
	}

	public void setHoursFri(int hoursFri)
	{
		this.hoursFri = hoursFri;
	}

	public int getHoursSat()
	{
		return hoursSat;
	}

	public void setHoursSat(int hoursSat)
	{
		this.hoursSat = hoursSat;
	}

	public int getHoursSun()
	{
		return hoursSun;
	}

	public void setHoursSun(int hoursSun)
	{
		this.hoursSun = hoursSun;
	}
}
