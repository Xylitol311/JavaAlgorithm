import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static class People {
		int row;
		int col;
		int arrivedTime; // 계단까지 도착하는 시간.
		int possibleGoingDownStairTime; // 실제 계단을 내려갈 수 있는 시간.
		boolean isArrived; // 도착 여부.
		boolean isGoingDown; // 계단을 내려가고 있는지.
		
		public People(int row, int col) {
			this.row = row;
			this.col = col;
			possibleGoingDownStairTime = 0;
            isGoingDown = false;
		}
		
		
		
	}
	
	static class Stair {
		int row;
		int col;
		int length;
		Queue<People> goingDownPeoples;
		
		public Stair(int row, int col, int length) {
			super();
			this.row = row;
			this.col = col;
			this.length = length;
			
			this.goingDownPeoples = new PriorityQueue<>((people1, people2) -> {
				if(people1.arrivedTime > people2.arrivedTime) {
					return 1;
				}
				else if(people1.arrivedTime < people2.arrivedTime) {
					return -1;
				}
				return 0;
			});
		}
	}
	
	static int minDownStairTime;
	static int mapSize;
	static int[][] map;
	static final int PEOPLE = 1, STAIR = 2;
	static List<People> peopleList;
	static List<Stair> stairList;
	private static void inputTestCase() throws IOException{
		minDownStairTime = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		
		map = new int[mapSize][mapSize];
		peopleList = new ArrayList<>();
		stairList = new ArrayList<>();
		for(int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				
				if(map[row][col] == PEOPLE) {
					peopleList.add(new People(row, col));
				}
				else if(map[row][col] >= STAIR) {
					stairList.add(new Stair(row, col, map[row][col]));
				}
			}	
		}
	}
	
	public static void initializedStatus() {
		for (int peopleIndex = 0; peopleIndex < peopleList.size(); peopleIndex++) {
			People people = peopleList.get(peopleIndex);
			people.isArrived = false;
			people.isGoingDown = false;
			
			Stair stair = stairList.get(selectedStairList[peopleIndex]);
			
			people.arrivedTime = Math.abs(people.row - stair.row) + Math.abs(people.col - stair.col);
			people.possibleGoingDownStairTime = people.arrivedTime + 1;
		}
	}
	
	public static boolean isRemainPeople() {
		
		for(People people : peopleList) {
			if(!people.isArrived) {
				return true;
			}
		}
		
		return false;
	}
	
	static final int MAX_PEOPLE_ON_STAIR = 3;
	public static void addPossibleGoingDownPeople() {
		for (int peopleIndex = 0; peopleIndex < peopleList.size(); peopleIndex++) {
			People people = peopleList.get(peopleIndex);
			// 계단을 내려가고 있는가?
			if(people.isGoingDown) {
				continue;
			}
			// 이미 도착했는가?
			if(people.isArrived) {
				continue;
			}
			
			Stair stair = stairList.get(selectedStairList[peopleIndex]);
			
			if(stair.goingDownPeoples.size() < MAX_PEOPLE_ON_STAIR
					&& people.possibleGoingDownStairTime <= currentTime) {
				stair.goingDownPeoples.add(people);
				people.isGoingDown = true;
			}
			else if(people.possibleGoingDownStairTime < currentTime) {
				people.possibleGoingDownStairTime++;
			}
		}
	}
	
	public static void removeArrivedPeople() {
		for (Stair stair : stairList) {
			if(stair.goingDownPeoples.isEmpty()) {
				continue;
			}
			
			int currentGoingDownPeopleCount = stair.goingDownPeoples.size();
			for(int goingDownPeopleIndex = 0; goingDownPeopleIndex < currentGoingDownPeopleCount; goingDownPeopleIndex++) {
				People goingDownPeople = stair.goingDownPeoples.poll();
				
				if (goingDownPeople == null) {
                    break;
                }
				
				if(goingDownPeople.possibleGoingDownStairTime + stair.length > currentTime) {
					stair.goingDownPeoples.add(goingDownPeople);
					continue;
				}
				
				goingDownPeople.isArrived = true;
				
				// 한 명이 나간 즉시 내려가려고 대기중인 사람이 있으면 즉시 투입.
				addPossibleGoingDownPeople();
			}
		}
	}
	
	static int currentTime;
	public static void downStair() {
		currentTime = 0;
		
		while(isRemainPeople()) {
			currentTime++;
			
			// 계단을 내려갈 수 있는 사람이 있으면 추가.
			addPossibleGoingDownPeople();
			
			// 도착을 한 사람이 있으면 제거.
			removeArrivedPeople();
		}
	}
	
	static final int FIRST_STAIR = 0, SECOND_STAIR = 1;
	public static void selectStair(int peopleIndex) {
		// 모든 사람들이 계단을 선택한 경우
		if(peopleIndex == peopleList.size()) {
			// 계단을 내려감
			initializedStatus();
			
			downStair();
			minDownStairTime = Math.min(minDownStairTime, currentTime);
			return;
		}
		
		// 아직 모든 사람들이 계단을 선택하지 못했어요.
		selectedStairList[peopleIndex] = FIRST_STAIR;
		selectStair(peopleIndex + 1);
		
		selectedStairList[peopleIndex] = SECOND_STAIR;
		selectStair(peopleIndex + 1);
	}
	
	static int[] selectedStairList;

	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase; tc++) {
			// 각 테스트 케이스 입력
			inputTestCase();
			
			// 계단을 선택
			selectedStairList = new int[peopleList.size()];
			selectStair(0);
			
			sb.append("#").append(tc).append(" ").append(minDownStairTime).append("\n");
		}
		System.out.println(sb);
	}
}
