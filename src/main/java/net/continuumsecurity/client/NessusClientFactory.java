package net.continuumsecurity.client;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.client.ClientProperties;

/**
 * 
 * 
 * @author Stephen de Vries
 * @author Adam Parsons
 * @version 0.0.2, 04/29/14
 * @since 1.0.0
 */
public class NessusClientFactory {

    public static Client buildInsecureSSLClient() {
        
        TrustManager[] trustAllCerts = new TrustManager[]{ new TrustingX509TrustManager() };

        HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {

                    @Override
                    public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                        return hostname.equals("localhost");
                    }

                });

        // Install the all-trusting trust manager
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ClientBuilder.newBuilder().sslContext(sc).property(ClientProperties.PROXY_URI, "http://localhost:8888").build();
    }
    
    private static final class TrustingX509TrustManager implements X509TrustManager {

        private static final X509Certificate[] X509_CERTIFICATES = new X509Certificate[0];
        
        @Override
        public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return X509_CERTIFICATES;
        }
        
    }
    
}
