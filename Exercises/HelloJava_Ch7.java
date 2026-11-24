/* Introduction to Java Programming and Data Structures (Comprehensive Version) 
11th Edition 2018*/

// Chapter 7 Exercises - Single-Dimensional Arrays
package Exercises;

import java.util.Arrays;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/*
 * Input conventions:
 * - Scalar prompts take one value per line. Array prompts accept space-separated
 *   values on one or several lines. Use a dot for decimal values.
 * - A size-prefixed list may be entered as: 5 2 4 6 8 10. The first 5 is a size,
 *   not a data value. The size and contents may also be on separate lines.
 * - 7.3 stops at 0; 7.4 stops at any negative score; 7.5 stops at 0.
 *   A sentinel is excluded. Any remaining tokens on its line are discarded.
 * - 7.17 intentionally reads names with Scanner.next(), then consumes the final
 *   numeric line ending. Other text prompts read complete lines.
 * - 7.35 accepts :q to leave the game; in run-all mode, the next exercise starts.
 * - Invalid numeric text is requested again. Method domain errors are reported;
 *   in run-all mode they end the current exercise and processing continues.
 *
 * Notes about the supplied pages and implementation choices:
 * - 7.1 uses this edition's best-minus-5/10/15/20 grade boundaries.
 * - 7.2 compares all eleven values, so the last comparison is equal to itself.
 * - 7.5 follows its 0 sentinel and sample, rather than the inconsistent "ten".
 * - 7.8's int average truncates toward zero because its required return type is
 *   int. The double overload retains fractional results.
 * - 7.9 reports the maximum; the sample's word "minimum" is a labeling error.
 * - 7.10 uses double[], as in its header, and returns the LAST maximum index.
 * - 7.12 reverses the original array and returns that same array reference.
 * - 7.13 selects FROM the supplied arguments, not from numbers excluding them.
 * - 7.14 uses nonnegative LCM magnitudes; any zero makes the LCM zero. An
 *   empty argument list or a result outside int range is rejected.
 * - 7.16 defaults to the full 100000000-element benchmark. A smaller entered
 *   size is an explicitly labeled practice run; search keys scale with it.
 *   Single-call timings illustrate the exercise, not a rigorous JVM benchmark.
 * - 7.17 keeps input order for equal scores (stable sorting).
 * - 7.19 isSorted(int[]) checks nonincreasing order, allowing equal neighbors.
 *   7.26 checks nondecreasing order, also allowing equality. Its separately
 *   scoped Exercise726 class preserves the printed Boolean return header;
 *   Java cannot distinguish methods by return type alone. Boolean wraps a
 *   primitive boolean here and is never null. The PDF's "Bboolean" is a typo.
 * - 7.21 divides by the maximum, as requested, rather than using min-max
 *   scaling. Only nonnegative inputs give the stated 0-1 range. All-zero input
 *   is explicitly mapped to zeros instead of dividing by zero.
 * - 7.24 collects ONE representative from EACH suit (four cards total), as in
 *   its introduction and sample. "Four cards from each suit" is inconsistent.
 * - 7.25 requires a != 0. It uses scaled coefficients and a stable quadratic
 *   calculation, writes real roots into roots[], and returns their count.
 * - 7.27 treats empty/singleton arrays as sorted; constant lists have interval 0.
 * - 7.28 prints distinct value arrangements; repeated input values produce
 *   fewer than 24 arrangements. 7.29 treats the three dice as distinguishable.
 * - 7.31 assumes ascending inputs; the driver validates them outside merge.
 *   The merge performs at most m+n-1 element comparisons when both are nonempty.
 * - 7.32 compares each nonpivot element once. A valid partition can differ
 *   from the sample's internal ordering; a partition is not a complete sort.
 * - 7.34 sorts UTF-16 char values, including case and spaces. It is not a
 *   language-aware collation or a grapheme-aware Unicode sort.
 * - 7.35 reveals every occurrence of a correct letter. Repeated guesses,
 *   including wrong ones, are not counted as new misses. English A-Z only.
 * - 7.36 uses iterative backtracking and int[8], not a two-dimensional board.
 * - 7.37 uses slots-1 independent L/R decisions per ball; its destination
 *   is the number of R decisions. Histogram columns retain empty slots.
 *
 * Unless noted otherwise, methods reject null arrays; numerical calculations
 * require finite doubles and results. Empty arrays are accepted by structural
 * operations, but a mean, maximum, or sample deviation needs enough data.
 * Interactive size limits keep printed output manageable.
 */
public class HelloJava_Ch7 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            input.useLocale(Locale.US);
            System.out.println("Chapter 7: Single-Dimensional Arrays");
            System.out.println("Choose 1-37 (or 7.1-7.37), or 0 to run all.");
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
            System.out.println("\nInput ended. Run again when ready to continue.");
        }
    }

    private static void runExercise(int exercise, Scanner input) {
        switch (exercise) {
            // Exercise 7.1: Assign grades relative to the best score
            case 1: {
                System.out.println("\nExercise 7.1: Assign grades relative to the best score");
                int n = readIntInRange(input, "Enter the number of students (1-1000): ", 1, 1000);
                int[] scores = readIntArray(input, n, "Enter " + n + " scores: ");
                int best = scores[0];
                for (int score : scores)
                    if (score > best)
                        best = score;
                for (int i = 0; i < scores.length; i++) {
                    System.out.println("Student " + i + " score is " + scores[i]
                            + " and grade is " + grade(scores[i], best));
                }
                break;
            }

            // Exercise 7.2: Compare eleven integers
            case 2: {
                System.out.println("\nExercise 7.2: Compare eleven integers");
                int[] numbers = readIntArray(input, 11, "Enter 11 integers: ");
                int reference = numbers[10];
                for (int i = 0; i < numbers.length; i++) {
                    String relation = numbers[i] > reference ? "greater" : numbers[i] < reference ? "smaller" : "equal";
                    System.out.println(numbers[i] + " is " + relation + " to " + reference);
                }
                break;
            }

            // Exercise 7.3: Count occurrences of numbers from 1 to 50
            case 3: {
                System.out.println("\nExercise 7.3: Count occurrences of numbers from 1 to 50");
                int[] counts = readOccurrenceCounts(input);
                for (int n = 1; n <= 50; n++) {
                    if (counts[n - 1] > 0) {
                        System.out.println(n + " occurs " + counts[n - 1]
                                + (counts[n - 1] == 1 ? " time" : " times"));
                    }
                }
                break;
            }

            // Exercise 7.4: Analyze scores
            case 4: {
                System.out.println("\nExercise 7.4: Analyze scores");
                double[] scores = readScoresUntilNegative(input);
                if (scores.length == 0) {
                    System.out.println("No scores were entered.");
                } else {
                    double avg = mean(scores);
                    int aboveOrEqual = 0;
                    for (double score : scores)
                        if (score >= avg)
                            aboveOrEqual++;
                    System.out.println("The average is " + avg);
                    System.out.println("Scores above or equal to the average: " + aboveOrEqual);
                    System.out.println("Scores below the average: " + (scores.length - aboveOrEqual));
                }
                break;
            }

            // Exercise 7.5: Count even and odd numbers until zero
            case 5: {
                System.out.println("\nExercise 7.5: Count even and odd numbers until zero");
                // Follow the sentinel and sample, which contains more than ten values.
                int[] counts = readParityCounts(input);
                System.out.println("The number of odd numbers: " + counts[0]);
                System.out.println("The number of even numbers: " + counts[1]);
                break;
            }

            // Exercise 7.6: Sieve of Eratosthenes
            case 6: {
                System.out.println("\nExercise 7.6: Sieve of Eratosthenes");
                boolean[] prime = sieve(50);
                boolean first = true;
                for (int n = 2; n < prime.length; n++) {
                    if (prime[n]) {
                        if (!first)
                            System.out.print(" ");
                        System.out.print(n);
                        first = false;
                    }
                }
                System.out.println();
                break;
            }

            // Exercise 7.7: Count 200 random single digits
            case 7: {
                System.out.println("\nExercise 7.7: Count 200 random single digits");
                int[] counts = new int[10]; // Java initializes each element to zero.
                for (int draw = 0; draw < 200; draw++)
                    counts[(int) (Math.random() * 10)]++;
                for (int digit = 0; digit < counts.length; digit++) {
                    System.out.println(digit + ": " + counts[digit]);
                }
                break;
            }

            // Exercise 7.8: Average an array
            case 8: {
                System.out.println("\nExercise 7.8: Average an array");
                double[] numbers = readDoubleArray(input, 10, "Enter 10 double values: ");
                System.out.println("The average is " + average(numbers));
                break;
            }

            // Exercise 7.9: Find the largest element
            case 9: {
                System.out.println("\nExercise 7.9: Find the largest element");
                double[] numbers = readDoubleArray(input, 10, "Enter 10 numbers: ");
                System.out.println("The maximum number is " + max(numbers));
                break;
            }

            // Exercise 7.10: Find the last index of the largest element
            case 10: {
                System.out.println("\nExercise 7.10: Find the last index of the largest element");
                double[] numbers = readDoubleArray(input, 10, "Enter 10 numbers: ");
                int index = indexOfLargestElement(numbers);
                System.out.println("The largest element is " + numbers[index] + " at index " + index);
                break;
            }

            // Exercise 7.11: Compute mean and sample deviation
            case 11: {
                System.out.println("\nExercise 7.11: Compute mean and sample deviation");
                double[] numbers = readDoubleArray(input, 10, "Enter 10 numbers: ");
                System.out.printf(Locale.US, "The mean is %.5f%n", mean(numbers));
                System.out.printf(Locale.US, "The standard deviation is %.5f%n", deviation(numbers));
                break;
            }

            // Exercise 7.12: Reverse an array in place
            case 12: {
                System.out.println("\nExercise 7.12: Reverse an array in place");
                double[] numbers = readDoubleArray(input, 10, "Enter 10 numbers: ");
                double[] result = reverse(numbers); // result and numbers refer to the same array.
                System.out.print("The reversed numbers are: ");
                printArray(result);
                break;
            }

            // Exercise 7.13: Select a random supplied number
            case 13: {
                System.out.println("\nExercise 7.13: Select a random supplied number");
                int[] numbers = readSizedIntArray(input, "Enter a size and that many candidate integers: ", 1, 1000);
                System.out.println("A randomly selected value is " + getRandom(numbers));
                break;
            }

            // Exercise 7.14: Least common multiple of five integers
            case 14: {
                System.out.println("\nExercise 7.14: Least common multiple of five integers");
                int[] numbers = readIntArray(input, 5, "Enter five integers: ");
                System.out.println("The least common multiple is " + lcm(numbers));
                break;
            }

            // Exercise 7.15: Eliminate duplicates
            case 15: {
                System.out.println("\nExercise 7.15: Eliminate duplicates");
                int[] numbers = readIntArray(input, 10, "Enter 10 integers: ");
                System.out.print("The distinct numbers are: ");
                printArray(eliminateDuplicates(numbers));
                break;
            }

            // Exercise 7.16: Compare linear and binary search execution times
            case 16: {
                System.out.println("\nExercise 7.16: Compare linear and binary search execution times");
                System.out.println("The book uses 100000000 integers, about 400 MB for the array.");
                System.out.print("Array size [Enter = 100000000; smaller practice size allowed]: ");
                String choice = input.nextLine().trim();
                int size;
                try {
                    size = choice.isEmpty() ? 100000000 : Integer.parseInt(choice);
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Array size must be an integer.");
                }
                if (size < 4 || size > 100000000)
                    throw new IllegalArgumentException("Choose a size from 4 to 100000000.");
                runSearchBenchmark(size);
                break;
            }

            // Exercise 7.17: Sort students by decreasing score
            case 17: {
                System.out.println("\nExercise 7.17: Sort students by decreasing score");
                int n = readIntInRange(input, "Enter the number of students (1-1000): ", 1, 1000);
                String[] names = new String[n];
                double[] scores = new double[n];
                for (int i = 0; i < n; i++) {
                    System.out.print("Enter student " + (i + 1) + " name (one word): ");
                    names[i] = input.next(); // next(), as explicitly requested by Exercise 7.17.
                    scores[i] = readDoubleToken(input, "Enter the score: ");
                }
                // Consume the final numeric line ending before the next line-based exercise.
                if (input.hasNextLine())
                    input.nextLine();
                sortStudents(names, scores);
                System.out.println("Students in decreasing score order:");
                for (int i = 0; i < n; i++)
                    System.out.println(names[i] + " " + scores[i]);
                break;
            }

            // Exercise 7.18: Ascending bubble sort
            case 18: {
                System.out.println("\nExercise 7.18: Ascending bubble sort");
                double[] numbers = readDoubleArray(input, 10, "Enter 10 numbers: ");
                bubbleSort(numbers);
                System.out.print("The sorted numbers are: ");
                printArray(numbers);
                break;
            }

            // Exercise 7.19: Check decreasing order
            case 19: {
                System.out.println("\nExercise 7.19: Check decreasing order");
                int[] numbers = readSizedIntArray(input, "Enter list size and contents: ", 0, 10000);
                System.out.println(isSorted(numbers) ? "The list is already sorted" : "The list is not sorted");
                break;
            }

            // Exercise 7.20: Descending bubble sort
            case 20: {
                System.out.println("\nExercise 7.20: Descending bubble sort");
                double[] numbers = readDoubleArray(input, 10, "Enter 10 numbers: ");
                bubbleSortDescending(numbers);
                System.out.print("The numbers in descending order are: ");
                printArray(numbers);
                break;
            }

            // Exercise 7.21: Normalize integers by their maximum
            case 21: {
                System.out.println("\nExercise 7.21: Normalize integers by their maximum");
                int[] numbers = readIntArray(input, 10, "Enter 10 nonnegative integers: ");
                System.out.print("The normalized numbers are: ");
                printArray(normalize(numbers));
                break;
            }

            // Exercise 7.22: Count lowercase and uppercase vowels
            case 22: {
                System.out.println("\nExercise 7.22: Count lowercase and uppercase vowels");
                System.out.print("Enter a string: ");
                int[] counts = countVowels(input.nextLine());
                String vowels = "aeiouAEIOU";
                int lower = 0, upper = 0;
                for (int i = 0; i < counts.length; i++) {
                    System.out.println(vowels.charAt(i) + ": " + counts[i]);
                    if (i < 5)
                        lower += counts[i];
                    else
                        upper += counts[i];
                }
                System.out.println("Lowercase vowels: " + lower);
                System.out.println("Uppercase vowels: " + upper);
                System.out.println("Total vowels: " + (lower + upper));
                break;
            }

            // Exercise 7.23: Locker puzzle
            case 23: {
                System.out.println("\nExercise 7.23: Locker puzzle");
                boolean[] open = toggleLockers(100);
                boolean first = true;
                for (int i = 0; i < open.length; i++) {
                    if (open[i]) {
                        if (!first)
                            System.out.print(" ");
                        System.out.print(i + 1); // Locker labels are 1-based; array indexes are 0-based.
                        first = false;
                    }
                }
                System.out.println();
                break;
            }

            // Exercise 7.24: Coupon collector: one card from each suit
            case 24: {
                System.out.println("\nExercise 7.24: Coupon collector: one card from each suit");
                int picks = collectSuits(new Random());
                System.out.println("Number of picks: " + picks);
                break;
            }

            // Exercise 7.25: Solve a quadratic using output array parameters
            case 25: {
                System.out.println("\nExercise 7.25: Solve a quadratic using output array parameters");
                double[] eqn = readDoubleArray(input, 3, "Enter a, b, and c: ");
                double[] roots = new double[2];
                int count = solveQuadratic(eqn, roots);
                System.out.println("The number of real roots is " + count);
                for (int i = 0; i < count; i++)
                    System.out.println("Root " + (i + 1) + ": " + roots[i]);
                break;
            }

            // Exercise 7.26: Check ascending order
            case 26: {
                System.out.println("\nExercise 7.26: Check ascending order");
                int[] numbers = readSizedIntArray(input, "Enter list size and contents: ", 0, 10000);
                System.out.println(Exercise726.isSorted(numbers) ? "The list is sorted" : "The list is not sorted");
                break;
            }

            // Exercise 7.27: Check ascending order with a constant interval
            case 27: {
                System.out.println("\nExercise 7.27: Check ascending order with a constant interval");
                int[] numbers = readSizedIntArray(input, "Enter list size and contents: ", 0, 10000);
                System.out.println(isSortedConstantInterval(numbers)
                        ? "The list is sorted with a constant interval."
                        : "The list is not sorted with a constant interval.");
                break;
            }

            // Exercise 7.28: Permutations of four integers
            case 28: {
                System.out.println("\nExercise 7.28: Permutations of four integers");
                int[] numbers = readIntArray(input, 4, "Enter four integers: ");
                int total = printPermutations(numbers);
                System.out.println("Number of distinct arrangements: " + total);
                break;
            }

            // Exercise 7.29: Three dice with a sum of nine
            case 29: {
                System.out.println("\nExercise 7.29: Three dice with a sum of nine");
                int total = 0;
                int[] dice = new int[3];
                for (dice[0] = 1; dice[0] <= 6; dice[0]++) {
                    for (dice[1] = 1; dice[1] <= 6; dice[1]++) {
                        for (dice[2] = 1; dice[2] <= 6; dice[2]++) {
                            if (dice[0] + dice[1] + dice[2] == 9) {
                                printArray(dice);
                                total++;
                            }
                        }
                    }
                }
                System.out.println("Number of ordered configurations: " + total);
                break;
            }

            // Exercise 7.30: Recognize four consecutive equal numbers
            case 30: {
                System.out.println("\nExercise 7.30: Recognize four consecutive equal numbers");
                int[] numbers = readSizedIntArray(input, "Enter list size and contents: ", 0, 10000);
                System.out.println(isConsecutiveFour(numbers)
                        ? "The list has consecutive fours"
                        : "The list has no consecutive fours");
                break;
            }

            // Exercise 7.31: Merge two sorted lists
            case 31: {
                System.out.println("\nExercise 7.31: Merge two sorted lists");
                int[] first = readSizedIntArray(input, "Enter list1 size and contents: ", 0, 10000);
                int[] second = readSizedIntArray(input, "Enter list2 size and contents: ", 0, 10000);
                // Validate outside merge so its key-comparison budget remains linear.
                if (!Exercise726.isSorted(first) || !Exercise726.isSorted(second)) {
                    throw new IllegalArgumentException("Both input lists must be in ascending order.");
                }
                System.out.print("list1 is ");
                printArray(first);
                System.out.print("list2 is ");
                printArray(second);
                System.out.print("The merged list is ");
                printArray(merge(first, second));
                break;
            }

            // Exercise 7.32: Partition a list around its first element
            case 32: {
                System.out.println("\nExercise 7.32: Partition a list around its first element");
                int[] numbers = readSizedIntArray(input, "Enter list size and contents: ", 1, 10000);
                int pivotIndex = partition(numbers);
                System.out.print("After the partition, the list is ");
                printArray(numbers);
                System.out.println("Pivot index: " + pivotIndex + "; pivot value: " + numbers[pivotIndex]);
                break;
            }

            // Exercise 7.33: Month name from an array
            case 33: {
                System.out.println("\nExercise 7.33: Month name from an array");
                int month = readIntInRange(input, "Enter a month (1-12): ", 1, 12);
                System.out.println(monthName(month));
                break;
            }

            // Exercise 7.34: Sort characters in a string
            case 34: {
                System.out.println("\nExercise 7.34: Sort characters in a string");
                System.out.print("Enter a string: ");
                System.out.println("The sorted string is " + sort(input.nextLine()));
                break;
            }

            // Exercise 7.35: Hangman
            case 35: {
                System.out.println("\nExercise 7.35: Hangman");
                playHangman(input, new Random());
                break;
            }

            // Exercise 7.36: Eight Queens using a one-dimensional array
            case 36: {
                System.out.println("\nExercise 7.36: Eight Queens using a one-dimensional array");
                int[] columns = solveEightQueens();
                printQueens(columns);
                break;
            }

            // Exercise 7.37: Bean machine
            case 37: {
                System.out.println("\nExercise 7.37: Bean machine");
                int balls = readIntInRange(input, "Enter the number of balls (0-1000): ", 0, 1000);
                int count = readIntInRange(input, "Enter the number of slots (1-40): ", 1, 40);
                int[] slots = simulateBeanMachine(balls, count, new Random());
                System.out.println("Histogram:");
                printHistogram(slots);
                System.out.print("Slot counts: ");
                printArray(slots);
                break;
            }

            default:
                throw new IllegalArgumentException("Unknown exercise.");
        }
    }

    // ---------------- Methods for Exercises 7.1-7.15 ----------------

    public static char grade(int score, int best) {
        // Widen before subtraction so extreme integer scores do not overflow.
        long gap = (long) best - score;
        if (gap <= 5)
            return 'A';
        if (gap <= 10)
            return 'B';
        if (gap <= 15)
            return 'C';
        if (gap <= 20)
            return 'D';
        return 'F';
    }

    /** 7.6: Index n represents the integer n; false at indexes 0 and 1. */
    public static boolean[] sieve(int limit) {
        if (limit < 0 || limit == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Limit must allow an array of size limit + 1.");
        }
        boolean[] flag = new boolean[limit + 1];
        Arrays.fill(flag, true);
        flag[0] = false;
        if (limit >= 1)
            flag[1] = false;
        for (int prime = 2; prime <= limit / prime; prime++) {
            if (flag[prime]) {
                // Smaller multiples were already marked by smaller prime factors.
                for (long multiple = (long) prime * prime; multiple <= limit; multiple += prime) {
                    flag[(int) multiple] = false;
                }
            }
        }
        return flag;
    }

    /** 7.8: Required integer return type means division truncates toward zero. */
    public static int average(int[] array) {
        requireNonempty(array);
        long sum = 0; // Any Java-sized int[] sum fits in long.
        for (int value : array)
            sum += value;
        return (int) (sum / array.length);
    }

    public static double average(double[] array) {
        return mean(array);
    }

    /** 7.9: Initialize from data, not zero; this also handles all-negative data. */
    public static double max(double[] array) {
        requireNonempty(array);
        double largest = array[0];
        for (double value : array) {
            requireFinite(value);
            if (value > largest)
                largest = value;
        }
        return largest;
    }

    /** 7.10: >= updates on ties, so the largest matching index is retained. */
    public static int indexOfLargestElement(double[] array) {
        requireArray(array);
        int index = -1; // Empty-array convention: there is no maximum index.
        for (int i = 0; i < array.length; i++) {
            requireFinite(array[i]);
            if (index == -1 || array[i] >= array[index])
                index = i;
        }
        return index;
    }

    /** 7.11: Scale first, then average, avoiding overflow of an ordinary sum. */
    public static double mean(double[] x) {
        requireNonempty(x);
        double scale = 0;
        for (double value : x) {
            requireFinite(value);
            scale = Math.max(scale, Math.abs(value));
        }
        if (scale == 0)
            return 0;
        double sum = 0;
        double correction = 0;
        for (double value : x) {
            // Compensated addition reduces loss from repeated rounding.
            double adjusted = value / scale - correction;
            double next = sum + adjusted;
            correction = (next - sum) - adjusted;
            sum = next;
        }
        double result = (sum / x.length) * scale;
        requireFinite(result);
        return result;
    }

    /** 7.11: Sample standard deviation; denominator is n - 1, not n. */
    public static double deviation(double[] x) {
        requireNonempty(x);
        if (x.length < 2)
            throw new IllegalArgumentException("Deviation needs at least two values.");
        double avg = mean(x);
        double scale = 0;
        for (double value : x) {
            double difference = value - avg;
            requireFinite(difference);
            scale = Math.max(scale, Math.abs(difference));
        }
        if (scale == 0)
            return 0;
        double squares = 0;
        for (double value : x) {
            double normalized = (value - avg) / scale;
            squares += normalized * normalized;
        }
        // Scaling is algebraically equivalent to the printed squared-deviation
        // formula but avoids overflowing intermediate squares unnecessarily.
        double result = scale * Math.sqrt(squares / (x.length - 1));
        requireFinite(result);
        return result;
    }

    /** 7.12: Swap the ends inward; do not allocate a replacement array. */
    public static double[] reverse(double[] array) {
        requireArray(array);
        for (int left = 0, right = array.length - 1; left < right; left++, right--) {
            double temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }
        return array;
    }

    /**
     * 7.13: Each supplied position is equally likely, including duplicate values.
     */
    public static int getRandom(int... numbers) {
        requireNonempty(numbers);
        return numbers[(int) (Math.random() * numbers.length)];
    }

    /** 7.14: lcm(a,b) = abs(a)/gcd(a,b) * abs(b), extended across the array. */
    public static int lcm(int... numbers) {
        requireNonempty(numbers);
        // Check zeros first: the final answer is zero even if an earlier pair
        // would otherwise exceed the required int return range.
        for (int value : numbers)
            if (value == 0)
                return 0;
        long result = 1;
        for (int value : numbers) {
            long magnitude = Math.abs((long) value); // Safe for Integer.MIN_VALUE.
            result = result / gcd(result, magnitude) * magnitude;
            if (result > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("The LCM exceeds the int return range.");
            }
        }
        return (int) result;
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    /** 7.15: A new array; retain the order in which values first appeared. */
    public static int[] eliminateDuplicates(int[] list) {
        requireArray(list);
        int[] distinct = new int[list.length];
        int used = 0;
        for (int value : list) {
            boolean present = false;
            for (int i = 0; i < used; i++) {
                if (distinct[i] == value) {
                    present = true;
                    break;
                }
            }
            if (!present)
                distinct[used++] = value;
        }
        return Arrays.copyOf(distinct, used);
    }

    // ---------------- Methods for Exercises 7.16-7.24 ----------------

    /** 7.16: Return an index, or -1 when no element matches. */
    public static int linearSearch(int[] list, int key) {
        requireArray(list);
        for (int i = 0; i < list.length; i++)
            if (list[i] == key)
                return i;
        return -1;
    }

    /**
     * 7.16: Ascending input is a precondition; negative result encodes insertion.
     */
    public static int binarySearch(int[] list, int key) {
        requireArray(list);
        int low = 0, high = list.length - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (key < list[middle])
                high = middle - 1;
            else if (key > list[middle])
                low = middle + 1;
            else
                return middle;
        }
        return -low - 1;
    }

    /** Allocate before timing so the reported intervals measure only searches. */
    public static void runSearchBenchmark(int size) {
        if (size < 4 || size > 100000000) {
            throw new IllegalArgumentException("Benchmark size must be 4-100000000.");
        }
        int[] list;
        try {
            list = new int[size];
        } catch (OutOfMemoryError ex) {
            System.out.println("This JVM could not allocate the array. Choose a smaller practice size,");
            System.out.println("or run the full exercise with a larger Java heap, for example:");
            System.out.println("java -Xmx1g -cp . Exercises.HelloJava_Ch7");
            return;
        }
        for (int i = 0; i < size; i++)
            list[i] = i + 1;
        int[] keys = { 1, size / 4, size / 2, (int) (3L * size / 4), size };
        System.out.println(size == 100000000 ? "Full textbook benchmark" : "Practice benchmark (scaled keys)");
        System.out.printf("%-12s %12s %14s %14s%n", "Key", "Index", "Linear ns", "Binary ns");
        for (int key : keys) {
            long start = System.nanoTime();
            int linearIndex = linearSearch(list, key);
            long linearTime = System.nanoTime() - start;
            start = System.nanoTime();
            int binaryIndex = binarySearch(list, key);
            long binaryTime = System.nanoTime() - start;
            if (linearIndex != binaryIndex)
                throw new IllegalStateException("Search indexes disagree.");
            System.out.printf("%-12d %12d %14d %14d%n", key, linearIndex, linearTime, binaryTime);
        }
    }

    /** 7.17: Keep the parallel name/score arrays synchronized; ties are stable. */
    public static void sortStudents(String[] names, double[] scores) {
        if (names == null)
            throw new IllegalArgumentException("Names cannot be null.");
        requireArray(scores);
        if (names.length != scores.length)
            throw new IllegalArgumentException("Array lengths must agree.");
        for (int i = 0; i < names.length; i++) {
            if (names[i] == null)
                throw new IllegalArgumentException("A student name cannot be null.");
            requireFinite(scores[i]);
        }
        for (int end = scores.length - 1; end > 0; end--) {
            boolean swapped = false;
            for (int i = 0; i < end; i++) {
                if (scores[i] < scores[i + 1]) {
                    double tempScore = scores[i];
                    scores[i] = scores[i + 1];
                    scores[i + 1] = tempScore;
                    String tempName = names[i];
                    names[i] = names[i + 1];
                    names[i + 1] = tempName;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    /** 7.18: Neighbor comparisons and swaps, not a library sort call. */
    public static void bubbleSort(double[] list) {
        bubbleSortByDirection(list, false);
    }

    /** 7.20: Reuse the same passes, with the comparison direction reversed. */
    public static void bubbleSortDescending(double[] list) {
        bubbleSortByDirection(list, true);
    }

    private static void bubbleSortByDirection(double[] list, boolean descending) {
        requireArray(list);
        for (double value : list)
            requireFinite(value);
        for (int end = list.length - 1; end > 0; end--) {
            boolean swapped = false;
            for (int i = 0; i < end; i++) {
                boolean outOfOrder = descending ? list[i] < list[i + 1] : list[i] > list[i + 1];
                if (outOfOrder) {
                    double temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    /**
     * 7.19: Decreasing means nonincreasing, consistent with its equal-value sample.
     */
    public static boolean isSorted(int[] list) {
        requireArray(list);
        for (int i = 1; i < list.length; i++)
            if (list[i - 1] < list[i])
                return false;
        return true;
    }

    /**
     * 7.21: Divide by maximum; nonnegative input is needed for the stated range.
     */
    public static double[] normalize(int[] values) {
        requireArray(values);
        int largest = 0;
        for (int value : values) {
            if (value < 0)
                throw new IllegalArgumentException("Negative input cannot give the stated 0-1 range.");
            if (value > largest)
                largest = value;
        }
        double[] result = new double[values.length];
        if (largest == 0)
            return result; // Explicit all-zero (or empty) convention.
        for (int i = 0; i < values.length; i++)
            result[i] = values[i] / (double) largest;
        return result;
    }

    /** 7.22: Indexes 0-4 count aeiou; indexes 5-9 count AEIOU. */
    public static int[] countVowels(String text) {
        if (text == null)
            throw new IllegalArgumentException("String cannot be null.");
        String vowels = "aeiouAEIOU";
        int[] counts = new int[vowels.length()];
        for (int i = 0; i < text.length(); i++) {
            int index = vowels.indexOf(text.charAt(i));
            if (index >= 0)
                counts[index]++;
        }
        return counts;
    }

    /** 7.23: The false default represents a closed locker. */
    public static boolean[] toggleLockers(int count) {
        if (count < 0)
            throw new IllegalArgumentException("Locker count cannot be negative.");
        boolean[] open = new boolean[count];
        for (int student = 1; student <= count; student++) {
            for (long locker = student; locker <= count; locker += student) {
                int index = (int) locker - 1;
                open[index] = !open[index];
            }
        }
        return open;
    }

    /**
     * 7.24: Sampling with replacement; print only the first card of each new suit.
     */
    public static int collectSuits(Random random) {
        if (random == null)
            throw new IllegalArgumentException("Random generator cannot be null.");
        boolean[] seen = new boolean[4];
        int suits = 0, picks = 0;
        while (suits < seen.length) {
            int card = random.nextInt(52);
            picks++;
            int suit = card / 13;
            if (!seen[suit]) {
                seen[suit] = true;
                suits++;
                System.out.println(cardName(card));
            }
        }
        return picks;
    }

    private static String cardName(int card) {
        String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
        String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
        return ranks[card % 13] + " of " + suits[card / 13];
    }

    // ---------------- Methods for Exercises 7.25-7.34 ----------------

    /** 7.25: eqn[0..2] = a,b,c; roots[0..1] are output slots. */
    public static int solveQuadratic(double[] eqn, double[] roots) {
        requireArray(eqn);
        requireArray(roots);
        if (eqn.length < 3 || roots.length < 2) {
            throw new IllegalArgumentException("Need three coefficients and two root slots.");
        }
        double a = eqn[0], b = eqn[1], c = eqn[2];
        requireFinite(a);
        requireFinite(b);
        requireFinite(c);
        if (a == 0)
            throw new IllegalArgumentException("a must not be zero for a quadratic equation.");
        roots[0] = Double.NaN;
        roots[1] = Double.NaN;
        // Scaling keeps b*b and 4*a*c from overflowing for ordinary large inputs.
        double scale = Math.max(Math.abs(a), Math.max(Math.abs(b), Math.abs(c)));
        a /= scale;
        b /= scale;
        c /= scale;
        if (a == 0)
            throw new IllegalArgumentException("Coefficient ratio exceeds double precision range.");
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0)
            return 0;
        if (discriminant == 0) {
            roots[0] = -b / (2 * a);
            requireFinite(roots[0]);
            return 1;
        }
        // Avoid subtracting nearly equal numbers when constructing one root;
        // obtain the other from the root product c/a. Output order is unspecified.
        double q = -0.5 * (b + Math.copySign(Math.sqrt(discriminant), b));
        roots[0] = q / a;
        roots[1] = c / q;
        requireFinite(roots[0]);
        requireFinite(roots[1]);
        return 2;
    }

    /**
     * Separate scope because 7.19 already defines isSorted(int[]) for decreasing
     * order.
     */
    public static class Exercise726 {
        /**
         * 7.26: Boolean, as printed in the screenshot; returned values are never null.
         */
        public static Boolean isSorted(int[] list) {
            requireArray(list);
            for (int i = 1; i < list.length; i++)
                if (list[i - 1] > list[i])
                    return false;
            return true;
        }
    }

    /** 7.27: Ascending (nondecreasing) with one fixed adjacent difference. */
    public static boolean isSortedConstantInterval(int[] list) {
        requireArray(list);
        if (list.length < 2)
            return true;
        // Widen before subtraction: two int values can have a difference > int max.
        long interval = (long) list[1] - list[0];
        if (interval < 0)
            return false;
        for (int i = 2; i < list.length; i++) {
            if ((long) list[i] - list[i - 1] != interval)
                return false;
        }
        return true;
    }

    /**
     * 7.28: Lexicographic successor visits each distinct value arrangement once.
     */
    public static int printPermutations(int[] numbers) {
        requireArray(numbers);
        if (numbers.length != 4)
            throw new IllegalArgumentException("This exercise requires four integers.");
        int[] values = numbers.clone(); // Do not change the caller's array.
        Arrays.sort(values);
        int count = 0;
        do {
            printArray(values);
            count++;
        } while (nextPermutation(values));
        return count;
    }

    private static boolean nextPermutation(int[] values) {
        int pivot = values.length - 2;
        while (pivot >= 0 && values[pivot] >= values[pivot + 1])
            pivot--;
        if (pivot < 0)
            return false;
        int successor = values.length - 1;
        while (values[successor] <= values[pivot])
            successor--;
        swap(values, pivot, successor);
        // The suffix was decreasing; reverse it to the smallest possible suffix.
        for (int left = pivot + 1, right = values.length - 1; left < right; left++, right--) {
            swap(values, left, right);
        }
        return true;
    }

    /**
     * 7.30: Return true as soon as a run reaches four; longer runs also qualify.
     */
    public static boolean isConsecutiveFour(int[] values) {
        requireArray(values);
        int run = 1;
        for (int i = 1; i < values.length; i++) {
            run = values[i] == values[i - 1] ? run + 1 : 1;
            if (run >= 4)
                return true;
        }
        return false;
    }

    /**
     * 7.31: Inputs must already be ascending. Return a new array without modifying
     * either.
     */
    public static int[] merge(int[] list1, int[] list2) {
        requireArray(list1);
        requireArray(list2);
        if ((long) list1.length + list2.length > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Merged array would exceed Java's length range.");
        }
        int[] result = new int[list1.length + list2.length];
        int first = 0, second = 0, next = 0;
        while (first < list1.length && second < list2.length) {
            // Exactly one element comparison per iteration of this loop.
            if (list1[first] <= list2[second])
                result[next++] = list1[first++];
            else
                result[next++] = list2[second++];
        }
        while (first < list1.length)
            result[next++] = list1[first++];
        while (second < list2.length)
            result[next++] = list2[second++];
        return result;
    }

    /** 7.32: In-place partition using the first value; n-1 pivot comparisons. */
    public static int partition(int[] list) {
        requireNonempty(list);
        int pivot = list[0];
        int boundary = 0;
        for (int i = 1; i < list.length; i++) {
            if (list[i] <= pivot) {
                boundary++;
                swap(list, boundary, i);
            }
        }
        swap(list, 0, boundary);
        return boundary;
    }

    private static void swap(int[] list, int first, int second) {
        int temp = list[first];
        list[first] = list[second];
        list[second] = temp;
    }

    public static String monthName(int month) {
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("Month must be 1-12.");
        String[] names = { "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December" };
        return names[month - 1];
    }

    /**
     * 7.34: Convert the immutable string to an array, sort it, then construct a
     * result.
     */
    public static String sort(String s) {
        if (s == null)
            throw new IllegalArgumentException("String cannot be null.");
        char[] characters = s.toCharArray();
        Arrays.sort(characters);
        return new String(characters);
    }

    // ---------------- Methods for Exercises 7.35-7.37 ----------------

    /**
     * 7.35: A word bank is a String[]; a seeded Random can reproduce a test run.
     */
    public static void playHangman(Scanner input, Random random) {
        if (input == null || random == null)
            throw new IllegalArgumentException("Input and random generator are required.");
        String[] words = { "write", "that", "program", "array", "method", "java", "loops", "science" };
        while (true) {
            String word = words[random.nextInt(words.length)];
            int misses = playHangmanRound(word, input);
            if (misses == -1) {
                System.out.println("Left hangman.");
                return;
            }
            System.out.println("The word is " + word + ". You missed " + misses
                    + (misses == 1 ? " time" : " times"));
            if (!readYesNo(input, "Do you want another word? Enter y or n: "))
                return;
        }
    }

    /** Return the miss count, or -1 for the added :q early-exit command. */
    public static int playHangmanRound(String word, Scanner input) {
        if (word == null || word.isEmpty() || input == null) {
            throw new IllegalArgumentException("A nonempty word and input are required.");
        }
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) < 'a' || word.charAt(i) > 'z') {
                throw new IllegalArgumentException("The word bank must use lowercase English letters.");
            }
        }
        char[] revealed = new char[word.length()];
        Arrays.fill(revealed, '*');
        boolean[] guessed = new boolean[26];
        int hidden = word.length(), misses = 0;
        while (hidden > 0) {
            System.out.print("(Guess) Enter a letter in word " + new String(revealed) + " (:q to leave) > ");
            String answer = input.nextLine().trim().toLowerCase(Locale.US);
            if (answer.equals(":q"))
                return -1;
            if (answer.length() != 1 || answer.charAt(0) < 'a' || answer.charAt(0) > 'z') {
                System.out.println("Enter one English letter.");
                continue;
            }
            char letter = answer.charAt(0);
            int index = letter - 'a';
            if (guessed[index]) {
                System.out.println(letter + " was already guessed.");
                continue;
            }
            guessed[index] = true;
            boolean found = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    revealed[i] = letter;
                    hidden--;
                    found = true;
                }
            }
            if (!found) {
                misses++;
                System.out.println(letter + " is not in the word.");
            }
        }
        return misses;
    }

    /** 7.36: columns[row] records the chosen column; -1 means no current choice. */
    public static int[] solveEightQueens() {
        int[] columns = new int[8];
        Arrays.fill(columns, -1);
        int row = 0;
        while (row >= 0 && row < columns.length) {
            columns[row]++;
            while (columns[row] < columns.length && !queenIsSafe(columns, row))
                columns[row]++;
            if (columns[row] < columns.length) {
                row++; // This row works; try placing a queen in the next row.
                if (row < columns.length)
                    columns[row] = -1;
            } else {
                columns[row] = -1;
                row--; // No column works: backtrack and revise the preceding row.
            }
        }
        if (row < 0)
            throw new IllegalStateException("No eight-queens solution was found.");
        return columns;
    }

    private static boolean queenIsSafe(int[] columns, int row) {
        for (int earlier = 0; earlier < row; earlier++) {
            if (columns[earlier] == columns[row]
                    || Math.abs(columns[row] - columns[earlier]) == row - earlier)
                return false;
        }
        return true;
    }

    public static void printQueens(int[] columns) {
        requireArray(columns);
        if (columns.length != 8)
            throw new IllegalArgumentException("Eight rows are required.");
        for (int row = 0; row < 8; row++) {
            if (columns[row] < 0 || columns[row] >= 8 || !queenIsSafe(columns, row)) {
                throw new IllegalArgumentException("The array is not a valid eight-queens solution.");
            }
        }
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                System.out.print(columns[row] == column ? "|Q" : "| ");
            }
            System.out.println("|");
        }
    }

    /** 7.37: Return the slot counts; print every simulated path. */
    public static int[] simulateBeanMachine(int balls, int slotCount, Random random) {
        if (balls < 0 || slotCount < 1 || random == null) {
            throw new IllegalArgumentException("Require balls >= 0, slots >= 1, and a random generator.");
        }
        int[] slots = new int[slotCount];
        for (int ball = 0; ball < balls; ball++) {
            int rights = 0;
            String path = "";
            for (int peg = 0; peg < slotCount - 1; peg++) {
                boolean right = random.nextBoolean();
                path += right ? 'R' : 'L';
                if (right)
                    rights++;
            }
            slots[rights]++;
            System.out.println("Ball " + (ball + 1) + ": " + path);
        }
        return slots;
    }

    public static void printHistogram(int[] slots) {
        requireNonempty(slots);
        int tallest = 0;
        for (int height : slots) {
            if (height < 0)
                throw new IllegalArgumentException("Slot counts cannot be negative.");
            if (height > tallest)
                tallest = height;
        }
        int width = Math.max(3, Integer.toString(slots.length - 1).length() + 1);
        for (int level = tallest; level >= 1; level--) {
            for (int height : slots)
                System.out.printf("%" + width + "s", height >= level ? "O" : " ");
            System.out.println();
        }
        // Label all slots, including empty ones, to keep horizontal positions clear.
        for (int index = 0; index < slots.length; index++)
            System.out.printf("%" + width + "d", index);
        System.out.println();
    }

    // ---------------- Shared input and output helpers ----------------

    private static int readExercise(Scanner input) {
        while (true) {
            System.out.print("Exercise: ");
            String text = input.nextLine().trim();
            if (text.startsWith("7."))
                text = text.substring(2);
            try {
                int value = Integer.parseInt(text);
                if (value >= 0 && value <= 37)
                    return value;
            } catch (NumberFormatException ex) {
                /* Request a valid replacement line. */ }
            System.out.println("Enter 1-37, 7.1-7.37, or 0.");
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

    /** 7.17's token-oriented companion to Scanner.next() for student names. */
    private static double readDoubleToken(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(input.next());
                if (Double.isFinite(value))
                    return value;
            } catch (NumberFormatException ex) {
                /* Invalid token was consumed. */ }
            System.out.println("Enter a finite numeric score.");
        }
    }

    private static int[] readIntArray(Scanner input, int size, String prompt) {
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

    private static double[] readDoubleArray(Scanner input, int size, String prompt) {
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

    /** Read a size-prefixed list, allowing size and values on the same line. */
    private static int[] readSizedIntArray(Scanner input, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String text = input.nextLine().trim();
            if (text.isEmpty()) {
                System.out.println("Enter the list size first.");
                continue;
            }
            String[] tokens = text.split("\\s+");
            int size;
            try {
                size = Integer.parseInt(tokens[0]);
            } catch (NumberFormatException ex) {
                System.out.println("The first value must be an integer size.");
                continue;
            }
            if (size < min || size > max || tokens.length - 1 > size) {
                System.out.println("Use a size from " + min + " through " + max + " and no excess contents.");
                continue;
            }
            int[] result = new int[size];
            boolean valid = true;
            for (int i = 1; i < tokens.length; i++) {
                try {
                    result[i - 1] = Integer.parseInt(tokens[i]);
                } catch (NumberFormatException ex) {
                    valid = false;
                    break;
                }
            }
            if (!valid) {
                System.out.println("Invalid integer; re-enter the size and contents.");
                continue;
            }
            int used = tokens.length - 1;
            if (used < size) {
                System.out.print("Enter " + (size - used) + " remaining integers: ");
                fillIntArray(input, result, used);
            }
            return result;
        }
    }

    private static int[] readOccurrenceCounts(Scanner input) {
        int[] counts = new int[50];
        System.out.print("Enter integers 1-50, ending with 0: ");
        while (true) {
            String text = input.nextLine().trim();
            if (text.isEmpty())
                continue;
            for (String token : text.split("\\s+")) {
                try {
                    int value = Integer.parseInt(token);
                    if (value == 0)
                        return counts;
                    if (value >= 1 && value <= 50)
                        counts[value - 1]++;
                    else
                        System.out.println("Ignored " + value + ": enter 1-50 or the 0 sentinel.");
                } catch (NumberFormatException ex) {
                    System.out.println("Ignored invalid integer: " + token);
                }
            }
        }
    }

    private static double[] readScoresUntilNegative(Scanner input) {
        double[] scores = new double[100];
        int used = 0;
        System.out.print("Enter up to 100 scores, ending with any negative number: ");
        while (true) {
            String text = input.nextLine().trim();
            if (text.isEmpty())
                continue;
            for (String token : text.split("\\s+")) {
                try {
                    double score = Double.parseDouble(token);
                    if (!Double.isFinite(score)) {
                        System.out.println("Ignored nonfinite score.");
                        continue;
                    }
                    if (score < 0)
                        return Arrays.copyOf(scores, used);
                    if (used < scores.length)
                        scores[used++] = score;
                    else
                        System.out.println("100 scores already stored; enter a negative number to finish.");
                } catch (NumberFormatException ex) {
                    System.out.println("Ignored invalid score: " + token);
                }
            }
        }
    }

    private static int[] readParityCounts(Scanner input) {
        int[] counts = new int[2]; // [0] odd, [1] even.
        System.out.print("Enter integers ending with 0: ");
        while (true) {
            String text = input.nextLine().trim();
            if (text.isEmpty())
                continue;
            for (String token : text.split("\\s+")) {
                try {
                    int value = Integer.parseInt(token);
                    if (value == 0)
                        return counts;
                    counts[value % 2 == 0 ? 1 : 0]++;
                } catch (NumberFormatException ex) {
                    System.out.println("Ignored invalid integer: " + token);
                }
            }
        }
    }

    private static boolean readYesNo(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String answer = input.nextLine().trim();
            if (answer.equalsIgnoreCase("y"))
                return true;
            if (answer.equalsIgnoreCase("n"))
                return false;
            System.out.println("Enter y or n.");
        }
    }

    public static void printArray(int[] array) {
        requireArray(array);
        for (int i = 0; i < array.length; i++) {
            if (i > 0)
                System.out.print(" ");
            System.out.print(array[i]);
        }
        System.out.println();
    }

    public static void printArray(double[] array) {
        requireArray(array);
        for (int i = 0; i < array.length; i++) {
            if (i > 0)
                System.out.print(" ");
            System.out.print(array[i]);
        }
        System.out.println();
    }

    private static void requireArray(int[] array) {
        if (array == null)
            throw new IllegalArgumentException("Array cannot be null.");
    }

    private static void requireArray(double[] array) {
        if (array == null)
            throw new IllegalArgumentException("Array cannot be null.");
    }

    private static void requireNonempty(int[] array) {
        requireArray(array);
        if (array.length == 0)
            throw new IllegalArgumentException("Array cannot be empty.");
    }

    private static void requireNonempty(double[] array) {
        requireArray(array);
        if (array.length == 0)
            throw new IllegalArgumentException("Array cannot be empty.");
    }

    private static void requireFinite(double value) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Values and numerical results must be finite.");
    }
}
