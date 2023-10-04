package mslearning.hrapigatway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Objects;

@Service
@RefreshScope
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;
    @Value("${jwt.prefix}")
    private String PREFIX;

    private SecretKey getKeyBySecret(){
        return Keys.hmacShaKeyFor(this.SECRET_KEY.getBytes());
    }

    private Claims getClaims(String token){
        SecretKey key = getKeyBySecret();
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build().parseClaimsJws(token).getBody();
        }catch (Exception e){
            return null;
        }
    }

    public boolean isValidToken(String token){
        token = token.replace(this.PREFIX + " ", "");
        Claims claims = getClaims(token);

        if(Objects.nonNull(claims)){
            String userName = claims.getSubject();
            Date expirarionDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if(Objects.nonNull(userName) && Objects.nonNull(expirarionDate) && now.before(expirarionDate)){
                return true;
            }
        }
        return false;
    }

    public String getUserName(String token){
        Claims claims = getClaims(token);
        if(Objects.nonNull(claims)){
            return claims.getSubject();
        }
        return null;
    }


}
