public class BulletProof extends Villager {
    private boolean die = false ;

    public BulletProof(String name) {
        super(name);
    }

    @Override
    public String getRole() {
        return "BulletProof";
    }

    public boolean isDie() {
        return die;
    }

    public void setDie() {
        this.die = true;
    }
}
