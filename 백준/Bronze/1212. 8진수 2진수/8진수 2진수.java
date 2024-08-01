import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String octalNum = br.readLine();

        if(octalNum.equals("0")) {
            System.out.println(octalNum);
            return;
        }
        
		String temp;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < octalNum.length(); i++) {
			temp = Character.toString(octalNum.charAt(i));
			int num = Integer.parseInt(temp, 8);
			temp = Integer.toBinaryString(num);
			
			if (temp.length()==1) {
				sb.append("00").append(temp);
				continue;
			}
			if (temp.length()==2) {
				sb.append("0").append(temp);
				continue;
			}
			sb.append(temp);
		}

		String result = sb.toString();
		
		while (result.charAt(0) == '0') {
			result = result.substring(1);
		}

		System.out.println(result);
	}
}
