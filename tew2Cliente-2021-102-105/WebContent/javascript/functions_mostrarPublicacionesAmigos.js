$(function() 
		{
	if(sessionStorage.getItem('token')!=null && sessionStorage.getItem('usuario')!=null)
	{
		var model = new Model();
		var view = new View();
		var control = new Controller(model, view);
		control.init();
	}
	else
	{
		alert("Usted no se ha autenticado");
		window.location.href="index.html";
	}
		}); 


//Clase que contiene el Modelo de la aplicación.
function Model(){
	//Lista de alumnos.
	this.tbPublicaciones = null;
	
	// Carga los datos del servicio sobreescribiendo el dato this.tbAlumnos.
	 this.load = function(email_user) 
	 {
		 var email = sessionStorage.getItem("usuario");
		 var token = sessionStorage.getItem("token");
		 this.tbPublicaciones = PublicacionesServicesRs.getPublicacionesAmigos({email : email_user, N : email, T : token,$contentType : "application/json"});
		 
	 }
	 

	
};
//Clase que contiene la gestión de la capa Vista
function View(){
	 this.list = function (lista) {
		 
		 
		 for ( var i in lista) {
			 var publicacion = lista[i];
			 var d = new Date(0); 
			 d.setUTCSeconds(publicacion.fecha_format);
			 $("#listaPublicacion").append("<div class='publicacion'>" +
				 		"<h4 class='titulo-publicacion'>"+publicacion.titulo+"</h2>" +
				 		"<p class='contenido-publicacion'> "+publicacion.texto+"</p>" +
				 		"<p class='fecha-publicacion'>"+d.getDate()+"/"+d.getMonth()+"/"+d.getFullYear()+"</p></div>");
		 }
	 } 
	
	 
	 
};

function Controller(varmodel, varview) {
	 
	 var that = this;
	 
	 this.model = varmodel;
	 
	 this.view = varview;
	 
	 // Funcion de inicialización para cargar modelo y vista, definición de manejadores
	 this.init = function() {
		 
		 this.model.load(sessionStorage.getItem("usuario"));
		 
		 this.view.list(this.model.tbPublicaciones);

		 
		 
	 }
	 
	 
	 
	 
	}; 