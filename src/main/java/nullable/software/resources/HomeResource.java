package nullable.software.resources;

import nullable.software.views.HomeView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {
    private String contactEmail = "";

    public HomeResource(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @GET
    public HomeView homeView() {
        return new HomeView(contactEmail);
    }
}
