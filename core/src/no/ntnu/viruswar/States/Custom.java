package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import no.ntnu.viruswar.Data.Player;
import no.ntnu.viruswar.DataHolderClass;
import no.ntnu.viruswar.FireBaseInterface;

public class Custom extends State{
    protected Stage stage;
    protected Skin skin;
    protected Sprite playerVirus;
    private Color color;
    private TextButton backBtn;
    private TextButton colorChangePlus;
    private TextButton colorChangeMinus;
    private TextButton submitBtn;
    private int colorIndex;
    private TextField usernameInput;
    private Player player;
    private FireBaseInterface _FBIC;
    private DataHolderClass dataHolder;

    public Custom(final GameStateManager gsm, final Player player, String gamePin){
        super(gsm);
        this.player= player;
        _FBIC = gsm.get_FBIC();

        //Sets the stage
        stage = new Stage(new ScreenViewport());
        //Initializes the player color
        setPlayerVirus(true,false);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        //skin.getFont("default-font").getData().setScale(scale);

        //Sets username text field
        usernameInput = new TextField("username", skin);
        usernameInput.setPosition(120,Gdx.graphics.getHeight() - 200);
        stage.addActor(usernameInput);


        // Setting up the back button
        backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - 70);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.pop();
            }
        });
        stage.addActor(backBtn);

        //Sets up color change button (right)
        colorChangePlus = new TextButton(">", skin);
        colorChangePlus.setPosition(Gdx.graphics.getWidth()-185, Gdx.graphics.getHeight() - 200);

        colorChangePlus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               setPlayerVirus(false, true);

            }
        });
        stage.addActor(colorChangePlus);

        //Sets up color change button (left)
        colorChangeMinus = new TextButton("<", skin);
        colorChangeMinus.setPosition(310, Gdx.graphics.getHeight() - 200);
        colorChangeMinus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setPlayerVirus(false,false);
            }
        });
        stage.addActor(colorChangeMinus);



        Label label= new Label("Customize avatar",skin);
        label.setPosition(120, Gdx.graphics.getHeight()-100);
        stage.addActor(label);
        //Sets up submit button
        submitBtn= new TextButton("Submit", skin);
        submitBtn.setPosition(160, 100);
        submitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player.setColor(color.toString());
            }
        });
        stage.addActor(submitBtn);
        Gdx.input.setInputProcessor(stage);

        _FBIC.addPlayerToGame(gamePin, this.player);
    }
    private void setPlayerVirus(boolean initial, boolean add){

        playerVirus= new Sprite(new Texture("virus.png"));
        List<Color> colors= Arrays.asList(Color.BLUE,Color.PINK,Color.CYAN, Color.RED, Color.GREEN, Color.MAGENTA, Color.BROWN,
                Color.FIREBRICK, Color.FOREST, Color.PURPLE, Color.CORAL, Color.LIME, Color.SKY,Color.ORANGE, Color.OLIVE,Color.YELLOW, Color.VIOLET, Color.WHITE,Color.GOLDENROD, Color.SALMON, Color.MAROON, Color.NAVY);

        if(initial){
            colorIndex=colors.indexOf(Color.valueOf(player.getColor()));

        }
        else if(add){
            colorIndex=(colorIndex==colors.size()-1)?0:colorIndex+1;
        }
        else{
            colorIndex=(colorIndex==0)?colors.size()-1:colorIndex-1;

        }

        color= colors.get(colorIndex);


    }

    @Override
    protected InputProcessor getInputprosesspr() {
        return stage;
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float dt) {

    }

    public void render(SpriteBatch sb) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sb.draw(playerVirus, (int)(Gdx.graphics.getWidth()/2)-50,(int)(Gdx.graphics.getHeight()/2)-120, 250,250);
        sb.setColor(color);
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
