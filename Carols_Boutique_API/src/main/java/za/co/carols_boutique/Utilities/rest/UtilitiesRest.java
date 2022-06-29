package za.co.carols_boutique.Utilities.rest;

import za.co.carols_boutique.Utilities.KeepAsideImp;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import za.co.carols_boutique.Utilities.IDGenerator;
import za.co.carols_boutique.models.KeepAside;

@Path("/utilities")

public class UtilitiesRest {
	
	@POST
	@Path("/createKeepAside")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response startKeepAside(KeepAside keepAside) {
		keepAside.setId(IDGenerator.generateID("ka"));
		KeepAsideImp keep = new KeepAsideImp(keepAside);
		
		return Response.status(Response.Status.OK).entity("Keep Aside addes succesfully. Keep Aside ID is: " + keepAside.getId()).build();		
	}
	
//	@POST
//	@Path("/testKA")
//	@Produces(MediaType.APPLICATION_JSON)
////	@Consumes(MediaType.APPLICATION_JSON)
//	public Response testKA() {
//		KeepAside keepAside = new KeepAside(IDGenerator.generateID("KA"), "str1", new Date(System.currentTimeMillis()), "mustafaaOsman339@gmail.com", new LineItem("li3", IDGenerator.generateID("sa"), new Product("pro6", "Produvt", "Description", 500F, "M"), 1, "M"), new Time(System.currentTimeMillis()));
//		keepAside.getLineItem().setId(IDGenerator.generateID("li"));
//		keepAside.getLineItem().setSaleID(IDGenerator.generateID("sa"));
//		keepAside.getLineItem().setProduct(new Product(keepAside.getLineItem().getId(), "Product"));
//		
//		System.out.println(keepAside.getLineItem().getId());
//		System.out.println(keepAside.getId());
//		System.out.println(keepAside.getStoreID());
//		
//		return Response.status(Response.Status.OK).entity(new KeepAsideImp(keepAside)).build();
//	}	
}
