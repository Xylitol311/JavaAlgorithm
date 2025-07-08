/*
 * 1. 장르 우선순위큐(음악, 재생수->번호 순 정렬), 음악(고유번호, 재생 수, 재생수 내림차순으로 비교) 객체 생성
 * 2. 음악 입력
 *      2-1. 
 */
import java.util.*;

public class BestAlbum {
    private class Music implements Comparable<Music> {
        int number;
        int playCnt;

        public Music(int number, int playCnt) {
            this.number = number;
            this.playCnt = playCnt;
        }
        
        @Override
        public int compareTo(Music object) {
            if (this.playCnt != object.playCnt) {
                return Integer.compare(object.playCnt, this.playCnt);
            }
            else {
                return Integer.compare(this.number, object.number);
            }
        }
    }

    private class Genre implements Comparable<Genre> {
        String name;
        int totalPlayCnt;
        PriorityQueue<Music> musics; //해당 장르의 음악들을 저장하는 우선순위 큐

        public Genre(String name) {
            this.name = name;
            this.totalPlayCnt=0;
            this.musics = new PriorityQueue<>();
        }

        @Override
        public int compareTo(Genre o) {
            return Integer.compare(o.totalPlayCnt, this.totalPlayCnt);
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Genre> album = new HashMap<>();

        // 각 장르별 차트를 만들어서 음악 입력(재생수 내림차순, 번호 오름차순)
        for (int i = 0; i < genres.length; i++) {
            Music nowMusic = new Music(i, plays[i]);
            String genreName = genres[i];

            // 해당 장르가 이전에 없었다면 새로운 장르 객체 생성 후 장르 리스트에 추가
            album.putIfAbsent(genreName, new Genre(genreName));
            Genre currentGenre = album.get(genreName);
            currentGenre.totalPlayCnt += nowMusic.playCnt;
            currentGenre.musics.add(nowMusic);
        }

        // 모든 노래 입력이 끝나면 장르 리스트를 장르의 재생수대로 정렬
        List<Genre> sortedGenres = new ArrayList<>(album.values());
        Collections.sort(sortedGenres);

        // 정렬된 장르 리스트를 순회하며 장르별로 최대 두 곡씩 곡의 번호를 answer에 순서대로 입력한다
        List<Integer> answerList = new ArrayList<>();

        for (Genre genre : sortedGenres) {
            int count = 0;

            while (!genre.musics.isEmpty() && count < 2) {
                answerList.add(genre.musics.poll().number);
                count++;
            }
        }
        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }
}
