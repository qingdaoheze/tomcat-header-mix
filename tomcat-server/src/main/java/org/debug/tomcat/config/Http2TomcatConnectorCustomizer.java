package org.debug.tomcat.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http2.Http2Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.stereotype.Component;

/**
 * @author Thomas Li
 * @date 2025/2/20
 */
@Component
public class Http2TomcatConnectorCustomizer implements TomcatConnectorCustomizer {
    private static final Logger log = LoggerFactory.getLogger(Http2TomcatConnectorCustomizer.class);
    @Override
    public void customize(Connector connector) {
        Http2Protocol http2Protocol = new Http2Protocol();
        // 客户端配置的maxConcurrentStreams只用于建立连接时的并发控制，最终的复用量由服务端在协商时发送给客户端
        connector.addUpgradeProtocol(http2Protocol);
        log.info("http2 enabled...");
    }
}
