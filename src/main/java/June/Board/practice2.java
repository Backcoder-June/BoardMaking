package June.Board;

import java.util.*;

import static java.util.Arrays.sort;

public class practice2 {


    static class computer implements Comparable{
        int price;
        String computername;
        computer(String compname, int price){
            this.computername = compname;
            this.price = price;
        }

        @Override
        public int compareTo(Object c1){
            return this.price - ((computer)c1).price;}

        public String toString(){
            return computername + " : " + price;
        }




    }






    public static void main(String[] args) {


        List<computer> computers = new ArrayList<>();

        computers.add(new computer("Apple", 120000));
        computers.add(new computer("Samsung", 110000));
        computers.add(new computer("LG", 90000));

        System.out.println(computers.toString());

        Collections.sort(computers);

        System.out.println(computers.toString());







        List<Integer> price = new ArrayList<>();
        price.add(1000);
        price.add(5500);
        price.add(17000);

        System.out.println("담배값은 : "+ price.get(2));

        for (int money: price
             ) {
            System.out.println("지출내역 : "+money);

        }

        Map<Integer, String> kv = new HashMap<>();
        kv.put(1, "1등은 구글");
        kv.put(2, "2등은 네이버");
        kv.put(3, "3등은 다음");

        System.out.println("랭킹결과 "+kv.get(2));

        for (String result:kv.values()
             ) {
            System.out.println(result);

        }























/*
        배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.

        예를 들어 array가 [1, 5, 2, 6, 3, 7, 4], i = 2, j = 5, k = 3이라면

        array의 2번째부터 5번째까지 자르면 [5, 2, 6, 3]입니다.
        1에서 나온 배열을 정렬하면 [2, 3, 5, 6]입니다.
        2에서 나온 배열의 3번째 숫자는 5입니다.
                배열 array, [i, j, k]를 원소로 가진 2차원 배열 commands가 매개변수로 주어질 때,
                 commands의 모든 원소에 대해 앞서 설명한 연산을 적용했을 때 나온 결과를 배열에 담아
                 return 하도록 solution 함수를 작성해주세요.

                제한사항
        array의 길이는 1 이상 100 이하입니다.
                array의 각 원소는 1 이상 100 이하입니다.
                commands의 길이는 1 이상 50 이하입니다.
                commands의 각 원소는 길이가 3입니다.*/








        class Solution2 {
            public int[] solution(int[] array, int[][] commands) {
                int[] answer = {};
                return answer;
            }
        }
















        //~18.5 저체중 / ~25 정상 / ~ 30 과체중 / ~ 비만
        //bmi = k / t^2
        double weight = 80;
        double height = 171;
        double BMI = (weight / ((height*height)/10000));

        if (BMI < 18.5){
            System.out.println("BMI 지수 : "+ String.format("%.2f",BMI) + " 로 \"저체중\"에 해당합니다.");
        } else if (BMI <25){
            System.out.println("BMI 지수 : "+ String.format("%.2f",BMI) + " 로 \"정상\"에 해당합니다.");
        } else if (BMI <30) {
            System.out.println("BMI 지수 : " + String.format("%.2f",BMI) + " 로 \"과체중\"에 해당합니다.");
        } else if (30<=BMI) {
            System.out.println("BMI 지수 : " + String.format("%.2f",BMI) + " 로 \"비만\"에 해당합니다.");
        } else {
            System.out.println("잘못된 수치 입니다. 다시 측정해 주세요.");
        }



        }
}
