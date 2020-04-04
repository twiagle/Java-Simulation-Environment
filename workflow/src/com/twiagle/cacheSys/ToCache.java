package com.twiagle.cacheSys;

import java.io.*;

/**
 * @author tb
 * @date 7/30/19-9:27 AM
 * get seq ID form trace  xx.tr, get decision prediction from decision xx.decision
 */
public class ToCache {
    public static String run(String dataInName, String decisionInName) {
        //String dataInName = "./data/original52w-unisize.tr";
        //String decisionInName = args;
        StringBuilder outFile = new StringBuilder();
        outFile.append(decisionInName.substring(0,decisionInName.lastIndexOf('.'))).append(".tocache");
        String outFileName = outFile.toString();
        try (BufferedReader dataIn = new BufferedReader(new FileReader(dataInName));
             BufferedReader deciIn = new BufferedReader(new FileReader(decisionInName));
             BufferedWriter out = new BufferedWriter(new FileWriter(outFileName))){

            String inLineData;
            String inLineDeci;
            while ((inLineData = dataIn.readLine()) != null &&
                    (inLineDeci = deciIn.readLine())!= null) {  // exclude newline
                StringBuilder outLine = new StringBuilder();
                outLine.append(inLineData.substring(0, inLineData.lastIndexOf(' '))).append(' ')
                        .append(inLineDeci.substring(inLineDeci.indexOf(' ')+1));
                outLine.append("\n");
                out.write(outLine.toString());
                //System.out.println(outLine);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return outFileName;
    }
}
