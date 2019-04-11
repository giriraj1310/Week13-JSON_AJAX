function getPerson(url, id){
	if(document.getElementById("person"+id).innerHTML==""){
//		document.getElementById("person"+id).innerHTML="Hello";
		fetch(url+id)
		.then(data => data.json())
		.then(function (data){
			var textToDisplay = "";
			
			// data = HashMap
			// person = key
			// 
			textToDisplay+= "Name: " + data.person.name + " <br/> ";
			textToDisplay+= "Email" + data.person.email + " <br/> ";
			textToDisplay+= "Phone: " + data.person.phone + " <br/> ";
			textToDisplay+= "Address: " + data.person.address + " <br/> ";
			
			document.getElementById("person"+id).innerHTML=textToDisplay;
			
		});
		
	}
	else{
		document.getElementById("person"+id).innerHTML="";
	}
}
