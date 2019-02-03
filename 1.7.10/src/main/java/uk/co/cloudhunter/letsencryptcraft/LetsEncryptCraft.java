package uk.co.cloudhunter.letsencryptcraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mod(modid = LetsEncryptCraft.MOD_ID, name = LetsEncryptCraft.NAME, version = LetsEncryptCraft.VERSION, acceptableRemoteVersions="*", acceptedMinecraftVersions = "*")
public class LetsEncryptCraft implements ILetsEncryptMod
{

    public static final String MOD_ID = "letsencryptcraft";
    public static final String NAME = "Let's Encrypt Craft";
    public static final String VERSION = "@VERSION@";
    public static Logger logger = LogManager.getLogger(LetsEncryptCraft.NAME);

    public LetsEncryptCraft()
    {
        LetsEncryptAdder.doStuff(this);
    }

    public void info(String log) {
        logger.info(log);
    }

    public void error(String log) {
        logger.error(log);
    }

    public void error(String log, Throwable t) {
        logger.error(log, t);
    }
}
