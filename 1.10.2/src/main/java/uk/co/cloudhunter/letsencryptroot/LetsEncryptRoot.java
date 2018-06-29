package uk.co.cloudhunter.letsencryptroot;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.net.URL;


@Mod(modid = LetsEncryptRoot.MOD_ID, name = LetsEncryptRoot.NAME, version = LetsEncryptRoot.VERSION, clientSideOnly = true, acceptableRemoteVersions="*", acceptedMinecraftVersions = "*")
public class LetsEncryptRoot
{

    public static final String MOD_ID = "letsencryptroot";
    public static final String NAME = "Let's Encrypt Root";
    public static final String VERSION = "@VERSION@";
    public static final Logger logger = LogManager.getLogger("letsencryptroot");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        String body = "";
        try {
            LetsEncryptAdder.addLetsEncryptCertificate();
            URL url = new URL("https://helloworld.letsencrypt.org");
            InputStream inputStream = url.openStream();
            body = IOUtils.toString(inputStream);
        } catch (Exception e) {
            logger.error("An error occurred whilst adding the Let's Encrypt root certificate. I'm afraid you wont be able to access web sites with a Let's Encrypt certificate D:", e);
        }

        if (body.isEmpty())
        {
            logger.error("An unknown error occurred whilst adding the Let's Encrypt root certificate. I'm afraid you may not be able to access web sites with a Let's Encrypt certificate D:");
        }
    }
}
