class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        while (!canFit(wallet, bill)) {
            foldBill(bill);
            answer++;
        }
        
        return answer;
    }
    
    // 지폐가 지갑에 들어가는지 확인 (회전 고려)
    private boolean canFit(int[] wallet, int[] bill) {
        int walletMin = Math.min(wallet[0], wallet[1]);
        int walletMax = Math.max(wallet[0], wallet[1]);
        int billMin = Math.min(bill[0], bill[1]);
        int billMax = Math.max(bill[0], bill[1]);
        
        return billMin <= walletMin && billMax <= walletMax;
    }
    
    // 지폐의 긴 쪽을 반으로 접기
    private void foldBill(int[] bill) {
        if (bill[0] > bill[1]) {
            bill[0] /= 2;
        } else {
            bill[1] /= 2;
        }
    }
}