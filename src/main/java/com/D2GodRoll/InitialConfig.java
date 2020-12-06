package com.D2GodRoll;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

public class InitialConfig {

    public void getCharacterInfo(){

    }
    public void createConfig(){
        try (OutputStream output = new FileOutputStream("config.properties")) {

            System.out.println("Inilizating properties.");
            Properties prop = new Properties();

            // Need to call Init Setup Gui.

            // set the properties value
            prop.setProperty("user.name", "gatoraidab");
            prop.setProperty("bungie.id", "20796584");
            bungieApi membershipID = new bungieApi();

            bungieApi AccountInfo = new bungieApi();

            String membership = AccountInfo.getMembership(prop.getProperty("bungie.id"));
            List characters = AccountInfo.getCharacters(membershipID.getMembership(prop.getProperty("bungie.id")));
            List currentItems = AccountInfo.getItems(characters, membership);

            prop.setProperty("d2.membership", membership);
            prop.setProperty("d2.characters", String.valueOf(characters));
            //membershipID.getCharacters(membershipID.getMembership(prop.getProperty("bungie.id")));

            //prop.setProperty("d2.characters", String.valueOf(membershipID.getCharacters(membershipID.getMembership(prop.getProperty("bungie.id")))));
            //prop.setProperty("d2.currentItems", String.valueOf(membershipID.getCharacters(membershipID.getMembership(prop.getProperty("bungie.id"))));

            System.out.println("Property values set.");

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            System.out.println("Cannot find file.");
            io.printStackTrace();
        }
    }
}
