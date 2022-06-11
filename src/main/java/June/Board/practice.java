package June.Board;

import javax.swing.*;

import java.util.Arrays;

import static java.lang.Math.pow;
import static java.lang.Math.random;

class kcalcacl{

     static Double calc (int walk){

        return (walk * 0.02) ;
    }

}


class carEfficiency {

   public static Double Litercalc(Double km, Double gasorlinL){
        return km/gasorlinL;
    }
}

class gathcha {

     static int dice() {

         return (int)(Math.random()*5+1);
     }
     static double dollorchange(int dollor){
         return dollor*1082.25;
     }
}


class dia{

    String name;
    int hp;

    public dia(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }


       void attack(dia enemy){
         int damage = (int)(random() * 10 + 11);
         enemy.hp -= damage;

         System.out.println( this.name+"의 공격으로 "+enemy.name + "의 피가 -"+ damage +"달았습니다. ");
         if (enemy.hp>=1){
         System.out.println("현재 "+enemy.name+"의 남은 hp : "+ enemy.hp);}
         else {
             System.out.println("전투 중 " +this.name+"의 공격으로 "+enemy.name +"이(가) 사망하였습니다.");
         }

    }

     static void sum(){
        System.out.println("sum");
    }

}

class company{
    private company(){
    }


    public static company getc1(){

        company c1 = new company();

        return c1;

    }
}


// 구구단 출력해보자구
class gugu {


    public static void multiple() {

        int aa = 0;
        int bb = 1;
        while (aa < 10) {
            while (bb < 10) {
                System.out.println(aa + " X " + bb + "= " + aa * bb);
                bb++;
            }
        }


        for (int num = 1; num < 10; num++) {
            for (int num2 = 1; num2 < 10; num2++) {
                System.out.println(num + " X " + num2 + "= " + num * num2);

            }

        }


    }

}


class bacteria{







}




















public class practice {

    public static void main(String[] args) {





        int[] vac = new int[10];
        for (int v = 0; v<vac.length; v++){

            vac[v] = (int)Math.pow(2, v);

            System.out.println(vac[v]);
        }









        String[] Fruits = new String[3];
        Fruits[0] = "applte";
        Fruits[1] = "appltbbe";
        Fruits[2] = "appltcce";

        for (String fruut: Fruits) {
            System.out.println(fruut);}

        for(int f = 0; f < Fruits.length; f++){
            System.out.println(Fruits[f]);
        }









//        gugu.multiple();





//        company c1 = new company();

        System.out.println(company.getc1());

        company c1 = company.getc1(); // c1 은 유일한고정값 (Single tone)






        int walknum = 5001;
        double result = kcalcacl.calc(walknum);
        System.out.println("당신이 소모한 칼로리는"+ result + "칼로리 입니다!");


        double km = 182.736;
        double L = 8.68;
        double efficiencyresult = carEfficiency.Litercalc(km, L);
         efficiencyresult = (int)efficiencyresult;
        System.out.println("당신 차는 가솔린 1L 당 약 " + efficiencyresult + "km 를 갈 수있는 연비 입니다.");



        int first = gathcha.dice();
        int second = gathcha.dice();
        int third = gathcha.dice();


        System.out.println(first+"/"+second+"/"+third);



        System.out.println("도박 결과는 " + gathcha.dollorchange(first+second+third)+"원 입니다!" );
        System.out.println("도박 결과는 " + (first+second+third)+"$ 입니다!" );


        dia user1 = new dia("아마존", 35);
        dia enemy1 = new dia("디아블로", 100);



        dia.sum();
        user1.attack(enemy1);
        enemy1.attack(user1);
        user1.attack(enemy1);
        user1.attack(enemy1);

        enemy1.attack(user1);
        if (user1.hp<1){
            System.out.println("아마존이 디아블로의 공격으로 사망했습니다.\n악의 사도의 힘이 더욱 강해집니다.");}
        else if (user1.hp>=1) {
        user1.attack(enemy1);
        user1.attack(enemy1);
        user1.attack(enemy1);}
        if (enemy1.hp>=1){
        user1.attack(enemy1);}
        if (enemy1.hp<1){
            System.out.println("디아블로를 죽여 악의 사도의 힘이 약해졌습니다.");
        }
        else if (enemy1.hp>=1 && user1.hp>=1)          {
            enemy1.attack(user1);
            System.out.println("아마존이 디아블로의 공격으로 사망했습니다.\n악의 사도의 힘이 더욱 강해집니다.");
        }



    }

}

