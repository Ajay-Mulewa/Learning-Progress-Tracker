package tracker;

public enum Operation {

    ADD_STUDENT("add students"),
    ADD_POINTS("add points"),
    STATISTICS("statistics"),
    NOTIFY("notify"),
    BACK("back"),
    FIND("find"),
    LIST("list"),
    EXIT("exit");

    public final String command;

    Operation(String command) {
        this.command = command;
    }
}
