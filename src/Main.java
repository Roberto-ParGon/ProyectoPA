import com.formdev.flatlaf.IntelliJTheme;
import view.LoginView;

public class Main{

    public static void main(String[] args){
        //FlatDarculaLaf.setup();
        IntelliJTheme.setup(Main.class.getResourceAsStream("./resources/theme/Gradianto_midnight_blue.theme.json"));
        LoginView loginView = new LoginView();
        loginView.setVisible(true);

    }
}