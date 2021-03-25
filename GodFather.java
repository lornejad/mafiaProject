public class GodFather extends Mafia {
    @Override
    public String getRole() {
        return "GodFather";
    }

    public GodFather(String name) {
        super(name);
        God = true ;
    }
}
