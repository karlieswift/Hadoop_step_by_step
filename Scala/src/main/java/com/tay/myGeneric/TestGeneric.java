package com.tay.myGeneric;

/**
 * @author karlieswift
 * date: 2020/7/6 17:15
 * ClassName: Test1
 * @version java "13.0.1"
 */
public class TestGeneric {
    public static void main(String[] args) {

        test1(C.class);
        test1(B.class);
//        test1(A.class);//出错
        test2(A.class);
        test2(B.class);
//        test2(C.class);//出错
    }
    public static void test1(Class<? extends B> c){ }
    public static void test2(Class<? super B> c){ }
}
class A{ }
class B extends A{ }
class C extends B{ }