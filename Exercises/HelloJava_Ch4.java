/* Introduction to Java Programming and Data Structures (Comprehensive Version) 
11th Edition 2018*/

// Chapter 4 Exercises - Mathematical Functions, Characters, and Strings
package Exercises;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

/*
 * Notes:
 * - 4.3 uses the exact GPS coordinates in Section 4.1 and the requested
 *   two-triangle approximation with great-circle side lengths.
 * - 4.7 corrects the sample's fourth x-coordinate: -59.08, not -58.08.
 * - Short loops handle repeated checks in 4.6, 4.19, 4.21, and 4.26.
 * - 4.9 and 4.20 include Unicode code-point handling as a small extension.
 * - 4.23 uses BigDecimal (an optional extension) for exact decimal money and
 *   rounds each monetary amount to cents with HALF_UP. Its purpose here is
 *   the textbook calculation from supplied rates, not a complete payroll system.
 */

public class HelloJava_Ch4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Chapter 4: type one answer per prompt, then press Enter.");

        // Exercise 4.1: Compute the area of a pentagon
        {
            System.out.println("\nExercise 4.1");
            System.out.print("Enter the center-to-vertex length as a number (example: 5.5): ");
            double radius = Double.parseDouble(input.nextLine().trim());
            if (radius <= 0) {
                System.out.println("Invalid length: enter a positive number.");
            } else {
                // Java's trigonometric methods take angles in radians.
                double side = 2 * radius * Math.sin(Math.PI / 5);
                double area = 5 * side * side / (4 * Math.tan(Math.PI / 5));
                // %.2f rounds the displayed value to two decimal places.
                System.out.printf(Locale.US, "The area of the pentagon is %.2f%n", area);
            }
        }

        // Exercise 4.2: Compute great-circle distance
        {
            System.out.println("\nExercise 4.2");
            // Use the page's convention: north/west positive, south/east negative.
            // Reversing BOTH longitude signs gives the same distance.
            System.out.print("Enter point 1 latitude in degrees (example: 39.55): ");
            double latitude1 = Double.parseDouble(input.nextLine().trim());
            System.out.print("Enter point 1 longitude in degrees (example: -116.25): ");
            double longitude1 = Double.parseDouble(input.nextLine().trim());
            System.out.print("Enter point 2 latitude in degrees (example: 41.5): ");
            double latitude2 = Double.parseDouble(input.nextLine().trim());
            System.out.print("Enter point 2 longitude in degrees (example: 87.37): ");
            double longitude2 = Double.parseDouble(input.nextLine().trim());
            if (Math.abs(latitude1) > 90 || Math.abs(latitude2) > 90
                    || Math.abs(longitude1) > 180 || Math.abs(longitude2) > 180) {
                System.out.println("Invalid coordinates: latitude must be -90 to 90 and longitude -180 to 180.");
            } else {
                double lat1 = Math.toRadians(latitude1);
                double lon1 = Math.toRadians(longitude1);
                double lat2 = Math.toRadians(latitude2);
                double lon2 = Math.toRadians(longitude2);
                double cosine = Math.sin(lat1) * Math.sin(lat2)
                        + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2);
                // Rounding can put cosine just outside [-1, 1]; keep acos valid.
                cosine = Math.max(-1.0, Math.min(1.0, cosine));
                double distance = 6371.01 * Math.acos(cosine);
                System.out.println("The distance between the two points is " + distance + " km.");
            }
        }

        // Exercise 4.3: Estimate the area enclosed by four cities
        {
            System.out.println("\nExercise 4.3");
            // Coordinates are from the figure in Section 4.1.
            // These GPS coordinates use negative longitude for west.
            double atlantaLat = Math.toRadians(33.7489954);
            double atlantaLon = Math.toRadians(-84.3879824);
            double orlandoLat = Math.toRadians(28.5383355);
            double orlandoLon = Math.toRadians(-81.3792365);
            double savannahLat = Math.toRadians(32.0835407);
            double savannahLon = Math.toRadians(-81.0998342);
            double charlotteLat = Math.toRadians(35.2270869);
            double charlotteLon = Math.toRadians(-80.8431267);
            double earthRadius = 6371.01;

            // Split the quadrilateral along the Atlanta-Savannah diagonal.
            double aoCosine = Math.sin(atlantaLat) * Math.sin(orlandoLat)
                    + Math.cos(atlantaLat) * Math.cos(orlandoLat) * Math.cos(atlantaLon - orlandoLon);
            double aoDistance = earthRadius * Math.acos(Math.max(-1.0, Math.min(1.0, aoCosine)));
            double osCosine = Math.sin(orlandoLat) * Math.sin(savannahLat)
                    + Math.cos(orlandoLat) * Math.cos(savannahLat) * Math.cos(orlandoLon - savannahLon);
            double osDistance = earthRadius * Math.acos(Math.max(-1.0, Math.min(1.0, osCosine)));
            double asCosine = Math.sin(atlantaLat) * Math.sin(savannahLat)
                    + Math.cos(atlantaLat) * Math.cos(savannahLat) * Math.cos(atlantaLon - savannahLon);
            double asDistance = earthRadius * Math.acos(Math.max(-1.0, Math.min(1.0, asCosine)));
            double scCosine = Math.sin(savannahLat) * Math.sin(charlotteLat)
                    + Math.cos(savannahLat) * Math.cos(charlotteLat) * Math.cos(savannahLon - charlotteLon);
            double scDistance = earthRadius * Math.acos(Math.max(-1.0, Math.min(1.0, scCosine)));
            double caCosine = Math.sin(charlotteLat) * Math.sin(atlantaLat)
                    + Math.cos(charlotteLat) * Math.cos(atlantaLat) * Math.cos(charlotteLon - atlantaLon);
            double caDistance = earthRadius * Math.acos(Math.max(-1.0, Math.min(1.0, caCosine)));

            // Heron's formula gives a flat-triangle approximation using these distances.
            // This is the textbook's estimate, not an exact spherical polygon area.
            double s1 = (aoDistance + osDistance + asDistance) / 2;
            double s2 = (asDistance + scDistance + caDistance) / 2;
            double area1 = Math.sqrt(Math.max(0, s1 * (s1 - aoDistance)
                    * (s1 - osDistance) * (s1 - asDistance)));
            double area2 = Math.sqrt(Math.max(0, s2 * (s2 - asDistance)
                    * (s2 - scDistance) * (s2 - caDistance)));
            System.out.printf(Locale.US, "Estimated area enclosed by the four cities: %.2f square km%n", area1 + area2);
        }

        // Exercise 4.4: Compute the area of a hexagon
        {
            System.out.println("\nExercise 4.4");
            System.out.print("Enter the hexagon side length (example: 5.5): ");
            double side = Double.parseDouble(input.nextLine().trim());
            if (side <= 0) {
                System.out.println("Invalid side length: enter a positive number.");
            } else {
                double area = 6 * side * side / (4 * Math.tan(Math.PI / 6));
                System.out.printf(Locale.US, "The area of the hexagon is %.2f%n", area);
            }
        }

        // Exercise 4.5: Compute the area of a regular polygon
        {
            System.out.println("\nExercise 4.5");
            System.out.print("Enter the integer number of sides (example: 5): ");
            int sides = Integer.parseInt(input.nextLine().trim());
            System.out.print("Enter the side length (example: 6.5): ");
            double sideLength = Double.parseDouble(input.nextLine().trim());
            if (sides < 3 || sideLength <= 0) {
                System.out.println("Invalid polygon: use at least 3 sides and a positive side length.");
            } else {
                double area = sides * sideLength * sideLength / (4 * Math.tan(Math.PI / sides));
                System.out.println("The area of the polygon is " + area);
            }
        }

        // Exercise 4.6: Generate three random points on a circle
        {
            System.out.println("\nExercise 4.6");
            double radius = 40;
            double theta1 = Math.random() * 2 * Math.PI;
            double theta2 = Math.random() * 2 * Math.PI;
            // A short loop prevents almost identical points, which make angles unstable.
            while (Math.abs(Math.sin((theta2 - theta1) / 2)) < 1e-6) {
                theta2 = Math.random() * 2 * Math.PI;
            }
            double theta3 = Math.random() * 2 * Math.PI;
            while (Math.abs(Math.sin((theta3 - theta1) / 2)) < 1e-6
                    || Math.abs(Math.sin((theta3 - theta2) / 2)) < 1e-6) {
                theta3 = Math.random() * 2 * Math.PI;
            }
            double x1 = radius * Math.cos(theta1);
            double y1 = radius * Math.sin(theta1);
            double x2 = radius * Math.cos(theta2);
            double y2 = radius * Math.sin(theta2);
            double x3 = radius * Math.cos(theta3);
            double y3 = radius * Math.sin(theta3);

            // a is opposite point 1, b opposite point 2, and c opposite point 3.
            double a = Math.hypot(x2 - x3, y2 - y3);
            double b = Math.hypot(x1 - x3, y1 - y3);
            double c = Math.hypot(x1 - x2, y1 - y2);
            double cosineA = (b * b + c * c - a * a) / (2 * b * c);
            double cosineB = (a * a + c * c - b * b) / (2 * a * c);
            double cosineC = (a * a + b * b - c * c) / (2 * a * b);
            double angleA = Math.toDegrees(Math.acos(Math.max(-1, Math.min(1, cosineA))));
            double angleB = Math.toDegrees(Math.acos(Math.max(-1, Math.min(1, cosineB))));
            double angleC = Math.toDegrees(Math.acos(Math.max(-1, Math.min(1, cosineC))));
            System.out.printf(Locale.US, "Point 1: (%.2f, %.2f)%n", x1, y1);
            System.out.printf(Locale.US, "Point 2: (%.2f, %.2f)%n", x2, y2);
            System.out.printf(Locale.US, "Point 3: (%.2f, %.2f)%n", x3, y3);
            System.out.printf(Locale.US, "Triangle angles: %.2f, %.2f, %.2f degrees%n", angleA, angleB, angleC);
        }

        // Exercise 4.7: Display the five pentagon vertices
        {
            System.out.println("\nExercise 4.7");
            System.out.print("Enter the radius of the bounding circle (example: 100.52): ");
            double radius = Double.parseDouble(input.nextLine().trim());
            if (radius <= 0) {
                System.out.println("Invalid radius: enter a positive number.");
            } else {
                // Figure 4.4c order: p1 at 18 degrees, then 90, 162, 234, 306.
                // The top point p2 is the book's 0 o'clock position.
                System.out.println("The five pentagon vertices, in p1-to-p5 order, are:");
                System.out.printf(Locale.US, "p1: (%.2f, %.2f)%n",
                        radius * Math.cos(Math.toRadians(18)), radius * Math.sin(Math.toRadians(18)));
                System.out.printf(Locale.US, "p2: (%.2f, %.2f)%n", 0.0, radius);
                System.out.printf(Locale.US, "p3: (%.2f, %.2f)%n",
                        radius * Math.cos(Math.toRadians(162)), radius * Math.sin(Math.toRadians(162)));
                System.out.printf(Locale.US, "p4: (%.2f, %.2f)%n",
                        radius * Math.cos(Math.toRadians(234)), radius * Math.sin(Math.toRadians(234)));
                System.out.printf(Locale.US, "p5: (%.2f, %.2f)%n",
                        radius * Math.cos(Math.toRadians(306)), radius * Math.sin(Math.toRadians(306)));
                // The sample's p4 x-coordinate -58.08 is a typo; it should be -59.08.
            }
        }

        // Exercise 4.8: Display the ASCII code of a character
        {
            System.out.println("\nExercise 4.8");
            System.out.print("Enter exactly one ASCII character (example: E): ");
            String text = input.nextLine();
            if (text.length() != 1 || text.charAt(0) > 127) {
                System.out.println("Invalid input: enter one ASCII character with a code from 0 to 127.");
            } else {
                char character = text.charAt(0);
                int asciiCode = character;
                System.out.println("The ASCII code for character " + character + " is " + asciiCode);
            }
        }

        // Exercise 4.9: Display the Unicode value of a character
        {
            System.out.println("\nExercise 4.9");
            System.out.print("Enter one Unicode character (example: E): ");
            String text = input.nextLine();
            // codePointCount also handles characters represented by two Java char values.
            if (text.codePointCount(0, text.length()) != 1) {
                System.out.println("Invalid input: enter exactly one Unicode code point.");
            } else {
                int codePoint = text.codePointAt(0);
                System.out.printf(Locale.US, "The Unicode value for %s is %d (U+%04X).%n", text, codePoint, codePoint);
            }
        }

        // Exercise 4.10: Guess a birthday using Y and N
        {
            System.out.println("\nExercise 4.10");
            // Think of a day of the month from 1 to 31; do not type the day itself.
            // Answer Y or N for each set. Each yes contributes 1, 2, 4, 8, or 16.
            System.out.println("Think of your birthday's day of the month (1-31).");
            int day = 0;
            boolean validAnswers = true;

            System.out.println("\nSet 1:\n 1  3  5  7\n 9 11 13 15\n17 19 21 23\n25 27 29 31");
            System.out.print("Is your day in Set 1? Enter Y for yes or N for no: ");
            String answer1 = input.nextLine().trim();
            if (answer1.equalsIgnoreCase("Y")) {
                day += 1;
            } else if (!answer1.equalsIgnoreCase("N")) {
                validAnswers = false;
            }

            System.out.println("\nSet 2:\n 2  3  6  7\n10 11 14 15\n18 19 22 23\n26 27 30 31");
            System.out.print("Is your day in Set 2? Enter Y for yes or N for no: ");
            String answer2 = input.nextLine().trim();
            if (answer2.equalsIgnoreCase("Y")) {
                day += 2;
            } else if (!answer2.equalsIgnoreCase("N")) {
                validAnswers = false;
            }

            System.out.println("\nSet 3:\n 4  5  6  7\n12 13 14 15\n20 21 22 23\n28 29 30 31");
            System.out.print("Is your day in Set 3? Enter Y for yes or N for no: ");
            String answer3 = input.nextLine().trim();
            if (answer3.equalsIgnoreCase("Y")) {
                day += 4;
            } else if (!answer3.equalsIgnoreCase("N")) {
                validAnswers = false;
            }

            System.out.println("\nSet 4:\n 8  9 10 11\n12 13 14 15\n24 25 26 27\n28 29 30 31");
            System.out.print("Is your day in Set 4? Enter Y for yes or N for no: ");
            String answer4 = input.nextLine().trim();
            if (answer4.equalsIgnoreCase("Y")) {
                day += 8;
            } else if (!answer4.equalsIgnoreCase("N")) {
                validAnswers = false;
            }

            System.out.println("\nSet 5:\n16 17 18 19\n20 21 22 23\n24 25 26 27\n28 29 30 31");
            System.out.print("Is your day in Set 5? Enter Y for yes or N for no: ");
            String answer5 = input.nextLine().trim();
            if (answer5.equalsIgnoreCase("Y")) {
                day += 16;
            } else if (!answer5.equalsIgnoreCase("N")) {
                validAnswers = false;
            }

            if (!validAnswers) {
                System.out.println("Invalid answer: each response must be Y or N.");
            } else if (day == 0) {
                System.out.println("All N answers do not describe a day from 1 to 31.");
            } else {
                System.out.println("Your birthday is on day " + day + " of the month!");
            }
        }

        // Exercise 4.11: Convert four binary digits to decimal
        {
            System.out.println("\nExercise 4.11");
            System.out.print("Enter exactly four binary digits (example: 0111): ");
            String binary = input.nextLine().trim();
            // Length must be checked before charAt accesses positions 0 through 3.
            boolean valid = binary.length() == 4;
            if (valid) {
                valid = (binary.charAt(0) == '0' || binary.charAt(0) == '1')
                        && (binary.charAt(1) == '0' || binary.charAt(1) == '1')
                        && (binary.charAt(2) == '0' || binary.charAt(2) == '1')
                        && (binary.charAt(3) == '0' || binary.charAt(3) == '1');
            }
            if (!valid) {
                System.out.println("Invalid binary input: use exactly four 0/1 digits.");
            } else {
                // Subtracting '0' converts a digit character to its numeric value.
                int decimal = (binary.charAt(0) - '0') * 8 + (binary.charAt(1) - '0') * 4
                        + (binary.charAt(2) - '0') * 2 + (binary.charAt(3) - '0');
                System.out.println("The decimal value is " + decimal);
            }
        }

        // Exercise 4.12: Convert a hexadecimal digit to binary
        {
            System.out.println("\nExercise 4.12");
            System.out.print("Enter one hex digit (0-9 or A-F; lowercase also accepted): ");
            String text = input.nextLine().trim().toUpperCase(Locale.US);
            if (text.length() != 1) {
                System.out.println("Invalid input: enter exactly one hex digit.");
            } else {
                char digit = text.charAt(0);
                int value = -1;
                if (digit >= '0' && digit <= '9') {
                    value = digit - '0';
                } else if (digit >= 'A' && digit <= 'F') {
                    value = digit - 'A' + 10;
                }
                if (value == -1) {
                    System.out.println(text + " is an invalid input.");
                } else {
                    // The standard conversion omits unnecessary leading zeros.
                    System.out.println("The binary value is " + Integer.toBinaryString(value));
                }
            }
        }

        // Exercise 4.13: Determine whether a letter is a vowel or consonant
        {
            System.out.println("\nExercise 4.13");
            System.out.print("Enter one English letter (example: a): ");
            String text = input.nextLine().trim();
            if (text.length() != 1) {
                System.out.println("Invalid input: enter one English letter.");
            } else {
                char letter = Character.toLowerCase(text.charAt(0));
                if (letter < 'a' || letter > 'z') {
                    System.out.println(text + " is an invalid input.");
                } else if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') {
                    System.out.println(text + " is a vowel.");
                } else {
                    System.out.println(text + " is a consonant.");
                }
            }
        }

        // Exercise 4.14: Convert a letter grade to a number
        {
            System.out.println("\nExercise 4.14");
            System.out.print("Enter a grade A, B, C, D, or F (lowercase also accepted): ");
            String grade = input.nextLine().trim().toUpperCase(Locale.US);
            int value;
            switch (grade) {
                case "A":
                    value = 4;
                    break;
                case "B":
                    value = 3;
                    break;
                case "C":
                    value = 2;
                    break;
                case "D":
                    value = 1;
                    break;
                case "F":
                    value = 0;
                    break;
                default:
                    value = -1;
            }
            if (value == -1) {
                System.out.println(grade + " is an invalid grade.");
            } else {
                System.out.println("The numeric value for grade " + grade + " is " + value);
            }
        }

        // Exercise 4.15: Convert a letter using a telephone keypad
        {
            System.out.println("\nExercise 4.15");
            System.out.print("Enter one English letter, uppercase or lowercase: ");
            String text = input.nextLine().trim();
            if (text.length() != 1) {
                System.out.println("Invalid input: enter one English letter.");
            } else {
                char letter = Character.toUpperCase(text.charAt(0));
                int number;
                if (letter < 'A' || letter > 'Z') {
                    number = -1;
                } else if (letter <= 'C') {
                    number = 2;
                } else if (letter <= 'F') {
                    number = 3;
                } else if (letter <= 'I') {
                    number = 4;
                } else if (letter <= 'L') {
                    number = 5;
                } else if (letter <= 'O') {
                    number = 6;
                } else if (letter <= 'S') {
                    number = 7;
                } else if (letter <= 'V') {
                    number = 8;
                } else {
                    number = 9;
                }
                if (number == -1) {
                    System.out.println(text + " is an invalid input.");
                } else {
                    System.out.println("The corresponding number is " + number);
                }
            }
        }

        // Exercise 4.16: Generate a random lowercase letter
        {
            System.out.println("\nExercise 4.16");
            // Offset 0 gives 'a'; offset 25 gives 'z'.
            char letter = (char) ('a' + (int) (Math.random() * 26));
            System.out.println("Random lowercase letter: " + letter);
        }

        // Exercise 4.17: Find the number of days from a month abbreviation
        {
            System.out.println("\nExercise 4.17");
            System.out.print("Enter a positive year as an integer (example: 2016): ");
            int year = Integer.parseInt(input.nextLine().trim());
            System.out.print("Enter exactly Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, or Dec: ");
            String month = input.nextLine().trim();
            boolean leapYear = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
            int days;
            // Case matters in this exercise: jan is invalid, while Jan is valid.
            switch (month) {
                case "Jan":
                case "Mar":
                case "May":
                case "Jul":
                case "Aug":
                case "Oct":
                case "Dec":
                    days = 31;
                    break;
                case "Apr":
                case "Jun":
                case "Sep":
                case "Nov":
                    days = 30;
                    break;
                case "Feb":
                    days = leapYear ? 29 : 28;
                    break;
                default:
                    days = 0;
            }
            if (year < 1) {
                System.out.println("Invalid year: enter a positive integer.");
            } else if (days == 0) {
                System.out.println(month + " is not a correct month name.");
            } else {
                System.out.println(month + " " + year + " has " + days + " days.");
            }
        }

        // Exercise 4.18: Display student major and status
        {
            System.out.println("\nExercise 4.18");
            System.out.print("Enter a two-character code (I1, C2, or A3, for example): ");
            String code = input.nextLine().trim().toUpperCase(Locale.US);
            if (code.length() != 2) {
                System.out.println("Invalid input: use one major letter followed by a digit 1-4.");
            } else {
                String major;
                String status;
                switch (code.charAt(0)) {
                    case 'I':
                        major = "Information Management";
                        break;
                    case 'C':
                        major = "Computer Science";
                        break;
                    case 'A':
                        major = "Accounting";
                        break;
                    default:
                        major = "";
                }
                switch (code.charAt(1)) {
                    case '1':
                        status = "Freshman";
                        break;
                    case '2':
                        status = "Sophomore";
                        break;
                    case '3':
                        status = "Junior";
                        break;
                    case '4':
                        status = "Senior";
                        break;
                    default:
                        status = "";
                }
                if (major.isEmpty() || status.isEmpty()) {
                    System.out.println("Invalid input.");
                } else {
                    System.out.println(major + " " + status);
                }
            }
        }

        // Exercise 4.19: Compute ISBN-10 from a string
        {
            System.out.println("\nExercise 4.19");
            System.out.print("Enter the first NINE ISBN digits as text (example: 013601267): ");
            String isbn = input.nextLine().trim();
            boolean valid = isbn.length() == 9;
            int sum = 0;
            // A short loop applies the same character check and weight to each digit.
            for (int i = 0; valid && i < isbn.length(); i++) {
                char digit = isbn.charAt(i);
                if (digit < '0' || digit > '9') {
                    valid = false;
                } else {
                    sum += (digit - '0') * (i + 1);
                }
            }
            if (!valid) {
                System.out.println("Invalid ISBN prefix: enter exactly nine digits.");
            } else {
                int checksum = sum % 11;
                String lastDigit = checksum == 10 ? "X" : "" + checksum;
                // Reading a String preserves leading zeros without extra formatting.
                System.out.println("The ISBN-10 number is " + isbn + lastDigit);
            }
        }

        // Exercise 4.20: Display string length and its last character
        {
            System.out.println("\nExercise 4.20");
            System.out.print("Enter a string (spaces are allowed): ");
            String text = input.nextLine();
            // String.length() counts UTF-16 code units, including spaces.
            System.out.println("Length (Java String.length()): " + text.length());
            if (text.isEmpty()) {
                System.out.println("The empty string has no last character.");
            } else {
                // This also preserves a final supplementary Unicode character, such as an
                // emoji.
                int lastIndex = text.offsetByCodePoints(text.length(), -1);
                String lastCharacter = text.substring(lastIndex);
                System.out.println("Last character: " + lastCharacter);
            }
        }

        // Exercise 4.21: Check the format of a sample SSN
        {
            System.out.println("\nExercise 4.21");
            System.out.print("Enter a sample SSN in DDD-DD-DDDD format (example: 123-45-6789): ");
            String ssn = input.nextLine().trim();
            boolean valid = ssn.length() == 11;
            for (int i = 0; valid && i < ssn.length(); i++) {
                char character = ssn.charAt(i);
                if (i == 3 || i == 6) {
                    valid = character == '-';
                } else {
                    valid = character >= '0' && character <= '9';
                }
            }
            // This exercise checks the pattern only, not whether a number was issued.
            System.out.println(ssn + (valid ? " has a valid SSN format." : " has an invalid SSN format."));
        }

        // Exercise 4.22: Check whether one string is a substring of another
        {
            System.out.println("\nExercise 4.22");
            System.out.print("Enter string s1 (spaces are allowed): ");
            String first = input.nextLine();
            System.out.print("Enter string s2: ");
            String second = input.nextLine();
            // contains is case-sensitive. The empty string is a substring of every string.
            if (first.contains(second)) {
                System.out.println("\"" + second + "\" is a substring of \"" + first + "\".");
            } else {
                System.out.println("\"" + second + "\" is not a substring of \"" + first + "\".");
            }
        }

        // Exercise 4.23: Print a payroll statement
        {
            System.out.println("\nExercise 4.23");
            System.out.print("Enter the employee's name (spaces are allowed): ");
            String name = input.nextLine().trim();
            // Optional extension beyond Chapter 4: BigDecimal keeps decimal money exact.
            // Read the original text directly, rather than constructing it from a double.
            System.out.print("Enter hours worked in the week (example: 10): ");
            BigDecimal hours = new BigDecimal(input.nextLine().trim());
            System.out.print("Enter hourly pay rate (example: 9.75): ");
            BigDecimal hourlyRate = new BigDecimal(input.nextLine().trim());
            System.out.print("Enter federal withholding as a fraction (0.20 means 20%): ");
            BigDecimal federalRate = new BigDecimal(input.nextLine().trim());
            System.out.print("Enter state withholding as a fraction (0.09 means 9%): ");
            BigDecimal stateRate = new BigDecimal(input.nextLine().trim());
            if (name.isEmpty() || hours.signum() < 0 || hourlyRate.signum() < 0
                    || federalRate.signum() < 0 || stateRate.signum() < 0
                    || federalRate.add(stateRate).compareTo(BigDecimal.ONE) > 0) {
                System.out.println(
                        "Invalid payroll input: use a name, nonnegative hours/pay, and rates totaling at most 1.");
            } else {
                // The exercise uses hours * rate, without an overtime calculation.
                BigDecimal grossPay = hours.multiply(hourlyRate).setScale(2, RoundingMode.HALF_UP);
                BigDecimal federalTax = grossPay.multiply(federalRate).setScale(2, RoundingMode.HALF_UP);
                BigDecimal stateTax = grossPay.multiply(stateRate).setScale(2, RoundingMode.HALF_UP);
                BigDecimal deductions = federalTax.add(stateTax);
                BigDecimal netPay = grossPay.subtract(deductions);
                System.out.println("\nEmployee Name: " + name);
                System.out.println("Hours Worked: " + hours.toPlainString());
                System.out.println("Pay Rate: $" + hourlyRate.setScale(2, RoundingMode.HALF_UP).toPlainString());
                System.out.println("Gross Pay: $" + grossPay.toPlainString());
                System.out.println("Deductions:");
                System.out.println(
                        "  Federal Withholding (" + federalRate.movePointRight(2).stripTrailingZeros().toPlainString()
                                + "%): $" + federalTax.toPlainString());
                System.out.println(
                        "  State Withholding (" + stateRate.movePointRight(2).stripTrailingZeros().toPlainString()
                                + "%): $" + stateTax.toPlainString());
                System.out.println("  Total Deduction: $" + deductions.toPlainString());
                System.out.println("Net Pay: $" + netPay.toPlainString());
                // At the sample inputs, state withholding rounds to $8.78 and total to $28.28.
                // The printed book sample shows $8.77/$28.27; its displayed cents are
                // inconsistent.
            }
        }

        // Exercise 4.24: Display three countries in descending order
        {
            System.out.println("\nExercise 4.24");
            System.out.print("Enter the first country: ");
            String first = input.nextLine().trim();
            System.out.print("Enter the second country: ");
            String second = input.nextLine().trim();
            System.out.print("Enter the third country: ");
            String third = input.nextLine().trim();
            if (first.isEmpty() || second.isEmpty() || third.isEmpty()) {
                System.out.println("Invalid input: country names must not be empty.");
            } else {
                // Descending alphabetical order, ignoring differences in letter case.
                if (first.compareToIgnoreCase(second) < 0) {
                    String temporary = first;
                    first = second;
                    second = temporary;
                }
                if (second.compareToIgnoreCase(third) < 0) {
                    String temporary = second;
                    second = third;
                    third = temporary;
                }
                if (first.compareToIgnoreCase(second) < 0) {
                    String temporary = first;
                    first = second;
                    second = temporary;
                }
                System.out.println("The countries in descending order are: " + first + ", " + second + ", " + third);
            }
        }

        // Exercise 4.25: Generate a vehicle plate number
        {
            System.out.println("\nExercise 4.25");
            char letter1 = (char) ('A' + (int) (Math.random() * 26));
            char letter2 = (char) ('A' + (int) (Math.random() * 26));
            char letter3 = (char) ('A' + (int) (Math.random() * 26));
            int digits = (int) (Math.random() * 10000);
            // %04d keeps all four digits even when the number begins with zeros.
            System.out.printf(Locale.US, "Generated plate number: %c%c%c%04d%n", letter1, letter2, letter3, digits);
        }

        // Exercise 4.26: Compute monetary units by parsing a string
        {
            System.out.println("\nExercise 4.26");
            System.out.print("Enter dollars and exactly two cent digits (example: 11.56): ");
            String amount = input.nextLine().trim();
            int decimalPoint = amount.indexOf('.');
            boolean valid = decimalPoint >= 1 && decimalPoint == amount.lastIndexOf('.')
                    && amount.length() == decimalPoint + 3 && decimalPoint <= 15;
            String dollarsText = "";
            String centsText = "";
            if (valid) {
                // substring's ending index is exclusive.
                dollarsText = amount.substring(0, decimalPoint);
                centsText = amount.substring(decimalPoint + 1);
                for (int i = 0; valid && i < dollarsText.length(); i++) {
                    valid = dollarsText.charAt(i) >= '0' && dollarsText.charAt(i) <= '9';
                }
                valid = valid && centsText.charAt(0) >= '0' && centsText.charAt(0) <= '9'
                        && centsText.charAt(1) >= '0' && centsText.charAt(1) <= '9';
            }
            if (!valid) {
                System.out.println("Invalid amount: use 1-15 dollar digits, a dot, and exactly 2 cent digits.");
            } else {
                // No floating-point conversion: parse the two parts as integers.
                long dollarPart = Long.parseLong(dollarsText);
                int centPart = Integer.parseInt(centsText);
                long remaining = dollarPart * 100 + centPart;
                long dollars = remaining / 100;
                remaining %= 100;
                long quarters = remaining / 25;
                remaining %= 25;
                long dimes = remaining / 10;
                remaining %= 10;
                long nickels = remaining / 5;
                long pennies = remaining % 5;
                System.out.println("Your amount consists of:");
                System.out.println(dollars + (dollars == 1 ? " dollar" : " dollars"));
                System.out.println(quarters + (quarters == 1 ? " quarter" : " quarters"));
                System.out.println(dimes + (dimes == 1 ? " dime" : " dimes"));
                System.out.println(nickels + (nickels == 1 ? " nickel" : " nickels"));
                System.out.println(pennies + (pennies == 1 ? " penny" : " pennies"));
            }
        }

        // Close the shared Scanner only after the last exercise.
        input.close();
    }
}
