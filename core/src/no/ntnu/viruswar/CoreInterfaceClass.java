package no.ntnu.viruswar;


import no.ntnu.viruswar.Data.Player;

public class CoreInterfaceClass implements FireBaseInterface {

    @Override
    public void SomeFunction() {
        System.out.println("Wubbbadubbbbba");
    }

    @Override
    public void FirstFireBaseTest() {

    }

    @Override
    public void SetOnValueChangedListener(DataHolderClass datader) {

    }

    @Override
    public void SetValueInDb(String target, String value) {

    }

    @Override
    public void AddPlayerToGame(String gamePin, Player player) {

    }

    @Override
    public void RemovePlayerFromGame(String gamePin, String playerId) {

    }

    @Override
    public void SetChildEventListener(DataHolderClass dataholder, String gamePin) {

    }
}