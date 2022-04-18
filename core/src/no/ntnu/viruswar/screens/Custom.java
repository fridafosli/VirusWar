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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Arrays;
import java.util.List;
import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.data.Player;

public class Custom extends MenuBaseScreen{
    protected Stage stage;
    protected Sprite playerVirus;
    private Color color;
    private TextButton backBtn;
    private TextButton colorChangePlus;
    private TextButton colorChangeMinus;
    private TextButton submitBtn;
    private int colorIndex;
    private TextField usernameInput;
    private Player player;
    private Context context;


    public Custom(final Context context, final Player player){
        super(context);
        this.context=context;
        this.player= player;

        //Sets the stage
        stage = new Stage(new ScreenViewport());

        //Initializes the player color
        setPlayerVirus(true,false);


        //Sets username text field
        usernameInput = new TextField("username", skin);
        usernameInput.setText(player.getName());
        usernameInput.setPosition(400,Gdx.graphics.getHeight() - 600);
        stage.addActor(usernameInput);


        // Setting up the back button
        backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - 70);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("back", "clicked");
                context.getScreens().pop();
            }
        });

        stage.addActor(backBtn);

        //Sets up color change button (right)
        colorChangePlus = new TextButton(">", skin);
        colorChangePlus.setPosition(Gdx.graphics.getWidth()-300, Gdx.graphics.getHeight() - 600);

        colorChangePlus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               setPlayerVirus(false, true);

            }
        });
        stage.addActor(colorChangePlus);

        //Sets up color change button (left)
        colorChangeMinus = new TextButton("<", skin);
        colorChangeMinus.setPosition(Gdx.graphics.getWidth()-1350, Gdx.graphics.getHeight() - 600);
        colorChangeMinus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setPlayerVirus(false,false);
            }
        });
        stage.addActor(colorChangeMinus);



        Label label= new Label("Customize Avatar",skin);
        label.setPosition(400,Gdx.graphics.getHeight() - 200);
        stage.addActor(label);
        Label usernameLabel= new Label("Edit username",skin);
        usernameLabel.setPosition(400,Gdx.graphics.getHeight() - 500);
        stage.addActor(usernameLabel);
        //Sets up submit button
        submitBtn= new TextButton("Submit", skin);
        submitBtn.setPosition(400,Gdx.graphics.getHeight() - 750);
        submitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player.setColor(color.toString());
                player.setName(usernameInput.getText());
            }
        });
        stage.addActor(submitBtn);

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
    public void render(float dt) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(dt);

        SpriteBatch sb= context.getBatch();
        sb.begin();
        sb.draw(playerVirus, (int)(Gdx.graphics.getWidth()/2)-50,(int)(Gdx.graphics.getHeight()/2)-400, 800,800);
        sb.setColor(color);
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
