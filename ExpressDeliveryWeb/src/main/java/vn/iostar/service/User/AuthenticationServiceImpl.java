package vn.iostar.service.User;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.iostar.service.IAuthenService;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements IAuthenService {

    private static final String SIGN_KEY = "llOLLAGjN8hjLQTCefRomDKTGzzBxZISXiNKTgt9LNai5o1gHSCkFr9uR5cmrNFJ";
    private static final String ISSUER = "ducdung";
    private static final long TOKEN_EXPIRATION_HOURS = 6L; // Token expires in 1 hour
    @Override
    public String generateToken(String email, String roles) {
        try {
            // Create JWT claims
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(email)
                    .issuer(ISSUER)
                    .issueTime(new Date())
                    .expirationTime(Date.from(Instant.now().plus(TOKEN_EXPIRATION_HOURS, ChronoUnit.HOURS)))
                    .claim("scope", roles) // Example custom claim
                    .build();
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
            SignedJWT signedJWT = new SignedJWT(header, claims);
            signedJWT.sign(new MACSigner(SIGN_KEY.getBytes(StandardCharsets.UTF_8)));
            return signedJWT.serialize();
        } catch (JOSEException e) {
            log.error("Error generating JWT token", e);
            throw new RuntimeException("Error generating JWT token", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            JWSVerifier jwsVerifier = new MACVerifier(SIGN_KEY.getBytes(StandardCharsets.UTF_8));
            // take it from token
            SignedJWT signedJWT = SignedJWT.parse(token);
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            var verified = signedJWT.verify(jwsVerifier);
            return verified && expiryTime.after(new Date());
        } catch (Exception e) {
            log.error("Invalid JWT token", e);
            return false;
        }
    }

    public String extractUsername(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (Exception e) {
            log.error("Error extracting username from JWT token", e);
            throw new RuntimeException("Error extracting username from JWT token", e);
        }
    }
}
