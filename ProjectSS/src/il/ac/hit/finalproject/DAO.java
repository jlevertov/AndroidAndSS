package il.ac.hit.finalproject;

import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author Roman&Jacob
 * 
 * Data Access Object class
 * Links The User To The Database
 * 
 * Implemented as Singleton
 */

public class DAO implements IDAO {
	private SessionFactory factory = null;
	Session session = null;
	private static DAO instance = null;
	
	public static DAO GetInstance()
	{
		if(instance == null)
		{
			instance = new DAO();
		}
		
		return instance;
	}
	
	/**
	 * Primary Constructor
	 */
	private DAO() {
		try{
			factory = new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	@Override
	public boolean AddListItem(ToDoListItem Item) throws ToDoListPlatformException {
		session = factory.openSession();
		session.beginTransaction();
		try{
		session.save(Item);
		session.getTransaction().commit();
		} catch(HibernateException e){
			System.out.println(e.getMessage());
			throw new ToDoListPlatformException();
		}
		finally{
			session.close();
			}
		return true;
	}
	@Override
	public boolean RemoveListItem(int ItemId) throws ToDoListPlatformException {
		session = factory.openSession();
		session.beginTransaction();
		
		ToDoListItem item = new ToDoListItem();
		item.setId(ItemId);
		session.delete(item);
		session.getTransaction().commit();
		return true;
	}

	/*public ToDoListItem[] SelectAllItems() throws ToDoListPlatformException {
		session = factory.openSession();
		List items = null;
		try{
		session.beginTransaction();
		items = session.createQuery("from ToDoListItem").list();
		session.getTransaction().commit();
		}catch(HibernateException e)
		{
			//
		}
		finally{
			session.close();
			}
		//session.close();
		if(items != null)
		{
			return (ToDoListItem[])items.toArray();
		}
		else return null;
	}*/

	@Override
	public boolean AddUser(User user) throws ToDoListPlatformException {
		session = factory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	
	public User CheckUser(String username,String password) throws Exception
	{
		session = factory.openSession();
		session.beginTransaction();
		List<User> userList = session.createQuery("from User where USERNAME= :username and PASSWORD= :password ").setParameter("password", password).setParameter("username", username).list();
		User currentUser;
		Iterator i = userList.iterator();
		if(i.hasNext())
		{
			currentUser = (User)i.next();
		}
		else
		{
			currentUser = null;
		}
		return currentUser;
	}
	
	public List<ToDoListItem> GetItemsByUserId(int userId)throws ToDoListPlatformException
	{
		session = factory.openSession();
		session.beginTransaction();
		List<ToDoListItem> ListItems = (List<ToDoListItem>)session.createQuery("from ToDoListItem where USERID= :userId ").setParameter("userId", userId).list();
		session.close();
		return ListItems;
	}
	
	public void EditItem(int itemId, String itemTitle, String itemText, Date parsedDate) throws ToDoListPlatformException
	{
		session = factory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("UPDATE ToDoListItem SET TITLE = :itemTitle , TEXT = :itemText , DEADLINE = :parsedDate WHERE ID = :itemId");
		query.setParameter("itemId", itemId);
		query.setParameter("itemTitle", itemTitle);
		query.setParameter("itemText", itemText);
		query.setParameter("parsedDate", parsedDate);
		
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

}
