package nullable.software.views;

import io.dropwizard.views.View;

public class HomeView extends View {
    private String contactEmail;
    private int coinHiveRounds;

    public HomeView(String contactEmail, int coinHiveRounds) {
        super("pageHome.mustache");
        this.contactEmail = contactEmail;
        this.coinHiveRounds = coinHiveRounds;
    }

    public String getContactEmail() {
        return contactEmail;
    }
}
