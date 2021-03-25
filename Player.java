abstract class Player {
    private String name;
    protected boolean jocker = false;
    protected boolean wakeup = false;
    protected boolean alive = true ;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name +"' , "+"role='"+getRole()+ '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public abstract String getRole();

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
