package nullable.software.views;

import io.dropwizard.views.View;

public class HomeView extends View {
    String contactEmail = "";

    public HomeView(String contactEmail) {
        super("pageHome.mustache");
        this.contactEmail = contactEmail;
    }

    public String getContactEmail() {
        return contactEmail;
    }
}
