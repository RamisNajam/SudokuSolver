import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

// Abstract class? Make private attributes wherever possible
public class HolderObject {
  public int loc;
  public CopyOnWriteArrayList<Square> contents = new CopyOnWriteArrayList<>();  // The squares in the HolderObject. This is a hard dependency, supply it in the constructor?
  public int filled;
  public int empty = 9;
  public CopyOnWriteArrayList<Integer> openValues = new CopyOnWriteArrayList<>();
  public CopyOnWriteArrayList<Integer> closedValues = new CopyOnWriteArrayList<>();

  public HolderObject(int loc, Square a, Square b, Square c,
      Square d, Square e, Square f,
      Square g, Square h, Square i) {
    this.loc = loc;
    contents.add(a);
    contents.add(b);
    contents.add(c);
    contents.add(d);
    contents.add(e);
    contents.add(f);
    contents.add(g);
    contents.add(h);
    contents.add(i);

    for (int possib = 1; possib < 10; possib++) {
      openValues.add(possib);
    }
  }

  public int getLoc() { return this.loc; }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(loc);
    for (Square s : contents) {
      sb.append(' ');
      sb.append(s.getLoc());
    }
    return sb.toString();
  }

  public CopyOnWriteArrayList<Square> getContents() { return contents; }
}
