package com.twiagle.cache;

import java.io.*;

/**
 * @author
 * @date 7/19/19-12:48 PM
 */
public class PrepareData{
    public static int INTERVAL = 10000;
    final static String cacheSize = "20%";
    public static double cachePrcnt = 0.2;
    //public static int CACHESIZE;
    public static String makeArff(String traceName){
        //int fileSize = 533;
        StringBuilder traceNameBuilder = new StringBuilder();
        //traceNameBuilder.append("./data/original").append(fileSize).append("w.tr");
        //traceName = traceNameBuilder.toString();
        //CACHESIZE = popObj;
        long req;
        long id;
        boolean ignoreFirstInterval = true;
        Interval interval = new Interval();
        Feature cacheFeature = new Feature();
        StringBuilder outName = new StringBuilder();
        outName.append(traceName.substring(0,traceName.lastIndexOf('.')));
        outName.append("pop").append(PrepareData.cacheSize).append(".arff");
        String dataSetName = outName.toString();
        try(BufferedWriter out = new BufferedWriter(new FileWriter(dataSetName));
            BufferedReader reader = new BufferedReader(new FileReader(traceName))
            ){
                String header = "@relation cache\n" +
                "\n" +
                "@attribute gap1 numeric\n" +
                "@attribute gap2 numeric\n" +
                "@attribute gap3 numeric\n" +
                "@attribute gap4 numeric\n" +
                "@attribute gap5 numeric\n" +
                "@attribute gap6 numeric\n" +
                "@attribute gap7 numeric\n" +
                "@attribute gap8 numeric\n" +
                //        "@attribute gap16 numeric\n" +
                //"@attribute gap9 numeric\n" +
                "@attribute class {0,1}\n" +
                "@data\n";
                out.write(header);

            String line = null;
            int n = 0;
            while ((line = reader.readLine()) != null) {
                int blank = line.indexOf(" ");
                int front = blank + 1;
                int end = line.indexOf(" ", front);
                req = Long.parseLong(line.substring(0,blank));
                id = Long.parseLong(line.substring(front, end));
                cacheFeature.updateMetaObj(id,req);
                interval.updateFeature(cacheFeature, id);
                n++;
                if(n==INTERVAL){
                    interval.judgeClass();

                    //output to file

                    if(ignoreFirstInterval){//donot print first interval 10000
                        ignoreFirstInterval = false;
                        interval = new Interval();
                        n=0;
                    }
                    interval.printToFile(out);
                    interval = new Interval();
                    n=0;
                }
            }
//           test cache.java
            //cache.getLookupTable();
        }catch(Exception ex){
            ex.printStackTrace();
        }
           //test metaObj.java
//           MetaObj obj = new MetaObj(1);
//           for(int i=10;i<321;i+=10){
//               obj.updateFeature(i);
//           }
//           obj.getFeatures();
        return outName.toString();
    }

}
