
/**
 * Represents time - hours:minutes.
 * Values must represent a proper time.
 * 
 * @author Sinaya Nudel
 * @version 25-11-2017
 */
public class Time2 {

	private int _minFromMid;
	private final int DEFAULT_VAL = 0;
	private final int MAX_HOUR = 23;
	private final int MAX_MIN = 59;
	
	/**
	* Constructs a Time2 object.
	* Construct a new time instance with the specified hour and minute.
	* Hour should be between 0-23, otherwise it should be set to 0.
	* Minute should be between 0-59, otherwise they should be set to 0.
	* @param h hour.
	* @param m minute.
	*/    
	
	public Time2(int h, int m){
		int hour = (h > DEFAULT_VAL && h <= MAX_HOUR)? h :DEFAULT_VAL;
		int min = (m > DEFAULT_VAL && m <= MAX_MIN)? m :DEFAULT_VAL;
		_minFromMid = hour*60 + min;
	}
	/**
	* Copy constructor for Time2.
	* Constructs a time with the same variables as another time.
	* @param other The time object from which to construct the new time.
	*/ 
	public Time2 (Time2 other){
		if(other!=null){
			_minFromMid = other._minFromMid;
		}
	}
	/**
	* Returns the hour of the time.
	* @return The hour of the time.
	*/ 
	public int getHour(){
		return _minFromMid/60;
	}
	/**
	* Returns the minute of the time.
	* @return The minute of the time.
	*/ 
	public int getMinute(){
		return _minFromMid%60;
	}
	/**
	* Changes the hour of the time.
	* If an illegal number is received hour will be unchanged.
	* @param num The new hour.
	*/ 
	public void setHour(int num){
		if(num >= DEFAULT_VAL && num <= MAX_HOUR){
			_minFromMid = (_minFromMid%60) + (num*60); 
		}
	}
	/**
	* Changes the minute of the time.
	* If an illegal number is received minute will be unchanged.
	* @param num The new minute.
	*/ 
	public void setMinute(int num){
		if(num >= DEFAULT_VAL && num <= MAX_MIN){
			_minFromMid = _minFromMid - (_minFromMid%60) + num; 
		}
	}
	/**
	* Return a string representation of this time (hh:mm).
	* @return String representation of this time (hh:mm).
	*/ 
	public String toString(){
		return (getHour() < 10 ? "0" : "") + getHour() + ":" + (getMinute() < 10 ? "0" : "") + getMinute();
		
	}
	/**
	* Return the amount of minutes since midnight.
	* @return amount of minutes since midnight.
	*/ 
	public int minFromMidnight(){
		return _minFromMid;
	}
	/**
	* Check if the received time is equal to this time.
	* @param other The time to be compared with this time.
	* @return True if the received time is equal to this time.
	*/ 
	public boolean equals (Time2 other){
		return (_minFromMid == other._minFromMid);
	}
	/**
	* Check if this time is before a received time.
	* @param other The time to check if this point is before.
	* @return True if this time is before other time.
	*/ 
	public boolean before (Time2 other){
		return (_minFromMid < other._minFromMid);
	}
	/**
	* Checks if this time is after a received time.
	* @param other  The time to check if this time is after.
	* @return True if this time is after other time.
	*/ 
	public boolean after (Time2 other){
		return other.before(this);
	}
	/**
	* Calculates the difference (in minutes) between two times.
	* @param other The time to check the difference with. Assumption: this time is after other time.
	* @return int difference in minutes.
	*/ 
	public int difference(Time2 other){
		return Math.abs(_minFromMid - other._minFromMid);
	}
}
