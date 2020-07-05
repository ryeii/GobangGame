import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Created by Ryan An
 * Date: 2020/7/4 1:01 下午
 */

public class chessPane extends Pane
{
    public Canvas canvas;
    public GraphicsContext gc;
    public Gobang gobang;

    public chessPane(Gobang gobang)
    {
        this.gobang = gobang;
        drawPane(this.gobang.celllen);
        drawChess(this.gobang.celllen);
        drawCurrentPlayer();
        getChildren().add(canvas);
    }

    public void drawPane(double cell)
    {
        canvas = new Canvas(800,700);
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        gc.setStroke(Color.BLACK);
        for(int i = 0; i < gobang.getYGrid() - 1; i++)
        {
            for(int j = 0; j < gobang.getXGrid() - 1; j++)
            {
                gc.strokeRect(100+i*cell,100+cell*j,cell,cell);
            }
        }
    }

    public void drawChess(double cell)
    {
        int[][] chess = gobang.getChess();

        for (int i = 0; i < gobang.getYGrid(); i++)
        {
            for (int j = 0; j < gobang.getXGrid(); j++)
            {
                if(chess[i][j] != 0)
                {
                    if(chess[i][j] == 1)
                    {
                        gc.setFill(Color.BLACK);
                        gc.fillOval(100+i*cell-cell/2,100+j*cell-cell/2, cell, cell);
                    }
                    else
                    {
                        gc.setFill(Color.WHITE);

                        gc.fillOval(100+i*cell-cell/2,100+j*cell-cell/2, cell, cell);
                        gc.strokeOval(100+i*cell-cell/2,100+j*cell-cell/2, cell, cell);
                    }
                }
            }
        }
    }

    public void drawCurrentPlayer()
    {
        double cell = this.gobang.celllen;
        if(this.gobang.getCurrentPlayer())
        {
            gc.setFill(Color.BLACK);
        }
        else
        {
            gc.setFill(Color.WHITE);
        }
        gc.fillOval(100+this.gobang.getYGrid()*cell-cell/2,100, cell, cell);
    }
}
