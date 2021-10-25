package com.prmgpregistrationsmi.utils;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileJSONParser {
    private FileJSONParser() {
        throw new IllegalStateException("Utility class, no need to initialise new instance");
    }

    public static Object readFile(String fileLocation) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader fileReader = new FileReader(fileLocation, StandardCharsets.UTF_8);
            Object parsedJson = jsonParser.parse(fileReader);
            fileReader.close();
            return parsedJson;
        } catch (Exception e) {
            System.out.println("Issue with FileReader reading file");
            throw e;
        } finally {
            jsonParser.reset();
        }
    }
}
