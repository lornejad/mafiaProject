public class Order {
    int max = 100 ;
    Player[] player = new Player[max];
    int counter = 0;
    public void assign_role(String name,String role){
        switch (role){
            case "Joker" : player[counter++]=new Joker(name);break;
            case "villager" : player[counter++]=new villager(name);break;
            case "detective" : player[counter++]=new Detective(name);break;
            case "doctor" : player[counter++]=new Doctor(name);break;
            case "bulletproof" : player[counter++]=new BulletProof(name);break;
            case "mafia" : player[counter++]=new Mafia(name);break;
            case "godfather" : player[counter++]=new GodFather(name);break;
            case "silencer" : player[counter++]=new Silencer(name);break;
            default:System.out.println("role not found");
        }
    }
}
