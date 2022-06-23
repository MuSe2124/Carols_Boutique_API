/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import za.co.carols_boutique.models.Review;



/**
 *
 * @author Jomar
 */
@Path("/report")
public class RestEndpointReport {
    
    private RepService service = new RepServiceImp();
    

    @GET
    @Path("/viewTopAchievingStores/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewTopAchievingStores(@PathParam("month")String month){
        return Response.status(Response.Status.OK).entity(service.viewTopAchievingStores(month)).build();
    }
    
    @GET
    @Path("/getCustomerReviews/{month}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerReviews(@PathParam("month")String month,@PathParam("amount")String amount){
        return Response.status(Response.Status.OK).entity(service.getCustomerReviews(month,Integer.parseInt(amount))).build();
    }
    
    @GET
    @Path("/viewMonthlySales/{storeID}/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewMonthlySales(@PathParam("storeID")String storeID, @PathParam("month")String month){
		System.out.println(month);
		System.out.println(storeID);
        return Response.status(Response.Status.OK).entity(service.viewMonthlySales(storeID,month)).build();
    }
    
    @GET
    @Path("/viewTopSellingEmployees/{storeID}/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewTopSellingEmployees(@PathParam("storeID")String storeID, @PathParam("month")String month){
        return Response.status(Response.Status.OK).entity(service.viewTopSellingEmployees(storeID,month)).build();
    }
    
    @GET
    @Path("/viewStoresThatAchievedTarget/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewStoresThatAchievedTarget(@PathParam("month")String month){
        return Response.status(Response.Status.OK).entity(service.viewStoresThatAchievedTarget(month)).build();
    }
    
    @GET
    @Path("/viewTopSellingProducts/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewTopSellingProducts(@PathParam("month")String month){
        return Response.status(Response.Status.OK).entity(service.viewTopSellingProducts(month)).build();
    }
    
    @GET
    @Path("/viewLeastPerformingStores/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewLeastPerformingStores(@PathParam("month")String month){
        Response response = null;  
            response = Response.status(Response.Status.OK).entity(service.viewLeastPerformingStores(month)).build();
        return response;
    }
    
    @GET
    @Path("/viewProductReport/{productID}/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewProductReport(@PathParam("productID")String productID, @PathParam("month")String month){
        return Response.status(Response.Status.OK).entity(service.viewProductReport(productID,month)).build();
    }
    
    @GET
    @Path("/viewDailySalesReport/{storeID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewSalesReport(@PathParam("storeID")String storeID){
        return Response.status(Response.Status.OK).entity(service.viewDailySalesReport(storeID)).build();
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
	
	@GET
    @Path("/testing")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee viewProductReport(){
		Date date = new Date(System.currentTimeMillis());
		Email email = new Email("newsLetterPromotion","jomarvn@gmail.com","Johannes",date);
        return new Employee();
    }
}
