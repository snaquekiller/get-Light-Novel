package getLn.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import getLn.GetLnJob;
import getLn.controller.ApiControllerInterface;
import getLn.service.ApiService;
import getln.data.LnData;
import getln.service.common.SqlService;

//@formatter:off
@Configuration
@Import({JpaConfiguration.class, NukeServletApiConfiguration.class
})
@ComponentScan(basePackageClasses = {
        GetLnJob.class,
        ApiControllerInterface.class,
        SqlService.class
})
@LnData
@SqlService
@ApiService
//@formatter:on
public class GetLnConfiguration {

}
