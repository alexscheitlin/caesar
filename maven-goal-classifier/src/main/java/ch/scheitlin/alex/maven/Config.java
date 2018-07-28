package ch.scheitlin.alex.maven;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Config {
    public String[] clean;
    public String[] validation;
    public String[] preProcessing;
    public String[] compilation;
    public String[] testing;
    public String[] packaging;
    public String[] analysis;
    public String[] deployment;
    public String[] externalTasks;
    public String[] documentation;
    public String[] releasePreparation;
    public String[] support;
    public String[] dependency;

    private static final String path = "config/config.yaml";

    public static Config readConfig() throws IOException, NullPointerException {
        // get the config class to later map the YAML's content to
        Class<Config> configClass = Config.class;

        // create a YAML factory to read the YAML file
        YAMLFactory yamlFactory = new YAMLFactory();

        // create an object mapper to read JSON files
        ObjectMapper mapper = new ObjectMapper(yamlFactory);

        InputStream stream = Config.class.getResourceAsStream("/" + path);
        if (stream != null) {
            return mapper.readValue(stream, configClass);
        }
        File file = new File(path);
        return mapper.readValue(file, configClass);
    }
}
