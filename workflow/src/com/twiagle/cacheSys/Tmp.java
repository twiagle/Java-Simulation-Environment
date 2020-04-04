package com.twiagle.cacheSys;

/**
 * @author tb
 * @date 9/3/19-10:39 AM
 */
public class Tmp {
    public static void main(String[] args) {
        //int eleNum = 2265489;//1000w
        int eleNum = 1580299;;//Financial1
        //int eleNum = 911221;;//Web
        int cs;
        double[] test = new double[]{0.001, 0.0025,0.005, 0.01, 0.025, 0.05};
        for (double i : test) {
            cs = (int)(eleNum * i);
            System.out.print(cs+ " ");
        }
    }
}
