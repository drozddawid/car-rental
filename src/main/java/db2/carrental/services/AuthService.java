package db2.carrental.services;

import db2.carrental.entities.AuthToken;
import db2.carrental.entities.Uzytkownicy;
import db2.carrental.repositories.UzytkownicyRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final List<AuthToken> authTokens;
    private final SecureRandom random;
    private final UzytkownicyRepository uzytkownicyRepository;

    public AuthService(UzytkownicyRepository uzytkownicyRepository) {
        this.uzytkownicyRepository = uzytkownicyRepository;
        this.authTokens = new ArrayList<>();
        this.random = new SecureRandom();
        authTokens.add(new AuthToken("permission1", "testMail1", 1));//TODO: remove this two lines
        authTokens.add(new AuthToken("permission0", "testMail0", 0));//for testing
    }


    public boolean isUserAuthenticated(String token, int permissionLevel) {
        return authTokens.stream().anyMatch(stream -> stream.getAuthToken().equals(token)
                && stream.getPermissionLevel() >= permissionLevel);
    }

    public Optional<AuthToken> getTokenForEmail(String email){
        return authTokens.stream().filter(authToken -> authToken.getEmail().equals(email)).findAny();
    }


    public Optional<AuthToken> getTokenObjForTokenStr(String token) {
        return authTokens.stream().filter(authToken -> authToken.getAuthToken().equals(token)).findAny();
    }

    public AuthToken createToken(String email, int permissionLevel){
        AuthToken newToken = new AuthToken(email, permissionLevel);
        authTokens.add(newToken);
        return newToken;
    }

    public AuthToken login(String email, String password, int permissionLevel) throws NoSuchAlgorithmException {
        Optional<Uzytkownicy> user = uzytkownicyRepository.findById(email);
        if (user.isEmpty()) throw new IllegalArgumentException("Could not login, user does not exist.");
        if (user.get().getUserGroupId() < permissionLevel)
            throw new IllegalArgumentException("Could not login, user permissions insufficient");
        String hashPswd = hashPassword(password);
        if (!user.get().getPasswordHash().equals(hashPswd))
            throw new IllegalArgumentException("Wrong password.");
        return createToken(email, permissionLevel);
    }



    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(password.getBytes());

        byte[] bytes = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }


    public void registerUser(Uzytkownicy user) throws NoSuchAlgorithmException {
        if (uzytkownicyRepository.findById(user.getId()).isPresent()) throw new IllegalArgumentException("Account already registered.");
        user.setPasswordHash(hashPassword(user.getPasswordHash()));
        uzytkownicyRepository.save(user);
    }

    public void revoke(String token) {
        Optional<AuthToken> authToken = getTokenObjForTokenStr(token);
        if (authToken.isEmpty()) throw new IllegalArgumentException("Token does not exist.");
        authTokens.remove(authToken);
    }
}
