package com.huayue.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/1/14.
 */
public class JWTUtil {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    private static final String SECRET = "MySecret";
    private static final String ISS = "WHAT";
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.ES256);
    /**
     * 不选择记住，则过期时间为1小时
     */
    private static final long EXPIRATION = 3600L;
    /**
     * 选择记住，则过期时间为7天
     */
    private static final long EXPIRATION_REMEMBER = 604800L;
    public static String createToken(String username, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        return Jwts.builder()
                .signWith(key)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }
    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }
    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }
}
