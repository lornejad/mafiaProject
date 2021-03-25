public class Doctor extends villager {
    private String healName;
    public Doctor(String name){
        super(name);
        wakeup = true ;
    }
    public void setHealName(String healName) {
        this.healName = healName;
    }

    public String getHealName() {
        return healName;
    }
}
