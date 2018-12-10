package getLn.configuration;

import getLn.GetLnJob;
import getLn.controller.ApiControllerInterface;
import getLn.service.ApiService;
import getln.data.LnData;
import getln.service.common.Service;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@formatter:off
@Configuration
@Import({JpaConfiguration.class, NukeServletApiConfiguration.class
})
@ComponentScan(basePackageClasses = {
        GetLnJob.class,
        LnData.class,
        ApiControllerInterface.class,
        Service.class,
        ApiService.class
})
//@formatter:on
public class GetLnConfiguration {

}
