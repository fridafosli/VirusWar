package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;
import java.util.List;

public class Tutorial extends State {

    protected Stage stage;
    protected Skin skin;

    private TextButton backBtn;
    private TextButton nextBtn;
    private TextButton prevBtn;

    private List<Image> pages = new ArrayList<Image>();
    private int page = 0;


    protected Tutorial(final GameStateManager gsm) {
        super(gsm);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        //skin.getFont("default-font").getData().setScale(scale);
        stage = new Stage(new ScreenViewport());

        // Creating the back button
        backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - 50);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Settings", "clicked");
                gsm.pop();
            }
        });
        stage.addActor(backBtn);

        // creating the images in the tutorial
        Image img1 = new Image(new Texture(Gdx.files.internal("badlogic.jpg")));
        img1.setSize(Gdx.graphics.getWidth() * 2/3, Gdx.graphics.getHeight() * 2/3);
        img1.setPosition(Gdx.graphics.getWidth() / 2 - img1.getWidth() / 2, Gdx.graphics.getHeight() / 2 - img1.getHeight() / 2);
        pages.add(img1);

        Image img2 = new Image(new Texture(Gdx.files.internal("settings.png")));
        img2.setSize(Gdx.graphics.getWidth() * 2/3, Gdx.graphics.getHeight() * 2/3);
        img2.setPosition(Gdx.graphics.getWidth() / 2 - img2.getWidth() / 2, Gdx.graphics.getHeight() / 2 - img2.getHeight() / 2);
        pages.add(img2);

        // Setting pages to not visible
        for (Image page : pages ) {
            stage.addActor(page);
            page.setVisible(false);
        }
        // Setting first page to visible
        pages.get(page).setVisible(true);

        // Setting up the next button
        nextBtn = new TextButton("Next", skin);
        nextBtn.setPosition(Gdx.graphics.getWidth()/2, 20);
        nextBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("next", "clicked");
                if (page < pages.size() - 1 )  {
                    pages.get(page).setVisible(false);
                    page++;
                    pages.get(page).setVisible(true);
                    updateButtonsDisabled();
                }
            }
        });
        stage.addActor(nextBtn);

        // Setting up the prev button
        prevBtn = new TextButton("previous", skin);
        prevBtn.setPosition(Gdx.graphics.getWidth()/2- prevBtn.getWidth() - 50, 20);
        prevBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("prev", "clicked");
                if (page > 0 )  {
                    pages.get(page).setVisible(false);
                    page--;
                    pages.get(page).setVisible(true);
                    updateButtonsDisabled();
                }
            }
        });
        prevBtn.setDisabled(true);
        stage.addActor(prevBtn);

        // Set the input processor
        Gdx.input.setInputProcessor(stage);
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

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {

    }

    private void updateButtonsDisabled() {
        if ( page == 0 )
            prevBtn.setDisabled(true);
        else
            prevBtn.setDisabled(false);
        if (page == pages.size()-1)
            nextBtn.setDisabled(true);
        else
            nextBtn.setDisabled(false);
    }
}
