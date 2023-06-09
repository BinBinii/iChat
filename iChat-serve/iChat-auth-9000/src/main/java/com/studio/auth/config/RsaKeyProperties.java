package com.studio.auth.config;

import com.studio.auth.utils.RsaUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Author: BinBin
 * @Date: 2023/03/15/16:12
 * @Description:
 */
@Data
@ConfigurationProperties("rsa.key")
public class RsaKeyProperties {

    private String pubKeyPath;

    private String priKeyPath;

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @PostConstruct
    public void createKey() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }
}
