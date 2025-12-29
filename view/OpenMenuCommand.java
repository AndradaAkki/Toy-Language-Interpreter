package view;

public class OpenMenuCommand extends Command {
    private final ProgramView menu;

    public OpenMenuCommand(String key, String description, ProgramView menu) {
        super(key, description);
        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.show();
    }
}
