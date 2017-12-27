package nullable.software.resources;

import nullable.software.views.HomeView;
import nullable.software.core.CoinHive;
import nullable.software.core.Email;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {
    private String contactEmail;
    private String coinHiveKey;
    private int coinHiveRounds;

    public HomeResource(String contactEmail, String coinHiveKey, int coinHiveRounds) {
        this.contactEmail = contactEmail;
        this.coinHiveKey = coinHiveKey;
        this.coinHiveRounds = coinHiveRounds;
    }

    @GET
    public HomeView homeView() {
        return new HomeView(contactEmail, coinHiveRounds);
    }

    @POST
    @Path("/contact")
    public void contactForm(@FormParam("captchaToken") String captchaToken) {
        CoinHive hive = new CoinHive(coinHiveKey, coinHiveRounds, captchaToken);
        if (hive.verify()) {
            Email.SendMail();
        }
    }

}