package combate2000lasecuela;

import combate2000lasecuela.screen.MessageManager;

public class Gameflow {

    private MessageManager messageManager;

    public Gameflow() {
        messageManager = new MessageManager();

    }

    public void start(){
       messageManager.showInitMenu();
    }
}
