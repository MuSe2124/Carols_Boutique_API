package za.co.carols_boutique.Utilities.rest;

import za.co.carols_boutique.Utilities.KeepAsideImp;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import za.co.carols_boutique.Utilities.IBTImp;
import za.co.carols_boutique.Utilities.IDGenerator;
import za.co.carols_boutique.models.IBT;
import za.co.carols_boutique.models.KeepAside;
import za.co.carols_boutique.models.Store_Product;

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

	@GET
	@Path("/createIBT/{prodID}/{storeID}/{amount}/{phone}/{size}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createIBT(@PathParam("prodID") String prodID, @PathParam("storeID") String storeID, @PathParam("amount") Integer amount, @PathParam("phone") String phone, @PathParam("size") String size) {
		IBT ibt = new IBT();
		ibt.setProductID(prodID);
		ibt.setAmount(amount);
		ibt.setCustomerEmail(phone);
		ibt.setStoreID(storeID);
		ibt.setSize(size);
		IBTImp ibtImp = new IBTImp();
		ibtImp.createIBT(ibt);

		return Response.status(Response.Status.OK).entity("IBT added succesfully. IBT ID is: " + ibt.getId()).build();
	}

	@GET
	@Path("/getIBTs/{storeID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<IBT> getIBTs(@PathParam("storeID") String storeID) {
		IBTImp ibtImp = new IBTImp();
		return ibtImp.getIBT(storeID);
	}

	@GET
	@Path("/getStoreProducts/{prodID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Store_Product> getStoreProducts(@PathParam("prodID") String prodCode) {
		IBTImp ibtImp = new IBTImp();
		return ibtImp.getProdStores(prodCode);
	}

	@GET
	@Path("/deleteIBT/{ibtID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteIBT(@PathParam("ibtID") String ibtID) {
		IBTImp ibtImp = new IBTImp();
		Boolean b = ibtImp.removeIBT(ibtID);
		return Response.status(Response.Status.OK).entity("IBT added successfully").build();
	}
}
