package com.adam.stan;

import com.adam.stan.connection.Connector;
import com.adam.stan.threads.WorkerPool;
import com.adam.stan.view.PrimaryStageSceneChanger;

import javafx.application.Application;
import javafx.stage.Stage;

public class ClientApplication extends Application {
    public static final WorkerPool GLOBAL_WORKER_POOL = new WorkerPool();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationPrimaryStage.INSTANCE.setPrimaryStage(primaryStage);
        PrimaryStageSceneChanger changer = new PrimaryStageSceneChanger();
        changer.showLoginView();
    }

    @Override
    public void stop() throws Exception {
        Connector.disconnect();
        GLOBAL_WORKER_POOL.interruptAll();
    }

}
