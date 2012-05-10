package model;

class ResourceStorage implements IResourceStorage {

    private int money;
    private int vp;
    
    ResourceStorage(int money, int vp) {
        this.money = money;
        this.vp = vp;
    }
    
    public void transferMoney(IResourceStorage to, int amount) {
        if(this.getMoney() >= amount) {
            this.setMoney(this.getMoney() - amount);
            to.setMoney(to.getMoney() + amount);
        }
    }

    public void transferVP(IResourceStorage to, int amount) {
        if(this.getVP() >= amount) {
            this.setVP(this.getVP() - amount);
            to.setVP(to.getVP() + amount);
        }
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
    public int getMoney() {
        return money;
    }

    public void setVP(int vp) {
        this.vp = vp;
    }
    
    public int getVP() {
        return vp;
    }
    
}
