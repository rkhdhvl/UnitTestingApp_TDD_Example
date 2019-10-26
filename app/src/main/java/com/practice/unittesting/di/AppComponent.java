package com.practice.unittesting.di;

import android.app.Application;

import com.practice.unittesting.BaseApplicationClass;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                AppModule.class,
                ActivityBuildersModule.class,
                ViewModelFactoryModule.class
        }
)
public interface AppComponent extends AndroidInjector<BaseApplicationClass> {
        @Component.Builder
        interface Builder{
                // Binding the application instance to the component
                @BindsInstance
                Builder myDaggerApplication(Application application);
                AppComponent build();
        }
}
