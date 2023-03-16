package com.studio.auth.test;

import com.studio.auth.utils.RsaUtils;

/**
 * @Author: BinBin
 * @Date: 2023/03/15/16:56
 * @Description:
 */
public class GenerateKey {

    private static String publicKeyFilename = "/Users/binbini/Documents/workspace/iChat/iChat-serve/auth_key/id_key_rsa.pub";

    private static String privateKeyFilename = "/Users/binbini/Documents/workspace/iChat/iChat-serve/auth_key/id_key_rsa";

    public static void main(String[] args) throws Exception {
        RsaUtils.generateKey(publicKeyFilename, privateKeyFilename, "binbini", 20);
    }
}
