package se254.a4.q1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * SE254 Assignment 4 Question 1 Main. This class should be implemented to
 * contain the functionality described in the assignment handout. you may
 * implement additional classes if you wish, but this class should be the main
 * program entry point.
 */
public class Q1Main {

	public static void main (String[] args) {
		// TODO Assignment 4, question 1.
		Scanner reader = null;
		Class<?> classLoaded = null;
		Object classObject = null;

		while (true) {
			try {
				reader = new Scanner(System.in); // create scanner for user input
				System.out.print("Please give the class name for reflection (without .java): ");
				String className = reader.nextLine();

				classLoaded = initClass(className); // gets class
				classObject = classLoaded.newInstance(); // get instance of class
				break;
			} catch (Exception | NoClassDefFoundError e) {
				System.out.println("Invalid class name, try again.");
			}
		}
		
		while (true) {
						System.out.println("");
			try {
				getPublicFields(classObject);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getPublicMethods(classObject);
			
			System.out.print("Type a method (without () at end): ");
			String method = reader.nextLine();
			invokeMethod(classLoaded, classObject, method);
			
			System.out.print("Continue with program? y/n: ");
			String yn = reader.nextLine();

			if (yn.trim().equals("n")) {
				System.out.println("Exiting!");
				return;
			}
		}
	}


	// loads class of given name
	private static Class<?> initClass(String className) throws Exception, NoClassDefFoundError {
		ClassLoader cl = Q1Main.class.getClassLoader();
		Class<?> classLoaded = null;

		classLoaded = cl.loadClass("se254.a4.q1." + className);

		return classLoaded;
	}

	// gets all public fields that are declared in given object instance
	public static void getPublicFields(Object classObject) throws IllegalArgumentException, IllegalAccessException {
		System.out.println("All public fields (type:name:value) are:");
		for (Field f : classObject.getClass().getDeclaredFields()) {
			System.out.println("\t" + f.getType() + ":" + f.getName() + ":" + f.get(classObject));
		}
	}

	// gets all public methods without parameters
	public static void getPublicMethods(Object classObject) {
		System.out.println("All public methods (return type:name) with no parameters: ");
		for (Method m : classObject.getClass().getDeclaredMethods()) {
			if (m.getParameterCount() == 0) {
				System.out.println("\t" + m.getReturnType() + ":" + m.getName());
			}
		}
		System.out.println("");
	}

	public static void invokeMethod(Class<?> classLoaded, Object classObject, String method) {
		Method m = null;
		try {
			m = classLoaded.getMethod(method, null);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("No such method exists, please try again");
			return;
		}

		try {
			m.invoke(classObject, null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

	}
}
