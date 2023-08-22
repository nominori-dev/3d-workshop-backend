package com.nominori.oms.infrastructure.s3;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.contentHandlers.B2ContentMemoryWriter;
import com.backblaze.b2.client.exceptions.B2Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
@Slf4j
public class B2Sample {

    @Value("${blackblaze.bucket.id")
    private String bucketId;

    @Value("${blackblaze.bucket.name")
    private String bucketName;

    private final B2StorageClient b2StorageClient;

    public B2Sample(B2StorageClient b2StorageClient) {
        this.b2StorageClient = b2StorageClient;

        log.info("Trying to download demo TXT file from B2");
        B2ContentMemoryWriter sink = B2ContentMemoryWriter.build();
        try {
            b2StorageClient.downloadById("4_z8019f4c251bfb1be869a031d_f113bedf79e0b5f90_d20230822_m214035_c005_v0501001_t0009_u01692740435078", sink);
        } catch (B2Exception e) {
            throw new RuntimeException(e);
        }

        log.info("Downloaded file content: " + new String(sink.getBytes()));
    }
}
