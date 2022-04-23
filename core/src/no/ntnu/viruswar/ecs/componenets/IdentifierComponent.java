package no.ntnu.viruswar.ecs.componenets;

import com.badlogic.ashley.core.Component;

public class IdentifierComponent implements Component {
    public final String id;

    public IdentifierComponent(String id) {
        this.id = id;
    }
}
