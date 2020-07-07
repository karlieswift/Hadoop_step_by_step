package com.tay.scala;

/**
 * @author karlieswift
 * date: 2020/6/22 17:32
 * ClassName: Person
 * @version java "13.0.1"
 */
public class Person {
    public static void main(String[] args) throws Exception {




//        new Person().show();//this is java's [Ljava.lang.reflect.Method;@3941a79c
//
        new Person().fun1(10);

    }
    public void show(){
        System.out.println("this is java's "+Person.class.getMethods());
    }


    public void fun1(int n){
        for(int i=1;i<=n;i++){
            int k=i*2-1;
            int j=0;
            for(j=0;j<n-i;j++){
                System.out.print(" ");
            }
            while(k>0){
                System.out.print("*");
                k--;
            }
            System.out.println();
        }

    }


   public void fun(int n){
       for(int i=n;i>0;i--){
           int k=i;
           for(int j=0;j<n-i;j++){
               System.out.print(" ");
           }
           while(k>0){
               System.out.print("* ");
               k--;
           }
           System.out.println();
       }

       for(int i=1;i<=n;i++){
           int k=i;
           int j=0;
           for(j=0;j<n-i;j++){
               System.out.print(" ");
           }
           while(k>0){
               System.out.print("* ");
               k--;
           }
           System.out.println();
       }

   }
}
 