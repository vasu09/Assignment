package Assignment;

import java.util.*;

public class OscarGuest {

    private static Map<Integer,Map<Integer,Integer>> possibilitiesUtil(int n,Map<Integer,Map<Integer,Integer>> map ){
        //BASE CASE:  1 guest invited
        if(n==1) {
            Map<Integer,Integer> arr=new HashMap<>();
            arr.put(0,0);
            arr.put(1,1);
            map.put(1,arr);
            return map;
        }

        Map<Integer,Integer> singlePersonsMap=map.get(n-1);
        Map<Integer,Integer> nextMap=new HashMap<>();
        for(int i=0;i<singlePersonsMap.size();i++){
            int count=singlePersonsMap.get(i);
            if(singlePersonsMap.get(i)>0){
                if (nextMap.get(i + 1) ==null) {
                    nextMap.put(i+1,count);
                } else {
                    nextMap.put(i + 1, nextMap.get(i + 1) +count);
                }

                if(i-1>=0 ){
                    if(nextMap.get(i-1)==null)
                        nextMap.put(i-1,(i* count));
                    else
                        nextMap.put(i-1,nextMap.get(i -1)+(i* count));
                }
                nextMap.put(i,0);
            }
        }
        map.put(n,nextMap);
        return map;
    }

    private static int possibilities(int n){
        //for each n
        Map<Integer,Map<Integer,Integer>> singlePersonMap =new HashMap<>();
        for(int i=1;i<=n;i++)
            singlePersonMap=possibilitiesUtil(i,singlePersonMap);

        //how many single person going in event v/s total possible combinations of that
        Map<Integer,Integer> singlePersonsMap=singlePersonMap.get(n);
        int result=0;
        System.out.println(singlePersonsMap.toString());
        for(int a:singlePersonsMap.values()){
            result+=a;
        }
        return result;
    }


    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter n");
        System.out.println(possibilities(scanner.nextInt()));
    }
}
