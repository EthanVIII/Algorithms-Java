import javax.swing.text.rtf.RTFEditorKit;

import edu.princeton.cs.algs4.*;

public class Percolation{
    int sideLen;
    boolean[] open;
    boolean[] full;
    WeightedQuickUnionUF grid;
    int top;
    int bot;
    

    // creates n-by-n grid, with all sites initially blocked.
    public Percolation(int n)
    {
        this.sideLen = n;
        this.num = n*n;
        this.open = new boolean[num + 2];
        this.full = new boolean[num + 2];
        this.grid = new WeightedQuickUnionUF(num + 2);

        this.top = num;
        this.open[top] = true;
        this.bot = num + 1;
        this.open[bot] = true;
        for (int x = 0; x < n; x++)
        {
            grid.union(x, top);
        }

        for (int x = num-1; x > num - 5; x--)
        {
            grid.union(x, bot);
        }
        

        for (int x = 0; x < num; x++)
        {
            this.open[x] = false;
        }

        // ? Unsure if will need full?
        // for (int x = 0; x < this.open.length; x++)
        // {
        //     this.full[x] = false;
        // }
    }

    public void testBounds(int row, int col)
    {
        if (row >= this.sideLen || col >= this.sideLen || row < 0 || col < 0)
        {
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        row -= 1;
        col -= 1;
        testBounds(row, col);
        int index = row * col;
        if (!this.open[index]) {
            this.open[index] = true;
        }
    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row -= 1;
        col -= 1;
        testBounds(row, col);
        int index = row * col;
        return this.open[index];
    }

    // ? Unsure if needed.
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
         row -= 1;
         col -= 1;
        testBounds(row, col);
        int index = row * col;
        // return this.full[index];
        return false;
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
        connectOpens();
        return this.grid.connected(top, bot);
    }

    // TODO connect all open elements.
    // ! Prevent rolling over to the next row.
    public void connectOpens() {
        for (int x = 0; x < this.num; x++)
        {
            if (open[x])
            {
                // +sidelen, -sidelen, +1, -1
                
            }
        }
    }

    public static void main(String[] args) {
        Percolation test1 = new Percolation(3);
        System.out.println("(Done Nothing): Connected?: " + test1.percolates());
        
    }
}