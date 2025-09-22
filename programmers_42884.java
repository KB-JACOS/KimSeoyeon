import java.io.*;
import java.util.*;

class programmers_42884 {
    public int solution(int[][] routes) {
        int answer = 1;
        
        Arrays.sort(routes, (a, b) -> a[0] - b[0]);
        
        int min = routes[0][1];
        for(int i = 1; i < routes.length; i++) {
            int s = routes[i][0];
            int e = routes[i][1];
            
            if(s <= min) {
                min = Math.min(min, e);
            } else {
                min = e;
                answer++;
            }
        }
        
        // System.out.println(Arrays.deepToString(routes));
        return answer;
    }
}
