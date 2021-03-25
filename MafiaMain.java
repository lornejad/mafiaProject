import java.util.*;
public class MafiaMain {
    private static Order order = new Order();
    private static boolean create = false ;
    private static boolean start = false ;
    public static void main (String[] args){
     int max = 100 ;
     String[] name = new String[max];
     int counter_2 = 0;
     Scanner n = new Scanner(System.in);
     while(n.hasNext()){
         String x = n.nextLine();
         int i = sub(x,0);
         if(x.substring(0,i).equals("create_game")){
             create = true ;
             while(i!=-1){
                 if(sub(x,i+1)!=-1)
                     name[counter_2++] = x.substring(i+1, sub(x,i+1));
                 i = sub(x,i+1);
             }
         }else if (x.substring(0,i).equals("assign_role")){
             if(!create)
                 System.out.println("no game created");
             else{
                 if(searchPlayer(x.substring(i+1, sub(x,i+1)),name,counter_2)==-1)
                     System.out.println("user not found");
                 else
                    order.assign_role(name[searchPlayer(x.substring(i+1, sub(x,i+1)),name,counter_2)],x.substring(sub(x,i+1)+1));
             }
         }else if(x.substring(0,i).equals("start_game")){
             if(order.getCounter()!=counter_2)
                 System.out.println("one or more player do not have a role");
             else if(!create)
                 System.out.println("no game created");
             else if(start)
                 System.out.println("game has already started");
             else {
                 for (int j = 0; j <counter_2 ; j++)
                     System.out.println(order.getPlayer()[j]);
                 start = true ;
                 System.out.println("\n"+"Ready? Set! Go.");
                 order.Day();
             }
         }
     }
    }
    public static int searchPlayer(String x,String[] name,int counter_2){
        for (int i = 0; i <counter_2 ; i++)
            if(name[i].equals(x))
                return i ;
        return -1;
    }
    public static int sub(String x, int i){
        for (int j = i;; j++) {
            if(j>=x.length())
                return -1;
            if(j==x.length()-1)
                return j+1;
            if(x.charAt(j)==' ')
                return j;
        }
    }
}
