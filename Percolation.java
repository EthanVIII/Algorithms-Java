
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int sideLen;
    private boolean[] open;
    private WeightedQuickUnionUF grid;
    private int num;
    private int top;
    private int bot;

    // creates n-by-n grid, with all sites initially blocked.
    public Percolation(int n) {
        this.sideLen = n;
        this.num = n * n;
        this.open = new boolean[num];
        this.grid = new WeightedQuickUnionUF(num);
        this.top = 0;    
        this.bot = num-1;
    
        for (int x = 0; x < n; x++) {
            grid.union(x, top);
        }

        for (int x = num - 1; x > num - n - 1; x--) {
            grid.union(x, bot );
        }

        for (int x = 0; x < num; x++) {
            this.open[x] = false;
        }
    }

    private void testBounds(int row, int col) {
        if (row > this.sideLen || col > this.sideLen || row < 0 || col < 0) {
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        row -= 1;
        col -= 1;
        testBounds(row, col);
        int index = (row * sideLen) + col;

        if (!this.open[index]) {
            this.open[index] = true;

            boolean topFlag = (row == 0);
            boolean botFlag = (row == sideLen-1);
            boolean rFlag = (col == sideLen-1);
            boolean lFlag = (col == 0);

            if (!topFlag) {
                if (this.open[index - sideLen]) {
                    grid.union(index, index - sideLen);
                }
            }
            if (!botFlag) {
                if (this.open[index + sideLen]) {
                    grid.union(index, index + sideLen);
                }
            }
            if (!rFlag) {
                if (this.open[index + 1]) {
                    grid.union(index, index + 1);
                }
            }
            if (!lFlag) {
                if (this.open[index - 1]) {
                    grid.union(index, index - 1);
                }
            }
        }
    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row -= 1;
        col -= 1;
        testBounds(row, col);
        int index = (row * sideLen) + col;
        return this.open[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        row -= 1;
        col -= 1;
        testBounds(row, col);
        int index = (row * sideLen) + col;
        return (((Integer) this.grid.find(top)).equals(this.grid.find(index)) && open[index]);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int counter = 0;
        for (int x = 0; x < this.open.length; x++) {
            if (this.open[x]) {
                counter++;
            }
        }
        return counter;
    }

    // does the system percolate?
    public boolean percolates() {
        return ((Integer) this.grid.find(top)).equals(this.grid.find(bot));
        
    }

    private void printGrid() {
        System.out.println("-----------------------------");
        String constString = "";
        for (int row = 0; row < sideLen; row++) {
            for (int col = 0; col < sideLen; col++) {
                if (open[col + (row * sideLen)]) {
                    constString += "+ ";
                } else {
                    constString += "0 ";
                }
            }
            System.out.println(constString);
            constString = "";
        }
        System.out.println("-----------------------------");
    }

    public static void main(String[] args) {
        Percolation test1 = new Percolation(10);
        System.out.println(test1.grid.count());
        System.out.println(test1.percolates());
        for (int x = 1; x <= 10; x++) {
            test1.open(x, 1);
        }
        test1.printGrid();
        System.out.println(test1.percolates());
        System.out.println(test1.isFull(1, 1));
        System.out.println(test1.isFull(5, 5));
        test1.open(5,5);
        test1.printGrid();
        System.out.println(test1.isFull(5, 1));
        test1.testBounds(1, 5);
    }

}