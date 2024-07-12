import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();

		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < str.length(); i++) {
			char now = str.charAt(i);
			if(now>=97 && now <= 122) {
				now -= 32;
			} else if(now>=65 && now <= 90){
				now += 32;
			} else {
				System.out.println("입력이 잘못됨");
			}
			sb.append(now);
		}
		System.out.println(sb.toString());
	}
}