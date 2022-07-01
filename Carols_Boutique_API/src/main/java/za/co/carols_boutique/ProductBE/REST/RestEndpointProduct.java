package za.co.carols_boutique.ProductBE.REST;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import za.co.carols_boutique.ProductBE.ServiceProduct.ProdService;
import za.co.carols_boutique.ProductBE.ServiceProduct.ProdServiceImp;
import za.co.carols_boutique.models.Category;
import za.co.carols_boutique.models.ProdCat;
import za.co.carols_boutique.models.Product;
import za.co.carols_boutique.models.Refund;
import za.co.carols_boutique.models.Stock;

@Path("/product")
public class RestEndpointProduct {
    
    private ProdService service = new ProdServiceImp();
    
    @GET
    @Path("/getProduct/{productID}/{sizeID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct(@PathParam("productID")String productID, @PathParam("sizeID")String sizeID){
        return service.getProduct(productID, sizeID);
    }
    
    @POST
    @Path("/addProductToInventory")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response addProductToInventory(Stock stock){
        return Response.status(Response.Status.OK).entity(service.addProductToInventory(stock.getStoreID(), stock.getProductID(), stock.getEmployeeID(), stock.getAmount(),stock.getSizeID())).build();
    }
    
    @POST
    @Path("/addNewProduct")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response addNewProduct(ProdCat prodCat){
        return Response.status(Response.Status.OK).entity(service.addNewProduct(prodCat.getProduct(), prodCat.getCatID())).build();
    }
    
    @POST
    @Path("/removeProductFromInventory")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response removeProductFromInventory(Stock stock){
        return Response.status(Response.Status.OK).entity(service.removeProductFromInventory(stock.getStoreID(), stock.getProductID(), stock.getEmployeeID(), stock.getAmount(), stock.getSizeID())).build();
    }
    
    @DELETE
    @Path("/deleteProduct/{productID}/{catID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("productID")String productID, @PathParam("catID")String catID){
        return Response.status(Response.Status.OK).entity(service.deleteProduct(productID, catID)).build();
    }
//    

	@POST
	@Path("/refund")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response refund(Refund refund) {
		return Response.status(Response.Status.OK).entity(service.refund(refund)).build();
	}
	
	@GET
	@Path("/getCategories")
	@Produces (MediaType.APPLICATION_JSON)
	public ArrayList<Category> getCategories(){
		return service.getCategories();
	}

}
