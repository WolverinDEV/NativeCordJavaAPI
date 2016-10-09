package dev.wolveringer.nativecord.plugin;

import dev.wolveringer.configuration.file.YamlConfiguration;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wolverindev on 09.10.16.
 */
public class PluginDescription {
    private YamlConfiguration yamlConfig;
    @Getter
    private List<String> authors;
    @Getter
    private String version;
    @Getter
    private String mainFile;
    @Getter
    private String name;

    public PluginDescription(YamlConfiguration config){
        this.yamlConfig = config;
        /*
main: dev.wolveringer.testplugin.Main
version: 1.0-SNAPSHOT
depend: []
author: [WolverinDEV]
         */
        if(!config.isString("name"))
            throw  new RuntimeException("Cant load plugin without name. LOL");
        this.name = config.getString("name");
        if(!config.isString("main"))
            throw  new RuntimeException("Invalid main path!");
        this.mainFile = config.getString("main");

        if(!config.contains("version") && !config.isString("version"))
            throw  new RuntimeException("Invalid versions string.");
        this.version = config.getString("version");

        if(config.isList("author"))
            authors = config.getStringList("author");
        else if(config.isString("author"))
            authors = Arrays.asList(config.getString("author"));
        else
            authors = Arrays.asList("unknown");
    }
}
