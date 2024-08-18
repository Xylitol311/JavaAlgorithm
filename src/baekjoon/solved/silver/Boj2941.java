package baekjoon.solved.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2941 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String alphabet = br.readLine();
        String[] croBet = new String[]{"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        for (int i = 0; i < croBet.length; i++) {
            if (alphabet.contains(croBet[i])) {
                alphabet = alphabet.replace(croBet[i], "0");
            }
        }
        System.out.println(alphabet.length());
    }
}
