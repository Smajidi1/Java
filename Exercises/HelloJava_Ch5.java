/* Introduction to Java Programming and Data Structures (Comprehensive Version) 
11th Edition 2018*/

// Chapter 5 Exercises - Loops
package Exercises;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class HelloJava_Ch5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US);
        System.out.println("Chapter 5: Loops");
        System.out.print("Choose an exercise (1-51), or 0 to run all: ");
        while (!input.hasNextInt()) {
            if (!input.hasNextLine()) {
                input.close();
                return;
            }
            input.nextLine();
            System.out.print("Please enter an integer from 0 through 51: ");
        }
        int selected = input.nextInt();
        input.nextLine();
        if (selected < 0 || selected > 51) {
            System.out.println("Invalid exercise number: run again and choose 0-51.");
            input.close();
            return;
        }

        // Exercise 5.1: Repeat pass-or-fail checks until -1
        if (selected == 0 || selected == 1) {
            System.out.println("\nExercise 5.1: Repeat pass-or-fail checks until -1");
            // -1 is a sentinel: test it before processing the score.
            int count = 0;
            while (true) {
                System.out.print("Enter a numeric Java score, or -1 to finish this exercise: ");
                double score = Double.parseDouble(input.nextLine().trim());
                if (score == -1) {
                    break;
                }
                System.out.println(score >= 60 ? "You pass the exam." : "You don't pass the exam.");
                count++;
            }
            if (count == 0) {
                // The sample mentions 0, but this exercise's sentinel is actually -1.
                System.out.println("No scores were entered.");
            } else {
                System.out.println("Scores processed: " + count);
            }
        }

        // Exercise 5.2: Answer ten multiplication questions
        if (selected == 0 || selected == 2) {
            System.out.println("\nExercise 5.2: Answer ten multiplication questions");
            int correctCount = 0;
            long startTime = System.nanoTime();
            for (int question = 1; question <= 10; question++) {
                int first = (int) (Math.random() * 12) + 1;
                int second = (int) (Math.random() * 12) + 1;
                System.out.print("Question " + question + ": " + first + " * " + second + " = ");
                int answer = Integer.parseInt(input.nextLine().trim());
                if (answer == first * second) {
                    System.out.println("Correct!");
                    correctCount++;
                } else {
                    System.out.println("Incorrect. The answer is " + (first * second) + ".");
                }
            }
            double seconds = (System.nanoTime() - startTime) / 1_000_000_000.0;
            System.out.println("Correct count: " + correctCount + " out of 10");
            System.out.printf(Locale.US, "Test time: %.2f seconds%n", seconds);
        }

        // Exercise 5.3: Display a Celsius-to-Fahrenheit table
        if (selected == 0 || selected == 3) {
            System.out.println("\nExercise 5.3: Display a Celsius-to-Fahrenheit table");
            System.out.printf("%-10s %12s%n", "Celsius", "Fahrenheit");
            for (int celsius = 0; celsius <= 100; celsius += 2) {
                // 9.0 / 5 prevents integer division.
                double fahrenheit = celsius * 9.0 / 5 + 32;
                System.out.printf(Locale.US, "%-10d %12.1f%n", celsius, fahrenheit);
            }
        }

        // Exercise 5.4: Display an inch-to-centimeter table
        if (selected == 0 || selected == 4) {
            System.out.println("\nExercise 5.4: Display an inch-to-centimeter table");
            System.out.printf("%-10s %12s%n", "Inches", "Centimeters");
            for (int inches = 1; inches <= 10; inches++) {
                System.out.printf(Locale.US, "%-10d %12.2f%n", inches, inches * 2.54);
            }
        }

        // Exercise 5.5: Display two temperature conversion tables
        if (selected == 0 || selected == 5) {
            System.out.println("\nExercise 5.5: Display two temperature conversion tables");
            System.out.printf("%-8s %12s | %-12s %10s%n", "Celsius", "Fahrenheit", "Fahrenheit", "Celsius");
            for (int row = 0; row <= 50; row++) {
                int celsius = row * 2;
                int fahrenheit = 20 + row * 5;
                double convertedF = celsius * 9.0 / 5 + 32;
                double convertedC = (fahrenheit - 32) * 5.0 / 9;
                System.out.printf(Locale.US, "%-8d %12.3f | %-12d %10.3f%n",
                        celsius, convertedF, fahrenheit, convertedC);
            }
        }

        // Exercise 5.6: Display ping and square-meter conversion tables
        if (selected == 0 || selected == 6) {
            System.out.println("\nExercise 5.6: Display ping and square-meter conversion tables");
            System.out.printf("%-8s %12s | %-12s %10s%n", "Ping", "Square meter", "Square meter", "Ping");
            for (int row = 0; row <= 14; row++) {
                int ping = 10 + row * 5;
                int squareMeters = 30 + row * 5;
                System.out.printf(Locale.US, "%-8d %12.3f | %-12d %10.3f%n",
                        ping, ping * 3.305, squareMeters, squareMeters / 3.305);
            }
        }

        // Exercise 5.7: Compute future tuition
        if (selected == 0 || selected == 7) {
            System.out.println("\nExercise 5.7: Compute future tuition");
            double tuition = 10000;
            for (int year = 1; year <= 10; year++) {
                tuition *= 1.06;
            }
            System.out.printf(Locale.US, "Tuition in year 10: $%.2f%n", tuition);
            double total = 0;
            // Interpret "after the tenth year" as years 11, 12, 13, and 14.
            for (int year = 11; year <= 14; year++) {
                tuition *= 1.06;
                total += tuition;
            }
            System.out.printf(Locale.US, "Total tuition for years 11-14: $%.2f%n", total);
        }

        // Exercise 5.8: Find the student with the highest score
        if (selected == 0 || selected == 8) {
            System.out.println("\nExercise 5.8: Find the student with the highest score");
            System.out.print("Enter the positive integer number of students: ");
            int students = Integer.parseInt(input.nextLine().trim());
            if (students < 1) {
                System.out.println("At least one student is required.");
            } else {
                String highestName = "";
                double highestScore = Double.NEGATIVE_INFINITY;
                for (int i = 1; i <= students; i++) {
                    // The problem explicitly requires next(), so names here are one word.
                    System.out.print("Enter student " + i + " name (one word): ");
                    String name = input.next();
                    System.out.print("Enter " + name + "'s numeric score: ");
                    double score = input.nextDouble();
                    if (score > highestScore) {
                        highestScore = score;
                        highestName = name;
                    }
                }
                // Consume the line ending left by nextDouble before later nextLine calls.
                input.nextLine();
                // For a tie, retain the first student entered at the highest score.
                System.out.println("Highest score: " + highestName + " (" + highestScore + ")");
            }
        }

        // Exercise 5.9: Find the two students with the lowest scores
        if (selected == 0 || selected == 9) {
            System.out.println("\nExercise 5.9: Find the two students with the lowest scores");
            System.out.print("Enter the integer number of students (at least 2): ");
            int students = Integer.parseInt(input.nextLine().trim());
            if (students < 2) {
                System.out.println("At least two students are required.");
            } else {
                String lowestName = "";
                String secondName = "";
                double lowest = Double.POSITIVE_INFINITY;
                double secondLowest = Double.POSITIVE_INFINITY;
                for (int i = 1; i <= students; i++) {
                    System.out.print("Enter student " + i + " name: ");
                    String name = input.nextLine().trim();
                    System.out.print("Enter the numeric score: ");
                    double score = Double.parseDouble(input.nextLine().trim());
                    if (score < lowest) {
                        secondLowest = lowest;
                        secondName = lowestName;
                        lowest = score;
                        lowestName = name;
                    } else if (score < secondLowest) {
                        secondLowest = score;
                        secondName = name;
                    }
                }
                // These are two student records, not necessarily two distinct score values.
                // Equal scores retain input order.
                System.out.println("Lowest: " + lowestName + " (" + lowest + ")");
                System.out.println("Second-lowest: " + secondName + " (" + secondLowest + ")");
            }
        }

        // Exercise 5.10: Display numbers divisible by both 3 and 4
        if (selected == 0 || selected == 10) {
            System.out.println("\nExercise 5.10: Display numbers divisible by both 3 and 4");
            int count = 0;
            for (int number = 100; number <= 1000; number++) {
                if (number % 3 == 0 && number % 4 == 0) {
                    if (count % 10 != 0) {
                        System.out.print(" ");
                    }
                    System.out.print(number);
                    count++;
                    if (count % 10 == 0) {
                        System.out.println();
                    }
                }
            }
            if (count % 10 != 0) {
                System.out.println();
            }
        }

        // Exercise 5.11: Display numbers divisible by 3 or 4, but not both
        if (selected == 0 || selected == 11) {
            System.out.println("\nExercise 5.11: Display numbers divisible by 3 or 4, but not both");
            int count = 0;
            for (int number = 100; number <= 200; number++) {
                if ((number % 3 == 0) ^ (number % 4 == 0)) {
                    if (count % 10 != 0) {
                        System.out.print(" ");
                    }
                    System.out.print(number);
                    count++;
                    if (count % 10 == 0) {
                        System.out.println();
                    }
                }
            }
            if (count % 10 != 0) {
                System.out.println();
            }
        }

        // Exercise 5.12: Find the smallest n with n cubed greater than 12000
        if (selected == 0 || selected == 12) {
            System.out.println("\nExercise 5.12: Find the smallest n with n cubed greater than 12000");
            int n = 0;
            while (n * n * n <= 12000) {
                n++;
            }
            System.out.println("Smallest n with n^3 > 12000: " + n);
            System.out.println("n^3 = " + (n * n * n));
        }

        // Exercise 5.13: Find the largest n with n squared less than 12000
        if (selected == 0 || selected == 13) {
            System.out.println("\nExercise 5.13: Find the largest n with n squared less than 12000");
            int n = 0;
            while ((n + 1) * (n + 1) < 12000) {
                n++;
            }
            System.out.println("Largest n with n^2 < 12000: " + n);
            System.out.println("n^2 = " + (n * n));
        }

        // Exercise 5.14: Find a GCD by checking divisors in descending order
        if (selected == 0 || selected == 14) {
            System.out.println("\nExercise 5.14: Find a GCD by checking divisors in descending order");
            System.out.print("Enter the first positive integer: ");
            int first = Integer.parseInt(input.nextLine().trim());
            System.out.print("Enter the second positive integer: ");
            int second = Integer.parseInt(input.nextLine().trim());
            if (first <= 0 || second <= 0) {
                System.out.println("Invalid input: both integers must be positive.");
            } else {
                int divisor = Math.min(first, second);
                // Stop at the first common divisor found while counting down.
                while (first % divisor != 0 || second % divisor != 0) {
                    divisor--;
                }
                System.out.println("The greatest common divisor is " + divisor);
            }
        }

        // Exercise 5.15: Display ASCII characters from ! through ~
        if (selected == 0 || selected == 15) {
            System.out.println("\nExercise 5.15: Display ASCII characters from ! through ~");
            int count = 0;
            for (int code = '!'; code <= '~'; code++) {
                if (count % 10 != 0) {
                    System.out.print(" ");
                }
                System.out.print((char) code);
                count++;
                if (count % 10 == 0) {
                    System.out.println();
                }
            }
            if (count % 10 != 0) {
                System.out.println();
            }
        }

        // Exercise 5.16: Display prime factors in increasing order
        if (selected == 0 || selected == 16) {
            System.out.println("\nExercise 5.16: Display prime factors in increasing order");
            System.out.print("Enter an integer greater than 1 (example: 120): ");
            int number = Integer.parseInt(input.nextLine().trim());
            if (number <= 1) {
                System.out.println("Invalid input: prime factorization here requires an integer greater than 1.");
            } else {
                int remaining = number;
                int divisor = 2;
                boolean firstFactor = true;
                System.out.print("Prime factors: ");
                while ((long) divisor * divisor <= remaining) {
                    while (remaining % divisor == 0) {
                        if (!firstFactor) {
                            System.out.print(", ");
                        }
                        System.out.print(divisor);
                        firstFactor = false;
                        remaining /= divisor;
                    }
                    divisor++;
                }
                // Any remaining value greater than 1 is itself prime.
                if (remaining > 1) {
                    if (!firstFactor) {
                        System.out.print(", ");
                    }
                    System.out.print(remaining);
                }
                System.out.println();
            }
        }

        // Exercise 5.17: Display the shrinking number pyramid
        if (selected == 0 || selected == 17) {
            System.out.println("\nExercise 5.17: Display the shrinking number pyramid");
            System.out.print("Enter the integer number of lines (1-15): ");
            int lines = Integer.parseInt(input.nextLine().trim());
            if (lines < 1 || lines > 15) {
                System.out.println("Invalid input: enter an integer from 1 to 15.");
            } else {
                // Follow the supplied sample - widest row first, with rows left-aligned.
                for (int row = lines; row >= 1; row--) {
                    for (int value = row; value >= 1; value--) {
                        if (value != row) {
                            System.out.print(" ");
                        }
                        System.out.print(value);
                    }
                    for (int value = 2; value <= row; value++) {
                        System.out.print(" " + value);
                    }
                    System.out.println();
                }
            }
        }

        // Exercise 5.18: Display four star patterns using nested loops
        if (selected == 0 || selected == 18) {
            System.out.println("\nExercise 5.18: Display four star patterns using nested loops");
            System.out.println("Pattern A");
            for (int row = 1; row <= 6; row++) {
                for (int star = 1; star <= row; star++) {
                    System.out.print(star == 1 ? "*" : " *");
                }
                System.out.println();
            }
            System.out.println("\nPattern B");
            for (int row = 6; row >= 1; row--) {
                for (int star = 1; star <= row; star++) {
                    System.out.print(star == 1 ? "*" : " *");
                }
                System.out.println();
            }
            System.out.println("\nPattern C");
            for (int row = 1; row <= 6; row++) {
                for (int space = 0; space < 6 - row; space++) {
                    System.out.print("  ");
                }
                for (int star = 1; star <= row; star++) {
                    System.out.print(star == 1 ? "*" : " *");
                }
                System.out.println();
            }
            System.out.println("\nPattern D");
            for (int row = 6; row >= 1; row--) {
                for (int space = 0; space < 6 - row; space++) {
                    System.out.print("  ");
                }
                for (int star = 1; star <= row; star++) {
                    System.out.print(star == 1 ? "*" : " *");
                }
                System.out.println();
            }
        }

        // Exercise 5.19: Display a pyramid of powers of 3
        if (selected == 0 || selected == 19) {
            System.out.println("\nExercise 5.19: Display a pyramid of powers of 3");
            for (int row = 0; row < 8; row++) {
                for (int blank = 0; blank < 7 - row; blank++) {
                    System.out.print("     ");
                }
                int value = 1;
                for (int column = 0; column <= row; column++) {
                    System.out.printf("%5d", value);
                    value *= 3;
                }
                // value is now three times the peak; divide by 9 to start descending.
                value /= 9;
                for (int column = row - 1; column >= 0; column--) {
                    System.out.printf("%5d", value);
                    value /= 3;
                }
                System.out.println();
            }
        }

        // Exercise 5.20: Display all primes from 2 through 1200
        if (selected == 0 || selected == 20) {
            System.out.println("\nExercise 5.20: Display all primes from 2 through 1200");
            int count = 0;
            for (int number = 2; number <= 1200; number++) {
                boolean prime = true;
                for (int divisor = 2; divisor * divisor <= number; divisor++) {
                    if (number % divisor == 0) {
                        prime = false;
                        break;
                    }
                }
                if (prime) {
                    if (count % 8 != 0) {
                        System.out.print(" ");
                    }
                    System.out.print(number);
                    count++;
                    if (count % 8 == 0) {
                        System.out.println();
                    }
                }
            }
            if (count % 8 != 0) {
                System.out.println();
            }
            System.out.println("Number of primes: " + count);
        }

        // Exercise 5.21: Compare loan payments at different interest rates
        if (selected == 0 || selected == 21) {
            System.out.println("\nExercise 5.21: Compare loan payments at different interest rates");
            System.out.print("Enter the loan amount in dollars (example: 10000): ");
            double loan = Double.parseDouble(input.nextLine().trim());
            System.out.print("Enter the positive integer number of years (example: 5): ");
            int years = Integer.parseInt(input.nextLine().trim());
            if (loan <= 0 || years <= 0) {
                System.out.println("Invalid input: loan amount and years must be positive.");
            } else {
                long months = years * 12L;
                System.out.printf("%-15s %18s %18s%n", "Interest Rate", "Monthly Payment", "Total Payment");
                // Integer quarter-percentage steps avoid accumulating floating-point steps.
                for (int quarter = 20; quarter <= 40; quarter++) {
                    double annualRate = quarter / 4.0;
                    double monthlyRate = annualRate / 1200;
                    double monthlyPayment = loan * monthlyRate
                            / (1 - Math.pow(1 + monthlyRate, -months));
                    double totalPayment = monthlyPayment * months;
                    System.out.printf(Locale.US, "%7.3f%% %25.2f %18.2f%n",
                            annualRate, monthlyPayment, totalPayment);
                }
            }
        }

        // Exercise 5.22: Display a loan amortization schedule
        if (selected == 0 || selected == 22) {
            System.out.println("\nExercise 5.22: Display a loan amortization schedule");
            System.out.print("Enter the positive loan amount in dollars (example: 10000): ");
            BigDecimal loan = new BigDecimal(input.nextLine().trim()).setScale(2, RoundingMode.HALF_UP);
            System.out.print("Enter the positive integer number of years (example: 1): ");
            int years = Integer.parseInt(input.nextLine().trim());
            System.out.print("Enter the nonnegative annual percentage rate (example: 7): ");
            BigDecimal annualRate = new BigDecimal(input.nextLine().trim());
            if (loan.signum() <= 0 || years <= 0 || annualRate.signum() < 0) {
                System.out.println("Invalid loan amount, period, or interest rate.");
            } else {
                long months = years * 12L;
                double monthlyRate = annualRate.doubleValue() / 1200;
                double exactPayment = monthlyRate == 0 ? loan.doubleValue() / months
                        : loan.doubleValue() * monthlyRate / (1 - Math.pow(1 + monthlyRate, -months));
                long normalPaymentCents = BigDecimal.valueOf(exactPayment)
                        .setScale(2, RoundingMode.HALF_UP).movePointRight(2).longValueExact();
                if (normalPaymentCents < 1) {
                    System.out.println("The calculated payment is below one cent; use a shorter period.");
                } else {
                    long balanceCents = loan.movePointRight(2).longValueExact();
                    long totalPaidCents = 0;
                    System.out.printf(Locale.US, "Normal monthly payment: $%.2f%n", normalPaymentCents / 100.0);
                    System.out.printf("%-10s %12s %12s %12s %14s%n",
                            "Payment#", "Payment", "Interest", "Principal", "Balance");
                    for (long month = 1; month <= months && balanceCents > 0; month++) {
                        long interestCents = BigDecimal.valueOf(balanceCents).multiply(annualRate)
                                .divide(BigDecimal.valueOf(1200), 0, RoundingMode.HALF_UP).longValueExact();
                        long paymentCents = normalPaymentCents;
                        if (month == months || paymentCents > balanceCents + interestCents) {
                            // Adjust the final payment to retire the remaining balance exactly.
                            paymentCents = balanceCents + interestCents;
                        }
                        long principalCents = paymentCents - interestCents;
                        balanceCents -= principalCents;
                        totalPaidCents += paymentCents;
                        System.out.printf(Locale.US, "%-10d %12.2f %12.2f %12.2f %14.2f%n", month,
                                paymentCents / 100.0, interestCents / 100.0,
                                principalCents / 100.0, balanceCents / 100.0);
                    }
                    System.out.printf(Locale.US, "Total actually paid: $%.2f%n", totalPaidCents / 100.0);
                    // The book truncates some displayed cents. Here amounts are rounded,
                    // each row balances, and the last balance is exactly $0.00.
                }
            }
        }

        // Exercise 5.23: Compare two orders of harmonic summation
        if (selected == 0 || selected == 23) {
            System.out.println("\nExercise 5.23: Compare two orders of harmonic summation");
            int n = 50000;
            double leftToRight = 0;
            double rightToLeft = 0;
            for (int i = 1; i <= n; i++) {
                leftToRight += 1.0 / i;
            }
            for (int i = n; i >= 1; i--) {
                rightToLeft += 1.0 / i;
            }
            System.out.printf(Locale.US, "Left to right: %.15f%n", leftToRight);
            System.out.printf(Locale.US, "Right to left: %.15f%n", rightToLeft);
            System.out.printf(Locale.US, "Absolute difference: %.3e%n", Math.abs(leftToRight - rightToLeft));
        }

        // Exercise 5.24: Sum the odd-numerator series
        if (selected == 0 || selected == 24) {
            System.out.println("\nExercise 5.24: Sum the odd-numerator series");
            double sum = 0;
            for (int numerator = 1; numerator <= 97; numerator += 2) {
                sum += numerator / (double) (numerator + 2);
            }
            System.out.println("Sum of 1/3 + 3/5 + ... + 97/99: " + sum);
        }

        // Exercise 5.25: Approximate pi at successive term counts
        if (selected == 0 || selected == 25) {
            System.out.println("\nExercise 5.25: Approximate pi at successive term counts");
            double sum = 0;
            int sign = 1;
            System.out.printf("%-12s %20s%n", "Terms", "Approximation of pi");
            for (int i = 1; i <= 100000; i++) {
                sum += sign / (2.0 * i - 1);
                sign = -sign;
                if (i % 10000 == 0) {
                    System.out.printf(Locale.US, "%-12d %20.15f%n", i, 4 * sum);
                }
            }
        }

        // Exercise 5.26: Approximate e using reciprocal factorials
        if (selected == 0 || selected == 26) {
            System.out.println("\nExercise 5.26: Approximate e using reciprocal factorials");
            double e = 1; // Includes 1/0.
            double term = 1;
            System.out.printf("%-12s %20s%n", "i", "Approximation of e");
            for (int i = 1; i <= 100000; i++) {
                term /= i; // From 1/(i-1)! to 1/i! without building a huge factorial.
                e += term; // i = 1 adds 1/1!, which must not be omitted.
                if (i % 10000 == 0) {
                    System.out.printf(Locale.US, "%-12d %20.15f%n", i, e);
                }
            }
            System.out.println("Later rows agree because terms become too small to change a double.");
        }

        // Exercise 5.27: Display leap years from 2014 through 2114
        if (selected == 0 || selected == 27) {
            System.out.println("\nExercise 5.27: Display leap years from 2014 through 2114");
            int count = 0;
            for (int year = 2014; year <= 2114; year++) {
                boolean leapYear = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
                if (leapYear) {
                    if (count % 10 != 0) {
                        System.out.print(" ");
                    }
                    System.out.print(year);
                    count++;
                    if (count % 10 == 0) {
                        System.out.println();
                    }
                }
            }
            if (count % 10 != 0) {
                System.out.println();
            }
            System.out.println("Number of leap years: " + count);
        }

        // Exercise 5.28: Display the first weekday of every month
        if (selected == 0 || selected == 28) {
            System.out.println("\nExercise 5.28: Display the first weekday of every month");
            System.out.print("Enter a positive integer year (example: 2013): ");
            int year = Integer.parseInt(input.nextLine().trim());
            System.out.print("Enter January 1's weekday: Sunday=0, Monday=1, ..., Saturday=6: ");
            int firstDay = Integer.parseInt(input.nextLine().trim());
            if (year < 1 || firstDay < 0 || firstDay > 6) {
                System.out.println("Invalid year or weekday.");
            } else {
                // Use the supplied weekday as the starting assumption and no need to calculate
                // it.
                boolean leapYear = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
                for (int month = 1; month <= 12; month++) {
                    String monthName;
                    int days;
                    switch (month) {
                        case 1:
                            monthName = "January";
                            days = 31;
                            break;
                        case 2:
                            monthName = "February";
                            days = leapYear ? 29 : 28;
                            break;
                        case 3:
                            monthName = "March";
                            days = 31;
                            break;
                        case 4:
                            monthName = "April";
                            days = 30;
                            break;
                        case 5:
                            monthName = "May";
                            days = 31;
                            break;
                        case 6:
                            monthName = "June";
                            days = 30;
                            break;
                        case 7:
                            monthName = "July";
                            days = 31;
                            break;
                        case 8:
                            monthName = "August";
                            days = 31;
                            break;
                        case 9:
                            monthName = "September";
                            days = 30;
                            break;
                        case 10:
                            monthName = "October";
                            days = 31;
                            break;
                        case 11:
                            monthName = "November";
                            days = 30;
                            break;
                        default:
                            monthName = "December";
                            days = 31;
                    }

                    String dayName;
                    switch (firstDay) {
                        case 0:
                            dayName = "Sunday";
                            break;
                        case 1:
                            dayName = "Monday";
                            break;
                        case 2:
                            dayName = "Tuesday";
                            break;
                        case 3:
                            dayName = "Wednesday";
                            break;
                        case 4:
                            dayName = "Thursday";
                            break;
                        case 5:
                            dayName = "Friday";
                            break;
                        default:
                            dayName = "Saturday";
                    }
                    System.out.println(monthName + " 1, " + year + " is " + dayName);

                    // Advance from this month's first day to the next month's first day.
                    firstDay = (firstDay + days) % 7;
                }
            }
        }

        // Exercise 5.29: Display the full calendar for a year
        if (selected == 0 || selected == 29) {
            System.out.println("\nExercise 5.29: Display the full calendar for a year");
            System.out.print("Enter a positive integer year (example: 2013): ");
            int year = Integer.parseInt(input.nextLine().trim());
            System.out.print("Enter January 1's weekday: Sunday=0, Monday=1, ..., Saturday=6: ");
            int firstDay = Integer.parseInt(input.nextLine().trim());
            if (year < 1 || firstDay < 0 || firstDay > 6) {
                System.out.println("Invalid year or weekday.");
            } else {
                // Use the supplied weekday as the starting assumption
                boolean leapYear = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
                for (int month = 1; month <= 12; month++) {
                    String monthName;
                    int days;
                    switch (month) {
                        case 1:
                            monthName = "January";
                            days = 31;
                            break;
                        case 2:
                            monthName = "February";
                            days = leapYear ? 29 : 28;
                            break;
                        case 3:
                            monthName = "March";
                            days = 31;
                            break;
                        case 4:
                            monthName = "April";
                            days = 30;
                            break;
                        case 5:
                            monthName = "May";
                            days = 31;
                            break;
                        case 6:
                            monthName = "June";
                            days = 30;
                            break;
                        case 7:
                            monthName = "July";
                            days = 31;
                            break;
                        case 8:
                            monthName = "August";
                            days = 31;
                            break;
                        case 9:
                            monthName = "September";
                            days = 30;
                            break;
                        case 10:
                            monthName = "October";
                            days = 31;
                            break;
                        case 11:
                            monthName = "November";
                            days = 30;
                            break;
                        default:
                            monthName = "December";
                            days = 31;
                    }

                    System.out.println("\n             " + monthName + " " + year);
                    System.out.println("-----------------------------------");
                    System.out.println("  Sun  Mon  Tue  Wed  Thu  Fri  Sat");
                    for (int blank = 0; blank < firstDay; blank++) {
                        System.out.print("     ");
                    }
                    for (int day = 1; day <= days; day++) {
                        System.out.printf("%5d", day);
                        if ((firstDay + day) % 7 == 0) {
                            System.out.println();
                        }
                    }
                    if ((firstDay + days) % 7 != 0) {
                        System.out.println();
                    }

                    // Advance from this month's first day to the next month's first day.
                    firstDay = (firstDay + days) % 7;
                }
            }
        }

        // Exercise 5.30: Compute savings after monthly deposits
        if (selected == 0 || selected == 30) {
            System.out.println("\nExercise 5.30: Compute savings after monthly deposits");
            System.out.print("Enter the monthly deposit amount (example: 100): ");
            double deposit = Double.parseDouble(input.nextLine().trim());
            System.out.print("Enter the nonnegative annual interest percentage (example: 3.75): ");
            double annualRate = Double.parseDouble(input.nextLine().trim());
            System.out.print("Enter the nonnegative integer number of months (example: 6): ");
            int months = Integer.parseInt(input.nextLine().trim());
            if (deposit < 0 || annualRate < 0 || months < 0) {
                System.out.println("Invalid input: deposit, rate, and months must be nonnegative.");
            } else {
                double monthlyRate = annualRate / 1200;
                double balance = 0;
                for (int month = 1; month <= months; month++) {
                    balance = (balance + deposit) * (1 + monthlyRate);
                }
                System.out.printf(Locale.US, "Savings after %d months: $%.2f%n", months, balance);
            }
        }

        // Exercise 5.31: Display monthly CD values
        if (selected == 0 || selected == 31) {
            System.out.println("\nExercise 5.31: Display monthly CD values");
            System.out.print("Enter the initial CD deposit (example: 10000): ");
            double balance = Double.parseDouble(input.nextLine().trim());
            System.out.print("Enter the annual percentage supplied by the exercise (example: 6.15): ");
            double annualRate = Double.parseDouble(input.nextLine().trim());
            System.out.print("Enter the nonnegative integer number of months (example: 18): ");
            int months = Integer.parseInt(input.nextLine().trim());
            if (balance < 0 || annualRate < 0 || months < 0) {
                System.out.println("Invalid input: amount, rate, and months must be nonnegative.");
            } else {
                double monthlyRate = annualRate / 1200;
                System.out.printf("%-10s %14s%n", "Month", "CD Value");
                for (int month = 1; month <= months; month++) {
                    balance *= 1 + monthlyRate;
                    System.out.printf(Locale.US, "%-10d %14.2f%n", month, balance);
                }
            }
        }

        // Exercise 5.32: Generate a two-digit lottery with distinct digits
        if (selected == 0 || selected == 32) {
            System.out.println("\nExercise 5.32: Generate a two-digit lottery with distinct digits");
            int firstDigit = (int) (Math.random() * 9) + 1;
            int secondDigit = (int) (Math.random() * 10);
            while (secondDigit == firstDigit) {
                secondDigit = (int) (Math.random() * 10);
            }
            int lottery = firstDigit * 10 + secondDigit;
            System.out.print("Enter a two-digit lottery guess (10-99): ");
            int guess = Integer.parseInt(input.nextLine().trim());
            if (guess < 10 || guess > 99) {
                System.out.println("Invalid guess: enter an integer from 10 to 99.");
            } else {
                int guessFirst = guess / 10;
                int guessSecond = guess % 10;
                System.out.println("The lottery number is " + lottery);
                if (guess == lottery) {
                    System.out.println("Exact match: you win $10,000.");
                } else if (guessFirst == secondDigit && guessSecond == firstDigit) {
                    System.out.println("Both digits match in reverse order: you win $3,000.");
                } else if (guessFirst == firstDigit || guessFirst == secondDigit
                        || guessSecond == firstDigit || guessSecond == secondDigit) {
                    System.out.println("One digit matches: you win $1,000.");
                } else {
                    System.out.println("No matching digits. No prize.");
                }
            }
        }

        // Exercise 5.33: Find the perfect numbers below 10000
        if (selected == 0 || selected == 33) {
            System.out.println("\nExercise 5.33: Find the perfect numbers below 10000");
            int count = 0;
            for (int number = 2; number < 10000; number++) {
                int divisorSum = 1;
                for (int divisor = 2; divisor * divisor <= number; divisor++) {
                    if (number % divisor == 0) {
                        divisorSum += divisor;
                        int partner = number / divisor;
                        if (partner != divisor) {
                            divisorSum += partner;
                        }
                    }
                }
                if (divisorSum == number) {
                    System.out.println(number);
                    count++;
                }
            }
            System.out.println("Perfect numbers found: " + count);
        }

        // Exercise 5.34: Play scissor, rock, paper until a three-win lead
        if (selected == 0 || selected == 34) {
            System.out.println("\nExercise 5.34: Play scissor, rock, paper until a three-win lead");
            // Interpret the ambiguous "three times more" as a lead of three wins,
            // not a 3:1 ratio. Draws do not change either win count.
            int playerWins = 0;
            int computerWins = 0;
            System.out.println("The game ends when either side leads by 3 wins; -1 stops it early.");
            while (Math.abs(playerWins - computerWins) < 3) {
                System.out.print("Enter scissor (0), rock (1), paper (2), or -1 to stop: ");
                int player = Integer.parseInt(input.nextLine().trim());
                if (player == -1) {
                    break;
                }
                if (player < 0 || player > 2) {
                    System.out.println("Invalid choice: enter 0, 1, 2, or -1.");
                    continue;
                }
                int computer = (int) (Math.random() * 3);
                String playerName = player == 0 ? "scissor" : player == 1 ? "rock" : "paper";
                String computerName = computer == 0 ? "scissor" : computer == 1 ? "rock" : "paper";
                System.out.println("You: " + playerName + "; computer: " + computerName);
                if (player == computer) {
                    System.out.println("Draw.");
                } else if ((player - computer + 3) % 3 == 1) {
                    playerWins++;
                    System.out.println("You win this round.");
                } else {
                    computerWins++;
                    System.out.println("The computer wins this round.");
                }
                System.out.println("Wins - you: " + playerWins + ", computer: " + computerWins);
            }
            if (playerWins - computerWins >= 3) {
                System.out.println("You won the match with a lead of three wins.");
            } else if (computerWins - playerWins >= 3) {
                System.out.println("The computer won the match with a lead of three wins.");
            } else {
                System.out.println("Game stopped before either side reached a three-win lead.");
            }
        }

        // Exercise 5.35: Sum the square-root denominator series
        if (selected == 0 || selected == 35) {
            System.out.println("\nExercise 5.35: Sum the square-root denominator series");
            double sum = 0;
            for (int i = 1; i <= 999; i++) {
                sum += 1.0 / (Math.sqrt(i) + Math.sqrt(i + 1));
            }
            System.out.println("The sum is " + sum);
            // Rationalizing each term shows that the exact sum is sqrt(1000) - 1.
            System.out.println("Telescoping check, sqrt(1000) - 1: " + (Math.sqrt(1000) - 1));
        }

        // Exercise 5.36: Compute ISBN-10 using a digit-extraction loop
        if (selected == 0 || selected == 36) {
            System.out.println("\nExercise 5.36: Compute ISBN-10 using a digit-extraction loop");
            System.out.print("Enter the first nine ISBN digits as an integer (example: 013601267): ");
            int prefix = Integer.parseInt(input.nextLine().trim());
            if (prefix < 0 || prefix > 999999999) {
                System.out.println("Invalid prefix: enter an integer from 0 to 999999999.");
            } else {
                int remaining = prefix;
                int sum = 0;
                // Extract rightmost digits first, with weights 9 down to 1.
                for (int weight = 9; weight >= 1; weight--) {
                    sum += (remaining % 10) * weight;
                    remaining /= 10;
                }
                int checksum = sum % 11;
                String lastDigit = checksum == 10 ? "X" : "" + checksum;
                System.out.printf(Locale.US, "The ISBN-10 number is %09d%s%n", prefix, lastDigit);
            }
        }

        // Exercise 5.37: Convert a decimal integer to binary using a loop
        if (selected == 0 || selected == 37) {
            System.out.println("\nExercise 5.37: Convert a decimal integer to binary using a loop");
            System.out.print("Enter a decimal integer (example: 5): ");
            int number = Integer.parseInt(input.nextLine().trim());
            // Cast before abs so Integer.MIN_VALUE is handled without overflow.
            long remaining = Math.abs((long) number);
            String binary = "";
            do {
                binary = (remaining % 2) + binary;
                remaining /= 2;
            } while (remaining > 0);
            if (number < 0) {
                binary = "-" + binary;
            }
            System.out.println("Binary value: " + binary);
        }

        // Exercise 5.38: Convert a decimal integer to octal using a loop
        if (selected == 0 || selected == 38) {
            System.out.println("\nExercise 5.38: Convert a decimal integer to octal using a loop");
            System.out.print("Enter a decimal integer (example: 64): ");
            int number = Integer.parseInt(input.nextLine().trim());
            long remaining = Math.abs((long) number);
            String octal = "";
            do {
                octal = (remaining % 8) + octal;
                remaining /= 8;
            } while (remaining > 0);
            if (number < 0) {
                octal = "-" + octal;
            }
            System.out.println("Octal value: " + octal);
        }

        // Exercise 5.39: Find sales needed for total earnings of $30000
        if (selected == 0 || selected == 39) {
            System.out.println("\nExercise 5.39: Find sales needed for total earnings of $30000");
            long salaryDollars = 5000;
            long targetPayDollars = 30000;
            long targetCommission = (targetPayDollars - salaryDollars) * 10000;
            long salesCents = 0;
            long commissionScaled;
            do {
                salesCents += 100;
                commissionScaled = salesCents <= 500000 ? salesCents * 6
                        : salesCents <= 1000000 ? 3000000 + (salesCents - 500000) * 8
                                : 7000000 + (salesCents - 1000000) * 10;
            } while (commissionScaled < targetCommission);
            salesCents = Math.max(0, salesCents - 100);
            commissionScaled = salesCents <= 500000 ? salesCents * 6
                    : salesCents <= 1000000 ? 3000000 + (salesCents - 500000) * 8
                            : 7000000 + (salesCents - 1000000) * 10;
            if (commissionScaled < targetCommission) {
                do {
                    salesCents++;
                    commissionScaled = salesCents <= 500000 ? salesCents * 6
                            : salesCents <= 1000000 ? 3000000 + (salesCents - 500000) * 8
                                    : 7000000 + (salesCents - 1000000) * 10;
                } while (commissionScaled < targetCommission);
            }
            System.out.printf(Locale.US, "Minimum sales, to the cent: $%.2f%n", salesCents / 100.0);
            System.out.printf(Locale.US, "Commission: $%.2f%n", commissionScaled / 10000.0);
            System.out.printf(Locale.US, "Base salary plus commission: $%.2f%n",
                    salaryDollars + commissionScaled / 10000.0);
        }

        // Exercise 5.40: Simulate two million coin flips
        if (selected == 0 || selected == 40) {
            System.out.println("\nExercise 5.40: Simulate two million coin flips");
            int flips = 2_000_000;
            int heads = 0;
            for (int flip = 0; flip < flips; flip++) {
                if ((int) (Math.random() * 2) == 0) {
                    heads++;
                }
            }
            int tails = flips - heads;
            System.out.println("Heads: " + heads);
            System.out.println("Tails: " + tails);
            System.out.println("Total flips: " + (heads + tails));
        }

        // Exercise 5.41: Count occurrences of the largest entered integer
        if (selected == 0 || selected == 41) {
            System.out.println("\nExercise 5.41: Count occurrences of the largest entered integer");
            System.out.println("Enter integers separated by spaces or newlines; finish with 0.");
            int maximum = Integer.MIN_VALUE;
            int count = 0;
            while (true) {
                int number = input.nextInt();
                if (number == 0) {
                    break;
                }
                // count == 0 handles the first value, including a negative first value.
                if (count == 0 || number > maximum) {
                    maximum = number;
                    count = 1;
                } else if (number == maximum) {
                    count++;
                }
            }
            input.nextLine();
            if (count == 0) {
                System.out.println("No non-sentinel numbers were entered.");
            } else {
                System.out.println("The largest number is " + maximum);
                System.out.println("The occurrence count of the largest number is " + count);
            }
        }

        // Exercise 5.42: Find sales for a user-specified commission goal
        if (selected == 0 || selected == 42) {
            System.out.println("\nExercise 5.42: Find sales for a user-specified commission goal");
            // This input is a COMMISSION goal, excluding the $5000 base salary.
            System.out.print("Enter the commission sought in dollars (example: 25000): ");
            BigDecimal goal = new BigDecimal(input.nextLine().trim());
            if (goal.signum() < 0) {
                System.out.println("Invalid commission goal: use a nonnegative amount.");
            } else {
                // Commission can change by hundredths of a cent when sales change by a cent.
                long targetCommission = goal.movePointRight(4)
                        .setScale(0, RoundingMode.CEILING).longValueExact();
                long salesCents = 0;
                long commissionScaled = 0;
                // Whole-dollar steps, then cent steps, avoid a much longer cent-only search.
                while (commissionScaled < targetCommission) {
                    salesCents += 100;
                    commissionScaled = salesCents <= 500000 ? salesCents * 6
                            : salesCents <= 1000000 ? 3000000 + (salesCents - 500000) * 8
                                    : 7000000 + (salesCents - 1000000) * 10;
                }
                salesCents = Math.max(0, salesCents - 100);
                commissionScaled = salesCents <= 500000 ? salesCents * 6
                        : salesCents <= 1000000 ? 3000000 + (salesCents - 500000) * 8
                                : 7000000 + (salesCents - 1000000) * 10;
                while (commissionScaled < targetCommission) {
                    salesCents++;
                    commissionScaled = salesCents <= 500000 ? salesCents * 6
                            : salesCents <= 1000000 ? 3000000 + (salesCents - 500000) * 8
                                    : 7000000 + (salesCents - 1000000) * 10;
                }
                System.out.printf(Locale.US, "Minimum sales, to the cent: $%.2f%n", salesCents / 100.0);
                System.out.printf(Locale.US, "Calculated commission: $%.4f%n", commissionScaled / 10000.0);
            }
        }

        // Exercise 5.43: Display every combination of two distinct integers from 1 to 7
        if (selected == 0 || selected == 43) {
            System.out.println("\nExercise 5.43: Display every combination of two distinct integers from 1 to 7");
            int count = 0;
            for (int first = 1; first <= 7; first++) {
                for (int second = first + 1; second <= 7; second++) {
                    System.out.println(first + " " + second);
                    count++;
                }
            }
            System.out.println("The total number of combinations is " + count);
        }

        // Exercise 5.44: Display the eight bits of a signed byte integer
        if (selected == 0 || selected == 44) {
            System.out.println("\nExercise 5.44: Display the eight bits of a signed byte integer");
            System.out.print("Enter a byte integer (-128 through 127): ");
            int number = Integer.parseInt(input.nextLine().trim());
            if (number < -128 || number > 127) {
                System.out.println("Invalid byte value: use -128 through 127.");
            } else {
                System.out.print("The 8 bits are ");
                for (int bit = 7; bit >= 0; bit--) {
                    System.out.print((number >> bit) & 1);
                }
                System.out.println();
                // Negative bytes use two's-complement representation.
                // For +5, print all 8 bits: 00000101 .
            }
        }

        // Exercise 5.45: Compute the mean and sample standard deviation of ten numbers
        if (selected == 0 || selected == 45) {
            System.out.println("\nExercise 5.45: Compute the mean and sample standard deviation of ten numbers");
            System.out.println("Enter exactly 10 numeric values, separated by spaces or newlines:");
            double reference = 0;
            double sum = 0;
            double sumSquares = 0;
            for (int i = 1; i <= 10; i++) {
                double value = input.nextDouble();
                if (i == 1) {
                    reference = value;
                }
                double shifted = value - reference;
                sum += shifted;
                sumSquares += shifted * shifted;
            }
            input.nextLine();
            double mean = reference + sum / 10;
            double variance = (sumSquares - sum * sum / 10) / 9;
            double deviation = Math.sqrt(Math.max(0, variance));
            System.out.println("The mean is " + mean);
            System.out.printf(Locale.US, "The sample standard deviation is %.5f%n", deviation);
        }

        // Exercise 5.46: Reverse a string using a loop
        if (selected == 0 || selected == 46) {
            System.out.println("\nExercise 5.46: Reverse a string using a loop");
            System.out.print("Enter a string (spaces are allowed): ");
            String text = input.nextLine();
            System.out.print("The reversed string is ");
            int index = text.length();
            while (index > 0) {
                int codePoint = text.codePointBefore(index);
                System.out.print(Character.toChars(codePoint));
                index -= Character.charCount(codePoint);
            }
            System.out.println();
        }

        // Exercise 5.47: Compute an ISBN-13 checksum
        if (selected == 0 || selected == 47) {
            System.out.println("\nExercise 5.47: Compute an ISBN-13 checksum");
            System.out.print("Enter exactly the first 12 ISBN-13 digits as text: ");
            String prefix = input.nextLine().trim();
            boolean valid = prefix.length() == 12;
            int sum = 0;
            for (int i = 0; valid && i < prefix.length(); i++) {
                char digit = prefix.charAt(i);
                if (digit < '0' || digit > '9') {
                    valid = false;
                } else {
                    int weight = i % 2 == 0 ? 1 : 3;
                    sum += (digit - '0') * weight;
                }
            }
            if (!valid) {
                System.out.println(prefix + " is an invalid input.");
            } else {
                int checksum = (10 - sum % 10) % 10;
                System.out.println("The ISBN-13 number is " + prefix + checksum);
            }
        }

        // Exercise 5.48: Display characters at even positions
        if (selected == 0 || selected == 48) {
            System.out.println("\nExercise 5.48: Display characters at even positions");
            System.out.print("Enter a string (spaces are included in the positions): ");
            String text = input.nextLine();
            System.out.print("Characters at even positions: \"");
            int position = 1;
            int index = 0;
            while (index < text.length()) {
                int codePoint = text.codePointAt(index);
                if (position % 2 == 0) {
                    System.out.print(Character.toChars(codePoint));
                }
                index += Character.charCount(codePoint);
                position++;
            }
            System.out.println("\"");
        }

        // Exercise 5.49: Count English vowels and consonants
        if (selected == 0 || selected == 49) {
            System.out.println("\nExercise 5.49: Count English vowels and consonants");
            System.out.print("Enter a string: ");
            String text = input.nextLine();
            int vowels = 0;
            int consonants = 0;
            for (int i = 0; i < text.length(); i++) {
                char letter = Character.toUpperCase(text.charAt(i));
                // Count English letters only ignore numbers, punctuation, and spaces.
                if (letter >= 'A' && letter <= 'Z') {
                    if (letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O' || letter == 'U') {
                        vowels++;
                    } else {
                        consonants++;
                    }
                }
            }
            System.out.println("The number of vowels is " + vowels);
            System.out.println("The number of consonants is " + consonants);
        }

        // Exercise 5.50: Display multiplication tables with three kinds of loops
        if (selected == 0 || selected == 50) {
            System.out.println("\nExercise 5.50: Display multiplication tables with three kinds of loops");
            System.out.println("Using for loops:");
            for (int row = 1; row <= 9; row++) {
                for (int column = 1; column <= 9; column++) {
                    System.out.printf("%d*%d=%2d%s", column, row, column * row, column == 9 ? "" : "  ");
                }
                System.out.println();
            }
            System.out.println("\nUsing while loops:");
            int row = 1;
            while (row <= 9) {
                int column = 1;
                while (column <= 9) {
                    System.out.printf("%d*%d=%2d%s", column, row, column * row, column == 9 ? "" : "  ");
                    column++;
                }
                System.out.println();
                row++;
            }
            System.out.println("\nUsing do-while loops:");
            row = 1;
            do {
                int column = 1;
                do {
                    System.out.printf("%d*%d=%2d%s", column, row, column * row, column == 9 ? "" : "  ");
                    column++;
                } while (column <= 9);
                System.out.println();
                row++;
            } while (row <= 9);
        }

        // Exercise 5.51: Find the longest common prefix of two strings
        if (selected == 0 || selected == 51) {
            System.out.println("\nExercise 5.51: Find the longest common prefix of two strings");
            System.out.print("Enter the first string: ");
            String first = input.nextLine();
            System.out.print("Enter the second string: ");
            String second = input.nextLine();
            int firstIndex = 0;
            int secondIndex = 0;
            while (firstIndex < first.length() && secondIndex < second.length()) {
                int firstCodePoint = first.codePointAt(firstIndex);
                int secondCodePoint = second.codePointAt(secondIndex);
                if (firstCodePoint != secondCodePoint) {
                    break;
                }
                firstIndex += Character.charCount(firstCodePoint);
                secondIndex += Character.charCount(secondCodePoint);
            }
            if (firstIndex == 0) {
                System.out.println("The strings have no common prefix.");
            } else {
                // Quotes make any trailing space in the actual common prefix visible.
                System.out.println("The common prefix is \"" + first.substring(0, firstIndex) + "\".");
            }
        }

        // Close the shared Scanner only after the selected work is finished.
        input.close();
    }
}
