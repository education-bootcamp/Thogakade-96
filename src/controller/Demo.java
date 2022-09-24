package controller;

public class Demo {
    public static void main(String[] args) {
        int man1=calcPlus("+",10,20);
        int man2=calcPlus("+",10,20,30);
        int man3=calcPlus("+");
    }

    private static int calcPlus(String op,int...params){

        if (op.equalsIgnoreCase("+")){
            int ttl=0;
            for (int i = 0; i < params.length; i++) {
                ttl+=params[i];
            }
            return ttl;
        }
        return 0;
    }

}
