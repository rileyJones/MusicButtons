import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MusicPanelListener implements ActionListener {
    private MusicPanel delReference;
    public MusicPanelListener(MusicPanel delReference){
        this.delReference = delReference;
    }
    public MusicPanel getDelReference(){
        return delReference;
    }
}
