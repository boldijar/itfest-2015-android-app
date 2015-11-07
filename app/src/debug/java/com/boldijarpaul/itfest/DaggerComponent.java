package com.boldijarpaul.itfest;

import com.boldijarpaul.itfest.data.DebugDataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by razvan on 10/27/15.
 */
@Singleton
@Component(modules = {DaggerAppModule.class, DebugDataModule.class})
public interface DaggerComponent extends DaggerGraph {

    final class Initializer {
        static DaggerGraph init(DaggerApp app) {
            return DaggerDaggerComponent.builder()
                    .daggerAppModule(new DaggerAppModule(app))
                    .build();
        }

        private Initializer() {
        } // No instances
    }
}

