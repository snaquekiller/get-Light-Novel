package getLn.configuration;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import getln.service.common.UserSqlService;

/**
 * Autoconfiguration of oauth server
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${spring.client.id}")
    private String clientId;

    @Value("${spring.client.secret}")
    private String clientSecret;


    private static final String GRANT_TYPE_PASSWORD = "password";
    private static final String AUTHORIZATION_CODE = "authorization_code";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String IMPLICIT = "implicit";
    private  static final String SCOPE_READ = "read";
    private static final String SCOPE_WRITE = "write";
    private static final String TRUST = "trust";
    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 60*60;
    private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private UserSqlService userDetailsService;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("as466gf");
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
            .inMemory()
            .withClient(clientId)
            .secret(clientSecret)
            .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
            .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
            .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
            refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(this.tokenStore())
                .authenticationManager(this.authenticationManager)
                .userDetailsService(this.userDetailsService)
                .accessTokenConverter(accessTokenConverter());
    }
}
