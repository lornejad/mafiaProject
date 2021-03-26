import jdk.nashorn.internal.scripts.JO;

import java.util.*;
public class Order {
    boolean end_game = false;
    Scanner n = new Scanner(System.in);
    int max = 100 ;
    Player[] player = new Player[max];
    int counter = 0;
    int dayCounter = 1;
    int nightCounter = 1;
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
        cal_state();
        if(dayCounter==1)
            System.out.println("Day " + dayCounter++);
        String vote = n.nextLine();
        while(!vote.equals("end_vote")) {
            if(vote.equals("get_game_state"))
                get_game_state();
            String votee = vote.substring(sub(vote, 0) + 1);
            String voter = vote.substring(0, sub(vote, 0));
            if (!(debug(votee) && debug(voter)))
                System.out.println("user not found");
            else if (searchPlayer(voter).isSilence())
                System.out.println("voter is silenced");
            else if (!searchPlayer(votee).isAlive())
                System.out.println("votee already dead");
            else if (!searchPlayer(voter).isAlive())
                System.out.println("voter dead");
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
            if (max_player(0).getRole().equals("Joker")){
                System.out.println("Joker won!");
                System.exit(0);
                end_game = true;
            }
            else{
                System.out.println(max_player(0).getName() + " died");
                max_player(0).setAlive(false);
            }
        }
        for (int i = 0; i <counter ; i++){
            player[i].resetVote_Count();
            player[i].resetSilence();
            Player a = player[i];
            if(a instanceof Silencer)
                ((Silencer) a).end_Day();
        }

        if(!end_game)
            night();
    }

    public void night(){
        cal_state();
        System.out.println("night " + nightCounter++);
        String heal ="";
        String silence ="";
        for (int i = 0; i <counter ; i++)
            if(player[i].isWakeup() && player[i].isAlive())
                System.out.println(player[i]);
        String vote = n.nextLine();
        while(!vote.equals("end_night")) {
            if(vote.equals("get_game_state"))
                get_game_state();
            else{
                String second = vote.substring(sub(vote, 0) + 1);
                String first = vote.substring(0, sub(vote, 0));
                if(!searchPlayer(first).isWakeup())
                    System.out.println("user can not wake up during night");
                else if(!searchPlayer(first).isAlive()||!searchPlayer(second).isAlive())
                    System.out.println("user is dead");
                else if(!(debug(first)&&debug(second)))
                    System.out.println("user not joined");
                else{
                    if(!searchPlayer(second).isAlive())
                        System.out.println("votee already dead");
                    else if(searchPlayer(first) instanceof Mafia){
                        if((searchPlayer(first) instanceof Silencer) && !((Silencer) searchPlayer(first)).isDo_silence()){
                            ((Silencer) searchPlayer(first)).do_silence();
                            ((Silencer) searchPlayer(first)).setSilence(second);
                            searchPlayer(second).setSilence();
                            silence = second ;
                        }else
                            ((Mafia) searchPlayer(first)).setWhoDie(second);
                    }else if (searchPlayer(first) instanceof Doctor){
                        ((Doctor) searchPlayer(first)).setHealName(second);
                        heal  = second ;
                    }
                    else if(searchPlayer(first) instanceof Detective){
                        if(!searchPlayer(second).isAlive())
                            System.out.println("suspect is dead");
                        else{
                            if(((Detective) searchPlayer(first)).isDetect()){
                                System.out.println("detective has already asked");
                            }else{
                                ((Detective) searchPlayer(first)).Detect();
                                if(searchPlayer(second) instanceof Mafia){
                                    if(searchPlayer(second).getRole().equals("GodFather")){
                                        System.out.println("NO");
                                    }else
                                        System.out.println("YES");
                                }else
                                    System.out.println("NO");
                            }
                        }
                    }
                }
            }
            vote = n.nextLine();
        }
        //pardazesh
        boolean die = false ;
        int whokill = -1 ;
        for (int i = 0; i <counter ; i++) {
            Player x =player[i];
            if(x instanceof Mafia && x.isAlive())
                searchPlayer(((Mafia) x).getWhoDie()).increase_vote_Count();
        }
        Player[] kill = new Player[max];
        int counter_kill = 0;
        kill[counter_kill++] = max_player(0);
        for (int i = 1; i <counter ; i++) {
            if(!kill[0].getName().equals(max_player(i).getName()))
                kill[counter_kill++]=max_player(i);
        }
        if(counter_kill==2){
            for (int i = 0; i <counter_kill ; i++)
                if(kill[i].getName().equals(heal)){
                    whokill = 1-i;
                    die = true ;
                }
        }else if (counter_kill==1){
            if(!kill[0].getName().equals(heal)){
                die  = true;
                whokill = 0;
            }
        }
        cal_state();
        System.out.println("Day " + dayCounter++);
        for (int i = 0; i <counter_kill ; i++) {
            if(kill[i].getVote_Count()>0)
                System.out.println("mafia tried to kill "+kill[i]);
        }
        if(die)
            System.out.println(kill[whokill]+" was killed");
        else
            System.out.println("nobody kill");
        System.out.println("Silenced "+silence);
        for (int i = 0; i <counter ; i++){
            player[i].resetVote_Count();
            Player x = player[i];
            if(x instanceof Mafia)
                ((Mafia) x).resetWhoDie();
            if(x instanceof Detective)
                ((Detective) x).resetDetect();
        }
        Day();
    }

    public Player max_player(int h){
        Player max_player = player[h];
        for (int i = 1; i <counter ; i++) {
            if(player[i].getVote_Count()>max_player.getVote_Count())
                max_player = player[i];
        }
        return max_player;
    }

    public int[] cal_state(){
        int mafia = 0;
        int villager =0;
        int Joker = 0;
        for (int i = 0; i <counter ; i++) {
            Player x = player[i];
            if(x instanceof Mafia && x.isAlive())
                mafia++;
            else if(x instanceof Villager && x.isAlive())
                villager++;
            else if(x instanceof Joker && x.isAlive())
                Joker++;
        }
        if(mafia==0){
            System.out.println("Villagers won!");
            System.exit(0);
        }
        if(villager<=mafia){
            System.out.println("Mafia won!");
            System.exit(0);
        }
        int[] x = new int[3];
        x[0]=mafia;
        x[1]=villager;
        x[2]=Joker;
        return x;
    }

    public void get_game_state(){
        int[] y = cal_state();
        System.out.println("Mafia: "+y[0]);
        System.out.println("Villager: "+y[1]);
        System.out.println("Joker: "+y[2]);
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
