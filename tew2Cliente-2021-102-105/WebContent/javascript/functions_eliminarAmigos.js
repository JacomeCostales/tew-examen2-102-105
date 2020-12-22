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
	this.tablaUsuarios = null;
	 this.load = function(emailUsuario) 
	 {
		 console.log(emailUsuario);
		 var email = sessionStorage.getItem("usuario");
		 var token = sessionStorage.getItem("token");
		 this.tablaUsuarios = AmigosServicesRs.getListadoAmigos({email : emailUsuario, N : email, T : token, $contentType : "application/json"});	 
	 }
	 
	this.createAmigo = function(emailUsuario, emailAmigo) 
	{
		var amigo = 
		{
				email_usuario : emailUsuario,
				email_amigo : emailAmigo,
				aceptada : false
		};
	    return amigo;
	}
		
	this.eliminarAmigo = function(amigo) 
	{
		var email = sessionStorage.getItem("usuario");
		var token = sessionStorage.getItem("token");
		AmigosServicesRs.deleteAmigos({$entity : amigo, N : email, T : token, $contentType : "application/json"});
	}
};

function View()
{
	
	this.obtenerEmailAmigo = function(celda) 
	{
		var emailUsuario = String(celda.closest('tr').find('td').get(0).innerHTML);
		var emailAmigo = String(celda.closest('tr').find('td').get(1).innerHTML);
		
		var amigo = 
		{
				email_usuario : emailUsuario,
				email_amigo : emailAmigo,
				aceptada : true
		};
	    return amigo;
		
	}
	
	 this.list = function (lista) 
	 {
		 $("#tablaUsuarios").html("");
		 $("#tablaUsuarios").html( "<thead class='Tabla'>" + "<tr>" + "<th>E-mail usuario</th>" + "<th>E-mail amigo</th>" + "<th>Eliminar</th>" + "</tr>" + "</thead>" + "<tbody>" + "</tbody>");
		 
		 for ( var i in lista) 
		 {
			 var usuario = lista[i];
			 $("#tablaUsuarios tbody").append("<tr>"+ "<td class='filaTabla'>" + usuario.email_usuario + "</td>"  + "<td class='filaTabla'>" + usuario.email_amigo + "</td>"  + "<td class='filaTabla'>" + "<input type='button' value='Agregar Amigo' class='botonAgregarAmigo'/>" + "</td>"  + "</tr>");
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
		 this.view.list(this.model.tablaUsuarios);
		 
		 $("#tablaUsuarios").on("click", ".botonAgregarAmigo", function(event) 
		    {
				var amigos = that.view.obtenerEmailAmigo($(this));
				that.model.eliminarAmigo(amigos);
				that.model.load(sessionStorage.getItem('usuario')); 
				that.view.list(that.model.tablaUsuarios);
			});
		 
		 
		 $(document).ready(function(){
			  $("#textoFiltro").on("keyup", function() 
			   {
			    var value = $(this).val().toLowerCase();
			    $("#tablaUsuarios tbody tr").filter(function() 
			    {
			    	var string_original = $(this).text().toLowerCase();
	                var string_filtrar = string_original.substring(0, string_original.length - 7);
			        $(this).toggle(string_filtrar.indexOf(value) > -1)
			    });
			  });
			});
		 
	 }
};