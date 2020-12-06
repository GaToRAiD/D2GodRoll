package com.D2GodRoll;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class parseConfig {

    public List<String> parse(InputStream input) throws IOException {
        List<String> props = new ArrayList<>();

        Properties prop = new Properties();
        prop.load(input);
        String username = prop.getProperty("user.name");
        String id = prop.getProperty("bungie.id");
        System.out.println(prop.getProperty("user.name"));
        System.out.println(prop.getProperty("bungie.id"));
        

        props.add(username);
        props.add(id);

        return props;
    }
}
