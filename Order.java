import java.util.*;
public class Order {
    Scanner n = new Scanner(System.in);
    int max = 100 ;
    Player[] player = new Player[max];
    int counter = 0;
    int dayCounter = 1;
    public void assign_role(String name,String role){
        switch (role){
            case "Joker" : player[counter++]=new Joker(name);break;
            case "Villager" : player[counter++]=new Villager(name);break;
            case "detective" : player[counter++]=new Detective(name);break;
            case "doctor" : player[counter++]=new Doctor(name);break;
            case "bulletproof" : player[counter++]=new BulletProof(name);break;
            case "mafia" : player[counter++]=new Mafia(name);break;
            case "godfather" : player[counter++]=new GodFather(name);break;
            case "silencer" : player[counter++]=new Silencer(name);break;
            default:System.out.println("role not found");
        }
    }

    public Player[] getPlayer() {
        return player;
    }

    public void Day(){
        System.out.println("Day " + dayCounter++);
        String vote = n.nextLine();
        while(!vote.equals("end_vote")) {
            String votee = vote.substring(sub(vote, 0) + 1);
            String voter = vote.substring(0, sub(vote, 0));
            if (!(debug(votee) && debug(voter)))
                System.out.println("user not found");
            else if (searchPlayer(voter).isSilence())
                System.out.println("voter is silenced");
            else if (!searchPlayer(votee).isAlive())
                System.out.println("votee already dead");
            else
                searchPlayer(vote.substring(sub(vote, 0) + 1)).increase_vote_Count();
            vote = n.nextLine();
        }
        Player x = max_player(0);
        boolean die = true ;
        for (int i = 1; i <counter ; i++) {
            if(!x.equals(max_player(i))){
                System.out.println("nobody died");
                die = false ;
                break;
            }
        }
        if(die) {
            if (max_player(0).getRole().equals("Joker"))
                System.out.println("Joker won!");
            else
                System.out.println(max_player(0).getName() + " died");
        }   }

    public Player max_player(int h){
        Player max_player = player[h];
        for (int i = 1; i <counter ; i++) {
            if(player[i].getVote_Count()>max_player.getVote_Count())
                max_player = player[i];
        }
        return max_player;
    }

    public boolean debug(String name){
        for (int i = 0; i <counter ; i++) {
            if(player[i].getName().equals(name))
                return true;
        }
        return false;
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

    public int getCounter() {
        return counter;
    }

    public Player searchPlayer(String name){
        for (int i = 0; i <counter ; i++) {
            if(player[i].getName().equals(name))
                return player[i] ;
        }
        return player[0];
    }
}
