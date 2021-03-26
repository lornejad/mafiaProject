public class Silencer extends Mafia{
    private String silence;
    private boolean do_silence = false ;

    public Silencer(String name) {
        super(name);
    }

    public void do_silence(){do_silence=true;}

    public boolean isDo_silence() {
        return do_silence;
    }

    public void end_night(){do_silence=false;}

    @Override
    public String getRole() {
        return "Silencer";
    }

    public String getSilence() {
        return silence;
    }

    public void setSilence(String silence) {
        this.silence = silence;
    }
}
