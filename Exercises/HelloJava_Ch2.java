/* Introduction to Java Programming and Data Structures (Comprehensive Version) 
11th Edition 2018*/

// Chapter 2 Exercises
package Exercises;

import java.util.Scanner;

public class HelloJava_Ch2 {
        public static void main(String[] args) {

                // Create one Scanner to read keyboard input for all these exercises.
                Scanner input = new Scanner(System.in);

                // Exercise 2.1: Convert miles to kilometers
                System.out.println("\nExercise 2.1");
                System.out.print("Enter miles: ");

                double miles = input.nextDouble();

                double kilometers = miles * 1.6;

                System.out.println(
                                miles + " miles is " + kilometers + " kilometers");

                // Exercise 2.2: Equilateral triangle area and prism volume
                // Triangle area = sqrt(3) / 4 * side * side
                // Prism volume = triangle area * prism length
                System.out.println("\nExercise 2.2");

                System.out.print("Enter the triangle's side length: ");
                double triangleSide = input.nextDouble();

                System.out.print("Enter the prism's length: ");
                double prismLength = input.nextDouble();

                double equilateralArea = Math.sqrt(3) / 4 * triangleSide * triangleSide;

                double prismVolume = equilateralArea * prismLength;

                System.out.printf("Triangle area: %.2f%n", equilateralArea);
                System.out.printf("Prism volume: %.2f%n", prismVolume);

                // Exercise 2.3: Convert meters to feet
                System.out.println("\nExercise 2.3");

                System.out.print("Enter meters: ");
                double meters = input.nextDouble();

                double feet = meters * 3.2786;

                System.out.println(
                                meters + " meters is " + feet + " feet");

                // Exercise 2.4: Convert square meters to ping
                System.out.println("\nExercise 2.4");

                System.out.print("Enter an area in square meters: ");
                double squareMeters = input.nextDouble();

                double ping = squareMeters * 0.3025;

                System.out.println(
                                squareMeters + " square meters is " + ping + " ping");

                // Exercise 2.5: Calculate gratuity and total
                System.out.println("\nExercise 2.5");

                System.out.print(
                                "Enter the subtotal and gratuity percentage: ");
                double subtotal = input.nextDouble();
                double gratuityRate = input.nextDouble();

                double gratuity = subtotal * gratuityRate / 100;
                double totalWithTip = subtotal + gratuity;

                System.out.printf(
                                "The gratuity is $%.2f and the total is $%.2f%n",
                                gratuity, totalWithTip);

                // Exercise 2.6: Multiply an integer's digits
                // % 10 extracts the last digit.
                // Integer division by 10 removes the last digit.
                System.out.println("\nExercise 2.6");

                System.out.print("Enter an integer from 0 through 1000: ");
                int digitNumber = input.nextInt();

                int remainingDigits = digitNumber;
                int digitProduct = 1;

                // A do-while loop runs at least once, including for input 0.
                do {
                        int lastDigit = remainingDigits % 10;
                        digitProduct = digitProduct * lastDigit;
                        remainingDigits = remainingDigits / 10;
                } while (remainingDigits > 0);

                System.out.println(
                                "The product of the digits in "
                                                + digitNumber + " is " + digitProduct);

                // Exercise 2.7: Convert minutes to years and remaining days
                // Assume 365 days per year. Leftover minutes are omitted.
                System.out.println("\nExercise 2.7");

                System.out.print("Enter the number of minutes: ");
                long minutes = input.nextLong();

                long totalDays = minutes / (60 * 24);
                long wholeYears = totalDays / 365;
                long remainingDays = totalDays % 365;

                System.out.println(
                                minutes + " minutes is approximately "
                                                + wholeYears + " years and "
                                                + remainingDays + " days");

                // Exercise 2.8: Display the time at a GMT offset
                // This uses a fixed offset, without daylight-saving rules.
                System.out.println("\nExercise 2.8");

                System.out.print(
                                "Enter the GMT offset in hours (for example, -5): ");
                double gmtOffset = input.nextDouble();

                long totalSeconds = System.currentTimeMillis() / 1000;

                long localSeconds = Math.floorMod(
                                totalSeconds + Math.round(gmtOffset * 3600),
                                24L * 60 * 60);

                long currentHour = localSeconds / 3600;
                long currentMinute = (localSeconds % 3600) / 60;
                long currentSecond = localSeconds % 60;

                System.out.printf(
                                "The current time is %02d:%02d:%02d%n",
                                currentHour, currentMinute, currentSecond);

                // Exercise 2.9: Calculate average acceleration
                // Acceleration = (ending velocity - starting velocity) / time
                System.out.println("\nExercise 2.9");

                System.out.print(
                                "Enter starting velocity, ending velocity, "
                                                + "and elapsed seconds (time > 0): ");
                double startingVelocity = input.nextDouble();
                double endingVelocity = input.nextDouble();
                double elapsedTime = input.nextDouble();

                double averageAcceleration = (endingVelocity - startingVelocity) / elapsedTime;

                System.out.println(
                                "Average acceleration: "
                                                + averageAcceleration + " meters/second squared");

                // Exercise 2.10: Calculate energy needed to heat water
                // Energy = mass * temperature change * 4184
                System.out.println("\nExercise 2.10");

                System.out.print("Enter the water mass in kilograms: ");
                double waterMass = input.nextDouble();

                System.out.print("Enter the initial temperature in Celsius: ");
                double initialTemperature = input.nextDouble();

                System.out.print("Enter the final temperature in Celsius: ");
                double finalTemperature = input.nextDouble();

                double energy = waterMass
                                * (finalTemperature - initialTemperature)
                                * 4184;

                System.out.println("Energy needed: " + energy + " joules");

                // Exercise 2.11: Project population after an entered year count
                // Use the assumptions from Exercise 1.11.
                System.out.println("\nExercise 2.11");

                System.out.print("Enter the number of years: ");
                int populationYears = input.nextInt();

                long initialPopulation = 312032486L;
                long secondsPerYear = 365L * 24 * 60 * 60;

                double annualBirths = secondsPerYear / 7.0;
                double annualDeaths = secondsPerYear / 13.0;
                double annualImmigrants = secondsPerYear / 45.0;

                double annualPopulationChange = annualBirths - annualDeaths + annualImmigrants;

                long futurePopulation = (long) (initialPopulation
                                + populationYears * annualPopulationChange);

                System.out.println(
                                "The population in " + populationYears
                                                + " years is " + futurePopulation);

                // Exercise 2.12: Calculate minimum runway length
                // Length = speed squared / (2 * acceleration)
                System.out.println("\nExercise 2.12");

                System.out.print(
                                "Enter take-off speed and positive acceleration: ");
                double takeoffSpeed = input.nextDouble();
                double airplaneAcceleration = input.nextDouble();

                double runwayLength = takeoffSpeed * takeoffSpeed
                                / (2 * airplaneAcceleration);

                System.out.println(
                                "Minimum runway length: " + runwayLength + " meters");

                // Exercise 2.13: Calculate savings after six months
                // Each deposit is added before that month's interest.
                System.out.println("\nExercise 2.13");

                System.out.print("Enter the monthly saving amount: ");
                double monthlySaving = input.nextDouble();

                double monthlySavingsRate = 0.0375 / 12;
                double savingsBalance = 0;

                for (int month = 1; month <= 6; month++) {
                        savingsBalance = (savingsBalance + monthlySaving)
                                        * (1 + monthlySavingsRate);
                }

                System.out.printf(
                                "After six months, the account value is $%.2f%n",
                                savingsBalance);

                // Exercise 2.14: Compute BMI from pounds and inches
                System.out.println("\nExercise 2.14");

                System.out.print("Enter weight in pounds: ");
                double weightPounds = input.nextDouble();

                System.out.print("Enter height in inches: ");
                double heightInches = input.nextDouble();

                double weightKilograms = weightPounds * 0.45359237;
                double heightMeters = heightInches * 0.0254;

                double bmi = weightKilograms / (heightMeters * heightMeters);

                System.out.println("BMI: " + bmi);

                // Exercise 2.15: Calculate the distance between two points
                System.out.println("\nExercise 2.15");

                System.out.print("Enter x1 and y1: ");
                double point1X = input.nextDouble();
                double point1Y = input.nextDouble();

                System.out.print("Enter x2 and y2: ");
                double point2X = input.nextDouble();
                double point2Y = input.nextDouble();

                double distance = Math.sqrt(
                                Math.pow(point2X - point1X, 2)
                                                + Math.pow(point2Y - point1Y, 2));

                System.out.println(
                                "The distance between the points is " + distance);

                // Exercise 2.16: Calculate the area of a regular hexagon
                // Area = 3 * sqrt(3) / 2 * side squared
                System.out.println("\nExercise 2.16");

                System.out.print("Enter the hexagon's side length: ");
                double hexagonSide = input.nextDouble();

                double hexagonArea = 3 * Math.sqrt(3) / 2 * hexagonSide * hexagonSide;

                System.out.println("Hexagon area: " + hexagonArea);

                // Exercise 2.17: Calculate wind-chill temperature
                System.out.println("\nExercise 2.17");

                System.out.print(
                                "Enter temperature in Fahrenheit (-58 through 41): ");
                double airTemperature = input.nextDouble();

                System.out.print("Enter wind speed in mph (at least 2): ");
                double windSpeed = input.nextDouble();

                if (airTemperature < -58
                                || airTemperature > 41
                                || windSpeed < 2) {
                        System.out.println(
                                        "These inputs are outside the exercise's limits.");
                } else {
                        double windPower = Math.pow(windSpeed, 0.16);

                        double windChill = 35.74
                                        + 0.6215 * airTemperature
                                        - 35.75 * windPower
                                        + 0.4275 * airTemperature * windPower;

                        System.out.println(
                                        "Wind-chill temperature: "
                                                        + windChill + " degrees Fahrenheit");
                }

                // Exercise 2.18: Calculate and display midpoints
                // Midpoint = ((x1 + x2) / 2, (y1 + y2) / 2)
                System.out.println("\nExercise 2.18");
                System.out.println(
                                "a          b          Middle Point");

                System.out.println(
                                "(0, 0)     (2, 1)     ("
                                                + ((0 + 2) / 2.0) + ", "
                                                + ((0 + 1) / 2.0) + ")");

                System.out.println(
                                "(1, 4)     (4, 2)     ("
                                                + ((1 + 4) / 2.0) + ", "
                                                + ((4 + 2) / 2.0) + ")");

                System.out.println(
                                "(2, 7)     (6, 3)     ("
                                                + ((2 + 6) / 2.0) + ", "
                                                + ((7 + 3) / 2.0) + ")");

                System.out.println(
                                "(3, 9)     (10, 5)    ("
                                                + ((3 + 10) / 2.0) + ", "
                                                + ((9 + 5) / 2.0) + ")");

                System.out.println(
                                "(4, 11)    (12, 7)    ("
                                                + ((4 + 12) / 2.0) + ", "
                                                + ((11 + 7) / 2.0) + ")");

                // Exercise 2.19: Calculate triangle area from three points
                // (calculate side lengths first, then use Heron's formula)
                System.out.println("\nExercise 2.19");

                System.out.print("Enter x1 y1 x2 y2 x3 y3: ");
                double triangleX1 = input.nextDouble();
                double triangleY1 = input.nextDouble();
                double triangleX2 = input.nextDouble();
                double triangleY2 = input.nextDouble();
                double triangleX3 = input.nextDouble();
                double triangleY3 = input.nextDouble();

                double side1 = Math.sqrt(
                                Math.pow(triangleX2 - triangleX1, 2)
                                                + Math.pow(triangleY2 - triangleY1, 2));

                double side2 = Math.sqrt(
                                Math.pow(triangleX3 - triangleX2, 2)
                                                + Math.pow(triangleY3 - triangleY2, 2));

                double side3 = Math.sqrt(
                                Math.pow(triangleX1 - triangleX3, 2)
                                                + Math.pow(triangleY1 - triangleY3, 2));

                double semiperimeter = (side1 + side2 + side3) / 2;

                double triangleArea = Math.sqrt(
                                semiperimeter
                                                * (semiperimeter - side1)
                                                * (semiperimeter - side2)
                                                * (semiperimeter - side3));

                System.out.println("Triangle area: " + triangleArea);

                // Exercise 2.20: Calculate one month's interest
                // Enter the annual rate as a percentage
                System.out.println("\nExercise 2.20");

                System.out.print(
                                "Enter the balance and annual interest percentage: ");
                double accountBalance = input.nextDouble();
                double annualInterestRate = input.nextDouble();

                double monthlyInterest = accountBalance * annualInterestRate / 1200;

                System.out.printf(
                                "Interest for the next month: $%.2f%n",
                                monthlyInterest);

                // Exercise 2.21: Calculate future investment value
                System.out.println("\nExercise 2.21");

                System.out.print("Enter the investment amount: ");
                double investmentAmount = input.nextDouble();

                System.out.print("Enter the annual interest percentage: ");
                double investmentAnnualRate = input.nextDouble();

                System.out.print("Enter the number of years: ");
                double investmentYears = input.nextDouble();

                double investmentMonthlyRate = investmentAnnualRate / 1200;

                double futureInvestmentValue = investmentAmount * Math.pow(
                                1 + investmentMonthlyRate,
                                investmentYears * 12);

                System.out.printf(
                                "Future investment value: $%.2f%n",
                                futureInvestmentValue);

                // Exercise 2.22: Separate an amount into monetary units
                // Read integer cents: 1156 means 11 dollars and 56 cents.
                // Calculate dollars, quarters, dimes, nickels, pennies.
                System.out.println("\nExercise 2.22");

                System.out.print(
                                "Enter the amount in integer cents (e.g., 1156): ");
                int amountInCents = input.nextInt();

                int remainingCents = amountInCents;

                int dollars = remainingCents / 100;
                remainingCents = remainingCents % 100;

                int quarters = remainingCents / 25;
                remainingCents = remainingCents % 25;

                int dimes = remainingCents / 10;
                remainingCents = remainingCents % 10;

                int nickels = remainingCents / 5;
                int pennies = remainingCents % 5;

                System.out.println("Dollars: " + dollars);
                System.out.println("Quarters: " + quarters);
                System.out.println("Dimes: " + dimes);
                System.out.println("Nickels: " + nickels);
                System.out.println("Pennies: " + pennies);

                // Exercise 2.23: Calculate the cost of driving
                // Cost = distance / miles per gallon * price per gallon
                System.out.println("\nExercise 2.23");

                System.out.print("Enter the driving distance in miles: ");
                double drivingDistance = input.nextDouble();

                System.out.print("Enter the car's miles per gallon: ");
                double milesPerGallon = input.nextDouble();

                System.out.print("Enter the price per gallon: ");
                double pricePerGallon = input.nextDouble();

                double drivingCost = drivingDistance / milesPerGallon * pricePerGallon;

                System.out.printf(
                                "The cost of driving is $%.2f%n",
                                drivingCost);

                // Close the Scanner after all keyboard input is finished.
                input.close();
        }
}