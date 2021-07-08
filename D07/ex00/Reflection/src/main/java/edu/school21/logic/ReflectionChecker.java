package edu.school21.logic;

import org.reflections.Reflections;

import java.lang.reflect.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import static org.reflections.ReflectionUtils.*;


public class ReflectionChecker {

    private List<ClassLoader> classLoadersList;
    private Reflections reflections;
    private Set<Class<?>> classes;
    private Set<Field> fields;
    private Set<Method> methods;
    private Class<?>[] parameters;
    private Class cl;
    private Object obj;
    private Object tmp = new Object();
    private Scanner sc = new Scanner(System.in);

    public Set<Field> getFields() {
        return fields;
    }

    public ReflectionChecker() {

        classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("edu.school21.models"))));
        classes = reflections.getSubTypesOf(Object.class);


    }

    public void showClassName() {
        for (Class<?> set : classes) {
            System.out.println(set.getName().substring(20));
        }
    }

    public void showClassFields(String className) {
        for (Class<?> set : classes) {
            if (set.getName().substring(20, set.getName().length()).equals(className)) {
                cl = forName(set.getName());
                fields = getAllFields(cl);
                for (Field field : fields) {
                    System.out.print(field.getType().getTypeName().substring(10) + " ");
                    System.out.println(field.getName() + "  ");
                }
            }
        }
    }

    public void showClassMethods(String className) {
        for (Class<?> set : classes) {
            if (set.getName().substring(20, set.getName().length()).equals(className)) {
                cl = forName(set.getName());
                methods = getAllMethods(cl);
                for (Method method : methods) {
                    System.out.print(method.getReturnType().getSimpleName() + " ");
                    System.out.print(method.getName() + "(");
                    parameters = method.getParameterTypes();
                    for (Class<?> par : parameters) {
                        System.out.print(par.getName());
                    }
                    System.out.println(")");
                }
            }
        }
    }

    public void createNewObject() {
        try {
            obj = cl.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void objToString()
    {
        while (sc.hasNext() == false) {
            System.out.println("Must be String type, try again:");
            sc.next();
        }
        tmp = sc.nextLine();
    }

    private void objToInt()
    {
        while (sc.hasNextInt() == false) {
            System.out.println("Must be Integer type, try again:");
            sc.next();
        }
        tmp = sc.nextInt();
    }

    private void objToDouble () {
        while (sc.hasNextDouble() == false) {
            System.out.println("Must be Double type, try again:");
            sc.next();
        }
        tmp = sc.nextDouble();
    }

    private void objToLong () {
        while (sc.hasNextLong() == false) {
            System.out.println("Must be Long type, try again:");
            sc.next();
        }
        tmp = sc.nextLong();
    }

    private void objToBoolean () {
        while (sc.hasNextBoolean() == false) {
            System.out.println("Must be Boolean type, try again:");
            sc.next();
        }
        tmp = sc.nextBoolean();
    }

    public void setClassFields() {
        for (Field field : fields) {
            System.out.println("-> " + field.getName() + ": ");
            try {
                tmp = Class.forName(field.getType().getName());
                if (tmp == String.class) {
                    objToString();
                } else if (tmp == Integer.class) {
                    objToInt();
                } else if (tmp == Long.class) {
                    objToLong();
                } else if (tmp == Boolean.class) {
                    objToBoolean();
                } else if (tmp == Double.class) {
                    objToDouble();
                } else {
                    System.err.println("type is not supported");
                }
//                Field f = obj.getClass().getField(field.getName());
                Field f = obj.getClass().getDeclaredField(field.getName());
                f.setAccessible(true);
                f.set(obj, tmp);
            } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Object created: " + obj.toString());
    }
}