package mslearning.hroauth.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import mslearning.hroauth.model.UserModel;
import mslearning.hroauth.request.UserLoginRequest;
import mslearning.hroauth.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SecurityFilter extends UsernamePasswordAuthenticationFilter {

    private TokenUtil tokenUtil;
    private AuthenticationManager authenticationManager;

    private UserService userService;

    public SecurityFilter(AuthenticationManager autManager, TokenUtil tkUtil, UserService userService){
        //setAuthenticationFailureHandler(new AuthenticationFailure());
        authenticationManager = autManager;
        tokenUtil = tkUtil;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        UserLoginRequest userLoginRequest = null;
        try {
            userLoginRequest = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(userLoginRequest.getUserName() == null || userLoginRequest.getPassword() == null){
                throw new BadRequestException("Requisição Inválida!");
            }

            UserModel userModel = this.userService.userFindByEmail(userLoginRequest.getUserName());
            if(Objects.isNull(userModel)){
                throw new RuntimeException("Credenciais Imvalida!");
            }


            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userModel.getUsername(), userLoginRequest.getPassword(),
                    new ArrayList<>());

            Authentication authentication = this.authenticationManager.authenticate(authToken);

            return authentication;

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) throws ServletException, IOException {
        UserModel userModel = (UserModel) authentication.getPrincipal();

        String userName = userModel.getUsername();

        String token = this.tokenUtil.createToken(userName);

        response.addHeader("Authorization", token);
        response.addHeader("access-control-expose-headers", "Authorization");
    }

}
