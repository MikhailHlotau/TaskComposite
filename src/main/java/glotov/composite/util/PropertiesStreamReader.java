package glotov.composite.util;
import glotov.composite.exception.TextException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class PropertiesStreamReader {
    private static final Logger logger = LogManager.getLogger(PropertiesStreamReader.class);

    // Получение файла из ресурсов
    public File getFileFromResource(String fileName) throws TextException {
        try {
            URL resource = getClass().getClassLoader().getResource(fileName);
            if (resource == null) {
                String errorMessage = "File not found: " + fileName;
                logger.error(errorMessage);
                throw new TextException(errorMessage);
            }
            return new File(resource.toURI());
        } catch (URISyntaxException e) {
            String errorMessage = "Failed to get file from resource: " + fileName;
            logger.error(errorMessage, e);
            throw new TextException(errorMessage, e);
        }
    }
    // Чтение содержимого файла из ресурсов
    public String readStream(String fileName) throws TextException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
             InputStreamReader reader = new InputStreamReader(input);
             BufferedReader buff = new BufferedReader(reader)) {
            return buff.readLine();
        } catch (IOException e) {
            String errorMessage = "Failed to read stream: " + fileName;
            logger.error(errorMessage, e);
            throw new TextException (errorMessage, e);
        }
    }

    public static void main(String[] args) throws TextException {
        logger.info("Program started.");
        PropertiesStreamReader ob = new PropertiesStreamReader();
        logger.info("Program completed.");
    }
}

