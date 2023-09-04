import java.io.*;
import java.util.*;
 
// 21,740kb   134ms
public class Solution {
    static int answer = 0;
    static int sumA, sumB;
    static int m, totalAP;
    static int[] A, B;
 
    // 이동X, 상, 우, 하, 좌
    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 1, 0, -1};
    static Data[][] arr;
    static StringBuilder sb = new StringBuilder();
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxIter = Integer.parseInt(st.nextToken());
        for (int test_case = 1; test_case <= maxIter; test_case++) {
            answer = sumA = sumB = 0;
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            totalAP = Integer.parseInt(st.nextToken());
 
            A = new int[m + 1];
            B = new int[m + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= m; i++)
                A[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= m; i++)
                B[i] = Integer.parseInt(st.nextToken());
 
 
            // 10x10 지도를 저장할 변수, 각 칸에는 AP의 정보가 담겨있음
            // null -> AP 의 영향이 없음
            Queue<Data> Q = new ArrayDeque<>();
            arr = new Data[11][11];
            int x, y;
            for (int i = 0; i < totalAP; i++) {
                st = new StringTokenizer(br.readLine());
                y = Integer.parseInt(st.nextToken());
                x = Integer.parseInt(st.nextToken());
                AP ap = new AP(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i + 1);
                Data data = new Data(x, y);
                Q.add(data);
                data.apList.add(ap);
                arr[x][y] = data;
            }
 
            while (!Q.isEmpty()) {
                Data cur = Q.poll();
                AP ap = cur.apList.get(0);
                int maxDepth = ap.c;
                Queue<Tuple> q = new ArrayDeque<>();
                boolean[][] ok = new boolean[11][11];
//              System.out.println("cur  = " + cur.x + " " + cur.y + " " + maxDepth);
                q.add(new Tuple(cur.x, cur.y, 0));
                ok[cur.x][cur.y] = true;
                while (!q.isEmpty()) {
                    Tuple cur2 = q.poll();
                    if (cur2.depth == maxDepth) {
                        continue;
                    }
                    for (int i = 1; i <= 4; i++) {
                        int nx = cur2.x + dx[i];
                        int ny = cur2.y + dy[i];
                        if (nx < 0 || nx > 10 || ny < 0 || ny > 10 || ok[nx][ny]) continue;
                        ok[nx][ny] = true;
                        if (arr[nx][ny] == null) {
                            arr[nx][ny] = new Data(nx, ny);
                        }
//                      System.out.println(nx + " " + ny);
                        arr[nx][ny].apList.add(ap);
                        q.add(new Tuple(nx, ny, cur2.depth + 1));
                    }
                }
            }
 
            Person personA = new Person(1, 1);
            Person personB = new Person(10, 10);
 
//          System.out.println(arr[10][10].apList);
 
            for (int time = 0; time <= m; time++) {
 
                personA.x += dx[A[time]];
                personA.y += dy[A[time]];
 
                personB.x += dx[B[time]];
                personB.y += dy[B[time]];
//              System.out.println(personA + " " + personB);
 
                if (arr[personA.x][personA.y] != null && arr[personB.x][personB.y] == null) {
 
                    List<AP> list = arr[personA.x][personA.y].apList;
                    int maxCharge = 0;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).p > maxCharge) {
                            maxCharge = list.get(i).p;
                        }
                    }
                    sumA += maxCharge;
 
                } else if (arr[personA.x][personA.y] == null && arr[personB.x][personB.y] != null) {
 
                    List<AP> list = arr[personB.x][personB.y].apList;
                    int maxCharge = 0;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).p > maxCharge) {
                            maxCharge = list.get(i).p;
                        }
                    }
                    sumB += maxCharge;
 
                } else if (arr[personA.x][personA.y] != null && arr[personB.x][personB.y] != null) {
 
                    List<AP> listA = arr[personA.x][personA.y].apList;
                    List<AP> listB = arr[personB.x][personB.y].apList;
                    if (listA.size() == 1 && listB.size() == 1) {
 
                        if (listA.get(0).idx == listB.get(0).idx) {
                            sumA += listA.get(0).p / 2;
                            sumB += listA.get(0).p / 2;
                        } else {
                            sumA += listA.get(0).p;
                            sumB += listB.get(0).p;
                        }
 
                    } else if (listA.size() > 1 && listB.size() == 1) {
 
                        int B_idx = listB.get(0).idx;
                        int maxCharge_A = 0;
                        for (int i = 0; i < listA.size(); i++) {
                            if (listA.get(i).idx == B_idx) continue;
                            if (listA.get(i).p > maxCharge_A) {
                                maxCharge_A = listA.get(i).p;
                            }
                        }
                        sumA += maxCharge_A;
                        sumB += listB.get(0).p;
 
                    } else if (listA.size() == 1 && listB.size() > 1) {
                        int A_dix = listA.get(0).idx;
                        int maxCharge_B = 0;
                        for (int i = 0; i < listB.size(); i++) {
                            if (listB.get(i).idx == A_dix) continue;
                            if (listB.get(i).p > maxCharge_B) {
                                maxCharge_B = listB.get(i).p;
                            }
                        }
                        sumA += listA.get(0).p;
                        sumB += maxCharge_B;
 
                    } else {
//                      int maxCharge_A_idx = 0;
                        int maxCharge_B_p = 0;
                        int maxCharge_A_p = 0;
                        for (int i = 0; i < listA.size(); i++) {
                            int A_idx = listA.get(i).idx;
                            int max_A_p = listA.get(i).p;
                            int max_B_p = 0;
                            for (int j = 0; j < listB.size(); j++) {
                                if (listB.get(j).idx == A_idx) continue;
                                if (listB.get(j).p > max_B_p) {
                                    max_B_p = listB.get(j).p;
                                }
                            }
                            if (maxCharge_A_p + maxCharge_B_p < max_A_p + max_B_p) {
                                maxCharge_A_p = max_A_p;
                                maxCharge_B_p = max_B_p;
                            }
                        }
//                      System.out.println(maxCharge_A_p + " " + maxCharge_B_p);
                        sumA += maxCharge_A_p;
                        sumB += maxCharge_B_p;
                    }
                }
//              System.out.println(time + " " + sumA + " " + sumB);
            }
            answer = sumA + sumB;
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
 
    static class Person {
        int x;
        int y;
 
        public Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
 
    static class Tuple {
        int x;
        int y;
        int depth;
 
        public Tuple(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
 
    static class AP {
        int c;
        int p;
        int idx;
 
        public AP(int c, int p, int idx) {
            this.c = c;
            this.p = p;
            this.idx = idx;
        }
    }
 
    static class Data {
        int x;
        int y;
        List<AP> apList; // 현재 위치에 AP를 관리하는 리스트
 
        public Data(int x, int y) {
            this.x = x;
            this.y = y;
            this.apList = new ArrayList<>();
        }
    }
}