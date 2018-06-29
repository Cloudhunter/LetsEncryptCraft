package uk.co.cloudhunter.letsencryptcraft;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.*;

public class LetsEncryptAdder
{
    public static void addLetsEncryptCertificate() throws Exception
    {
        InputStream cert = LetsEncryptAdder.class.getResourceAsStream("/assets/letsencryptroot/lets-encrypt-x3-cross-signed.der");

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        Path ksPath = Paths.get(System.getProperty("java.home"),"lib", "security", "cacerts");
        keyStore.load(Files.newInputStream(ksPath), "changeit".toCharArray());

        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        InputStream caInput = new BufferedInputStream(cert);
        Certificate crt = cf.generateCertificate(caInput);
        //System.out.println("Added Cert for " + ((X509Certificate) crt).getSubjectDN());

        keyStore.setCertificateEntry("lets-encrypt-x3-cross-signed", crt);


        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(keyStore);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);
        SSLContext.setDefault(sslContext);
    }
}
