package za.co.carols_boutique.StoreBE.REST;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import za.co.carols_boutique.StoreBE.ServiceStore.StoreService;
import za.co.carols_boutique.StoreBE.ServiceStore.StoreServiceImp;
import za.co.carols_boutique.models.Sale;
import za.co.carols_boutique.models.Store;

@Path("/store")
public class RestEndpointStore {

	private StoreService service = new StoreServiceImp();

	@POST
	@Path("/loginStore")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(Store store) {
		return Response.status(Response.Status.OK).entity(service.loginStore(store.getId(), store.getPassword())).build();
	}

	@POST
	@Path("/registerStore")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(Store store) {
		return Response.status(Response.Status.OK).entity(service.registerStore(store)).build();
	}

	@POST
	@Path("/addSale")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSale(Sale sale) {
		return Response.status(Response.Status.OK).entity(service.addSale(sale)).build();
	}

	@GET
	@Path("/deleteStore/{storeID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("storeID") String storeID) {
		return Response.status(Response.Status.OK).entity(service.deleteStore(storeID)).build();
	}
        
        @GET
        @Path("/getSale/{saleID}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getSale(@PathParam("saleID") String saleID){
            return Response.status(Response.Status.OK).entity(service.getSale(saleID)).build();
        }
	
	@POST
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Store test() {
		return new Store("ID", "PASSWORD");
	}
}
