/*
 * A Row object that contains nine boxes
 */
import java.util.ArrayList;

public class Row extends HolderObject {
  public Row(int loc, Square a, Square b, Square c,
      Square d, Square e, Square f,
      Square g, Square h, Square i) {
    super(loc, a, b, c, d, e, f, g, h, i);
  }
}
