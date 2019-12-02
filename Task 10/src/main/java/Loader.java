import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Loader {
    private String className;
    private String packageName;
    private String path;

    public Loader(String packageName, String className, String path) {
        this.packageName = packageName;
        this.className = className;
        this.path = path;
    }

    public Class load() throws ClassNotFoundException, MalformedURLException {
        if (packageName != "") {
            ClassLoader loader = Loader.class.getClassLoader();
            Class obj = loader.loadClass(packageName + className);
            return obj;
        }
        else if (path != "") {
            String url = "file:///" + path.replace("\\", "/");
            URLClassLoader urlClassLoader = URLClassLoader.newInstance(new URL[] {
                    new URL(url)
            });
            System.out.println(url);
            return urlClassLoader.loadClass(className);
        }
        else
            throw new ClassNotFoundException("Specify package or file path!");
    }
}
