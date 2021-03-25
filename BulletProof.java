public class BulletProof extends villager {
    private boolean die = false ;

    public BulletProof(String name) {
        super(name);
    }

    public boolean isDie() {
        return die;
    }

    public void setDie(boolean die) {
        this.die = die;
    }
}
