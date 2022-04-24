package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.assets.AssetManager;
import no.ntnu.viruswar.services.lobby.LobbyController;
import no.ntnu.viruswar.utils.Constants;

public class CustomizationScreen extends MenuBaseScreen {
    private final LobbyController lobby;
    protected Sprite playerVirus;
    private TextButton backBtn;
    private TextButton colorChangePlus;
    private TextButton colorChangeMinus;
    private TextButton submitBtn;
    private TextField usernameInput;
    private int virusIndex = 0;

    public CustomizationScreen(final Context context, final LobbyController lobby) {
        super(context);
        this.lobby = lobby;
        setPlayerVirus(virusIndex);

        // Setting up the back button
        backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - backBtn.getHeight());
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("back", "clicked");
                context.getScreens().pop();
            }
        });
        stage.addActor(backBtn);

        //Sets up color change button (right)
        colorChangePlus = new TextButton("   >  ", skin);
        colorChangePlus.setPosition(Constants.SCREEN_WIDTH_SCALE * 90,
                Gdx.graphics.getHeight() / 2f - colorChangePlus.getHeight() / 2);
        colorChangePlus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setPlayerVirus(1);
            }
        });
        stage.addActor(colorChangePlus);

        //Sets up color change button (left)
        colorChangeMinus = new TextButton("  <   ", skin);
        colorChangeMinus.setPosition(Constants.SCREEN_WIDTH_SCALE * 45,
                Gdx.graphics.getHeight() / 2f - colorChangeMinus.getHeight() / 2);
        colorChangeMinus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setPlayerVirus(-1);
            }
        });
        stage.addActor(colorChangeMinus);


        // Sets up submit button
        submitBtn = new TextButton("Submit", skin);
        submitBtn.setColor(Color.RED);
        submitBtn.setPosition(Gdx.graphics.getWidth() / 2f - submitBtn.getWidth() / 2, 0);
        submitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                lobby.setNickname(usernameInput.getText());
                lobby.setSkin(virusIndex);
                context.getScreens().pop();
            }
        });

        stage.addActor(submitBtn);

        // Sets up labels
        Label label = new Label("Customize Avatar:", skin);
        label.setPosition(Gdx.graphics.getWidth() / 2f - label.getWidth() / 2, Gdx.graphics.getHeight() - label.getHeight());
        stage.addActor(label);
        Label usernameLabel = new Label("Edit username:", skin);

        //Sets username text field
        usernameInput = new TextField("username", skin);
        usernameInput.setText(lobby.getUserPlayer().getName());

        // Put actors in to table
        table.setWidth(Constants.SCREEN_WIDTH_SCALE * 30);

        table.padTop(Constants.SCREEN_HEIGHT_SCALE * 40);
        table.add(usernameLabel);
        table.row();
        table.add(usernameInput).padBottom(Constants.SCREEN_HEIGHT_SCALE * 10);
    }

    private void setPlayerVirus(int increment) {
        virusIndex = (virusIndex + increment) % 6;
        while (virusIndex < 0) virusIndex += 6;
        playerVirus = new Sprite(AssetManager.getInstance().getViruses(virusIndex));
    }

    @Override
    public void render(float dt) {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(dt);


        SpriteBatch sb = context.getBatch();
        sb.begin();
        sb.draw(playerVirus, (int) Constants.SCREEN_WIDTH_SCALE * 50, (int) Constants.SCREEN_HEIGHT_SCALE * 50 - Constants.SCREEN_WIDTH_SCALE * 40 / 2,
                Constants.SCREEN_WIDTH_SCALE * 40, Constants.SCREEN_WIDTH_SCALE * 40);
        sb.end();
        stage.draw();


    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }
}
