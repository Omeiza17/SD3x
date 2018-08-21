package homework_II;

import java.util.*;
@SuppressWarnings({"Java8ListSort", "WeakerAccess"})
public class GreedyDynamicAlgorithms {

	/**
	 * Goal: find the smallest number of red intervals to select, such that
	 * every blue interval overlaps with at least one of the selected red intervals.
	 * Output this number
	 * 
	 * @param red - the list of red intervals
	 * @param blue - the list blue intervals
	 * @return int - the smallest number of red intervals
	 */
	public static int optimalIntervals(ArrayList<Interval> red, ArrayList<Interval> blue) {
		Interval.sortByStartTime(blue);
		Interval.sortByFinishTime(red);

		int i = 0; int j = 0; int count = 0;

		while (i < blue.size() && j < red.size()) {
		    if (!isIntersected(red.get(j), blue.get(i))) {
		        j++;
		        continue;
            }
            // at this point, blue[i] is intersected with red[j]


            while (j < red.size() && isIntersected(red.get(j), blue.get(i))) j++;

            count++;

            while (isIntersected(red.get(j - 1), blue.get(i))) {
                i++;
                if (i == blue.size()) return count; // the last blue has been passed
            }
        }

		return i < blue.size() ? -1 : count;
	}

	public static boolean isIntersected(Interval red, Interval blue) {
	    return red.start < blue.start ? red.finish >= blue.start : blue.finish <= red.finish;
    }
	
	/**
	 * Goal: find any path of lowest cost from the top-left of the grid (grid[0][0])
	 * to the bottom right of the grid (grid[m-1][n-1]).  Output this sequence of directions
	 * 
	 * @param grid - the 2d grid containing the cost of each location in the grid.
	 * @return path - the path of lowest cost
	 */
	public static List<Direction> optimalGridPath(int[][] grid) {
		int m = grid.length; int n = grid[0].length;

		int[][] min = new int[m][n];
		int minPath = minPath(grid, 0, 0, min);
        System.out.printf("Min cost = %d\n", minPath);

		List<Direction> path = new ArrayList<>();
		int i = 0; int j = 0;

        while (i != m - 1 || j != n - 1) {
            if (!isValidSquare(grid, i, j + 1) || (isValidSquare(grid, i + 1, j) && min[i + 1][j] < min[i][j + 1])) {
                path.add(Direction.DOWN);
                i++;
            } else {
                path.add(Direction.RIGHT);
                j++;
            }
        }
		return path;
	}

	/*
	* checks if a coordinate is a valid entry in the grid
	*/
    private static boolean isValidSquare(int[][] grid, int row, int col) {
	    return row < grid.length && col < grid[0].length;
    }

    /*
    * Recursively looks for the minimum path from a position in the grid to the bottom right
    */
    private static int minPath(int[][] grid, int row, int col, int[][] memo) {
	    if (!isValidSquare(grid, row, col)) return Integer.MIN_VALUE;

	    if (row == grid.length - 1 && col == grid[0].length - 1) { // the bottom right of the grid
	        memo[row][col] = grid[row][col];
	        return grid[row][col];
        }

        if (memo[row][col] == 0) { // doesn't exist already
            memo[row][col] = grid[row][col] + Math.min(minPath(grid, row, col + 1, memo),
                    minPath(grid, row + 1, col, memo));
        }
	    return memo[row][col];
    }

    /**
	 * A simple Direction enum
	 * directions can be either DOWN or RIGHT
	 * You will output a list of these in the grid-path problem
	 */
	public enum Direction {
		DOWN, RIGHT
	}

	/**
	 * A private Interval class to help with the interval question
	 */
	public static class Interval {
		
		int start;
		int finish;
		
		public Interval(int start, int finish) {
			this.start = start;
			this.finish = finish;
		}
		
		/**
		 * sorts a list of intervals by start time, you are free to use this on the first question
		 */
		public static void sortByStartTime(ArrayList<Interval> l) {
			Collections.sort(l, Comparator.comparingInt(o -> o.start));
		}
		
		/**
		 * sorts a list of intervals by finish time, you are free to use this on the first question
		 */
		public static void sortByFinishTime(ArrayList<Interval> l) {
			Collections.sort(l, Comparator.comparingInt(o -> o.finish));
		}
	}
	
}
