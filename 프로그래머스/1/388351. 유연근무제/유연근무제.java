class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int n = schedules.length;
        
        // 각 직원별로 체크 시작
        for (int i = 0; i < n; i++) {
            // 출근 인정 시각 계산 (희망 시각 + 10분)
            int deadline = addMinutes(schedules[i], 10);
            
            boolean isOnTime = true;
            
            // 7일 동안 체크
            for (int day = 0; day < 7; day++) {
                int dayOfWeek = getDayOfWeek(startday, day);
                
                // 평일만 체크 (토요일 6, 일요일 7 제외)
                if (isWeekday(dayOfWeek)) {
                    // 실제 출근 시각이 인정 시각보다 늦으면 탈락
                    if (timelogs[i][day] > deadline) {
                        isOnTime = false;
                        break;
                    }
                }
            }
            
            if (isOnTime) {
                answer++;
            }
        }
        
        return answer;
    }
    
    // 특정 날짜의 요일을 반환 (1=월, 2=화, ..., 7=일)
    private int getDayOfWeek(int startday, int day) {
        return (startday + day - 1) % 7 + 1;
    }
    
    // 평일인지 확인 (토요일 6, 일요일 7 제외)
    private boolean isWeekday(int dayOfWeek) {
        return dayOfWeek >= 1 && dayOfWeek <= 5;
    }
    
    // 시각에 분을 더하는 함수
    private int addMinutes(int time, int minutes) {
        int hour = time / 100;
        int min = time % 100;
        
        min += minutes;
        
        // 60분 이상이면 시간 증가
        if (min >= 60) {
            hour += min / 60;
            min = min % 60;
        }
        
        return hour * 100 + min;
    }
}