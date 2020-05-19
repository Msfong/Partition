import java.util.ArrayList;
import java.util.Arrays;
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
//    	"P1","P2","X11","X12","X21","X22", "X31", "X32"
        for (List<List<String>> partitions :
        		partitions(Arrays.asList("a","b","c","d"))) {
            System.out.println(partitions);
//        	count++;
            for(int i = 0; i < partitions.size(); i++) {
	                if (partitions.size() <= 2) {
//	                	System.out.println(partitions);
	                    System.out.println(partitions.size());
	                    System.out.println(partitions.get(i));
	                	count++;
	                 }
              }
        }   
        System.out.println("Total partition:"+count);
    }
}
