package StateCodeAnalyze;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCSVBuilder<>();
    }
    public static ICSVBuilder createCommonsCSVBuilder() {
        return new OpenCSVBuilder<>();
    }
}
