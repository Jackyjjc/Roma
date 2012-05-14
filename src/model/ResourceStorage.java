package model;

class ResourceStorage implements IResourceStorage {

    private IGameFinishManager g;
    private int money;
    private int vp;
    
    ResourceStorage(int money, int vp, IGameFinishManager g) {
        this.money = money;
        this.vp = vp;
        this.g = g;
    }
    
    public void transferMoney(IResourceStorage to, int amount) {
        
        if(this.getMoney() < amount) {
            amount = getMoney();
        }
        
        this.setMoney(this.getMoney() - amount);
        to.setMoney(to.getMoney() + amount);
        
    }

    public void transferVP(IResourceStorage to, int amount) {
        
        if(this.getVP() < amount) {
            amount = getVP();
        }
        
        this.setVP(this.getVP() - amount);
        to.setVP(to.getVP() + amount);
        
        if(getVP() == 0) {
            g.finish();
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
