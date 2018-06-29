package uk.co.cloudhunter.letsencryptroot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = LetsEncryptRoot.MOD_ID, name = LetsEncryptRoot.NAME, version = LetsEncryptRoot.VERSION, acceptableRemoteVersions="*")
public class LetsEncryptRoot
{

    public static final String MOD_ID = "letsencryptroot";
    public static final String NAME = "Let's Encrypt Root";
    public static final String VERSION = "@VERSION@";
    public static final Logger logger = LogManager.getLogger("letsencryptroot");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LetsEncryptAdder.addLetsEncryptCertificate(LetsEncryptRoot.class.getResourceAsStream("assets/letsencryptroot/DSTRootCAX3.der"));
    }
}
