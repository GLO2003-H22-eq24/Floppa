package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import ulaval.glo2003.floppa.app.repository.Environnement;
import ulaval.glo2003.floppa.app.api.HttpServerConfig;
import ulaval.glo2003.floppa.app.api.HttpServerFactory;

import java.io.IOException;
import java.security.InvalidParameterException;

public class FloppaRunnable implements Runnable {
    private static final int DEFAULT_PORT = 8080;
    private static final Environnement DEFAULT_ENV = Environnement.LOCAL;
    private final int port;
    private final Environnement environnement;
    private HttpServer httpServer;

    public FloppaRunnable(int port, Environnement environnement) {
        this.port = port;
        this.environnement = environnement;
    }

    public static void main(String[] args) {
        FloppaRunnable floppaRunnable;
        if (args.length > 0) {
            floppaRunnable = new FloppaRunnable(retrievePortArg(args), retrieveEnvArg(args));
        } else {
            floppaRunnable = new FloppaRunnable(DEFAULT_PORT, DEFAULT_ENV);
        }
        floppaRunnable.run();
    }

    private static Environnement retrieveEnvArg(String[] args) {
        try {
            return Environnement.toEnum(args[1]);
        } catch (Exception e) {
            throw new InvalidParameterException("Missing valid positional argument in -Denv=");
        }
    }

    private static int retrievePortArg(String[] args) {
        try {
            return Integer.parseInt(args[0]);
        } catch (Exception e) {
            throw new InvalidParameterException("Missing valid positional argument in -Dport=");
        }
    }

    @Override
    public void run() {
        try {
            this.httpServer = new HttpServerFactory(new HttpServerConfig(environnement)).createLocalServer(this.port);
            this.httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpServer getHttpServer() {
        return httpServer;
    }
}