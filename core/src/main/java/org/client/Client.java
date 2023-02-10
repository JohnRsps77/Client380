package org.client;

import com.badlogic.gdx.Game;
import org.client.screens.FirstScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Client extends Game {
    @Override
    public void create() {
        setScreen(new FirstScreen());
    }
}
