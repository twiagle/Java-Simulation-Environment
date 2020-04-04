
package com.twiagle.cacheSys;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

/**
 * @author tb
 * @date 7/30/19-9:17 AM
 */
public class Statistic {
    public static void run(String args, int cacheSize) {
        String inFileName = args;
        int lruSize = (int) (cacheSize * 0.01);
        int ILSize = cacheSize - lruSize;
        SimpleLRUCache<Long, Double> lruCache = new SimpleLRUCache<>(lruSize);
        SimpleLRUCache<Long, Double> ILcache = new SimpleLRUCache<>(ILSize);
        try (BufferedReader reader = new BufferedReader(new FileReader(inFileName))) {
            String line;
            int seq;
            long id;
            int decision;
            double prediction;
            long hit = 0;
//            long hit1=0;
//            long hit2=0;
            long req = 0;
            while((line = reader.readLine())!=null) {
                SparseLine varibles = new SparseLine(line);
                List<String> v = varibles.getValues();
                Iterator<String> i = v.iterator();
                seq = Integer.parseInt(i.next());
                id = Long.parseLong(i.next());
                decision = Integer.parseInt(i.next());// not cache=0 or cache=1
                prediction = Double.parseDouble(i.next());
                req++;

                if (ILcache.map.containsKey(id)) {//hit
                    hit++;
//                    hit1++;
                    ILcache.get(id);
                }else{//miss
                    if (lruCache.map.containsKey(id)) {
                        hit++;
//                        hit2++;
                        lruCache.get(id);
                        if (decision > 0) {
                            lruCache.remove(id);
                            ILcache.put(id, prediction);
                        }
                    }else{
                        if (decision > 0 ) {
                            ILcache.put(id, prediction);
                        }else{
                            lruCache.put(id, prediction);
                        }

                    }
                }
            }
//            if ((hit1 + hit2)==hit) {
//                System.out.print("yes");
//            }else System.out.print("no");
            System.out.println((double)(hit)/req);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
//gradle
//        1 0.49522311557788945
//        2 0.5984130653266332
//        3 0.7191527638190954
//        4  0.7735341708542713
//        5  0.8080804020100503
//        6  0.8337281407035176

//financial pure IL
//1580 yes0.28554741873804973
//3950 yes0.3017007648183556
//7901 yes0.3093019120458891
//15802 yes0.3149969407265774
//39507 yes0.32141051625239003
//79014 yes0.3263455066921606