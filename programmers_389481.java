import java.util.*;

class programmers_389481 {
    public String solution(long n, String[] bans) {        
        long[] nums = new long[bans.length];
        for(int j = 0; j < bans.length; j++) {
            long num = 0;
            int wordSize = bans[j].length();
            
            for(int i = 0; i < wordSize; i++) {                
                num += (bans[j].charAt(i) - 'a' + 1) * (Math.pow(26, wordSize - i - 1));
            }            
            nums[j] = num;
        }
        Arrays.sort(nums);
        
        int cnt = 0;
        for(long num : nums) {
            if(num <= n + cnt) {
                cnt++;
            } else break;       
        }
        n += cnt;
        
        int len = 1;
        long count = 26, base = 0;
        while (n > base + count) {
            base += count;
            count *= 26;
            len++;
        }
        
        long offset = n - base - 1;
        
        char[] word = new char[len];
        for (int i = len - 1; i >= 0; i--) {
            word[i] = (char)('a' + (offset % 26));
            offset /= 26;
        }
        
        return new String(word);
    }
}
