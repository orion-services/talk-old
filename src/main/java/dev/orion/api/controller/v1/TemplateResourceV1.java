package dev.orion.api.controller.v1;

import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dev.orion.api.dto.TemplateDTO;
import dev.orion.api.entity.Template;

@Path("/templates")
@Transactional
@ApplicationScoped
public class TemplateResourceV1 {

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createTemplate(TemplateDTO templateDTO) {
    Template template = new Template();
    template.title = templateDTO.title;
    template.msg = templateDTO.msg;
    template.persist();
    return Response.ok(template).build();
  }

  @GET
  @Path("/{uuid}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getTemplateByUUID(@PathParam("uuid") String templateUUID) {
    Template template = Template.findById(UUID.fromString(templateUUID));
    return Response.ok(template).build();
  }

  @GET
  @Path("/all")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllTemplates() {
    List<Template> templatesList = Template.listAll();
    return Response.ok(templatesList).build();
  }

  @PUT
  @Path("/{uuid}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response userFullUpdate(@PathParam("uuid") String templateUUID, TemplateDTO templateDTO) {
    Template template = Template.findById(UUID.fromString(templateUUID));
    if (template == null) {
      throw new NotFoundException();
    }
    template.title = templateDTO.title;
    template.msg = templateDTO.msg;
    return Response.ok(template).build();
  }

  @DELETE
  @Path("/{uuid}")
  public Response deleteUserByUUID(@PathParam("uuid") String templateUUID) {
    Template template = Template.findById(UUID.fromString(templateUUID));
    template.delete();
    return Response.ok().build();
  }

}