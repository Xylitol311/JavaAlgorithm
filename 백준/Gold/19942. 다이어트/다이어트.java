import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    static class Food {
        int mp;
        int mf;
        int ms;
        int mv;
        int price;

        Food(int a, int b, int c, int d, int e) {
            mp = a;
            mf = b;
            ms = c;
            mv = d;
            price = e;
        }

        Food(String[] splitedLine) {
            mp = stoi(splitedLine[0]);
            mf = stoi(splitedLine[1]);
            ms = stoi(splitedLine[2]);
            mv = stoi(splitedLine[3]);
            price = stoi(splitedLine[4]);
        }

        public void add(Food other) {
            mp += other.mp;
            mf += other.mf;
            ms += other.ms;
            mv += other.mv;
            price += other.price;
        }

        public Food minus(Food other) {
            return new Food(this.mp - other.mp, this.mf - other.mf, this.ms - other.ms, this.mv - other.mv,
                    this.price - other.price);
        }
    }

    static Food[] foods;

    static int N, minValue;
    static Food target;
    static Set<Integer> set;
    static List<String> list;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = stoi(in.readLine());
        set = new HashSet<>();
        list = new ArrayList<>();
        String[] splitedLine = in.readLine().split(" ");
        target = new Food(stoi(splitedLine[0]), stoi(splitedLine[1]), stoi(splitedLine[2]), stoi(splitedLine[3]), 0);

        foods = new Food[N + 1];
        Food sum = new Food(0, 0, 0, 0, 0);
        minValue = Integer.MAX_VALUE;
        for (int i = 1; i <= N; ++i) {
            foods[i] = new Food(in.readLine().split(" "));
            sum.add(foods[i]);
            set.add(i);
        }

        // 시뮬레이션을 해보자.
        simulation(sum, 1);

        // 만족시키는 조합이 있었다면
        if (list.size() > 0) {
            System.out.println(minValue); // 가격
            Collections.sort(list); // 정렬을 통한 조합 확인
            String s = list.get(0);
            for (int i = 0; i < s.length(); i++) {
                System.out.print(s.charAt(i));
            }
        } else
            System.out.println(-1);
    }

    private static void simulation(Food sum, int depth) {
        if (depth > N) {
            if (check(sum, target)) {
                if (sum.price <= minValue) {
                    if (minValue > sum.price)
                        list.clear(); // 기존 보다 저렴한 가격이다. 기존의 조합을 전부 버리자.
                    String str = "";
                    // 조건을 만족하므로, 조합을 기록해두자.
                    for (int i : set) {
                        str += Integer.toString(i);
                        str += " ";
                    }
                    list.add(str);
                    minValue = sum.price;
                }
            }
            return;
        }

        // i번째 food를 제외시켜 본다.
        set.remove(depth);
        simulation(sum.minus(foods[depth]), depth + 1);
        set.add(depth);

        // i번째 food를 제외하지 않는다.
        simulation(sum, depth + 1);
    }

    private static boolean check(Food sum, Food target) {
        if (sum.mp >= target.mp && sum.mf >= target.mf && sum.ms >= target.ms && sum.mv >= target.mv)
            return true;
        return false;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}