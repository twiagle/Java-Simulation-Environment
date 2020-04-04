package com.twiagle.cache;

import java.io.BufferedWriter;
import java.util.*;

public class Interval {
    List<Long> requestIdSeq;
    List<Boolean> decision;
    List<long[]> featureList;//respond to requestId
    Map<Long, Integer> counter;//ID-->times
    Set<Long> popularSet;
    public Interval(){
        featureList = new ArrayList<>(PrepareData.INTERVAL);
        requestIdSeq = new ArrayList<>(PrepareData.INTERVAL);
        counter = new HashMap<>();
        popularSet = new HashSet<>();
        decision = new ArrayList<>(PrepareData.INTERVAL);
    }
    public void updateFeature(Feature cacheFeature, long id){
        requestIdSeq.add(id);
        featureList.add(cacheFeature.getFeatures(id).clone());
        if (counter.containsKey(id)) {
            counter.put(id, counter.get(id) + 1);
        }else{
            counter.put(id, 1);
        }
    }
    public ArrayList<Map.Entry<Long, Integer>> sortMap(Map map){
        List<Map.Entry<Long, Integer>> entries = new ArrayList<Map.Entry<Long, Integer>>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Long, Integer>>() {
            public int compare(Map.Entry<Long, Integer> obj1, Map.Entry<Long, Integer> obj2) {
                return Integer.compare(obj2.getValue(), obj1.getValue());// up to down
            }
        });
        return (ArrayList<Map.Entry<Long, Integer>>) entries;
    }

    public Set<Long> findTopPopularObj(ArrayList<Map.Entry<Long, Integer>> entries) {
        int popEle = (int)(entries.size() * PrepareData.cachePrcnt);
        Set set = new HashSet<>(popEle);
        for (Map.Entry<Long, Integer> e : entries.subList(0, popEle)) {
            set.add(e.getKey());
        }
        return set;
    }
    public void judgeClass(){
        popularSet = findTopPopularObj(sortMap(counter));
        for (long e : requestIdSeq) {
            if (popularSet.contains(e)) {
                decision.add(true);
            }else{
                decision.add(false);
            }
        }
    }

    public void printToFile(BufferedWriter out) throws Exception{

        for (int i = 0; i < decision.size(); i++) {
            StringBuilder str = new  StringBuilder();
//            if(featureList.get(i)[0] == 0 ){
//                for (int j = 0; j < featureList.get(i).length; j++) {
//                    str.append(Short.MAX_VALUE).append(" ");
//                }
//            }else{
                for (int j = 0; j < featureList.get(i).length; j++) {
                    str.append(featureList.get(i)[j]).append(" ");
                }
//            }
            str.append(decision.get(i) ? 1 : 0).append("\n");
            out.write(str.toString());
        }
    }

}
