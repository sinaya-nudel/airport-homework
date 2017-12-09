/**
 * Represents an Airport.
 * An Airport object is represented by the Airport's city, by array of flights for one day schedule and its number of flights.
 * 
 * @author Sinaya Nudel
 * @version 09-12-2017
 */

public class Airport {
	private Flight[] _flightsSchedule; //array that contains flights from or to the airport, in one day. 
	private int _noOfFlights; // the flights number for the airport in one day
	private String _airport; // the city of the airport location
	private final int MAX_FLIGHT = 200; //the maximum flights number in one day.
	private final int EMPTY = 0;
	/**
	* Constructor for a Airport object.
	* the number of flights for the Schedule Initialized to 0.
	* the capacity of the possible number of flights for the Schedule Initialized to 200.
	* @param city The city of the airport location.

	*/ 
	
	public Airport(String city) {
		_airport = city;
		_flightsSchedule = new Flight[MAX_FLIGHT];
		_noOfFlights = EMPTY;
	}
	/**
	* Add flight to the airport's array of flights. 
	* If the number of flights for this airport is less than 200, and the flight's destination or origin equals to the airport city,
	* the flight is added and true is returned. 
	* Else, no flight is added and false is returned.
	* @param f The flight to be added to the airport's array of flights.
	* @return True if the flight was added to the airport's array of flights.
	*/ 
	public boolean addFlight(Flight f) {
		
		if (_noOfFlights<MAX_FLIGHT && (f.getOrigin().equals(_airport) || f.getDestination().equals(_airport))){
			_flightsSchedule[_noOfFlights++] = new Flight(f);
			return true;
		}
		else 
			return false;
	}
	/**
	* Remove flight from the airport's array of flights. 
	* If the flight was found, it is removed and true is returned.
	* If the flight not found, false is returned.
	* @param f The flight to be removed from the airport's array of flights.
	* @return True if the flight was removed from the airport's array of flights.
	*/ 
	public boolean removeFlight(Flight f){
		for (int i = 0; i<_noOfFlights; i++){
			if(_flightsSchedule[i].equals(f)){ 
				for(int j = i; j<_noOfFlights-1; j++){ // if the flight was found, the array memory will no longer connect to this object- the data moving one step backwards
					_flightsSchedule[j] = _flightsSchedule[j+1];  
				}
				_flightsSchedule[_noOfFlights-1] = null; //removing the connection to the last object since it copied one step backwards.
				_noOfFlights--; //reducing the number of flights  by 1 due to the removing.
				return true;
			}
		}
		return false;
	}
	/**
	* Returns the time for the earliest flight's departure from the city.
	* If no flight is departure from this city, null is returned.
	* @param place The origin city for the flight's departure .
	* @return the time for the earliest flight's departure from the city.
	*/ 
	public Time1 firstFlightFromDestination (String place) {
		for (int j=0; j<_noOfFlights; j++){
			if( _flightsSchedule[j].getOrigin()==place){
			Flight first = _flightsSchedule[j];	//if a flight from this city is found- it considered the first flight until the method find an earlier flight in the rest of the array.
			Time1 t1 = new Time1(first.getDeparture());
				for (int i = 1; i<_noOfFlights ; i++){
					if( _flightsSchedule[i].getOrigin()==place){
						Time1 t2 = new Time1(_flightsSchedule[i].getDeparture());
						if(t2.before(t1)) //check if the departure of the flight (from the specifiec place) is earlier that the previous "first" flight
							first = _flightsSchedule[i];
						return new Time1(first.getDeparture());
					}
				}
			continue;
			}
		}
		return null;
		
	}
	/**
	* Return a string representation of this airport contains the airport city and all the flights in the schedule.
	* @return String representation of this airport contains the airport city and all the flights in the schedule.
	*/ 
	public String toString() {
		String flights = "The flights for airport " + _airport + " today are:";
		for(int i=0; i<_noOfFlights; i++){
			flights += "\n" + _flightsSchedule[i].toString();
		}	
		return flights;	
	}
	/**
	* return the number of the full flights for the airport schedule (in a one day).
	* @return the number of the full flights for the airport schedule (in a one day).
	*/ 
	public int howManyFullFlights() {
		int count = 0;
		for (int j=0; j<_noOfFlights; j++){
			if(_flightsSchedule[j].getIsFull() == true){
				count++;
			}
		}
		return count;
	}
	/**
	* Check how many flights are between two cities.
	* The flights can be from the first city to the second, or reverse.
	* @param city1 the first city for the comparison
	* @param city2 the second city for the comparison
	* @return the number of flights between the two cities.
	*/ 
	public int howManyFlightsBetween (String city1, String city2) {
		int count = 0;
		for (int j=0; j<_noOfFlights; j++){
			if((_flightsSchedule[j].getDestination().equals(city1) && _flightsSchedule[j].getOrigin().equals(city2)) || 
					(_flightsSchedule[j].getDestination().equals(city2) && _flightsSchedule[j].getOrigin().equals(city1)))
				count++;
			
		}
		return count;
	}
	/**
	* return the most popular destination (city) for a day.
	* If there is no flights in the schedule, null is returned.
	* @return the most popular destination (city) for a day.
	*/ 
	public String mostPopularDestination() {
		 if (_noOfFlights==EMPTY){
			 return null;
		 }
		 else{
			 int[] popular = new int[_noOfFlights]; // array of numbers of the popularity value for each flight's destination
			 for(int j = 0; j<_noOfFlights;j++){
				 int popularity= 0;
				 Flight flight1 = new Flight(_flightsSchedule[j]); 
				 for(int i = 0; i<_noOfFlights;i++){ //checks if the destination appears also in the other flights. 
					 if(flight1.getDestination().equals(_flightsSchedule[i].getDestination())){
						 popularity++;
					 }
				 }
				 popular[j] = popularity;
			 }
			 int pop =  popular[0]; 
			 int popInd = 0;
			 for(int x =1; x<popular.length;x++){ //check what is the highest value of popularity for each int in the popularity array
				 if(popular[x]>pop){
					 pop = popular[x];
					 popInd = x; //the value of x represents the same index for the flights array as the popularity array.
				 }
			 }
			return _flightsSchedule[popInd].getDestination(); 
		 }
	}
	/**
	* return the most expensive flight due to the price of its ticket.
	* If there is no flights in the schedule, null is returned.
	* @return the most expensive flight due to the price of its ticket.
	*/ 
	 public Flight mostExpensiveTicket() {
		 if (_noOfFlights==EMPTY){
			 return null;
		 }
		 else{
			 Flight mostExpensive = _flightsSchedule[0];
			 for(int i = 0; i<_noOfFlights;i++){
				 if(mostExpensive.isCheaper( _flightsSchedule[i])){
					 mostExpensive = _flightsSchedule[i];
				 }
			 }
			 return new Flight(mostExpensive);
		 }
	 }
	 /**
		* return the longest flight due to the flight's duration.
		* If there is no flights in the schedule, null is returned.
		* @return the longest flight due to the flight's duration.
		*/ 
	public Flight longestFlight() {
		 if (_noOfFlights==EMPTY){
			 return null;
		 }
		 else{
			 Flight longest = _flightsSchedule[0];
			 for(int i = 0; i<_noOfFlights;i++){
				 if(longest.getFlightDuration()< _flightsSchedule[i].getFlightDuration()){
					 longest = _flightsSchedule[i];
				 }
			 }
			 return new Flight(longest);
		 }
	}
		
}


