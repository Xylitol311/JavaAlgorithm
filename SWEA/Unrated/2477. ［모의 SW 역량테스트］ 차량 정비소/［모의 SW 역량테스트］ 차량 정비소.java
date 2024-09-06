import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * - 접수 창구, 정비 창구, 고객 등 데이터 입력
 * - 접수 창구, 정비 창구, 고객 각각의 클래스를 만들고 리스트로 관리한다.
 * - 작업 순서
 * 1. 데이터 입력
 * 2. 클래스와 리스트를 활용해 고객과 접수 & 정비 창구 저장
 * 		a. 고객 객체 생성 시 다음  작업 시작 시간을 도착 시간으로 입력
 * 		b. 고객 리스트 해당 인덱스에 입력 및 접수 대기 고객 큐에 저장
 * 3. while문을 통해 1초씩 탐색하고 모든 고객의 이용이 끝날 때까지 반복
 * 		while 조건문: 고객 리스트를 탐색하며 한 명이라도 정비가 완료되지 않은 사람이 있다면 내부 구문 실행.
 * 		a. 접수 창구 투입
 * 			1) 완료된 사람 제거(없으면 건너뜀)
 * 				1-1) for문으로 접수 창구 리스트를 순회하면서 이용중인 고객 여부를 확인
 * 				1-2) 고객이 존재하고 고객의 도착시간(접수 종료 시간)이 현재 시간과 일치하면 접수 완료 true, 작업 중 false 처리
 * 				1-3) 고객의 다음 작업 시작(정비 시작 시간)을 도착시간(접수 종료 시간)으로 수정
 * 				1-4) 현재 접수 창구에서 제거 후 정비 대기 고객 큐에 입력
 * 				1-4) 고객이 존재하지 않으면 다음 창구 탐색
 * 			2) 고객 투입
 * 				2-1) 빈 접수 창구가 있는 경우
 * 					2-1-1) 접수 창구 리스트를 순회하며 빈 접수 창구를 탐색
 * 					2-1-2) 빈 접수 창구에 접수 대기 고객 큐에서 가장 먼저 도착한 고객을 뽑아 아래 조건 확인
							- 다음 작업 시작 시간이 현재 시간과 같다면 고객에 접수 창구 번호 입력, 작업 중 true 처리, 
							도착시간(작업 종료 시간)을 접수 시작 시간 + 현 접수 창구 소요 시간으로 수정
							- 다음 작업 시작 시간이 현재 시간보다 크다면 패스
							- 접수 창구에 고객 등록
 * 				2-2) 더 이상 빈 접수 창구가 없는 경우(2-1 이후 반드시 실행)
 * 					2-2-1) 접수가 완료되지 않고 접수 중이지 않은 고객 중 현재 시간 이전에 도착한 고객의 다음 작업 시작 시간을 하나 더한다.
 * 		b. 정비 창구 투입
 * 			1) 완료된 사람 제거(없으면 건너뜀)
 * 				1-1) for문으로 정비 창구 리스트를 순회하면서 이용중인 고객 여부를 확인
 * 				1-2) 고객이 존재하고 고객의 도착시간(정비 종료 시간)이 현재 시간과 일치하면 정비 완료 true, 정비 중 false 처리 후 현재 정비 창구에서 제거
 * 				1-3) 고객이 존재하지 않으면 다음 창구 탐색
 * 			2) 고객 투입
 * 				2-1) 빈 정비 창구가 있는 경우
 * 					2-1-1) 정비 창구 리스트를 순회하며 빈 정비 창구를 탐색
 * 					2-1-2) 빈 정비 창구에 정비 대기 중인 고객 큐에서 가장 먼저 도착한 고객을 뽑아 아래 조건 확인
							- 다음 작업 시작 시간이 현재 시간과 같다면 고객에 정비 창구 번호 입력, 작업 중 true 처리, 도착시간(작업 종료 시간)을 정비 시작 시간 + 현 정비 창구 소요 시간으로 수정
							- 정비 창구에 고객 등록
 * 				2-2) 빈 정비 창구가 없는 경우
 * 					2-2-1) 정비가 완료되지 않고 접수가 완료되었으며 정비 중이지 않은 고객 중 현재 시간 이전에 도착한 고객의 다음 작업 시작 시간을 하나 더한다.
 */
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static class Customer implements Comparator<Customer>{
		int customerNum, arrivedTime, possibleTime, infoDeskNum, repairDeskNum;
		boolean inTask, completeInfo, completeRepair;
		
		public Customer(int customerNum, int arrivedTime) {
			super();
			this.customerNum = customerNum;
			this.arrivedTime = arrivedTime;
			this.possibleTime = arrivedTime;
			this.infoDeskNum = -1;
			this.repairDeskNum = -1;
			this.completeInfo = false;
			this.completeRepair = false;
			this.inTask = false;
		}

		@Override
		public int compare(Customer o1, Customer o2) {
			if (o1.arrivedTime == o2.arrivedTime) {
				return o1.customerNum - o2.customerNum;
			}
			return o1.arrivedTime - o2.arrivedTime;
		}
	}
	
	static class InfoDesk{
		int infoNum, taskTime;
		Customer nowCustomer;

		public InfoDesk(int infoNum, int taskTime) {
			super();
			this.infoNum = infoNum;
			this.taskTime = taskTime;
			this.nowCustomer = null;
		}
	}
	static class RepairDesk{
		int repairNum, taskTime;
		Customer nowCustomer;

		public RepairDesk(int repairNum, int taskTime) {
			super();
			this.repairNum = repairNum;
			this.taskTime = taskTime;
			this.nowCustomer = null;
		}	
	}

	static int infoDeskSize, repairDeskSize, customerSize, customerInfoNum, customerRepairNum;
	static Customer[] customers;
	static InfoDesk[] infoDesks;
	static RepairDesk[] repairDesks;
	static Queue<Customer> waitInfo;
	static Queue<Customer> waitRepair;

	private static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		infoDeskSize = Integer.parseInt(st.nextToken());
		repairDeskSize = Integer.parseInt(st.nextToken());
		customerSize = Integer.parseInt(st.nextToken());
		customerInfoNum = Integer.parseInt(st.nextToken());
		customerRepairNum = Integer.parseInt(st.nextToken());
		
		// collection 초기화
		customers = new Customer[customerSize+1];
		infoDesks = new InfoDesk[infoDeskSize+1];
		repairDesks = new RepairDesk[repairDeskSize+1];
		// 접수 창구 도착 시간이 같은 경우 고객 번호(먼저 온 순서)로 정렬 
		waitInfo = new PriorityQueue<Customer>((customer1, customer2) -> {
			if(customer1.arrivedTime == customer2.arrivedTime) {
				return customer1.customerNum - customer2.customerNum;
			}
			return customer1.arrivedTime-customer2.arrivedTime;
		});
		// 정 창구 도착 시간이 같은 경우 접수 창구 번호 순으로 정렬
		waitRepair =  new PriorityQueue<Customer>((customer1, customer2) -> {
			if(customer1.arrivedTime == customer2.arrivedTime) {
				return customer1.infoDeskNum - customer2.infoDeskNum;
			}
			return customer1.arrivedTime-customer2.arrivedTime;
		});
		
		// 접수 창구 입력
		st = new StringTokenizer(br.readLine());
		for (int idx = 1; idx <= infoDeskSize; idx++) {
			infoDesks[idx] = new InfoDesk(idx, Integer.parseInt(st.nextToken()));
		}
		
		// 정비 창구 입력
		st = new StringTokenizer(br.readLine());
		for (int idx = 1; idx <= repairDeskSize; idx++) {
			repairDesks[idx] = new RepairDesk(idx, Integer.parseInt(st.nextToken()));
		}
		
		// 고객 번호 및 도착 시간 입력
		st = new StringTokenizer(br.readLine());
		for (int idx = 1; idx <= customerSize; idx++) {
			customers[idx] = new Customer(idx, Integer.parseInt(st.nextToken()));
			waitInfo.add(customers[idx]);
		}
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			input();
			sb.append("#").append(tc).append(" ").append(solution()).append("\n");
		}
		System.out.println(sb);
	}
	
	private static boolean isRemainCustomer() {
		for (int idx = 1; idx <= customerSize; idx++) {
			if(!customers[idx].completeRepair) return false;
		}
		return true;
	}
	
	static int time;
	private static int solution() {
		// 모든 고객의 정비가 끝날 때까지 반복
		time = 0;
		while(!isRemainCustomer()) {
			// 접수 창구 투입
			removeCustomerAtInfo();
			addCustomerAtInfo();
			// 정비 창구 투입
			removeCustomerAtRepair();
			addCustomerAtRepair();
			// 시간 추가
			time++;
		}
		
		// 주어진 창구 번호들과 일치하는 고객이 있는지 확인
		int answer = 0;
		for (int idx = 1; idx <= customerSize; idx++) {
			Customer customer = customers[idx];
			if(customer.infoDeskNum == customerInfoNum && customer.repairDeskNum == customerRepairNum)
				answer += customer.customerNum;
		}
		
		return answer == 0 ? -1 : answer;
	}

	private static void removeCustomerAtInfo() {
		for (int idx = 1; idx <= infoDeskSize; idx++) {
			InfoDesk infoDesk = infoDesks[idx];
			// 고객이 존재하는 경우
			if(infoDesk.nowCustomer != null) {
				Customer nowCustomer = infoDesk.nowCustomer;
				// 접수 종료 시간이 현재 시간과 일치하면 접수 완료 처리 후 창구에서 제거 및 정비 대기 큐에 추가
				if (nowCustomer.possibleTime + infoDesk.taskTime == time) {
					nowCustomer.inTask = false; // 작업 표시 제거
					nowCustomer.completeInfo = true; // 접수 완료
					nowCustomer.arrivedTime = time; // 도착시간(정비창구 도착시간=접수 종료 시간)
					nowCustomer.possibleTime = time;
					infoDesk.nowCustomer = null;
					waitRepair.add(nowCustomer);
				}
			}
		}
	}

	private static void addCustomerAtInfo() {
		for (int idx = 1; idx <= infoDeskSize; idx++) {
			// 대기 중인 고객이 없으면 생략
			if (waitInfo.isEmpty()) return;
			
			InfoDesk infoDesk = infoDesks[idx];
			if (infoDesk.nowCustomer == null) {
				Customer nowCustomer = waitInfo.peek();
				if (nowCustomer.possibleTime == time) {
					nowCustomer.infoDeskNum = infoDesk.infoNum;
					nowCustomer.inTask = true;
					infoDesk.nowCustomer = nowCustomer;
					waitInfo.poll();
				}
			}
		}
		for (int idx = 1; idx <= customerSize; idx++) {
			Customer customer = customers[idx];
			if(!customer.completeInfo && !customer.inTask &&customer.arrivedTime <= time)
				customer.possibleTime++;
		}
//		for (Customer customer : waitInfo) {
//			if (customer.arrivedTime <= time) {
//				customer.possibleTime++;
//			}
//		}
	}
	
	private static void removeCustomerAtRepair() {
		for (int idx = 1; idx <= repairDeskSize; idx++) {
			RepairDesk repairDesk = repairDesks[idx];
			// 고객이 존재하는 경우
			if(repairDesk.nowCustomer != null) {
				Customer nowCustomer = repairDesk.nowCustomer;
				// 접수 종료 시간이 현재 시간과 일치하면 정비 완료 처리 후 창구에서 제거
				if (nowCustomer.possibleTime + repairDesk.taskTime == time) {
					nowCustomer.inTask = false; // 작업 표시 제거
					nowCustomer.completeRepair = true; // 접수 완료
					repairDesk.nowCustomer = null;
				}
			}
		}
	}

	private static void addCustomerAtRepair() {
		for (int idx = 1; idx <= repairDeskSize; idx++) {
			// 대기 중인 고객이 없으면 생략
			if (waitRepair.isEmpty()) return;
			
			RepairDesk repairDesk = repairDesks[idx];
			if (repairDesk.nowCustomer == null) {
				Customer nowCustomer = waitRepair.peek();
				if (nowCustomer.possibleTime == time) {
					nowCustomer.repairDeskNum = repairDesk.repairNum;
					nowCustomer.inTask = true;
					repairDesk.nowCustomer = nowCustomer;
					waitRepair.poll();
				}
			}
		}
		
		for (int idx = 1; idx <= customerSize; idx++) {
			Customer customer = customers[idx];
			if (customer.completeInfo && !customer.completeRepair && !customer.inTask && customer.arrivedTime <= time)
				customer.possibleTime++;
		}
		
//		for (Customer customer : waitRepair) {
//			if (customer.arrivedTime <= time) {
//				customer.possibleTime++;
//			}
//		}
	}
}