public class Joker extends Player {
    public Joker(String name) {
        super(name);
        jocker = true ;
    }

    @Override
    public String getRole() {
        return "Joker";
    }
}
