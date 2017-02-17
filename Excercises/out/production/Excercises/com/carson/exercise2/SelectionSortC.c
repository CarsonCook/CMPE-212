/* Your job is to convert this working C program to a Java program.  For this exercise, you
 * will be given exact instructions in the comments scattered throughout the code below.
 * Follow the instructions closely, and pay attention to any compiler warnings you get
 * after you think you have made the proper change.
 *
 * The first thing to do is to open up Eclipse and create a new project if necessary.
 * If you are at all puzzled about how to do this, open Eclipse, click on "Help" and
 * go to the "Welcome" page. Find and work through the "Create a Hello World Application"
 * Tutorial.
 * 
 * Add a new Java class to your project - call it "JavaSelectionSort" for example.
 * You should get a shell class with the following class header:
 
 public class JavaSelectionSort {
	 
 * which ends at the last curly bracket:

 }
 
 * Copy and paste all the code below into the editor window between the { } brackets.
 * As you make the necessary changes, all those delightful error messages 
 * should start to go away! Note that the class is saved in a file called
 * JavaSelectionSort.java.
 */

// Get rid of the following two lines - you won't need them!
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Add the line: "import java.util.Scanner;" above the "public class JavaSelection Sort {" line,
// but below the "package ..." line (if there is one).
// You will be using this Scanner class to help with console window input.

/* You will need the array and the constant in the Java program too, but you cannot declare them
 * as they are declared in C.  Delete the two C code lines below and replace them with:
 
	public static final int MAXSIZE = 10;
	public static int[] nums = new int[MAXSIZE];
	
 * Note that these two lines define attributes inside, and available throughout the
 * SelectionSort class.  In this program the constant MAXSIZE, and
 * the integer array nums are available throughout the entire SelectionSort class.
*/ 
#define MAXSIZE 10000
int nums[MAXSIZE];

// Delete these three function prototypes - you won't need them.
void selectionSort ();
void swap (int, int);
void printNums ();

// Add "public static" before the "void" in the swap method header line:
void swap (int pos1, int pos2) {
	
	int temp = nums[pos1];
	nums[pos1] = nums[pos2];
	nums[pos2] = temp;
	
} // end swap

// Add "public static" before the "void" in the selectionSort method header line,
// as you did with the swap method.  Nothing else in the selectionSort method has
// to be changed!!
void selectionSort () {
	
	int endOfSortedRegion;
	int j;
	int minLocation;
	
	for (endOfSortedRegion = 0; endOfSortedRegion < MAXSIZE - 1; endOfSortedRegion++) {
		
		minLocation = endOfSortedRegion;
		
		for (j = endOfSortedRegion + 1; j < MAXSIZE; j++)
			
			if (nums[j] < nums[minLocation])
				minLocation = j;

			
		if (minLocation != endOfSortedRegion)
			swap(minLocation, endOfSortedRegion);
	}

} // end selectionSort

// Add the usual "public static" access modifiers before the "void":
void printNums () {
	
	int i;
	
	for (i = 0; i < MAXSIZE; i++)
		// Instead of the printf line below, use "System.out.println(nums[i]);"
		printf("\n%d", nums[i]);
	
} // end printNums

// Change the main header to "public static void main (String[] args) {"
int main() {
	int i;
double start;
srand(time(NULL));
	
for (i = 0; i < MAXSIZE; i++)
	nums[i] = rand();
		
start = clock();
selectionSort();
printf("\n\nSorting %d elements took %g milliseconds.", MAXSIZE, (clock() - start));
return 0;
	
} // end main

/* Finally, to really tidy up your Java program, you should delete all these comments
 * and add comments that actually describe what the program does!
 */