package com.java.service;

public class Data {
    public static final int EMPLOYEE = 10;
    public static final int PROGRAMMER = 11;
    public static final int DESIGNER = 12;
    public static final int ARCHITECT = 13;

    public static final int PC = 21;
    public static final int NOTEBOOK = 22;
    public static final int PRINTER = 23;

    //Employee  :  10, id, name, age, salary
    //Programmer:  11, id, name, age, salary
    //Designer  :  12, id, name, age, salary, bonus
    //Architect :  13, id, name, age, salary, bonus, stock
    public static final String[][] EMPLOYEES = {
        {"10", "1", "嬴政", "22", "3000"},
        {"13", "2", "项少龙", "32", "18000", "15000", "2000"},
        {"11", "3", "赵倩", "23", "7000"},
        {"11", "4", "赵雅", "24", "7300"},
        {"12", "5", "连晋", "28", "10000", "5000"},
        {"11", "6", "秦青", "22", "6800"},
        {"12", "7", "琴清", "29", "10800","5200"},
        {"13", "8", "乌廷威", "30", "19800", "15000", "2500"},
        {"12", "9", "善柔", "26", "9800", "5500"},
        {"11", "10", "吕不韦", "21", "6600"},
        {"11", "11", "乌应元", "25", "7100"},
        {"12", "12", "乌廷芳", "27", "9600", "4800"}
    };

    //model表示机器的型号
    //display表示显示器名称

    //PC      :21, model, display
    //NoteBook:22, model, price
    //Printer :23, type, name
    public static final String[][] EQUIPMENTS = {
        {},
        {"22", "联想Y5", "6000"},
        {"21", "宏碁 ", "AT7-N52"},
        {"21", "戴尔", "3800-R33"},
        {"23", "激光", "佳能 2900"},
        {"21", "华硕", "K30BD-21寸"},
        {"21", "海尔", "18-511X 19"},
        {"23", "针式", "爱普生20K"},
        {"22", "惠普m6", "5800"},
        {"21", "联想", "ThinkCentre"},
        {"21", "华硕","KBD-A54M5 "},
        {"22", "惠普m6", "5800"}
    };
}
