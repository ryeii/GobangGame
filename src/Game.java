import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by Ryan An
 * Date: 2020/7/4 3:41 下午
 */
public class Game extends Application {

    private int xGrid = 8;
    private int yGrid = 8;

    private boolean RobotBlack = false;
    private boolean RobotWhite = false;

    @FXML
    private Button RobotBlackButton;

    @FXML
    private Button RobotWhiteButton;

    @FXML
    private TextField XGrid;

    @FXML
    private TextField YGrid;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        stage.setTitle("Gobang Game");
        stage.setScene(new Scene(root, 700, 400));
        stage.show();


    }

    public void okMouseClicked(MouseEvent mouseEvent) throws Exception {

        try{
            xGrid = Integer.parseInt(XGrid.getText());
        } catch (Exception e)
        {

        }

        try{
            yGrid = Integer.parseInt(YGrid.getText());
        } catch (Exception e)
        {

        }

        startGame(new Stage());

    }

    public void BlackRobotClicked(MouseEvent mouseEvent) throws Exception {

        RobotBlack = !RobotBlack;

        if(RobotBlack)
        {
            RobotBlackButton.setText("Robotic Black Chess: YES");
        }
        else
        {
            RobotBlackButton.setText("Robotic Black Chess: NO");
        }

    }

    public void WhiteRobotClicked(MouseEvent mouseEvent) throws Exception {

        RobotWhite = !RobotWhite;

        if(RobotWhite)
        {
            RobotWhiteButton.setText("Robotic White Chess: YES");
        }
        else
        {
            RobotWhiteButton.setText("Robotic White Chess: NO");
        }

    }

    private void startGame(Stage stage) throws Exception {

        Gobang gobang = new Gobang(xGrid, yGrid, RobotBlack, RobotWhite);
        chessPane chessPane = new chessPane(gobang);
        Robot robot = new Robot(gobang);

        chessPane.setOnMouseClicked(new Controller(gobang, chessPane, robot));

        stage.setTitle("Gobang Game");
        stage.setScene(new Scene(chessPane, 200 + gobang.celllen * (yGrid - 1), 200 + gobang.celllen * (xGrid - 1)));
        stage.show();

    }

}
