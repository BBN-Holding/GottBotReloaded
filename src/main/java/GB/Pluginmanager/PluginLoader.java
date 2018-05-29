package GB.Pluginmanager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginLoader {

    public static Class<?> loadClass(File dir) throws MalformedURLException, ClassNotFoundException {
        return Class.forName("Main", true, new URLClassLoader(new URL[] {dir.toURI().toURL()}));
    }

    public static Class<?>[] loadDirectory(File dir) throws Exception {
        File[] files = dir.listFiles();
        Class<?>[] classes = new Class<?>[files.length];
            for (int i = 0; i < files.length; i++) {
                classes[i] = loadClass(files[i]);
            }
        return classes;
    }

    public static Plugin initAsPlugin(Class<?> group) throws IllegalAccessException, InstantiationException {
        return (Plugin) group.newInstance();
    }

    public static Plugin[] initAsPlugin(Class<?>[] group) throws InstantiationException, IllegalAccessException {
        Plugin[] plugins = new Plugin[group.length];
        for (int i=0; i<group.length; i++) plugins[i] = initAsPlugin(group[i]);
        return plugins;
    }

}
