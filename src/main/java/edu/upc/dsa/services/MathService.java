package edu.upc.dsa.services;

import edu.upc.dsa.MathManager;
import edu.upc.dsa.MathManagerImpl;
import edu.upc.dsa.models.Institute;
import edu.upc.dsa.models.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/Math", description = "MathManager Service")
@Path("/Math")
public class MathService {

    private MathManager mm;

    public MathService() {
        this.mm = MathManagerImpl.getInstance();
        if (mm.size() == 0) {
            this.mm.addOperation("5 1 2 + 4 * + 3 -", "student1", "inst1");
            this.mm.addOperation("10 2 /", "student2", "inst2");
            this.mm.addOperation("2 2 +", "student1", "inst1");
        }
    }

    @POST
    @ApiOperation(value = "Crea una nueva operación", notes = "Añade una operación a la cola")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Operation.class),
            @ApiResponse(code = 500, message = "Error en la operación")
    })
    @Path("/operations")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newOperation(Operation op) {
        if (op.getExpression() == null || op.getStudentId() == null || op.getInstituteId() == null) {
            return Response.status(500).entity(op).build();
        }
        
        this.mm.addOperation(op.getExpression(), op.getStudentId(), op.getInstituteId());
        return Response.status(201).entity(op).build();
    }

    @GET
    @ApiOperation(value = "Procesa operaciones", notes = "Procesa la operación en orden de llegada")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Operation.class),
            @ApiResponse(code = 404, message = "Cola vacia")
    })
    @Path("/Operaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response processOperation() {
        Operation op = this.mm.processNextOperation();
        
        // Adaptado de tu estilo de if/else
        if (op == null) return Response.status(404).build();
        else return Response.status(201).entity(op).build();
    }

    @GET
    @ApiOperation(value = "Operaciones por instituto", notes = "Lista operaciones de un instituto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Operation.class, responseContainer="List")
    })
    @Path("/Institutos/{id}/Operaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOperationsByInstitute(@PathParam("id") String id) {
        List<Operation> operations = this.mm.getOperationsByInstitute(id);

        GenericEntity<List<Operation>> entity = new GenericEntity<List<Operation>>(operations) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Operaciones por alumno", notes = "Lista operaciones de un alumno")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Operation.class, responseContainer="List")
    })
    @Path("/Alumnos/{id}/Operaciones")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOperationsByStudent(@PathParam("id") String id) {
        List<Operation> operations = this.mm.getOperationsByStudent(id);

        GenericEntity<List<Operation>> entity = new GenericEntity<List<Operation>>(operations) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Ranking de institutos", notes = "Lista de institutos ordenados por operaciones")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Institute.class, responseContainer="List")
    })
    @Path("/Institutos/Ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInstitutesRanking() {
        List<Institute> ranking = this.mm.getInstitutesByOperationsDesc();

        GenericEntity<List<Institute>> entity = new GenericEntity<List<Institute>>(ranking) {};
        return Response.status(201).entity(entity).build();
    }
}