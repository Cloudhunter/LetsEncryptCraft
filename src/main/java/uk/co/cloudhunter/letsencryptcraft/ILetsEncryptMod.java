package uk.co.cloudhunter.letsencryptcraft;

public interface ILetsEncryptMod {
    void info(String log);
    void error(String log);
    void error(String message, Throwable t);
}
