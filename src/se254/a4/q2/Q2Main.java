package se254.a4.q2;

/**
 * SE254 Assignment 4 Question 2 Main. Running this should generate and print out the MyLibTest class as detailed
 * in the assignment handout.
 */
public class Q2Main {

	public static void main(String[] args) {

		UnitTestTemplate template = new UnitTestTemplate();
		String result = template.generate(MyLib.class);
		System.out.println(result);

	}

}
