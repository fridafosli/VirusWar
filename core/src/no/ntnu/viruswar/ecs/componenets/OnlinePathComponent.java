package no.ntnu.viruswar.ecs.componenets;

import com.badlogic.ashley.core.Component;

public class OnlinePathComponent implements Component {

    public String pathName;

    public OnlinePathComponent(String pathName) {
        this.pathName = pathName;
    }
}
