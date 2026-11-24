/* Introduction to Java Programming and Data Structures (Comprehensive Version) 
11th Edition 2018*/

// Chapter 6 Exercises - Methods
package Exercises;

import java.util.Scanner;
import java.util.Locale;
import java.util.NoSuchElementException;

public class HelloJava_Ch6 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            input.useLocale(Locale.US);
            System.out.println("Choose 1-39 (or 6.1-6.39), or 0 to run all.");
            int selected = readExercise(input);
            for (int exercise = 1; exercise <= 39; exercise++) {
                if (selected == 0 || selected == exercise) {
                    try {
                        runExercise(exercise, input);
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Input cannot be used: " + ex.getMessage());
                    }
                }
            }
        } catch (NoSuchElementException ex) {
            System.out.println("\nInput ended. Run again.");
        }
    }

    private static void runExercise(int exercise, Scanner input) {
        switch (exercise) {
            // Exercise 6.1: Pentagonal numbers
            case 1: {
                System.out.println("\nExercise 6.1: Pentagonal numbers");
                // The returned int is formatted by the caller, not printed by the method.
                for (int n = 1; n <= 100; n++) {
                    System.out.printf("%7d", getPentagonalNumber(n));
                    if (n % 10 == 0)
                        System.out.println();
                }
                break;
            }

            // Exercise 6.2: Sum digits
            case 2: {
                System.out.println("\nExercise 6.2: Sum digits");
                long number = readLong(input, "Enter an integer: ");
                System.out.println("The sum of the digits is " + sumDigits(number));
                break;
            }

            // Exercise 6.3: Palindrome integer
            case 3: {
                System.out.println("\nExercise 6.3: Palindrome integer");
                int number = readInt(input, "Enter an integer: ");
                // The nested class preserves the required int-returning reverse header.
                System.out.println(number + (Exercise63.isPalindrome(number)
                        ? " is a palindrome"
                        : " is not a palindrome"));
                break;
            }

            // Exercise 6.4: Display an integer reversed
            case 4: {
                System.out.println("\nExercise 6.4: Display an integer reversed");
                int number = readInt(input, "Enter an integer: ");
                // This reverse prints; Exercise63.reverse returns an int instead.
                Exercise64.reverse(number);
                break;
            }

            // Exercise 6.5: Sort three numbers in decreasing order
            case 5: {
                System.out.println("\nExercise 6.5: Sort three numbers in decreasing order");
                double a = readDouble(input, "Enter the first number: ");
                double b = readDouble(input, "Enter the second number: ");
                double c = readDouble(input, "Enter the third number: ");
                displaySortedNumbers(a, b, c);
                break;
            }

            // Exercise 6.6: Display a number pattern
            case 6: {
                System.out.println("\nExercise 6.6: Display a number pattern");
                int n = readIntInRange(input, "Enter the number of rows (1-50): ", 1, 50);
                displayPattern(n);
                break;
            }

            // Exercise 6.7: Future investment value
            case 7: {
                System.out.println("\nExercise 6.7: Future investment value");
                double amount = readNonnegative(input, "Enter the investment amount: ");
                double annualPercent = readNonnegative(input, "Enter annual interest rate (9 for 9%): ");
                // Convert the annual percentage to a fractional monthly rate once.
                double monthlyRate = annualPercent / 1200;
                System.out.printf("%-8s %18s%n", "Years", "Future Value");
                for (int years = 1; years <= 30; years++) {
                    System.out.printf(Locale.US, "%-8d %18.2f%n", years,
                            futureInvestmentValue(amount, monthlyRate, years));
                }
                break;
            }

            // Exercise 6.8: Miles and kilometers
            case 8: {
                System.out.println("\nExercise 6.8: Miles and kilometers");
                System.out.println("Using 1 mile = 1.609 km, consistent with the sample table.");
                System.out.printf("%-8s %12s | %-12s %10s%n", "Miles", "Kilometers", "Kilometers", "Miles");
                for (int mile = 1; mile <= 10; mile++) {
                    int km = 20 + (mile - 1) * 5;
                    System.out.printf(Locale.US, "%-8d %12.3f | %-12d %10.3f%n",
                            mile, mileToKilometer(mile), km, kilometerToMile(km));
                }
                break;
            }

            // Exercise 6.9: Pounds and kilograms
            case 9: {
                System.out.println("\nExercise 6.9: Pounds and kilograms");
                System.out.println("Using kg = lb * 0.453 and lb = kg * 2.204; see the source note.");
                System.out.printf("%-10s %10s | %-10s %12s%n", "Kilograms", "Pounds", "Pounds", "Kilograms");
                for (int row = 0; row < 100; row++) {
                    int kg = 1 + row * 2;
                    int lb = 20 + row * 5;
                    System.out.printf(Locale.US, "%-10d %10.1f | %-10d %12.2f%n",
                            kg, kilogramToPound(kg), lb, poundToKilogram(lb));
                }
                break;
            }

            // Exercise 6.10: Count primes below 10000
            case 10: {
                System.out.println("\nExercise 6.10: Count primes below 10000");
                int total = 0;
                for (int n = 2; n < 10000; n++) {
                    if (isPrime(n))
                        total++;
                }
                System.out.println("The number of primes below 10000 is " + total);
                break;
            }

            // Exercise 6.11: Compute commissions
            case 11: {
                System.out.println("\nExercise 6.11: Compute commissions");
                System.out.println("Using Exercise 5.39's graduated 6%, 8%, and 10% rates.");
                System.out.printf("%-14s %12s%n", "Sales Amount", "Commission");
                for (int sales = 10000; sales <= 100000; sales += 5000) {
                    System.out.printf(Locale.US, "%-14d %12.1f%n", sales, computeCommission(sales));
                }
                break;
            }

            // Exercise 6.12: Display numbers
            case 12: {
                System.out.println("\nExercise 6.12: Display numbers");
                // The description says characters, but its header and 1-100 test require
                // numbers.
                printNumbers(1, 100, 10);
                break;
            }

            // Exercise 6.13: Sum a series
            case 13: {
                System.out.println("\nExercise 6.13: Sum a series");
                System.out.printf("%-8s %12s%n", "i", "m(i)");
                for (int i = 1; i <= 20; i++) {
                    System.out.printf(Locale.US, "%-8d %12.4f%n", i, sumSeries(i));
                }
                break;
            }

            // Exercise 6.14: Estimate pi
            case 14: {
                System.out.println("\nExercise 6.14: Estimate pi");
                System.out.printf("%-8s %12s%n", "i", "m(i)");
                for (int i = 1; i <= 901; i += 100) {
                    System.out.printf(Locale.US, "%-8d %12.4f%n", i, estimatePi(i));
                }
                break;
            }

            // Exercise 6.15: Print a historical tax table
            case 15: {
                System.out.println("\nExercise 6.15: Print a historical tax table");
                System.out.println("2009 textbook tax brackets; exercise data.");
                System.out.printf("%-10s %10s %14s %14s %14s%n",
                        "Income", "Single", "Married Joint", "Married Sep.", "Head of House");
                for (int income = 50000; income <= 70000; income += 100) {
                    System.out.printf("%-10d %10d %14d %14d %14d%n", income,
                            Math.round(computeTax(0, income)), Math.round(computeTax(1, income)),
                            Math.round(computeTax(2, income)), Math.round(computeTax(3, income)));
                }
                break;
            }

            // Exercise 6.16: Number of days in a year
            case 16: {
                System.out.println("\nExercise 6.16: Number of days in a year");
                System.out.printf("%-8s %8s%n", "Year", "Days");
                for (int year = 2014; year <= 2034; year++) {
                    System.out.printf("%-8d %8d%n", year, numberOfDaysInYear(year));
                }
                break;
            }

            // Exercise 6.17: Random binary matrix
            case 17: {
                System.out.println("\nExercise 6.17: Random binary matrix");
                int n = readIntInRange(input, "Enter n (1-50): ", 1, 50);
                printMatrix(n);
                break;
            }

            // Exercise 6.18: Check a password
            case 18: {
                System.out.println("\nExercise 6.18: Check a password");
                System.out.print("Enter a practice password (letters/digits, 10+ characters, 3+ digits): ");
                String password = input.nextLine();
                System.out.println(isValidPassword(password) ? "Valid Password" : "Invalid Password");
                break;
            }

            // Exercise 6.19: Triangle validity and area
            case 19: {
                System.out.println("\nExercise 6.19: Triangle validity and area");
                double a = readDouble(input, "Enter side 1: ");
                double b = readDouble(input, "Enter side 2: ");
                double c = readDouble(input, "Enter side 3: ");
                if (isValid(a, b, c)) {
                    System.out.println("The area of the triangle is " + area(a, b, c));
                } else {
                    System.out.println("Invalid triangle sides.");
                }
                break;
            }

            // Exercise 6.20: Count letters in a string
            case 20: {
                System.out.println("\nExercise 6.20: Count letters in a string");
                System.out.print("Enter a string: ");
                String text = input.nextLine();
                System.out.println("The number of letters is " + countLetters(text));
                break;
            }

            // Exercise 6.21: Translate phone keypad letters
            case 21: {
                System.out.println("\nExercise 6.21: Translate phone keypad letters");
                System.out.print("Enter a phone number or string: ");
                String phone = input.nextLine();
                System.out.println(translatePhone(phone));
                break;
            }

            // Exercise 6.22: Approximate a square root
            case 22: {
                System.out.println("\nExercise 6.22: Approximate a square root");
                long n = readLong(input, "Enter a nonnegative integer: ");
                System.out.println("The approximated square root is " + sqrt(n));
                break;
            }

            // Exercise 6.23: Count a specified character
            case 23: {
                System.out.println("\nExercise 6.23: Count a specified character");
                System.out.print("Enter a string: ");
                String text = input.nextLine();
                char target = readChar(input, "Enter one character to count: ");
                System.out.println("The number of occurrences is " + count(text, target));
                break;
            }

            // Exercise 6.24: Current date and time
            case 24: {
                System.out.println("\nExercise 6.24: Current date and time");
                // UTC is explicit; epoch milliseconds themselves have no local time zone.
                System.out.println("Current date and time (UTC) is " + currentDateTime(System.currentTimeMillis()));
                break;
            }

            // Exercise 6.25: Convert milliseconds to elapsed time
            case 25: {
                System.out.println("\nExercise 6.25: Convert milliseconds to elapsed time");
                long millis = readLong(input, "Enter nonnegative milliseconds: ");
                System.out.println(convertMillis(millis));
                break;
            }

            // Exercise 6.26: First 120 palindromic primes
            case 26: {
                System.out.println("\nExercise 6.26: First 120 palindromic primes");
                int printed = 0;
                for (int candidate = 2; printed < 120; candidate++) {
                    if (isPrime(candidate) && Exercise63.isPalindrome(candidate)) {
                        printSequenceValue(candidate, printed, 10);
                        printed++;
                    }
                }
                break;
            }

            // Exercise 6.27: First 120 emirps
            case 27: {
                System.out.println("\nExercise 6.27: First 120 emirps");
                int printed = 0;
                for (int candidate = 2; printed < 120; candidate++) {
                    if (isEmirp(candidate)) {
                        printSequenceValue(candidate, printed, 10);
                        printed++;
                    }
                }
                break;
            }

            // Exercise 6.28: Mersenne primes with p at most 31
            case 28: {
                System.out.println("\nExercise 6.28: Mersenne primes with p at most 31");
                System.out.printf("%-8s %14s%n", "p", "2^p - 1");
                for (int p = 2; p <= 31; p++) {
                    // long shifting prevents int overflow while forming the candidate.
                    long value = (1L << p) - 1;
                    if (isPrime((int) value))
                        System.out.printf("%-8d %14d%n", p, value);
                }
                break;
            }

            // Exercise 6.29: Twin primes below 1200
            case 29: {
                System.out.println("\nExercise 6.29: Twin primes below 1200");
                for (int n = 2; n + 2 < 1200; n++) {
                    if (isPrime(n) && isPrime(n + 2)) {
                        System.out.println("(" + n + ", " + (n + 2) + ")");
                    }
                }
                break;
            }

            // Exercise 6.30: Play one game of craps
            case 30: {
                System.out.println("\nExercise 6.30: Play one game of craps");
                playCraps(true);
                break;
            }

            // Exercise 6.31: Validate a card number
            case 31: {
                System.out.println("\nExercise 6.31: Validate a card number");
                long number = readLong(input, "Enter a practice card number as a long integer: ");
                System.out.println(number + (isValid(number) ? " is valid" : " is invalid"));
                break;
            }

            // Exercise 6.32: Simulate 15000 games of craps
            case 32: {
                System.out.println("\nExercise 6.32: Simulate 15000 games of craps");
                int wins = 0;
                for (int game = 0; game < 15000; game++) {
                    // Reuse the same game method; suppress the per-roll output here.
                    if (playCraps(false))
                        wins++;
                }
                System.out.println("Winning games: " + wins + " out of 15000");
                System.out.println("Losing games: " + (15000 - wins));
                break;
            }

            // Exercise 6.33: Current date and time from epoch milliseconds
            case 33: {
                System.out.println("\nExercise 6.33: Current date and time from epoch milliseconds");
                // Same requirement as 6.24, so reuse rather than duplicate the date algorithm.
                System.out.println("Current date and time (UTC) is " + currentDateTime(System.currentTimeMillis()));
                break;
            }

            // Exercise 6.34: Print a month calendar using Zeller
            case 34: {
                System.out.println("\nExercise 6.34: Print a month calendar using Zeller");
                int year = readIntInRange(input, "Enter a year (1-9999): ", 1, 9999);
                int month = readIntInRange(input, "Enter a month (1-12): ", 1, 12);
                printMonth(year, month);
                break;
            }

            // Exercise 6.35: Area of a pentagon
            case 35: {
                System.out.println("\nExercise 6.35: Area of a pentagon");
                double side = readPositive(input, "Enter the side: ");
                System.out.println("The area of the pentagon is " + area(side));
                break;
            }

            // Exercise 6.36: Area of a regular polygon
            case 36: {
                System.out.println("\nExercise 6.36: Area of a regular polygon");
                int n = readIntInRange(input, "Enter the number of sides (at least 3): ", 3, 1000000);
                double side = readPositive(input, "Enter the side: ");
                System.out.println("The area of the polygon is " + area(n, side));
                break;
            }

            // Exercise 6.37: Format an integer with leading zeros
            case 37: {
                System.out.println("\nExercise 6.37: Format an integer with leading zeros");
                int number = readInt(input, "Enter an integer: ");
                int width = readIntInRange(input, "Enter a width (0-1000): ", 0, 1000);
                System.out.println(format(number, width));
                break;
            }

            // Exercise 6.38: Generate random characters
            case 38: {
                System.out.println("\nExercise 6.38: Generate random characters");
                System.out.println("200 uppercase letters:");
                for (int i = 0; i < 200; i++) {
                    printSequenceCharacter(RandomCharacter.getRandomUpperCaseLetter(), i, 10);
                }
                System.out.println("200 single digits:");
                for (int i = 0; i < 200; i++) {
                    printSequenceCharacter(RandomCharacter.getRandomDigitCharacter(), i, 10);
                }
                break;
            }

            // Exercise 6.39: Point position relative to a directed line
            case 39: {
                System.out.println("\nExercise 6.39: Point position relative to a directed line");
                double x0 = readDouble(input, "Enter p0 x: ");
                double y0 = readDouble(input, "Enter p0 y: ");
                double x1 = readDouble(input, "Enter p1 x: ");
                double y1 = readDouble(input, "Enter p1 y: ");
                double x2 = readDouble(input, "Enter p2 x: ");
                double y2 = readDouble(input, "Enter p2 y: ");
                String position;
                // Segment membership is more specific than membership of the infinite line.
                if (onTheLineSegment(x0, y0, x1, y1, x2, y2)) {
                    position = "on the line segment";
                } else if (onTheSameLine(x0, y0, x1, y1, x2, y2)) {
                    position = "on the same line, outside the segment";
                } else if (leftOfTheLine(x0, y0, x1, y1, x2, y2)) {
                    position = "on the left side of the line";
                } else {
                    position = "on the right side of the line";
                }
                System.out.println("Point (" + x2 + ", " + y2 + ") is " + position + ".");
                break;
            }

            default:
                throw new IllegalArgumentException("Unknown exercise.");
        }
    }

    // ---------------- Methods for Exercises 6.1-6.6 ----------------

    /** 6.1: n must be positive; the result must fit the required int type. */
    public static int getPentagonalNumber(int n) {
        if (n < 1 || n > 40000) {
            throw new IllegalArgumentException("n is outside the possible positive int-result range.");
        }
        long value = (long) n * (3L * n - 1) / 2;
        if (value > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("The pentagonal number exceeds int range.");
        }
        return (int) value;
    }

    /** 6.2: Sum magnitude digits, including for Long.MIN_VALUE. */
    public static int sumDigits(long n) {
        int total = 0;
        do {
            // Taking abs of a single remainder avoids abs(Long.MIN_VALUE).
            total += (int) Math.abs(n % 10);
            n /= 10;
        } while (n != 0);
        return total;
    }

    /** Separate scope for the value-returning header required by 6.3. */
    public static class Exercise63 {
        public static int reverse(int number) {
            long remaining = Math.abs((long) number);
            long reversed = 0;
            do {
                reversed = reversed * 10 + remaining % 10;
                remaining /= 10;
            } while (remaining != 0);
            if (number < 0)
                reversed = -reversed;
            if (reversed < Integer.MIN_VALUE || reversed > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("The reversal exceeds int range.");
            }
            return (int) reversed;
        }

        public static boolean isPalindrome(int number) {
            if (number < 0)
                return false;
            try {
                // Reuse the required reverse method rather than duplicate it.
                return number == reverse(number);
            } catch (IllegalArgumentException ex) {
                // An out-of-range reversal cannot equal the original int.
                return false;
            }
        }
    }

    /** Separate scope for the output-producing header required by 6.4. */
    public static class Exercise64 {
        public static void reverse(int number) {
            long remaining = Math.abs((long) number);
            if (number < 0)
                System.out.print("-");
            do {
                System.out.print(remaining % 10);
                remaining /= 10;
            } while (remaining != 0);
            System.out.println();
        }
    }

    /** 6.5: Local swaps arrange three finite values from largest to smallest. */
    public static void displaySortedNumbers(double num1, double num2, double num3) {
        requireFinite(num1);
        requireFinite(num2);
        requireFinite(num3);
        if (num1 < num2) {
            double temp = num1;
            num1 = num2;
            num2 = temp;
        }
        if (num1 < num3) {
            double temp = num1;
            num1 = num3;
            num3 = temp;
        }
        if (num2 < num3) {
            double temp = num2;
            num2 = num3;
            num3 = temp;
        }
        System.out.println(num1 + " " + num2 + " " + num3);
    }

    /** 6.6: Ascending rows, consistent with the first three sample rows. */
    public static void displayPattern(int n) {
        if (n < 1)
            throw new IllegalArgumentException("n must be positive.");
        for (int row = 1; row <= n; row++) {
            for (int value = 1; value <= row; value++) {
                if (value > 1)
                    System.out.print(" ");
                System.out.print(value);
            }
            System.out.println();
        }
    }

    // ---------------- Methods for Exercises 6.7-6.16 ----------------

    /** 6.7: monthlyInterestRate is a fraction, not an annual percentage. */
    public static double futureInvestmentValue(
            double investmentAmount, double monthlyInterestRate, int years) {
        requireNonnegative(investmentAmount);
        requireNonnegative(monthlyInterestRate);
        if (years < 0)
            throw new IllegalArgumentException("Years cannot be negative.");
        double value = investmentAmount * Math.pow(1 + monthlyInterestRate, years * 12.0);
        requireFinite(value);
        return value;
    }

    /** 6.8: Use one factor in both directions so they are inverse conversions. */
    public static double mileToKilometer(double mile) {
        requireNonnegative(mile);
        return mile * 1.609;
    }

    public static double kilometerToMile(double kilometer) {
        requireNonnegative(kilometer);
        return kilometer / 1.609;
    }

    /** 6.9: The textbook reverses the variable labels in its formulas. */
    public static double poundToKilogram(double pound) {
        requireNonnegative(pound);
        return pound * 0.453;
    }

    public static double kilogramToPound(double kilogram) {
        requireNonnegative(kilogram);
        return kilogram * 2.204;
    }

    /** 6.10 and later prime exercises: 0, 1, and negatives are not prime. */
    public static boolean isPrime(int number) {
        if (number < 2)
            return false;
        // number/divisor is equivalent to a square-root bound without overflow.
        for (int divisor = 2; divisor <= number / divisor; divisor++) {
            if (number % divisor == 0)
                return false;
        }
        return true;
    }

    /** 6.11: Graduated commission from Exercise 5.39; excludes base salary. */
    public static double computeCommission(double salesAmount) {
        requireNonnegative(salesAmount);
        if (salesAmount <= 5000)
            return salesAmount * 0.06;
        if (salesAmount <= 10000)
            return 5000 * 0.06 + (salesAmount - 5000) * 0.08;
        return 5000 * 0.06 + 5000 * 0.08 + (salesAmount - 10000) * 0.10;
    }

    /** 6.12: Inclusive range; exactly one space between adjacent numbers. */
    public static void printNumbers(int num1, int num2, int numberPerLine) {
        if (numberPerLine < 1 || num1 > num2) {
            throw new IllegalArgumentException("Require ascending bounds and a positive line count.");
        }
        long printed = 0;
        // A long loop variable can pass Integer.MAX_VALUE without wrapping.
        for (long value = num1; value <= num2; value++) {
            if (printed % numberPerLine != 0)
                System.out.print(" ");
            System.out.print(value);
            printed++;
            if (printed % numberPerLine == 0)
                System.out.println();
        }
        if (printed % numberPerLine != 0)
            System.out.println();
    }

    /** 6.13: Sum j/(j+2), retaining floating-point division. */
    public static double sumSeries(int i) {
        if (i < 0)
            throw new IllegalArgumentException("Term count cannot be negative.");
        double sum = 0;
        for (int j = 1; j <= i; j++)
            sum += j / (j + 2.0);
        return sum;
    }

    /** 6.14: Alternating odd-denominator series, multiplied by four. */
    public static double estimatePi(int i) {
        if (i < 1)
            throw new IllegalArgumentException("At least one term is required.");
        double sum = 0;
        for (int term = 1; term <= i; term++) {
            sum += (term % 2 == 1 ? 1.0 : -1.0) / (2.0 * term - 1);
        }
        return 4 * sum;
    }

    /** 6.15: 2009 Table 3.2. Status: 0 single, 1 joint, 2 separate, 3 head. */
    public static double computeTax(int status, double taxableIncome) {
        requireNonnegative(taxableIncome);
        double b1, b2, b3, b4, b5;
        switch (status) {
            case 0:
                b1 = 8350;
                b2 = 33950;
                b3 = 82250;
                b4 = 171550;
                b5 = 372950;
                break;
            case 1:
                b1 = 16700;
                b2 = 67900;
                b3 = 137050;
                b4 = 208850;
                b5 = 372950;
                break;
            case 2:
                b1 = 8350;
                b2 = 33950;
                b3 = 68525;
                b4 = 104425;
                b5 = 186475;
                break;
            case 3:
                b1 = 11950;
                b2 = 45500;
                b3 = 117450;
                b4 = 190200;
                b5 = 372950;
                break;
            default:
                throw new IllegalArgumentException("Tax status must be 0-3.");
        }
        // Each bracket taxes only its own portion of income.
        double tax = Math.min(taxableIncome, b1) * 0.10;
        if (taxableIncome > b1)
            tax += (Math.min(taxableIncome, b2) - b1) * 0.15;
        if (taxableIncome > b2)
            tax += (Math.min(taxableIncome, b3) - b2) * 0.25;
        if (taxableIncome > b3)
            tax += (Math.min(taxableIncome, b4) - b3) * 0.28;
        if (taxableIncome > b4)
            tax += (Math.min(taxableIncome, b5) - b4) * 0.33;
        if (taxableIncome > b5)
            tax += (taxableIncome - b5) * 0.35;
        return tax;
    }

    /** 6.16: Gregorian leap-year rule; year must be positive. */
    public static int numberOfDaysInYear(int year) {
        if (year < 1)
            throw new IllegalArgumentException("Year must be positive.");
        return isLeapYear(year) ? 366 : 365;
    }

    // Alias for the spelling present in the supplied PDF's method header.
    public static int numberOfDaysInAYear(int year) {
        return numberOfDaysInYear(year);
    }

    private static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    // ---------------- Methods for Exercises 6.17-6.25 ----------------

    /** 6.17: Each element is an independent draw from {0,1}. */
    public static void printMatrix(int n) {
        if (n < 1)
            throw new IllegalArgumentException("Matrix size must be positive.");
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                if (column > 0)
                    System.out.print(" ");
                System.out.print((int) (Math.random() * 2));
            }
            System.out.println();
        }
    }

    /**
     * 6.18: Textbook exercise rule, using Java char letter/digit classification.
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 10)
            return false;
        int digits = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (!Character.isLetterOrDigit(ch))
                return false;
            if (Character.isDigit(ch))
                digits++;
        }
        return digits >= 3;
    }

    /** 6.19: Positive finite sides with a strict triangle inequality. */
    public static boolean isValid(double side1, double side2, double side3) {
        if (!Double.isFinite(side1) || !Double.isFinite(side2) || !Double.isFinite(side3)
                || side1 <= 0 || side2 <= 0 || side3 <= 0)
            return false;
        // Compare the largest side minus a smaller side to the remaining one.
        // This avoids overflowing a sum of two very large sides.
        double a = side1, b = side2, c = side3;
        if (a > b) {
            double t = a;
            a = b;
            b = t;
        }
        if (b > c) {
            double t = b;
            b = c;
            c = t;
        }
        if (a > b) {
            double t = a;
            a = b;
            b = t;
        }
        return c - b < a;
    }

    /** 6.19: Heron's formula, scaled before multiplication. */
    public static double area(double side1, double side2, double side3) {
        if (!isValid(side1, side2, side3)) {
            throw new IllegalArgumentException("The sides do not form a triangle.");
        }
        double scale = Math.max(side1, Math.max(side2, side3));
        double a = side1 / scale, b = side2 / scale, c = side3 / scale;
        double s = (a + b + c) / 2;
        double normalizedArea = Math.sqrt(Math.max(0, s * (s - a) * (s - b) * (s - c)));
        double result = normalizedArea * scale * scale;
        requireFinite(result);
        return result;
    }

    /**
     * 6.20: Count char values classified as letters; spaces and digits do not
     * count.
     */
    public static int countLetters(String s) {
        if (s == null)
            throw new IllegalArgumentException("String cannot be null.");
        int letters = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i)))
                letters++;
        }
        return letters;
    }

    /** 6.21: The method's contract is one uppercase English letter A-Z. */
    public static int getNumber(char uppercaseLetter) {
        if (uppercaseLetter >= 'A' && uppercaseLetter <= 'C')
            return 2;
        if (uppercaseLetter >= 'D' && uppercaseLetter <= 'F')
            return 3;
        if (uppercaseLetter >= 'G' && uppercaseLetter <= 'I')
            return 4;
        if (uppercaseLetter >= 'J' && uppercaseLetter <= 'L')
            return 5;
        if (uppercaseLetter >= 'M' && uppercaseLetter <= 'O')
            return 6;
        if (uppercaseLetter >= 'P' && uppercaseLetter <= 'S')
            return 7;
        if (uppercaseLetter >= 'T' && uppercaseLetter <= 'V')
            return 8;
        if (uppercaseLetter >= 'W' && uppercaseLetter <= 'Z')
            return 9;
        throw new IllegalArgumentException("Keypad letter must be uppercase A-Z.");
    }

    public static String translatePhone(String phone) {
        if (phone == null)
            throw new IllegalArgumentException("String cannot be null.");
        String result = "";
        for (int i = 0; i < phone.length(); i++) {
            char ch = phone.charAt(i);
            if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
                result += getNumber(Character.toUpperCase(ch));
            } else {
                result += ch; // Preserve digits, punctuation, spaces, and other text.
            }
        }
        return result;
    }

    /** 6.22: Babylonian iteration; no Math.sqrt call in the implementation. */
    public static double sqrt(long n) {
        if (n < 0)
            throw new IllegalArgumentException("Square-root input cannot be negative.");
        if (n == 0)
            return 0;
        double lastGuess = 1;
        while (true) {
            double nextGuess = (lastGuess + n / lastGuess) / 2;
            // At large magnitudes consecutive doubles can differ by more than
            // 0.0001. Stop at representable precision too, avoiding oscillation.
            double tolerance = Math.max(0.0001, 2 * Math.ulp(nextGuess));
            if (Math.abs(nextGuess - lastGuess) <= tolerance)
                return nextGuess;
            lastGuess = nextGuess;
        }
    }

    /** 6.23: Case-sensitive count of the requested char, including a space. */
    public static int count(String str, char a) {
        if (str == null)
            throw new IllegalArgumentException("String cannot be null.");
        int occurrences = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == a)
                occurrences++;
        }
        return occurrences;
    }

    /** 6.24/6.33: Decode epoch milliseconds with loops, in UTC, Gregorian years. */
    public static String currentDateTime(long millis) {
        long epochDay = Math.floorDiv(millis, 86_400_000L);
        long secondOfDay = Math.floorMod(Math.floorDiv(millis, 1000L), 86400L);
        int year = 1970;
        long remainingDays = epochDay;
        // One Gregorian 400-year cycle has exactly 146097 days. Skip cycles
        // first so even extreme long millisecond values need only short loops.
        long cycles = Math.floorDiv(remainingDays, 146097L);
        year += (int) (cycles * 400);
        remainingDays -= cycles * 146097L;
        while (remainingDays >= daysInGregorianYear(year)) {
            remainingDays -= daysInGregorianYear(year);
            year++;
        }
        int month = 1;
        while (remainingDays >= daysInGregorianMonth(year, month)) {
            remainingDays -= daysInGregorianMonth(year, month);
            month++;
        }
        long hour = secondOfDay / 3600;
        long minute = secondOfDay / 60 % 60;
        long second = secondOfDay % 60;
        return String.format(Locale.US, "%s %d, %d %02d:%02d:%02d",
                monthName(month), remainingDays + 1, year, hour, minute, second);
    }

    /** 6.25: Elapsed hours do not wrap at 24; discard subsecond milliseconds. */
    public static String convertMillis(long millis) {
        if (millis < 0)
            throw new IllegalArgumentException("Elapsed milliseconds cannot be negative.");
        long totalSeconds = millis / 1000;
        return totalSeconds / 3600 + ":" + totalSeconds / 60 % 60 + ":" + totalSeconds % 60;
    }

    // ---------------- Methods for Exercises 6.26-6.34 ----------------

    public static boolean isEmirp(int number) {
        if (!isPrime(number))
            return false;
        try {
            int reversed = Exercise63.reverse(number);
            return reversed != number && isPrime(reversed);
        } catch (IllegalArgumentException ex) {
            return false; // This int-based implementation requires an int reversal.
        }
    }

    private static void printSequenceValue(int value, int printed, int perLine) {
        if (printed % perLine != 0)
            System.out.print(" ");
        System.out.print(value);
        if ((printed + 1) % perLine == 0)
            System.out.println();
    }

    private static int rollDie() {
        return 1 + (int) (Math.random() * 6);
    }

    /** 6.30: Return the result; detailed output is optional for 6.32 reuse. */
    public static boolean playCraps(boolean showRolls) {
        int first = rollDie(), second = rollDie();
        int sum = first + second;
        if (showRolls)
            printRoll(first, second);
        if (sum == 7 || sum == 11) {
            if (showRolls)
                System.out.println("You win");
            return true;
        }
        if (sum == 2 || sum == 3 || sum == 12) {
            if (showRolls)
                System.out.println("You lose");
            return false;
        }
        int point = sum; // Retain the original point throughout subsequent rolls.
        if (showRolls)
            System.out.println("Point is " + point);
        while (true) {
            first = rollDie();
            second = rollDie();
            sum = first + second;
            if (showRolls)
                printRoll(first, second);
            if (sum == point || sum == 7) {
                boolean won = sum == point;
                if (showRolls)
                    System.out.println(won ? "You win" : "You lose");
                return won;
            }
        }
    }

    private static void printRoll(int first, int second) {
        System.out.println("You rolled " + first + " + " + second + " = " + (first + second));
    }

    /** 6.31: Overload isValid by its parameter list, not just its return type. */
    public static boolean isValid(long number) {
        if (number <= 0 || getSize(number) < 13 || getSize(number) > 16)
            return false;
        boolean prefix = prefixMatched(number, 4) || prefixMatched(number, 5)
                || prefixMatched(number, 37) || prefixMatched(number, 6);
        return prefix && (sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0;
    }

    public static int sumOfDoubleEvenPlace(long number) {
        if (number < 0)
            throw new IllegalArgumentException("Card number cannot be negative.");
        number /= 10; // Start in the second position from the right.
        int sum = 0;
        while (number > 0) {
            sum += getDigit((int) (number % 10) * 2);
            number /= 100; // Skip the next odd position.
        }
        return sum;
    }

    /** Return a single digit, or the sum of a two-digit number's digits. */
    public static int getDigit(int number) {
        if (number < 0 || number > 99) {
            throw new IllegalArgumentException("Expected a nonnegative one- or two-digit number.");
        }
        return number < 10 ? number : number / 10 + number % 10;
    }

    public static int sumOfOddPlace(long number) {
        if (number < 0)
            throw new IllegalArgumentException("Card number cannot be negative.");
        int sum = 0;
        while (number > 0) {
            sum += (int) (number % 10);
            number /= 100;
        }
        return sum;
    }

    public static boolean prefixMatched(long number, int d) {
        if (number < 0 || d < 0)
            return false;
        return getSize(number) >= getSize(d) && getPrefix(number, getSize(d)) == d;
    }

    /** Number of magnitude digits; zero has one digit, and the sign is excluded. */
    public static int getSize(long d) {
        int size = 1;
        while (d <= -10 || d >= 10) {
            d /= 10;
            size++;
        }
        return size;
    }

    public static long getPrefix(long number, int k) {
        if (number < 0 || k < 1) {
            throw new IllegalArgumentException("Require a nonnegative number and positive k.");
        }
        for (int size = getSize(number); size > k; size--)
            number /= 10;
        return number;
    }

    /** 6.34: Zeller returns 0 Saturday; convert to 0 Sunday for the calendar. */
    public static int getStartDay(int year, int month) {
        if (year < 1 || month < 1 || month > 12) {
            throw new IllegalArgumentException("Require year >= 1 and month 1-12.");
        }
        int adjustedYear = year;
        int adjustedMonth = month;
        if (month <= 2) {
            adjustedMonth += 12;
            adjustedYear--;
        }
        int k = adjustedYear % 100;
        int j = adjustedYear / 100;
        int h = (int) ((1L + 26L * (adjustedMonth + 1) / 10
                + k + k / 4 + j / 4 + 5L * j) % 7);
        return (h + 6) % 7;
    }

    public static void printMonth(int year, int month) {
        int start = getStartDay(year, month);
        int days = daysInGregorianMonth(year, month);
        System.out.println("          " + monthName(month) + " " + year);
        System.out.println("-----------------------------------");
        System.out.println("  Sun  Mon  Tue  Wed  Thu  Fri  Sat");
        for (int blank = 0; blank < start; blank++)
            System.out.print("     ");
        for (int day = 1; day <= days; day++) {
            System.out.printf("%5d", day);
            if ((start + day) % 7 == 0)
                System.out.println();
        }
        if ((start + days) % 7 != 0)
            System.out.println();
    }

    private static int daysInGregorianYear(int year) {
        return isLeapYear(year) ? 366 : 365;
    }

    private static int daysInGregorianMonth(int year, int month) {
        switch (month) {
            case 2:
                return isLeapYear(year) ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            default:
                throw new IllegalArgumentException("Month must be 1-12.");
        }
    }

    private static String monthName(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                throw new IllegalArgumentException("Month must be 1-12.");
        }
    }

    // ---------------- Methods for Exercises 6.35-6.39 ----------------

    /** 6.35: This overload delegates to the general regular-polygon formula. */
    public static double area(double side) {
        return area(5, side);
    }

    /** 6.36: n is the number of sides, not a radius or angle. */
    public static double area(int n, double side) {
        if (n < 3)
            throw new IllegalArgumentException("A polygon needs at least three sides.");
        requireFinite(side);
        if (side <= 0)
            throw new IllegalArgumentException("Side length must be positive.");
        double result = n * side * side / (4 * Math.tan(Math.PI / n));
        requireFinite(result);
        return result;
    }

    /** 6.37: Minimum width; no truncation, and the sign precedes any padding. */
    public static String format(int number, int width) {
        if (width < 0)
            throw new IllegalArgumentException("Width cannot be negative.");
        String digits = Long.toString(Math.abs((long) number));
        String sign = number < 0 ? "-" : "";
        int zeros = width - sign.length() - digits.length();
        String padding = "";
        for (int i = 0; i < zeros; i++)
            padding += "0";
        return sign + padding + digits;
    }

    /** 6.38: Listing 6.10's methods, kept in this file for the one-file project. */
    public static class RandomCharacter {
        public static char getRandomCharacter(char ch1, char ch2) {
            if (ch1 > ch2)
                throw new IllegalArgumentException("Character bounds are reversed.");
            return (char) (ch1 + (int) (Math.random() * (ch2 - ch1 + 1)));
        }

        public static char getRandomLowerCaseLetter() {
            return getRandomCharacter('a', 'z');
        }

        public static char getRandomUpperCaseLetter() {
            return getRandomCharacter('A', 'Z');
        }

        public static char getRandomDigitCharacter() {
            return getRandomCharacter('0', '9');
        }

        public static char getRandomCharacter() {
            return getRandomCharacter(Character.MIN_VALUE, Character.MAX_VALUE);
        }
    }

    private static void printSequenceCharacter(char ch, int printed, int perLine) {
        if (printed % perLine != 0)
            System.out.print(" ");
        System.out.print(ch);
        if ((printed + 1) % perLine == 0)
            System.out.println();
    }

    public static boolean leftOfTheLine(double x0, double y0, double x1,
            double y1, double x2, double y2) {
        return pointSide(x0, y0, x1, y1, x2, y2) > 0;
    }

    public static boolean onTheSameLine(double x0, double y0, double x1,
            double y1, double x2, double y2) {
        return pointSide(x0, y0, x1, y1, x2, y2) == 0;
    }

    public static boolean onTheLineSegment(double x0, double y0, double x1,
            double y1, double x2, double y2) {
        if (!onTheSameLine(x0, y0, x1, y1, x2, y2))
            return false;
        // Endpoint comparisons are inclusive; tolerance applies to collinearity.
        return x2 >= Math.min(x0, x1) && x2 <= Math.max(x0, x1)
                && y2 >= Math.min(y0, y1) && y2 <= Math.max(y0, y1);
    }

    /** 6.39: Positive determinant means left of the directed line p0 -> p1. */
    private static int pointSide(double x0, double y0, double x1,
            double y1, double x2, double y2) {
        requireFinite(x0);
        requireFinite(y0);
        requireFinite(x1);
        requireFinite(y1);
        requireFinite(x2);
        requireFinite(y2);
        if (x0 == x1 && y0 == y1) {
            throw new IllegalArgumentException("Two distinct endpoints must define the line.");
        }
        double first = (x1 - x0) * (y2 - y0);
        double second = (x2 - x0) * (y1 - y0);
        double cross = first - second;
        requireFinite(first);
        requireFinite(second);
        requireFinite(cross);
        double tolerance = 1e-10 * Math.max(1, Math.max(Math.abs(first), Math.abs(second)));
        if (Math.abs(cross) <= tolerance)
            return 0;
        return cross > 0 ? 1 : -1;
    }

    // ---------------- Shared input helpers ----------------
    // All helpers read an entire line, avoiding nextInt/nextLine transitions.

    private static int readExercise(Scanner input) {
        while (true) {
            System.out.print("Exercise: ");
            String text = input.nextLine().trim();
            if (text.startsWith("6."))
                text = text.substring(2);
            try {
                int value = Integer.parseInt(text);
                if (value >= 0 && value <= 39)
                    return value;
            } catch (NumberFormatException ex) {
                // Consume the whole invalid line, then ask again.
            }
            System.out.println("Enter 1-39, 6.1-6.39, or 0.");
        }
    }

    private static long readLong(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Long.parseLong(input.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Enter a whole number within the long integer range.");
            }
        }
    }

    private static int readInt(Scanner input, String prompt) {
        while (true) {
            long value = readLong(input, prompt);
            if (value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE)
                return (int) value;
            System.out.println("Enter an integer from -2147483648 through 2147483647.");
        }
    }

    private static int readIntInRange(Scanner input, String prompt, int min, int max) {
        while (true) {
            int value = readInt(input, prompt);
            if (value >= min && value <= max)
                return value;
            System.out.println("Enter a value from " + min + " through " + max + ".");
        }
    }

    private static double readDouble(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(input.nextLine().trim());
                if (Double.isFinite(value))
                    return value;
            } catch (NumberFormatException ex) {
                // Letter input is not a number; request a replacement.
            }
            System.out.println("Enter a finite number using a dot for decimals.");
        }
    }

    private static double readNonnegative(Scanner input, String prompt) {
        while (true) {
            double value = readDouble(input, prompt);
            if (value >= 0)
                return value;
            System.out.println("Enter a nonnegative number.");
        }
    }

    private static double readPositive(Scanner input, String prompt) {
        while (true) {
            double value = readDouble(input, prompt);
            if (value > 0)
                return value;
            System.out.println("Enter a positive number.");
        }
    }

    private static char readChar(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = input.nextLine();
            if (text.length() == 1)
                return text.charAt(0);
            System.out.println("Enter exactly one Java char value, such as e or a space.");
        }
    }

    private static void requireFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("A value or result is outside finite double range.");
        }
    }

    private static void requireNonnegative(double value) {
        requireFinite(value);
        if (value < 0)
            throw new IllegalArgumentException("A value cannot be negative.");
    }
}
