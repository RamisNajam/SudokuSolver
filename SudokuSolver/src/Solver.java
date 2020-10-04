import java.util.ArrayList;

/**
 * Created by Ramis on 2017-08-17.
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
