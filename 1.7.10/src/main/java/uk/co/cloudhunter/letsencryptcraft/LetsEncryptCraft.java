package uk.co.cloudhunter.letsencryptcraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.net.URL;

@Mod(modid = LetsEncryptCraft.MOD_ID, name = LetsEncryptCraft.NAME, version = LetsEncryptCraft.VERSION, acceptableRemoteVersions="*", acceptedMinecraftVersions = "*")
public class LetsEncryptCraft
{

    public static final String MOD_ID = "letsencryptcraft";
    public static final String NAME = "Let's Encrypt Craft";
    public static final String VERSION = "@VERSION@";
    public static Logger logger = LogManager.getLogger(MOD_ID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        String version = System.getProperty("java.version");
        String majorVersion = version.substring(0, version.lastIndexOf("."));
        int minorVersion = Integer.valueOf(version.substring(version.lastIndexOf("_") + 1));

        switch (majorVersion)
        {
            case "1.7":
                if (minorVersion >= 111)
                {
                    logger.info("Not running as Java version is at least Java 7u111.");
                    return;
                }
                break;
            case "1.8":
                if (minorVersion >= 101)
                {
                    logger.info("Not running as Java version is at least Java 8u101.");
                    return;
                }
                break;
        }

        String body = "";
        try {
            logger.info("Adding Let's Encrypt certificate...");
            LetsEncryptAdder.addLetsEncryptCertificate();
            logger.info("Done, attempting to connect to https://helloworld.letsencrypt.org...");
            URL url = new URL("https://helloworld.letsencrypt.org");
            InputStream inputStream = url.openStream();
            body = IOUtils.toString(inputStream);
        } catch (Exception e) {
            logger.error("An error occurred whilst adding the Let's Encrypt root certificate. I'm afraid you wont be able to access resources with a Let's Encrypt certificate D:", e);
        }

        if (body.isEmpty())
        {
            logger.error("An unknown error occurred whilst adding the Let's Encrypt root certificate. I'm afraid you may not be able to access resources with a Let's Encrypt certificate D:");
        } else {
            logger.info("Done - you are now able to access resources with a Let's Encrypt certificate :D");
        }
    }
}
