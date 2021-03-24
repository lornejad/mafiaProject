public class Doctor extends Citizenry {
    private String healName;
    public Doctor(){
        wakeup = true ;
    }
    public void setHealName(String healName) {
        this.healName = healName;
    }

    public String getHealName() {
        return healName;
    }
}
