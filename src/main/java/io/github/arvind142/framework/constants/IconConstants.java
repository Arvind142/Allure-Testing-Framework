package io.github.arvind142.framework.constants;

import com.aventstack.extentreports.Status;

public class IconConstants {

    public static class TestStatus{
        private static final String PASS = "<i class='fa fa-check'></i>";
        private static final String FAIL = "<i class='fa fa-cross'></i>";
        private static final String OTHERS = "<i class='fa fa-question'></i>";

        private TestStatus(){}

        public static String getStatusIcon(Status status){
            switch (status){
                case PASS:{
                    return PASS;
                }
                case FAIL:{
                    return FAIL;
                }
                default:{
                    return OTHERS;
                }
            }
        }
    }
    private IconConstants() {
    }

    public static class OS {
        private static final String WINDOWS = "<i class='fa fa-windows' ></i>";
        private static final String MAC = "<i class='fa fa-apple' ></i>";
        private static final String LINUX = "<i class='fa fa-linux' ></i>";

        private OS() {
        }

        public static String getOSIcon(String operatingSystem) {
            if (operatingSystem.contains("win")) {
                return WINDOWS;
            } else if (operatingSystem.contains("nix") || operatingSystem.contains("nux") || operatingSystem.contains("aix")) {
                return LINUX;
            } else if (operatingSystem.contains("mac")) {
                return MAC;
            }
            return operatingSystem;
        }
    }

    public static class Browser {
        private static final String PREFIX = "<i class='fa fa-";
        private static final String SUFFIX = "'></i>";
        private static final String CHROME = PREFIX + "chrome" + SUFFIX;
        private static final String EDGE = PREFIX + "edge" + SUFFIX;
        private static final String FIREFOX = PREFIX + "firefox" + SUFFIX;
        private static final String WINDOW = PREFIX + "window-maximize" + SUFFIX;

        private Browser() {
        }

        public static String getBrowserIcon(String browserName) {
            browserName = browserName.toLowerCase();
            if (browserName.contains("chrome")) {
                return CHROME;
            } else if (browserName.contains("fox")) {
                return FIREFOX;
            } else if (browserName.contains("edge")) {
                return EDGE;
            }else{
                return WINDOW;
            }
        }
    }
}
