package com.studio.image.config;

import com.studio.image.utils.RsaUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
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

    private PublicKey publicKey;

    @PostConstruct
    public void createKey() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
    }
}
