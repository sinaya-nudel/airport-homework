
/**
 * Represents time - hours:minutes.
 * Coordinates cannot be negative.
 * 
 * @author Sinaya Nudel
 * @version 25-11-2017
 */
public class Time1 {
	
	private int _hour;
	private int _minute;
	private final int DEFAULT_VAL = 0;
	private final int MAX_HOUR = 23;
	private final int MAX_MIN = 59;
	
	/**
	* Constructs a Time1 object.
	* Construct a new time instance with the specified hour and minute.
	* hour should be between 0-23, otherwise it should be set to 0.
	* minute should be between 0-59, otherwise it should be set to 0.  
	* @param h the hour of the time.
	* @param m the minute of the time.
	*/    
	
	public Time1(int h, int m){
		_hour = (h > DEFAULT_VAL && h <= MAX_HOUR)? h :DEFAULT_VAL;
		_minute = (m > DEFAULT_VAL && m <= MAX_MIN)? m :DEFAULT_VAL;
	}
	
	/**
	* Copy constructor for Time1.
	* Construct a time with the same instance variables as another time.
	* @param t The time object from which to construct the new time.
	*/ 
	
	public Time1 (Time1 t){
		if(t!=null){
			_hour = t._hour;
			_minute = t._minute;
		}
	}
	/**
	* Returns the hour of the time.
	* @return The hour of the time.
	*/ 
	
	public int getHour(){
		return _hour;
	}	
	
	/**
	* Returns the minute of the time.
	* @return The minute of the time.
	*/ 
	
	public int getMinute(){
		return _minute;
	}
	/**
	* Changes the hour of the time.
	* If an illegal number is received hour will be unchanged.
	* @param num The new hour.
	*/ 
	public void setHour(int num){
		if(num >= DEFAULT_VAL && num <= MAX_HOUR){
			_hour = num; 
		}
	}
	/**
	* Changes the minute of the time.
	* If an illegal number is received minute will be unchanged.
	* @param num The new minute.
	*/ 
	public void setMinute(int num){
		if(num >= DEFAULT_VAL && num <= MAX_MIN){
			_minute = num; 
		}
	}
	/**
	* Return a string representation of this time (hh:mm).
	* @return String representation of this time (hh:mm).
	*/ 
	public String toString(){
		return ((_hour < 10 ? "0" : "") + _hour) + ":" + (_minute < 10 ? "0" : "") + _minute;
		
	}
	/**
	* Return the amount of minutes since midnight.
	* @return amount of minutes since midnight.
	*/ 
	public int minFromMidnight(){
		return ((_hour*60) + _minute);
	}
	/**
	* Check if the received time is equal to this time.
	* @param other The time to be compared with this time.
	* @return True if the received time is equal to this time.
	*/ 
	public boolean equals (Time1 other){
		return (_hour == other._hour && _minute == other._minute);
	}
	/**
	* Check if this time is before a received time.
	* @param other The time to check if this point is before.
	* @return True if this time is before other time.
	*/ 
	public boolean before (Time1 other){
		return (minFromMidnight() < other.minFromMidnight());
	}
	/**
	* Check if this time is after a received time.
	* @param other The time to check if this point is after.
	* @return True if this time is after other time.
	*/ 
	public boolean after (Time1 other){
		return other.before(this);
	}
	/**
	* Calculates the difference (in minutes) between two times.
	* Assumption: this time is after other time.
	* @param other The time to check the difference to.
	* @return int difference in minutes.
	*/ 
	public int difference(Time1 other){
		return Math.abs(minFromMidnight() - other.minFromMidnight());
	}
	
}

	
