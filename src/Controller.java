import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;


public class Controller implements EventHandler<MouseEvent> {

    private Gobang gobang;
    private chessPane chessPane;
    private Robot robot;

    public Controller(Gobang gobang, chessPane chessPane, Robot robot)
    {
        this.chessPane = chessPane;
        this.gobang = gobang;
        this.robot = robot;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        double cell = this.gobang.celllen;

        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        int j = (int)((x-100+cell/2)/cell);
        int i = (int)((y-100+cell/2)/cell);

        try
        {
            gobang.move(i,j);
            chessPane.drawChess(cell);
            chessPane.drawCurrentPlayer();
        } catch (Exception ignored)
        {

        }

        int win = gobang.checkWin();
        if(win != 0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("WIN!!!");
            alert.setHeaderText("The Game Ended.");
            if(win == 1)
            {
                alert.setContentText("Player of the BLACK chess have won this round.");
            }
            if(win == 2)
            {
                alert.setContentText("Player of the WHITE chess have won this round.");
            }
            alert.showAndWait();
        }
        else
        {
            if(gobang.isCurrentPlayerRobot())
            {
                if(this.gobang.getCurrentPlayer())
                {
                    robot.AI(2);
                }
                else
                {
                    robot.AI(1);
                }
                gobang.move(robot.getY(),robot.getX());
                chessPane.drawChess(cell);
                chessPane.drawCurrentPlayer();
            }

            win = gobang.checkWin();
            if(win != 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("WIN!!!");
                alert.setHeaderText("The Game Ended.");
                if(win == 1)
                {
                    alert.setContentText("Player of the BLACK chess have won this round.");
                }
                if(win == 2)
                {
                    alert.setContentText("Player of the WHITE chess have won this round.");
                }
                alert.showAndWait();
            }
        }

    }
}
