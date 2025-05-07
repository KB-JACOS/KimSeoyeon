import java.util.*;

class programmers_120956 {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] canSay = {"aya", "ye", "woo", "ma"};

        for (String word : babbling) {
            
            while (!word.isEmpty()) {
                boolean matched = false;

                for (String say : canSay) {
                    if (word.startsWith(say)) {
                        word = word.substring(say.length());
                        matched = true;
                        break;
                    }
                }

                if (!matched) {
                    break;
                }
            }

            if (word.isEmpty()) {
                answer++;
            }
        }

        return answer;
    }
    /*
    public int solution(String[] babbling) {
        int answer = 0;

        for(String str : babbling) {

            for(String word : new String[] {"aya", "ye", "woo", "ma"}){
                if(str.contains(word)){
                    str = str.replace(word, " ");
                }
            }

            str = str.replace(" ", "");
            if(str.equals("")) answer++;


        }
        return answer;
    }
    */
}
