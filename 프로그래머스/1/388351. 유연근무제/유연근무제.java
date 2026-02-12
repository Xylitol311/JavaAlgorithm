class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int n = schedules.length;
        
        // 이벤트 시작일자에 따라 일차별 평일/주말 판별
        int saturday = (7 - startday == 0) ? 7 : 7 - startday;
        int sunday = 7 - startday + 1;
        
        // 직원별로 평일 출근 시간을 체크.
        for (int cow = 0; cow < n; cow++){
            // 데드라인
            int deadline = calDeadline(schedules[cow]);
            boolean isWinner = true;
            // 일자별로 근무일인지, 정상출근했는지 확인
            for (int day = 0; day < 7; day++) {
                // 근무일 확인
                if (day + 1 == saturday || day + 1 == sunday) continue;
                
                // 정상 출근 확인
                if (timelogs[cow][day] > deadline) {
                    isWinner = false;
                    break;
                }
            }
            if (isWinner) answer++;
        }
        
        return answer;
    }

    // 정상출근 기한 계산 함수
    private int calDeadline(int setDead) {
        int result = setDead + 10;
        
        return (result % 100 >= 60) ? result + 40 : result;
    }
}