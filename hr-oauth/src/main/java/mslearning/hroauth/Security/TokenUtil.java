package mslearning.hroauth.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.util.Objects;

@Component
@RefreshScope
public class TokenUtil {

    @Value("${jwt.header}")
    private String HEADER;
    @Value("${jwt.prefix}")
    private String PREFIX;

    @Value("${jwt.expiration}")
    private long EXPIRATION;

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;
    @Value("${jwt.emissor}")
    private String EMISSOR;

    public String createToken(String userName){
        SecretKey secretKey = getKeyBySecret();

        String token = Jwts.builder()
                .setSubject(userName)
                .setIssuer(this.EMISSOR)
                .setExpiration(new Date(System.currentTimeMillis() + this.EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return this.PREFIX + " " + token;

    }

    private SecretKey getKeyBySecret(){
        return Keys.hmacShaKeyFor(this.SECRET_KEY.getBytes());
    }

}
