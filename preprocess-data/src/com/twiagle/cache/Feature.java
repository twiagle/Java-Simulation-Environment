package com.twiagle.cache;
import java.util.*;

/**
 * @author
 * @date 7/19/19-1:42 PM
 */
public class Feature {

    Map<Long, MetaObj> lookupTable;

    public Feature() {
        lookupTable = new HashMap<>();
    }

    public void updateMetaObj(long id, long req) {
        MetaObj obj = lookupTable.get(id);
        if(obj != null){
            obj.updateFeature(req);
        }else{
            lookupTable.put(id, new MetaObj(id,req));
        }
    }

    public void getLookupTable(){
        for (Map.Entry<Long, MetaObj> entry : lookupTable.entrySet()) {
            System.out.println(entry.getKey() + ":");
            System.out.println(entry.getValue().getFeatures().toString());
        }
    }
    public long[] getFeatures(long id) {
        return lookupTable.get(id).getFeatures();
    }
    private class MetaObj {
        //features
        final int FEATURENUM = 8;
        //final int MAXGAP = 16;
        final int[] gaps = {1,2,3,4,5,6,7,8};
        long[] features;//coresponding to gap
        //features

        long id;
        CycQueue preReqSeq;
        MetaObj(long id,long req){
            this.id = id;
            features = new long[FEATURENUM];
            for (int i = 0; i < features.length; i++) {
                features[i] = Integer.MAX_VALUE;
            }
            preReqSeq = new CycQueue();
            preReqSeq.EnQueue(req);
        }

        void updateFeature(long reqSqe) {
            for (int i = 0; i < gaps.length; i++) {
                long tmp = preReqSeq.getLastElement(gaps[i]);

                if(tmp != Long.MAX_VALUE){
                    features[i] = reqSqe - tmp;
                }else {
                    features[i] = Integer.MAX_VALUE;
                }
            }
            preReqSeq.EnQueue(reqSqe);
        }

         long[] getFeatures() {
            return features;
        }
    }
}
