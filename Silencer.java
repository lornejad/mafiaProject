public class Silencer extends Mafia{
    private String silence;

    public Silencer(String name) {
        super(name);
    }

    public String getSilence() {
        return silence;
    }

    public void setSilence(String silence) {
        this.silence = silence;
    }
}
