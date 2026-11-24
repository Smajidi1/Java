/* Introduction to Java Programming and Data Structures (Comprehensive Version) 
11th Edition 2018*/

// Chapter 3 Exercises
package Exercises;

import java.util.Scanner;

public class HelloJava_Ch3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Exercise 3.1: Solve a quadratic equation
        {
            System.out.println("\nExercise 3.1");
            // The discriminant determines how many real roots exist.
            System.out.print("Enter a, b, and c: ");
            double a = input.nextDouble();
            double b = input.nextDouble();
            double c = input.nextDouble();
            double discriminant = b * b - 4 * a * c;

            if (a == 0) {
                System.out.println("Invalid quadratic equation: a must not be zero.");
            } else if (discriminant > 0) {
                double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                System.out.println("The equation has two roots " + root1 + " and " + root2);
            } else if (discriminant == 0) {
                System.out.println("The equation has one root " + (-b / (2 * a)));
            } else {
                System.out.println("The equation has no real roots.");
            }
        }

        // Exercise 3.2: Multiply three single-digit numbers
        {
            System.out.println("\nExercise 3.2");
            // Math.random() returns a double from 0.0 (inclusive) to 1.0 (exclusive).
            int number1 = (int) (Math.random() * 10);
            int number2 = (int) (Math.random() * 10);
            int number3 = (int) (Math.random() * 10);
            System.out.print("What is " + number1 + " * " + number2 + " * " + number3 + "? ");
            int answer = input.nextInt();
            int product = number1 * number2 * number3;
            if (answer == product) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer is " + product + ".");
            }
        }

        // Exercise 3.3: Solve two linear equations
        {
            System.out.println("\nExercise 3.3");
            // Solve a*x + b*y = e and c*x + d*y = f using Cramer's rule.
            System.out.print("Enter a, b, c, d, e, and f: ");
            double a = input.nextDouble();
            double b = input.nextDouble();
            double c = input.nextDouble();
            double d = input.nextDouble();
            double e = input.nextDouble();
            double f = input.nextDouble();
            double determinant = a * d - b * c;
            if (determinant == 0) {
                // A zero determinant can mean no solutions OR infinitely many solutions.
                System.out.println("The system has no unique solution.");
            } else {
                double x = (e * d - b * f) / determinant;
                double y = (a * f - e * c) / determinant;
                System.out.println("x is " + x + " and y is " + y);
            }
        }

        // Exercise 3.4: Display a random month
        {
            System.out.println("\nExercise 3.4");
            int month = (int) (Math.random() * 12) + 1;
            String monthName;
            switch (month) {
                case 1:
                    monthName = "January";
                    break;
                case 2:
                    monthName = "February";
                    break;
                case 3:
                    monthName = "March";
                    break;
                case 4:
                    monthName = "April";
                    break;
                case 5:
                    monthName = "May";
                    break;
                case 6:
                    monthName = "June";
                    break;
                case 7:
                    monthName = "July";
                    break;
                case 8:
                    monthName = "August";
                    break;
                case 9:
                    monthName = "September";
                    break;
                case 10:
                    monthName = "October";
                    break;
                case 11:
                    monthName = "November";
                    break;
                default:
                    monthName = "December";
            }
            System.out.println("Month " + month + " is " + monthName + ".");
        }

        // Exercise 3.5: Find a future day
        {
            System.out.println("\nExercise 3.5");
            // Sunday = 0, Monday = 1, ..., Saturday = 6.
            System.out.print("Enter today's day (0-6): ");
            int today = input.nextInt();
            System.out.print("Enter the number of days after today: ");
            int daysElapsed = input.nextInt();
            if (today < 0 || today > 6 || daysElapsed < 0) {
                System.out.println("Invalid input: use a day from 0 to 6 and nonnegative elapsed days.");
            } else {
                // Reduce elapsed days first to avoid overflowing today + daysElapsed.
                int futureDay = (today + daysElapsed % 7) % 7;
                String todayName;
                String futureName;
                switch (today) {
                    case 0:
                        todayName = "Sunday";
                        break;
                    case 1:
                        todayName = "Monday";
                        break;
                    case 2:
                        todayName = "Tuesday";
                        break;
                    case 3:
                        todayName = "Wednesday";
                        break;
                    case 4:
                        todayName = "Thursday";
                        break;
                    case 5:
                        todayName = "Friday";
                        break;
                    default:
                        todayName = "Saturday";
                }
                switch (futureDay) {
                    case 0:
                        futureName = "Sunday";
                        break;
                    case 1:
                        futureName = "Monday";
                        break;
                    case 2:
                        futureName = "Tuesday";
                        break;
                    case 3:
                        futureName = "Wednesday";
                        break;
                    case 4:
                        futureName = "Thursday";
                        break;
                    case 5:
                        futureName = "Friday";
                        break;
                    default:
                        futureName = "Saturday";
                }
                System.out.println("Today is " + todayName + " and the future day is " + futureName + ".");
            }
        }

        // Exercise 3.6: Compute and interpret BMI
        {
            System.out.println("\nExercise 3.6");
            // This section implements the textbook's BMI categories.
            System.out.print("Enter weight in pounds: ");
            double pounds = input.nextDouble();
            System.out.print("Enter feet: ");
            int feet = input.nextInt();
            System.out.print("Enter inches (0 to less than 12): ");
            double inches = input.nextDouble();
            double totalInches = feet * 12.0 + inches;
            if (pounds <= 0 || feet < 0 || inches < 0 || inches >= 12 || totalInches <= 0) {
                System.out.println("Invalid weight or height.");
            } else {
                double kilograms = pounds * 0.45359237;
                double meters = totalInches * 0.0254;
                double bmi = kilograms / (meters * meters);
                System.out.println("BMI is " + bmi);
                if (bmi < 18.5) {
                    System.out.println("Underweight");
                } else if (bmi < 25) {
                    System.out.println("Normal");
                } else if (bmi < 30) {
                    System.out.println("Overweight");
                } else {
                    System.out.println("Obese");
                }
            }
        }

        // Exercise 3.7: Display nonzero monetary units
        {
            System.out.println("\nExercise 3.7");
            System.out.print("Enter a nonnegative dollar amount (for example, 11.56): ");
            double amount = input.nextDouble();
            if (amount < 0) {
                System.out.println("The amount must not be negative.");
            } else {
                // Round to the nearest cent instead of truncating a binary double.
                long remainingCents = Math.round(amount * 100);
                long dollars = remainingCents / 100;
                remainingCents %= 100;
                long quarters = remainingCents / 25;
                remainingCents %= 25;
                long dimes = remainingCents / 10;
                remainingCents %= 10;
                long nickels = remainingCents / 5;
                long pennies = remainingCents % 5;
                if (dollars > 0) {
                    System.out.println(dollars + (dollars == 1 ? " dollar" : " dollars"));
                }
                if (quarters > 0) {
                    System.out.println(quarters + (quarters == 1 ? " quarter" : " quarters"));
                }
                if (dimes > 0) {
                    System.out.println(dimes + (dimes == 1 ? " dime" : " dimes"));
                }
                if (nickels > 0) {
                    System.out.println(nickels + (nickels == 1 ? " nickel" : " nickels"));
                }
                if (pennies > 0) {
                    System.out.println(pennies + (pennies == 1 ? " penny" : " pennies"));
                }
                // Zero has no nonzero denominations to display.
            }
        }

        // Exercise 3.8: Sort three integers
        {
            System.out.println("\nExercise 3.8");
            System.out.print("Enter three integers: ");
            int first = input.nextInt();
            int second = input.nextInt();
            int third = input.nextInt();
            // Each swap puts the smaller value first.
            if (first > second) {
                int temporary = first;
                first = second;
                second = temporary;
            }
            if (second > third) {
                int temporary = second;
                second = third;
                third = temporary;
            }
            if (first > second) {
                int temporary = first;
                first = second;
                second = temporary;
            }
            System.out.println("Non-decreasing order: " + first + " " + second + " " + third);
        }

        // Exercise 3.9: Compute the ISBN-10 checksum
        {
            System.out.println("\nExercise 3.9");
            // Read an integer as requested; formatting restores any leading zeros.
            System.out.print("Enter the first 9 ISBN digits as an integer: ");
            int isbn = input.nextInt();
            if (isbn < 0 || isbn > 999999999) {
                System.out.println("Invalid input: enter at most nine nonnegative decimal digits.");
            } else {
                int d1 = isbn / 100000000;
                int d2 = isbn / 10000000 % 10;
                int d3 = isbn / 1000000 % 10;
                int d4 = isbn / 100000 % 10;
                int d5 = isbn / 10000 % 10;
                int d6 = isbn / 1000 % 10;
                int d7 = isbn / 100 % 10;
                int d8 = isbn / 10 % 10;
                int d9 = isbn % 10;
                int checksum = (d1 + d2 * 2 + d3 * 3 + d4 * 4 + d5 * 5
                        + d6 * 6 + d7 * 7 + d8 * 8 + d9 * 9) % 11;
                String lastDigit = checksum == 10 ? "X" : "" + checksum;
                // %09d prints nine digits, padding the left side with zeros.
                System.out.printf("The ISBN-10 number is %09d%s%n", isbn, lastDigit);
            }
        }

        // Exercise 3.10: Multiplication quiz with two integers below 1000
        {
            System.out.println("\nExercise 3.10");
            int number1 = (int) (Math.random() * 1000);
            int number2 = (int) (Math.random() * 1000);
            System.out.print("What is " + number1 + " * " + number2 + "? ");
            int answer = input.nextInt();
            int product = number1 * number2;
            if (answer == product) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer is " + product + ".");
            }
        }

        // Exercise 3.11: Find the number of days in a month
        {
            System.out.println("\nExercise 3.11");
            System.out.print("Enter the month (1-12) and year: ");
            int month = input.nextInt();
            int year = input.nextInt();
            if (month < 1 || month > 12 || year < 1) {
                System.out.println("Invalid month or year.");
            } else {
                boolean leapYear = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
                int days;
                String monthName;
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
                System.out.println(monthName + " " + year + " has " + days + " days.");
            }
        }

        // Exercise 3.12: Check a three-digit palindrome
        {
            System.out.println("\nExercise 3.12");
            System.out.print("Enter a three-digit integer (negative is allowed): ");
            int number = input.nextInt();
            // Cast before abs so even Integer.MIN_VALUE can be checked safely.
            long magnitude = Math.abs((long) number);
            if (magnitude < 100 || magnitude > 999) {
                System.out.println("Invalid input: the magnitude must have three digits.");
            } else if (magnitude / 100 == magnitude % 10) {
                System.out.println(number + " is a palindrome.");
            } else {
                System.out.println(number + " is not a palindrome.");
            }
        }

        // Exercise 3.13: Compute taxes for all four filing statuses
        {
            System.out.println("\nExercise 3.13");
            // Use Table 3.2's 2009 rates, not current tax rates.
            System.out.println("Textbook tax calculation using the 2009 table.");
            System.out.println("0 = single; 1 = married jointly / qualifying widow(er);");
            System.out.println("2 = married separately; 3 = head of household");
            System.out.print("Enter filing status and taxable income: ");
            int status = input.nextInt();
            double income = input.nextDouble();
            if (status < 0 || status > 3 || income < 0) {
                System.out.println("Invalid status or taxable income.");
            } else {
                double limit1, limit2, limit3, limit4, limit5;
                if (status == 0) {
                    limit1 = 8350;
                    limit2 = 33950;
                    limit3 = 82250;
                    limit4 = 171550;
                    limit5 = 372950;
                } else if (status == 1) {
                    limit1 = 16700;
                    limit2 = 67900;
                    limit3 = 137050;
                    limit4 = 208850;
                    limit5 = 372950;
                } else if (status == 2) {
                    limit1 = 8350;
                    limit2 = 33950;
                    limit3 = 68525;
                    limit4 = 104425;
                    limit5 = 186475;
                } else {
                    limit1 = 11950;
                    limit2 = 45500;
                    limit3 = 117450;
                    limit4 = 190200;
                    limit5 = 372950;
                }
                // Each rate applies only to the income within its own bracket.
                double tax;
                if (income <= limit1) {
                    tax = income * 0.10;
                } else if (income <= limit2) {
                    tax = limit1 * 0.10 + (income - limit1) * 0.15;
                } else if (income <= limit3) {
                    tax = limit1 * 0.10 + (limit2 - limit1) * 0.15
                            + (income - limit2) * 0.25;
                } else if (income <= limit4) {
                    tax = limit1 * 0.10 + (limit2 - limit1) * 0.15
                            + (limit3 - limit2) * 0.25 + (income - limit3) * 0.28;
                } else if (income <= limit5) {
                    tax = limit1 * 0.10 + (limit2 - limit1) * 0.15
                            + (limit3 - limit2) * 0.25 + (limit4 - limit3) * 0.28
                            + (income - limit4) * 0.33;
                } else {
                    tax = limit1 * 0.10 + (limit2 - limit1) * 0.15
                            + (limit3 - limit2) * 0.25 + (limit4 - limit3) * 0.28
                            + (limit5 - limit4) * 0.33 + (income - limit5) * 0.35;
                }
                System.out.printf("Tax is $%.2f%n", tax);
            }
        }

        // Exercise 3.14: Guess heads or tails
        {
            System.out.println("\nExercise 3.14");
            int coin = (int) (Math.random() * 2);
            System.out.print("Guess the coin flip: heads (0) or tails (1): ");
            int guess = input.nextInt();
            if (guess != 0 && guess != 1) {
                System.out.println("Invalid guess: enter 0 or 1.");
            } else {
                System.out.println("The coin is " + (coin == 0 ? "heads" : "tails") + ".");
                System.out.println(guess == coin ? "Correct!" : "Incorrect.");
            }
        }

        // Exercise 3.15: Play the three-digit lottery
        {
            System.out.println("\nExercise 3.15");
            int lottery = (int) (Math.random() * 900) + 100;
            System.out.print("Enter a three-digit lottery guess (100-999): ");
            int guess = input.nextInt();
            if (guess < 100 || guess > 999) {
                System.out.println("Invalid guess: enter a three-digit positive integer.");
            } else {
                int a = lottery / 100;
                int b = lottery / 10 % 10;
                int c = lottery % 10;
                int x = guess / 100;
                int y = guess / 10 % 10;
                int z = guess % 10;
                // Six permutations correctly handle repeated digits as well.
                boolean allDigitsMatch = (x == a && y == b && z == c) || (x == a && y == c && z == b)
                        || (x == b && y == a && z == c) || (x == b && y == c && z == a)
                        || (x == c && y == a && z == b) || (x == c && y == b && z == a);
                boolean anyDigitMatches = x == a || x == b || x == c
                        || y == a || y == b || y == c || z == a || z == b || z == c;
                System.out.println("The lottery number is " + lottery + ".");
                if (guess == lottery) {
                    System.out.println("Exact match: you win $12,000!");
                } else if (allDigitsMatch) {
                    System.out.println("All digits match in a different order: you win $5,000!");
                } else if (anyDigitMatches) {
                    System.out.println("At least one digit matches: you win $2,000!");
                } else {
                    System.out.println("No matching digits. No prize.");
                }
            }
        }

        // Exercise 3.16: Generate a random point in a rectangle
        {
            System.out.println("\nExercise 3.16");
            // Center (0, 0), width 50, height 150.
            // x is in [-25, 25) and y is in [-75, 75).
            double x = Math.random() * 50 - 25;
            double y = Math.random() * 150 - 75;
            System.out.println("Random point: (" + x + ", " + y + ")");
        }

        // Exercise 3.17: Play scissor, rock, paper
        {
            System.out.println("\nExercise 3.17");
            int computer = (int) (Math.random() * 3);
            System.out.print("Enter scissor (0), rock (1), or paper (2): ");
            int player = input.nextInt();
            if (player < 0 || player > 2) {
                System.out.println("Invalid choice: enter 0, 1, or 2.");
            } else {
                String computerName = computer == 0 ? "scissor" : computer == 1 ? "rock" : "paper";
                String playerName = player == 0 ? "scissor" : player == 1 ? "rock" : "paper";
                System.out.println("The computer is " + computerName + ". You are " + playerName + ".");
                if (player == computer) {
                    System.out.println("It is a draw.");
                } else if ((player - computer + 3) % 3 == 1) {
                    System.out.println("You won!");
                } else {
                    System.out.println("You lost.");
                }
            }
        }

        // Exercise 3.18: Compute shipping cost
        {
            System.out.println("\nExercise 3.18");
            System.out.print("Enter package weight in pounds: ");
            double weight = input.nextDouble();
            // These are flat costs for each weight interval, not prices per pound.
            if (weight <= 0) {
                System.out.println("Invalid weight: it must be positive.");
            } else if (weight <= 2) {
                System.out.println("Shipping cost: $2.50");
            } else if (weight <= 4) {
                System.out.println("Shipping cost: $4.50");
            } else if (weight <= 10) {
                System.out.println("Shipping cost: $7.50");
            } else if (weight <= 20) {
                System.out.println("Shipping cost: $10.50");
            } else {
                System.out.println("The package cannot be shipped.");
            }
        }

        // Exercise 3.19: Compute a valid triangle perimeter
        {
            System.out.println("\nExercise 3.19");
            System.out.print("Enter three triangle edges: ");
            double side1 = input.nextDouble();
            double side2 = input.nextDouble();
            double side3 = input.nextDouble();
            boolean valid = side1 > 0 && side2 > 0 && side3 > 0
                    && side1 + side2 > side3 && side1 + side3 > side2
                    && side2 + side3 > side1;
            if (valid) {
                System.out.println("The perimeter is " + (side1 + side2 + side3));
            } else {
                System.out.println("Invalid input: the edges do not form a triangle.");
            }
        }

        // Exercise 3.20: Compute wind-chill temperature with input checks
        {
            System.out.println("\nExercise 3.20");
            System.out.print("Enter temperature in Fahrenheit (-58 to 41): ");
            double temperature = input.nextDouble();
            System.out.print("Enter wind speed in miles per hour (at least 2): ");
            double windSpeed = input.nextDouble();
            boolean validTemperature = temperature >= -58 && temperature <= 41;
            boolean validWind = windSpeed >= 2;
            if (!validTemperature || !validWind) {
                if (!validTemperature) {
                    System.out.println("Invalid temperature: use -58 through 41 Fahrenheit.");
                }
                if (!validWind) {
                    System.out.println("Invalid wind speed: use at least 2 miles per hour.");
                }
            } else {
                double windPower = Math.pow(windSpeed, 0.16);
                double windChill = 35.74 + 0.6215 * temperature - 35.75 * windPower
                        + 0.4275 * temperature * windPower;
                System.out.println("The wind-chill temperature is " + windChill + " Fahrenheit.");
            }
        }

        // Exercise 3.21: Find the day of the week with Zeller's congruence
        {
            System.out.println("\nExercise 3.21");
            System.out.print("Enter year (1-9999), month (1-12), and day: ");
            int year = input.nextInt();
            int month = input.nextInt();
            int day = input.nextInt();
            boolean leapYear = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
            int daysInMonth = 31;
            if (month == 2) {
                daysInMonth = leapYear ? 29 : 28;
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                daysInMonth = 30;
            }
            if (year < 1 || year > 9999 || month < 1 || month > 12 || day < 1 || day > daysInMonth) {
                System.out.println("Invalid date.");
            } else {
                // January and February belong to the previous year
                if (month == 1 || month == 2) {
                    month += 12;
                    year--;
                }
                int k = year % 100;
                int j = year / 100;
                // All divisions here intentionally use integer division.
                int h = (day + 26 * (month + 1) / 10 + k + k / 4 + j / 4 + 5 * j) % 7;
                String dayName;
                switch (h) {
                    case 0:
                        dayName = "Saturday";
                        break;
                    case 1:
                        dayName = "Sunday";
                        break;
                    case 2:
                        dayName = "Monday";
                        break;
                    case 3:
                        dayName = "Tuesday";
                        break;
                    case 4:
                        dayName = "Wednesday";
                        break;
                    case 5:
                        dayName = "Thursday";
                        break;
                    default:
                        dayName = "Friday";
                }
                System.out.println("Day of the week is " + dayName + ".");
            }
        }

        // Exercise 3.22: Check whether a point is in a circle
        {
            System.out.println("\nExercise 3.22");
            // Circle center (0, 0), radius 10. The boundary counts as inside.
            System.out.print("Enter a point's x and y coordinates: ");
            double x = input.nextDouble();
            double y = input.nextDouble();
            boolean inside = x * x + y * y <= 100;
            System.out.println("Point (" + x + ", " + y + ") is "
                    + (inside ? "in" : "not in") + " the circle.");
        }

        // Exercise 3.23: Check whether a point is in a rectangle
        {
            System.out.println("\nExercise 3.23");
            // Follow the stated center (1, 1), width 10, height 5.
            System.out.print("Enter a point's x and y coordinates: ");
            double x = input.nextDouble();
            double y = input.nextDouble();
            boolean inside = Math.abs(x - 1) <= 10.0 / 2 && Math.abs(y - 1) <= 5.0 / 2;
            System.out.println("Point (" + x + ", " + y + ") is "
                    + (inside ? "in" : "not in") + " the rectangle.");
        }

        // Exercise 3.24: Pick a card from a 52-card deck
        {
            System.out.println("\nExercise 3.24");
            int card = (int) (Math.random() * 52);
            int rank = card % 13 + 1;
            int suit = card / 13;
            String rankName;
            switch (rank) {
                case 1:
                    rankName = "Ace";
                    break;
                case 11:
                    rankName = "Jack";
                    break;
                case 12:
                    rankName = "Queen";
                    break;
                case 13:
                    rankName = "King";
                    break;
                default:
                    rankName = "" + rank;
            }
            String suitName;
            switch (suit) {
                case 0:
                    suitName = "Clubs";
                    break;
                case 1:
                    suitName = "Diamonds";
                    break;
                case 2:
                    suitName = "Hearts";
                    break;
                default:
                    suitName = "Spades";
            }
            System.out.println("The card you picked is " + rankName + " of " + suitName + ".");
        }

        // Exercise 3.25: Find the intersection of two lines
        {
            System.out.println("\nExercise 3.25");
            System.out.print("Enter x1, y1, x2, y2, x3, y3, x4, and y4: ");
            double x1 = input.nextDouble();
            double y1 = input.nextDouble();
            double x2 = input.nextDouble();
            double y2 = input.nextDouble();
            double x3 = input.nextDouble();
            double y3 = input.nextDouble();
            double x4 = input.nextDouble();
            double y4 = input.nextDouble();
            if ((x1 == x2 && y1 == y2) || (x3 == x4 && y3 == y4)) {
                System.out.println("Invalid line: its two defining points must be different.");
            } else {
                // Convert the lines to a*x + b*y = e and c*x + d*y = f.
                double a = y1 - y2;
                double b = x2 - x1;
                double c = y3 - y4;
                double d = x4 - x3;
                double e = a * x1 + b * y1;
                double f = c * x3 + d * y3;
                double determinant = a * d - b * c;
                if (determinant == 0) {
                    // Coincident lines also have a zero determinant.
                    if (a * x3 + b * y3 == e) {
                        System.out.println("The lines coincide; there is no unique intersection.");
                    } else {
                        System.out.println("The two lines are parallel.");
                    }
                } else {
                    double x = (e * d - b * f) / determinant;
                    double y = (a * f - e * c) / determinant;
                    System.out.println("The intersecting point is at (" + x + ", " + y + ").");
                }
            }
        }

        // Exercise 3.26: Use AND, OR, and exclusive OR
        {
            System.out.println("\nExercise 3.26");
            System.out.print("Enter an integer: ");
            int number = input.nextInt();
            boolean divisibleBy4 = number % 4 == 0;
            boolean divisibleBy5 = number % 5 == 0;
            System.out.println("Is " + number + " divisible by 4 and 5? "
                    + (divisibleBy4 && divisibleBy5));
            System.out.println("Is " + number + " divisible by 4 or 5? "
                    + (divisibleBy4 || divisibleBy5));
            System.out.println("Is " + number + " divisible by 4 or 5 but not both? "
                    + (divisibleBy4 ^ divisibleBy5));
        }

        // Exercise 3.27: Check whether a point is in a right triangle
        {
            System.out.println("\nExercise 3.27");
            // Vertices: (0, 0), (200, 0), (0, 100). Include the boundary.
            // The sloping edge is y = 100 - x / 2, or x + 2*y = 200.
            System.out.print("Enter a point's x and y coordinates: ");
            double x = input.nextDouble();
            double y = input.nextDouble();
            boolean inside = x >= 0 && y >= 0 && x + 2 * y <= 200;
            System.out.println(inside ? "The point is in the triangle." : "The point is not in the triangle.");
        }

        // Exercise 3.28: Compare two rectangles
        {
            System.out.println("\nExercise 3.28");
            // Rectangles have sides parallel to the axes. Touching counts as overlap.
            System.out.print("Enter r1 center x, center y, width, and height: ");
            double x1 = input.nextDouble();
            double y1 = input.nextDouble();
            double width1 = input.nextDouble();
            double height1 = input.nextDouble();
            System.out.print("Enter r2 center x, center y, width, and height: ");
            double x2 = input.nextDouble();
            double y2 = input.nextDouble();
            double width2 = input.nextDouble();
            double height2 = input.nextDouble();
            if (width1 <= 0 || height1 <= 0 || width2 <= 0 || height2 <= 0) {
                System.out.println("Invalid dimensions: widths and heights must be positive.");
            } else {
                double dx = Math.abs(x2 - x1);
                double dy = Math.abs(y2 - y1);
                boolean inside = dx + width2 / 2 <= width1 / 2
                        && dy + height2 / 2 <= height1 / 2;
                boolean overlaps = dx <= width1 / 2 + width2 / 2
                        && dy <= height1 / 2 + height2 / 2;
                if (inside) {
                    System.out.println("r2 is inside r1.");
                } else if (overlaps) {
                    System.out.println("r2 overlaps r1.");
                } else {
                    System.out.println("r2 does not overlap r1.");
                }
            }
        }

        // Exercise 3.29: Compare two circles
        {
            System.out.println("\nExercise 3.29");
            System.out.print("Enter circle1 center x, center y, and radius: ");
            double x1 = input.nextDouble();
            double y1 = input.nextDouble();
            double radius1 = input.nextDouble();
            System.out.print("Enter circle2 center x, center y, and radius: ");
            double x2 = input.nextDouble();
            double y2 = input.nextDouble();
            double radius2 = input.nextDouble();
            if (radius1 <= 0 || radius2 <= 0) {
                System.out.println("Invalid radii: both must be positive.");
            } else {
                double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
                if (distance + radius2 <= radius1) {
                    System.out.println("circle2 is inside circle1.");
                } else if (distance <= radius1 + radius2) {
                    // Includes tangency and the case where circle1 is inside circle2.
                    System.out.println("circle2 overlaps circle1.");
                } else {
                    System.out.println("circle2 does not overlap circle1.");
                }
            }
        }

        // Exercise 3.30: Display current time using a 12-hour clock
        {
            System.out.println("\nExercise 3.30");
            System.out.print("Enter the time zone offset to GMT in hours: ");
            double offset = input.nextDouble();
            if (offset < -12 || offset > 14) {
                System.out.println("Invalid offset: use -12 through 14 hours.");
            } else {
                long totalSeconds = System.currentTimeMillis() / 1000;
                long offsetSeconds = Math.round(offset * 3600);
                long localSeconds = Math.floorMod(totalSeconds + offsetSeconds, 86400L);
                int hour24 = (int) (localSeconds / 3600);
                int minute = (int) (localSeconds / 60 % 60);
                int second = (int) (localSeconds % 60);
                int hour12 = hour24 % 12;
                if (hour12 == 0) {
                    hour12 = 12;
                }
                String period = hour24 < 12 ? "AM" : "PM";
                System.out.printf("The current time is %d:%02d:%02d %s%n", hour12, minute, second, period);
            }
        }

        // Exercise 3.31: Convert between U.S. dollars and Chinese RMB
        {
            System.out.println("\nExercise 3.31");
            System.out.print("Enter the exchange rate (RMB per U.S. dollar): ");
            double rate = input.nextDouble();
            System.out.print("Enter 0 for dollars to RMB or 1 for RMB to dollars: ");
            int direction = input.nextInt();
            if (rate <= 0) {
                System.out.println("Invalid exchange rate: it must be positive.");
            } else if (direction != 0 && direction != 1) {
                System.out.println("Incorrect input: choose 0 or 1.");
            } else {
                System.out.print(direction == 0 ? "Enter the dollar amount: " : "Enter the RMB amount: ");
                double amount = input.nextDouble();
                if (amount < 0) {
                    System.out.println("The amount must not be negative.");
                } else if (direction == 0) {
                    System.out.printf("$%.2f is %.2f yuan.%n", amount, amount * rate);
                } else {
                    System.out.printf("%.2f yuan is $%.2f.%n", amount, amount / rate);
                }
            }
        }

        // Exercise 3.32: Find a point relative to a directed line
        {
            System.out.println("\nExercise 3.32");
            System.out.print("Enter x0, y0, x1, y1, x2, and y2: ");
            double x0 = input.nextDouble();
            double y0 = input.nextDouble();
            double x1 = input.nextDouble();
            double y1 = input.nextDouble();
            double x2 = input.nextDouble();
            double y2 = input.nextDouble();
            if (x0 == x1 && y0 == y1) {
                System.out.println("Invalid line: p0 and p1 must be different.");
            } else {
                double cross = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
                // A scaled tolerance allows for rounding in the two products.
                double scale = Math.abs((x1 - x0) * (y2 - y0))
                        + Math.abs((x2 - x0) * (y1 - y0));
                double tolerance = 1e-12 * scale;
                if (cross > tolerance) {
                    System.out.println("p2 is on the left side of the line.");
                } else if (cross < -tolerance) {
                    System.out.println("p2 is on the right side of the line.");
                } else {
                    System.out.println("p2 is on the same line.");
                }
            }
        }

        // Exercise 3.33: Compare package unit prices
        {
            System.out.println("\nExercise 3.33");
            System.out.print("Enter weight and price for package 1: ");
            double weight1 = input.nextDouble();
            double price1 = input.nextDouble();
            System.out.print("Enter weight and price for package 2 (same weight unit): ");
            double weight2 = input.nextDouble();
            double price2 = input.nextDouble();
            if (weight1 <= 0 || weight2 <= 0 || price1 < 0 || price2 < 0) {
                System.out.println("Invalid input: use positive weights and nonnegative prices.");
            } else {
                double unitPrice1 = price1 / weight1;
                double unitPrice2 = price2 / weight2;
                if (unitPrice1 < unitPrice2) {
                    System.out.println("Package 1 has a better price per unit weight.");
                } else if (unitPrice2 < unitPrice1) {
                    System.out.println("Package 2 has a better price per unit weight.");
                } else {
                    System.out.println("The two packages have the same price per unit weight.");
                }
            }
        }

        // Exercise 3.34: Check whether a point lies on a line segment
        {
            System.out.println("\nExercise 3.34");
            System.out.print("Enter x0, y0, x1, y1, x2, and y2: ");
            double x0 = input.nextDouble();
            double y0 = input.nextDouble();
            double x1 = input.nextDouble();
            double y1 = input.nextDouble();
            double x2 = input.nextDouble();
            double y2 = input.nextDouble();
            double cross = (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
            double scale = Math.abs((x1 - x0) * (y2 - y0))
                    + Math.abs((x2 - x0) * (y1 - y0));
            // Collinear is not enough - p2 must also be between the endpoints.
            boolean onLine = Math.abs(cross) <= 1e-12 * scale;
            boolean withinBounds = x2 >= Math.min(x0, x1) && x2 <= Math.max(x0, x1)
                    && y2 >= Math.min(y0, y1) && y2 <= Math.max(y0, y1);
            boolean onSegment = onLine && withinBounds;
            System.out.println("(" + x2 + ", " + y2 + ") is " + (onSegment ? "on" : "not on")
                    + " the line segment from (" + x0 + ", " + y0 + ") to (" + x1 + ", " + y1 + ").");
        }

        // Exercise 3.35: Determine whether an integer is even or odd
        {
            System.out.println("\nExercise 3.35");
            System.out.print("Enter an integer: ");
            int number = input.nextInt();
            if (number % 2 == 0) {
                System.out.println(number + " is an even number.");
            } else {
                System.out.println(number + " is an odd number.");
            }
        }

        input.close();
    }
}
