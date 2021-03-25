public class Doctor extends Villager {
    private String healName;

    @Override
    public String getRole() {
        return "Doctor" ;
    }

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
