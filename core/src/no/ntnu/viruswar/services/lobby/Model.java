package no.ntnu.viruswar.services.lobby;

public class Model {
    protected Boolean isHost;
    protected String pin;
    protected String playerId;

    public void set(Boolean isHost, String pin, String playerId) {
        this.isHost = isHost;
        this.pin = pin;
        this.playerId = playerId;
    }

    public Boolean getHost() {
        return isHost;
    }

    public String getPin() {
        return pin;
    }

    public String getPlayerId() {
        return playerId;
    }
}
