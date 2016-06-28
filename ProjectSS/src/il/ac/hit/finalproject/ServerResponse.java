package il.ac.hit.finalproject;

/**
 * 
 * @author Roman&Jacob
 *	The Class Represents The Full Server's Response, Including Response Data Status and Error Messages
 */
public class ServerResponse {
	
	// The status of the result True/False
	public boolean status;
	// The errorMessage for the end User
	public String errorMessage;
	// The Json Object result
	public Object result;
	
	public ServerResponse (boolean status, String errorMessage, Object result)
	{
		this.status = status;
		this.errorMessage = errorMessage;
		this.result = result;
	}

}
