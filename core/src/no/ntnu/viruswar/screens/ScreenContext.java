package no.ntnu.viruswar.screens;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.screen.Screen;

/*Returns context, so that it can be implemented by all screens to update state*/
abstract public class ScreenContext extends Screen {

    final Context context;

    protected ScreenContext(Context context) {
        super(context.getScreens());
        this.context = context;
    }
}
