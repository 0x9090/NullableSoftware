package nullable.software.resources;

import nullable.software.core.SNS;
import nullable.software.views.HomeView;
import nullable.software.core.CoinHive;

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
    private String snsAccessKey;
    private String snsSecretKey;
    private String snsARN;

    public HomeResource(String contactEmail, String coinHiveKey, int coinHiveRounds, String snsAccessKey,
                        String snsSecretKey, String snsARN) {
        this.contactEmail = contactEmail;
        this.coinHiveKey = coinHiveKey;
        this.coinHiveRounds = coinHiveRounds;
        this.snsAccessKey = snsAccessKey;
        this.snsSecretKey = snsSecretKey;
        this.snsARN = snsARN;
    }

    @GET
    public HomeView homeView() {
        return new HomeView(contactEmail, coinHiveRounds);
    }

    @POST
    @Path("/contact")
    public void contactForm(@FormParam("coinhive-captcha-token") String captchaToken,
                            @FormParam("name") String name,
                            @FormParam("email") String email,
                            @FormParam("message") String message) {
        CoinHive hive = new CoinHive(coinHiveKey, coinHiveRounds, captchaToken);
        if (hive.verify()) {
            SNS sns = new SNS(this.snsAccessKey, this.snsSecretKey, this.snsARN);
            String payload = "Name: " + name + "\nEmail: " + email + "\nMessage: " + message + "\n";
            sns.send(payload);
        }
    }

}