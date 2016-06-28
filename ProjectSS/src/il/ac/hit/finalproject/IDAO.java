package il.ac.hit.finalproject;

import java.util.List;
import java.util.Date;

public interface IDAO{
	
	//ITEMS
	/**
	 * Adds an item to the Database
	 * @param Item
	 * @return true if adding item succeeded
	 * @throws ToDoListPlatformException
	 */
	public boolean AddListItem(ToDoListItem Item)throws ToDoListPlatformException;
	/**
	 * Removes an item from the Database
	 * @param ItemId
	 * @return true if removing succeeded
	 * @throws ToDoListPlatformException
	 */
	public boolean RemoveListItem(int ItemId)throws ToDoListPlatformException;
	//public ToDoListItem[] SelectAllItems()throws ToDoListPlatformException;
	/**
	 * Gets all user's items by ID
	 * @param userId
	 * @return List of items that the user created
	 * @throws ToDoListPlatformException
	 */
	public List<ToDoListItem> GetItemsByUserId(int userId)throws ToDoListPlatformException;
	/**
	 * updates an item according to the parameters and updates the Database accordingly
	 * @param itemId
	 * @param itemTitle
	 * @param itemText
	 * @param parsedDate
	 * @throws ToDoListPlatformException
	 */
	public void EditItem(int itemId, String itemTitle, String itemText, Date parsedDate) throws ToDoListPlatformException; 
	
	//USERS
	/**
	 * Adding a user to the Database
	 * @param user
	 * @return true if adding the user succeeded
	 * @throws ToDoListPlatformException
	 */
	public boolean AddUser(User user) throws ToDoListPlatformException;
	/**
	 * 
	 * @param username
	 * @param password
	 * @return A user if exists, else null
	 * @throws ToDoListPlatformException
	 * @throws Exception
	 */
	public User CheckUser(String username ,String password) throws ToDoListPlatformException, Exception;

	
}
