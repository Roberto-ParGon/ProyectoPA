import com.formdev.flatlaf.IntelliJTheme;
import model.Connection;
import view.LoginView;

public class Main {

    public static void main(String[] args) {
        //FlatDarculaLaf.setup();
        IntelliJTheme.setup(Main.class.getResourceAsStream("./resources/theme/Gradianto_midnight_blue.theme.json"));
        //IntelliJTheme.setup(Main.class.getResourceAsStream("./resources/theme/DraculaColorful.theme.json"));
        //IntelliJTheme.setup(Main.class.getResourceAsStream("./resources/theme/LightFlatTheme.theme.json"));
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
        Connection con = Connection.getInstance();

    }
}