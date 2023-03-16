package utils;

import com.studio.auth.utils.RsaUtils;
import org.junit.Test;

/**
 * @author Robod
 * @date 2020/8/9 23:14
 */
public class RsaUtilsTest {

    private String privateFilePath = "/Users/binbini/Documents/workspace/iChat/iChat-serve/auth_key/id_key_rsa";
    private String publicFilePath = "/Users/binbini/Documents/workspace/iChat/iChat-serve/auth_key/id_key_rsa.pub";

    @Test
    public void generateKey() throws Exception {
        RsaUtils.generateKey(publicFilePath,privateFilePath,"iChatLee",2048);
    }

    @Test
    public void getPublicKey() throws Exception {
        System.out.println(RsaUtils.getPublicKey(publicFilePath));
    }

    @Test
    public void getPrivateKey() throws Exception {
        System.out.println(RsaUtils.getPrivateKey(privateFilePath));
    }


}