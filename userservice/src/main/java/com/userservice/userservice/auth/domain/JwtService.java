package com.userservice.userservice.auth.domain;

import com.userservice.userservice.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class JwtService {
    public String generateToken(Usuario user, Map<String, Object> claims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 8);
        return Jwts.builder()
                .claims().empty().add(claims)
                .subject(user.getCorreo())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .and()
                .header().add("typ", "JWT").and()
                .signWith(generateKey())
                .compact();
    }

    public SecretKey generateKey() {
        byte[] secretAsBytes = Decoders.BASE64.decode("5Rq7K1SXQD2xx/jY0Chyf1LnzBmOqz/R3bENMlAMAws=");
        return Keys.hmacShaKeyFor(secretAsBytes);
    }

    public String extractUsername(String jwt) { //Extra corre. GUardamos correo en vez de  username
        return extractAllClaims(jwt).getSubject();
    }
    public Date extractExp(String jwt) { //Extra corre. GUardamos correo en vez de  username
        return extractAllClaims(jwt).getExpiration();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(jwt).getPayload();
    }

    public ResponseEntity<?> is_token_Expired(String jwt) {
        Date exp = extractExp(jwt);
        Date currentTime = new Date(System.currentTimeMillis());
        Map<String, Object> data = new HashMap<>();
        if(exp.before(currentTime)) {
            data.put("message", "Token ha expirado");
            data.put("expired", true);
            data.put("status", 200);
        }else{
            data.put("message", "Token valido");
            data.put("expired", false);
            data.put("status", 200);
        }
        return ResponseEntity.ok(data);

    }
}
