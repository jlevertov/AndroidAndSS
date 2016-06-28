 var NotesApp = {};
    
    	var userItemList;
    	$body = $("body");
    	$(document).on({
    	    ajaxStart: function() { $body.addClass("loading");    },
    	     ajaxStop: function() { $body.removeClass("loading"); }    
    	});

    	NotesApp.clearWhatToDoTextArea = function ()
    	{
    		document.getElementById("itemTextEdit").value = "";
            document.getElementById("itemTitleEdit").value = "";
    	}
    	
    	NotesApp.clearAllAddAreas = function ()
    	{
    		document.getElementById("itemTextAdd").value = "";
            document.getElementById("itemTitleAdd").value = "";
    	}

    	NotesApp.ShowItems = function (toDoListItemList)
    	{
    		var place = document.getElementById("listPlace");
            place.innerHTML = "";
            for(var i=0; i < toDoListItemList.length; i++)
            {

                var newhtml = "";
                newhtml += "<div id='itemDiv_"+i+"' style='width:100%;background-color:#ffffff;border-radius:20px'>";
                newhtml += "<div style='width:100%;background-color:#1a1a1a;border-top-right-radius:20px;'>";
                newhtml += "<form name='ItemForm_"+i+"' id='ItemForm_"+i+"' method='post' action='' enctype='multipart/form-data'>";
                newhtml += "<input type='hidden' name='ItemUsersIdHidden' id='ItemUsersIdHidden' value='"+toDoListItemList[i].UserId+"' />";
                newhtml += "<input type='hidden' name='ItemIdHidden' id='ItemIdHidden' value='"+toDoListItemList[i].Id+"' />";
                newhtml += "<input type='hidden' name='ItemIdHidden_"+i+"' id='ItemIdHidden_"+i+"' value='"+toDoListItemList[i].Id+"' />";
                newhtml += "<input type='hidden' name='ItemUsersIdHidden_"+i+"' id='ItemUsersIdHidden_"+i+"' value='"+toDoListItemList[i].UserId+"' />";
                newhtml += "<p class='textNegative' id='itemDate_"+i+"' style='margin-left:10px;padding-top:10px;'>" + toDoListItemList[i].DeadLine + "</p>";
                newhtml += "<h3 id='itemTitle_"+i+"' class='textNegative' style='margin-left:10px;'>" + toDoListItemList[i].Title + "</h3>";
                newhtml += "</div>";
                newhtml += "<div style='width:100%;border-bottom-left-radius:20px;'>";
                newhtml += "<p class='textNormal' id='itemText_"+i+"' style='margin-left:10px'>" + toDoListItemList[i].WhatToDo + "</p>";
                newhtml += "</div>";
                newhtml += "<div style='width:100%;border-bottom-left-radius:20px;background-color:white'>";
                newhtml += "<p class='textNormal' style='margin-left:10px'><b>Created on: " + toDoListItemList[i].CreationDate + "</b></p></div>";
                newhtml += "<a href='#editMemo' style='border-radius:20px;margin-left:10px;margin-right:10px;background-color:#404040' class='ui-btn' onclick='NotesApp.OpenEditItem("+i+")' data-transition='slide'>Edit</a>";
                newhtml += "<a style='border-radius:20px;margin-left:10px;margin-right:10px;background-color:#800000' class='ui-btn' onclick='NotesApp.DeleteItem("+i+")'>Delete</a>";
                newhtml += "</form>";
                newhtml += "<br></div>"
                place.innerHTML += newhtml;
            }
            place.innerHTML += "<br><br>";
            NotesApp.StopShowLoading();
    	}
        
    	NotesApp.DeleteItem = function(index)
    	{
    		var selectedForm = document.getElementById("ItemForm_" + index);
        	selectedForm.action = "./deleteItem";
            NotesApp.AjaxManager(selectedForm, "NotesApp.checkResultShowItems");
    	}

    	
    	NotesApp.OpenEditItem = function(index)
    	{
    		var monthArrText = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
            document.getElementById("itemTitleEdit").value = document.getElementById("itemTitle_"+index).innerHTML;
            document.getElementById("itemTextEdit").value = document.getElementById("itemText_"+index).innerHTML;
            document.getElementById("itemIdHiddenEdit").value = document.getElementById("ItemIdHidden_"+index).value;
            document.getElementById("UserIdHiddenEdit").value = document.getElementById("ItemUsersIdHidden_"+index).value;

            var Unformattedline = document.getElementById("itemDate_"+index).innerHTML;
            var deadlineDD = Unformattedline.slice(4,6);
            var deadlineMM = Unformattedline.slice(0,3);
            var monthIndex = monthArrText.indexOf(deadlineMM);
            var leadingZero = "0";
            deadlineMM = (leadingZero+(monthIndex+1).toString());
            var deadlineYYYY = Unformattedline.slice(7,12);
            var dateString = deadlineYYYY + "-" + deadlineMM + "-" + deadlineDD;
            
            document.getElementById("ItemFinalDateEdit").value = dateString.trim();
    	}


		NotesApp.AddItemToList = function()
		{
			NotesApp.ShowLoading();
        	var Form = document.getElementById('addItemForm');
        	setTimeout(function(){ NotesApp.AjaxManager(Form, "NotesApp.checkResultShowItems") } , 2000);
		}

        
        NotesApp.ShowLoading = function()
		{
        	document.getElementById("itemsLoadingMessage").style.visibility = "visible";
		}

        
        NotesApp.StopShowLoading = function()
		{
        	document.getElementById("itemsLoadingMessage").style.visibility = "hidden";
		}

        
        NotesApp.LoginAttempt = function()
		{
        	document.getElementById("listPlace").innerHTML = "";
        	document.getElementById("userWelcomeHeader").innerHTML = "Welcome";
        	NotesApp.ShowLoading();
        	var Form = document.getElementById('logInForm');
        	setTimeout(function(){ NotesApp.AjaxManager(Form, "NotesApp.LetInUser") } , 2000);
		}
        
        NotesApp.RegisterAttempt = function()
		{
        	document.getElementById("listPlace").innerHTML = "";
        	document.getElementById("userWelcomeHeader").innerHTML = "Welcome";
        	NotesApp.ShowLoading();
        	var Form = document.getElementById('RegisterForm');
        	setTimeout(function(){ NotesApp.AjaxManager(Form, "NotesApp.LetInUser") } , 2000);
		}

        
        NotesApp.checkResultShowItems = function(serverResponse)
        {
        	if(serverResponse.status == true)
        		{
        			NotesApp.ShowItems(serverResponse.result);
        			if(serverResponse.errorMessage != "")
        				{        					NotesApp.errorMessagePop(serverResponse.errorMessage);

        				}
        		}
        	else
        		{
        			NotesApp.errorMessagePop(serverResponse.errorMessage);
        		}
        }
        
        NotesApp.errorMessageRead = function()
        {
        	document.getElementById("ErrorMessageBox").style.visibility = "hidden";
        }
        NotesApp.errorMessagePop = function(errorMessage)
        {
        	document.getElementById("ErrorMessageBox").style.visibility = "visible";
        	document.getElementById("errorMessage").innerHTML = errorMessage;
        }
        
        NotesApp.LetInUser = function(serverResponse)
        {
        	if(serverResponse.status == true)
        		{
	        		document.getElementById("userIdHiddenAdd").value = serverResponse.result.id;
	    			document.getElementById("userWelcomeHeader").innerHTML = "Welcome "+ serverResponse.result.Username;
	    			document.getElementById("userIdHiddenAdd").value = serverResponse.result.id;
	    			var form = document.createElement("form");
	    			form.setAttribute("type", "hidden");
	    			form.setAttribute("method", "post");
	    			form.setAttribute("action", "./loadItems");
	    			form.innerHTML = "<input name='userId' id='userId' type='text' value='"+serverResponse.result.id+"'/>";
	    			NotesApp.AjaxManager(form, "NotesApp.checkResultShowItems");
        		}
        	else
        		{
        			NotesApp.errorMessagePop(serverResponse.errorMessage);
        			document.getElementById("logOutButton").click();
        			NotesApp.StopShowLoading();
        		}
        }
        
        NotesApp.AjaxManager = function(FormObject , ResultFunction)
		{
        	var xmlhttp = new XMLHttpRequest();
            xmlhttp.open(FormObject.method, FormObject.action, true);
            xmlhttp.onreadystatechange = function ()
            {
                if (xmlhttp.readyState == 4)
                {
                    if (xmlhttp.status == 200)
                    {
                        eval(ResultFunction + "(" + xmlhttp.responseText + ")");
                    }
                    else
                    {
                    	
                    }
                    xmlhttp = null;
                }

            }
            xmlhttp.send(new FormData(FormObject));
		}
        
        NotesApp.EditAttempt = function()
		{
        	NotesApp.ShowLoading();
        	var Form = document.getElementById('EditItemForm');
        	setTimeout(function(){ NotesApp.AjaxManager(Form, "NotesApp.checkResultShowItems") } , 2000);
		}
