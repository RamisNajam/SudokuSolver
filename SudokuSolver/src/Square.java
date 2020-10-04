/*
 * An empty Square class.
 */

import java.util.concurrent.CopyOnWriteArrayList;

public class Square {

  private int value;
  private int x;  // The horizontal location
  private int y;  // The vertical location
  private int loc; // The 1-81 location
  private boolean solved = false;

  public Row row;
  public Column col;
  public Box box;

  public CopyOnWriteArrayList<Integer> possibs = new CopyOnWriteArrayList<>();  // All possible numbers for value

  public Square(int loc, int x, int y) {
    this.x = x;
    this.y = y;
    this.loc = loc;
    populatePossibs();
  }

  /**
   * This method populates the empty square with all possibilities from 1-9.
   */
  private void populatePossibs() {
    for (int i = 1; i < 10; i++) {
      possibs.add(i);
    }
  }

  /**
   * Getter methods below
   */
  public int getValue() {
    return value;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getLoc() {
    return loc;
  }

  public CopyOnWriteArrayList<Integer> getPossibs() {
    return possibs;
  }

  public void setRow(Row row) { this.row = row;}

  public void setCol(Column col) { this.col = col;}

  public void setBox(Box box) { this.box = box;}




  public String toString() {
    String s = "Value: " + String.valueOf(value);
    s += (", x: " + x);
    s += (", y: " + y);
    s += (", Row: " + row.getLoc());
    s += (", Col: " + col.getLoc());
    s += (", Box: " + box.getLoc());
    s += (", Loc: " + getLoc());
    return s;
  }

  public int getNumPossibs() { return possibs.size(); }

  public void removePossib(int value) {
    try {
      possibs.remove(Integer.valueOf(value));
    } catch(Exception e) {
      System.out.println("This ArrayList is empty!");
      e.printStackTrace();
    }
  }

  public void setSolved(boolean c) { solved = c; }

  public boolean getSolved() { return solved; }

  // Should return a bool to make sure that no errors were thrown. Although I have to restructure my code to ensure that that is the case.
  public boolean setValue(int v) {
    if ((box.closedValues.contains(v) || row.closedValues.contains(v) || col.closedValues.contains(v)) && (!(v == 0))) {
      return false;
    } else if (v == 0) {
      this.value = v;
      return true;
    } else {
      this.value = v;
      this.possibs.clear();
      notifyHolderObject(box, v);
      notifyHolderObject(row, v);
      notifyHolderObject(col, v);
      setSolved(true);
      return true;
    }
  }

  public void notifyHolderObject(HolderObject ho, int value) {
    ho.filled++;
    ho.empty--;
    ho.openValues.remove(Integer.valueOf(value));
    ho.closedValues.add(value);
    for (Square s : ho.getContents()) {
      s.removePossib(value);
    }
  }
}

