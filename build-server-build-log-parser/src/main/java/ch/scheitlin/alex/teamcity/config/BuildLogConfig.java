package ch.scheitlin.alex.teamcity.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * <p>Reads the {@code buildLogConfig.yaml} file containing regular expressions.</p>
 * <br>
 *
 * <p>The {@code buildLogConfig.yaml} file needs to have the following structure:
 * <pre>{@code
 * regex:
 *   regex1: "^regular expression$"
 *   regex2: "^regular expression$"
 *   ...
 * }</pre>
 *
 * <p>To access the regular expressions, use the following code:</p>
 * <pre>{@code
 * BuildLogConfig config = BuildLogConfig.readConfig();
 * String regex = config.getRegex().get("regex1")
 * }</pre>
 */
public class BuildLogConfig {
    /**
     * The regular expression and its name.
     */
    private Map<String, String> regex;

    /**
     * The location of the buildLogConfig.yaml file containing the regular expressions.
     */
    private static final String path = "buildLogConfig.yaml";

    /**
     * Gets the regular expression and its name.
     *
     * @return the regular expression and its name
     */
    public Map<String, String> getRegex() {
        return this.regex;
    }

    /**
     * Reads the buildLogConfig.yaml file.
     *
     * @return the yaml file's content as a Java Object
     * @throws IOException          if the file can not be read
     * @throws JsonParseException   if the content of the YAML file is not conform to JSON syntax
     * @throws JsonMappingException if the structure of the YAML file is not conform to JSON syntax
     * @throws NullPointerException if the path is null
     */
    public static BuildLogConfig readConfig() throws IOException, JsonParseException, JsonMappingException, NullPointerException {
        // get the config class to later map the YAML's content to
        Class<BuildLogConfig> configClass = BuildLogConfig.class;

        // create a YAML factory to read the YAML file
        YAMLFactory yamlFactory = new YAMLFactory();

        // create an object mapper to read JSON files
        ObjectMapper mapper = new ObjectMapper(yamlFactory);

        /*
        // get the path to the jar
        ProtectionDomain domain = BuildLogConfig.class.getProtectionDomain();
        CodeSource source = domain.getCodeSource();
        URL url = source.getLocation();
        File jarPath = new File(url.getPath());

        // split the path to get the last folder
        String[] paths = jarPath.getAbsolutePath().split(Pattern.quote(File.separator));
        String configPath = paths[paths.length - 1];
        */


        InputStream stream = BuildLogConfig.class.getResourceAsStream("/" + path);
        if (stream != null) {
            return mapper.readValue(stream, configClass);
        }
        File file = new File(path);
        return mapper.readValue(file, configClass);

        /*
        // depending on the folder the code was called from either
        // - this project
        // - a project using this project as a jar
        if (configPath.equals("classes")) {
            // this project
            File file = new File(path);

            // map the configuration within the YAMl file to a new instance of the config class
            return mapper.readValue(file, configClass);
        } else {
            // a project using this project as a jar
            InputStream stream = BuildLogConfig.class.getResourceAsStream("/" + path);

            // map the configuration within the YAMl file to a new instance of the config class
            return mapper.readValue(stream, configClass);
        }*/
    }
}
