package org.debug.client;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpClientTransport;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http2.client.HTTP2Client;
import org.eclipse.jetty.http2.client.http.HttpClientTransportOverHTTP2;
import org.eclipse.jetty.util.component.ContainerLifeCycle;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author Thomas Li
 * @date 2025/2/20
 */
public class JettyHttp2Client {
    public static void main(String[] args) throws Exception {
        HTTP2Client http2LowLevelClient = new HTTP2Client() {
            @Override
            protected void doStop() throws Exception {
                super.doStop();
                Executor executor = getExecutor();
                if (executor instanceof ContainerLifeCycle) {
                    ((ContainerLifeCycle) executor).stop();
                }
            }
        };
        http2LowLevelClient.setInitialSessionRecvWindow(64 * 1024 * 1024);
        http2LowLevelClient.setInitialStreamRecvWindow(32 * 1024 * 1024);
        // disable dynamic header table
        http2LowLevelClient.setMaxDynamicTableSize(0);
        HttpClientTransport transport = new HttpClientTransportOverHTTP2(http2LowLevelClient);

        HttpClient http2Client = new HttpClient(transport);
        http2Client.setFollowRedirects(true);
        http2Client.setRemoveIdleDestinations(true);
        http2Client.start();
        try {
            for (int i = 0; i < 3; i++) {
                Request request = http2Client.newRequest("http://localhost:8080/debug")
                        .method("POST");
                request.header("x-forwarded-for", "10.117.168.145");
                request.header("content-length", "0");
                request.header("x-fake-value", "debug");
                request.timeout(3, TimeUnit.SECONDS);
                request.idleTimeout(3, TimeUnit.SECONDS);
                ContentResponse response = request.send();
                int status = response.getStatus();
                System.out.println("status:" + status + ", reason:" + response.getReason() + ", " + response.getContentAsString());
                if (status != 200) {
                    break;
                }
            }
        } finally {
            http2Client.stop();
        }
    }
}
