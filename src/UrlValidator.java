import java.util.Arrays;

public class UrlValidator {
    private static boolean validateUrl(String url) {
        return checkProtochol(url) && checkDomain(url);
    }

    private static boolean checkDomain(String url) {
        String urlWithoutProtocol = url.substring(8);
        String[] resources = urlWithoutProtocol.split("/");
        String hostName = resources.length == 0 ? urlWithoutProtocol : resources[0];
        if (hostName.isEmpty() || hostName.startsWith(".") | hostName.endsWith(".")) {
            return false;
        }
        String[] subDomains = hostName.split("\\.");
        if (subDomains.length == 0) {
            return false;
        }

        return Arrays.stream(subDomains).allMatch(subDomain -> !subDomain.isEmpty());
    }

    private static boolean checkProtochol(String url) {
        return url.startsWith("https://");
    }

    public static void main(String[] args) {
        String[][] testUrls = { { "https://google.com", "true" }, { "http://google.com", "false" },
                { "https:///google.com", "false" }, { "https://google.", "false" }, { "https://.com", "false" },
                { "https://google.com/", "true" }, { "https://google.com/alpha", "true" } };

        Arrays.stream(testUrls).forEach(url -> {
            if (validateUrl(url[0]) != Boolean.parseBoolean(url[1]))
                throw new RuntimeException(url[0]);
        });
        System.out.println("good code");
    }
}
