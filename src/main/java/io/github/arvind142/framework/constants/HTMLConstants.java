package io.github.arvind142.framework.constants;

public class HTMLConstants {
    public static final class ResultHtml{

        private static final String detailsOpening = "<details>";
        private static final String detailsClosing = "</details>";
        private static final String summaryOpening = "<summary>";
        private static final String summaryClosing = "</summary>";
        private static final String ulOpening = "<ul>";
        private static final String ulClosing = "</ul>";
        private static final String liOpening = "<li>";
        private static final String liClosing = "</li>";
        private static final String paragraphOpening = "<p>";
        private static final String paragraphClosing = "</p>";
        private static final String boldOpening = "<b>";
        private static final String boldClosing = "</b>";
        public static String getTestDescription(String[] testName,String testDescription){
            StringBuilder builder= new StringBuilder();
            builder.append(detailsOpening+summaryOpening+boldOpening+"Name (Click To Expand) ⬇️⬇️⬇️ "+boldClosing+summaryClosing+paragraphOpening+ulOpening);
            for(String test:testName){
                builder.append(liOpening+boldOpening+"Test Name: "+boldClosing+test+liClosing);
            }
            builder.append(ulClosing+paragraphClosing+detailsClosing);
            String descriptionTag = detailsOpening+summaryOpening+boldOpening+"Description (Click To Expand) ⬇️⬇️⬇️ "+boldClosing+summaryClosing + (paragraphOpening + testDescription + paragraphClosing + detailsClosing);
            return (builder+descriptionTag).replace(" ","&nbsp;");
        }
    }
}
