public class Villager extends Player{
    public Villager(String name) {
        super(name);
    }

    @Override
    public String getRole() {
        return "Villager";
    }
}
