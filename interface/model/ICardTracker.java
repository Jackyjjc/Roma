package model;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 21/05/12
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ICardTracker {

    //Gets the lives of a Kat at a certain index
    public int getLives(int index);

    public void addKat(int index, int lives);

}
