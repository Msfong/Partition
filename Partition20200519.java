package forkoya;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Partition {
	
    private static List<List<List<String>>> partitions(List<String> inputSet) {
    	
        List<List<List<String>>> pset = new ArrayList<>();
        if (inputSet.isEmpty()) {
        	
            List<List<String>> empty = new ArrayList<>();
            pset.add(empty);
            return pset;
        }
        
        int limit = 1 << (inputSet.size() - 1);
        
        for (int j = 0; j < limit; j++) {
        	//List<List<String>>で繰り返し処理をするごとに新しい要素を作る
        	
            List<List<String>> parts = new ArrayList<>();
            List<String> part1 = new ArrayList<>();
            List<String> part2 = new ArrayList<>();
            parts.add(part1);
            parts.add(part2);
            int i = j;
            for (String item : inputSet) {
                parts.get(i&1).add(item);
                i >>= 1;
            }
            
            for (List<List<String>> b : partitions(part2)) {
                List<List<String>> pset2 = new ArrayList<>();
                pset2.add(part1);
                pset2.addAll(b);
                pset.add(pset2);
            }
        }
        return pset;
    }
    
    public static void main(String[] args) {
    	int count = 0;
    	
    	System.out.println("All Partitions of [P11,P2,X11,X12,X21,X22,X31,X32] are:");
        for (List<List<String>> Set :
    		partitions(Arrays.asList("P1","P12","X11","X22","X33"))) {
        	boolean flg=true;
        	
	       for(List<String> partitions: Set){
	    	 //①条件1:ショート端子が存在する
			   	if (partitions.size() < 2) { 
				   	flg=false;
			       	continue;
		    	}	
			  //②条件2:全ての要素が同じタイプであれば除外
			   	boolean issametype =false;
			   	String header =partitions.get(0).substring(0, 1);
			   	
			   	for(int i=1;i<partitions.size();i++){
			   		String p=partitions.get(i).substring(0, 1);
			   		if(p.equals(header)){
			   			issametype=true;
			   		}
			   		else{
			   			issametype=false;
			   		}
			   	}
			   	
			   	if(issametype){
			   		flg=false;
			       	continue;
			   	}
	       }
	      
	       	if(flg){
//	       		subset.add(Set);
	       		count++;
	       		System.out.println(Set);
	        }
	       
        } 
        System.out.println("Total partition:"+count);
    }
    
    //以下は使用しない
    /**
     * 二つのリストの要素比較
     * @param p1
     * @param p2
     * @return：(true:同じ、false:異なる)
     */
    public boolean comparelist1(List<String> p1, List<String> p2){
    	if(p1.size() !=p2.size()){
    		return false;
    	}
    	for(int i=0;i<p1.size();i++){
    		if(p1.get(i) !=p2.get(i)){
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * 二つのリストのサイズ比較
     * @param p1
     * @param p2
     * @return：(true:同じ、false:異なる)
     */
    public List<String> comparelistSize(List<String> p1, List<String> p2){
    	if(p1.size() < p2.size()){
    		return p1;
    	}
    	return p2;
    }
}
