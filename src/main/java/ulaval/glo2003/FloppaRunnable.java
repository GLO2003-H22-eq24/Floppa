package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import ulaval.glo2003.floppa.app.api.HttpServerConfig;
import ulaval.glo2003.floppa.app.api.HttpServerFactory;

import java.io.IOException;

public class FloppaRunnable implements Runnable {
    private static final int DEFAULT_PORT = 8080;
    private final int port;
    private HttpServer httpServer;

    public FloppaRunnable(int port) {
        this.port = port;
    }

    public static void main(String[] args){
        FloppaRunnable floppaRunnable;
        if (args.length > 0) {
            floppaRunnable = new FloppaRunnable(Integer.parseInt(args[0]));
        } else{
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
