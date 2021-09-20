package com.company;

import java.util.Random;

public class Main {
    public static int roundNubber = 1;
    //ХАР-КА БОССА
    public static  int BossJizn = 2000;
    public static  int BossUron = 100;
    public static String BossDefence = "";

    //ХАР-КИ ГЕРОЕВ
    public static String [] HeroiTipyAtak ={
            "Phyzicheskya", "Magicheskaya" , "Kineticheskaya" , "Medic"};
    public static int[] HeroicheskieHP = {200 , 240 , 300 , 150};
    public static int[] HeroicheskayaAtaka = {45 , 30 , 15 , 0};
    public static void medicHP(){
       //Метод лечения
        Random random = new Random();
        int hp = random.nextInt(40);
        int randomHero = random.nextInt(HeroiTipyAtak.length);
        if (randomHero == 3){
            medicHP();
        }
        if (HeroicheskieHP[randomHero] < 100 && HeroicheskieHP[randomHero] > 0 && HeroicheskieHP[3] > 0);{
            HeroicheskieHP[randomHero]=HeroicheskieHP[randomHero] + hp;
            System.out.println("Лечение: " + hp);
        }
    }

    public static void main(String[] args) {
     printStatistics();
     round();

     while (!isGameFinish()){
         round();
     }
    }

    public static void viborZashityBossa(){
        Random random = new Random();
        int randomIndex = random.nextInt(HeroicheskayaAtaka.length);
        BossDefence = HeroiTipyAtak[randomIndex];
        System.out.println("Босс выбрал тип защиты: " + BossDefence);
    }

    public static boolean isGameFinish(){
        if(BossJizn <= 0){
            System.out.println("Герои Победили! \uD83C\uDF89");
            return  true;
        }
        /*if(HeroicheskieHP[0] <= 0 && HeroicheskieHP[1] <= 0 &&
                HeroicheskieHP[2] <= 0){
            System.out.println("Босс Победил! \uD83D\uDE31");
            return  true;
        }
        return false;*/
        boolean allHeresDead = true;
        for (int i = 0; i < HeroicheskieHP.length; i++) {
            if (HeroicheskieHP[i] > 0){
                allHeresDead = false;
                break;
            }
        }
        if (allHeresDead){
            System.out.println("Босс Победил! \uD83D\uDE31");
        }
        return allHeresDead;
    }

    //Метод постраения раунда
    public static void round () {
        System.out.println("ROUND: " + roundNubber);
        viborZashityBossa();
        UdaryHeroev();
        UdaryBossa();
        medicHP();
        printStatistics();
        roundNubber++;
    }

    //Метод для нанесения урон от Босса
    public static void UdaryBossa (){
        for (int i = 0; i < HeroicheskieHP.length; i++) {
            if(HeroicheskieHP[i] > 0) {
                if (HeroicheskieHP[i] - BossUron < 0){
                    HeroicheskieHP[i] = 0;
                }else {
                    HeroicheskieHP[i] = HeroicheskieHP[i] - BossUron;
                }
            }
        }
    }

    //Метод для нанесения урон от Героев
    public static void UdaryHeroev () {
        Random random = new Random();
        int coeff = random.nextInt(9);
        for (int i = 0; i < HeroicheskayaAtaka.length; i++) {
            if (HeroicheskieHP[i] > 0 && BossJizn > 0) {
                if (HeroiTipyAtak[i] == BossDefence) {
                } else {
                    if (BossJizn - HeroicheskayaAtaka[i] * coeff < 0) {
                        BossJizn = 0;
                    } else {
                        BossJizn = BossJizn - HeroicheskayaAtaka[i] * coeff;
                        System.out.println("Критический урон: " + HeroicheskayaAtaka[i] * coeff);
                    }
                }
            }
        }
    }

    public static void printStatistics(){
        System.out.println("=====================================================");
        System.out.println("Жизнь Босса " + BossJizn + " урон от Босса: " + BossUron);
        System.out.println("                      ⚔️");
        for (int i = 0; i < HeroicheskieHP.length; i++) {
            System.out.println( HeroiTipyAtak[i] + " Жизнь Героя " +
                    HeroicheskieHP[i] +
                    " Урон от Героев: " +
                    HeroicheskayaAtaka[i] );
        }
        System.out.println("======================================================");
    };
}
