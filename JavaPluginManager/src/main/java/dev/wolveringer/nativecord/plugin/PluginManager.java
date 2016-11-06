package dev.wolveringer.nativecord.plugin;

import dev.wolveringer.configuration.file.YamlConfiguration;
import dev.wolveringer.nativecord.Native;
import dev.wolveringer.nativecord.api.EventHandler;
import dev.wolveringer.nativecord.api.InvalidEventMethode;
import dev.wolveringer.nativecord.api.Listener;
import dev.wolveringer.nativecord.impl.EventMapper;
import dev.wolveringer.nativecord.impl.PluginManagerImpl;
import dev.wolveringer.nativecord.terminal.Console;
import lombok.NonNull;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by wolverindev on 03.10.16.
 */
public class PluginManager {
    private static final File pluginFoulderPath = new File("/home/wolverindev/NativeCord/plugins/");

    public static PluginManager getInstance(){
        return instance;
    }

    private static PluginManager instance;

    private PluginManagerImpl impl;
    private ArrayList<Plugin> avariablePlugins = new ArrayList<>();

    @Native
    private PluginManager(PluginManagerImpl impl){
        this.impl = impl;
        instance = this;
        Console.setup();
        System.out.println("Java plugin manager created. Using "+impl.toString()+" as implementation.");
    }

    @Native
    private void loadPlugins(){
        System.out.println("Loading plugins...");

        for(File file : pluginFoulderPath.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".jar");
            }
        })){
            System.out.println("Loading plugin "+file.getName());
            try{
                Plugin plugin = loadPlugin(new JarFile(file));
                impl.registerPlugin(plugin);
                avariablePlugins.add(plugin);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        //TestPlugin plugin = new TestPlugin();
        /*
        try{
            ((Plugin)plugin).setPluginManager(this);
            impl.registerPlugin(plugin);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Registered test plugin. Plugin id: " + plugin.getNativePluginAddress());
        */
    }

    private Plugin loadPlugin(JarFile file) throws IOException {
        Enumeration<JarEntry> it = file.entries();

        JarEntry yamlFile = null;
        int match = 0;

        while (it.hasMoreElements()) {
            JarEntry e = it.nextElement();
            int m = 0;
            switch (e.getName().toLowerCase()) {
                case "plugin.yml":
                    m = 1;
                case "bungee.yml":
                    m = 2;
                case "native.yml":
                    m = 3;
            }
            if(m > match){
                match = m;
                yamlFile = e;
            }
        }
        if(yamlFile == null)
            throw new RuntimeException("Plugin "+file+" missing native.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file.getInputStream(yamlFile));
        PluginDescription description = new PluginDescription(config);


        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{new URL("file:"+file.getName())},getClass().getClassLoader());
        Class<? extends  Plugin> main = null;
        try {
            main = (Class<? extends Plugin>) Class.forName(description.getMainFile(), true, loader);
        }catch (ClassNotFoundException e){
            throw  new RuntimeException("Cant find main class! ("+description.getMainFile()+")");
        }
        Plugin plugin = null;
        try{
            plugin = main.newInstance();
        }catch (Exception e){
            throw new RuntimeException("Cant create main class! ("+e.getMessage()+")");
        }
        plugin.setDescription(description);
        plugin.setPluginManager(this);
        return plugin;
    }

    public void enablePlugin(Plugin plugin){

    }

    public void registerListener(@NonNull Plugin plugin, @NonNull Listener listener){
        Method[] methods = listener.getClass().getMethods();
        for(Method m : methods){
            if(m.isAnnotationPresent(EventHandler.class)){
                Console.debugMessage("Register listener methode: "+m.getName());
                EventHandler props = m.getAnnotation(EventHandler.class);
                if(m.getParameterTypes().length != 1)
                    throw new InvalidEventMethode("Methode "+m.getClass().getName()+"#"+m.getName()+" hase not oney argument.");
                int classId = EventMapper.getEventId(m.getParameterTypes()[0]);
                if(classId < 0)
                    throw new InvalidEventMethode("Chant find event class: "+m.getParameterTypes()[0].getName());
                impl.registerListener(plugin.getNativePluginAddress(), listener, classId, m);
            }
        }
    }

    public void unregisterListener(Listener listener){

    }

    public void unregisterListener(Plugin plugin){

    }
}
