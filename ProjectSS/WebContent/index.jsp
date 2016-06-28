<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
 <!-- 
    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
  -->
  	
    <link rel="stylesheet" href="css/jquery.mobile-1.4.5.min.css">
    <link rel="stylesheet" href="css/roman-jacob.css">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/jquery.mobile-1.4.5.min.js"></script>
    <script type="text/javascript" src="js/roman-jacob.js"></script>

</head>
<body>


<div data-role="page" data-theme="b" id="LoginPage">
    <div data-role="header">
        <h1>To Do List App:</h1>
    </div>

    <div data-role="main" class="ui-content">
        <form name="logInForm" id="logInForm" method="post" action="./login" enctype="multipart/form-data">
            <input id="usernameField" name="usernameField" type="text" data-clear-btn="true" placeholder="Username" >
            <input id="passwordField" name="passwordField" type="password" data-clear-btn="true" placeholder="Password"  >
            <a href="#MainPage" class="ui-btn" data-transition="slide" onclick="NotesApp.LoginAttempt()" >Log In</a>
            <a href="#RegisterPage" class="ui-btn" data-transition="slide" >Register</a>
        </form>
        <div id="loginLoadingMessage">
        	<div style="margin: 0 auto; width:20%; height:auto; background-color:black">
        		<img style="width:100%;height:100%;" src="/AndroidAppServerSide/images/loading.gif" />
        	</div>
        </div>
    </div>
    
    <div id="ErrorMessageBox">
        	<div class="errorBoxDiv">
        		<h4 id="errorMessage" style="color:red;" align="center"></h4>
        		<a href="" class="ui-btn" data-transition="" onclick="NotesApp.errorMessageRead()" >OK</a>
        	</div>
        </div>

    <div data-role="footer">
        <h1>All Rights Reserved to J&R Inc.</h1>
    </div>
</div>



<div data-role="page" data-theme="b" id="RegisterPage">
    <div data-role="header">
        <h1>To Do List App:</h1>
    </div>


    <div data-role="main" class="ui-content">
    
    <div data-role="controlgroup" data-type="horizontal" >
            <a href="#LoginPage" class="ui-btn ui-icon-back ui-btn-icon-left" style="background-color:#808080;color:white;text-shadow:0 2px 0 #000000" data-transition="slide" data-direction="reverse">Return</a>
            </div>
    
        <form name="RegisterForm" id="RegisterForm" method="post" action="./register" enctype="multipart/form-data">
            <input id="usernameField" name="usernameField" type="text" data-clear-btn="true" placeholder="Username" >
            <input id="passwordField" name="passwordField" type="password" data-clear-btn="true" placeholder="Password"  >
            <input id="emailField" name="emailField" type="email" data-clear-btn="true" placeholder="Email" >
            <a href="#MainPage" class="ui-btn" data-transition="slide" onclick="NotesApp.RegisterAttempt()" >Register</a>
        </form>
        <div id="loginLoadingMessage">
        	<div style="margin: 0 auto; width:20%; height:auto; background-color:black">
        		<img style="width:100%;height:100%;" src="/AndroidAppServerSide/images/loading.gif" />
        	</div>
        </div>
    </div>
    
    <div id="ErrorMessageBox" >
        	<div class="errorBoxDiv">
        		<h4 id="errorMessage" style="color:red;" align="center"></h4>
        		<a href="" class="ui-btn" data-transition="" onclick="NotesApp.errorMessageRead()" >OK</a>
        	</div>
        </div>

    <div data-role="footer">
        <h1>All Rights Reserved to J&R Inc.</h1>
    </div>
</div>



<div data-role="page" data-theme="b" id="MainPage">
    <div data-role="header">
        <h1 id="userWelcomeHeader" >Welcome User</h1>
    </div>

    <div data-role="main" class="ui-content">
        <div data-role="controlgroup" data-type="horizontal" >
            <a href="#addMemo" class="ui-btn ui-icon-check ui-btn-icon-left ui-shadow ui-corner-all" style="background-color:#808080;color:white;text-shadow:0 2px 0 #000000" data-transition="slide">Add</a>
            <a id="logOutButton" href="#LoginPage" class="ui-btn ui-icon-delete ui-btn-icon-left" style="background-color:#4d0000;color:white;text-shadow:0 2px 0 #000000" data-transition="slide" data-direction="reverse">Log Out</a>
        </div>

        <input type="search" id="searchBar" name="searchBar" id="search-basic" value="" placeholder="Search Memo's" />

        <div id="listPlace" style="width:100%;margin:auto;border-radius: 20px;">

        </div>
        <div id="itemsLoadingMessage">
        	<div style="margin: 0 auto;margin-top:30vh; width:20%; height:auto; background-color:black">
        		<img style="width:100%;height:100%" src="/AndroidAppServerSide/images/loading.gif" />
        	</div>
        </div>
         <div id="ErrorMessageBox" >
        	<div class="errorBoxDiv">
        		<h4 id="errorMessage" style="color:red" align="center"></h4>
        		<a href="" class="ui-btn" data-transition="" onclick="NotesApp.errorMessageRead()" >OK</a>
        	</div>
        </div>

    </div>


    <br>

    <div data-role="footer">
        <h1>All Rights Reserved to J&R Inc.</h1>
    </div>
</div>


<div data-role="page" data-theme="b" id="addMemo">
    <div data-role="header">
        <h1><b>Add</b></h1>
    </div>

    <div data-role="main" class="ui-content">

        <div data-role="controlgroup" data-type="horizontal" >
            <a href="#MainPage" class="ui-btn ui-icon-back ui-btn-icon-left" style="background-color:#808080;color:white;text-shadow:0 2px 0 #000000" data-transition="slide" data-direction="reverse">Return</a>
            <a href="#LoginPage" class="ui-btn ui-icon-delete ui-btn-icon-left" style="background-color:#4d0000;color:white;text-shadow:0 2px 0 #000000" data-transition="slide" data-direction="reverse">Log Out</a>
        </div>

        <form name="addItemForm" id="addItemForm" method="post" action="./addNewItem" enctype="multipart/form-data">
        	<input name="userIdHiddenAdd" id="userIdHiddenAdd" type="hidden" value="">
            <input name="itemTitleAdd" id="itemTitleAdd" type="text" data-clear-btn="true" placeholder="Title" >
            <textarea data-clear-btn="true" placeholder="What to do..." name="itemTextAdd" id="itemTextAdd"></textarea>
            <input type="button" style="background-color:white" value="Clear" onclick="NotesApp.clearAllAddAreas()" >
            <label for="itemFinalDate">Select Deadline Date:</label>
            <input type="date" name="itemFinalDate" id="ItemFinalDate">
            <a href="#MainPage" class="ui-btn ui-icon-check ui-btn-icon-left ui-shadow ui-corner-all" style="background-color:#001a00" data-transition="slide" data-direction="reverse" onclick="NotesApp.AddItemToList()">Add</a>

        </form>

    </div>
    
     <div id="ErrorMessageBox" >
        	<div class="errorBoxDiv">
        		<h4 id="errorMessage" style="color:red" align="center"></h4>
        		<a href="" class="ui-btn" data-transition="" onclick="NotesApp.errorMessageRead()" >OK</a>
        	</div>
        </div>

    <div data-role="footer">
        <h1>All Rights Reserved to J&R Inc.</h1>
    </div>
</div>

<div data-role="page" data-theme="b" id="editMemo">
    <div data-role="header">
        <h1><b>Edit</b></h1>
    </div>

    <div data-role="main" class="ui-content">

        <div data-role="controlgroup" data-type="horizontal" >
            <a href="#MainPage" class="ui-btn ui-icon-back ui-btn-icon-left" style="background-color:#808080;color:white;text-shadow:0 2px 0 #000000" data-transition="slide" data-direction="reverse">Return</a>
            <a href="#LoginPage" class="ui-btn ui-icon-delete ui-btn-icon-left" style="background-color:#4d0000;color:white;text-shadow:0 2px 0 #000000" data-transition="slide" data-direction="reverse">Log Out</a>
        </div>

        <form name="EditItemForm" id="EditItemForm" method="post" action="./editItem" enctype="multipart/form-data">
        	<input name="ItemIdHiddenEdit" id="itemIdHiddenEdit" type="hidden" value="">
        	<input name="UserIdHiddenEdit" id="UserIdHiddenEdit" type="hidden" value="">
            <input name="itemTitleEdit" id="itemTitleEdit" type="text" data-clear-btn="true" placeholder="Title" >
            <textarea data-clear-btn="true" placeholder="What to do..." name="itemTextEdit" id="itemTextEdit"></textarea>
            <input type="button" style="background-color:white" value="Clear" onclick="NotesApp.clearWhatToDoTextArea()" >
            <label for="ItemFinalDateEdit">Select Deadline Date:</label>
            <input type="date" name="ItemFinalDateEdit" id="ItemFinalDateEdit">
            <a href="#MainPage" class="ui-btn ui-icon-check ui-btn-icon-left ui-shadow ui-corner-all" style="background-color:#001a00" data-transition="slide" data-direction="reverse" onclick="NotesApp.EditAttempt()">Edit</a>

        </form>

        <script>

        </script>

    </div>
    
    <div id="ErrorMessageBox" >
        	<div class="errorBoxDiv">
        		<h3 id="errorMessage"></h3>
        		<a href="" class="ui-btn" data-transition="" onclick="NotesApp.errorMessageRead()" >OK</a>
        	</div>
        </div>

    <div data-role="footer">
        <h1>All Rights Reserved to J&R Inc.</h1>
    </div>
</div>


<script>
    //ShowItems();
</script>
</body>
</html>