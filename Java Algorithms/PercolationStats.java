import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private double[] results;
    private int trials;
    private double mean1;
    private double stddev;
    private double loConf;
    private double hiConf;
    
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

        mean1 = mean();
        stddev = stddev();
        loConf = confidenceLo();
        hiConf = confidenceHi();
        
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
        return mean1 - (1.96 * stddev / java.lang.Math.sqrt(trials));
    }
    
    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean1 + (1.96 * stddev / java.lang.Math.sqrt(trials));
    }
    
   // test client (see below)
   public static void main(String[] args)
   {
       String n;
       String trials;
       try {
        n = args[0];
        trials = args[1];
       } catch (ArrayIndexOutOfBoundsException e) {
        throw new IllegalArgumentException();
       }

       if (n.charAt(0) == '-' || trials.charAt(0) == '-')
       {
           throw new IllegalArgumentException();
       }
       int n1;
       int trials1;
       try {
        n1 = Integer.parseInt(n);
        trials1 = Integer.parseInt(trials);
       } catch (NumberFormatException e) {
           throw new IllegalArgumentException();
       }
       if (n1 <= 0 || trials1 <= 0 )
       {
           throw new IllegalArgumentException();
       }
       PercolationStats perc = new PercolationStats(n1, trials1);
        System.out.println("mean                    = " + perc.mean1);
        System.out.println("stddev                  = " + perc.stddev);
        System.out.println("95% confidence interval = " + "[" + perc.loConf+ ", " + perc.hiConf + "]");
   }
}
