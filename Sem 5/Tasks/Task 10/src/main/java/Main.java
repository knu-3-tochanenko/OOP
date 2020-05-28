import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Workspace\\Repos\\knu-3\\OOP Tasks\\Task 10\\Dummy\\";
        Loader loader = new Loader("", "Dummy", path);
        Class obj;
        try {
            obj = loader.load();
            System.out.println("NAME: " + obj.getName());

            Field[] fields = obj.getDeclaredFields();
            for (Field i : fields) {
                System.out.println("\t" + i.getType() + " - " + i.getName());
            }

            Constructor[] constructors = obj.getDeclaredConstructors();
            for (Constructor i : constructors) {
                System.out.print("\t" + i.getName() + "(");
                Parameter[] parameters = i.getParameters();
                for (Parameter j : parameters)
                    System.out.print(j.getType() + " : " + j.getName() + "; ");
                System.out.println(")");
            }

            Method[] methods = obj.getDeclaredMethods();
            for (Method i : methods) {
                System.out.print("\t" + i.getReturnType() + " " + i.getName() + "(");
                Parameter[] parameters = i.getParameters();
                for (Parameter j : parameters)
                    System.out.print(j.getType() + " : " + j.getName() + "; ");
                System.out.println(")");
            }
        } catch (ClassNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
