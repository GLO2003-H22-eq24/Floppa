package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import ulaval.glo2003.floppa.app.api.HttpServerConfig;
import ulaval.glo2003.floppa.app.api.HttpServerFactory;

import java.io.IOException;

public class FloppaRunnable implements Runnable {
    //TODO: Probablement pas comme ca que tu recupere le port
    //#4 https://devcenter.heroku.com/articles/preparing-a-codebase-for-heroku-deployment
    private static final String DEFAULT_PORT_AS_STRING = System.getenv("PORT");
    private static final int DEFAULT_PORT = DEFAULT_PORT_AS_STRING == null || DEFAULT_PORT_AS_STRING.isEmpty() ? 8080
            : Integer.parseInt(DEFAULT_PORT_AS_STRING);
    private final int port;
    private HttpServer httpServer;

    public FloppaRunnable(int port) {
        this.port = port;
    }

    public static void main(String[] args){
        FloppaRunnable floppaRunnable;
        if (args.length > 0) {
            floppaRunnable = new FloppaRunnable(Integer.parseInt(args[0]));
        } else {
            floppaRunnable = new FloppaRunnable(DEFAULT_PORT);
        }
        floppaRunnable.run();
    }

    @Override
    public void run() {
        try {
            this.httpServer = new HttpServerFactory(new HttpServerConfig()).createLocalServer(this.port);
            this.httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpServer getHttpServer() {
        return httpServer;
    }
}
