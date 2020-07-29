package com.tay.hive.etl;

/**
 * @author karlieswift
 * date: 2020/6/7 15:35
 * ClassName: EtlUtils
 * @version java "13.0.1"
 */
public class EtlUtils {

    /**
     *  SDNkMu8ZT68	w00dy911	630	People & Blogs	186	10181	3.49	494	257	rjnbgpPJUks udr9sLkoZ0s	3IU1GyX_zio
     *      */

    public static void main(String[] args) {

       // String s="SDNkMu8ZT68\tw00dy911\t630\tPeople & Blogs\t186\t10181\t3.49\t494\t257\trjnbgpPJUks\tudr9sLkoZ0s\t3IU1GyX_zio";
       String s="SDNkMu8ZT68\tw00dy911\t630\tPeople & Blogs\t186\t10181\t3.49\t494\t257";
        String str=etldata(s);
        System.out.println(str);
    }


    public static String etldata(String str){

        StringBuilder sb=new StringBuilder();
        String[] split = str.split("\t");
        if(split.length<9){
            return  null;
        }
        split[3]=split[3].replaceAll(" ","");
        for(int i=0;i<8;i++){
            sb.append(split[i]).append("\t");
        }
        sb.append(split[8]);
        if(split.length>9){
            sb.append("\t");
            for(int i=9;i<=split.length-2;i++){
                sb.append(split[i]).append("&");
            }
            sb.append(split[split.length-1]);
        }

        return sb.toString();
    }
}
 