package il.ac.hit.finalproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Home
 */

@WebServlet
@MultipartConfig
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private IDAO dao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURL().toString();
		boolean wasException = false;
		if (path.endsWith("login")) {
			IDAO dao = DAO.GetInstance();
			
			//Creating Variables For Further Use
			String errorMessage = "Wrong Username or Password";
			User currentUser = null;
			try {
				//Checking If the User Exists in Database
				currentUser = dao.CheckUser(request.getParameter("usernameField"), request.getParameter("passwordField"));
			} catch (Exception e) {
				e.printStackTrace();
				errorMessage = "Login Error Accured";
				wasException = true;
			}
			finally
			{
				//Creating a Server Response to transfer the Result and Errors to the Users end
				ServerResponse servResponse = new ServerResponse(false, errorMessage, null);
				if(currentUser != null && !wasException) 
				{
					servResponse.result = currentUser;
					servResponse.status = true;
				}
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsonView.jsp");
				request.setAttribute("Object", servResponse);
			    rd.forward(request, response);
			}
		}
		else if (path.endsWith("register")) {
			IDAO dao = DAO.GetInstance();
			
			//Creating Variables For Further Use
			String errorMessage = "";
			User currentUser = new User(0,request.getParameter("usernameField"),request.getParameter("passwordField"),request.getParameter("emailField"),false);
			try {
				//Adding User to the Database
				dao.AddUser(currentUser);
			}catch (Exception e) {
				e.printStackTrace();
				errorMessage = "Username is Taken";
				wasException = true;
			}
			try{	
				//Checking if the newly registered user was added by letting him in
				currentUser = dao.CheckUser(request.getParameter("usernameField"), request.getParameter("passwordField"));
			} catch (Exception e) {
				e.printStackTrace();
				errorMessage = "Login Error Accured";
				wasException = true;
			}
			finally
			{
				//Creating a Server Response to transfer the Result and Errors to the Users end
				ServerResponse servResponse = new ServerResponse(false, errorMessage, null);
				if(currentUser != null && !wasException) 
				{
					servResponse.result = currentUser;
					servResponse.status = true;
				}
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsonView.jsp");
				request.setAttribute("Object", servResponse);
			    rd.forward(request, response);
			}
		}
		else if (path.endsWith("addNewItem")) {
			IDAO dao = DAO.GetInstance();
			
			//Creating Variables For Further Use
			String errorMessage = "";
			Date parsedDate = new Date();
			List<ToDoListItem> items = null;
			
			//Getting Parameters From HTML Form and Parsing Them
			int userId = Integer.parseInt(request.getParameter("userIdHiddenAdd"));
			String itemTitle = request.getParameter("itemTitleAdd");
			String itemText = request.getParameter("itemTextAdd");
			String itemDeadLineAdd = request.getParameter("itemFinalDate");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				parsedDate = df.parse(itemDeadLineAdd);
				System.out.println("parsedDate  " + parsedDate);
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.println("userId " + userId);
			try {
				Date startDate = df.parse(itemDeadLineAdd);
				System.out.println(startDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			// Creating and adding listItem to the Database
			ToDoListItem itemtoAdd = new ToDoListItem(userId ,itemTitle,itemText, new Date() , parsedDate);
			try {
				dao.AddListItem(itemtoAdd);
				items = dao.GetItemsByUserId(userId);
			} catch (ToDoListPlatformException e) {
				e.printStackTrace();
				errorMessage = "Error Adding Item";
			}
			finally
			{
				//Creating a Server Response to transfer the Result and Errors to the Users end
				ServerResponse servResponse = new ServerResponse(false, errorMessage, null);
				if(items != null)
				{
					servResponse.result = items;
					servResponse.status = true;
				}
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsonView.jsp");
				request.setAttribute("Object", servResponse);
			    rd.forward(request, response);
			}
		}
		else if (path.endsWith("loadItems")) {
			IDAO dao = DAO.GetInstance();
			
			//Creating Variables For Further Use
			String errorMessage = "";
			List<ToDoListItem> items = null;
			try {
				//Selecting All relevant Items with the same UserId
				items = dao.GetItemsByUserId(Integer.parseInt(request.getParameter("userId")));
			} catch (ToDoListPlatformException e) {
				e.printStackTrace();
				errorMessage = "Error Loading Items";
			}
			finally
			{
				//Creating a Server Response to transfer the Result and Errors to the Users end
				ServerResponse servResponse = new ServerResponse(false, errorMessage, null);
				if(items != null)
				{
					servResponse.result = items;
					servResponse.status = true;
				}
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsonView.jsp");
				request.setAttribute("Object", servResponse);
			    rd.forward(request, response);
			}
		}
		else if (path.endsWith("deleteItem")) {
			IDAO dao = DAO.GetInstance();
			
			//Creating Variables For Further Use
			String errorMessage = "";
			//Getting Parameters From HTML Form and Parsing Them
			int currentItemId = Integer.parseInt(request.getParameter("ItemIdHidden"));
			System.out.println("here " + currentItemId);
			List<ToDoListItem> items = null;
			try {
				// Removing List Item From Database By ItemId
				dao.RemoveListItem(currentItemId);
				//Selecting All Items after Deleting to Display
				items = dao.GetItemsByUserId(Integer.parseInt(request.getParameter("ItemUsersIdHidden")));
			} catch (ToDoListPlatformException e) {
				e.printStackTrace();
				errorMessage = "Error Deleting Item";
			}
			finally
			{
				//Creating a Server Response to transfer the Result and Errors to the Users end
				ServerResponse servResponse = new ServerResponse(false, errorMessage, null);
				if(items != null)
				{
					servResponse.result = items;
					servResponse.status = true;
				}
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsonView.jsp");
				request.setAttribute("Object", servResponse);
			    rd.forward(request, response);
			}
		}
		else if (path.endsWith("editItem")) {
			IDAO dao = DAO.GetInstance();
			
			//Creating Variables For Further Use
			Date parsedDate = new Date();
			String errorMessage = "";
			List<ToDoListItem> items = null;
			//Getting Parameters From HTML Form and Parsing Them
			int userId = Integer.parseInt(request.getParameter("UserIdHiddenEdit"));
			int itemId = Integer.parseInt(request.getParameter("ItemIdHiddenEdit"));
			System.out.println("here: "+ itemId);
			String itemTitle = request.getParameter("itemTitleEdit");
			String itemText = request.getParameter("itemTextEdit");
			String itemDeadLineEdit = request.getParameter("ItemFinalDateEdit");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				parsedDate = df.parse(itemDeadLineEdit);
				System.out.println("parsedDate  " + parsedDate);
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.println("userId " + userId);
			try {
				Date startDate = df.parse(itemDeadLineEdit);
				System.out.println(startDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			//ToDoListItem itemtoAdd = new ToDoListItem(userId ,itemTitle,itemText, new Date() , parsedDate);
			try {
				dao.EditItem(itemId, itemTitle, itemText, parsedDate);
				items = dao.GetItemsByUserId(userId);
			} catch (ToDoListPlatformException e) {
				e.printStackTrace();
				errorMessage = "Error Adding Item";
			}
			finally
			{
				//Creating a Server Response to transfer the Result and Errors to the Users end
				ServerResponse servResponse = new ServerResponse(false, errorMessage, null);
				if(items != null)
				{
					servResponse.result = items;
					servResponse.status = true;
				}
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/jsonView.jsp");
				request.setAttribute("Object", servResponse);
			    rd.forward(request, response);
			}
		}
	}

}
