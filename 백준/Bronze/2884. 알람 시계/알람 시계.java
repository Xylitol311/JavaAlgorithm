import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int time = sc.nextInt();
		int min = sc.nextInt();
		
		int timeToMin = time*60 + min ;
		
		int result = timeToMin - 45;
		
		int resultTime = result/60 ;
		int resultMin = result%60 ;
		
		if (result > 0) {
			System.out.println(resultTime+" "+resultMin);
		}else if (result ==0) {
			System.out.println(0+" "+0);
		}else {
			System.out.println(23+" "+(60-(45-min)));
		}
		}
	}
