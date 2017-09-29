package com.weddingcrashers.repositories;

import java.util.Base64;
/**
 *  @author Michel Schlatter
 *  */
public class Utils {

    // yes I know this is not secure, but it should be enough for this purpose - if somebody wants to change it, lets go...
    public static String hashPassword(String pw){
        Base64.Encoder enc = Base64.getEncoder();
        String hashedPw = new String(enc.encode(pw.getBytes()));
        return hashedPw;
    }
}
