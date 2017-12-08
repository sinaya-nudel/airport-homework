


public class Airport {
	private Flight[] _flightsSchedule;
	private int _noOfFlights;
	private String _airport;
	private final int MAX_FLIGHT = 200;
	private final int EMPTY = 0;
	
	public Airport(String city) {
		_airport = city;
		_flightsSchedule = new Flight[MAX_FLIGHT];
		_noOfFlights = EMPTY;
	}
	
	public boolean addFlight(Flight f) {
		
		if (_noOfFlights<MAX_FLIGHT && (f.getOrigin() == _airport || f.getDestination() == _airport)){
			_flightsSchedule[_noOfFlights++] = new Flight(f);
			return true;
		}
		else 
			return false;
	}
	public boolean removeFlight(Flight f){
		for (int i = 0; i<_noOfFlights; i++){
			if(_flightsSchedule[i].equals(f)){
				for(int j = i; j<_noOfFlights-1; j++){
					_flightsSchedule[j] = _flightsSchedule[j+1]; 
				}
				_flightsSchedule[_noOfFlights-1] = null;
				_noOfFlights--;
				return true;
			}
		}
		return false;
	}
	public Time1 firstFlightFromDestination (String place) {
		for (int j=0; j<_noOfFlights; j++){
			if( _flightsSchedule[j].getOrigin()==place){
			Flight first = _flightsSchedule[j];	
			Time1 t1 = new Time1(first.getDeparture());
				for (int i = 1; i<_noOfFlights ; i++){
					if( _flightsSchedule[i].getOrigin()==place){
						Time1 t2 = new Time1(_flightsSchedule[i].getDeparture());
						if(t2.before(t1))
							first = _flightsSchedule[i];
						return new Time1(first.getDeparture());
					}
				}
			continue;
			}
		}
		return null;
		
	}
	public String toString() {
		String flights = "The flights for airport " + _airport + " today are:";
		for(int i=0; i<_noOfFlights; i++){
			flights += "\n" + _flightsSchedule[i].toString();
		}	
		return flights;	
	}
	public int howManyFullFlights() {
		int count = 0;
		for (int j=0; j<_noOfFlights; j++){
			if(_flightsSchedule[j].getIsFull() == true){
				count++;
			}
		}
		return count;
	}
	public int howManyFlightsBetween (String city1, String city2) {
		int count = 0;
		for (int j=0; j<_noOfFlights; j++){
			if((_flightsSchedule[j].getDestination() == city1 && _flightsSchedule[j].getOrigin() == city2) || 
					(_flightsSchedule[j].getDestination() == city2 || _flightsSchedule[j].getOrigin() == city1)){
				count++;
			}
		}
		return count;
	}
	public String mostPopularDestination() {
		 if (_noOfFlights==EMPTY){
			 return null;
		 }
		 else{
			 int[] popular = new int[_noOfFlights];
			 for(int j = 0; j<_noOfFlights;j++){
				 int popularity= 0;
				 Flight flight1 = _flightsSchedule[j]; 
				 for(int i = 0; i<_noOfFlights;i++){
					 if(flight1.getDestination() == _flightsSchedule[i].getDestination()){
						 popularity++;
					 }
				 }
				 popular[j] = popularity-1;
			 }
			 int pop =  popular[0];
			 int popInd = 0;
			 for(int x =1; x<popular.length;x++){
				 if(popular[x]>pop){
					 pop = popular[x];
					 popInd = x;
				 }
			 }
			return _flightsSchedule[popInd].getDestination();
		 }
	}
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
	
	public static void main(String[] args) {
		/*******************************  Airport CLASS TESTER *******************************/
		/***********************************************************************************/

		//Check constructor
		Airport a1 = new Airport("Tel-Aviv");
		
		//AddFlight
		Flight f1 = new Flight("Tel-Aviv","London",12,0,210,100,100);
		Flight f2 = new Flight("New York","Tel-Aviv",10,50,210,250,150);
		a1.addFlight(f1);
		a1.addFlight(f2);
		System.out.println(a1);
		//RemoveFlight
		a1.removeFlight(f1);
		System.out.println(a1);
		
		//First Flight From Destination
		Flight f3 = new Flight("Tel-Aviv","Paris",11,35,210,100,50);
		a1.addFlight(f3);
		System.out.println(a1.firstFlightFromDestination("Tel-Aviv"));

		//HowMany Full Flights
		
		System.out.println("Full Flight - " + a1.howManyFullFlights());
		
		//HowMany Flights Between
		Flight f4 = new Flight("London","Tel-Aviv",12,1,210,249,100);
		a1.addFlight(f4);
		int y = a1.howManyFlightsBetween("Tel-Aviv","London");
		System.out.println("FlightsBetween Tel-Aviv to London - " + y);

		//Most Popular Destination
		String mostPopular = a1.mostPopularDestination();
		System.out.println("Most Popular Destination - " + mostPopular);

		//Most Expensive Ticket
		Flight mostExpensive = a1.mostExpensiveTicket();
		System.out.println("Most Expensive Ticket - " + mostExpensive);
		
		//Longest Flight
		Flight longestFlight = a1.longestFlight();
		System.out.println("Longest Flight - " + longestFlight);
		
	}

}
