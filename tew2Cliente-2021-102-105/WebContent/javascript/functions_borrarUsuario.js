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
	this.listUsuarios = null;
	
	// Carga los datos del servicio sobreescribiendo el dato this.tbAlumnos.
	 this.load = function() 
	 {
		 var email = sessionStorage.getItem("usuario");
		 var token = sessionStorage.getItem("token");
		 this.listUsuarios = UsuariosServicesRs.getUsuarios({N : email, T : token});
	 }
	 
	 
	 
	// Eliminación un alumno existente
	 this.remove = function() {
		
		 var noBorrados = "";
		 $("input:checkbox").each(function() {
			    
			 if($(this).prop('checked'))
			 {
				 var email = sessionStorage.getItem("usuario");
				 var token = sessionStorage.getItem("token");
				 AdministradorServicesRs.borrarUsarios({a: [$(this).val()],N : email, T : token,$contentType : "application/json"});
				 
				 var u = LoginServicesRs.compruebaExiste({emailRegistrado:$(this).val(),N : email, T : token,$contentType : "application/json"});
				 console.log(u);
				 if(u){noBorrados+=u.email+"\n";}
			 };
			 
			   });
		 

		 // Recargamos la lista de alumnos.
		 this.load();
		 if(noBorrados != ""){
			 alert("Hay usuarios con la sesion abierta, no se les puede borrar\n"+noBorrados);
		 }
	 }
	
	
};
//Clase que contiene la gestión de la capa Vista
function View(){
	 this.list = function (lista) {
		 
		 $("#listaUsuarios").empty()
		 
		 for ( var i in lista) {
			 var usuario = lista[i];
			 if(usuario.rol!="admin"){
				 $("#listaUsuarios").append("<div class='usuarioBorrar'><input type='checkbox' id='usuario"+i+"' name='usuario"+i+"' value="+usuario.email+"><label for='usuario"+i+"'> "+usuario.email+"</label><br></div>");
			 }
		 }
	 }
	 
	 

};

function Controller(varmodel, varview) {
	 
	 var that = this;
	 
	 this.model = varmodel;
	 
	 this.view = varview;
	  
	 this.init = function() {
		 
		 this.model.load();
		 
		 this.view.list(this.model.listUsuarios);
		 

		 $("#btnBorrar").click(
				
				function() {
					that.model.remove();
					that.model.load();
					
					that.view.list(that.model.listUsuarios);
				});

	 }
	}; 