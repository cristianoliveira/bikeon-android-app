package cc.bikeon.app;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;

/**
 * Custom Roboletric runner to work with Gradle.
 * Created by cristianoliveira on 23/08/15.
 */
public class BaseRoboletricTestRunner extends RobolectricTestRunner {

    public BaseRoboletricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        String buildVariant = (BuildConfig.FLAVOR.isEmpty() ? "" : BuildConfig.FLAVOR + "/") + BuildConfig.BUILD_TYPE;
        System.setProperty("android.package", BuildConfig.APPLICATION_ID);
        System.setProperty("android.manifest", "build/intermediates/manifests/full/" + buildVariant + "/AndroidManifest.xml");
        System.setProperty("android.resources", "build/intermediates/res/" + buildVariant);
        System.setProperty("android.assets", "build/intermediates/assets/" + buildVariant);
    }
}