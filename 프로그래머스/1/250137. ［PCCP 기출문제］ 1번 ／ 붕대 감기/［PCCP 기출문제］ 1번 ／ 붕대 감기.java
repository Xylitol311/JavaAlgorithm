class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int castTime = bandage[0];      // 시전 시간
        int healPerSec = bandage[1];    // 초당 회복량
        int bonusHeal = bandage[2];     // 추가 회복량

        int maxHealth = health;
        int currentHealth = health;
        int lastAttackTime = 0;

        for (int[] attack : attacks) {
            int attackTime = attack[0];
            int damage = attack[1];

            // 이전 공격과 현재 공격 사이의 회복 처리
            int timeGap = attackTime - lastAttackTime - 1;
            int totalHeal = (timeGap * healPerSec) + (timeGap / castTime * bonusHeal);
            currentHealth = Math.min(currentHealth + totalHeal, maxHealth);

            // 공격 처리 및 생존 체크
            currentHealth -= damage;
            if (currentHealth <= 0) {
                return -1;
            }

            lastAttackTime = attackTime;
        }
    
        return currentHealth;
    }
}