/*
 * A Box object that contains nine boxes
 */

public class Box extends HolderObject {
  public Box(int loc, Square a, Square b, Square c,
      Square d, Square e, Square f,
      Square g, Square h, Square i) {
    super(loc, a, b, c, d, e, f, g, h, i);
  }
}