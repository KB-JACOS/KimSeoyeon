import java.util.*;

class programmers_258705 {
    public int solution(int n, int[] tops) {
        
        int[] g = new int[n];
        int[] p = new int[n];
        
        if(tops[0] == 0) {
            g[0] = 2;
            p[0] = 1;
        } else {
            g[0] = 3;
            p[0] = 1;
        }
        
        for(int i = 1; i < n; i++) {
            if(tops[i] == 0) {
                g[i] = (2*g[i-1] + p[i-1]) % 10007;
                p[i] = (g[i-1] + p[i-1]) % 10007;
            } else {
                g[i] = (3*g[i-1] + 2*p[i-1]) % 10007;
                p[i] = (g[i-1] + p[i-1]) % 10007;
            }
            
            // System.out.println(Arrays.toString(g) + " "  + Arrays.toString(p));
        }
        return (g[n-1] + p[n-1]) % 10007;
    }
}
