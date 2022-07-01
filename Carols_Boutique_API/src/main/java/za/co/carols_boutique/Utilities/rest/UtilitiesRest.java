package za.co.carols_boutique.Utilities.rest;

import za.co.carols_boutique.Utilities.KeepAsideImp;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import za.co.carols_boutique.Utilities.IBTImp;
import za.co.carols_boutique.Utilities.IDGenerator;
import za.co.carols_boutique.models.IBT;
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

		return Response.status(Response.Status.OK).entity("Keep Aside added succesfully. Keep Aside ID is: " + keepAside.getId()).build();
	}

	@POST
	@Path("/createIBT")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createIBT(IBT ibt) {
		IBTImp ibtImp = new IBTImp(ibt);
		ibtImp.createIBT();
		
		return Response.status(Response.Status.OK).entity("IBT added succesfully. IBT ID is: " + ibt.getId()).build();

	}
}
