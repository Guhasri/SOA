package com.uok.demo1;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Path("/expenses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpenseResource {
    private static Map<Integer, com.uok.demo1.Expense> expenseMap = new HashMap<>();
    private static int idCounter = 1;

    // List expenses with optional category filter
    @GET
    @Path("/list")
    public List<com.uok.demo1.Expense> getExpenses(@QueryParam("category") String category) {
        if (category == null || category.isEmpty()) {
            return new ArrayList<>(expenseMap.values());
        } else {
            return expenseMap.values().stream()
                    .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }
    }

    // Add a new expense
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addExpense(com.uok.demo1.Expense expense) {
        expense.setId(idCounter++);
        expenseMap.put(expense.getId(), expense);
        return Response.status(Response.Status.CREATED).entity("Added successfully").build();
    }

    // Update an existing expense by ID
    @PUT
    @Path("/update/{id}")
    public Response updateExpense(@PathParam("id") int id, com.uok.demo1.Expense updatedExpense) {
        com.uok.demo1.Expense existingExpense = expenseMap.get(id);
        if (existingExpense == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        updatedExpense.setId(id);
        expenseMap.put(id, updatedExpense);
        return Response.ok(updatedExpense).build();
    }

    // Delete an expense by ID
    @DELETE
    @Path("delete/{id}")
    public Response deleteExpense(@PathParam("id") int id) {
        com.uok.demo1.Expense removedExpense = expenseMap.remove(id);
        if (removedExpense == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    // Get total expenses within a date range
    @GET
    @Path("/total")
    public Response getTotalExpenses(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        Date start = parseDate(startDate);
        Date end = parseDate(endDate);

        if (start == null || end == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid date format").build();
        }

        double total = expenseMap.values().stream()
                .filter(expense -> !expense.getDate().before(start) && !expense.getDate().after(end))
                .mapToDouble(com.uok.demo1.Expense::getAmount)
                .sum();

        return Response.ok(total).build();
    }
    @GET
    @Path("/test")
    public Response test(){
        return Response.ok().entity("Test success").build();
    }

    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}
