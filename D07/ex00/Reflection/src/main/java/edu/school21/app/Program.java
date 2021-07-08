package edu.school21.app;


import com.google.inject.internal.util.Classes;
import edu.school21.models.Car;
import edu.school21.models.User;
import edu.school21.logic.ReflectionChecker;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Program {
    public static void main(String args[]) throws NoSuchFieldException, IllegalAccessException {
        ReflectionChecker checker = new ReflectionChecker();

        System.out.println("Classes:");
        checker.showClassName();
        System.out.println("------------------------");

        Scanner sc = new Scanner(System.in);
        String className;
        System.out.print("Enter class name:\n->");
        className = sc.nextLine();
        System.out.println("------------------------");
        System.out.println("fields: ");
        checker.showClassFields(className);
        System.out.println("methods: ");
        checker.showClassMethods(className);
        System.out.println("------------------------");
        System.out.println("Let's create an object: ");
        checker.createNewObject();
        checker.setClassFields();



        //  тут будет печать филдов
        //checker.showClassFields(user);

    }

}
