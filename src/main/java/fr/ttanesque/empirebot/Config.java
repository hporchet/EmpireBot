package fr.ttanesque.empirebot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import fr.ttanesque.empirebot.config.InitConf;

import java.io.File;
import java.io.IOException;

/**
 * This class manage the retrieve of the configuration object in file.
 */
public class Config {
    /**
     * The instance.
     */
    private static Config configInstance;

    static {
        try {
            configInstance = new Config("settings.yaml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * The file who contain the config.
     */
    private File configFile;
    /**
     * The {@link ObjectMapper} who read the configFile.
     */
    private ObjectMapper om;

    /**
     * The constructor for create an config.
     *
     * @param name the name of the file
     */
    public Config(final String name) throws IOException {
        this.configFile = new File(name);
        if (!configFile.exists()) {
            if (!configFile.createNewFile()) {
                throw new IOException();
            }
        }
        this.om = new ObjectMapper(new YAMLFactory());
    }

    /**
     * Get the {@link InitConf} object.
     *
     * @return the initconf found
     * @throws IOException too bad no valid configuration
     */
    public InitConf getInitConf() throws IOException {
        return om.readValue(configFile, InitConf.class);
    }

    /**
     * The getter for the instance.
     * @return the config object
     */
    public static Config getConfigInstance() {
        return configInstance;
    }

}
