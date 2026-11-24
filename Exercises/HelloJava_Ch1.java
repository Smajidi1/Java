/* Introduction to Java Programming and Data Structures (Comprehensive Version) 
11th Edition 2018*/

// Chapter 1 Exercises
package Exercises;

public class HelloJava_Ch1 {
        public static void main(String[] args) {

                // Exercise 1.1: Display three messages
                System.out.println("\nExercise 1.1");
                System.out.println("Welcome to Java");
                System.out.println("Learning Java Now");
                System.out.println("Programming is fun");

                // Exercise 1.2: Display "I love Java" five times
                System.out.println("\nExercise 1.2");
                System.out.println("I love Java");
                System.out.println("I love Java");
                System.out.println("I love Java");
                System.out.println("I love Java");
                System.out.println("I love Java");

                // Exercise 1.2 - Advanced Approach
                System.out.println("\nExercise 1.2");
                for (int i = 1; i <= 5; i++) {
                        System.out.println("I love Java");
                }

                // Exercise 1.3: Display a pattern
                System.out.println("\nExercise 1.3");
                System.out.println("    J    a    v     v    a");
                System.out.println("    J   a a    v   v    a a");
                System.out.println("J   J  aaaaa    v v    aaaaa");
                System.out.println(" J J  a     a    v    a     a");

                // Exercise 1.4: Display a table of powers
                System.out.println("\nExercise 1.4");
                System.out.println("a    a^2    a^3    a^4");
                System.out.println("1    1      1      1");
                System.out.println("2    4      8      16");
                System.out.println("3    9      27     81");
                System.out.println("4    16     64     256");

                // Exercise 1.5: Compute an expression
                // Parentheses to group the numerator and denominator.
                System.out.println("\nExercise 1.5");
                System.out.println(
                                (7.5 * 6.5 - 4.5 * 3) / (47.5 - 5.5));

                // Exercise 1.6: Add the integers from 1 through 10
                System.out.println("\nExercise 1.6");
                System.out.println(
                                1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10);

                // Exercise 1.7: Approximate pi using two expressions
                System.out.println("\nExercise 1.7");

                System.out.println(
                                4 * (1.0 - 1.0 / 3 + 1.0 / 5
                                                - 1.0 / 7 + 1.0 / 9 - 1.0 / 11));

                System.out.println(
                                4 * (1.0 - 1.0 / 3 + 1.0 / 5
                                                - 1.0 / 7 + 1.0 / 9 - 1.0 / 11
                                                + 1.0 / 13));

                // Exercise 1.8: Circle perimeter and area
                // Radius = 6.5; pi=3.14159.
                System.out.println("\nExercise 1.8");
                System.out.println("Perimeter: " + (2 * 6.5 * 3.14159));
                System.out.println("Area: " + (6.5 * 6.5 * 3.14159));

                // Exercise 1.9: Rectangle area and perimeter
                // Width = 5.3; height = 8.6.
                System.out.println("\nExercise 1.9");
                System.out.println("Area: " + (5.3 * 8.6));
                System.out.println("Perimeter: " + (2 * (5.3 + 8.6)));

                // Exercise 1.10: Average speed in miles per hour
                // Convert 15 kilometers to miles.
                // Convert 50 minutes and 30 seconds to hours.
                System.out.println("\nExercise 1.10");
                System.out.println(
                                "Average speed: "
                                                + ((15 / 1.6) / (50 / 60.0 + 30 / 3600.0))
                                                + " miles per hour");

                // Exercise 1.11: Project population for five years
                System.out.println("\nExercise 1.11");

                long initialPopulation = 312032486L;
                long secondsPerYear = 365L * 24 * 60 * 60;

                double annualBirths = secondsPerYear / 7.0;
                double annualDeaths = secondsPerYear / 13.0;
                double annualImmigrants = secondsPerYear / 45.0;

                double annualChange = annualBirths - annualDeaths + annualImmigrants;

                for (int year = 1; year <= 5; year++) {

                        long projectedPopulation = (long) (initialPopulation + year * annualChange);

                        System.out.println(
                                        "Year " + year + ": " + projectedPopulation);
                }

                // Exercise 1.12: Average speed in kilometers per hour
                // Convert 24 miles to kilometers.
                // Convert 1 hour and 40 minutes and 35 seconds to hours.
                System.out.println("\nExercise 1.12");
                System.out.println(
                                "Average speed: "
                                                + ((24 * 1.6) / (1 + 40 / 60.0 + 35 / 3600.0))
                                                + " kilometers per hour");

        }
}
