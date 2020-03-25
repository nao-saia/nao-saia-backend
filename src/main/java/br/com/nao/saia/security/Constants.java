package br.com.nao.saia.security;

public final class Constants {

    private Constants() {
    }

    public static final long TOKEN_TTL_SECONDS = 5 * 60 * 60;
    public static final String SIGNING_KEY = "n4os4i4";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORITIES_KEY = "role";

}
