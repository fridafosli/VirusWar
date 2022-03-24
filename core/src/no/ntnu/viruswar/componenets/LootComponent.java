package no.ntnu.viruswar.componenets;

import com.badlogic.ashley.core.Component;

public class LootComponent implements Component {

    // indicates value gained/lost if absorbed
    private int points; //public or getter?
    // given or constant?

    public LootComponent(int points){
        this.points = points;
    }
}
