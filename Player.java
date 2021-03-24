class Player {
    private String name;
    protected boolean jocker = false;
    protected boolean wakeup = false;
    protected boolean alive = true ;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
