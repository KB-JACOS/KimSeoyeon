import java.io.*;
import java.util.*;

public class BOJ_14891 {

    static int[][] wheels = new int[4][8];
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 4; i++) {
            String s = br.readLine();
            for(int j = 0; j < 8; j++) {
                wheels[i][j] = s.charAt(j) - '0'; // 1 S, 0 N
            }
        }

        k = Integer.parseInt(br.readLine());
        while(k-- > 0) {
            st = new StringTokenizer(br.readLine());

            int number = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken()); // 1 시계, -1 반시계

            List<Integer> leftList = new ArrayList<>();
            List<Integer> rightList = new ArrayList<>();

            if(number == 1) {
                if(wheels[0][2] != wheels[1][6]) {
                    if(direction == 1) {
                        rightList.add(0);
                        leftList.add(1);
                    } else {
                        leftList.add(0);
                        rightList.add(1);
                    }

                    if(wheels[1][2] != wheels[2][6]) {
                        if(direction == 1) {
                            rightList.add(2);
                        } else {
                            leftList.add(2);
                        }

                        if(wheels[2][2] != wheels[3][6]) {
                            if(direction == 1) {
                                leftList.add(3);
                            } else {
                                rightList.add(3);
                            }
                        }
                    }
                } else {
                    if(direction == 1) {
                        rightList.add(0);
                    } else {
                        leftList.add(0);
                    }
                }
            } else if(number == 2) {
                if(wheels[0][2] != wheels[1][6]) {
                    if(direction == 1) {
                        rightList.add(1);
                        leftList.add(0);
                    } else {
                        leftList.add(1);
                        rightList.add(0);
                    }
                } else {
                    if(direction == 1) {
                        rightList.add(1);
                    } else {
                        leftList.add(1);
                    }
                }

                if(wheels[1][2] != wheels[2][6]) {
                    if(direction == 1) {
                        leftList.add(2);
                    } else {
                        rightList.add(2);
                    }

                    if(wheels[2][2] != wheels[3][6]) {
                        if(direction == 1) {
                            rightList.add(3);
                        } else {
                            leftList.add(3);
                        }
                    }
                }
            } else if(number == 3) {
                if(wheels[2][2] != wheels[3][6]) {
                    if(direction == 1) {
                        rightList.add(2);
                        leftList.add(3);
                    } else {
                        leftList.add(2);
                        rightList.add(3);
                    }
                } else {
                    if(direction == 1) {
                        rightList.add(2);
                    } else {
                        leftList.add(2);
                    }
                }

                if(wheels[1][2] != wheels[2][6]) {
                    if(direction == 1) {
                        leftList.add(1);
                    } else {
                        rightList.add(1);
                    }

                    if(wheels[0][2] != wheels[1][6]) {
                        if(direction == 1) {
                            rightList.add(0);
                        } else {
                            leftList.add(0);
                        }
                    }
                }
            } else if(number == 4) {
                if(wheels[2][2] != wheels[3][6]) {
                    if(direction == 1) {
                        rightList.add(3);
                        leftList.add(2);
                    } else {
                        leftList.add(3);
                        rightList.add(2);
                    }

                    if(wheels[1][2] != wheels[2][6]) {
                        if(direction == 1) {
                            rightList.add(1);
                        } else {
                            leftList.add(1);
                        }

                        if(wheels[0][2] != wheels[1][6]) {
                            if(direction == 1) {
                                leftList.add(0);
                            } else {
                                rightList.add(0);
                            }
                        }
                    }
                } else {
                    if(direction == 1) {
                        rightList.add(3);
                    } else {
                        leftList.add(3);
                    }
                }
            }

            for(int i = 0; i < leftList.size(); i++) left(leftList.get(i));
            for(int i = 0; i < rightList.size(); i++) right(rightList.get(i));

            leftList.clear();
            rightList.clear();
        }

        int answer = wheels[0][0] + (wheels[1][0]*2) + (wheels[2][0]*4) + (wheels[3][0]*8);
        System.out.println(answer);
    }

    // 반시계 이동
    public static void left(int idx) {
//        System.out.println("변경 전 = " + Arrays.deepToString(wheels));
        System.out.printf("%d회차에 %d번 바퀴 반시계입니다.\n", k+1, idx+1);

        int[] copy = new int[8];
        for(int i = 0; i < 7; i++) {
            copy[i] = wheels[idx][i+1];
        }
        copy[7] = wheels[idx][0];

        wheels[idx] = copy;
//        System.out.println("변경 후 = " + Arrays.deepToString(wheels));
    }

    // 시계 이동
    public static void right(int idx) {
//        System.out.println("변경 전 = " + Arrays.deepToString(wheels));
        System.out.printf("%d회차에 %d번 바퀴 시계입니다.\n", k+1, idx+1);

        int[] copy = new int[8];
        for(int i = 1; i < 8; i++) {
            copy[i] = wheels[idx][i-1];
        }
        copy[0] = wheels[idx][7];

        wheels[idx] = copy;
//        System.out.println("변경 후 = " + Arrays.deepToString(wheels));
    }
}
