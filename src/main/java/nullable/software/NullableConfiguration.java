package nullable.software;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;
import org.pac4j.dropwizard.Pac4jFactory;

import javax.validation.constraints.NotNull;

public class NullableConfiguration extends Configuration {
    @NotEmpty private String contactEmail;
    @NotEmpty private String smtpHost;
    @NotEmpty private String smtpUser;
    @NotEmpty private String smtpPass;
    @NotEmpty private String coinHiveKey;
    @NotEmpty private String coinHiveRounds;

    @JsonProperty public String getContactEmail() {
        return contactEmail;
    }
    @JsonProperty public String getSmtpHost() {
        return smtpHost;
    }
    @JsonProperty public String getSmtpUser() {
        return smtpUser;
    }
    @JsonProperty public String getSmtpPass() {
        return smtpPass;
    }
    @JsonProperty public String getCoinHiveKey() {
        return coinHiveKey;
    }
    @JsonProperty public int getCoinHiveRounds() {
        return Integer.parseInt(coinHiveRounds);
    }

    @NotNull private Pac4jFactory pac4jFactory = new Pac4jFactory();

    @JsonProperty("pac4j") public Pac4jFactory getPac4jFactory() {
        return pac4jFactory;
    }
    @JsonProperty("pac4j") public void setPac4jFactory(Pac4jFactory pac4jFactory) {
        this.pac4jFactory = pac4jFactory;
    }
}
