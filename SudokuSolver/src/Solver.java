import java.util.ArrayList;

/**
 * Created by Ramis on 2017-08-17.
 
 This is the only class that needs to be accessed. Transcribe the digits of the Sudoku puzzle row by row
 (going left to right) without any spaces. Blank squares are to be inputted as zeroes. This is to be placed in line 25 for the values attribute.
 Next, run the code, and it will output a formatted, solved version of the grid. An example string is
 included there.
 */
public class Solver {

  public static void setGridValues(Grid g, ArrayList<Integer> nums) {
    for (int i = 0; i < 81; i++) {
      Square s = g.squares.get(i);
      int value = nums.get(i);
      s.setValue(value);
    }

  }

  public static void main(String[] args) {
    ArrayList<Integer> nums = new ArrayList<>();

    String values = "10100000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    System.out.println(values.length());

    for (Character c : values.toCharArray()) {
      nums.add(Character.getNumericValue(c));
    }

    Grid g = new Grid();
    setGridValues(g, nums);
    System.out.println("==============BEFORE============");
    System.out.println(g.toString());
    System.out.println();
    System.out.println("==============AFTER=============");
    g.solveGrid();
    System.out.println(g.toString());
  }

}
