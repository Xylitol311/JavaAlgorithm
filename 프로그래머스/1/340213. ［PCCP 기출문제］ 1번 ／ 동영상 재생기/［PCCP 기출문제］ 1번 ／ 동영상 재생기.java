import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        // 모든 시간을 초 단위로 변환
        int videoLength = timeToSeconds(video_len);
        int currentPos = timeToSeconds(pos);
        int opStart = timeToSeconds(op_start);
        int opEnd = timeToSeconds(op_end);
        
        // 초기 위치가 오프닝 구간이면 건너뛰기
        currentPos = skipOpening(currentPos, opStart, opEnd);
        
        // 각 명령 처리
        for (String command : commands) {
            if (command.equals("prev")) {
                currentPos = Math.max(0, currentPos - 10);
            } else if (command.equals("next")) {
                currentPos = Math.min(videoLength, currentPos + 10);
            }
            
            // 명령 실행 후 오프닝 구간 체크
            currentPos = skipOpening(currentPos, opStart, opEnd);
        }
        
        return secondsToTime(currentPos);
    }
    
    // "mm:ss" 형식의 시간을 초 단위로 변환
    private int timeToSeconds(String time) {
        String[] parts = time.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return minutes * 60 + seconds;
    }
    
    // 초 단위 시간을 "mm:ss" 형식으로 변환
    private String secondsToTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }
    
    // 오프닝 구간이면 오프닝 끝으로 이동
    private int skipOpening(int currentPos, int opStart, int opEnd) {
        if (currentPos >= opStart && currentPos <= opEnd) {
            return opEnd;
        }
        return currentPos;
    }
}