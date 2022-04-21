package no.ntnu.viruswar.ecs.componenets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class LeadTextComponent implements Component {
    public String leadPlayer;
    public Vector3 position = new Vector3(0,0,0);
}
