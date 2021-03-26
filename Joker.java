public class Joker extends Player {
    public Joker(String name) {
        super(name);
        jocker = true ;
    }
    public void set(){}
    @Override
    public String getRole() {
        return "Joker";
    }
}
