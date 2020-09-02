import edu.princeton.cs.algs4.*;
public class PercolationStats {
    double[] results;
    int trials;
    
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        this.trials = trials;
        results = new double[trials];
        for (int x = 0; x < trials; x ++)
        {
            Percolation grid = new Percolation(n);
            boolean percFlag = false;
            while (!percFlag)
            {
                int row = StdRandom.uniform(1,n+1);
                int col = StdRandom.uniform(1,n+1);
                grid.open(row, col);
                percFlag = grid.percolates();
            }
            // grid.printGrid();
            // System.out.println(grid.numberOfOpenSites());
            results[x] = (double) grid.numberOfOpenSites() / (n*n);
            // System.out.println(results[x]);
        }
        System.out.println("mean                    = " + mean());
        System.out.println("stddev                  = " + stddev());
        System.out.println("95% confidence interval = " + "[" + confidenceLo() + ", " + confidenceHi() + "]");
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(results);
    }
    
    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(results);
    }
    
    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return mean() - (1.96 * stddev()/ java.lang.Math.sqrt(trials));
    }
    
    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean() + (1.96 * stddev()/ java.lang.Math.sqrt(trials));
    }
    
   // test client (see below)
   public static void main(String[] args)
   {
       PercolationStats trial = new PercolationStats(2000,10);

   }
}
