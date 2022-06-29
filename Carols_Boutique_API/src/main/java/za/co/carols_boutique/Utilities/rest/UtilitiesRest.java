package za.co.carols_boutique.Utilities.rest;

import KeepAsideStuff.KeepAsideImp;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import za.co.carols_boutique.models.KeepAside;


@Path("/utilities")

public class UtilitiesRest {
	
	@POST
	@Path("/createKeepAside")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response startKeepAside(KeepAside keepAside) {
		new KeepAsideImp(keepAside);
		return Response.status(Response.Status.OK).entity("Keep Aside addes succesfully").build();
	}	
}
