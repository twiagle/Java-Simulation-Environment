package com.twiagle.cacheSys;

/**
 * @author
 * @date 8/5/19-11:08 AM
 */
public class Main {
    //public static int CACHESIZE;
    public static void main(String[] args) {
        //String [] cmd = {"EvaluateInterleavedTestThenTrain","-l","(meta.LearnNSE -p 10000 -c 1000)","-s","(ArffFileStream -f ./data/original52w-unisize-500.arff -c 6) -i 510000 -f 10000"};

        String file = "./data/cdn";
//        String file = "./data/gradle";//147498--0.2M
//        String file = "./data/financial";
//        String file = "./data/zipf";
          int eleNum = 2265489;//CDN 10M
//        int eleNum = 1580299;;//financial1 5.23M
//        int eleNum = 1709742;//wikipedia 10M
//        int eleNum = 147498;//gradel 2M
//        int eleNum = 10000;//zipf  1.8M


        //--------------------//
        String inFileName = file+".tr";
        String testFileName = file+"-test.tr";
        String featureFileName = PrepareData.makeArff(inFileName);
        String decisionFileName  = DoTask.tbDoTask(featureFileName);
        String toCacheFileName = ToCache.run(testFileName, decisionFileName);

        int cs;
        double Percentage = 0;
        for (int i=0;i<8;i++) {
            Percentage = 0.001 + i*0.007;
            cs = (int)(eleNum * Percentage);
            System.out.print(cs+" ");
            Statistic.run(toCacheFileName, cs);
        }
    }
}
