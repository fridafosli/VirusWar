package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.utils.Constants;

public class TutorialScreen extends ScreenContext {

    private final TextButton backBtn;
    private final TextButton nextBtn;
    private final TextButton prevBtn;
    private final List<Image> pages = new ArrayList<Image>();
    protected Stage stage;
    protected Skin skin;
    private int page = 0;


    protected TutorialScreen(final Context context) {
        super(context);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().setScale(Constants.FONT_SCALE);
        stage = new Stage(new ScreenViewport());

        // Creating the back button
        backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - backBtn.getHeight());
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Settings", "clicked");
                context.getScreens().pop();
            }
        });
        stage.addActor(backBtn);

        // creating the images in the tutorial
        for (int i = 0; i < 5; i++) {
            Image img1 = new Image(new Texture(Gdx.files.internal("tutImg" + i + ".png")));
            img1.setSize(Gdx.graphics.getWidth() * 2 / 3f, Gdx.graphics.getHeight() * 2 / 3f);
            img1.setPosition(Gdx.graphics.getWidth() / 2f - img1.getWidth() / 2, Gdx.graphics.getHeight() / 2f - img1.getHeight() / 2);
            pages.add(img1);

        }
        // Setting pages to not visible
        for (Image page : pages) {
            stage.addActor(page);
            page.setVisible(false);
        }
        // Setting first page to visible
        pages.get(page).setVisible(true);

        // Setting up the next button
        nextBtn = new TextButton("Next", skin);
        nextBtn.setPosition(Constants.SCREEN_WIDTH_SCALE * 60 - nextBtn.getWidth() /2f, Constants.SCREEN_HEIGHT_SCALE * 5);
        nextBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("next", "clicked");
                if (page < pages.size() - 1) {
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
        prevBtn.setPosition(Constants.SCREEN_WIDTH_SCALE * 40 - prevBtn.getWidth()/2f, Constants.SCREEN_HEIGHT_SCALE * 5);
        prevBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("prev", "clicked");
                if (page > 0) {
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
    public void render(float dt) {
        Gdx.gl.glClearColor(50/256f,50/256f,50/256f,0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(dt);
        stage.draw();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {

    }

    // Set buttons navigating through slides disabled when on top/bottom of stack
    private void updateButtonsDisabled() {
        prevBtn.setDisabled(page == 0);
        nextBtn.setDisabled(page == pages.size() - 1);
    }
}
