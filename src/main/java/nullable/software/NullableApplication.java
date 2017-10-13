package nullable.software;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import nullable.software.resources.HomeResource;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.pac4j.core.config.Config;
import org.pac4j.dropwizard.Pac4jBundle;
import org.pac4j.dropwizard.Pac4jFactory;

public class NullableApplication extends Application<NullableConfiguration> {
    final Pac4jBundle<NullableConfiguration> securityBundle = new Pac4jBundle<NullableConfiguration>() {
        @Override
        public Pac4jFactory getPac4jFactory(NullableConfiguration nullableConfiguration) {
            return nullableConfiguration.getPac4jFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new NullableApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<NullableConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/www", "/static"));
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new MultiPartBundle());
        bootstrap.addBundle(securityBundle);
    }

    @Override
    public void run(NullableConfiguration config, Environment environment) throws Exception {
        /* Security Utilities */
        AbstractBinder securityBinder = new AbstractBinder() {
            @Override
            protected void configure() {
                bind(securityBundle.getConfig()).to(Config.class);
            }
        };

        /* Error Handling */
        ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
        errorHandler.addErrorPage(300,403,"/error/");
        errorHandler.addErrorPage(404, "/error/404/");
        errorHandler.addErrorPage(405,599,"/error/");
        //InvalidMethodMapper invalidMethodMapper = new InvalidMethodMapper();

        /* Database Connection */
        //final DBIFactory factory = new DBIFactory();
        //final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "mysql");

		/* Security Filters */
        //CSRFRequestFilter csrfRequestFilter	= new CSRFRequestFilter();
        //CSRFResponseFilter csrfResponseFilter = new CSRFResponseFilter();
        //HeadersResponseFilter headersResponseFilter = new HeadersResponseFilter();

        /* Resources */
        final HomeResource homeResource = new HomeResource("");

        /* Health Checks */


        /* Environment Registration */
        environment.jersey().register(securityBinder);
        environment.getApplicationContext().setErrorHandler(errorHandler);
        //environment.jersey().register(invalidMethodMapper);
        environment.jersey().register(homeResource);
    }
}
