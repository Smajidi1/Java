/* Introduction to Java Programming and Data Structures (Comprehensive Version) 
11th Edition 2018*/

// Chapter 8 Exercises - Multidimensional Arrays

package Exercises;

import java.util.Arrays;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/*
 * Save in Exercises beside the other chapter files. Run main, then choose
 * 1-37 (or 8.1-8.37). Choose 0 to run the chapter in order.
 * Scalar prompts take one value per line. Matrix values may share a line
 * or span several lines; they are filled row by row, left to right.
 * Dimensions are entered together, such as 3 4. Indexes start at 0.
 * Enter plain numbers with a dot for decimals. Invalid numeric text is retried.
 *
 * A matrix is an array of rows: m[row][column]. Most methods here require
 * a nonempty rectangle with finite values. Points use one row per point.
 * Game input -1 leaves tic-tac-toe or connect four. Type :q to leave the
 * capitals quiz. In run-all mode, leaving a game moves to the next exercise.
 *
 * Small choices made explicit:
 * - 8.3 ranks grades decreasingly; 8.4 ranks hours increasingly. Ties keep order.
 * - 8.8 treats distances within 1e-10 relative error as ties.
 * - 8.12 uses the historical 2009 table: joint bracket 208850, top rate 35%.
 *   The exercise has typos in that bracket and in its written example.
 * - 8.14 reports zero-based indexes, unlike the sample's one-based row labels.
 * - 8.15/8.32 use a small cross-product tolerance for decimal coordinates.
 * - 8.16 puts larger row values first; ties use the next value, as in its example.
 * - 8.17 works on a copy of the loans and reports banks in discovery order.
 * - 8.23 assumes one flip from an even-parity board. Parity cannot prove that
 *   only one cell changed. Multiple odd rows/columns are rejected.
 * - 8.25 follows the word positive: zero entries do not qualify.
 * - 8.26/8.27 deep-copy the rows; the original matrix stays intact.
 * - 8.28 compares positions. 8.29 compares value counts, including duplicates.
 *   Their identical equals headers live in separate scopes (Exercise829).
 * - 8.30/8.31 return null for no unique solution or intersection. This also
 *   covers coincident lines, not just distinct parallel lines.
 * - 8.33 expects a strictly convex polygon with vertices in boundary order.
 * - 8.34 chooses the lowest y, then the greatest x when y values tie.
 * - 8.35 returns {-1,-1,0} if no 1 exists; tied blocks use row-first order.
 * - 8.37 ignores answer case and surrounding spaces; multiword capitals work.
 *
 * Methods sit outside main so the same work can be called and tested again.
 * Short comments explain the steps that are easiest to mix up.
 */
public class HelloJava_Ch8 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            input.useLocale(Locale.US);
            System.out.println("Chapter 8: Multidimensional Arrays");
            int selected = readExercise(input);
            for (int exercise = 1; exercise <= 37; exercise++) {
                if (selected == 0 || selected == exercise) {
                    try {
                        runExercise(exercise, input);
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Input cannot be used: " + ex.getMessage());
                    }
                }
            }
        } catch (NoSuchElementException ex) {
            System.out.println("\nInput ended. Run again when ready.");
        }
    }

    private static void runExercise(int exercise, Scanner input) {
        switch (exercise) {
            // Exercise 8.1: Sum each row
            case 1: {
                System.out.println("\nExercise 8.1: Sum each row");
                double[][] matrix = readDoubleMatrix(input, 3, 4, "Enter a 3-by-4 matrix: ");
                for (int row = 0; row < matrix.length; row++) {
                    System.out.println("Sum of the elements at row " + row + " is " + sumRow(matrix, row));
                }
                break;
            }

            // Exercise 8.2: Average the major diagonal
            case 2: {
                System.out.println("\nExercise 8.2: Average the major diagonal");
                double[][] matrix = readDoubleMatrix(input, 4, 4, "Enter a 4-by-4 matrix: ");
                System.out.println("Average of the major diagonal is " + averageMajorDiagonal(matrix));
                break;
            }

            // Exercise 8.3: Rank students by correct answers
            case 3: {
                System.out.println("\nExercise 8.3: Rank students by correct answers");
                char[][] answers = {
                        { 'A', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D' },
                        { 'D', 'B', 'A', 'B', 'C', 'A', 'E', 'E', 'A', 'D' },
                        { 'E', 'D', 'D', 'A', 'C', 'B', 'E', 'E', 'A', 'D' },
                        { 'C', 'B', 'A', 'E', 'D', 'C', 'E', 'E', 'A', 'D' },
                        { 'A', 'B', 'D', 'C', 'C', 'D', 'E', 'E', 'A', 'D' },
                        { 'B', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D' },
                        { 'B', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D' },
                        { 'E', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D' }
                };
                char[] key = { 'D', 'B', 'D', 'C', 'C', 'D', 'A', 'E', 'A', 'D' };
                for (int[] student : rankStudents(answers, key)) {
                    System.out.println("Student " + student[0] + " correct count: " + student[1]);
                }
                break;
            }

            // Exercise 8.4: Rank employees by weekly hours
            case 4: {
                System.out.println("\nExercise 8.4: Rank employees by weekly hours");
                int[][] hours = { { 2, 4, 3, 4, 5, 8, 8 }, { 7, 3, 4, 3, 3, 4, 4 }, { 3, 3, 4, 3, 3, 2, 2 },
                        { 9, 3, 4, 7, 3, 4, 1 }, { 3, 5, 4, 3, 6, 3, 8 }, { 3, 4, 4, 6, 3, 4, 4 },
                        { 3, 7, 4, 8, 3, 8, 4 }, { 6, 3, 5, 9, 2, 7, 9 } };
                for (int[] employee : rankEmployees(hours)) {
                    System.out.println("Employee " + employee[0] + " total hours: " + employee[1]);
                }
                break;
            }

            // Exercise 8.5: Add two matrices
            case 5: {
                System.out.println("\nExercise 8.5: Add two matrices");
                double[][] a = readDoubleMatrix(input, 2, 2, "Enter matrix1 (2-by-2): ");
                double[][] b = readDoubleMatrix(input, 2, 2, "Enter matrix2 (2-by-2): ");
                System.out.println("The sum is:");
                printMatrix(addMatrix(a, b));
                break;
            }

            // Exercise 8.6: Multiply two matrices
            case 6: {
                System.out.println("\nExercise 8.6: Multiply two matrices");
                double[][] a = readDoubleMatrix(input, 3, 3, "Enter matrix1 (3-by-3): ");
                double[][] b = readDoubleMatrix(input, 3, 3, "Enter matrix2 (3-by-3): ");
                System.out.println("The product is:");
                printMatrix(multiplyMatrix(a, b));
                break;
            }

            // Exercise 8.7: Nearest pair of points in 3D
            case 7: {
                System.out.println("\nExercise 8.7: Nearest pair of points in 3D");
                double[][] points = { { -1, 0, 3 }, { -1, -1, -1 }, { 4, 1, 1 }, { 2, 0.5, 9 },
                        { 3.5, 2, -1 }, { 3, 1.5, 3 }, { -1.5, 4, 2 }, { 5.5, 4, -0.5 } };
                int[] pair = nearestPair(points);
                System.out.println("Closest points: " + Arrays.toString(points[pair[0]])
                        + " and " + Arrays.toString(points[pair[1]]));
                System.out.println("Their distance is " + distance(points[pair[0]], points[pair[1]]));
                break;
            }

            // Exercise 8.8: All closest pairs of points
            case 8: {
                System.out.println("\nExercise 8.8: All closest pairs of points");
                int n = readIntInRange(input, "Enter the number of points (2-200): ", 2, 200);
                double[][] points = readDoubleMatrix(input, n, 2, "Enter the x and y coordinates: ");
                int[][] pairs = closestPairs(points);
                for (int[] pair : pairs) {
                    System.out.println("Closest points: " + Arrays.toString(points[pair[0]])
                            + " and " + Arrays.toString(points[pair[1]]));
                }
                System.out.println("Their distance is " + distance(points[pairs[0][0]], points[pairs[0][1]]));
                break;
            }

            // Exercise 8.9: Tic-tac-toe
            case 9: {
                System.out.println("\nExercise 8.9: Tic-tac-toe");
                playTicTacToe(input);
                break;
            }

            // Exercise 8.10: First row and column with the most ones
            case 10: {
                System.out.println("\nExercise 8.10: First row and column with the most ones");
                int[][] matrix = randomBinaryMatrix(5, 5, new Random());
                printMatrix(matrix);
                int[] indexes = largestRowAndColumn(matrix);
                System.out.println("The largest row index: " + indexes[0]);
                System.out.println("The largest column index: " + indexes[1]);
                break;
            }

            // Exercise 8.11: Nine heads and tails
            case 11: {
                System.out.println("\nExercise 8.11: Nine heads and tails");
                int number = readIntInRange(input, "Enter a number between 0 and 511: ", 0, 511);
                int[][] coins = coinMatrix(number);
                for (int[] row : coins) {
                    for (int col = 0; col < row.length; col++) {
                        if (col > 0)
                            System.out.print(" ");
                        System.out.print(row[col] == 0 ? 'H' : 'T');
                    }
                    System.out.println();
                }
                break;
            }

            // Exercise 8.12: Tax calculation using arrays
            case 12: {
                System.out.println("\nExercise 8.12: Tax calculation using arrays");
                System.out.println("Using the book's historical 2009 brackets.");
                int status = readIntInRange(input, "Status (0 single, 1 joint, 2 separate, 3 head): ", 0, 3);
                double income = readNonnegative(input, "Enter taxable income: ");
                System.out.printf(Locale.US, "Tax is %.2f%n", computeTax(status, income));
                break;
            }

            // Exercise 8.13: Locate the smallest element
            case 13: {
                System.out.println("\nExercise 8.13: Locate the smallest element");
                int[] size = readDimensions(input);
                double[][] matrix = readDoubleMatrix(input, size[0], size[1], "Enter the matrix: ");
                int[] location = locateSmallest(matrix);
                System.out.println("The smallest element is at (" + location[0] + ", " + location[1] + ")");
                break;
            }

            // Exercise 8.14: Find uniform rows columns and diagonals
            case 14: {
                System.out.println("\nExercise 8.14: Find uniform rows columns and diagonals");
                int n = readIntInRange(input, "Enter the square matrix size (1-30): ", 1, 30);
                int[][] matrix = randomBinaryMatrix(n, n, new Random());
                printMatrix(matrix);
                reportUniformLines(matrix);
                break;
            }

            // Exercise 8.15: Check whether points are on one line
            case 15: {
                System.out.println("\nExercise 8.15: Check whether points are on one line");
                double[][] points = readDoubleMatrix(input, 5, 2, "Enter five x,y points: ");
                System.out.println(
                        sameLine(points) ? "The points are on the same line" : "The points are not on the same line");
                break;
            }

            // Exercise 8.16: Sort pairs in decreasing order
            case 16: {
                System.out.println("\nExercise 8.16: Sort pairs in decreasing order");
                int[][] pairs = { { 4, 2 }, { 1, 7 }, { 4, 5 }, { 1, 2 }, { 1, 1 }, { 4, 1 } };
                sort(pairs);
                printMatrix(pairs);
                break;
            }

            // Exercise 8.17: Financial tsunami
            case 17: {
                System.out.println("\nExercise 8.17: Financial tsunami");
                readAndCheckBanks(input);
                break;
            }

            // Exercise 8.18: Shuffle whole rows
            case 18: {
                System.out.println("\nExercise 8.18: Shuffle whole rows");
                int[][] matrix = { { 1, 2 }, { 3, 4 }, { 5, 6 }, { 7, 8 }, { 9, 10 } };
                shuffle(matrix);
                printMatrix(matrix);
                break;
            }

            // Exercise 8.19: Four equal neighbors in a matrix
            case 19: {
                System.out.println("\nExercise 8.19: Four equal neighbors in a matrix");
                int[] size = readDimensions(input);
                int[][] matrix = readIntMatrix(input, size[0], size[1], "Enter the matrix: ");
                System.out.println(isConsecutiveFour(matrix));
                break;
            }

            // Exercise 8.20: Connect four
            case 20: {
                System.out.println("\nExercise 8.20: Connect four");
                playConnectFour(input);
                break;
            }

            // Exercise 8.21: Find the central city
            case 21: {
                System.out.println("\nExercise 8.21: Find the central city");
                int n = readIntInRange(input, "Enter the number of cities (1-200): ", 1, 200);
                double[][] cities = readDoubleMatrix(input, n, 2, "Enter city coordinates: ");
                int city = centralCity(cities);
                double total = 0;
                for (double[] other : cities)
                    total += distance(cities[city], other);
                System.out.println("The central city is at " + Arrays.toString(cities[city]));
                System.out.printf(Locale.US, "Total distance to all other cities: %.2f%n", total);
                break;
            }

            // Exercise 8.22: Check even row and column parity
            case 22: {
                System.out.println("\nExercise 8.22: Check even row and column parity");
                int[][] matrix = randomBinaryMatrix(6, 6, new Random());
                printMatrix(matrix);
                System.out.println("Every row and column has an even number of 1s: " + hasEvenParity(matrix));
                break;
            }

            // Exercise 8.23: Locate a flipped cell
            case 23: {
                System.out.println("\nExercise 8.23: Locate a flipped cell");
                int[][] matrix = readIntMatrix(input, 6, 6, "Enter the 6-by-6 matrix after one flip: ");
                int[] cell = findFlippedCell(matrix);
                System.out.println(cell == null ? "No odd row or column was found."
                        : "The flipped cell is at (" + cell[0] + ", " + cell[1] + ")");
                break;
            }

            // Exercise 8.24: Check a Sudoku solution
            case 24: {
                System.out.println("\nExercise 8.24: Check a Sudoku solution");
                int[][] board = readIntMatrix(input, 9, 9, "Enter the completed 9-by-9 Sudoku: ");
                System.out.println(isValidSudoku(board) ? "Valid Sudoku solution" : "Invalid Sudoku solution");
                break;
            }

            // Exercise 8.25: Check a positive Markov matrix
            case 25: {
                System.out.println("\nExercise 8.25: Check a positive Markov matrix");
                double[][] matrix = readDoubleMatrix(input, 3, 3, "Enter a 3-by-3 matrix: ");
                System.out.println(isMarkovMatrix(matrix) ? "It is a Markov matrix" : "It is not a Markov matrix");
                break;
            }

            // Exercise 8.26: Sort each row in a new matrix
            case 26: {
                System.out.println("\nExercise 8.26: Sort each row in a new matrix");
                double[][] matrix = readDoubleMatrix(input, 4, 4, "Enter a 4-by-4 matrix: ");
                System.out.println("The row-sorted array is:");
                printMatrix(sortRows(matrix));
                break;
            }

            // Exercise 8.27: Sort each column in a new matrix
            case 27: {
                System.out.println("\nExercise 8.27: Sort each column in a new matrix");
                double[][] matrix = readDoubleMatrix(input, 4, 4, "Enter a 4-by-4 matrix: ");
                System.out.println("The column-sorted array is:");
                printMatrix(sortColumns(matrix));
                break;
            }

            // Exercise 8.28: Compare corresponding matrix elements
            case 28: {
                System.out.println("\nExercise 8.28: Compare corresponding matrix elements");
                int[][] a = readIntMatrix(input, 3, 3, "Enter list1 (3-by-3): ");
                int[][] b = readIntMatrix(input, 3, 3, "Enter list2 (3-by-3): ");
                System.out.println(
                        equals(a, b) ? "The arrays are strictly identical" : "The arrays are not strictly identical");
                break;
            }

            // Exercise 8.29: Compare matrix contents regardless of position
            case 29: {
                System.out.println("\nExercise 8.29: Compare matrix contents regardless of position");
                int[][] a = readIntMatrix(input, 3, 3, "Enter list1 (3-by-3): ");
                int[][] b = readIntMatrix(input, 3, 3, "Enter list2 (3-by-3): ");
                System.out.println(Exercise829.equals(a, b) ? "The arrays have identical contents"
                        : "The arrays have different contents");
                break;
            }

            // Exercise 8.30: Solve two linear equations
            case 30: {
                System.out.println("\nExercise 8.30: Solve two linear equations");
                double[][] a = readDoubleMatrix(input, 2, 2, "Enter a00 a01 a10 a11: ");
                double[] b = readDoubleValues(input, 2, "Enter b0 and b1: ");
                double[] solution = linearEquation(a, b);
                System.out.println(solution == null ? "The system has no unique solution."
                        : "x is " + solution[0] + " and y is " + solution[1]);
                break;
            }

            // Exercise 8.31: Find the intersection of two lines
            case 31: {
                System.out.println("\nExercise 8.31: Find the intersection of two lines");
                double[][] points = readDoubleMatrix(input, 4, 2, "Enter four x,y points (two per line): ");
                double[] intersection = getIntersectingPoint(points);
                System.out.println(intersection == null ? "The lines have no unique intersection."
                        : "The intersecting point is " + Arrays.toString(intersection));
                break;
            }

            // Exercise 8.32: Triangle area from three points
            case 32: {
                System.out.println("\nExercise 8.32: Triangle area from three points");
                double[][] points = readDoubleMatrix(input, 3, 2, "Enter three x,y points: ");
                double area = getTriangleArea(points);
                System.out.println(area == 0 ? "The three points are on the same line"
                        : "The area of the triangle is " + area);
                break;
            }

            // Exercise 8.33: Four subareas of a convex quadrilateral
            case 33: {
                System.out.println("\nExercise 8.33: Four subareas of a convex quadrilateral");
                double[][] points = readDoubleMatrix(input, 4, 2, "Enter four vertices in boundary order: ");
                double[] areas = polygonSubareas(points);
                System.out.print("The areas are");
                for (double area : areas)
                    System.out.printf(Locale.US, " %.2f", area);
                System.out.println();
                break;
            }

            // Exercise 8.34: Rightmost lowest point
            case 34: {
                System.out.println("\nExercise 8.34: Rightmost lowest point");
                double[][] points = readDoubleMatrix(input, 6, 2, "Enter six x,y points: ");
                System.out.println("The rightmost lowest point is " + Arrays.toString(getRightmostLowestPoint(points)));
                break;
            }

            // Exercise 8.35: Largest square block of ones
            case 35: {
                System.out.println("\nExercise 8.35: Largest square block of ones");
                int n = readIntInRange(input, "Enter the square matrix size (1-100): ", 1, 100);
                int[][] matrix = readIntMatrix(input, n, n, "Enter the binary matrix: ");
                int[] block = findLargestBlock(matrix);
                System.out.println(block[2] == 0 ? "There is no all-1 square."
                        : "The maximum square submatrix is at (" + block[0] + ", " + block[1] + ") with size "
                                + block[2]);
                break;
            }

            // Exercise 8.36: Check a Latin square
            case 36: {
                System.out.println("\nExercise 8.36: Check a Latin square");
                int n = readIntInRange(input, "Enter n (1-26): ", 1, 26);
                char[][] letters = readLatinLetters(input, n);
                System.out.println(isLatinSquare(letters) ? "The input array is a Latin square"
                        : "The input array is not a Latin square");
                break;
            }

            // Exercise 8.37: Guess the fifty state capitals
            case 37: {
                System.out.println("\nExercise 8.37: Guess the fifty state capitals");
                quizCapitals(input);
                break;
            }

            default:
                throw new IllegalArgumentException("Unknown exercise.");
        }
    }

    // ---------------- 8.1-8.8: rows, matrices, and distances ----------------

    public static double sumRow(double[][] m, int rowIndex) {
        int columns = requireMatrix(m);
        if (rowIndex < 0 || rowIndex >= m.length)
            throw new IllegalArgumentException("Row index is outside the matrix.");
        double sum = 0;
        for (int col = 0; col < columns; col++)
            sum += m[rowIndex][col];
        requireFinite(sum);
        return sum;
    }

    public static double averageMajorDiagonal(double[][] m) {
        int columns = requireMatrix(m);
        if (columns != m.length)
            throw new IllegalArgumentException("The matrix must be square.");
        double average = 0;
        // On the main diagonal, row and column are the same index.
        for (int i = 0; i < m.length; i++)
            average += m[i][i] / m.length;
        requireFinite(average);
        return average;
    }

    public static int[][] rankStudents(char[][] answers, char[] key) {
        if (answers == null || key == null)
            throw new IllegalArgumentException("Answers and key are required.");
        int[][] results = new int[answers.length][2];
        for (int student = 0; student < answers.length; student++) {
            if (answers[student] == null || answers[student].length != key.length) {
                throw new IllegalArgumentException("Each student needs one answer per question.");
            }
            results[student][0] = student; // Keep the ID beside the count.
            for (int question = 0; question < key.length; question++) {
                if (answers[student][question] == key[question])
                    results[student][1]++;
            }
        }
        sortRecords(results, true);
        return results;
    }

    public static int[][] rankEmployees(int[][] hours) {
        if (requireMatrix(hours) != 7)
            throw new IllegalArgumentException("Each employee needs seven days.");
        int[][] results = new int[hours.length][2];
        for (int employee = 0; employee < hours.length; employee++) {
            results[employee][0] = employee;
            long total = 0;
            for (int value : hours[employee]) {
                if (value < 0)
                    throw new IllegalArgumentException("Hours cannot be negative.");
                total += value;
            }
            if (total > Integer.MAX_VALUE)
                throw new IllegalArgumentException("Total hours exceed int range.");
            results[employee][1] = (int) total;
        }
        sortRecords(results, false);
        return results;
    }

    private static void sortRecords(int[][] records, boolean descending) {
        for (int end = records.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                boolean wrongOrder = descending ? records[i][1] < records[i + 1][1]
                        : records[i][1] > records[i + 1][1];
                if (wrongOrder) {
                    // Swap the whole row so the ID follows its total.
                    int[] temp = records[i];
                    records[i] = records[i + 1];
                    records[i + 1] = temp;
                }
            }
        }
    }

    public static double[][] addMatrix(double[][] a, double[][] b) {
        int columns = requireMatrix(a);
        if (requireMatrix(b) != columns || a.length != b.length) {
            throw new IllegalArgumentException("Addition needs matching dimensions.");
        }
        double[][] result = new double[a.length][columns];
        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < columns; col++) {
                result[row][col] = a[row][col] + b[row][col];
                requireFinite(result[row][col]);
            }
        }
        return result;
    }

    public static double[][] multiplyMatrix(double[][] a, double[][] b) {
        int shared = requireMatrix(a);
        int columns = requireMatrix(b);
        if (shared != b.length)
            throw new IllegalArgumentException("Columns of a must equal rows of b.");
        double[][] result = new double[a.length][columns];
        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < columns; col++) {
                // k walks across a's row and down b's column.
                for (int k = 0; k < shared; k++)
                    result[row][col] += a[row][k] * b[k][col];
                requireFinite(result[row][col]);
            }
        }
        return result;
    }

    public static double distance(double[] first, double[] second) {
        if (first == null || second == null || first.length != second.length) {
            throw new IllegalArgumentException("Points must have matching dimensions.");
        }
        double result = 0;
        for (int i = 0; i < first.length; i++) {
            requireFinite(first[i]);
            requireFinite(second[i]);
            result = Math.hypot(result, first[i] - second[i]);
        }
        requireFinite(result);
        return result;
    }

    public static int[] nearestPair(double[][] points) {
        requireMatrix(points);
        if (points.length < 2)
            throw new IllegalArgumentException("At least two points are needed.");
        int[] pair = { 0, 1 };
        double best = distance(points[0], points[1]);
        for (int i = 0; i < points.length; i++) {
            // j starts after i: no self-pairs or reversed duplicates.
            for (int j = i + 1; j < points.length; j++) {
                double current = distance(points[i], points[j]);
                if (current < best) {
                    best = current;
                    pair[0] = i;
                    pair[1] = j;
                }
            }
        }
        return pair;
    }

    public static int[][] closestPairs(double[][] points) {
        int[] firstPair = nearestPair(points);
        double best = distance(points[firstPair[0]], points[firstPair[1]]);
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (sameDistance(distance(points[i], points[j]), best))
                    count++;
            }
        }
        int[][] pairs = new int[count][2];
        int next = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (sameDistance(distance(points[i], points[j]), best)) {
                    pairs[next][0] = i;
                    pairs[next++][1] = j;
                }
            }
        }
        return pairs;
    }

    private static boolean sameDistance(double a, double b) {
        return a == b || Math.abs(a - b) <= 1e-10 * Math.max(Math.abs(a), Math.abs(b));
    }

    // ---------------- 8.9-8.20: boards and matrix patterns ----------------

    public static void playTicTacToe(Scanner input) {
        int[][] board = new int[3][3]; // 0 empty, 1 X, 2 O.
        int player = 1, moves = 0;
        printBoard(board, " XO");
        while (true) {
            char mark = player == 1 ? 'X' : 'O';
            int row = readIntInRange(input, "Row for " + mark + " (0-2, -1 to leave): ", -1, 2);
            if (row == -1)
                return;
            int col = readIntInRange(input, "Column (0-2): ", 0, 2);
            if (board[row][col] != 0) {
                System.out.println("That cell is occupied.");
                continue;
            }
            board[row][col] = player;
            moves++;
            printBoard(board, " XO");
            if (hasWin(board, player, 3)) {
                System.out.println(mark + " player won");
                return;
            }
            if (moves == 9) {
                System.out.println("Draw");
                return;
            }
            player = 3 - player; // Switch only after a valid move.
        }
    }

    public static int[][] randomBinaryMatrix(int rows, int columns, Random random) {
        if (rows < 1 || columns < 1 || random == null)
            throw new IllegalArgumentException("Positive dimensions and a generator are required.");
        int[][] matrix = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++)
                matrix[row][col] = random.nextInt(2);
        }
        return matrix;
    }

    public static int[] largestRowAndColumn(int[][] matrix) {
        int columns = requireBinary(matrix);
        int bestRow = 0, bestColumn = 0, mostRow = -1, mostColumn = -1;
        for (int row = 0; row < matrix.length; row++) {
            int ones = 0;
            for (int value : matrix[row])
                ones += value;
            if (ones > mostRow) {
                mostRow = ones;
                bestRow = row;
            }
        }
        for (int col = 0; col < columns; col++) {
            int ones = 0;
            for (int[] row : matrix)
                ones += row[col];
            if (ones > mostColumn) {
                mostColumn = ones;
                bestColumn = col;
            }
        }
        // Strict > keeps the first index when totals tie.
        return new int[] { bestRow, bestColumn };
    }

    public static int[][] coinMatrix(int number) {
        if (number < 0 || number > 511)
            throw new IllegalArgumentException("Number must be 0-511.");
        int[][] coins = new int[3][3];
        for (int position = 0; position < 9; position++) {
            // Read the highest bit first, then fill left to right.
            coins[position / 3][position % 3] = (number >> (8 - position)) & 1;
        }
        return coins;
    }

    public static double computeTax(int status, double income) {
        if (status < 0 || status > 3)
            throw new IllegalArgumentException("Status must be 0-3.");
        requireNonnegative(income);
        double[] rates = { 0.10, 0.15, 0.25, 0.28, 0.33, 0.35 };
        int[][] brackets = { { 8350, 33950, 82250, 171550, 372950 },
                { 16700, 67900, 137050, 208850, 372950 },
                { 8350, 33950, 68525, 104425, 186475 },
                { 11950, 45500, 117450, 190200, 372950 } };
        double tax = 0, lower = 0;
        for (int band = 0; band < rates.length; band++) {
            double upper = band < 5 ? brackets[status][band] : income;
            double portion = Math.min(income, upper) - lower;
            if (portion > 0)
                tax += portion * rates[band];
            if (income <= upper)
                break;
            lower = upper;
        }
        return tax;
    }

    public static int[] locateSmallest(double[][] a) {
        int columns = requireMatrix(a);
        int[] location = { 0, 0 };
        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < columns; col++) {
                if (a[row][col] < a[location[0]][location[1]]) {
                    location[0] = row;
                    location[1] = col;
                }
            }
        }
        return location;
    }

    public static void reportUniformLines(int[][] matrix) {
        int n = requireBinary(matrix);
        if (matrix.length != n)
            throw new IllegalArgumentException("A square matrix is required.");
        boolean foundRow = false, foundCol = false;
        for (int row = 0; row < n; row++) {
            if (hasRun(matrix, row, 0, 0, 1, n)) {
                System.out.println("All " + matrix[row][0] + "s on row " + row);
                foundRow = true;
            }
        }
        for (int col = 0; col < n; col++) {
            if (hasRun(matrix, 0, col, 1, 0, n)) {
                System.out.println("All " + matrix[0][col] + "s on column " + col);
                foundCol = true;
            }
        }
        if (!foundRow)
            System.out.println("No uniform row");
        if (!foundCol)
            System.out.println("No uniform column");
        System.out.println(hasRun(matrix, 0, 0, 1, 1, n)
                ? "All " + matrix[0][0] + "s on the major diagonal"
                : "No uniform major diagonal");
        System.out.println(hasRun(matrix, 0, n - 1, 1, -1, n)
                ? "All " + matrix[0][n - 1] + "s on the other diagonal"
                : "No uniform other diagonal");
    }

    public static boolean sameLine(double[][] points) {
        requirePoints(points, 2, 1);
        int second = 1;
        // Skip repeated copies of the first point before defining a line.
        while (second < points.length && samePoint(points[0], points[second]))
            second++;
        if (second == points.length)
            return true;
        for (double[] point : points) {
            if (!collinear(points[0], points[second], point))
                return false;
        }
        return true;
    }

    public static void sort(int[][] m) {
        requireMatrix(m);
        for (int end = m.length - 1; end > 0; end--) {
            for (int row = 0; row < end; row++) {
                if (compareRows(m[row], m[row + 1]) < 0) {
                    int[] temp = m[row];
                    m[row] = m[row + 1];
                    m[row + 1] = temp;
                }
            }
        }
    }

    private static int compareRows(int[] a, int[] b) {
        for (int col = 0; col < a.length; col++) {
            if (a[col] != b[col])
                return Integer.compare(a[col], b[col]);
        }
        return 0;
    }

    public static int[] findUnsafeBanks(double[] balances, double[][] loans, double limit) {
        int n = requireMatrix(loans);
        if (balances == null || balances.length != n || loans.length != n) {
            throw new IllegalArgumentException("One balance and loan row per bank are required.");
        }
        requireNonnegative(limit);
        for (double balance : balances)
            requireNonnegative(balance);
        for (double[] row : loans)
            for (double loan : row)
                requireNonnegative(loan);
        double[][] remaining = copyMatrix(loans);
        boolean[] unsafe = new boolean[n];
        int[] order = new int[n];
        int count = 0;
        boolean changed;
        do {
            changed = false;
            for (int bank = 0; bank < n; bank++) {
                if (unsafe[bank])
                    continue;
                double assets = balances[bank];
                for (double loan : remaining[bank])
                    assets += loan;
                requireFinite(assets);
                if (assets < limit) {
                    unsafe[bank] = true;
                    order[count++] = bank;
                    changed = true;
                    // No lender can count money owed by this failed bank.
                    for (int lender = 0; lender < n; lender++)
                        remaining[lender][bank] = 0;
                }
            }
            // A failed borrower may make an earlier lender fail on the next pass.
        } while (changed);
        return Arrays.copyOf(order, count);
    }

    public static void shuffle(int[][] m) {
        requireMatrix(m);
        for (int row = m.length - 1; row > 0; row--) {
            int chosen = (int) (Math.random() * (row + 1));
            int[] temp = m[row];
            m[row] = m[chosen];
            m[chosen] = temp;
        }
    }

    public static boolean isConsecutiveFour(int[][] values) {
        int columns = requireMatrix(values);
        for (int row = 0; row < values.length; row++) {
            for (int col = 0; col < columns; col++) {
                if (hasRun(values, row, col, 0, 1, 4) || hasRun(values, row, col, 1, 0, 4)
                        || hasRun(values, row, col, 1, 1, 4) || hasRun(values, row, col, 1, -1, 4))
                    return true;
            }
        }
        return false;
    }

    private static boolean hasRun(int[][] m, int row, int col, int dr, int dc, int length) {
        int endRow = row + dr * (length - 1), endCol = col + dc * (length - 1);
        // Check the far end before indexing any cells along the path.
        if (endRow < 0 || endRow >= m.length || endCol < 0 || endCol >= m[0].length)
            return false;
        for (int step = 1; step < length; step++) {
            if (m[row + step * dr][col + step * dc] != m[row][col])
                return false;
        }
        return true;
    }

    public static boolean hasWin(int[][] board, int player, int length) {
        int columns = requireMatrix(board);
        if (player < 1 || length < 1)
            throw new IllegalArgumentException("Player and run length must be positive.");
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < columns; col++) {
                if (board[row][col] != player)
                    continue; // Empty cells never win.
                if (hasRun(board, row, col, 0, 1, length) || hasRun(board, row, col, 1, 0, length)
                        || hasRun(board, row, col, 1, 1, length) || hasRun(board, row, col, 1, -1, length))
                    return true;
            }
        }
        return false;
    }

    public static int dropDisk(int[][] board, int column, int player) {
        int columns = requireMatrix(board);
        if (column < 0 || column >= columns || (player != 1 && player != 2))
            throw new IllegalArgumentException("Invalid column or player.");
        // Start at the bottom; gravity uses the lowest empty cell.
        for (int row = board.length - 1; row >= 0; row--) {
            if (board[row][column] == 0) {
                board[row][column] = player;
                return row;
            }
        }
        return -1; // The column is full.
    }

    public static void playConnectFour(Scanner input) {
        int[][] board = new int[6][7];
        int player = 1, moves = 0;
        printBoard(board, " RY");
        while (true) {
            String color = player == 1 ? "red" : "yellow";
            int column = readIntInRange(input, "Drop a " + color + " disk (0-6, -1 to leave): ", -1, 6);
            if (column == -1)
                return;
            if (dropDisk(board, column, player) == -1) {
                System.out.println("That column is full.");
                continue;
            }
            moves++;
            printBoard(board, " RY");
            if (hasWin(board, player, 4)) {
                System.out.println("The " + color + " player won");
                return;
            }
            if (moves == 42) {
                System.out.println("Draw");
                return;
            }
            player = 3 - player;
        }
    }

    private static void printBoard(int[][] board, String symbols) {
        for (int[] row : board) {
            for (int value : row)
                System.out.print("|" + symbols.charAt(value));
            System.out.println("|");
        }
    }

    // ---------------- 8.21-8.29: checks, copies, and sorting ----------------

    public static int centralCity(double[][] cities) {
        requirePoints(cities, 2, 1);
        int best = 0;
        double shortest = Double.POSITIVE_INFINITY;
        for (int city = 0; city < cities.length; city++) {
            double total = 0;
            for (double[] other : cities)
                total += distance(cities[city], other);
            requireFinite(total);
            if (total < shortest) {
                shortest = total;
                best = city;
            }
        }
        return best;
    }

    private static int[][] parityCounts(int[][] m) {
        int columns = requireBinary(m);
        int[] rows = new int[m.length], cols = new int[columns];
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < columns; col++) {
                rows[row] += m[row][col];
                cols[col] += m[row][col];
            }
        }
        return new int[][] { rows, cols };
    }

    public static boolean hasEvenParity(int[][] m) {
        for (int[] counts : parityCounts(m)) {
            for (int count : counts)
                if (count % 2 != 0)
                    return false;
        }
        return true;
    }

    public static int[] findFlippedCell(int[][] m) {
        int[][] counts = parityCounts(m);
        int row = -1, col = -1, oddRows = 0, oddCols = 0;
        for (int i = 0; i < counts[0].length; i++) {
            if (counts[0][i] % 2 != 0) {
                row = i;
                oddRows++;
            }
        }
        for (int i = 0; i < counts[1].length; i++) {
            if (counts[1][i] % 2 != 0) {
                col = i;
                oddCols++;
            }
        }
        if (oddRows == 0 && oddCols == 0)
            return null;
        if (oddRows != 1 || oddCols != 1)
            throw new IllegalArgumentException("Parity does not match a single-cell flip.");
        return new int[] { row, col };
    }

    public static boolean isValidSudoku(int[][] board) {
        if (requireMatrix(board) != 9 || board.length != 9)
            return false;
        for (int index = 0; index < 9; index++) {
            boolean[] rowSeen = new boolean[10], colSeen = new boolean[10];
            for (int i = 0; i < 9; i++) {
                int rowValue = board[index][i], colValue = board[i][index];
                if (rowValue < 1 || rowValue > 9 || colValue < 1 || colValue > 9
                        || rowSeen[rowValue] || colSeen[colValue])
                    return false;
                rowSeen[rowValue] = true;
                colSeen[colValue] = true;
            }
        }
        for (int boxRow = 0; boxRow < 9; boxRow += 3) {
            for (int boxCol = 0; boxCol < 9; boxCol += 3) {
                boolean[] seen = new boolean[10];
                for (int dr = 0; dr < 3; dr++) {
                    for (int dc = 0; dc < 3; dc++) {
                        int value = board[boxRow + dr][boxCol + dc];
                        if (seen[value])
                            return false;
                        seen[value] = true;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isMarkovMatrix(double[][] m) {
        int columns = requireMatrix(m);
        if (m.length != columns)
            return false;
        for (int col = 0; col < columns; col++) {
            double sum = 0;
            for (int row = 0; row < m.length; row++) {
                if (m[row][col] <= 0)
                    return false;
                sum += m[row][col];
            }
            if (Math.abs(sum - 1) > 1e-9)
                return false;
        }
        return true;
    }

    public static double[][] sortRows(double[][] m) {
        requireMatrix(m);
        double[][] result = copyMatrix(m);
        for (double[] row : result)
            Arrays.sort(row);
        return result;
    }

    public static double[][] sortColumns(double[][] m) {
        int columns = requireMatrix(m);
        double[][] result = copyMatrix(m);
        for (int col = 0; col < columns; col++) {
            double[] values = new double[m.length];
            for (int row = 0; row < m.length; row++)
                values[row] = m[row][col];
            Arrays.sort(values);
            for (int row = 0; row < m.length; row++)
                result[row][col] = values[row];
        }
        return result;
    }

    private static double[][] copyMatrix(double[][] m) {
        double[][] copy = new double[m.length][];
        // Cloning only m would still share its row arrays.
        for (int row = 0; row < m.length; row++)
            copy[row] = m[row].clone();
        return copy;
    }

    public static boolean equals(int[][] m1, int[][] m2) {
        int columns1 = requireMatrix(m1), columns2 = requireMatrix(m2);
        if (m1.length != m2.length || columns1 != columns2)
            return false;
        for (int row = 0; row < m1.length; row++) {
            for (int col = 0; col < columns1; col++)
                if (m1[row][col] != m2[row][col])
                    return false;
        }
        return true;
    }

    public static class Exercise829 {
        public static boolean equals(int[][] m1, int[][] m2) {
            requireMatrix(m1);
            requireMatrix(m2);
            int[] first = flatten(m1), second = flatten(m2);
            // Sorting copies preserves the originals and keeps duplicate counts.
            Arrays.sort(first);
            Arrays.sort(second);
            return Arrays.equals(first, second);
        }
    }

    private static int[] flatten(int[][] m) {
        long size = (long) m.length * m[0].length;
        if (size > Integer.MAX_VALUE)
            throw new IllegalArgumentException("Matrix is too large to flatten.");
        int[] values = new int[(int) size];
        int next = 0;
        for (int[] row : m)
            for (int value : row)
                values[next++] = value;
        return values;
    }

    // ---------------- 8.30-8.35: geometry and square blocks ----------------

    public static double[] linearEquation(double[][] a, double[] b) {
        if (requireMatrix(a) != 2 || a.length != 2 || b == null || b.length != 2) {
            throw new IllegalArgumentException("Need a 2-by-2 matrix and two constants.");
        }
        requireFinite(b[0]);
        requireFinite(b[1]);
        double determinant = a[0][0] * a[1][1] - a[0][1] * a[1][0];
        requireFinite(determinant);
        if (determinant == 0)
            return null;
        double x = (b[0] * a[1][1] - b[1] * a[0][1]) / determinant;
        double y = (b[1] * a[0][0] - b[0] * a[1][0]) / determinant;
        requireFinite(x);
        requireFinite(y);
        return new double[] { x, y };
    }

    public static double[] getIntersectingPoint(double[][] points) {
        requirePoints(points, 2, 4);
        if (points.length != 4)
            throw new IllegalArgumentException("Exactly four points are required.");
        if (samePoint(points[0], points[1]) || samePoint(points[2], points[3])) {
            throw new IllegalArgumentException("Each line needs two distinct points.");
        }
        double[][] a = new double[2][2];
        double[] b = new double[2];
        for (int line = 0; line < 2; line++) {
            double[] first = points[2 * line], second = points[2 * line + 1];
            a[line][0] = first[1] - second[1];
            a[line][1] = second[0] - first[0];
            b[line] = a[line][0] * first[0] + a[line][1] * first[1];
        }
        return linearEquation(a, b);
    }

    private static boolean samePoint(double[] a, double[] b) {
        return a[0] == b[0] && a[1] == b[1];
    }

    private static double cross(double[] a, double[] b, double[] c) {
        double value = (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0]);
        requireFinite(value);
        return value;
    }

    private static boolean collinear(double[] a, double[] b, double[] c) {
        double first = (b[0] - a[0]) * (c[1] - a[1]);
        double second = (b[1] - a[1]) * (c[0] - a[0]);
        requireFinite(first);
        requireFinite(second);
        double value = cross(a, b, c);
        double tolerance = 1e-10 * Math.max(Math.abs(first), Math.abs(second));
        return Math.abs(value) <= tolerance;
    }

    public static double getTriangleArea(double[][] points) {
        requirePoints(points, 2, 3);
        if (points.length != 3)
            throw new IllegalArgumentException("Exactly three points are required.");
        if (collinear(points[0], points[1], points[2]))
            return 0;
        double a = distance(points[0], points[1]);
        double b = distance(points[1], points[2]);
        double c = distance(points[2], points[0]);
        double scale = Math.max(a, Math.max(b, c));
        a /= scale;
        b /= scale;
        c /= scale;
        double s = (a + b + c) / 2; // Half the perimeter, for Heron's formula.
        double area = Math.sqrt(Math.max(0, s * (s - a) * (s - b) * (s - c))) * scale * scale;
        requireFinite(area);
        return area;
    }

    public static double[] polygonSubareas(double[][] points) {
        requirePoints(points, 2, 4);
        if (points.length != 4)
            throw new IllegalArgumentException("Exactly four vertices are required.");
        double direction = cross(points[0], points[1], points[2]);
        if (direction == 0)
            throw new IllegalArgumentException("Use a strictly convex polygon.");
        for (int i = 1; i < 4; i++) {
            double turn = cross(points[i], points[(i + 1) % 4], points[(i + 2) % 4]);
            if (turn == 0 || (turn > 0) != (direction > 0)) {
                throw new IllegalArgumentException("Vertices must follow a convex boundary.");
            }
        }
        double[] center = getIntersectingPoint(new double[][] { points[0], points[2], points[1], points[3] });
        if (center == null)
            throw new IllegalArgumentException("The diagonals have no unique intersection.");
        double[] areas = new double[4];
        for (int i = 0; i < 4; i++) {
            areas[i] = getTriangleArea(new double[][] { points[i], points[(i + 1) % 4], center });
        }
        Arrays.sort(areas);
        return areas;
    }

    public static double[] getRightmostLowestPoint(double[][] points) {
        requirePoints(points, 2, 1);
        int best = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i][1] < points[best][1]
                    || (points[i][1] == points[best][1] && points[i][0] > points[best][0]))
                best = i;
        }
        return points[best].clone();
    }

    public static int[] findLargestBlock(int[][] m) {
        int columns = requireBinary(m);
        int[][] sizes = new int[m.length][columns];
        int[] best = { -1, -1, 0 };
        for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < columns; col++) {
                if (m[row][col] == 0)
                    continue;
                sizes[row][col] = 1;
                if (row > 0 && col > 0) {
                    // A square grows only as far as all three neighbors allow.
                    sizes[row][col] += Math.min(sizes[row - 1][col - 1],
                            Math.min(sizes[row - 1][col], sizes[row][col - 1]));
                }
                if (sizes[row][col] > best[2]) {
                    best[2] = sizes[row][col];
                    best[0] = row - best[2] + 1;
                    best[1] = col - best[2] + 1;
                }
            }
        }
        return best;
    }

    // ---------------- 8.36-8.37: letters and a capitals quiz ----------------

    public static boolean isLatinSquare(char[][] letters) {
        if (letters == null || letters.length < 1 || letters.length > 26)
            return false;
        int n = letters.length;
        for (char[] row : letters)
            if (row == null || row.length != n)
                return false;
        for (int index = 0; index < n; index++) {
            boolean[] rowSeen = new boolean[n], colSeen = new boolean[n];
            for (int i = 0; i < n; i++) {
                int rowLetter = letters[index][i] - 'A', colLetter = letters[i][index] - 'A';
                if (rowLetter < 0 || rowLetter >= n || colLetter < 0 || colLetter >= n
                        || rowSeen[rowLetter] || colSeen[colLetter])
                    return false;
                rowSeen[rowLetter] = true;
                colSeen[colLetter] = true;
            }
        }
        return true;
    }

    public static String[][] stateCapitals() {
        return new String[][] {
                { "Alabama", "Montgomery" }, { "Alaska", "Juneau" }, { "Arizona", "Phoenix" },
                { "Arkansas", "Little Rock" },
                { "California", "Sacramento" }, { "Colorado", "Denver" }, { "Connecticut", "Hartford" },
                { "Delaware", "Dover" },
                { "Florida", "Tallahassee" }, { "Georgia", "Atlanta" }, { "Hawaii", "Honolulu" }, { "Idaho", "Boise" },
                { "Illinois", "Springfield" }, { "Indiana", "Indianapolis" }, { "Iowa", "Des Moines" },
                { "Kansas", "Topeka" },
                { "Kentucky", "Frankfort" }, { "Louisiana", "Baton Rouge" }, { "Maine", "Augusta" },
                { "Maryland", "Annapolis" },
                { "Massachusetts", "Boston" }, { "Michigan", "Lansing" }, { "Minnesota", "Saint Paul" },
                { "Mississippi", "Jackson" },
                { "Missouri", "Jefferson City" }, { "Montana", "Helena" }, { "Nebraska", "Lincoln" },
                { "Nevada", "Carson City" },
                { "New Hampshire", "Concord" }, { "New Jersey", "Trenton" }, { "New Mexico", "Santa Fe" },
                { "New York", "Albany" },
                { "North Carolina", "Raleigh" }, { "North Dakota", "Bismarck" }, { "Ohio", "Columbus" },
                { "Oklahoma", "Oklahoma City" },
                { "Oregon", "Salem" }, { "Pennsylvania", "Harrisburg" }, { "Rhode Island", "Providence" },
                { "South Carolina", "Columbia" },
                { "South Dakota", "Pierre" }, { "Tennessee", "Nashville" }, { "Texas", "Austin" },
                { "Utah", "Salt Lake City" },
                { "Vermont", "Montpelier" }, { "Virginia", "Richmond" }, { "Washington", "Olympia" },
                { "West Virginia", "Charleston" },
                { "Wisconsin", "Madison" }, { "Wyoming", "Cheyenne" }
        };
    }

    public static int quizCapitals(Scanner input) {
        int correct = 0, answered = 0;
        for (String[] state : stateCapitals()) {
            System.out.print("What is the capital of " + state[0] + "? ");
            String answer = input.nextLine().trim();
            if (answer.equals(":q"))
                break;
            answered++;
            if (answer.equalsIgnoreCase(state[1])) {
                correct++;
                System.out.println("Your answer is correct");
            } else
                System.out.println("The correct answer should be " + state[1]);
        }
        System.out.println("The correct count is " + correct + " out of " + answered + " answered.");
        return correct;
    }

    // ---------------- Input helpers ----------------

    private static int readExercise(Scanner input) {
        while (true) {
            System.out.print("Choose 1-37 (or 8.1-8.37), or 0 for all: ");
            String text = input.nextLine().trim();
            if (text.startsWith("8."))
                text = text.substring(2);
            try {
                int n = Integer.parseInt(text);
                if (n >= 0 && n <= 37)
                    return n;
            } catch (NumberFormatException ex) {
                /* Ask again. */ }
            System.out.println("Enter a valid exercise number.");
        }
    }

    private static int[] readDimensions(Scanner input) {
        while (true) {
            int[] size = readIntValues(input, 2, "Enter rows and columns (1-100 each): ");
            if (size[0] >= 1 && size[0] <= 100 && size[1] >= 1 && size[1] <= 100)
                return size;
            System.out.println("Both dimensions must be from 1 through 100.");
        }
    }

    private static int[][] readIntMatrix(Scanner input, int rows, int columns, String prompt) {
        int[] values = readIntValues(input, rows * columns, prompt);
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < values.length; i++)
            matrix[i / columns][i % columns] = values[i];
        return matrix;
    }

    private static double[][] readDoubleMatrix(Scanner input, int rows, int columns, String prompt) {
        double[] values = readDoubleValues(input, rows * columns, prompt);
        double[][] matrix = new double[rows][columns];
        for (int i = 0; i < values.length; i++)
            matrix[i / columns][i % columns] = values[i];
        return matrix;
    }

    private static void readAndCheckBanks(Scanner input) {
        int[] setup = readIntValues(input, 2, "Enter the number of banks and the asset limit: ");
        int n = setup[0];
        if (n < 1 || n > 100 || setup[1] < 0)
            throw new IllegalArgumentException("Use 1-100 banks and a nonnegative limit.");
        double[] balances = new double[n];
        double[][] loans = new double[n][n];
        for (int bank = 0; bank < n; bank++) {
            while (true) {
                System.out.print("Bank " + bank + ": balance, borrower count, then ID/amount pairs: ");
                String[] parts = input.nextLine().trim().split("\\s+");
                try {
                    if (parts.length < 2)
                        throw new IllegalArgumentException("Enter a balance and borrower count.");
                    double balance = Double.parseDouble(parts[0]);
                    requireNonnegative(balance);
                    int count = Integer.parseInt(parts[1]);
                    if (count < 0 || count > n || parts.length != 2 + count * 2) {
                        throw new IllegalArgumentException("The borrower count must match the ID/amount pairs.");
                    }
                    double[] row = new double[n];
                    for (int pair = 0; pair < count; pair++) {
                        int borrower = Integer.parseInt(parts[2 + pair * 2]);
                        double amount = Double.parseDouble(parts[3 + pair * 2]);
                        if (borrower < 0 || borrower >= n)
                            throw new IllegalArgumentException("Borrower ID is outside the bank list.");
                        requireNonnegative(amount);
                        row[borrower] += amount;
                        requireFinite(row[borrower]);
                    }
                    balances[bank] = balance;
                    loans[bank] = row;
                    break;
                } catch (IllegalArgumentException ex) {
                    System.out.println("Re-enter this bank: " + ex.getMessage());
                }
            }
        }
        System.out.print("Unsafe banks are");
        int[] unsafe = findUnsafeBanks(balances, loans, setup[1]);
        for (int bank : unsafe)
            System.out.print(" " + bank);
        System.out.println(unsafe.length == 0 ? " none" : "");
    }

    private static char[][] readLatinLetters(Scanner input, int n) {
        char[][] letters = new char[n][n];
        for (int row = 0; row < n; row++) {
            while (true) {
                System.out.print("Enter " + n + " letters for row " + row + ", separated by spaces: ");
                String[] tokens = input.nextLine().trim().split("\\s+");
                if (tokens.length != n) {
                    System.out.println("Enter exactly " + n + " letters.");
                    continue;
                }
                boolean valid = true;
                for (int col = 0; col < n; col++) {
                    if (tokens[col].length() != 1 || tokens[col].charAt(0) < 'A' || tokens[col].charAt(0) >= 'A' + n) {
                        valid = false;
                        break;
                    }
                    letters[row][col] = tokens[col].charAt(0);
                }
                if (valid)
                    break;
                throw new IllegalArgumentException("Wrong input: letters must be from A to " + (char) ('A' + n - 1));
            }
        }
        return letters;
    }

    private static double readNonnegative(Scanner input, String prompt) {
        while (true) {
            double[] value = readDoubleValues(input, 1, prompt);
            if (value[0] >= 0)
                return value[0];
            System.out.println("Enter a nonnegative number.");
        }
    }

    private static int readInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Enter one whole number within int range.");
            }
        }
    }

    private static int readIntInRange(Scanner input, String prompt, int min, int max) {
        while (true) {
            int value = readInt(input, prompt);
            if (value >= min && value <= max)
                return value;
            System.out.println("Enter a number from " + min + " through " + max + ".");
        }
    }

    private static int[] readIntValues(Scanner input, int size, String prompt) {
        int[] result = new int[size];
        System.out.print(prompt);
        fillIntArray(input, result, 0);
        return result;
    }

    private static void fillIntArray(Scanner input, int[] result, int used) {
        while (used < result.length) {
            String text = input.nextLine().trim();
            if (text.isEmpty()) {
                System.out.print("Enter the remaining values: ");
                continue;
            }
            String[] tokens = text.split("\\s+");
            if (tokens.length > result.length - used) {
                System.out.println("Too many values on this line; enter at most " + (result.length - used) + ".");
                continue;
            }
            int[] parsed = new int[tokens.length];
            boolean valid = true;
            for (int i = 0; i < tokens.length; i++) {
                try {
                    parsed[i] = Integer.parseInt(tokens[i]);
                } catch (NumberFormatException ex) {
                    valid = false;
                    break;
                }
            }
            if (!valid) {
                System.out.println("Invalid integer; re-enter this line of values.");
                continue;
            }
            for (int value : parsed)
                result[used++] = value;
            if (used < result.length)
                System.out.print("Enter " + (result.length - used) + " more values: ");
        }
    }

    private static double[] readDoubleValues(Scanner input, int size, String prompt) {
        double[] result = new double[size];
        int used = 0;
        System.out.print(prompt);
        while (used < size) {
            String text = input.nextLine().trim();
            if (text.isEmpty()) {
                System.out.print("Enter the remaining values: ");
                continue;
            }
            String[] tokens = text.split("\\s+");
            if (tokens.length > size - used) {
                System.out.println("Too many values on this line; enter at most " + (size - used) + ".");
                continue;
            }
            double[] parsed = new double[tokens.length];
            boolean valid = true;
            for (int i = 0; i < tokens.length; i++) {
                try {
                    parsed[i] = Double.parseDouble(tokens[i]);
                    if (!Double.isFinite(parsed[i]))
                        valid = false;
                } catch (NumberFormatException ex) {
                    valid = false;
                }
            }
            if (!valid) {
                System.out.println("Invalid number; re-enter this line using finite doubles.");
                continue;
            }
            for (double value : parsed)
                result[used++] = value;
            if (used < size)
                System.out.print("Enter " + (size - used) + " more values: ");
        }
        return result;
    }

    // ---------------- Shape checks and output ----------------

    private static int requireMatrix(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            throw new IllegalArgumentException("A nonempty matrix is required.");
        }
        int columns = m[0].length;
        for (int[] row : m) {
            if (row == null || row.length != columns)
                throw new IllegalArgumentException("Rows must have equal lengths.");
        }
        return columns;
    }

    private static int requireMatrix(double[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            throw new IllegalArgumentException("A nonempty matrix is required.");
        }
        int columns = m[0].length;
        for (double[] row : m) {
            if (row == null || row.length != columns)
                throw new IllegalArgumentException("Rows must have equal lengths.");
            for (double value : row)
                requireFinite(value);
        }
        return columns;
    }

    private static int requireBinary(int[][] m) {
        int columns = requireMatrix(m);
        for (int[] row : m)
            for (int value : row) {
                if (value != 0 && value != 1)
                    throw new IllegalArgumentException("Use only 0 and 1 in a binary matrix.");
            }
        return columns;
    }

    private static void requirePoints(double[][] points, int dimensions, int minimum) {
        if (requireMatrix(points) != dimensions || points.length < minimum) {
            throw new IllegalArgumentException("Wrong point count or coordinate count.");
        }
    }

    private static void requireFinite(double value) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Values and results must be finite.");
    }

    private static void requireNonnegative(double value) {
        requireFinite(value);
        if (value < 0)
            throw new IllegalArgumentException("Values cannot be negative.");
    }

    public static void printMatrix(int[][] m) {
        for (int[] row : m) {
            for (int col = 0; col < row.length; col++) {
                if (col > 0)
                    System.out.print(" ");
                System.out.print(row[col]);
            }
            System.out.println();
        }
    }

    public static void printMatrix(double[][] m) {
        for (double[] row : m) {
            for (int col = 0; col < row.length; col++) {
                if (col > 0)
                    System.out.print(" ");
                System.out.printf(Locale.US, "%.6g", row[col]);
            }
            System.out.println();
        }
    }
}
