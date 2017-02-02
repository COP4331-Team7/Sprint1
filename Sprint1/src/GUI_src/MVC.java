package GUI_src;
import GUI_src.*;

public class MVC 
{
    public static void main(String[] args) 
    {
        View view = new View();
        Controller controller = new Controller( view );
    }
}