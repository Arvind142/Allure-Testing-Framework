package io.github.arvind142.framework.framework.constants;

import com.aventstack.extentreports.Status;

public class IconConstants {

    public static class TestStatus{
        private static final String pass = "<i class='fa fa-check'></i>";
        private static final String fail = "<i class='fa fa-cross'></i>";
        private static final String others = "<i class='fa fa-question'></i>";

        private TestStatus(){}

        public static String getStatusIcon(Status status){
            switch (status){
                case PASS:{
                    return pass;
                }
                case FAIL:{
                    return fail;
                }
                default:{
                    return others;
                }
            }
        }
    }
    private IconConstants() {
    }

    public static class OS {
        private static final String windows = "<i class='fa fa-windows' ></i>";
        private static final String mac = "<i class='fa fa-apple' ></i>";
        private static final String linux = "<i class='fa fa-linux' ></i>";

        private OS() {
        }

        public static String getOSIcon(String operatingSystem) {
            if (operatingSystem.contains("win")) {
                return windows;
            } else if (operatingSystem.contains("nix") || operatingSystem.contains("nux") || operatingSystem.contains("aix")) {
                return linux;
            } else if (operatingSystem.contains("mac")) {
                return mac;
            }
            return operatingSystem;
        }
    }

    public static class Browser {
        private static final String prefix = "<i class='fa fa-";
        private static final String suffix = "'></i>";
        private static final String chrome = prefix + "chrome" + suffix;
        private static final String edge = prefix + "edge" + suffix;
        private static final String firefox = prefix + "firefox" + suffix;
        private static final String window = prefix + "window-maximize" + suffix;

        private Browser() {
        }

        public static String getBrowserIcon(String browserName) {
            browserName = browserName.toLowerCase();
            if (browserName.contains("chrome")) {
                return chrome;
            } else if (browserName.contains("fox")) {
                return firefox;
            } else if (browserName.contains("edge")) {
                return edge;
            }else{
                return window;
            }
        }
    }
}
