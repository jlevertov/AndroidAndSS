package il.ac.hit.finalproject;

import java.util.Date;
import javax.persistence.*;

/**
 * 
 * @author Roman&Jacob
 *	The Class Represents A ToDo List Item For The Database.
 */
public class ToDoListItem {

	/**
	 * Item's Id
	 */
	private int Id;
	/**
	 * Item's User Id
	 */
	private int UserId;
	/**
	 * Item's Title
	 */
	private String Title;
	/**
	 * Item's Content Text
	 */
	private String WhatToDo;
	/**
	 * Item's CreationDate
	 */
	private Date CreationDate;
	/**
	 * Item's DeadLine Date
	 */
	private Date DeadLine;
	/**
	 * Default Constructor
	 */
	public ToDoListItem()
	{
		super();
	}
	
	
	/**
	 * Primary Constructor
	 */
	public ToDoListItem(int userId, String title, String whatToDo, Date creationDate, Date deadLine) {
		super();
		UserId = userId;
		Title = title;
		WhatToDo = whatToDo;
		CreationDate = creationDate;
		DeadLine = deadLine;
	}



	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String category) {
		Title = category;
	}

	public String getWhatToDo() {
		return WhatToDo;
	}

	public void setWhatToDo(String whatToDo) {
		WhatToDo = whatToDo;
	}

	public Date getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}
	@Override
	public String toString() {
		return "ToDoListItem [Id=" + Id + ", Category=" + Title + ", WhatToDo=" + WhatToDo + ", CreationDate="
				+ CreationDate + "]";
	}



	public Date getDeadLine() {
		return DeadLine;
	}



	public void setDeadLine(Date deadLine) {
		DeadLine = deadLine;
	}




	public int getUserId() {
		return UserId;
	}



	public void setUserId(int userId) {
		this.UserId = userId;
	}
	
}
