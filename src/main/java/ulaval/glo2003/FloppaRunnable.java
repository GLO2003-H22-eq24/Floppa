package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import ulaval.glo2003.floppa.app.config.AppConfigFactory;
import ulaval.glo2003.floppa.app.config.ArgConfigResolver;
import ulaval.glo2003.floppa.app.config.DefaultConfigResolver;
import ulaval.glo2003.floppa.app.config.SystemConfigResolver;
import ulaval.glo2003.floppa.app.config.dto.AppConfigDto;
import ulaval.glo2003.floppa.app.api.HttpServerConfig;
import ulaval.glo2003.floppa.app.api.HttpServerFactory;

import java.io.IOException;

public class FloppaRunnable implements Runnable {
    private final AppConfigDto appConfigDto;
    private HttpServer httpServer;

    public FloppaRunnable(AppConfigDto appConfigDto) {
        this.appConfigDto = appConfigDto;
    }

    public static void main(String[] args) {
        AppConfigFactory appConfigFactory = new AppConfigFactory(new ArgConfigResolver(), new SystemConfigResolver(), new DefaultConfigResolver());
        FloppaRunnable floppaRunnable = new FloppaRunnable(appConfigFactory.createAppConfig(args));
        floppaRunnable.run();
    }

    @Override
    public void run() {
        try {
            this.httpServer = new HttpServerFactory(new HttpServerConfig(appConfigDto)).createLocalServer(appConfigDto.getHttpConfigDto().getPort());
            this.httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpServer getHttpServer() {
        return httpServer;
    }
}