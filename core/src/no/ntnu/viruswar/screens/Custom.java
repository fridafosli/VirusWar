package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Arrays;
import java.util.List;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.data.Player;
import no.ntnu.viruswar.utils.Constants;

public class Custom extends MenuBaseScreen {
    protected Sprite playerVirus;
    private String color;
    private TextButton backBtn;
    private TextButton colorChangePlus;
    private TextButton colorChangeMinus;
    private TextButton submitBtn;
    private int colorIndex;
    private TextField usernameInput;
    private Player player;
    private Context context;


    public Custom(final Context context, final Player player) {
        super(context);
        this.context = context;
        this.player = player;

        //Initializes the player color
        setPlayerVirus(true, false);


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
                setPlayerVirus(false, true);

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
                setPlayerVirus(false, false);
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
                player.setColor(color.toString());
                player.setName(usernameInput.getText());
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
        usernameInput.setText(player.getName());

        // Put actors in to table
        table.setWidth(Constants.SCREEN_WIDTH_SCALE * 30);

        table.padTop(Constants.SCREEN_HEIGHT_SCALE * 40);
        table.add(usernameLabel);
        table.row();
        table.add(usernameInput).padBottom(Constants.SCREEN_HEIGHT_SCALE * 10);



    }

    private void setPlayerVirus(boolean initial, boolean add){


        List<String> colors= Arrays.asList("v1", "v2", "v3", "v4", "v5", "v6", "v7");

        if (initial) {
            colorIndex = colors.indexOf(player.getColor());

        } else if (add) {
            colorIndex = (colorIndex == colors.size() - 1) ? 0 : colorIndex + 1;
        } else {
            colorIndex = (colorIndex == 0) ? colors.size() - 1 : colorIndex - 1;

        }

        color= colors.get(colorIndex);
        playerVirus= new Sprite(new Texture(color+".PNG"));

    }

    @Override
    public void render(float dt) {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(dt);


        SpriteBatch sb= context.getBatch();
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
