package no.ntnu.viruswar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenu extends State {

        protected Stage stage;
        protected Skin skin;
        protected Table table;
        protected Sprite background;
        private  TextButton playBtn;
        private  TextButton tutorialBtn;
        private  TextButton settingsBtn;


        public MainMenu(GameStateManager gsm) {
            super(gsm);
            skin = new Skin(Gdx.files.internal("uiskin.json"));
            stage = new Stage(new ScreenViewport());
//
            table = new Table();
            table.setWidth(stage.getWidth());
            table.align(Align.center|Align.top);

            table.setPosition(0, Gdx.graphics.getHeight());



            //InputMultiplexer im = new InputMultiplexer(stage,this);


            playBtn = new TextButton("Play", skin);
            tutorialBtn = new TextButton("Tutorial", skin);
            settingsBtn = new TextButton("Settings", skin);

            settingsBtn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.log("Settings", "clicked");
                   // gsm.push(new SettingsMenu(gsm));
                }
            });
//
            table.padTop(150);
            table.add(playBtn).padBottom(30);
            table.row();
            table.add(tutorialBtn).padBottom(30);
            table.row();
            table.add(settingsBtn);

            stage.addActor(table);
            Gdx.input.setInputProcessor(stage);

            background = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
            background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        @Override
        protected void handleInput() {

        }

        @Override
        public void update(float dt) {

        }

        public void render(SpriteBatch sb) {
            // System.out.println("main "+ gsm.peek());

//            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//            sb.begin();
//            background.draw(sb);
//            sb.end();

            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();


        }

        @Override
        public void dispose() {

        }

    }
