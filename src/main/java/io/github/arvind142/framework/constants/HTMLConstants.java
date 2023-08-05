package io.github.arvind142.framework.constants;

public class HTMLConstants {
    public static final class ResultHtml{

        private static final String DETAILS_OPENING = "<details>";
        private static final String DETAILS_CLOSING = "</details>";
        private static final String SUMMARY_OPENING = "<summary>";
        private static final String SUMMARY_CLOSING = "</summary>";
        private static final String UL_OPENING = "<ul>";
        private static final String UL_CLOSING = "</ul>";
        private static final String LI_OPENING = "<li>";
        private static final String LI_CLOSING = "</li>";
        private static final String PARAGRAPH_OPENING = "<p>";
        private static final String PARAGRAPH_CLOSING = "</p>";
        private static final String BOLD_OPENING = "<b>";
        private static final String BOLD_CLOSING = "</b>";
        public static String getTestDescription(String[] testName,String testDescription){
            StringBuilder builder= new StringBuilder();
            builder.append(DETAILS_OPENING + SUMMARY_OPENING + BOLD_OPENING +"Name (Click To Expand) ⬇️⬇️⬇️ "+ BOLD_CLOSING + SUMMARY_CLOSING + PARAGRAPH_OPENING + UL_OPENING);
            for(String test:testName){
                builder.append(LI_OPENING + BOLD_OPENING +"Test Name: "+ BOLD_CLOSING +test+ LI_CLOSING);
            }
            builder.append(UL_CLOSING + PARAGRAPH_CLOSING + DETAILS_CLOSING);
            String descriptionTag = DETAILS_OPENING + SUMMARY_OPENING + BOLD_OPENING +"Description (Click To Expand) ⬇️⬇️⬇️ "+ BOLD_CLOSING + SUMMARY_CLOSING + (PARAGRAPH_OPENING + testDescription + PARAGRAPH_CLOSING + DETAILS_CLOSING);
            return (builder+descriptionTag).replace(" ","&nbsp;");
        }
        private ResultHtml(){}
    }
    private HTMLConstants(){}
}
