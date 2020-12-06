package com.D2GodRoll;


import java.io.*;
import java.util.List;

public class Startup {
    public void loadConfig() throws IOException {

        try (InputStream config = new FileInputStream("config.properties")) {

            parseConfig parser = new parseConfig();
            List<java.lang.String> properties = parser.parse(config);

            System.out.println(properties);

            // Retrieve Items
            retrieveItems items = new retrieveItems();
            System.out.println(items);
            //List<String> itemList = items.get(properties.get(0), properties.get(1));

        } catch (FileNotFoundException e) {

            System.out.println("Initial configuration has not been completed, calling InitialConfig setup.");
            InitialConfig initConfig = new InitialConfig();
            initConfig.createConfig();
            System.out.println("Created Config");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
