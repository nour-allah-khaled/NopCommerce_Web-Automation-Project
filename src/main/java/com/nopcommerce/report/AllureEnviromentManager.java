package com.nopcommerce.report;

import com.google.common.collect.ImmutableMap;
import com.nopcommerce.utils.logs.LogsManager;

import java.io.File;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static com.nopcommerce.datareader.PropertyReader.getProperty;

public class AllureEnviromentManager {
    public static void setEnvironmentVariables() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("OS", getProperty("os.name"))
                        .put("Java version:", getProperty("java.runtime.version"))
                        .put("Browser", getProperty("browserType"))
                        .put("Execution Type", getProperty("executionType"))
                        .put("URL", getProperty("baseUrlWeb"))
                        .build(), String.valueOf(AllureConstants.RESULTS_FOLDER) + File.separator
        );
        LogsManager.info("Allure environment variables set.");
        AllureBinaryManager.downloadAndExtract();
    }
}
