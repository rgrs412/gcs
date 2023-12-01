package org.example.activity2;

import org.example.utils.Input;
import org.example.utils.Validator;

import java.text.DecimalFormat;
import java.util.*;

public class Util {
    public static double sortAndFindMedian(int[] numbers) {
        sort(numbers);
        int n = numbers.length;
        if(n % 2 == 0) {
            return (double) (numbers[n/2 - 1] + numbers[n/2]) / 2;
        } else {
            return numbers[n/2];
        }
    }

    public static void sort(int[] numbers) {
        LinkedList<Integer> queue = new LinkedList<>();
        quickSort(numbers, queue);

        for(int i=0; i<numbers.length; i++) {
            numbers[i] = queue.removeFirst();
        }
    }

    /**
     * Takes in an empty queue and add numbers from arrayToSort to the queue
     * in the sorted order
     * @param arrayToSort
     * @param queue
     */
    private static void quickSort(int[] arrayToSort, LinkedList<Integer> queue) {
        if(arrayToSort.length == 1) {
            queue.add(arrayToSort[0]);
            return;
        } else if (arrayToSort.length == 0) {
            return;
        }

        //divide
        int[][] dividedArrays = divideByPivot(arrayToSort);

        //conquer
        quickSort(dividedArrays[0],  queue);
        quickSort(dividedArrays[1],  queue);
    }

    private static int[][] divideByPivot(int[] arrayToDivide) {
        LinkedList<Integer> deque = new LinkedList<>();

        //Select the first element as pivot
        int pivot = arrayToDivide[0];
        deque.add(pivot);

        //counter for # of element less than or equal to pivot
        //counter starts with 1 since we already added the pivot to the deque
        int counter = 1;

        //Add number to front of deque if it's less than or equal to pivot
        //Otherwise add it to the back of deque
        for(int i=1; i<arrayToDivide.length; i++) {
            if(arrayToDivide[i] <= pivot) {
                deque.addFirst(arrayToDivide[i]);
                counter++;
            } else {
                deque.addLast(arrayToDivide[i]);
            }
        }

        int[][] dividedArrays = new int[2][];
        dividedArrays[0] = new int[counter];
        dividedArrays[1]= new int[arrayToDivide.length - counter];

        //Prevent infinite loop in the case of:
        //dividedArrays[0] = [a, b, c, ...] and dividedArrays[1] = []
        //where divideByPivot([a, b, c, ...]) always result in
        //dividedArrays[0] = [a, b, c, ...] and dividedArrays[1] = []
        if(counter == arrayToDivide.length) {
            dividedArrays[0] = new int[counter-1];
            dividedArrays[1]= new int[1];
        }

        //Construct array for all numbers equal to or less than pivot
        for(int j=0; j < dividedArrays[0].length; j++) {
            dividedArrays[0][j] = deque.removeFirst();
        }

        //Construct array for all numbers more than pivot
        for(int k=0; k < dividedArrays[1].length; k++) {
            dividedArrays[1][k] = deque.removeFirst();
        }

        return dividedArrays;
    }

    public static void getUserInputAndSortAndFindMedium() {
        System.out.println("Enter a list of numbers to sort: ");
        System.out.println("Note: Format of input '2 4 5 7 1' ");
        String input = Input.getUserInput();

        String[] splitInput = input.split(" ");
        int[] nums = new int[splitInput.length];

        for(int i=0; i<splitInput.length; i++) {
            if(Validator.isInt(splitInput[i])) {
                nums[i] = Integer.parseInt(splitInput[i]);
            } else {
                System.out.println("Invalid input. Format of input '2 4 5 7 1'\n");
                return;
            }
        }

        DecimalFormat df = new DecimalFormat("0.#");
        System.out.println(df.format(sortAndFindMedian(nums)) + "\n");
    }

    public static void getUserInputAndGenerateRandomNumbersBySize() {
        System.out.println("Enter size of random numbers array: ");
        String input = Input.getUserInput();

        if(Validator.isInt(input)) {
            int size = Integer.parseInt(input);
            int[] randomNums = generateRandomNumbersBySize(size, 100);
            for(int i : randomNums) {
               System.out.print(i + " ");
            }
            System.out.println("\n");
        } else {
            System.out.println("Invalid input. Please enter a number.\n");
        }
    }

    /**
     * @param size The size of the random numbers array
     * @param bound The bound of the random number(s) to generate
     * @return A random numbers array of the specified size, where the
     * numbers are between the range of 0 and the bound specified.
     */
    private static int[] generateRandomNumbersBySize(int size, int bound) {
        int[] randomNumbers = new int[size];
        int i = 0;
        while(i < size) {
            Random random = new Random();
            randomNumbers[i] = random.nextInt(bound);
            i++;
        }

        return randomNumbers;
    }
}
