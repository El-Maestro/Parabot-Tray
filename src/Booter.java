import org.parabot.environment.tray.TrayNotifier;
import org.parabot.environment.tray.TrayUI;

public class Booter {

	public static void main(String[] args) {
		playAsClient();
	}

	private static void playAsClient(){
		TrayUI trayUI = TrayUI.getInstance();
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		playAsScript(trayUI.getTrayController("Script"));
	}

	private static void playAsScript(TrayNotifier notifier){
		notifier.notifyUser("Welcome to my script");
		// As you can see, I can call the interface method, but I cannot call any other method from the TrayController, like #messageUser
	}

}
