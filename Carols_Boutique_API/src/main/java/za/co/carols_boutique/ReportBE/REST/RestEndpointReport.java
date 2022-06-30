package za.co.carols_boutique.ReportBE.REST;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Date;
import za.co.carols_boutique.ReportBE.ServiceReport.RepService;
import za.co.carols_boutique.ReportBE.ServiceReport.RepServiceImp;
import za.co.carols_boutique.Utilities.Email;
import za.co.carols_boutique.models.Customer;
import za.co.carols_boutique.models.Employee;
import za.co.carols_boutique.models.Report;
import za.co.carols_boutique.models.Review;

@Path("/report")
public class RestEndpointReport {
    
    private RepService service = new RepServiceImp();

    @GET
    @Path("/viewTopAchievingStores/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Report viewTopAchievingStores(@PathParam("month")String month){
        return service.viewTopAchievingStores(month);
    }
    
    @GET
    @Path("/getCustomerReviews/{month}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Report getCustomerReviews(@PathParam("month")String month,@PathParam("amount")String amount){
        return service.getCustomerReviews(month,Integer.parseInt(amount));
    }
    
    @GET
    @Path("/viewMonthlySales/{storeID}/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Report viewMonthlySales(@PathParam("storeID")String storeID, @PathParam("month")String month){
        return service.viewMonthlySales(storeID,month);
    }
    
    @GET
    @Path("/viewTopSellingEmployees/{storeID}/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Report viewTopSellingEmployees(@PathParam("storeID")String storeID, @PathParam("month")String month){
        return service.viewTopSellingEmployees(storeID,month);
    }
    
    @GET
    @Path("/viewStoresThatAchievedTarget/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Report viewStoresThatAchievedTarget(@PathParam("month")String month){
        return service.viewStoresThatAchievedTarget(month);
    }
    
    @GET
    @Path("/viewTopSellingProducts/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Report viewTopSellingProducts(@PathParam("month")String month){
        return service.viewTopSellingProducts(month);
    }
    
    @GET
    @Path("/viewLeastPerformingStores/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Report viewLeastPerformingStores(@PathParam("month")String month){
          
        return service.viewLeastPerformingStores(month);
         
    }
    
    @GET
    @Path("/viewProductReport/{productID}/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Report viewProductReport(@PathParam("productID")String productID, @PathParam("month")String month){
        return service.viewProductReport(productID,month);
    }
    
    @GET
    @Path("/viewDailySalesReport/{storeID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Report viewSalesReport(@PathParam("storeID")String storeID){
        return service.viewDailySalesReport(storeID);
    }
    
    @POST
    @Path("/addReview")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReview(Review review){
        return Response.status(Response.Status.OK).entity(service.addReview(review)).build();
    }
    
    @POST
    @Path("/addCustomer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer){
        return Response.status(Response.Status.OK).entity(service.addCustomer(customer)).build();
    }

//	@GET
//	@Path("/testing")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Employee viewProductReport() {
//		Date date = new Date(System.currentTimeMillis());
//		Email email = new Email("newsLetterPromotion", "jomarvn@gmail.com", "Johannes", date);
//		return new Employee();
//	}
}
