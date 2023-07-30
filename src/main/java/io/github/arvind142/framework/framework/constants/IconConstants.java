package io.github.arvind142.framework.framework.constants;

public class IconConstants {
    public static final String ICON_SMILEY_PASS = "<i class='fa fa-smile-o' style='font-size:24px'></i>";
    public static final String ICON_SMILEY_SKIP = "<i class=\"fas fa-frown-open\"></i>";
    public static final String ICON_SMILEY_FAIL = "<i class='fa fa-frown-o' style='font-size:24px'></i>";

    public static final String ICON_Navigate_Right = "<i class='fa fa-arrow-circle-right' ></i>";
    public static final String ICON_LAPTOP = "<i class='fa fa-laptop' style='font-size:18px'></i>";
    public static final String ICON_BUG = "<i class='fa fa-bug' ></i>";

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
        private static final String suffix = "' aria-hidden='true'></i>";
        private static final String chrome = prefix + "chrome" + suffix;
        private static final String edge = prefix + "edge" + suffix;
        private static final String firefox = prefix + "firefox" + suffix;

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
            }
            return browserName;
        }
    }
}
