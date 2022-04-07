package no.ntnu.viruswar;

import no.ntnu.viruswar.Data.Player;

public interface FireBaseInterface {


    public void SomeFunction();

    public void FirstFireBaseTest();

    public void SetOnValueChangedListener(DataHolderClass dataholder);

    public void SetValueInDb(String target, String value);

    public void AddPlayerToGame(String gamePin, Player player);

    public void RemovePlayerFromGame(String gamePin, String playerId);

    public void SetChildEventListener(DataHolderClass dataholder, String gamePin);

}