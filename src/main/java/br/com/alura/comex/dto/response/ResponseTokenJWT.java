package br.com.alura.comex.dto.response;

public class ResponseTokenJWT {

    private String tokenJWT;

    public ResponseTokenJWT(String tokenJWT) {
        this.tokenJWT = tokenJWT;
    }

    public String getTokenJWT() {
        return tokenJWT;
    }

    public void setTokenJWT(String tokenJWT) {
        this.tokenJWT = tokenJWT;
    }

}
