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
function Model()
{
	
	 this.add = function(publicacion) 
	 {	 
		 var email = sessionStorage.getItem("usuario");
		 var token = sessionStorage.getItem("token");
		 PublicacionesServicesRs.savePublicacion({$entity : publicacion, N : email, T : token,$contentType : "application/json"});
		 this.load();
	 }
	
	
};

function View(){
	 
	 
	 this.loadPublicacionFromForm = function () {
		 
		 var fecha_actual = (Date.now())/1000;
		 var id_asig = Math.floor(Math.random() * 1000000); 
		 var publicacion = {
				 id : id_asig,
				 email : sessionStorage.getItem('usuario'),
				 titulo : $("#titulo").val(),
				 texto : $("#texto").val(),
				 fecha : fecha_actual
				 };
		 return publicacion;
		 
	 } 
	 
	 
};

function Controller(varmodel, varview) {
	 // Definimos una copia de this para evitar el conflicto con el this (del
	 // objeto que recibe el evento)
	 // en los manejadores de eventos
	 var that = this;
	 // referencia al modelo
	 this.model = varmodel;
	 // refefencia a la vista
	 this.view = varview;
	 
	 // Funcion de inicialización para cargar modelo y vista, definición de manejadores
	 this.init = function() {
		
		 
		// Manejador del botón submit del formulario de Alta y Edición
		 $("#frmPublicar").bind("submit",
			 
			 function(event) {
			 
			 publicacion = that.view.loadPublicacionFromForm();
			
			that.model.add(publicacion);
			 
			 
			
		}); 

	 }
	}; 