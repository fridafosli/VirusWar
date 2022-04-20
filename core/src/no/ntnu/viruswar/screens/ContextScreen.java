package no.ntnu.viruswar.screens;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.screen.Screen;


abstract public class ContextScreen extends Screen {

    final Context context;

    protected ContextScreen(Context context) {
        super(context.getScreens());

        this.context = context;
    }
}
