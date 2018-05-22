package GB.Pluginmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginLoader {

    public static Class<?> loadClass(File dir) throws MalformedURLException, ClassNotFoundException {
        HashMap<String, String> data = new HashMap<>();
        try {
            JarFile jf = new JarFile(dir);
            JarEntry je = jf.getJarEntry("config.yml");
            BufferedReader br = new BufferedReader(new InputStreamReader(jf.getInputStream(je)));
            String in;
            while ((in = br.readLine())!=null) {
                if (in.isEmpty()) continue;
                String[] split = in.split(": ");
                data.put(split[0], split[1]);
            }
            jf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Class.forName(data.get("Main"), true, new URLClassLoader(new URL[] {dir.toURI().toURL()}));
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
