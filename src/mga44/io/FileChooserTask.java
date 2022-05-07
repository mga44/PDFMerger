package mga44.io;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

import javax.swing.JFileChooser;

public class FileChooserTask implements Runnable {

	private String windowTitle;
	private Path selected;

	public FileChooserTask(String windowTitle) {
		this.windowTitle = windowTitle;
	}

	@Override
	public void run() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setDialogTitle(windowTitle);
		if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File selected = jfc.getSelectedFile();

			// TODO: needs fixing
			this.selected = Optional.ofNullable(selected).map(File::toPath).orElse(null);
		}
	}

	public Path getSelected() {
		return selected;
	}
}
