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


function Model()
{
	this.tablaPeticiones = null;
	 this.load = function(emailUsuario) 
	 {
		 console.log(emailUsuario);
		 var email = sessionStorage.getItem("usuario");
		 var token = sessionStorage.getItem("token");
		 this.tablaPeticiones = UsuariosServicesRs.getListadoPeticiones({email : emailUsuario,N : email, T : token, $contentType : "application/json"});	 
	 }
	 
	 this.obtenerPeticionAceptar = function(lista, celda) 
		{
		    var nombreAmigo = String(celda.closest('tr').find('td').get(0).innerHTML);
		    var emailAmigo = "";
		    for ( var i in lista) 
		    {
		    	var usuario = lista[i];
		    	if(usuario.nombre == nombreAmigo)
		    	{
		    		emailAmigo = usuario.email;
		    	}
		    }
		    console.log(emailAmigo);
		    console.log(nombreAmigo);
			return emailAmigo;
		}
	 
	this.createAmigo = function(emailUsuario, emailAmigo) 
	{
		var amigo = 
		{
				email_usuario : emailAmigo,
				email_amigo : emailUsuario,
				aceptada : true
		};
	    return amigo;
	}
		
	this.aceptarAmigo = function(amigo) 
	{
		var email = sessionStorage.getItem("usuario");
		var token = sessionStorage.getItem("token");
		AmigosServicesRs.updateAmigos({$entity : amigo,N : email, T : token, $contentType : "application/json"});
	}
};

function View()
{
	
	this.obtenerEmailAmigo = function(celda) 
	{
		var emailAmigo = String(celda.closest('tr').find('td').get(0).innerHTML);
		return emailAmigo;
	}
	
	 this.list = function (lista) 
	 {
		 $("#tablaPeticiones").html("");
		 $("#tablaPeticiones").html( "<thead  class='Tabla'>" + "<tr>" + "<th>Nombre</th>" + "<th>Aceptar Amigo</th>" + "</tr>" + "</thead>" + "<tbody>" + "</tbody>");
		 
		 for ( var i in lista) 
		 {
			 var usuario = lista[i];
			 $("#tablaPeticiones tbody").append("<tr>"+ "<td class='filaTabla'>" + usuario.nombre + "</td>" + "<td class='filaTabla'>" + "<input type='button' value='Aceptar Amigo' class='botonAceptarAmigo'/>" + "</td>"  + "</tr>");
		 }
	 } 
};

function Controller(varmodel, varview) 
{
	 var that = this;
	 this.model = varmodel;
	 this.view = varview;
	 this.init = function() 
	 {
		 this.model.load(sessionStorage.getItem('usuario'));
		 this.view.list(this.model.tablaPeticiones);
		 
		 $("#tablaPeticiones").on("click", ".botonAceptarAmigo", function(event) 
		    {
				var emailAmigo = that.model.obtenerPeticionAceptar(that.model.tablaPeticiones, $(this));
				var amigos = that.model.createAmigo(sessionStorage.getItem('usuario'), emailAmigo);
				that.model.aceptarAmigo(amigos);
				that.model.load(sessionStorage.getItem('usuario')); 
				that.view.list(that.model.tablaPeticiones);
			});
	 }
};