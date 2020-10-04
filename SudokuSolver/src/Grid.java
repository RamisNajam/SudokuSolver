import static java.lang.Math.floor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * Squares are like top-bottom, left-right. As precedent, I will use this traversal for
 * the data structures

 * index in squares
    0  9 18   27 36 45   54 63 72
    1 10 19   28 37 46   55 64 73
    2 11 20   29 38 47   56 65 74

    3 12 21   30 39 48   57 66 75
    4 13 22   31 40 49   58 67 76
    5 14 23   32 41 50   59 68 77

    6 15 24   33 42 51   60 69 78
    7 16 25   34 43 52   61 70 79
    8 17 26   35 44 53   62 71 80


 *
 *
 * Iterator design pattern for all my iterators??
 *
 */

/**
 * An empty grid that contains 81 squares, 9 boxes, 9 rows, and 9 columns
 */
public class Grid {

  /**
   * This is bad because this is a hard dependency injection. Fix? No its fine because its not a
   * separate class?
   */
  public ArrayList<Box> boxes = new ArrayList<>();
  public ArrayList<Row> rows = new ArrayList<>();
  public ArrayList<Column> columns = new ArrayList<>();
  public ArrayList<Square> squares = new ArrayList<>();
  public ArrayList<Integer> nums = new ArrayList<>();
  //public static Grid solvedGrid = new Grid();
  public CopyOnWriteArrayList<Grid> gridOptions = new CopyOnWriteArrayList<>();

  public Grid() {
    initialize();
  }

  public void initialize() {
    createSquares();
    createBoxes();
    createRows();
    createColumns();
    tellSquaresBox();
    tellSquaresRow();
    tellSquaresColumn();
    tellNumsList();
    gridOptions.add(this);
  }

  public void createSquares() {
    int loc = 1;
    for (int x = 1; x < 10; x++) {
      for (int y = 1; y < 10; y++) {
        Square s = new Square(loc, x, y);
        squares.add(s);
        loc++;
      }
    }
  }

  public Square getSquareLOC(int loc) {
    for (Square s : squares) {
      if (s.getLoc() == loc) {
        return s;
      }
    }
    // This should be handled with exceptions!
    return null;
  }


  /**
   * Check to see this works!
   */
  public void createBoxes() {
    for (int loc = 1; loc < 10; loc++) {
      double z = (double) (loc - 1);
      int s = (int) (3.0 * (z % 3.0) + 27.0 * floor(z / 3.0));
      Square a = squares.get(s);
      s++;
      Square b = squares.get(s);
      s++;
      Square c = squares.get(s);
      s += 7;
      Square d = squares.get(s);
      s++;
      Square e = squares.get(s);
      s++;
      Square f = squares.get(s);
      s += 7;
      Square g = squares.get(s);
      s++;
      Square h = squares.get(s);
      s++;
      Square i = squares.get(s);

      Box box = new Box(loc, a, b, c, d, e, f, g, h, i);
      boxes.add(box);
    }
  }


  public void createRows() {
    for (int loc = 1; loc < 10; loc++) {
      int s = loc - 1;
      Square a = squares.get(s);
      s += 9;
      Square b = squares.get(s);
      s += 9;
      Square c = squares.get(s);
      s += 9;
      Square d = squares.get(s);
      s += 9;
      Square e = squares.get(s);
      s += 9;
      Square f = squares.get(s);
      s += 9;
      Square g = squares.get(s);
      s += 9;
      Square h = squares.get(s);
      s += 9;
      Square i = squares.get(s);

      Row row = new Row(loc, a, b, c, d, e, f, g, h, i);
      rows.add(row);
    }
  }

  public void createColumns() {
    for (int loc = 1; loc < 10; loc++) {
      int s = 9 * (loc - 1);
      Square a = squares.get(s);
      s++;
      Square b = squares.get(s);
      s++;
      Square c = squares.get(s);
      s++;
      Square d = squares.get(s);
      s++;
      Square e = squares.get(s);
      s++;
      Square f = squares.get(s);
      s++;
      Square g = squares.get(s);
      s++;
      Square h = squares.get(s);
      s++;
      Square i = squares.get(s);

      Column col = new Column(loc, a, b, c, d, e, f, g, h, i);
      columns.add(col);
    }
  }

  public void tellSquaresBox() {
    for (Box box : boxes) {
      for (Square s : box.contents) {
        s.setBox(box);
      }
    }
  }

  public void tellSquaresRow() {
    for (Row row : rows) {
      for (Square s : row.contents) {
        s.setRow(row);
      }
    }

  }

  public void tellSquaresColumn() {
    for (Column col : columns) {
      for (Square s : col.contents) {
        s.setCol(col);
      }
    }
  }

  public void tellNumsList() {
    for (Square square : squares) {
      nums.add(square.getValue());
    }
  }

    /*
    Left to do:
    -check if this works with basic grids
    -have something that checks for errors
    -do
    tryValues()
      findBestSquare()
      tryValue()
        check()
     */





    // INITIALIZATIONS ABOVE































  // This was the culprit. Have to clone the grid, not just change the references

  /**
   * Takes the grid, and creates another with the same attributes
   *
   * @param grid Grid to be cloned
   * @return Grid
   */
  public Grid cloneGrid(Grid grid) {
    Grid g = new Grid();
    for (Square s : grid.squares) {
      int loc = s.getLoc();
      Square newSquare = g.getSquareLOC(loc);
      newSquare.setValue(s.getValue());
    }
    return g;
  }


  /**
  // Works only for very simply cases where its a 50/50 guess
  public Grid testValuesStandard(Grid grid) {
    // base case
    if (grid.checkSolved() && grid.works()) {
      solvedGrid = grid;
      return grid;
    }
    else {
      Grid g = cloneGrid(grid);
      Square s = g.findOptimalSquare();
      Iterator it = s.getPossibs().iterator();
      while (it.hasNext()) {
        Grid gg = cloneGrid(g);
        Square ss = gg.getSquareLOC(s.getLoc());
        ss.removePossib(Integer.valueOf((int) it.next()));
        gg.parseGrid();
        return testValuesStandard(gg);
        }
      }
    return solvedGrid;
  }
   */




  /**
  // Useful only with the function below
  public void addOptions(Grid grid) {
     if (grid.checkSolved() & grid.works()) {
       solvedGrid = grid;
     }
  }

  // can probably be taken out
  public Grid testValues(Grid grid) {
    // base case
    if (solvedGrid.checkSolved()) {
      return solvedGrid;
      // inductive step
    } else {
        Square s = grid.findOptimalSquare();
        Iterator it = s.getPossibs().iterator();
        while (it.hasNext()) {
          Grid temp = cloneGrid(grid);
          Square ss = temp.getSquareLOC(s.getLoc());
          temp.squares.remove(Integer.valueOf((int) it.next()));
          temp.parseGrid();
          addOptions(testValues(temp));
        }
      return null;
      }
  }
   */

  // Fix the braces!!!!!
  public void testValuesStandard2() {
    // Start the method. gridOptions has the original grid
    Iterator it = this.gridOptions.iterator();
    // True at the beginning. Should add grids as I go and remove the older ones.
    while (it.hasNext()) {
      // As long as there is a grid (should be true unless there's an error - as I exit within this loop)
      // Get the next grid. Important to know that the copyOnWrite arrayList is working here. It worked in a slightly different implementation
      Grid g = (Grid) it.next();
      // It should work (i.e. no contradictions here)
      if (works(g)) {
        parseGrid(g);
        // Those that do not work are weeded out, and I am now looking at a grid that does not visibly have contradictions
        // Check to see if it is solved!
        if (checkSolved(g)) {
          // If it is solved..............
          // No need for the commented line below!
          //gridOptions.clear();
          // This essentially makes this class' attributes such that they are these.
          this.squares = g.squares;
          this.boxes = g.boxes;
          this.rows = g.rows;
          this.columns = g.columns;
          return;
          //return g;
        } else {
          // If it does not have contradictions and is not solved...
          // Get the optimal square [check to see that this works - it has in previous implementations so I believe it odes here as well]
          Square s = findOptimalSquare(g);
          // Now I am going to run counterfactuals on the optimal square
          for (int possib : s.getPossibs()) {
            // For each possibility, clone a grid. Force a possibility. If it works, add that grid
            Grid gg = cloneGrid(g);
            // Find the square with the location that I originally have
            Square ss = gg.getSquareLOC(s.getLoc());
            // culprit! Removing possib won't do anything - I have to *set* possib
            ss.setValue(possib);
            parseGrid(gg);
            System.out.println(gridOptions.size());
            if (works(gg)) {
              gridOptions.add(gg);
              System.out.println(gridOptions.size());
            }
          }
          // Once that grid's possibs have been forced, I can remove that grid. Intuitively, wherever I force and check values, it should end up working.
          gridOptions.remove(g);
        }
      } else {
        // If it does not work (i.e. error), it should be removed!
        gridOptions.remove(g);
      }
      it = gridOptions.iterator();
    }
    System.out.println("Error! Shouldn't have got here.");
    }

  public Square findOptimalSquare(Grid grid) {
    ArrayList<Square> optimalSquares = new ArrayList<>();
    int best = 9;
    for (Square s : grid.squares) {
      if (!(s.getSolved())) {
        if (s.getNumPossibs() < best) {
          best = s.getNumPossibs();
          optimalSquares.add(s);
        }
      }
    }
    return optimalSquares.get(optimalSquares.size() - 1);
  }



  ////// DELETE THIS AND MAKE THE ORIGINAL THE METHOD SIGNATURE FOR SOLVEGRID /////
  public void solveGrid() {
    testValuesStandard2();
  }






  //// THE METHODS BELOW ARE ALL FOR PARSING GRID
  public void parseGrid(Grid grid) {
    boolean change = true;
    while (change) {
      boolean cs = checkSquares(grid);
      boolean cb = checkHolderObject(grid.boxes);
      boolean cr = checkHolderObject(grid.rows);
      boolean cc = checkHolderObject(grid.columns);
      change = (cs || cb || cr || cc);
    }
  }

  public boolean checkSquares(Grid grid) {
    boolean change = false;
    for (Square s : grid.squares) {
      if (s.getNumPossibs() == (1)) {
        s.setValue(s.getPossibs().get(0));
        change = true;
      }
    }
    return change;
  }

  public boolean checkHolderObject(ArrayList al) {
    boolean change = false;

    Iterator it = al.iterator();
    while (it.hasNext()) {
      HolderObject ho = (HolderObject) it.next();
      Iterator itr = ho.openValues.iterator();
      while (itr.hasNext()) {
        int placementPossibs = 0;
        int value = (int) itr.next();
        Iterator itr2 = ho.contents.iterator();
        while (itr2.hasNext()) {
          Square s = (Square) itr2.next();
          if (s.getPossibs().contains(value)) {
            placementPossibs++;
          }
        }
        if (placementPossibs == 1) {
          change = true;
          Iterator itr3 = ho.contents.iterator();
          while (itr3.hasNext()) {
            Square s = (Square) itr3.next();
            if (s.getPossibs().contains(value)) {
              s.setValue(value);
            }
          }
        }
      }
    }
    return change;
  }

  public String toString() {
      /*
    0  9 18   27 36 45   54 63 72
    1 10 19   28 37 46   55 64 73
    2 11 20   29 38 47   56 65 74

    3 12 21   30 39 48   57 66 75
    4 13 22   31 40 49   58 67 76
    5 14 23   32 41 50   59 68 77

    6 15 24   33 42 51   60 69 78
    7 16 25   34 43 52   61 70 79
    8 17 26   35 44 53   62 71 80
       */
    StringBuilder sb = new StringBuilder();
    int rowIndex = 0;
    for (Row row : rows) {
      if (rowIndex == 3 || rowIndex == 6) {
        sb.append(System.lineSeparator());
      }
      sb.append(row.contents.get(0).getValue());
      sb.append(" ");
      sb.append(row.contents.get(1).getValue());
      sb.append(" ");
      sb.append(row.contents.get(2).getValue());
      sb.append("   ");
      sb.append(row.contents.get(3).getValue());
      sb.append(" ");
      sb.append(row.contents.get(4).getValue());
      sb.append(" ");
      sb.append(row.contents.get(5).getValue());
      sb.append("   ");
      sb.append(row.contents.get(6).getValue());
      sb.append(" ");
      sb.append(row.contents.get(7).getValue());
      sb.append(" ");
      sb.append(row.contents.get(8).getValue());
      sb.append(System.lineSeparator());
      rowIndex++;
    }
    return sb.toString();
  }

  // This always checks this grids' instead of the one in question!!!!!
  public boolean checkSolved(Grid grid) {
    if (works(grid)) {
      for (Square s : grid.squares) {
        if (s.getValue() == 0) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  public boolean works(Grid grid) {
    boolean sq = checkSquaresValid(grid);
    /** the works methods below check if it is solved */
    //boolean ro = checkHolderObjectValid(rows);
    //boolean bo = checkHolderObjectValid(boxes);
    //boolean co = checkHolderObjectValid(columns);
    //return (sq && ro && bo && co);
    // maybe that only the squares need to work? Safe as it is above.
    return sq;
  }

  public boolean checkSquaresValid(Grid grid) {
    for (Square s : grid.squares) {
      if ((s.getValue() == 0) && (s.getNumPossibs() == 0)) {
        return false;
      }
    }
    return true;
  }

  public boolean checkHolderObjectValid(ArrayList hol) {
    ArrayList<Boolean> found = new ArrayList<>();
    for (Object o : hol) {
      HolderObject h = (HolderObject) o;
      for (int value : h.closedValues) {
        for (Square s : h.contents) {
          if (s.getValue() == value) {
            found.add(true);
          }
        }
      }
      ArrayList<Integer> values = new ArrayList<>();
      for (Square ss : h.contents) {
        values.add(ss.getValue());
      }
      if (values.contains(1) && values.contains(2) && values.contains(3) && values.contains(4) && values.contains(5) && values.contains(6) && values.contains(7) && (values.contains(8) && values.contains(9))) {
        found.add(true);
      } else {
        System.out.println("Error HERE!");
        found.add(false);
      }
    }
    return (!found.contains(false));
  }
}