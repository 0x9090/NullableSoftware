package nullable.software;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.pac4j.dropwizard.Pac4jFactory;

import javax.validation.constraints.NotNull;

public class NullableConfiguration extends Configuration {
    @NotNull
    private Pac4jFactory pac4jFactory = new Pac4jFactory();

    @JsonProperty("pac4j") public Pac4jFactory getPac4jFactory() {
        return pac4jFactory;
    }
    @JsonProperty("pac4j") public void setPac4jFactory(Pac4jFactory pac4jFactory) {
        this.pac4jFactory = pac4jFactory;
    }
}
