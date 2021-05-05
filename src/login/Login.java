
package login;

import java.util.ArrayList;

import roomCustomer.Customer;
import roomCustomer.Room;
import roomCustomer.RoomCustomerReader;

public class Login{
	
	public static Customer loggedCustomer = null;
	
	
	public static boolean checkLogin(int number, String password) {
		ArrayList<Room> rooms = RoomCustomerReader.getRoomsList();
		
		/* The array index of the room we're looking for*/
		int roomIndex;
		
		if(number / 100 == 1)
			roomIndex = (number % 100) - 1;
		else
			roomIndex = (number / 100) * 10 + (number % 100) - 1;
		
		//Checks if the login data is correct
		//If it is, saves the logged customer
		if(rooms.get(roomIndex).getPassword().equals(password))
		{
			loggedCustomer = rooms.get(roomIndex).getCustomer();
			return true;
		}
		else
			return false;
	}
	
	
	
	public static void logout() {
		loggedCustomer = null;
	}
}