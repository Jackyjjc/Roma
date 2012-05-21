package model;

/**
 * All the functions a card needs when activates
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public interface ICardResources {

    public IResourceStorage getBank();

    public ICardStorage getDeckStorage();

    public ICardStorage getDiscardStorage();

    public IPlayer getCurrentPlayer();

    public ITurnMover getTurnMover();

    public InputHandler getInputHandler();

}
