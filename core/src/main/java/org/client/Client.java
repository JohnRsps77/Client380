package org.client;

import com.badlogic.gdx.Game;
import org.client.net.ClientHandler;
import org.client.screens.FirstScreen;

import java.util.concurrent.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Client extends Game {

    private final ExecutorService networkService = Executors.newSingleThreadExecutor();

    @Override
    public void create() {
        setScreen(new FirstScreen());
        networkService.submit(new ClientHandler("localhost", 43594));
    }
}
