package edu.school21.logic;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;
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
    Set<Field> fields;
    Set<Method> methods;


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
                Class cl = forName(set.getName());
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
                Class cl = forName(set.getName());
                methods = getAllMethods(cl);
                for (Method method : methods) {
                    System.out.print(method.getReturnType().getSimpleName() + " ");
                    System.out.print(method.getName() + "(");
                    Class<?>[] parameters = method.getParameterTypes();
                    for (Class<?> par : parameters) {
                        System.out.print(par.getName());
                    }
                    System.out.println(")");
                }
            }
        }
    }

    public Object createNewObject(Object object) throws InstantiationException, IllegalAccessException {
        Class clazz = object.getClass();
        return clazz.newInstance();
    }
//    public void showFieldsAnnotations(Object object) {
//        Class clazz = object.getClass();
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            Annotation[] annotations = field.getAnnotations();
//            for (Annotation annotation : annotations) {
//                System.out.println(annotation.toString());
//            }
//        }
//    }
}