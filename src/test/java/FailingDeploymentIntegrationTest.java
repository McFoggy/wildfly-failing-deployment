import javax.enterprise.inject.spi.DefinitionException;
import javax.enterprise.inject.spi.Extension;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.ShouldThrowException;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import fr.brouillard.oss.jee.cdi.DeploymentFailExtension;
import fr.brouillard.oss.jee.control.Echo;
import fr.brouillard.oss.jee.control.FailIfPresent;

public class FailingDeploymentIntegrationTest extends Arquillian {
    @Inject
    Echo e;
    
    @Deployment
    @ShouldThrowException(value = DefinitionException.class, testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "failOnDeployment.war")
                .addPackage("fr.brouillard.oss.jee.cdi")
                .addClasses(Echo.class, FailIfPresent.class)
                .addAsServiceProvider(Extension.class, DeploymentFailExtension.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Test
    public void dummyTest() {
    }
}
